import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Room
{
    private String aDescription ;
    private HashMap<String,Room> aExits;
    private String aImageName;
    private Map<String, Item> aItems;

    /**
     * Constructeur mettant en place la description
     * et la hashmap des sorties en attributs
     * @param pDescription la description de la piece
     * @param pImage le nom de l'image
     */
    public Room(final String pDescription, String pImage){
        this.aDescription=pDescription;
        aExits = new  HashMap<String,Room>();
        this.aImageName = pImage;
        aItems = new HashMap<String,Item>();
    }

    /**
     * accesseur de la description
     * @return la description
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * definit les sorties
     * @param pDirection la direction
     * @param pExit  la sortie pour la direction
     */
    public void setExits(String pDirection, Room pExit){
        aExits.put(pDirection,pExit);

    }

    /**
     * accesseur de la direction
     * @param pDirection la direction
     * @return la room qui fait face à cette direction
     */
    public Room getExit(final String pDirection){
        return aExits.get(pDirection);
    }

    /**
     * affiche le string de la localisation
     */
    public String getLongDescription(){
        String vDescription = "Vous êtes dans "+ this.getDescription()+"\n Vous pouvez aller vers: "+this.getExitString() ;
        if (! this.aItems.isEmpty() ) {
            vDescription += "\n Vous trouvez dans cette salle :";
            for (Item vI : this.aItems.values() ){
                vDescription += vI.getDescription()+ "   ";
            }
        }
        return vDescription;
    }

    /**
     * affiche le string des sorties disponibles en sortant de cette salle
     */
    public String getExitString(){
        StringBuilder vExits = new StringBuilder( "Exits:" );
        for ( String vS : aExits.keySet() )
            vExits.append( " " + vS );
        return vExits.toString();
    }

    public void setItem(final Item pItem){
        this.aItems.put(pItem.getDescription(),pItem);
    }

    public Object[] getItems(){
        return this.aItems.values().toArray(); 
    }

    /**
     * Return a string describing the room's image name
     */
    public String getImageName()
    {
        return aImageName;
    }
} // Room
