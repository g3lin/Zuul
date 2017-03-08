 

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
        Room vOutside = new Room("outside the main entrance of the university" );
        Room vTheatre = new Room("in a lecture theatre" );
        Room vPub = new Room("in the campus pub" );
        Room vLab = new Room("in a computing lab" );
        Room vOffice = new Room("in the computing admin office" );
        
        this.aCurrentRoom=vOutside;
        
        vOutside.setExits(null,vLab,vTheatre,vPub);
        vTheatre.setExits(null,null,null,vOutside);
        vPub.setExits(null,null,vOutside,null);
        vLab.setExits(vOutside,null,vOffice,null);
        vOffice.setExits(null,null,null,vLab);
        

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
