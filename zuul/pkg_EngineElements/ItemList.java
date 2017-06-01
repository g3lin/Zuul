package pkg_EngineElements;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * Gere une collection d'items
 * 
 * @author GELIN
 * @version 2017
 */
public class ItemList
{
    // instance variables - replace the example below with your own
    private Map<String, Item> aItems;

    /**
     * default constructor for objects of class ItemList
     */
    public ItemList()
    {
        // initialise instance variables
        aItems = new HashMap<String,Item>();
    } 
    
    /**
     * verifie si la collection est vide
     * @return true si collection vide
     */
    public boolean isEmpty(){
        return this.aItems.isEmpty();
    }

    /**
     * accesseur de la liste d'items
     * return l'array de valeurs des Items
     */
    public Collection<Item> getValues(){
        return this.aItems.values();
    }

    /**
     * rajoute un item à la liste
     * @param pItem l'item à rajouter
     */
    public void setItem(final Item pItem){
        this.aItems.put(pItem.getName(),pItem);
    }

    /**
     * rajoute un item à la liste
     * @param pItemString le string de l'item à enlever
     * @return l'item enlevé
     * 
     */
    public Item takeItem(final String vItemString){
        for( String vS :this.aItems.keySet()){
        System.out.print(vS);}
        
        return this.aItems.remove(vItemString);
        
    }

    /**
     * affiche l'inventaire
     * @return String contenant les differents objets ou rien si il n'y en a pas
     */
    public String inventory(){
        String vString = "";
        if (! this.aItems.isEmpty() ){
            for (Item vI : this.aItems.values().toArray(new Item[this.aItems.size()])){
                vString += vI.getDescription()+ "   ";
            }
        }
        return vString;
    }
    
    
    /**
     * accesseur de la liste d'items
     * return l'array de valeurs des Items
     */
    
    public Item[] getItemArray(){
            return this.aItems.values().toArray(new Item[this.aItems.size()]);
    }
} // ItemList
