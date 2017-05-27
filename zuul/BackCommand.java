/**
 * Implementation of the 'quit' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class BackCommand extends Command
{
    /**
     * Constructor for objects of class QuitCommand
     */
    public BackCommand()
    {
    }

    /**
     *  
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            if (! pGE.getPlayer().getHistory().empty()) {
                Room vRoomPre = pGE.getPlayer().getHistory().pop();
                if (vRoomPre.getDescription().equals("Entrée du manoir")){
                    pGE.getUI().print("Vous ne pouvez pas revenir plus loin use"); 
                    pGE.getUI().println("car la porte s'est refermée quand vous êtes entré");
                }
                else{
                    pGE.goToRoom(vRoomPre);
                }
            }
            else pGE.getUI().println("Vous ne pouvez pas revenir plus loin");;
        }
        else {

        }
    }

}
