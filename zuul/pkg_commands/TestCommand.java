package pkg_commands;
import pkg_EngineElements.GameEngine;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Implementation of the 'quit' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */

public class TestCommand extends Command
{
    /**
     * Constructor for objects of class TestCommand
     */
    public TestCommand()
    {
    }

    /**
     *  cherche le fichier de test correspondant et si il le trouve va l'executer
     *  @param pGE le GameEngine du jeu
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println("Spécifiez le fichier de test");
        }
        else {
            Scanner vScan;
            try { 
                vScan = new Scanner(this.getClass().getClassLoader().getResource("tests/"+getSecondWord()+".txt").openStream());
                while ( vScan.hasNextLine() ) {
                    String vLigne = vScan.nextLine();
                    pGE.interpretCommand(vLigne);
                } // while
                vScan.close();
            } // try
            catch ( final Exception e ) {
                pGE.getUI().println("fichier non trouvé");
            } // catch

        }
    }

}
