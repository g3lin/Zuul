package pkg_commands;

import pkg_EngineElements.*;
/**
 * Implementation of the 'eat' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class EatCommand extends Command
{
    /**
     * Constructor for objects of class EatCommand
     */
    public EatCommand()
    {
    }

    /**
     *  affiche que l'on mange
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
