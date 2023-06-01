import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.text.Text;

public class RefreshThread extends Thread {
    private long startTime;
    private long endTime;
    private MySquare sq;
    private boolean runner = true;
    private Object mutex;
    
    public RefreshThread(MySquare sq, Object mutex){
        this.sq = sq;
        this.mutex = mutex;
    }

    @Override
    public void run(){
        // while (runner){
                
        //         Platform.runLater(() -> sq.loadSquare()); 
        //     try {
        //         Thread.sleep(133);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }
    }
    public void stopRun(){
        runner = false;
    }
}
