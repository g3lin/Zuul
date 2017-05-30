package pkg_EngineElements;


/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PNJElement extends Item
{
    // instance variables - replace the example below with your own
    private String[] aDialogues;
    private int aDialInt;
    
    private ItemList aItems;
    private Room aRoom;

    /**
     * Constructor for objects of class Character
     */
    public PNJElement(final String pName, final String pDescription, final String[] pDialogues)
    {
        super(9999,pName, pDescription);
        this.aDialogues = pDialogues;
        this.aDialInt =0;
    }

    /**
     *
     */
    public String talk()
    {
        if (this.aDialInt <= this.aDialogues.length)
        {
            this.aDialInt ++ ;
        }
        return this.aDialogues[this.aDialInt -1];
    }
    
    public void goToRoom(final Room pR){
        
    }
    
    public void setRoom(final Room pRoom){
        this.aRoom = pRoom;
    }
    
    public Room getRoom(){
        return this.aRoom;
    }
    
    public ItemList getItems(){
        return this.aItems;
    }
}
