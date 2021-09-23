package Memory;

public class Launcher {
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
