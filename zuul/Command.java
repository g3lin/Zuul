 

public abstract class Command
{
    private String aSecondWord ;
    /**
     * constructeur definissant les attributs de la command en fonction de ce qui lui a été passé en parametre
     * à l'initialisation 
     * @param pCommmandWord 1er mot
     * @param pSecond Word 2eme mot
     */
    public Command(){
        this.aSecondWord=null;        
    }
    

    
    /**
     * permet de retourner le 2nd mot
     *  @return le 2nd mot de la command
     */
    public String getSecondWord(){
        return this.aSecondWord;
    }
    
    /**
     * retourne si la command a un second mot
     * @return si la command a un second mot
     */    
    public boolean hasSecondWord(){
        return this.aSecondWord != null;
        }
        
        /**
     * Define the second word of this command (the word
     * entered after the command word). Null indicates that 
     * there was no second word.
     */
    public void setSecondWord(String pSecondWord)
    {
        this.aSecondWord = pSecondWord;
    }
        
    
    /**
     * Execute this command. A flag is returned indicating whether
     * the game is over as a result of this command.
     */
    public abstract void execute(GameEngine aGE);    
} // Command
