package pkg_EngineElements;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Color;

/**
 * Write a description of class Challenge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Challenge implements ActionListener
{
    // instance variables - replace the example below with your own
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextPane  aLog;
    private int aRandom;
    private int aAttempt;
    private GameEngine aGE;

    /**
     * Constructor for objects of class Challenge
     */
    public Challenge(final GameEngine pGE)
    {
        // initialise instance variables
        createGUI();
        this.aGE = pGE;
        Random vRandomObject = new Random();
        this.aRandom = vRandomObject.nextInt(100);
        this.aAttempt = 0;
    }

    public void jeu(int vN){
        aAttempt ++;
        
        if (aAttempt > 10){
            this.aGE.gameOver();
            this.aMyFrame.setVisible( false );
        }
        if (vN > aRandom){
            this.aLog.setText("\n\n  "+vN+" (-)");
        }
        if (vN < aRandom){
            this.aLog.setText("\n\n  "+vN+" (+)");
        }
        if (vN == aRandom){
            Room vR = aGE.getRooms().get("escape");
            aGE.goToRoom(vR);
            this.aMyFrame.setVisible( false );
        }

    }

    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Coffre fort" );
        ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("Images/icon.png"));
        this.aMyFrame.setIconImage(img.getImage());

        JPanel vPanel = new JPanel();
        vPanel.setLayout( new BoxLayout(vPanel, BoxLayout.PAGE_AXIS) );

        this.aEntryField = new JTextField( 5 );
        

        this.aLog = new JTextPane();
        this.aLog.setEditable( false );
        this.aLog.setText("\nEntrez le code :");
        try{
            Font vFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getClassLoader().getResource( "font.ttf").openStream());
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(vFont);
            Font vPS2PFont = new Font("Press Start 2P", Font.PLAIN, 35);
            this.aLog.setFont(vPS2PFont);
        }
        catch(Exception e){}
        this.aLog.setForeground(Color.red);
        

        vPanel.add(this.aLog);
        vPanel.add(this.aEntryField);

        this.aMyFrame.getContentPane().add( vPanel);
        // add some event listeners to some components
        this.aMyFrame.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) { getFrame().setVisible( false ); }
            } );

        this.aEntryField.addActionListener( this );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aMyFrame.setSize( new Dimension(400,400) );

    }

    /**
     * Actionlistener interface for entry textfield.
     * @param l'actionevent
     */
    public void actionPerformed( final ActionEvent pEvent ) 
    {
        try{
            int vNbr = Integer.parseInt(this.aEntryField.getText());
            jeu(vNbr);
        }catch(Exception e){
            this.aLog.setText("Erreur");
        }
        

        this.aEntryField.setText("");
    }

    public JFrame getFrame(){
        return this.aMyFrame;
    }
}
