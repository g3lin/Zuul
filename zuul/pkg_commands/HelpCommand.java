package pkg_commands;
import pkg_EngineElements.GameEngine;
/**
 * Implementation of the 'help' user command.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2011.07.31
 */
public class HelpCommand extends Command
{
    private CommandWords aCommandWords;
    
    /**
     * Constructor for objects of class HelpCommand
     */
    public HelpCommand(CommandWords pWords)
    {
        aCommandWords = pWords;
    }
    
    /**
     * Print out some help information. Here we print some stupid, 
     * cryptic message and a list of the command words.
     */
    public void execute(GameEngine pGE)
    {
        pGE.getUI().println("Bienvenue dans le jeu le manoir !");
        pGE.getUI().println("Vous vous retrouvez par hasard dans ce grand manoir et déciderez d'enqueter après d'etranges choses que vous retrouvez.");
        pGE.getUI().println("Vous pouvez piloter le jeu à l'aide des commandes texte :");
        this.aCommandWords.showAll();
        pGE.getUI().println("mais aussi à la souris à l'aide de l'interface graphique !");
        pGE.getUI().print("Cliquez sur un objet ou un personnage pour interragir avec lui");
        pGE.getUI().println(" ou cliquer sur les bords de l'image pour vous déplacer");
        pGE.getUI().println("N'hésitez pas à utiliser le bouton 'investigate' pour en savoir plus sur l'histoire!");
        pGE.getUI().println("Vous avez 7 minutes pour finir le jeu");
        pGE.getUI().println("Bonne chance !");
        
    }
}
