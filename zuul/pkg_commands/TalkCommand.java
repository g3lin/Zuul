package pkg_commands;

import pkg_EngineElements.*;
/**
 * Implementation of the 'talk' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class TalkCommand extends Command
{
    /**
     * Constructor for objects of class TalkCommand
     */
    public TalkCommand()
    {
    }

    /**
     *  parle à un personnage sous reserve qu'il soit dans la salle où le joueur est actuellement
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println("Vous ne trouvez personne à qui parler. \nC'est bien dommage");
        }
        else {
            String CharacterNString = getSecondWord();
            Item vIChara = pGE.getPlayer().getCurrentRoom().getCharacters().takeItem(getSecondWord());
            if (vIChara != null){
                PNJElement vChara = (PNJElement)vIChara;
                pGE.getPlayer().getCurrentRoom().getCharacters().setItem(vChara);  
                pGE.getUI().println(vChara.talk());
                
            }
            else {
                pGE.getUI().println("Vous parlez tout seul. \nC'est bien dommage");
            }
        }
    }

}
