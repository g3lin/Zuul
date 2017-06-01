package pkg_EngineElements;

import java.util.Timer;
import java.util.TimerTask;

public class TimerElement extends TimerTask
{
    private int aTime;
    private GameEngine aGE;
    private Timer aTimer;

    /**
     * constructeur du Timer Element avec le temps max
     */
    public TimerElement(){
        this.aTime = 150;

    }

    /**
     * passe les elements necessaires
     * @param pE le gameEngine
     * @param pT le timer
     */
    public void pass(final GameEngine pE , final Timer pT){
        this.aGE = pE;
        this.aTimer =pT;

    }

    /**
     * methode qui est appelée à chaque fois par le Timer et qui decremente le temps restant
     */
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
