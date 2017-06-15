package pkg_commands;
import pkg_EngineElements.*;



/**
 * Implementation of the 'go' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class GoCommand extends Command
{
    /**
     * Constructor for objects of class GoCommand
     */
    public GoCommand()
    {
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
     */
    public void execute(GameEngine pGE)
    {

        if (!hasSecondWord()) {
            pGE.getUI().println("Dans quelle direction aller ?");

        }
        else {
            String vDirection = getSecondWord() ;
            Room vNextRoom = pGE.getPlayer().getCurrentRoom().getExit(vDirection);
            if (vNextRoom == null){
                pGE.getUI().println("Impossible d'aller par là");

            }
            
            else if ((pGE.getPlayer().getCurrentRoom() == pGE.getRooms().get("entree"))&& (!pGE.getRooms().get("entree").getCharacters().isEmpty())){
                pGE.getUI().println("Il est impoli de s'inviter chez quelqu'un comme ca");
                pGE.getUI().println("Je vais plutot parler à l'homme en face de moi");
            }
            
            else{
                pGE.getPlayer().getHistory().push(pGE.getPlayer().getCurrentRoom());

                pGE.goToRoom(vNextRoom);
            }
        }
    }
}