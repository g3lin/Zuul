package pkg_commands;
import pkg_EngineElements.GameEngine;
/**
 * Implementation of the 'quit' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class LookCommand extends Command
{
    /**
     * Constructor for objects of class QuitCommand
     */
    public LookCommand()
    {
    }

    /**
     *  
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println(pGE.getPlayer().getCurrentRoom().getLongDescription());
        }
        else {
            pGE.getUI().println("Quoi ?");

        }
    }

}
