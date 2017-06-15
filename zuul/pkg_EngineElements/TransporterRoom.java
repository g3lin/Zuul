package pkg_EngineElements;

import java.util.Random;
/**
 * Salle speciale qui tp dans une salle au hasard
 * 
 * @author GELIN
 * @version 2017
 */
public class TransporterRoom extends Room
{
    private Room aPseudoRandom;
    private Room[] aAvailableRooms;
    /**
     * Constructor for objects of class TransporterRoom
     */
    public TransporterRoom (final String pDesc,final String pLook, final String pImg, final Room[] pARooms)
    {
        super(pDesc,pLook,pImg);
        this.aPseudoRandom = null;
        this.aAvailableRooms = pARooms;
    }

    @Override
    /**
     * accesseur de la direction
     * @param pDirection la direction
     * @return la room au hasard ou souhaitée si on afait alea
     */
    public Room getExit(final String pDirection){
        if (pDirection.equals("west")){
            return findARandomRoom();
        }
        else{            
            return null;
        }
    }

    /**
     * retoune la string des sorties modifiée pour la salle
     */
    @Override
    public String getExitString(){
        return "une salle au hasard dans la maison à portée de l'appareil";
    }

    /**
     * trouve une salle random
     * @return salle random
     */
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

    /**
     * Truque le jeu et met une salle définie au lieu de random
     * @param pRoom la salle vers laquelle il faut aller
     */
    public void setPseudoRandom(final Room pR){
        this.aPseudoRandom = pR;
    }

}
