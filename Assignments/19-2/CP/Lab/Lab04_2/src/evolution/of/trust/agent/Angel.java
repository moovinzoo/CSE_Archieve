package evolution.of.trust.agent;
import evolution.of.trust.match.Match;
import kiroong.Individual;

public class Angel extends Agent{
    public Angel() {
        super("Angle");
    }

    @Override
    public Individual clone() {
        return new Angel();
    }

    @Override
    public int choice(int previousOpponetChoice) {
        return Match.COOPERATE;
    }
}
