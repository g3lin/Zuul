import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.*;
import javax.imageio.ImageIO;

// import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling (DB edited)
 * @version 1.0 (Jan 2003)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private CustomPanel aUI;
    private JButton    aButton1;
    private JButton    aButton2;
    private JButton    aButton3;
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
        Image vImage = null;
        try {
            vImage = ImageIO.read(new File(pImageName));
        } catch (IOException e) {
            
        }
        this.aUI.setBGImage(vImage);
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff );
        if ( ! pOnOff )
            this.aEntryField.getCaret().setBlinkRate( 0 );
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Le Manoir de WanderStock" );
        ImageIcon img = new ImageIcon("Images/icon.png");
        this.aMyFrame.setIconImage(img.getImage());
        this.aEntryField = new JTextField( 34 );
        this.aEntryField.setMaximumSize(new Dimension(1000,50));

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,50) );

        JPanel vPanel = new JPanel();
        vPanel.setLayout( new BoxLayout(vPanel, BoxLayout.PAGE_AXIS) );
        
        
        // PLACEMENT DE L'UI PRINCIPALE
        this.aUI = new CustomPanel();
        this.aUI.setPreferredSize( new Dimension(100,100) );
        vPanel.add( this.aUI);
        
        
        // PLACEMENT DES BOUTONS
        JPanel vButtons = new JPanel();
        this.aButton1 = new JButton("eat");
        vButtons.add( this.aButton1);
        this.aButton1.addActionListener(this);

        this.aButton2 = new JButton("look");
        vButtons.add( this.aButton2);
        this.aButton2.addActionListener(this);

        this.aButton3 = new JButton("back");
        vButtons.add( this.aButton3);
        this.aButton3.addActionListener(this);
        
        vButtons.setMaximumSize(vButtons.getPreferredSize());
        vPanel.add( vButtons);

        //PLACEMENT DE LA ZONE DE TEXTE
        vPanel.add( vListScroller);

        // PLACEMENT DE LA ZONE D'ENTRÉE TEXTE
        vPanel.add( this.aEntryField);

        // INITIALISATION DE LA FENETRE
        this.aMyFrame.getContentPane().add( vPanel);

        // add some event listeners to some components

        this.aMyFrame.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) { System.exit(0); }
            } );

        this.aEntryField.addActionListener( this );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed( final ActionEvent pEvent ) 
    {

        if (pEvent.getSource() == aEntryField){
            this.processCommand();
        }

        else if (pEvent.getSource() == (aButton1 )){
            String vCommand = pEvent.getActionCommand();
            this.aEngine.interpretCommand( vCommand );
        }

        else if (pEvent.getSource() == (aButton2 )){
            String vCommand = pEvent.getActionCommand();
            this.aEngine.interpretCommand( vCommand );
        }

    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
