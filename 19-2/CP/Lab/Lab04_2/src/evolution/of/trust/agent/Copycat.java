package evolution.of.trust.agent;
import evolution.of.trust.match.Match;
import kiroong.Individual;

public class Copycat extends Agent {
    public Copycat() {
        super("Copycat");
    }

    @Override
    public Individual clone() {
        return new Copycat();
    }

    @Override
    public int choice(int previousOpponetChoice) {
        if (previousOpponetChoice == Match.UNDEFINED) {
            return Match.COOPERATE;
        } else {
            return previousOpponetChoice;
        }
    }

}
