import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card{

    public static final List<String> SUITES = Arrays.asList( "CLUBS", "DIAMONDS", "HEARTS", "SPADES");

    public static final List<String> CARD_VALUES = Arrays.asList("ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING");

    private final String cardSuite;
    private final String cardValue;

    public Card(String cardSuite, String cardValue){
        this.cardSuite = cardSuite;
        this.cardValue= cardValue;
    }

    public String getCardSuite() {
        return cardSuite;
    }

    public String getCardValue() {
        return cardValue;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(this.cardValue.toString() + " OF " + this.cardSuite.toString());
        return s.toString();
    }

}
