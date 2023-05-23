/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package app;

import data.DataStore;
import java.util.ArrayList;
import java.util.List;
import utils.GameInterface;

/**
 *
 * @author davidjelinek
 */
public class Game implements GameInterface {

    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Card> table = new ArrayList<>();
    private Card[] stack = new Card[DataStore.getNCards()];

    public Game() {
        FillDeck();
        FillHand();
    }

    private void FillDeck() {
        Card card;
        for (int i = 0; i < DataStore.loadSymbols().length; i++) {
            for (int j = 0; j < DataStore.loadValues().length; j++) {
                card = new Card(DataStore.loadValues()[j], DataStore.loadSymbols()[i], DataStore.loadNPoints()[j]);
                deck.add(card);
            }
        }

        /*card = new Card(DataStore.loadValues()[9], DataStore.loadSymbols()[1], DataStore.loadNPoints()[9]);
        deck.add(card);
        card = new Card(DataStore.loadValues()[0], DataStore.loadSymbols()[1], DataStore.loadNPoints()[9]);
        deck.add(card);*/
        //Testování výhry
        //for (int i = 0; i < 9; i++) {
        //card = new Card(DataStore.loadValues()[i], DataStore.loadSymbols()[1], DataStore.loadNPoints()[i]);
        //deck.add(card);
        //}
    }

    private void FillHand() {

        //hand.add(deck.get(1));
        //hand.add(deck.get(2));
        //Testování výhry
        for (int i = 0; i < 9; i++) {
            int rand;
            rand = (int) ((Math.random() * (9 - 0)) + 0);
            while (!deck.get(rand).isStatus()) {
                rand = (int) ((Math.random() * (9 - 0)) + 0);
            }
            hand.add(deck.get(rand));
            deck.get(rand).setStatus(false);
        }
    }

    @Override
    public String getName() {
        return DataStore.getName();
    }

    @Override
    public int nCards() {
        return DataStore.getNCards();
    }

    @Override
    public int getDeckSize() {
        int validCount = 0;
        for (int i = 0; i < deck.size() - 1; i++) {
            if (deck.get(i).isStatus()) {
                validCount++;
            }
        }
        return validCount;
    }

    @Override
    public String getCardDescriptionAt(int index) {
        if (index < getDeckSize()) {
            return hand.get(index).toString();
        }
        return null;
    }

    @Override
    public boolean anotherPlayIsPossible() {
        int sumPoints = 0; //Sum of numbers
        boolean sumJ = false; //Sum of J
        boolean sumQ = false; //Sum of Q
        boolean sumK = false; //Sum of K

        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < (hand.size()) - i; j++) {
                sumPoints = (hand.get(i).getPoints() + hand.get(j).getPoints());
                if (sumPoints == 11) {
                    return true;
                }
                sumPoints = 0;
            }
            if ("jack".equals(hand.get(i).getValue())) {
                sumJ = true;
            }
            if ("queen".equals(hand.get(i).getValue())) {
                sumQ = true;
            }
            if ("king".equals(hand.get(i).getValue())) {
                sumK = true;
            }
            if (sumJ && sumQ && sumK) {
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean playAndReplace(List<Integer> iSelectedCards) {
        int pointSum = 0;
        if (iSelectedCards.isEmpty()) {
            return false;
        }

        boolean sumJ = false; //Sum of J
        boolean sumQ = false; //Sum of Q
        boolean sumK = false; //Sum of K

        for (int i = 0; i < iSelectedCards.size(); i++) {
            pointSum += hand.get(iSelectedCards.get(i)).getPoints();
            if ("jack".equals(hand.get(iSelectedCards.get(i)).getValue())) {
                sumJ = true;
            }
            if ("queen".equals(hand.get(iSelectedCards.get(i)).getValue())) {
                sumQ = true;
            }
            if ("king".equals(hand.get(iSelectedCards.get(i)).getValue())) {
                sumK = true;
            }
            if (sumJ && sumQ && sumK) {
                takeNew(iSelectedCards, 3);
                return true;
            }
        }
        if (pointSum == 11) {
            takeNew(iSelectedCards, 2);
            return true;
        }
        return false;
    }

    public int numTrueDeck() {
        int trueAmount = 0;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).isStatus()) {
                trueAmount++;
            }
        }
        return trueAmount;
    }

    public int numTrueHand() {
        int trueAmount = 0;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).isStatus()) {
                trueAmount++;
            }
        }
        return trueAmount;
    }

    public void takeNew(List<Integer> iSelectedCards, int amount) {
        if (numTrueDeck() >= amount) {
            for (int i = 0; i < amount; i++) {
                int rand;
                rand = (int) ((Math.random() * (52 - 0)) + 0);
                while (!deck.get(rand).isStatus()) {
                    rand = (int) ((Math.random() * (52 - 0)) + 0);
                }
                hand.set((int) iSelectedCards.get(i), deck.get(rand));
                deck.get(rand).setStatus(false);
            }
        } else {
            for (int i = 0; i < amount; i++) {
                hand.remove((int) iSelectedCards.get(i));
            }
        }
    }

    @Override
    public boolean isWon() {
        boolean isTrueDeck = false;
        boolean isTrueHand = false;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).isStatus()) {
                isTrueDeck = true;
                break;
            }
        }
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).isStatus()) {
                isTrueHand = true;
                break;
            }
        }
        if (isTrueDeck && isTrueHand) {
            return true;
        }
        return false;
    }

}
