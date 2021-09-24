package Memory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlBarMenu implements ActionListener {
    private Window window;
    private Model model;

    public ControlBarMenu(){}

    public ControlBarMenu(Model model, Window window){
        this.model = model;
        this.window = window;
        this.window.setMenuBarController(this);
    }

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
