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
        getSprite().setHeight(33);
        getSprite().tpTo(20, 50);
    }

    /**
     *  fait parler le PNJ et verifie si il doit se deplacer
     */
    public String talk()
    {
        if (this.aDialInt < this.aDialogues.length)
        {
            this.aDialInt ++ ;
        }

        if (this.aDepPattern[this.aDialInt-1] != null){
            Room vR = this.aDepPattern[this.aDialInt-1];
            this.aGE.movePNJRoom(this,vR);
        }

        return this.aDialogues[this.aDialInt -1];
    }

    /**
     * definit la room où est le pnj
     * @param pRoom la salle 
     */
    public void setRoom(final Room pRoom){
        this.aRoom = pRoom;
    }

    
    /**
     * donne la room où est le pnj
     * @return la salle
     */
    public Room getRoom(){
        return this.aRoom;
    }

}
