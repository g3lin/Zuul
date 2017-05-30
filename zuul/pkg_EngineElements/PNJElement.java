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
    private Room[] aDepPattern;

    private GameEngine aGE;
    private ItemList aItems;
    private Room aRoom;

    /**
     * Constructor for objects of class Character
     */
    public PNJElement(final String pName, final String pDescription, final String[] pDialogues, final Room[] pDepPattern, final GameEngine pGE)
    {
        super(9999,pName, pDescription);
        this.aDialogues = pDialogues;
        this.aDepPattern = pDepPattern;
        this.aGE = pGE;
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

        if (this.aDepPattern[this.aDialInt-1] != null){
            Room vR = this.aDepPattern[this.aDialInt-1];
            this.aGE.movePNJRoom(this,vR);
        }

        return this.aDialogues[this.aDialInt -1];
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
