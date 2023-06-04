import java.io.FileWriter;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.text.Text;

public class ScoreThread extends Thread{
	private boolean isActive;
	private volatile boolean threadSuspended = false;
    private Text score;
    private int scoreVal;
    private TimerThread timer;
	

	ScoreThread(Text score, TimerThread timer)
    {
        this.score = score;
        this.timer = timer;
    }

	public boolean isActive(){
		return isActive;
	}

    public void stopRun()
    {
        threadSuspended = false;
    }
    
	synchronized public void Load(int score){
        scoreVal = score;
	}

	@Override
	public void run()
	{
            while(true){
				try 
				{	
					if (threadSuspended)
					{
						synchronized(this) 
						{
							while (threadSuspended)
								wait();						
						}
					}
				} 
				catch (InterruptedException e){}

				try
				{
                    Platform.runLater(() -> score.setText("Score: " + (int) (scoreVal - (timer.getTime()/10) * 100)));
					Thread.sleep(500);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
	}
}
