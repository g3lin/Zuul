

import java.util.Timer;
import java.util.TimerTask;

public class TimerElement extends TimerTask
{
    private int aTime;
    private GameEngine aGE;
    private Timer aTimer;
    
    public TimerElement(){
        this.aTime = 150;
        
    }
    
    public void pass(final GameEngine pE , final Timer pT){
        this.aGE = pE;
        this.aTimer =pT;
        
    }

    @Override
    public void run() {
        this.aTime -= 1;
        
        this.aGE.updateTime(this.aTime);
        
        
        if (this.aTime == 0){
            this.aTimer.cancel();
            this.aGE.gameOver();
        }
    }
}
