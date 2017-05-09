import java.util.Stack; 

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

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        this.aHistory = new Stack<Room>();
        this.aCurrentRoom = null;
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
}
