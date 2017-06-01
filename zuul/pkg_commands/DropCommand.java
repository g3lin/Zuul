package pkg_commands;

import pkg_EngineElements.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Implementation of the 'drop' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */

public class DropCommand extends Command
{
    /**
     * Constructor for objects of class DropCommand
     */
    public DropCommand()
    {
    }

    /**
     *  prend un item dans l'inventaire du joueur et le mose dans la piece où le joueur est actuellement
     *  @param pGE le GameEngine du jeu
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println("Quel objet voulez vous déposer ?");
        }
        else {
            Item vItem = pGE.getPlayer().getItems().takeItem(getSecondWord());
            if (vItem != null ){
                pGE.getPlayer().getCurrentRoom().getItems().setItem(vItem);
                pGE.getPlayer().setPoids( pGE.getPlayer().getPoids() - vItem.getPoids() );
            }

            else {
                pGE.getUI().println("objet non trouvé");
            }
            pGE.getUI().setSprites();

        }
       

    }
}


