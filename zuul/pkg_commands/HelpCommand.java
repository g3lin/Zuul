package pkg_commands;
import pkg_EngineElements.GameEngine;
/**
 * Implementation of the 'help' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class HelpCommand extends Command
{
    private CommandWords aCommandWords;
    
    /**
     * Constructor for objects of class HelpCommand
     */
    public HelpCommand(CommandWords pWords)
    {
        aCommandWords = pWords;
    }
    
    /**
     * Print out some help information. Here we print some stupid, 
     * cryptic message and a list of the command words.
     */
    public void execute(GameEngine pGE)
    {

        pGE.getUI().println("Vos commandes sont :");
        this.aCommandWords.showAll();
        
    }
}
