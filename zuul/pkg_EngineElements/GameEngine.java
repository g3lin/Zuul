package pkg_EngineElements;
/**
 * @author pour le timer en temps reel
 * @author https://docs.oracle.com/javase/8/docs/technotes/guides/lang/Countdown.java
 */
import pkg_commands.Parser;
import pkg_InterfaceElements.UserInterface;
import pkg_InterfaceElements.Sprite;
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
        this.aTime = 420;

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
        Room vHall = new Room("Entrée du manoir","Je suis trempé, l'orage est trop violent, je dois m'abriter. Je vais parler avec l'habitant" ,"Images/hall.png");
        aRooms.put("entree",vHall);

        Room vCouloirD = new Room("Couloir à droite de l'entrée","Je vais vers la chambre d'amis, cette maison est magnifique!","Images/couloirD.png" );
        aRooms.put("couloirD",vCouloirD);

        Room vCouloirG = new Room("Couloir à Gauche de l'entrée" ,"Je vais vers la chambre du comte, cette maison est magnifique!","Images/couloirG.png");
        aRooms.put("couloirG",vCouloirG);

        Room vCouloirF = new Room("Couloir en face de l'entrée","Quelle belle demeure! La bibliotheque semble etre en haut des escaliers", "Images/couloirF.png" );
        aRooms.put("couloirF",vCouloirF);

        Room vBibliotheque = new Room("Grande bibliothèque","Je me sens bien dans cette ambiance studieuse","Images/bibliotheque.png" );
        aRooms.put("bibliotheque",vBibliotheque);

        Room vChambreDAmis = new Room("Chambre d'amis" ,"Ma chambre pour la nuit, je suis content d'être à l'abri","Images/chambreAmis.png");
        aRooms.put("chambreamis",vChambreDAmis);

        Room vSallePuzzle = new Room("Salle du puzzle","Si je dois m'echapper de cette maison, c'est forcément par ici !" ,"Images/sallePuzzle.png");
        aRooms.put("sallepuzzle",vSallePuzzle);

        Room vChambrePrincipale = new Room("Chambre du maitre de maison","Le comte ne semble pas être dans sa chambre c'est tès bizarre, il m'a dit qu'il y serait !","Images/chambrePrincipale.png" );
        aRooms.put("chambreprincipale",vChambrePrincipale);

        Room vSalleEquipement = new Room("Salle secrete de l'équipement","Mon dieu cette si belle maison me parait bien louche tout d'un coup\nIl faut que je me tire d'ici!","Images/salleEquipement.png" );
        aRooms.put("lab",vSalleEquipement);

        String vDescTPRoom = "Salle expérimentale permettant de se téléporter à un endroit de la maison au hasard";
        Room[] vExitsArray = new Room[]{vSalleEquipement, vChambrePrincipale, vBibliotheque, vCouloirG};
        TransporterRoom vTPRoom = new TransporterRoom(vDescTPRoom,"Je pense que cette maison n'est pas ce qu'elle semble etre. Tant d'equipement, on dirait une base de la CIA avec mon hôte absent. Je pense que le mieux serait de s'en aller au plus vite !", "Images/TPRoom.png", vExitsArray);
        aRooms.put("tproom",vTPRoom);

        Room vEscape = new Room("Crypte avec un bateau pour s'echapper","Cela fait tant de bien de retrouver l'air libre, je peux retorner vers la côte avec le bateau maintenant qu'il fait beau","Images/escape.png"  );
        aRooms.put("escape",vEscape);

        this.aPlayer.setCurrentRoom(vHall);
        // SET LES EXITS 
        vHall.setExits("north",vCouloirF);
        //          vHall.setExits("east",vCouloirD);
        //          vHall.setExits("west",vCouloirG);

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

        //          vSallePuzzle.setExits("north",vEscape);
        vSallePuzzle.setExits("south",vChambreDAmis);

        vChambrePrincipale.setExits("north",vSalleEquipement);
        vChambrePrincipale.setExits("east",vCouloirG);

        vSalleEquipement.setExits("south",vChambrePrincipale);
        vSalleEquipement.setExits("east",vTPRoom);

        //          vEscape.setExits("south",vSallePuzzle);

        //DECLARE LES OBJETS DU JEU
        Item vBague = new Item(15,"dague","une dague rouillée");
        Item vMontre = new Item(150,"montre","une montre rolex");
        Item vMarteau = new Item(500,"marteau","un marteau de bricolage");
        Item vCookie = new Item(15,"cookie","un cookie brillant et attirant");
        Item vChallenger = new Item(9999,"challenger","un terminal qui semble deverouiller une sortie dans la roche");
        Item vBoat = new Item(9999,"boat","un bateau pour s'échapper");
        
        Item vBeamer = new Item(600,"beamer","un beamer, objet à l'apparence futuristique");
        String[] vDialC = new String[] {"Venez donc par ici! \nL'orage a l'air violent ce soir, vous êtes trempé !",
                "Vous pouvez me suivre dans la bibliotheque, vous réchauffer à l'intèrieur !",
                "Vous pouvez rester dans votre chambre le temps que l'orage cesse.\n Faites comme chez vous il y a une chambre d'amis à l'est !",
                "Je vais maintenant dans ma chambre"};

        Room[] vCPattern = new Room[] { null, vBibliotheque, null,vHall};
        PNJElement vPNJComte = new PNJElement("comte", "le comte du chateau", vDialC, vCPattern, this);

        //vCouloirF.getItems().setItem(vMontre);
        //vCouloirF.getItems().setItem(vMarteau);

        //vHall.getItems().setItem(vBague);
        vHall.getCharacters().setItem(vPNJComte);
        vPNJComte.setRoom(vHall);

        vTPRoom.getItems().setItem(vCookie);
        vSalleEquipement.getItems().setItem(vBeamer);
        vBeamer.getSprite().setHeight(20);
        
        vSallePuzzle.getItems().setItem(vChallenger);
        vChallenger.getSprite().setHeight(50);
        vChallenger.getSprite().tpTo(20,40);
        
        vEscape.getItems().setItem(vBoat);
        vBoat.getSprite().tpTo(0,20);
        vBoat.getSprite().setHeight(50);
        
        

    }
    
    public void createTimer(){
        UserInterface vGUI = this.aGUI;

        final Timer timer = new Timer();
        this.aTimer = timer;
        TimerElement vTT = new TimerElement();
        vTT.pass(this, timer);
        timer.scheduleAtFixedRate(vTT, 0, 1000);

    }

    public void updateTime(final int pTime){
        this.aGUI.updateTimeGUI(pTime);
        this.aTime = pTime;
    }

    public int getTime(){
        return this.aTime;
    }

    /**
     * procedure affichant via S.o.p l'intro du jeu ainsi que la localisation du joueur
     */
    public void printWelcome() {
        aGUI.println("LE MANOIR\n");
        aGUI.println("Vous êtes un journaliste. Par une nuit d'orage vous vous retrouvez dehors lors d'une enquête");
        aGUI.println("Le manoir sur le bord de la route vous semble un bon endroit pour trouver refuge le temps que le déluge cesse \n");
        
        
        aGUI.showImage(aPlayer.getCurrentRoom().getImageName());
        createTimer();
        while(this.aTime >= 415){
            this.aGUI.setFSSprite(new Sprite("welcome","Images/welcome.png",0,0,33));
        }
        this.aGUI.resetFSSprite();
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
        aGUI.resetText();
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

    public void movePNJRoom(final PNJElement pPNJ ,final Room pR){
       ItemList vIL = pPNJ.getRoom().getCharacters();
       vIL.takeItem("comte");
        pR.getCharacters().setItem(pPNJ);
        pPNJ.setRoom(pR);
        this.aGUI.setSprites();
    }

    public HashMap<String,Room> getRooms(){
        return this.aRooms;
    }

    public void gameOver(){
        this.aGUI.println("game over");
        this.aTimer.cancel();
        this.aGUI.enable(false);
        this.aGUI.resetButtons();
        this.aGUI.setFSSprite(new Sprite("gameover","Images/anim_gameover.gif",0,0,33));
    }
    
    public void win(){
        this.aGUI.println("Bravo et merci d'avoir joué");
        this.aGUI.enable(false);
        this.aGUI.resetButtons();
        this.aGUI.setFSSprite(new Sprite("gameover","Images/win.png",0,0,50));
        this.aTimer.cancel();
    }
} // Game
