package com.example;
import javafx.application.Platform;
import javafx.scene.text.Text;

public class TimerThread extends Thread 
    {
    private long startTime;
    private Text timer;
    private boolean runner = true;
    private double time = 0;
    
    public TimerThread(Text timer)
    {
        startTime = System.currentTimeMillis();
        this.timer = timer;
    }

    @Override
    public void run()
    {
        while (runner)
        {
            time = Math.round( (System.currentTimeMillis() - startTime)/10.0)  / 100.0;
            Platform.runLater(() -> timer.setText("Time: " + Double.toString(time)));
            
            
            try 
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    public void stopRun()
    {
        runner = false;
    }
    public double getTime(){
        return time;
    }
}
