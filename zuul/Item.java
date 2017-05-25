import java.io.*;
import javax.imageio.ImageIO;
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int aPoids;
    private String aName;
    private String aDescription;
    private Sprite aSprite;
    private int aState;
    private Object aStateVar;

    /**
     * default constructor for objects of class Item
     */
    public Item(final int pPoids, final String pName, final String pDescription)
    {
        // initialise instance variables
        this.aPoids = pPoids;
        this.aName = pName;
        this.aDescription = pDescription;
        this.aState = 0;
        this.aStateVar = null;
        String vS = "";
        try {
            ImageIO.read(new File("Images/"+pName+".png"));
            vS = pName;
        } catch (IOException e) {
            vS = "item";
        }
        this.aSprite = new Sprite( pName, "Images/"+vS+".png" , 50,50, 10);
    } 

    /**
     * accesseur du poids
     * @return     poids de l'objet 
     */
    public int getPoids()
    {
        return this.aPoids;
    } // sampleMethod(.)

    /**
     * accesseur du nom
     * @return nom
     */
    public String getName()
    {
        return this.aName;
    } // sampleMethod(.)
    
    /**
     * accesseur e la description
     * @return description
     */
    public String getDescription()
    {
        return this.aDescription;
    } // sampleMethod(.)
    
    public Sprite getSprite(){
        return this.aSprite;
    }
    
    public void setState(final int pState) {
        this.aState = pState;
    }
    
    public int getState(){
        return this.aState;
    }
    
    public void setStateVar(final Object pStateVar) {
        this.aStateVar = pStateVar;
    }
    
    public Object getStateVar(){
        return this.aStateVar;
    }
    
    
} // Item
