/**
 * @autor Bandity © 2020
 * @version 1.0.0
 * Please leave the credits
 */

package Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Card extends JButton {
    private ImageIcon backOfCard;
    private boolean faceUp;
    private ImageIcon frontCard;
    
    /**
     * Card constructor
     */
    public Card(){
        super();
        cardInit();
    }

    /**
     * Allow the card to return or show the image if is face down
     * @param image path of the image
     */
    public  void  returnCard(String image){
        if(isFaceUp()){
            setEnabled(true);
            setFaceUp(false);
            frontCard = new ImageIcon("./src/images/backcard.png");
            frontCard = new ImageIcon(frontCard.getImage().getScaledInstance(270, 230, BufferedImage.SCALE_DEFAULT));
        }
        else {
            setFaceUp(true);
            frontCard = new ImageIcon(image);
            frontCard = new ImageIcon(frontCard.getImage().getScaledInstance(270, 230, BufferedImage.SCALE_DEFAULT));
            setEnabled(false);
            setDisabledIcon(frontCard);
        }
    }

    /**
     * Init the card
     */
    public void cardInit(){
        this.setPreferredSize(new Dimension(270,230));
        this.faceUp = false;
        this.backOfCard = new ImageIcon("./src/images/backcard.png");
        this.backOfCard = new ImageIcon(backOfCard.getImage().getScaledInstance(270, 230, BufferedImage.SCALE_DEFAULT));
        this.frontCard = new ImageIcon("./src/images/1.jpg");
        this.frontCard = new ImageIcon(backOfCard.getImage().getScaledInstance(270, 230, BufferedImage.SCALE_DEFAULT));
        this.setIcon(backOfCard);
        this.setEnabled(true);
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }
}
