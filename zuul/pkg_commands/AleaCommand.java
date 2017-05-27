package pkg_commands;

import pkg_EngineElements.*;
/**
 * Implementation of the 'quit' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class AleaCommand extends Command
{
    /**
     * Constructor for objects of class QuitCommand
     */
    public AleaCommand()
    {
    }

    /**
     * "Quit" was entered. Check the argument to see whether
     * we really quit the game. Return true, if we should quit, false
     * otherwise.
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
