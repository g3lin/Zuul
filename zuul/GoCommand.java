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
                pGE.getUI().println("There is no door !");

            }
            else{
                pGE.getPlayer().getHistory().push(pGE.getPlayer().getCurrentRoom());

                pGE.goToRoom(vNextRoom);
            }
        }
    }
}