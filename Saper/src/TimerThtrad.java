import javafx.application.Platform;
import javafx.scene.text.Text;

public class TimerThtrad extends Thread {
    private long startTime;
    private long endTime;
    private Text timer;
    
    public TimerThtrad(Text timer){
        startTime = System.currentTimeMillis();
        this.timer = timer;
    }

    @Override
    public void run(){
        while (true){
            double time = Math.round( (System.currentTimeMillis() - startTime)/10.0)  / 100.0;
            Platform.runLater(() -> timer.setText("Time: " + Double.toString(time)));
            
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
