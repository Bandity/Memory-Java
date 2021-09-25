/**
 * @autor Bandity © 2020
 * @version 1.0.0
 * Please leave the credits
 */

package Memory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlBarMenu implements ActionListener {
    private Window window;
    private Model model;

    public ControlBarMenu(){}
    /**
     * Init the in-game menu to be controlled
     * @param model Game engine model
     * @param window Game window
     */
    public ControlBarMenu(Model model, Window window){
        this.model = model;
        this.window = window;
        this.window.setMenuBarController(this);
    }

    /**
     * Allows everytime that a button on the menu is choose 
     * do the different actions
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == window.getButtonNewGameMenu()){
            window.addWidget();
            window.display();
        }
        else if (event.getSource() == window.getQuitStartMenu()){
            window.dispose();
        }
        else if (event.getSource() == window.getBestScores()){
            window.scoreDisplay();
        }
        else if (event.getSource() == window.getResetScores()){
            model.resetScores();
            window.setListScore(model.readFile());
            window.getScore().setText("Last High Score : "+window.getListScore().get(0)+" Score : "+model.getScore());
        }
    }
}
