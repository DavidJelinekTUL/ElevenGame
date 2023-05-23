package data;

import java.util.Arrays;

/**
 * Contains data for cards
 */
public class DataStore {
    private static String[] values
            = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    private static String[] symbols
            = {"hearts", "clubs", "diamonds", "spades"};

    private static int[] nPoints
            = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};
    
    private static int nCards = 9;
    
    private static short nFull=52;
    
    private static String name = "Eleven Game";
    
    private static String[] triple = {"jack", "queen", "king"};
    
    public static String[] loadSymbols() {
        return Arrays.copyOf(symbols, symbols.length);
    }
    
    public static String[] loadValues() {
        return Arrays.copyOf(values, values.length);
    }
    
    public static int[] loadNPoints(){
        return Arrays.copyOf(nPoints, nPoints.length);
    }
    
    public static int getNCards(){
        return nCards;
    }
    
    public static String getName(){
        return name;
    }
    
    public static short getNFull(){
        return nFull;
    }
    
    public static String[] getTriple(){
        return Arrays.copyOf(triple, triple.length);
    }
}
