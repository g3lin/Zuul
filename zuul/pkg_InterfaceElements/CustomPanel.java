package pkg_InterfaceElements;
/**
 * Cette classe est le centre de l'interface "point and click" du jeu
 * Elle genere les choses à y afficher, les affiche et gère les actions de clics de la souris
 * C'est un JPanel custom
 * 
 * 

 * 
 * @author pour le clic de souris
 * @author https://stackoverflow.com/questions/12396066/how-to-get-location-of-a-mouse-click-relative-to-a-swing-window
 */
import pkg_EngineElements.GameEngine;
import pkg_EngineElements.Item;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class CustomPanel extends JPanel implements MouseListener {
    private GameEngine aEngine;
    private Image aBGImage;
    private HashMap<String,Sprite> aSprites ;
    private Sprite aPlayerSprite;
    private int bgWidth;
    private int bgHeight;

    /**
     * Constructeur naturel de CustomPanel
     * @param pGameEngine le GameEngine qui gère le jeu
     */
    public CustomPanel(final GameEngine pGameEngine)
    {
        super();
        this.aEngine = pGameEngine;
        bgWidth =500;
        bgHeight =800;
        aSprites = new HashMap<String,Sprite>();
        addMouseListener(this);
    }

    /**
     * Sert à peindre les differents elements ( le fond les items puis le personnage) sur l'écran 
     * @author Pour l'idée du override du paintComponent :
     * @author https://www.abeautifulsite.net/java-game-programming-for-beginners
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(scaleBG(this.aBGImage), 0, 0, this);
        for (Sprite vSprite : this.aSprites.values()){
            if(vSprite.estVisible()){
                int adjustedHeight = vSprite.getHeight()*this.bgHeight/100;
                
                Image rescaledImage = vSprite.getImage().getScaledInstance(-1,adjustedHeight,Image.SCALE_SMOOTH);;
               
                int adjustedX = vSprite.getX()*this.bgWidth/100 ;
                int adjustedY = vSprite.getY()*this.bgHeight/100 ;
                g2d.drawImage(rescaledImage, adjustedX, adjustedY, this);
            }
        }
    }

    /**
     * Fait en sorte que l'image soit toujours visible
     * @author pour le redimensionement de l'image
     * @author https://stackoverflow.com/questions/11959758/java-maintaining-aspect-ratio-of-jpanel-background-image
     */
    @Override
    public Dimension getPreferredSize() {
        if (this.getSize().equals(new Dimension(0,0))){
            return new Dimension(800,1000);
        }
        else {
            return new Dimension(this.bgHeight, this.bgWidth);
        }
    }

    /**
     * Redimensionne l'image de telle sorte qu'elle ait tjs la meme largeur que la fenetre
     * et eviter que l'on ne puisse pas cliquer à droite ou qu'il ya ait du vide
     * 
     * @param pImage l'image de fond à mette à l'echelle
     * @return vImg l'image de fond à l'echelle
     * 
     * @author pour le redimensionement de l'image
     * @author https://stackoverflow.com/questions/11959758/java-maintaining-aspect-ratio-of-jpanel-background-image
     */
    public Image scaleBG(final Image pImage){
        Image vImg = pImage;
        try{
            vImg = pImage.getScaledInstance(getWidth(),-1,pImage.SCALE_SMOOTH);
            this.bgHeight = vImg.getHeight(this);
            this.bgWidth = vImg.getWidth(this);
        }
        catch(NullPointerException e){System.out.println();}
        //requis pour eviter des exceptions au demarrage du prog 
        //(dimensions pas encore initialisées au 1er passage

        return vImg;
    }

    /**
     * Definit l'image de fond
     * @param l'image à mettre en fond
     */
    public void setBGImage(final Image pImage){
        this.aBGImage = pImage;
        repaint();
    }

    /**
     * Ajoute un element par dessus l'image
     * @param pSprite le Sprite de l'element à ajouter
     */
    public void addSprite(final Sprite pSprite){
        this.aSprites.put(pSprite.getName(),pSprite);
        repaint();
    }

    /**
     * Ajoute le sprite du joueur
     * @param pSprite le Sprite du joueur
     */
    public void setAsPlayerSprite( final Sprite pSprite){
        this.aPlayerSprite = pSprite;
        addSprite(pSprite);
    }

    /**
     * Remet à jour les elements Sprite présents
     */
    public void resetSprites(){
        this.aSprites = new HashMap<String,Sprite>();
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Methode appelée quand on clique sur l'un des boutons de la souris
     */
    @Override
    public void mouseClicked(MouseEvent pEvent) {
        int vX = pEvent.getX()*100/this.bgWidth;
        int vY = pEvent.getY()*100/this.bgHeight;

        //CLIC GAUCHE
        if (pEvent.getButton() == MouseEvent.BUTTON1) {
            this.aPlayerSprite.tpTo(vX,this.aPlayerSprite.getY());

            // CLICS POUR LES SORTIES
            // else ifs pour la priorité du deplacement sur x par rapport à celui sur y
            // cad si clic sur coin sup droit ---> dep à droite et par en haut
            if (vX<15){
                this.aEngine.interpretCommand("go west");
                this.aPlayerSprite.tpTo(100-this.aPlayerSprite.getX()-20,this.aPlayerSprite.getY());
            }
            else if (vX>85){
                this.aEngine.interpretCommand("go east");
                this.aPlayerSprite.tpTo(100-this.aPlayerSprite.getX(),this.aPlayerSprite.getY());
            }
            else if (vY<15){
                this.aEngine.interpretCommand("go north");
                //                 this.aPlayerSprite.tpTo(this.aPlayerSprite.getX(),100-this.aPlayerSprite.getY());
            }
            else if (vY>85){
                this.aEngine.interpretCommand("go south");
                //                 this.aPlayerSprite.tpTo(this.aPlayerSprite.getX(),100-this.aPlayerSprite.getY()+20);
            }

            // CLICS SUR LES OBJETS
            String vItemName = null;
            for (Sprite vSprite : this.aSprites.values()){
                boolean x1 = vX > vSprite.getX();
                boolean x2 = vX < vSprite.getX()+ 15;
                boolean y1 = vY > vSprite.getY();
                boolean y2 = vX < vSprite.getX()+ vSprite.getHeight();
                if(( x1 && x2)&&( y1 && y2)){
                    vItemName = vSprite.getName();
                }
            }

            if (vItemName == null){
                this.aEngine.getUI().setButtons(new String[]{"look","eat","back"});
            }

            else if (vItemName.equals("player")){
                String[] vSArray;
                if (! this.aEngine.getPlayer().getItems().isEmpty() ) {
                    Item[] vIArray = this.aEngine.getPlayer().getItems().getItemArray();
                    vSArray = new String[vIArray.length+1];
                    int i = 0;
                    for (Item vI : vIArray){
                        vSArray[i]= "drop "+vI.getName();
                    }
                    vSArray[vIArray.length] = "inventaire";
                }
                else {
                    vSArray = new String[]{"inventaire"};
                }
                this.aEngine.getUI().setButtons(vSArray);
            }
            else if (vItemName.equals("comte")){
                this.aEngine.getUI().setButtons(new String[]{"talk comte"});
            }

            else{
                this.aEngine.getUI().setButtons(new String[]{"take "+vItemName,"use "+vItemName});
            }
        }

        //CLIC MILIEU
        if (pEvent.getButton() == MouseEvent.BUTTON2) {
           
        }

        //CLIC DROIT
        if (pEvent.getButton() == MouseEvent.BUTTON3) {
           
        }

    }

}