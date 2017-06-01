package pkg_commands;

import pkg_EngineElements.*;
/**
 * Implementation of the 'quit' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class QuitCommand extends Command
{
    /**
     * Constructor for objects of class QuitCommand
     */
    public QuitCommand()
    {
    }

    /**
     * "Quit" was entered. Check the argument to see whether
     * we really quit the game. 
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println("Merci d'avoir joué !");
            pGE.getUI().enable(false);
        }
        else {
            pGE.getUI().println("Que voulez vous quitter ?");

        }
    }

}
