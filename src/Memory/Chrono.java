/**
 * @autor Bandity © 2020
 * @version 1.0.0
 * Please leave the credits
 */

package Memory;

import javax.swing.*;
import java.text.DecimalFormat;

public class Chrono extends Thread {
    private JLabel temps;
    private boolean demarre;

    /**
     * Timer creator
     * @param temps That is respossible to show the time
    */
    public Chrono(JLabel temps ){
        this.temps=temps;
    }

    /**
     * Timer start
     */
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

    /**
     * Timer killer
     */
    public void terminate(){
        demarre=false;
    }

}
