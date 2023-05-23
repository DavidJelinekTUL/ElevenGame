package app;

public class Card {

    private String value;
    private String symbol;
    private int points;
    private boolean status = true; // True - dostupná | False - nedostupná

    public Card(String value, String symbol,int points) {
        this.value = value;
        this.symbol = symbol;
        this.points = points;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPoints() {
        return points;
    }

    public String getValue() {
        return value;
    }
    
    

    @Override
    public String toString() {
        return "Card{" + "value=" + value + ", symbol=" + symbol + ", points=" + points + ", status=" + status + '}';
    }
}
