package pkg_InterfaceElements;

import java.awt.Image;
import java.io.*;
import javax.imageio.ImageIO;
import java.lang.InterruptedException;



/**
 * Un cercle qui peut etre manipule et qui se dessine sur une toile (canvas).
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  mod.by Denis BUREAU
 * @version 2006.03.30/2012.02.15
 */

public class Sprite 
{

    private String  aName;
    private Image   aImage;
    private int     aXPosition;
    private int     aYPosition;
    private int     aHeight;
    private boolean aEstVisible;
    private int     aAdjHeight;
    private int     aAdjWidth;

    /**
     * Cree un nouveau cercle avec les position et couleur par defaut.
     */

    /**
     * Cree un nouveau cercle avec les position et couleur donnés en paramère et le rend visible auto
     * @param pdiametre diametre du cercle
     * @param pcoordx positionx du cercle
     * @param pcoordY position y du cercle
     */
    public Sprite(String pName,String pImageURI,int pcoordX,int pcoordY,int pHeight)
    {
        this.aName = pName;
        this.aImage = null;
        try {
            this.aImage = ImageIO.read(new File(pImageURI));
        } catch (IOException e) {
            System.out.print("probleme de chargement de l'image");
        }

        this.aXPosition= pcoordX;
        this.aYPosition= pcoordY;
        this.aHeight=   pHeight;
        this.rendVisible();
    } // Cercle()

    /**
     * Rend ce cercle visible.
     */
    public void rendVisible()
    {
        this.aEstVisible= true;
    } // rendVisible()

    /**
     * Rend ce cercle invisible.
     */
    public void rendInvisible()
    {
        this.aEstVisible= false;
    } // rendInvisible()

    public boolean estVisible(){
        return this.aEstVisible;
    }

    public Image getImage(){
        return this.aImage;
    }

    public void tpTo(final int pX, final int pY)
    {
        this.aXPosition = pX;
        this.aYPosition = pY;
    }

    public void walkTo(final int pX, final int pY)
    {
        depLentHorizontal(pX-this.aXPosition);
        depLentVertical(pY-this.aYPosition);
    }

    /**
     * Deplace le cercle horizontalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers la droite
     */
    public void depHorizontal( final int pDistance )
    {
        this.aXPosition= this.aXPosition + pDistance;
    } // depHorizontal()

    /**
     * Deplace le cercle verticalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers le bas
     */
    public void depVertical( final int pDistance )
    {
        this.aYPosition= this.aYPosition + pDistance;
    } // depVertical()

    /**
     * Deplace lentement le cercle horizontalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers la droite
     */
    public void depLentHorizontal( final int pDistance )
    {
        int vDelta; // le pas unitaire de deplacement 
        int vXIni = this.aXPosition;
        if ( pDistance < 0 ) {
            vDelta=    -1;
        }
        else {
            vDelta=    1;
        } // if

        while (this.aXPosition != vXIni +pDistance) {
            this.depHorizontal( vDelta); 
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }

        }
    } // depLentHorizontal()

    /**
     * Deplace lentement le carre verticalement de 'pDistance' pixels.
     * @param pDistance nb de pixels dont il faut se deplacer vers le bas
     */
    public void depLentVertical( final int pDistance )
    {
        int vDelta; // le pas unitaire de deplacement
        int vYIni = this.aYPosition;
        if ( pDistance < 0 ) {
            vDelta=    -1;
        }
        else {
            vDelta=    1;
        } // if

        while (this.aYPosition != vYIni +pDistance) {
            this.depVertical( vDelta); 
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        }// deplacement rapide pour l'instant
    } // depLentVertical()

    /**
     * 
     */
    public int getX()
    {
        return this.aXPosition;

    } //getposition

    /**
     * 
     */
    public int getY()
    {
        return this.aYPosition;

    } //getposition

    /**
     * 
     */
    public int getHeight()
    {
        return this.aHeight;

    } //getposition
    
    
    
    /**
     * 
     */
    public void setAdjustedHeight(final int pH)
    {
        this.aAdjHeight = pH;

    }
    
    /**
     * 
     */
    public void setAdjustedWidth(final int pW)
    {
        this.aAdjHeight = pW;

    }
    
    /**
     * 
     */
    public int getAdjustedHeight()
    {
        return this.aAdjHeight;

    }
    
    /**
     * 
     */
    public int getAdjustedWidth()
    {
        return this.aAdjWidth;

    } //getposition

    /**
     * 
     */
    public String getName()
    {
        return this.aName;

    } //getposition
    

    
    
    
} // Cercle
