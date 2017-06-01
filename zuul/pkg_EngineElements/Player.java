package pkg_EngineElements;
import java.util.Stack; 
import java.util.HashMap;
import java.util.Map;
import pkg_InterfaceElements.Sprite;

/**
 * Objet pour le joueur
 * 
 * @author GELIN
 * @version 2017
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
     * definit la room actuelle du joueur
     * @param pRoom la salle
     */
    public void setCurrentRoom(final Room pRoom)
    {
        this.aCurrentRoom = pRoom;
    }

    /**
     * retourne le sprite du joueur
     * @return sprite du joueur
     */
    public Sprite getSprite(){
        return this.aSprite;
    }

    /**
     * donne la room dans laquelle le joueur est
     * @return room actuelle
     */
    public Room getCurrentRoom(){
        return this.aCurrentRoom;
    }

    /**
     * donne la l'historiqque de deplacement du joueur
     * @return stack des deplacements précédents
     */
    public Stack<Room> getHistory(){
        return this.aHistory;
    }

    /**
     * reinitialise la l'historiqque de deplacement du joueur
     */
    public void resetHistory(){
        this.aHistory = new Stack<Room>();
    }

    /**
     * donne les items du joueur
     * @return l'itemlist associée au joueur
     */
    public ItemList getItems(){
        return this.aItems;
    }

    /**
     * donne le poids de l'inventaire du joueur
     * @return le poids 
     */
    public int getPoids(){
        return this.aPoids;
    }

    /**
     * donne le poids max du joueur
     * @return le poids  max
     */
    public int getPoidsMax(){
        return this.aPoidsMax;
    }

    /**
     * met le poids du joueur
     * @param pPoids le poids total de l'inventaire
     */
    public void setPoids(final int pPoids){
        this.aPoids = pPoids;
    }

    /**
     * met le poids max  du joueur
     * @param pPoids le poids max que peut avoir l'inventaire
     */
    public void setPoidsMax(final int pPoids){
        this.aPoidsMax = pPoids;
    }

    /**
     * affiche tous les objets du joueur
     * @return String contenant tous les objets que le joueur porte
     */ 
    
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
