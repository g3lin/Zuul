/**
 * @author pour le timer en temps reel
 * @author https://docs.oracle.com/javase/8/docs/technotes/guides/lang/Countdown.java
 */

import java.util.HashMap; 
import java.util.Stack; 

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Timer;
import java.util.TimerTask;
// import java.io.PrintWriter;

public class GameEngine
{

    private Parser aParser;
    private HashMap<String,Room> aRooms;
    private UserInterface aGUI;
    private Player aPlayer;
    private Timer aTimer;
    private int aTime;

    /**
     * constructeur par defaut de la classe Game
     * Lance à la création la procédure play pour commencer à jouer
     */

    public GameEngine(){
        aRooms = new HashMap<String,Room> ();
        this.aPlayer = new Player();
        createRooms();
        this.aTime = 150;

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
        Room vHall = new Room("Hall d'entrée central du manoir" ,"Images/hall.png");
        aRooms.put("Hall",vHall);

        Room vCouloirD = new Room("Couloir à droite de l'entrée","Images/couloirD.png" );
        aRooms.put("Couloir droite",vCouloirD);

        Room vCouloirG = new Room("Couloir à Gauche de l'entrée" ,"Images/couloirG.png");
        aRooms.put("Couloir Gauche",vCouloirG);

        Room vCouloirF = new Room("Couloir en face de l'entrée", "Images/couloirF.png" );
        aRooms.put("Couloir face",vCouloirF);

        Room vBibliotheque = new Room("Grande bibliothèque","Images/bibliotheque.png" );
        aRooms.put("Bibliotheque",vBibliotheque);

        Room vChambreDAmis = new Room("Chambre d'amis" ,"Images/chambreAmis.png");
        aRooms.put("Chambre d'amis",vChambreDAmis);

        

        Room vSallePuzzle = new Room("Salle du puzzle" ,"Images/sallePuzzle.png");
        aRooms.put("Salle du puzzle",vSallePuzzle);

        Room vChambrePrincipale = new Room("Chambre du maitre de maison","Images/chambrePrincipale.png" );
        aRooms.put("Chambre Principale",vChambrePrincipale);

        Room vSalleEquipement = new Room("Salle secrete de l'équipement","Images/salleEquipement.png" );
        aRooms.put("Salle Equipement",vSalleEquipement);

        Room vEscape = new Room("Crypte avec un bateau pour s'echapper","Images/escape.png"  );
        aRooms.put("Salle finale",vEscape);

        this.aPlayer.setCurrentRoom(vHall);
        // SET LES EXITS 
        vHall.setExits("north",vCouloirF);
        vHall.setExits("east",vCouloirD);
        vHall.setExits("west",vCouloirG);

        vCouloirD.setExits("east",vChambreDAmis);
        vCouloirD.setExits("west",vCouloirF);

        vCouloirG.setExits("east",vCouloirF);
        vCouloirG.setExits("west",vChambrePrincipale);

        vCouloirF.setExits("north",vBibliotheque);
    //         vCouloirF.setExits("south",vHall);
        vCouloirF.setExits("east",vCouloirD);
        vCouloirF.setExits("west",vCouloirG);

        vBibliotheque.setExits("south",vCouloirF);

        vChambreDAmis.setExits("north",vSallePuzzle);
        vChambreDAmis.setExits("west",vCouloirD);

        vSallePuzzle.setExits("north",vEscape);
        vSallePuzzle.setExits("south",vChambreDAmis);

        vChambrePrincipale.setExits("north",vSalleEquipement);
        vChambrePrincipale.setExits("east",vCouloirG);

        vSalleEquipement.setExits("south",vChambrePrincipale);

        vEscape.setExits("south",vSallePuzzle);

        //DECLARE LES OBJETS DU JEU
        Item vBague = new Item(15,"dague","une dague rouillée");
        Item vMontre = new Item(150,"montre","une montre rolex");
        Item vMarteau = new Item(500,"marteau","un marteau de bricolage");
        Item vCookie = new Item(15,"cookie","un cookie brillant et attirant");

        vCouloirF.getItems().setItem(vMontre);
        vCouloirF.getItems().setItem(vMarteau);

        vHall.getItems().setItem(vBague);

        vSalleEquipement.getItems().setItem(vCookie);

    }

    public void createTimer(){
        UserInterface vGUI = this.aGUI;

        final Timer timer = new Timer();
        TimerElement vTT = new TimerElement();
        vTT.pass(this, timer);
        timer.scheduleAtFixedRate(vTT, 0, 1000);

    }
    public void updateTime(final int pTime){
        this.aGUI.updateTimeGUI(pTime);
    }

    public int getTime(){
        return this.aTime;
    }

    /**
     * procedure affichant via S.o.p l'intro du jeu ainsi que la localisation du joueur
     */
    public void printWelcome() {
        aGUI.println("Welcome to the World of Zuul!");
        aGUI.println("World of Zuul is a new, incredibly boring adventure game.");
        aGUI.println(this.aPlayer.getCurrentRoom().getLongDescription());
        aGUI.showImage(aPlayer.getCurrentRoom().getImageName());
        createTimer();
    }

