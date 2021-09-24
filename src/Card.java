package Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Card extends JButton {
    private ImageIcon backOfCard;
    private boolean faceUp;
    private ImageIcon frontCard;

    public Card(){
        super();
        cardInit();
    }
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
