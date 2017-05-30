package pkg_commands;

import pkg_EngineElements.*;
/**
 * Implementation of the 'quit' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class TalkCommand extends Command
{
    /**
     * Constructor for objects of class QuitCommand
     */
    public TalkCommand()
    {
    }

    /**
     *  
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println("Avec qui voulez vous parler ?");
        }
        else {
            String CharacterNString = getSecondWord();
            Item vIChara = pGE.getPlayer().getCurrentRoom().getCharacters().takeItem(getSecondWord());
            PNJElement vChara = (PNJElement)vIChara;
            pGE.getUI().println(vChara.talk());
            pGE.getPlayer().getCurrentRoom().getCharacters().setItem(vChara);  
        }
    }

}
