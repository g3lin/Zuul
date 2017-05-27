package pkg_EngineElements;
/**
 * @author pour le timer en temps reel
 * @author https://docs.oracle.com/javase/8/docs/technotes/guides/lang/Countdown.java
 */
import pkg_commands.Parser;
import pkg_InterfaceElements.UserInterface;
import pkg_commands.Command;

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
        Room vHall = new Room("Entrée du manoir" ,"Images/hall.png");
        aRooms.put("entree",vHall);

        Room vCouloirD = new Room("Couloir à droite de l'entrée","Images/couloirD.png" );
        aRooms.put("couloirD",vCouloirD);

        Room vCouloirG = new Room("Couloir à Gauche de l'entrée" ,"Images/couloirG.png");
        aRooms.put("couloirG",vCouloirG);

        Room vCouloirF = new Room("Couloir en face de l'entrée", "Images/couloirF.png" );
        aRooms.put("couloirF",vCouloirF);

        Room vBibliotheque = new Room("Grande bibliothèque","Images/bibliotheque.png" );
        aRooms.put("bibliotheque",vBibliotheque);

        Room vChambreDAmis = new Room("Chambre d'amis" ,"Images/chambreAmis.png");
        aRooms.put("chambreamis",vChambreDAmis);

        Room vSallePuzzle = new Room("Salle du puzzle" ,"Images/sallePuzzle.png");
        aRooms.put("sallepuzzle",vSallePuzzle);

        Room vChambrePrincipale = new Room("Chambre du maitre de maison","Images/chambrePrincipale.png" );
        aRooms.put("chambreprincipale",vChambrePrincipale);

        Room vSalleEquipement = new Room("Salle secrete de l'équipement","Images/salleEquipement.png" );
        aRooms.put("lab",vSalleEquipement);

        String vDescTPRoom = "Salle expérimentale permettant de se téléporter à un endroit de la maison au hasard";
        Room[] vExitsArray = new Room[]{vSalleEquipement, vChambrePrincipale, vBibliotheque, vCouloirG};
        TransporterRoom vTPRoom = new TransporterRoom(vDescTPRoom, "Images/TPRoom.png", vExitsArray);
        aRooms.put("tproom",vTPRoom);

        Room vEscape = new Room("Crypte avec un bateau pour s'echapper","Images/escape.png"  );
        aRooms.put("escape",vEscape);

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
        vSalleEquipement.setExits("east",vTPRoom);

        vEscape.setExits("south",vSallePuzzle);

        //DECLARE LES OBJETS DU JEU
        Item vBague = new Item(15,"dague","une dague rouillée");
        Item vMontre = new Item(150,"montre","une montre rolex");
        Item vMarteau = new Item(500,"marteau","un marteau de bricolage");
        Item vCookie = new Item(15,"cookie","un cookie brillant et attirant");
        Item vBeamer = new Item(600,"beamer","un beamer, objet à l'apparence futuristique");

        vCouloirF.getItems().setItem(vMontre);
        vCouloirF.getItems().setItem(vMarteau);

        vHall.getItems().setItem(vBague);

        vSalleEquipement.getItems().setItem(vCookie);
        vSalleEquipement.getItems().setItem(vBeamer);

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

    public UserInterface getUI(){
        return this.aGUI;
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
        vCommand.execute(this);

    }

    public void goToRoom(final Room pRoom){
        this.aPlayer.setCurrentRoom(pRoom);
        this.aGUI.println(this.aPlayer.getCurrentRoom().getLongDescription());
        if(this.aPlayer.getCurrentRoom().getImageName() != null)
        {this.aGUI.showImage(this.aPlayer.getCurrentRoom().getImageName());}
        this.aGUI.setSprites();
    }

    public HashMap<String,Room> getRooms(){
        return this.aRooms;
    }

    public void gameOver(){
        this.aGUI.println("game over");
    }
} // Game
