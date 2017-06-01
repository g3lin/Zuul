package pkg_commands;

import pkg_EngineElements.*;
/**
 * Implementation of the 'inventaire' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class InventaireCommand extends Command
{
    /**
     * Constructor for objects of class InventaireCommand
     */
    public InventaireCommand()
    {
    }

    /**
     * affiche l'inventaire du joueur
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println(pGE.getPlayer().getInventory());
        }
        else {
            pGE.getUI().println("Quoi ?");

        }
    }

}
