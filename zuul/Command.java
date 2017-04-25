 

public class Command
{
    private String aCommandWord = null;
    private String aSecondWord = null;
    
    
    /**
     * constructeur definissant les attributs de la command en fonction de ce qui lui a été passé en parametre
     * à l'initialisation 
     * @param pCommmandWord 1er mot
     * @param pSecond Word 2eme mot
     */
    public Command(final String pCommandWord, final String pSecondWord){
        this.aCommandWord=pCommandWord;
        this.aSecondWord=pSecondWord;        
    }
    
    /**
     * permet de retourner le 1er mot
     * @return le 1er mot de la commande
     */
    public String getCommandWord(){
        return this.aCommandWord;
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
     * verifie si la command a un 1er mot
     * @return si la command a un 1er mot
     */
        public boolean isUnknown(){
        return this.aCommandWord == null;
        }
        
} // Command
