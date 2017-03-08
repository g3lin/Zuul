 

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
             if (this.aNorthExit != null){
                      vExits += "Nord ";
                    }
             if (this.aSouthExit != null){
               vExits += "Sud ";
             }
             if (this.aEastExit != null){
               vExits += "Est ";
             }
             if (this.aWestExit != null){
                vExits += "Ouest ";
               }
             System.out.println(vExits)  ;
        }
} // Room
