/**
 * @autor Bandity © 2020
 * @version 1.0.0
 * Please leave the credits
 */


package Memory;

public class ControlGroup {
    private Model model;
    private Window window;
    private ControlCards controlCards;
    private ControlMenuBar controlMenuBar;
    private ControlBarMenu controlBarMenu;

    public ControlGroup(){}

    /**
     * Make's possible for the Launcher to start the game and init all the necessary
     * Stuff to make the game work
     * @param model Game Engine Model
     */
    public ControlGroup(Model model){
        this.model = model;
        this.window = new Window(model);
        this.controlCards = new ControlCards(model, window);
        this.controlMenuBar = new ControlMenuBar(model, window);
        this.controlBarMenu = new ControlBarMenu(model, window);
        window.display();
    }
}
