package pkg_EngineElements;

import java.util.Random;
/**
 * Write a description of class TransporterRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TransporterRoom extends Room
{
    private Room aPseudoRandom;
    private Room[] aAvailableRooms;
    /**
     * Constructor for objects of class TransporterRoom
     */
    public TransporterRoom (final String pDesc, final String pImg, final Room[] pARooms)
    {
        super(pDesc,pImg);
        this.aPseudoRandom = null;
        this.aAvailableRooms = pARooms;
    }

    @Override
    /**
     * accesseur de la direction
     * @param pDirection la direction
     * @return la room qui fait face à cette direction
     */
    public Room getExit(final String pDirection){
        if (pDirection.equals("west")){
            return findARandomRoom();
        }
        else{            
            return null;
        }
    }

    @Override
    public String getExitString(){
        return "une salle au hasard dans la maison à portée de l'appareil";
    }

    public Room findARandomRoom(){
        if (aPseudoRandom == null){
            Random vRandomObject = new Random();
            int vI = vRandomObject.nextInt(this.aAvailableRooms.length); //DERNIER NON INCLUS
            return aAvailableRooms[vI];
        }
        else{
            return this.aPseudoRandom;
        }
    }

    public void setPseudoRandom(final Room pR){
        this.aPseudoRandom = pR;
    }

}
