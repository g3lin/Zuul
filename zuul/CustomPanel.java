/**
 * @author Pour l'idée du override du paintComponent :
 * @author https://www.abeautifulsite.net/java-game-programming-for-beginners
 * 
 * @author pour le redimensionement de l'image
 * @author https://stackoverflow.com/questions/11959758/java-maintaining-aspect-ratio-of-jpanel-background-image
 * 
 * @author pour le clic de souris
 * @author https://stackoverflow.com/questions/12396066/how-to-get-location-of-a-mouse-click-relative-to-a-swing-window
 */

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

    public CustomPanel(final GameEngine pGameEngine)
    {
        super();
        this.aEngine = pGameEngine;
        bgWidth =500;
        bgHeight =800;
        aSprites = new HashMap<String,Sprite>();
        addMouseListener(this);
    }

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
                vSprite.setAdjustedHeight(adjustedHeight);
                Image rescaledImage = vSprite.getImage().getScaledInstance(-1,adjustedHeight,Image.SCALE_SMOOTH);;
                vSprite.setAdjustedWidth(rescaledImage.getHeight(this));
                int adjustedX = vSprite.getX()*this.bgWidth/100 ;
                int adjustedY = vSprite.getY()*this.bgHeight/100 ;
                g2d.drawImage(rescaledImage, adjustedX, adjustedY, this);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (this.getSize().equals(new Dimension(0,0))){
            return new Dimension(800,1000);
        }
        else {
            return new Dimension(this.bgHeight, this.bgWidth);
        }
    }

    public Image scaleBG(final Image pImage){
        Image vImg = pImage;
        try{
            vImg = pImage.getScaledInstance(getWidth(),-1,pImage.SCALE_SMOOTH);
            this.bgHeight = vImg.getHeight(this);
            this.bgWidth = vImg.getWidth(this);
        }
        catch(NullPointerException e){System.out.println("bug ");}
        //requis pour eviter des exceptions au demarrage du prog 
        //(dimensions pas encore initialisées au 1er passage

        return vImg;
    }

    public void setBGImage(final Image pImage){
        this.aBGImage = pImage;
        repaint();
    }

    public void addSprite(final Sprite pSprite){
        this.aSprites.put(pSprite.getName(),pSprite);
        repaint();
    }

    public void setAsPlayerSprite( final Sprite pSprite){
        this.aPlayerSprite = pSprite;
        addSprite(pSprite);
    }

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
            for (Sprite vSprite : this.aSprites.values()){
                boolean x1 = vX > vSprite.getX();
                boolean x2 = vX < vSprite.getX()+ 10;
                boolean y1 = vY > vSprite.getY();
                boolean y2 = vX < vSprite.getX()+ 10;
                if(( x1 && x2)&&( y1 && y2)){
                    this.aEngine.interpretCommand("take "+vSprite.getName());
                }
            }

        }

        //CLIC MILIEU
        if (pEvent.getButton() == MouseEvent.BUTTON2) {
            this.aPlayerSprite.tpTo(vX,vY);
        }

        //CLIC DROIT
        if (pEvent.getButton() == MouseEvent.BUTTON3) {
            this.aPlayerSprite.tpTo(50,50);
            System.out.println(pEvent.getX()+"  , bg width "+this.bgWidth);
        }

    }

}