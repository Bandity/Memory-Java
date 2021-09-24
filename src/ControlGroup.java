package Memory;

public class ControlGroup {
    private Model model;
    private Window window;
    private ControlCards controlCards;
    private ControlMenuBar controlMenuBar;
    private ControlBarMenu controlBarMenu;

    public ControlGroup(){}
    public ControlGroup(Model model){
        this.model = model;
        this.window = new Window(model);
        this.controlCards = new ControlCards(model, window);
        this.controlMenuBar = new ControlMenuBar(model, window);
        this.controlBarMenu = new ControlBarMenu(model, window);
        window.display();
    }
}
