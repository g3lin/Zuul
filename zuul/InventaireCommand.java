/**
 * Implementation of the 'quit' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class InventaireCommand extends Command
{
    /**
     * Constructor for objects of class QuitCommand
     */
    public InventaireCommand()
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
            pGE.getUI().println(pGE.getPlayer().getInventory());
        }
        else {
            pGE.getUI().println("Quoi ?");

        }
    }

}
