package Memory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenuBar implements ActionListener {
    private Model model;
    private Window window;
    public ControlMenuBar(){

    }
    public ControlMenuBar(Model model,Window window){
        this.model= model;
        this.window = window;
        window.setMenuBarControl(this);
    }
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
