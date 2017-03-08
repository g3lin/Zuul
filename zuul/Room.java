 

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
        
        public String getExits(){
            String vExits = "";
             if (this.aNorthExit != null){
                      vExits += "North ";
                    }
             if (this.aSouthExit != null){
               vExits += "South ";
             }
             if (this.aEastExit != null){
               vExits += "East ";
             }
             if (this.aWestExit != null){
                vExits += "West ";
               }
             return vExits  ;
        }
} // Room
