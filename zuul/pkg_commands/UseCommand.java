package pkg_commands;
import pkg_EngineElements.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Implementation of the 'use' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */

public class UseCommand extends Command
{
    /**
     * Constructor for objects of class UseCommand
     */
    public UseCommand()
    {
    }

    /**
     *  
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println("Spécifiez un objet à utiliser");
        }
        else {

            if (getSecondWord().equals("cookie")){
                Item vItem = pGE.getPlayer().getCurrentRoom().getItems().takeItem(getSecondWord());
                if ( vItem != null){
                    pGE.getUI().println("Vous decidez de manger le cookie");
                    pGE.getUI().println("Cela ne vous fait rien mais vous trouvez derriere le cookie un exosquelette");
                    pGE.getUI().println("Vous pouvez maintenant porter bien plus d'objets sur vous");
                    pGE.getPlayer().setPoidsMax(pGE.getPlayer().getPoidsMax()*5);
                }
                else{
                    pGE.getUI().println("Vous ne trouvez pas l'objet que vous voulez utiliser dans la piece");
                    pGE.getUI().println("Essayez de le poser si il est dans votre inventaire");
                }
                pGE.getUI().setSprites();
            }

            if (getSecondWord().equals("beamer")){
                Item vItem = pGE.getPlayer().getCurrentRoom().getItems().takeItem(getSecondWord());
                if ( vItem != null){
                    if (vItem.getState() == 0){
                        pGE.getUI().println("Ce beamer est maintenant chargé");
                        pGE.getUI().println("Si vous l'utilisez encore il vous ramènera ");
                        pGE.getUI().print("instantanément dans la pièce actuelle");
                        pGE.getUI().println("\n CET OBJET EST SECRET DEFENSE");
                        vItem.setState(1);
                        vItem.setStateVar(pGE.getPlayer().getCurrentRoom());
                    }
                    else if (vItem.getState() == 1){
                        pGE.getUI().println("BEAMER ACTIVÉ");
                        pGE.getUI().println("\n TÉLÉPORTATION EN COURS");
                        Room vRoom = (Room)vItem.getStateVar();
                        pGE.goToRoom(vRoom);
                        pGE.getPlayer().resetHistory();
                        vItem.setState(0);
                    }

                    pGE.getPlayer().getCurrentRoom().getItems().setItem(vItem);
                }
                else{
                    pGE.getUI().println("Vous ne trouvez pas l'objet que vous voulez utiliser dans la piece");
                    pGE.getUI().println("Essayez de le poser si il est dans votre inventaire");
                }
                pGE.getUI().setSprites();
            }
        }

    }
}

