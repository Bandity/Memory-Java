/**
 * @autor Bandity © 2020
 * @version 1.0.0
 * Please leave the credits
 */

package Memory;

public class Launcher {
    
    /**
     * Game starter
     */
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Model model = new Model();
                ControlGroup controlGroup = new ControlGroup(model);
            }
        });
    }
}
