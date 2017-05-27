package pkg_commands;

import pkg_EngineElements.*;
/**
 * Implementation of the 'quit' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class EatCommand extends Command
{
    /**
     * Constructor for objects of class QuitCommand
     */
    public EatCommand()
    {
    }

    /**
     *  
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println("Vous avez mangé une barre de céréales de votre sac");
        }
        else {
            

        }
    }

}
