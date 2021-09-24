package Memory;
import javax.swing.*;
import java.text.DecimalFormat;

public class Chrono extends Thread {
    private JLabel temps;
    private boolean demarre;

    public Chrono(JLabel temps ){
        this.temps=temps;
    }

    public void run(){
        demarre=true;
        float t=0;

        while (demarre) {
            try{
                this.sleep(100);
                t+=0.1;
                DecimalFormat df = new DecimalFormat("#######0.0");
                String str = df.format(t);
                temps.setText("Time : "+str);
            }
            catch(InterruptedException e){
            }
        }
    }

    public void terminate(){
        demarre=false;
    }

}
