package pkg_commands;
import pkg_EngineElements.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Implementation of the 'take' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */

public class TakeCommand extends Command
{
    /**
     * Constructor for objects of class TakeCommand
     */
    public TakeCommand()
    {
    }

    /**
     *  prend un item dans la salle où est le joueur et va le mettre dans l'inventaire du joueur
     *  si et seulement si il lui reste assez de place dans son inventaire
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
