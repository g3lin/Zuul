package pkg_commands;

import pkg_EngineElements.*;
/**
 * Implementation of the 'alea' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class AleaCommand extends Command
{
    /**
     * Constructor for objects of class AleaCommand
     */
    public AleaCommand()
    {
    }

    /**
     * triche dans le jeu en donnant une salle au lieu du hasard de la transporterRoom
     * @param pGE le GameEngine du jeu
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println("Spécifiez une salle de triche");
        }
        else {
            Room vR = pGE.getRooms().get(getSecondWord());
            if (vR != null){
                Room vRTPR = pGE.getRooms().get("tproom");
                TransporterRoom vTPR = (TransporterRoom) vRTPR;
                vTPR.setPseudoRandom(vR);
            }
            else{
                pGE.getUI().println("Salle non trouvée");
            }

        }
    }

}
