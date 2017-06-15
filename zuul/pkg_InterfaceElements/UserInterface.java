
package pkg_InterfaceElements;
import pkg_EngineElements.GameEngine;
import pkg_EngineElements.Room;
import pkg_EngineElements.Item;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
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
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Stack; 

/**
 * Cette classe est le centre de l'interface graphique du jeu
 * Elle genere les boutons, interfaces et zones de texte du jeu
 * 
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextPane  aLog;
    private JLabel  aTimer;
    private CustomPanel aUI;
    private Stack<JButton> aStackButtons;
    private JPanel aButtonsHolder;
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
     * @param pText le texte à imprimer
     */
    public void print( final String pText )
    {
        try{
            this.aLog.getDocument().insertString(this.aLog.getDocument().getLength(),pText, null );
        }
        catch(javax.swing.text.BadLocationException exc){System.out.print("bug du texte");}
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)
    
    public void resetText(){
        this.aLog.setText("");
    }
    
    /**
     * Print out some text into the text area, followed by a line break.
     * @param pText  le texte à imprimer
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     * @param pImageName l'url de l'image
     */
    public void showImage( final String pImageName )
    {
        Image vImage = null;
        try {
            vImage = ImageIO.read(this.getClass().getClassLoader().getResource( pImageName));
        } catch (IOException e) {

        }
        this.aUI.setBGImage(vImage);
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     * @param pOnOff pour activer ou desactiver
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff );
        if ( ! pOnOff )
            this.aEntryField.getCaret().setBlinkRate( 0 );
    } // enable(.)

    /**
     * Set up graphical user interface.
     * @author pour la transformation en jtextPane du champ de texte 
     * @author pour une meilleure flexibilité du chanp de texte
     * @author https://stackoverflow.com/questions/4059198/jtextpane-appending-a-new-string
     * 
     * 
     * @author importation d'une police custom
     * @author https://stackoverflow.com/questions/24800886/how-to-import-a-custom-java-awt-font-from-a-font-family-with-multiple-ttf-files
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Le Manoir" );
        ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("Images/icon.png"));
        this.aMyFrame.setIconImage(img.getImage());
        this.aEntryField = new JTextField( 34 );
        this.aEntryField.setMaximumSize(new Dimension(1000,50));

        this.aLog = new JTextPane();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,50) );

        JPanel vPanel = new JPanel();
        vPanel.setLayout( new BoxLayout(vPanel, BoxLayout.PAGE_AXIS) );

        // PLACEMENT DE L'UI PRINCIPALE
        this.aUI = new CustomPanel(this.aEngine);
        this.aUI.setPreferredSize( new Dimension(100,100) );
        vPanel.add( this.aUI);

        // PLACEMENT DES BOUTONS
        this.aStackButtons = new Stack<JButton>();
        this.aButtonsHolder = new JPanel();
        setButtons(new String[]{"help"});

        this.aTimer = new JLabel("timer");
        vPanel.add(this.aTimer);
        vPanel.add(this.aButtonsHolder);

        //PLACEMENT DE LA ZONE DE TEXTE
        try{
            Font vFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getClassLoader().getResource( "font.ttf").openStream());
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(vFont);
            Font vPS2PFont = new Font("Press Start 2P", Font.PLAIN, 15);
            this.aLog.setFont(vPS2PFont);
        }
        catch(Exception e){}

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
        this.aMyFrame.setSize( new Dimension(800,700) );
        this.aUI.revalidate(); //requis pour eviter des bugs d'affichage
        this.aEntryField.requestFocus();
        setSprites();
    } // createGUI()

    /**
     * Reset les boutons de l'interface
     */
    public void resetButtons(){
        this.aButtonsHolder.removeAll();
        this.aStackButtons = new Stack<JButton>();
    }

    /**
     * Ajoute un bouton à l'interface
     * @param JButton le bouton à ajouter
     */
    public void addButton( final JButton pB){
        pB.addActionListener(this);
        this.aButtonsHolder.add(pB);
        this.aStackButtons.push(pB);
    }

    /**
     * Met en place l'ensemble de boutons
     * @param pSArray les noms des boutons à ajouter dans un tableau de String
     */
    public void setButtons(final String[] pSArray){
        resetButtons();
        for (String vS : pSArray){
            addButton(new JButton(vS));
        }
    }

    /**
     * Remet à jour les sprites de l'inteface point and click
     */

    public void setSprites(){
        this.aUI.resetSprites();
        Room vRoom = this.aEngine.getPlayer().getCurrentRoom();
        for (Item vI :vRoom.getItems().getItemArray()){
            Sprite vS = vI.getSprite();
            this.aUI.addSprite(vS);
        }
        
        for (Item vI :vRoom.getCharacters().getItemArray()){
            Sprite vS = vI.getSprite();
            this.aUI.addSprite(vS);
        }
        
        this.aUI.setAsPlayerSprite(this.aEngine.getPlayer().getSprite());

    }
    
    public void setFSSprite(final Sprite pS){
        this.aUI.setFSSprite(pS);
    }
    
    public void resetFSSprite(){
        this.aUI.resetFSSprite();
    }

    /**
     * Actionlistener interface for entry textfield.
     * @param l'actionevent
     */
    public void actionPerformed( final ActionEvent pEvent ) 
    {

        if (pEvent.getSource() == aEntryField){
            this.processCommand();
        }

        for (JButton vB : this.aStackButtons){
            if (pEvent.getSource() == vB){
                String vCommand = pEvent.getActionCommand();
                this.aEngine.interpretCommand( vCommand );
            }
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

    /**
     * Met à jour le temps restant sur l'interface
     * @param pTime le temps restant en secondes
     */
    public void updateTimeGUI(final int pTime){
        this.aTimer.setText(pTime/60+":"+pTime%60);

    }
} // UserInterface 
