import java.util.StringTokenizer;

/*
 * This class is part of "World of Zuul". "World of Zuul" is a simple, 
 * text based adventure game.
 *
 * This parser takes user input and tries to interpret it as a "Zuul"
 * command. Every time it is called it takes a line as a String and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes (DB edited)
 * @version 2.0 (Jan 2003)
 */

public class Parser 
{

    private CommandWords aCommandWords;  // holds all valid command words

    /**
     * Create a new Parser.
     */
    public Parser() 
    {
        this.aCommandWords = new CommandWords();
    } // Parser()

    /**
     * Get a new command from the user. The command is read by
     * parsing the 'inputLine'.
     */
    public Command getCommand( final String pInputLine ) 
    {
        String vWord1 = "";
        String vWord2 = null;

        StringTokenizer tokenizer = new StringTokenizer( pInputLine );

        if ( tokenizer.hasMoreTokens() )
            vWord1 = tokenizer.nextToken();      // get first word

        if ( tokenizer.hasMoreTokens() )
            vWord2 = tokenizer.nextToken();      // get second word

        // note: we just ignore the rest of the input line.
        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        Command vCommand = this.aCommandWords.get(vWord1);
        if(vCommand != null) {
            vCommand.setSecondWord(vWord2);
        }

            return vCommand;
    } // getCommand(.)
    /**
     * Returns a String with valid command words.
     */
    public String showCommands() // bad name for this method !
    {
        return this.aCommandWords.showAll();
    } // showCommands() // bad name for this method !

} // Parser
