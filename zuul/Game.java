 

public class Game
{
    private Room aCurrentRoom = null;
    private Parser aParser;
    
    /**
     * constructeur par defaut de la classe Game
     * Lance à la création la procédure play pour commencer à jouer
     */
    
    public Game(){
        createRooms();
        this.aParser = new Parser();
        play();
    }
    
    /**
     * procédure principale initialisant le parser
     * et vérifiant continuellement si le joueur veut quitter le jeu.
     * Affiche un message à la sortie
     */
    private void play() {
        this.printWelcome();
        boolean vFinished = false;
        while (vFinished == false){
            Command vCommand = this.aParser.getCommand();
            vFinished = this.processCommand( vCommand );
       }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    
    /**
     * Procédure permettant de créer chaque pièce du jeu 
     * Met en place les sorties des salles par arpport aux autres
     */
    private void createRooms(){
        Room vHall = new Room("Hall d'entrée central du manoir" );
        Room vCouloirD = new Room("Couloir à droite de l'entrée" );
        Room vCouloirG = new Room("Couloir à Gauche de l'entrée" );
        Room vCouloirF = new Room("Couloir en face de l'entrée" );
        Room vBibliotheque = new Room("Grande bibliothèque" );
        Room vChambreDAmis = new Room("Chambre d'amis" );
        Room vAntichambreDAmis = new Room("Antihambre de la chambre d'amis" );
        Room vSallePuzzle = new Room("Salle du puzzle" );
        Room vChambrePrincipale = new Room("Chambre du maitre de maison" );
        Room vSalleEquipement = new Room("Salle secrete de l'équipement" );
        Room vEscape = new Room("Crypte avec un bateau pour s'echapper" );
        
        
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
     * procedure permettant de changer de pièce dans le jeu
     * @param une command
     */
    public void goRoom(final Command pCommand) {
       
        if (!pCommand.hasSecondWord()) {
            System.out.println("go where ?");
            return;
        }
      String vDirection = pCommand.getSecondWord() ;
        Room vNextRoom = this.aCurrentRoom.getExit(vDirection);
            if (vNextRoom == null){
                System.out.print("There is no door !");
                return ;
            }
            else{
                this.aCurrentRoom=vNextRoom;
                
                this.aCurrentRoom.getLongDescription();
            }
      
    }
    
    /**
     * procedure affichant via S.o.p l'intro du jeu ainsi que la localisation du joueur
     */
    public void printWelcome() {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        this.aCurrentRoom.getLongDescription();
    }
    
    /**
     * procedure affichant via S.o.p le message d'aide si le joueur l'a demandé
     */
    public void printHelp(){
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university.");
        System.out.println("Your command words are:");
        System.out.println("  go quit help");
    }
   
    /**
     * methode permettant de prendre en charge les commandes
     * en fonction du 1er mot de la commande, va voir les différentes actions à effectuer
     * @param une command
     * @return le fait ou non que le joueur veuille quitter
     */
    public boolean processCommand(final Command pCommand){
           
        String vCommandWord = pCommand.getCommandWord();
    
            if ( vCommandWord.equals("quit") ){
                
                return quit(pCommand);
            }
        
           
             else if (vCommandWord.equals("help")){
                    printHelp();
                    return false;
            }
        
             else if (vCommandWord.equals("go")){
                    goRoom(pCommand);
                    return false;
            }
                    
            else if (pCommand.isUnknown() ) {
                    System.out.println("I don't know what you mean...");
                    return false;
                }
            
                else if (vCommandWord.equals("look")){
                    look();
                    return false;
            }
            
            else if (vCommandWord.equals("eat")){
                    eat();
                    return false;
            }
 
        
            return false;
    }
    
    
    /**
     * verifie si le joueur veut bien quitter et renvoie true si c'est le cas
     * @param une command
     * @return le fait ou non que la command ait un 2nd mot
     */
    public boolean quit(final Command pCommand) {
           if (pCommand.hasSecondWord() ){
            System.out.println("quit what ?");
            return false;
        }
        return true;
    }
    
    private void look() {
        this.aCurrentRoom.getLongDescription() ;
    }
    
    private void eat() {
        System.out.println ("Vous avez maintenant mangé ");
        System.out.println ("Vous n'avez plus faim du tout ");
     }

} // Game
