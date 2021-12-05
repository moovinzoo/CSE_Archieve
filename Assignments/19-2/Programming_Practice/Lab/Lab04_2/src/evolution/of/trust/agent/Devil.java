package evolution.of.trust.agent;
import evolution.of.trust.match.Match;
import kiroong.Individual;

public class Devil extends Agent{
    public Devil() {
        super("Devil");
    }

    @Override
    public Individual clone() {
        return new Devil();
    }

    @Override
    public int choice(int previousOpponetChoice) {
        return Match.CHEAT;
    }
}
