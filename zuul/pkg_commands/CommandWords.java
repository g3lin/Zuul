package pkg_commands;
import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau
 * @version 2008.03.30 + 2013.09.15
 */

 

public class CommandWords
{
    // tableau constant qui contient tous les mots de commande valides
   private HashMap<String, CommandWord> validCommands;
   private HashMap<CommandWord, Command> aCommands;

    /**
     * Constructeur par defaut
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
        aCommands = new HashMap<CommandWord, Command>();
        aCommands.put(CommandWord.GO,new GoCommand());
        aCommands.put(CommandWord.HELP,new HelpCommand(this));
        aCommands.put(CommandWord.QUIT,new QuitCommand());
        aCommands.put(CommandWord.LOOK,new LookCommand());
        aCommands.put(CommandWord.EAT,new EatCommand());
        aCommands.put(CommandWord.BACK,new BackCommand());
        aCommands.put(CommandWord.TEST,new TestCommand());
        aCommands.put(CommandWord.TAKE,new TakeCommand());
        aCommands.put(CommandWord.DROP,new DropCommand());
        aCommands.put(CommandWord.USE,new UseCommand());
        aCommands.put(CommandWord.STUFF,new InventaireCommand());
        aCommands.put(CommandWord.ALEA,new AleaCommand());
        
    } // CommandWords()

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }

        /**
     * Given a command word, find and return the matching command object.
     * Return null if there is no command with this name.
     */
    public Command get(String pWord)
    {
        CommandWord vCWord = getCommandWord(pWord);
        return (Command)this.aCommands.get(vCWord);
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public String showAll() 
    {
        
        String vCommands = "";
        for(String command : validCommands.keySet()) {
            
            vCommands += command+"    ";
        }
        return vCommands;
    }
    
    
} // CommandWords