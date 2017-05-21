import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * Write a description of class ItemList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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

    public boolean isEmpty(){
        return this.aItems.isEmpty();
    }

    public Collection<Item> getValues(){
        return this.aItems.values();
    }

    public void setItem(final Item pItem){
        this.aItems.put(pItem.getName(),pItem);
    }

    public Item takeItem(final String vItemString){
        return this.aItems.remove(vItemString);
    }

    public String inventory(){
        String vString = "";
        if (! this.aItems.isEmpty() ){
            for (Item vI : this.aItems.values().toArray(new Item[this.aItems.size()])){
                vString += vI.getDescription()+ "   ";
            }
        }
        return vString;
    }
    
    public Item[] getItemArray(){
            return this.aItems.values().toArray(new Item[this.aItems.size()]);
    }
} // ItemList
