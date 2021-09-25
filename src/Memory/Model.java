/**
 * @autor Bandity © 2020
 * @version 1.0.0
 * Please leave the credits
 */

package Memory;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Model {
    private int score;
    private List<String> cardImageList;
    private int lifes ;
    private  int[] cardRandomiser;
    private int numberOfCardsRetuned;
    private List<Integer> cardComperor ;
    private List<Integer> disableCardList;
    private int counter;
    private int winCounter;
    private List<Integer>best3Scores;
    /** 
     * Respresents the game constructor
     */
    public Model(){
        this.cardImageList = new ArrayList<String>();
        this.cardImageList.add("./src/images/1.jpg");
        this.cardImageList.add("./src/images/2.jpg");
        this.cardImageList.add("./src/images/3.jpg");
        this.cardImageList.add("./src/images/4.jpg");
        this.cardImageList.add("./src/images/5.jpg");
        this.cardImageList.add("./src/images/6.jpg");
        this.cardImageList.add("./src/images/7.jpg");
        this.cardImageList.add("./src/images/8.jpg");
        this.cardRandomiser = randomiseImages();
        this.lifes = 10;
        this.numberOfCardsRetuned = 0;
        this.score = 0;
        System.out.println(Arrays.toString(cardRandomiser));
        this.cardComperor = new ArrayList<Integer>();
        this.disableCardList = new ArrayList<Integer>();
        this.counter = 0;
        this.winCounter = 0;
        this.best3Scores = new ArrayList<Integer>();

    }

    /**
     * This method verifies if the index of the cards are the same by subtracting 
     * them, if they are equal to 0 returns true if not returns false
     * @return true or false
     */
    public boolean isEqualIndexCard(){
        if(cardComperor.get(0)- cardComperor.get(1) == 0) {
                return true;
        }
        return false;
    }

    /**
     * Method responssible for setting the image random image 
     * @param card Objet card
     * @param index Index of the card that you want to set the image
     */
    public void setCardImage(Card card, int index){
        int newIndex= cardRandomiser[index];
        this.cardComperor.add(newIndex);
        ImageIcon newCardImage;
        boolean cardState = card.isFaceUp();
        card.returnCard(cardImageList.get(newIndex));
        if (!cardState) {
            disableCardList.add(index);
        }
    }

    public void returnAllCards(List<Card> cardList){
        ImageIcon backOfCard = new ImageIcon("./src/images/backcard.png");
        backOfCard = new ImageIcon(backOfCard.getImage().getScaledInstance(270, 230, BufferedImage.SCALE_DEFAULT));
        for (int i = 0 ; i< cardList.size(); i++){
            cardList.get(i).setIcon(backOfCard);
            cardList.get(i).setEnabled(true);
            cardList.get(i).setFaceUp(false);
        }
    }
    /**
     * 
     * @return A int array with the randomised other
     */
    public int[] randomiseImages(){
        int [] order = new int[16];
        Vector vector = new Vector();
        for (int i = 0 ; i<order.length ; i++){
            vector.add((int)(i%(order.length/2)));
        }
        for (int i = 0; i < order.length ; i++){
            int random = (int) (Math.random()*vector.size());
            order[i] = (Integer)(vector.elementAt(random));
            vector.removeElementAt(random);
        }
        return order;
    }

    /**
     * Method that allows to read the bestscores.txt and
     * stores the values in a list of Integers
     * @return The list of scores 
     */
    public List<Integer>readFile(){
        List<Integer> data = new ArrayList<Integer>();
        try {
            File myObj = new File("./src/bestscores.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                data.add( Integer.parseInt(myReader.nextLine()));
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        data.sort(Collections.reverseOrder());
        return data;
    }
    /**
     * Method that write the score. If the score is greater than those in the 
     * bestscore.txt file
     */
    public void writeBestScoreToTXT(){
        List<Integer> n = new ArrayList<Integer>(readFile());
        FileWriter myWriter;
        int highScoreEverString = readFile().get(2);

        if (highScoreEverString < getScore()) {
            n.add(getScore());
            n.sort(Collections.reverseOrder());
            try {
                myWriter = new FileWriter("./src/bestscores.txt");
                myWriter.write("" + n.get(0));
                myWriter.write("\n" + n.get(1));
                myWriter.write("\n" + n.get(2));
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
    /** 
    * Resets the scores file 
    */
    public void resetScores(){
        FileWriter myWriter;
        try {
            myWriter = new FileWriter("./src/bestscores.txt");
            myWriter.write("" + 0);
            myWriter.write("\n" + 0);
            myWriter.write("\n" + 0);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /**
     * Allows to set the order for the cards
     * @param cardRandomiser Int array with the cards randomise
     */
    public void setCardRandomiser(int[] cardRandomiser) {
        this.cardRandomiser = cardRandomiser;
    }

    public int[] getCardRandomiser() {
        return cardRandomiser;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLifes() {
        return lifes;
    }

    /**
     * 
     * @param lifes Int of how many lifes you want set
     */
    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    /**
     * 
     * @return The cards a int of how many cards are returned
     */
    public int getNumberOfCardsRetuned() {
        return numberOfCardsRetuned;
    }

    /**
     * Sets how many cards are returned
     * @param numberOfCardsRetuned Int to set how many cards are returned
     */
    public void setNumberOfCardsRetuned(int numberOfCardsRetuned) {
        this.numberOfCardsRetuned = numberOfCardsRetuned;
    }

    public List<Integer> getCardComperor() {
        return cardComperor;
    }

    public List<Integer> getDisableCardList() {
        return disableCardList;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getWinCounter() {
        return winCounter;
    }

    public void setWinCounter(int winCounter) {
        this.winCounter = winCounter;
    }
}