    public Player getPlayer(){
        return this.aPlayer;
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
        CommandWord vCommandWord = vCommand.getCommandWord();
        switch(vCommandWord){
            case UNKNOWN :
            System.out.println("La commance n'est pas reconnue");
            break;

            case   QUIT :
            if(vCommand.hasSecondWord())
                aGUI.println("Quit what?");
            else quit();
            break;

            case  HELP:
            printHelp();
            break;

            case GO:
            goRoom(vCommand);
            break;

            case  LOOK:
            look();
            break;

            case EAT:
            eat();
            break;

            case BACK:
            if(vCommand.hasSecondWord())
                aGUI.println("Je n'ai n'ai pas compris");
            else back();
            break;

            case TEST:
            if(vCommand.hasSecondWord())
                test(vCommand.getSecondWord() );
            else aGUI.println("Spécifier le fichier de test");
            break;

            case TAKE:
            if(vCommand.hasSecondWord())
                take(vCommand.getSecondWord() );
            else aGUI.println("Prendre quoi ?");
            break;

            case DROP:
            if(vCommand.hasSecondWord())
                drop(vCommand.getSecondWord() );
            else aGUI.println("Lacher quoi ?");
            break;

            case  STUFF:
            if(vCommand.hasSecondWord())
                aGUI.println("Je n'ai n'ai pas compris");
            else inventaire();
            break;

            case USE:
            if(vCommand.hasSecondWord())
                use(vCommand.getSecondWord() );
            else aGUI.println("Utisliser quoi ?");
            break;

            default:
            aGUI.println("I don't know what you mean...");
            break;

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
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection);
        if (vNextRoom == null){
            aGUI.println("There is no door !");
            return ;
        }
        else{
            this.aPlayer.getHistory().push(this.aPlayer.getCurrentRoom());

            goToRoom(vNextRoom);
        }

    }

    public void goToRoom(final Room pRoom){
        this.aPlayer.setCurrentRoom(pRoom);
        this.aGUI.println(this.aPlayer.getCurrentRoom().getLongDescription());
        if(this.aPlayer.getCurrentRoom().getImageName() != null)
        {this.aGUI.showImage(this.aPlayer.getCurrentRoom().getImageName());}
        this.aGUI.setSprites();
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
        aGUI.println(this.aPlayer.getCurrentRoom().getLongDescription()) ;
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

    public void back(){
        if (! this.aPlayer.getHistory().empty()) {
            Room vRoomPre = this.aPlayer.getHistory().pop();
            goToRoom(vRoomPre);
        }
        else this.aGUI.println("Vous ne pouvez pas revenir plus loin");
    }

    public void test(final String pFile){
        Scanner vScan;
        try { 
            vScan = new Scanner( new File("tests/"+pFile+".txt"));
            while ( vScan.hasNextLine() ) {
                String vLigne = vScan.nextLine();
                interpretCommand(vLigne);
            } // while
            vScan.close();
        } // try
        catch ( final FileNotFoundException pFNFE ) {
            this.aGUI.println("fichier non trouvé");
        } // catch
    }

    public void take(final String vItemString){

        Item vItem = this.aPlayer.getCurrentRoom().getItems().takeItem(vItemString);
        if(vItem != null ){
            if(this.aPlayer.getPoids() + vItem.getPoids() <= this.aPlayer.getPoidsMax()){
                this.aPlayer.getItems().setItem(vItem);
                this.aPlayer.setPoids( this.aPlayer.getPoids() + vItem.getPoids() );
            }
            else  {
                this.aGUI.println("Cet objet est trop lourd pour etre transporté");
                this.aPlayer.getCurrentRoom().getItems().setItem(vItem);                
            }

        }
        else{
            this.aGUI.println("objet non trouvé");
        }
        this.aGUI.setSprites();
    }

    public void drop(final String vItemString){

        Item vItem = this.aPlayer.getItems().takeItem(vItemString);
        if (vItem != null ){
            this.aPlayer.getCurrentRoom().getItems().setItem(vItem);
            this.aPlayer.setPoids( this.aPlayer.getPoids() - vItem.getPoids() );
        }

        else {
            this.aGUI.println("objet non trouvé");
        }
        this.aGUI.setSprites();
    }

    public void use(final String pItemString){
        if (pItemString.equals("cookie")){
            Item vItem = this.aPlayer.getCurrentRoom().getItems().takeItem(pItemString);
            if ( vItem != null){
                this.aGUI.println("Vous decidez de manger le cookie");
                this.aGUI.println("Cela ne vous fait rien mais vous trouvez derriere le cookie un exosquelette");
                this.aGUI.println("Vous pouvez maintenant porter bien plus d'objets sur vous");
                this.aPlayer.setPoidsMax(this.aPlayer.getPoidsMax()*5);
            }
            else{
                this.aGUI.println("Vous ne trouvez pas l'objet que vous voulez utiliser dans la piece");
                this.aGUI.println("Essayez de le poser si il est dans votre inventaire");
            }
        }
    }

    public void inventaire(){
        this.aGUI.println(this.aPlayer.getInventory());
    }

    public void gameOver(){
        this.aGUI.println("game over");
    }
} // Game
