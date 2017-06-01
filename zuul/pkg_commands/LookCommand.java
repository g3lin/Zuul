package pkg_commands;
import pkg_EngineElements.GameEngine;
/**
 * Implementation of the 'look' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class LookCommand extends Command
{
    /**
     * Constructor for objects of class LookCommand
     */
    public LookCommand()
    {
    }

    /**
     *  regarde autour et affiche la long description de la rooom actuelle  
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
