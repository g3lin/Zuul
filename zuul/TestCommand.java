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
     * Constructor for objects of class QuitCommand
     */
    public TestCommand()
    {
    }

    /**
     *  
     */
    public void execute(GameEngine pGE)
    {
        if(getSecondWord() == null) {
            pGE.getUI().println("Spécifiez le fichier de test");
        }
        else {
            Scanner vScan;
            try { 
                vScan = new Scanner( new File("tests/"+getSecondWord()+".txt"));
                while ( vScan.hasNextLine() ) {
                    String vLigne = vScan.nextLine();
                    pGE.interpretCommand(vLigne);
                } // while
                vScan.close();
            } // try
            catch ( final FileNotFoundException pFNFE ) {
                pGE.getUI().println("fichier non trouvé");
            } // catch

        }
    }

}
