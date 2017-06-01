import pkg_EngineElements.GameEngine;
import pkg_InterfaceElements.UserInterface;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class.
 * 
 *  This main class creates the necessary implementation objects and starts the game off.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author Antoine Gélin
 * @version 3.0 (May 2017)
 */

public class Game
{
    private UserInterface aGUI;
    private GameEngine aEngine;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        aEngine = new GameEngine();
        aGUI = new UserInterface(aEngine);
        aEngine.setGUI(aGUI);
    }

    public static void main( String[] pArgs ) { 
        new Game();
    }
}
