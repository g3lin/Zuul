 import java.util.HashMap;

public class Room
{
       private String aDescription ;
       private HashMap<String,Room> aExits;
       /**
        * Constructeur mettant en place la description
        * et la hashmap des sorties en attributs
        * @param pDescription la description de la piece
        */
       public Room(final String pDescription){
           this.aDescription=pDescription;
           aExits = new  HashMap<String,Room>();
        }
        
        /**
         * accesseur de la description
         * @return la description
         */
        public String getDescription() {
            return this.aDescription;
        }
        
        /**
         * definit les sorties
         * @param pDirection la direction
         * @param pExit  la sortie pour la direction
         */
        public void setExits(String pDirection, Room pExit){
            aExits.put(pDirection,pExit);
            
        }
        
        /**
         * accesseur de la direction
         * @param pDirection la direction
         * @return la room qui fait face à cette direction
         */
        public Room getExit(final String pDirection){
               return aExits.get(pDirection);
        }
        
        /**
         * affiche le string de la localisation
         */
        public void printLocationInfo(){
                System.out.print("Vous êtes dans ");
                System.out.println(this.getDescription());
                System.out.print("Vous pouvez aller vers: ");
            String vExits = "";
             
             System.out.println(this.getExitString())  ;
        }
        
        /**
         * affiche le string des sorties disponibles en sortant de cette salle
         */
        public String getExitString(){
            String vExits = "";
            if (this.getExit("north") != null){
                      vExits += "Nord ";
                    }
             if (this.getExit("south") != null){
               vExits += "Sud ";
             }
             if (this.getExit("east") != null){
               vExits += "Est ";
             }
             if (this.getExit("west") != null){
                vExits += "Ouest ";
               }
            
            
            return vExits;
        }
} // Room
