 import java.util.HashMap; 

public class GameEngine
{
    private Room aCurrentRoom = null;
    private Parser aParser;
    private HashMap<String,Room> aRooms;
    private UserInterface aGUI;
    /**
     * constructeur par defaut de la classe Game
     * Lance à la création la procédure play pour commencer à jouer
     */
    
    public GameEngine(){
        aRooms = new HashMap<String,Room> ();
        createRooms();
        this.aParser = new Parser();
        
    }
    
   /**
    * met en place l'interface graphique 
    * @param pUserInterface IHM souhaitée
    */
    public void setGUI(UserInterface pUserInterface)
    {
        aGUI = pUserInterface;
        printWelcome();
    }
    
    
    /**
     * Procédure permettant de créer chaque pièce du jeu 
     * Met en place les sorties des salles par arpport aux autres
     */
    private void createRooms(){
        Room vHall = new Room("Hall d'entrée central du manoir" ,"Images/hall.gif");
        aRooms.put("Hall",vHall);
        
        Room vCouloirD = new Room("Couloir à droite de l'entrée","Images/couloirD.gif" );
        aRooms.put("Couloir droite",vCouloirD);
        
        Room vCouloirG = new Room("Couloir à Gauche de l'entrée" ,"Images/couloirG.gif");
        aRooms.put("Couloir Gauche",vCouloirG);
        
        Room vCouloirF = new Room("Couloir en face de l'entrée", "Images/couloirF.gif" );
        aRooms.put("Couloir face",vCouloirF);
        
        Room vBibliotheque = new Room("Grande bibliothèque","Images/bibliotheque.gif" );
        aRooms.put("Bibliotheque",vBibliotheque);
        
        Room vChambreDAmis = new Room("Chambre d'amis" ,"Images/chambreAmis.gif");
        aRooms.put("Chambre d'amis",vChambreDAmis);
        
        Room vAntichambreDAmis = new Room("Antihambre de la chambre d'amis" ,"Images/antichambreAmis.gif");
        aRooms.put("Antichambre d'amis",vAntichambreDAmis);
        
        Room vSallePuzzle = new Room("Salle du puzzle" ,"Images/sallePuzzle.gif");
        aRooms.put("Salle du puzzle",vSallePuzzle);
        
        Room vChambrePrincipale = new Room("Chambre du maitre de maison","Images/chambrePrincipale.gif" );
        aRooms.put("Chambre Principale",vChambrePrincipale);
        
        Room vSalleEquipement = new Room("Salle secrete de l'équipement","Images/salleEquipement.gif" );
        aRooms.put("Salle Equipement",vSalleEquipement);
        
        Room vEscape = new Room("Crypte avec un bateau pour s'echapper","Images/escape.gif"  );
        aRooms.put("Salle finale",vEscape);
        
        
        
        this.aCurrentRoom=vHall;
        // Nord sud est ouest
        vHall.setExits("north",vCouloirF);
        vHall.setExits("east",vCouloirD);
        vHall.setExits("west",vCouloirG);
        
        vCouloirD.setExits("east",vChambreDAmis);
        vCouloirD.setExits("west",vHall);
        
        vCouloirG.setExits("east",vHall);
        vCouloirG.setExits("west",vChambrePrincipale);
        
        vCouloirF.setExits("north",vBibliotheque);
        vCouloirF.setExits("south",vHall);
        vCouloirF.setExits("east",vCouloirD);
        vCouloirF.setExits("west",vCouloirG);
        
        vBibliotheque.setExits("south",vCouloirF);
        
        vChambreDAmis.setExits("north",vAntichambreDAmis);
        vChambreDAmis.setExits("west",vCouloirD);
        
        vAntichambreDAmis.setExits("north",vSallePuzzle);
        vAntichambreDAmis.setExits("south",vChambreDAmis);
        
        vSallePuzzle.setExits("north",vEscape);
        vSallePuzzle.setExits("south",vAntichambreDAmis);
        
        vChambrePrincipale.setExits("north",vSalleEquipement);
        vChambrePrincipale.setExits("east",vCouloirG);
        
        vSalleEquipement.setExits("south",vChambrePrincipale);
        
        vEscape.setExits("south",vSallePuzzle);
        
        
        
    }
    
    
    
    /**
     * procedure affichant via S.o.p l'intro du jeu ainsi que la localisation du joueur
     */
    public void printWelcome() {
        aGUI.println("Welcome to the World of Zuul!");
        aGUI.println("World of Zuul is a new, incredibly boring adventure game.");
        aGUI.println(this.aCurrentRoom.getLongDescription());
        aGUI.showImage(aCurrentRoom.getImageName());
    }
    
   
    /**
     * methode permettant de prendre en charge les commandes
     * en fonction du 1er mot de la commande, va voir les différentes actions à effectuer
     * @param une command
     * @return le fait ou non que le joueur veuille quitter
     */
    public void interpretCommand(final String pCommand){
        aGUI.println(pCommand);
        Command vCommand = aParser.getCommand(pCommand);
        String vCommandWord = vCommand.getCommandWord();
        
    
            if ( vCommandWord.equals("quit") ){
                    if(vCommand.hasSecondWord())
                        aGUI.println("Quit what?");
                quit();
            }
        
           
             else if (vCommandWord.equals("help")){
                    printHelp();
                    
            }
        
             else if (vCommandWord.equals("go")){
                    goRoom(vCommand);
                    
            }
                    
            else if (vCommand.isUnknown() ) {
                    aGUI.println("I don't know what you mean...");
                    
                }
            
                else if (vCommandWord.equals("look")){
                    look();
                    
            }
            
            else if (vCommandWord.equals("eat")){
                    eat();
                   
            }
 
        else {
                    aGUI.println("I don't know what you mean...");
           
                }
            }
    
    /**
     * procedure permettant de changer de pièce dans le jeu
     * @param pCommand une command
     */
    public void goRoom(final Command pCommand) {
       
        if (!pCommand.hasSecondWord()) {
            aGUI.println("go where ?");
            return;
        }
      String vDirection = pCommand.getSecondWord() ;
        Room vNextRoom = this.aCurrentRoom.getExit(vDirection);
            if (vNextRoom == null){
                aGUI.print("There is no door !");
                return ;
            }
            else{
                this.aCurrentRoom=vNextRoom;
                
                this.aGUI.println(this.aCurrentRoom.getLongDescription());
                
                if(this.aCurrentRoom.getImageName() != null)
                this.aGUI.showImage(this.aCurrentRoom.getImageName());
            }
      
    }
    
    /**
     * quitte le jeu
     */
    public void quit() {
         aGUI.println("Thank you for playing.  Good bye.");
         aGUI.enable(false);
    }
    
   /**
     * regarde autour
     */
    private void look() {
        aGUI.println(this.aCurrentRoom.getLongDescription()) ;
    }
    
    /**
     * mange
     */
    private void eat() {
        aGUI.println ("Vous avez maintenant mangé ");
        aGUI.println ("Vous n'avez plus faim du tout ");
     }
     
      /**
     * procedure affichant via l'interface graphique le message d'aide si le joueur l'a demandé
     */
    public void printHelp(){
        aGUI.println("Vos commandes sont:");
        aGUI.println( this.aParser.showCommands() );
    }
   

} // Game