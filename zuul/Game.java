 

public class Game
{
    private Room aCurrentRoom = null;
    private Parser aParser;
    
    public Game(){
        createRooms();
        this.aParser = new Parser();
        play();
    }
    
    
    private void play() {
        this.printWelcome();
        boolean vFinished = false;
        while (vFinished == false){
            Command vCommand = this.aParser.getCommand();
            vFinished = this.processCommand( vCommand );
       }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    
    
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
        vHall.setExits(vCouloirF,null,vCouloirD,vCouloirG);
        vCouloirD.setExits(null,null,vChambreDAmis,vHall);
        vCouloirG.setExits(null,null,vHall,vChambrePrincipale);
        vCouloirF.setExits(vBibliotheque,vHall,vCouloirD,vCouloirG);
        vBibliotheque.setExits(null,vCouloirF,null,null);
        vChambreDAmis.setExits(vAntichambreDAmis,null,null,vCouloirD);
        vAntichambreDAmis.setExits(vSallePuzzle,vChambreDAmis,null,null);
        vSallePuzzle.setExits(vEscape,vAntichambreDAmis,null,null);
        vChambrePrincipale.setExits(vSalleEquipement,null,vCouloirG,null);
        vSalleEquipement.setExits(null,vChambrePrincipale,null,null);
        vEscape.setExits(null,vSallePuzzle,null,null);
        
        

    }
    
    public void goRoom(final Command pCommand) {
       Room vNextRoom = null;
        if (!pCommand.hasSecondWord()) {
            System.out.println("go where ?");
            return;
        }
      String vDirection = pCommand.getSecondWord() ;
          if (vDirection.equals("north")){
          vNextRoom = this.aCurrentRoom.aNorthExit;
        }
          if (vDirection.equals("south")){
          vNextRoom = this.aCurrentRoom.aSouthExit;
        }
          if (vDirection.equals("east")){
          vNextRoom = this.aCurrentRoom.aEastExit;
        }
          if (vDirection.equals("west")){
          vNextRoom = this.aCurrentRoom.aWestExit;
        }
        
            if (vNextRoom == null){
                System.out.print("There is no door !");
                return ;
            }
            else{
                this.aCurrentRoom=vNextRoom;
                System.out.print("You are ");
                System.out.println(this.aCurrentRoom.getDescription());
                System.out.print("Exits: ");
                System.out.println(this.aCurrentRoom.getExits());
            }
      
    }
    
    
    public void printWelcome() {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.print("You are ");
        System.out.println(this.aCurrentRoom.getDescription());
        System.out.print("Exits: ");
        System.out.println(this.aCurrentRoom.getExits());
    }
    
    
    public void printHelp(){
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university.");
        System.out.println("Your command words are:");
        System.out.println("  go quit help");
    }
   
    
    public boolean processCommand(final Command pCommand){
           
        String vCommandWord = pCommand.getCommandWord();
    
            if ( vCommandWord.equals("quit") ){
                
                return quit(pCommand);
            }
        
           
                if (vCommandWord.equals("help")){
                    printHelp();
                    return false;
            }
        
               if (vCommandWord.equals("go")){
                    goRoom(pCommand);
                    return false;
            }
                    
            
        System.out.println("I don't know what you mean...");
        return false;
        
    }
    
    
    public boolean quit(final Command pCommand) {
           if (pCommand.hasSecondWord() ){
            System.out.println("quit what ?");
            return false;
        }
        return true;
    }
} // Game
