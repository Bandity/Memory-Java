/**
 * @autor Bandity © 2020
 * @version 1.0.0
 * Please leave the credits
 */

package Memory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class ControlCards implements ActionListener {
    private Model model;
    private Window window;

    public ControlCards(){}

    public ControlCards(Model model, Window window){
        this.model = model;
        this.window = window;
        window.setCardsController(this);
    }

    /**
     * All the possible actions for the cards in this game
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        int cardIndex =0;
        boolean cardState;
        Card card;

        if (model.getCounter() == 0){
            model.setCounter(model.getCounter()+1);
            this.window.getTimer().start();
        }

        for (int i = 0 ; i< window.getCardList().size(); i++){
            card = window.getCardList().get(i);
            cardState = card.isFaceUp();
            if(event.getSource() == card && !cardState){
                cardIndex = i;
                model.setCardImage(card, cardIndex);
            }
        }

        model.setNumberOfCardsRetuned(model.getNumberOfCardsRetuned()+1);

        if(model.getCardComperor().size() == 2 && model.getWinCounter()==7){
            model.setNumberOfCardsRetuned(3);
        }

        if (model.getNumberOfCardsRetuned() == 3) {
            if (model.isEqualIndexCard()) {
                model.setScore(model.getScore() + 100);
                model.setWinCounter(model.getWinCounter()+1);
                if(model.getWinCounter() < 8){
                    model.setCardImage(window.getCardList().get(model.getDisableCardList().get(2)), model.getDisableCardList().get(2));
                }
            }
            else {

                model.setLifes(model.getLifes() - 1);
                window.getLifesLabel().setText("Lives : "+model.getLifes());
                if(model.getScore() >0) {
                    model.setScore(model.getScore() - 20);
                }
                model.setCardImage(window.getCardList().get(model.getDisableCardList().get(0)), model.getDisableCardList().get(0));
                model.setCardImage(window.getCardList().get(model.getDisableCardList().get(1)), model.getDisableCardList().get(1));
                model.setCardImage(window.getCardList().get(model.getDisableCardList().get(2)), model.getDisableCardList().get(2));
            }
            window.getScore().setText("Last High Score : "+window.getListScore().get(0)+" Score : "+model.getScore());
            model.getDisableCardList().clear();
            model.setNumberOfCardsRetuned(0);
            model.getCardComperor().clear();
        }

        if(model.getWinCounter() == 8){
            model.writeBestScoreToTXT();
            window.getTimer().terminate();
            window.doLoseWin(true);
        }

        if (model.getLifes() ==0){
            model.writeBestScoreToTXT();
            window.getTimer().terminate();
            window.doLoseWin(false);
        }
    }
}
