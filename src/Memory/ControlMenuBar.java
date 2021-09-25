/**
 * @autor Bandity © 2020
 * @version 1.0.0
 * Please leave the credits
 */

package Memory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenuBar implements ActionListener {
    private Model model;
    private Window window;

    public ControlMenuBar(){}

    /**
     * Init the menu to be controlled
     * @param model Game engine model
     * @param window Game window
     */
    public ControlMenuBar(Model model,Window window){
        this.model= model;
        this.window = window;
        window.setMenuBarControl(this);
    }

    /**
     * Game start menu
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == window.getNewStart()){
            window.reset();
        }
        else if (actionEvent.getSource() == window.getQuit()){
            window.dispose();
        }
        else if( actionEvent.getSource() == window.getBestScoresInMenu()){
            window.scoreDisplay();
        }
    }
}
