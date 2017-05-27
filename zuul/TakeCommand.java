import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Implementation of the 'quit' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */

public class TakeCommand extends Command
{
    /**
     * Constructor for objects of class QuitCommand
     */
    public TakeCommand()
    {
    }

    /**
     *  
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println("Quel objet voulez vous prendre ?");
        }
        else {
            Item vItem = pGE.getPlayer().getCurrentRoom().getItems().takeItem(getSecondWord());
            if(vItem != null ){
                if(pGE.getPlayer().getPoids() + vItem.getPoids() <= pGE.getPlayer().getPoidsMax()){
                    pGE.getPlayer().getItems().setItem(vItem);
                    pGE.getPlayer().setPoids( pGE.getPlayer().getPoids() + vItem.getPoids() );
                }
                else  {
                    pGE.getUI().println("Cet objet est trop lourd pour etre transporté");
                    pGE.getPlayer().getCurrentRoom().getItems().setItem(vItem);                
                }

            }
            else{
                pGE.getUI().println("objet non trouvé");
            }
            pGE.getUI().setSprites();
        }
    }

}
