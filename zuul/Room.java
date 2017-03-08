 

public class Room
{
       private String aDescription = "c'est une pièce" ;
       public Room aNorthExit = null;
       public Room aSouthExit = null;
       public Room aEastExit = null;
       public Room aWestExit = null;
       
       public Room(final String pDescription){
           this.aDescription=pDescription;
        }
        
        public String getDescription() {
            return this.aDescription;
        }
        
        public void setExits(final Room pRoomN,final Room pRoomS,final Room pRoomE,final Room pRoomW){
            this.aNorthExit = pRoomN;
            this.aSouthExit = pRoomS;
            this.aEastExit = pRoomE;
            this.aWestExit = pRoomW;
                        
        }
        
        public Room getExit(final String pDirection){
                if (pDirection.equals("north")){
                    return this.aNorthExit;
                }
                
                if (pDirection.equals("south")){
                    return this.aSouthExit;
                }
                
                if (pDirection.equals("east")){
                    return this.aEastExit;
                }
                
                if (pDirection.equals("west")){
                    return this.aWestExit;
                }
                
                return null;
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
