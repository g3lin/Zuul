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
    private Map<String, Item> aItems;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        this.aHistory = new Stack<Room>();
        this.aCurrentRoom = null;
        aItems = new HashMap<String,Item>();
    }

    /**
     *
     */
    public void setCurrentRoom(final Room pRoom)
    {
        this.aCurrentRoom = pRoom;
    }
    
    public Room getCurrentRoom(){
        return this.aCurrentRoom;
    }
    
    public Stack<Room> getHistory(){
        return this.aHistory;
    }
    
    public void setItem(final Item pItem){
        this.aItems.put(pItem.getName(),pItem);
    }

    public Object[] getItems(){
        return this.aItems.values().toArray(); 
    }
    
    public Item takeItem(final String vItemString){
        return this.aItems.remove(vItemString);
    }
}
