/**
 * @author Pour l'idée du override du paintComponent :
 * @author https://www.abeautifulsite.net/java-game-programming-for-beginners
 * 
 * @author pour le redimensionement de l'image
 * @author https://stackoverflow.com/questions/11959758/java-maintaining-aspect-ratio-of-jpanel-background-image
 */

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.awt.*;

public class CustomPanel extends JPanel {
    private Image aBGImage;
    private HashMap<String,Sprite> aSprites ;
    private int bgWidth;
    private int bgHeight;
    public CustomPanel(){
        super();
        bgWidth =500;
        bgHeight =800;
        aSprites = new HashMap<String,Sprite>();
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
                Image rescaledImage = vSprite.getImage().getScaledInstance(-1,adjustedHeight,Image. SCALE_SMOOTH);;

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
        Image vImg = pImage.getScaledInstance(getWidth(),-1,Image. SCALE_SMOOTH);
        this.bgHeight = vImg.getHeight(this);
        this.bgWidth = vImg.getHeight(this);
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

    public void resetSprites(){
        this.aSprites = new HashMap<String,Sprite>();
        repaint();
    }

}