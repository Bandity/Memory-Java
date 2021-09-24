package Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Window extends JFrame {
    private Model model;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem newStart;
    private JMenuItem quit;
    private JMenuItem bestScoresInMenu;
    private JMenuItem resetScores;
    private List<Card> cardList;
    private JLabel score;
    private JPanel generalWindow;
    private JPanel cardContainer;
    private JLabel timerLabel;
    private JLabel lifesLabel;
    private Chrono timer;
    private JButton bestScores;
    private JButton buttonNewGameMenu;
    private JButton quitStartMenu;
    private JOptionPane windowOptions;
    private Object[] objects;
    private List<Integer> listScore;

    public Window(){

    }

    public Window(Model model){
        this.model = model;
        this.model.readFile();
        initWindow();
        this.setTitle("Memory");
        Image icon = Toolkit.getDefaultToolkit().getImage("./src/images/icon.png");
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void display(){
        this.pack();
        this.setVisible(true);
    }
    public void initWindow(){
        createMenu();
        this.listScore = model.readFile();
        this.cardList = new ArrayList<Card>();
        this.lifesLabel = new JLabel("Lives : "+model.getLifes());
        this.generalWindow = new JPanel();
        this.cardContainer =new JPanel();
        this.objects = new  Object[]{"Quit","New Game"};
        this.score = new JLabel("Last High Score : "+listScore.get(0)+" Score : "+model.getScore());
        this.timerLabel = new JLabel("Time : "+0);
        this.timer = new Chrono(timerLabel);

        this.bestScores = new JButton("Show The Best Scores Ever");
        this.buttonNewGameMenu = new JButton("New Game");
        this.quitStartMenu = new JButton("Quit Just Looking Around");

        this.bestScores.setPreferredSize(new Dimension(300,50));
        this.bestScores.setBackground(Color.WHITE);
        this.bestScores.setForeground(Color.CYAN);
        this.buttonNewGameMenu.setForeground(Color.RED);
        this.buttonNewGameMenu.setPreferredSize(new Dimension(300,50));
        this.buttonNewGameMenu.setBackground(Color.WHITE);
        this.buttonNewGameMenu.setForeground(Color.RED);
        this.quitStartMenu.setPreferredSize(new Dimension(300, 50));
        this.quitStartMenu.setBackground(Color.WHITE);

        for (int i = 0; i<16 ; i++){
            cardList.add(new Card());
        }
        addStarMenuWidget();
    }

    public void createMenu() {
        this.menuBar = new JMenuBar();
        this.menu = new JMenu("Options");

        this.quit = new JMenuItem("Quit the game");
        this.newStart = new JMenuItem("New Game");
        this.resetScores = new JMenuItem("Reset Scores");
        this.bestScoresInMenu = new JMenuItem("Show The Best Scores");

        this.menu.add(newStart);
        this.menu.add(bestScoresInMenu);
        this.menu.add(resetScores);
        this.menu.add(quit);

        this.menuBar.add(menu);

        setJMenuBar(menuBar);
    }

    public void addStarMenuWidget(){
        JPanel button = new JPanel();
        JPanel button2= new JPanel();
        JPanel button3= new JPanel();

        this.generalWindow.setLayout(new BoxLayout(generalWindow,BoxLayout.Y_AXIS));

        button.add(buttonNewGameMenu);
        button2.add(bestScores);
        button3.add(quitStartMenu);

        this.generalWindow.add(button,BorderLayout.CENTER);
        this.generalWindow.add(button2, BorderLayout.CENTER);
        this.generalWindow.add(button3, BorderLayout.CENTER);
        this.setContentPane(generalWindow);
    }

    public void doLoseWin(boolean win){
        String msg;
        String title;

        if (win){
            msg="YOU WIN";
            title = "NICE!!!";
        }
        else {
            msg="YOU LOSE";
            title = "OH NO!!!";
        }

        int n = JOptionPane.showOptionDialog(windowOptions,
                msg,
                title,
                JOptionPane.OK_OPTION,
                JOptionPane.NO_OPTION,
                null,
                this.objects ,
                this.objects[1]);

        if(n == 0){
            this.dispose();
        }

        else if ( n==1 ){
            reset();
        }
    }
    public void scoreDisplay(){
        ImageIcon icon = new ImageIcon("./src/images/iconScore.png");
        icon = new ImageIcon(icon.getImage().getScaledInstance(50, 50, BufferedImage.SCALE_DEFAULT));
        JOptionPane.showMessageDialog(windowOptions,
                "Best scores :\n" + listScore.get(0) +"\n"
                +listScore.get(1)+"\n"+listScore.get(2),
                "BEST SCORES!!!",
                JOptionPane.INFORMATION_MESSAGE,
                icon);
    }

    public void addWidget(){
        this.getContentPane().removeAll();
        JPanel scoreContainer = new JPanel();
        JPanel timerContainer = new JPanel();
        JPanel livesContainer = new JPanel();

        this.timer = new Chrono(timerLabel);
        this.cardContainer.setLayout(new GridLayout(4,4));

        for (int i = 0; i< cardList.size() ; i++){
            cardContainer.add(cardList.get(i));
        }
        this.generalWindow.setLayout(new BoxLayout(generalWindow,BoxLayout.Y_AXIS));

        livesContainer.add(lifesLabel);
        scoreContainer.add(score);
        timerContainer.add(timerLabel);

        this.generalWindow.add(timerContainer);
        this.generalWindow.add(scoreContainer);
        this.generalWindow.add(cardContainer);
        this.generalWindow.add(livesContainer);

        this.setContentPane(generalWindow);
    }

    public void reset(){
        this.getContentPane().removeAll();
        model.setCardRandomiser(model.randomiseImages());
        System.out.println(Arrays.toString(model.getCardRandomiser()));
        model.setScore(0);
        model.setLifes(3);
        model.setCounter(0);
        model.setWinCounter(0);
        model.setNumberOfCardsRetuned(0);
        model.getDisableCardList().clear();
        model.getCardComperor().clear();
        this.listScore = model.readFile();
        this.lifesLabel.setText("Lives : "+model.getLifes());
        this.score.setText("Last High Score : "+getListScore().get(0)+" Score : "+model.getScore());
        this.timerLabel.setText("Time : "+0);

        // TO Stop the timer Bug of ... instead of the time
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.timer.terminate();
        addWidget();
        model.returnAllCards(cardList);
        display();
    }

    public void setMenuBarControl(ActionListener listener){
        this.quit.addActionListener(listener);
        this.newStart.addActionListener(listener);
        this.bestScoresInMenu.addActionListener(listener);
    }

    public  void setCardsController(ActionListener listener){
        for (int i = 0; i< cardList.size(); i++){
            this.cardList.get(i).addActionListener(listener);
        }
    }

    public void setMenuBarController(ActionListener listener){
        this.buttonNewGameMenu.addActionListener(listener);
        this.quitStartMenu.addActionListener(listener);
        this.resetScores.addActionListener(listener);
        this.bestScores.addActionListener(listener);
    }

    public JMenuItem getNewStart() {
        return newStart;
    }

    public JMenuItem getQuit() {
        return quit;
    }

    public JMenuItem getBestScoresInMenu() {
        return bestScoresInMenu;
    }

    public JMenuItem getResetScores() {
        return resetScores;
    }

    public JLabel getLifesLabel() {
        return lifesLabel;
    }

    public JLabel getScore() {
        return score;
    }

    public JButton getButtonNewGameMenu() {
        return buttonNewGameMenu;
    }

    public JButton getQuitStartMenu() {
        return quitStartMenu;
    }

    public JButton getBestScores() {
        return bestScores;
    }

    public Chrono getTimer() {
        return timer;
    }

    public List<Integer> getListScore() {
        return listScore;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setListScore(List<Integer> listScore) {
        this.listScore = listScore;
    }
}
