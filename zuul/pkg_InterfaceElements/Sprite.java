package pkg_InterfaceElements;

import java.awt.Image;
import java.io.*;
import javax.imageio.ImageIO;
import java.lang.InterruptedException;


/**
 * Un element qui contient une image pouvant etre affichée sur un CustomPanel
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
     * Cree un nouveau Sprite 
     * @param pName le nom du Sprite
     * @param pImageURI le chemein d'acces à l'image
     * @param pcoordX positionx du Sprite en pourcentage
     * @param pcoordY position y du Sprite en pourcentage
     * @param pheight la hauteur du Sprite en pourcentage
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
     * Rend ce sprite visible.
     */
    public void rendVisible()
    {
        this.aEstVisible= true;
    } // rendVisible()

    /**
     * Rend ce sprite invisible.
     */
    public void rendInvisible()
    {
        this.aEstVisible= false;
    } // rendInvisible()

    /**
     * accesseur de l'etat de visibilité
     * @return visibilité
     */
    public boolean estVisible(){
        return this.aEstVisible;
    }

    /**
     * accesseur de l'image
     * @return image
     */
    public Image getImage(){
        return this.aImage;
    }

    /**
     * permet de mod les attributs de position x et y
     * @param x le x
     * @param y le y
     */
    public void tpTo(final int pX, final int pY)
    {
        this.aXPosition = pX;
        this.aYPosition = pY;
    }

    /**
     * appelle la methode deplacement lent en horizonal puis vertical
     * @param x le x
     * @param y le y
     */
    public void walkTo(final int pX, final int pY)
    {
        depLentHorizontal(pX-this.aXPosition);
        depLentVertical(pY-this.aYPosition);
    }

    /**
     * Deplace le Sprite horizontalement.
     * @param pDistance nb de poucents dont il faut se deplacer vers la droite
     */
    public void depHorizontal( final int pDistance )
    {
        this.aXPosition= this.aXPosition + pDistance;
    } // depHorizontal()

    /**
     * Deplace le Sprite verticalement.
     * @param pDistance nb de pourcents dont il faut se deplacer vers le bas
     */
    public void depVertical( final int pDistance )
    {
        this.aYPosition= this.aYPosition + pDistance;
    } // depVertical()

    /**
     * Deplace lentement le Sprite horizontalement.
     * @param pDistance nb de pourcents dont il faut se deplacer vers la droite
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
     * Deplace lentement le Sprite verticalement.
     * @param pDistance nb de pourcents dont il faut se deplacer vers le bas
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
     * accesseur du x
     * @return X
     */
    public int getX()
    {
        return this.aXPosition;

    } //getposition

    /**
     * accesseur du y
     * @return Y
     */
    public int getY()
    {
        return this.aYPosition;

    } //getposition

    /**
     * accesseur de la hauteur du sprite
     * @return Hauteur en pourcents
     */
    public int getHeight()
    {
        return this.aHeight;

    } //getposition

    
    

    /**
     * accesseur du nom
     * @return nom du sprite
     */
    public String getName()
    {
        return this.aName;

    } //getposition

    
    
} // Cercle
