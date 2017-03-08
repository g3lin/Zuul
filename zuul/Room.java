 import java.util.HashMap;

public class Room
{
       private String aDescription = "c'est une pièce" ;
       private HashMap<String,Room> aExits;
       
       public Room(final String pDescription){
           this.aDescription=pDescription;
           aExits = new  HashMap<String,Room>();
        }
        
        public String getDescription() {
            return this.aDescription;
        }
        
        public void setExits(String pDirection, Room pExit){
            aExits.put(pDirection,pExit);
            
        }
        
        public Room getExit(final String pDirection){
               return aExits.get(pDirection);
        }
        
        public void printLocationInfo(){
                System.out.print("Vous êtes dans ");
                System.out.println(this.getDescription());
                System.out.print("Vous pouvez aller vers: ");
            String vExits = "";
             
             System.out.println(this.getExitString())  ;
        }
        
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
