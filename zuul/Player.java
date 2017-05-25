import java.util.Stack; 
import java.util.HashMap;
import java.util.Map;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room aCurrentRoom;
    private Stack<Room> aHistory;
    private ItemList aItems;
    public int aPoids;
    public int aPoidsMax;
    private Sprite aSprite;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        this.aHistory = new Stack<Room>();
        this.aCurrentRoom = null;
        this.aItems= new ItemList();
        this.aPoids = 0;
        this.aPoidsMax = 1000;
        this.aSprite = new Sprite( "player", "Images/character.png" , 50,50, 33);
        this.aSprite.rendVisible(); 

    }

    /**
     *
     */
    public void setCurrentRoom(final Room pRoom)
    {
        this.aCurrentRoom = pRoom;
    }

    public Sprite getSprite(){
        return this.aSprite;
    }

    public Room getCurrentRoom(){
        return this.aCurrentRoom;
    }

    public Stack<Room> getHistory(){
        return this.aHistory;
    }
    
    public void resetHistory(){
        this.aHistory = new Stack<Room>();
    }

    public ItemList getItems(){
        return this.aItems;
    }

    public int getPoids(){
        return this.aPoids;
    }

    public int getPoidsMax(){
        return this.aPoidsMax;
    }

    public void setPoids(final int pPoids){
        this.aPoids = pPoids;
    }

    public void setPoidsMax(final int pPoids){
        this.aPoidsMax = pPoids;
    }

    public String getInventory(){
        String vString = "";
        if (! this.aItems.isEmpty() ) {
            vString +=  "Votre inventaire contient :"+this.aItems.inventory();
        }
        else {
            vString += "Votre inventaire est vide";
        }
        vString += "\n Le poids de votre equipement est de : "+getPoids()+"g";
        return vString;
    }

}
