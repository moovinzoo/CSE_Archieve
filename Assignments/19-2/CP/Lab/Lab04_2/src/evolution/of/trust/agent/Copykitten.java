package evolution.of.trust.agent;
import evolution.of.trust.match.Match;
import kiroong.Individual;

public class Copykitten extends Agent {
    public Copykitten() {
        super("Copykitten");
    }

    @Override
    public Individual clone() {
        return new Copykitten();
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
