import evolution.of.trust.agent.Agent;
import evolution.of.trust.environment.Tournament;
import evolution.of.trust.match.Match;
import evolution.of.trust.agent.Devil;
import evolution.of.trust.agent.Copycat;

public class Main {
    public static void main(String[] args) {
        Tournament tournament = new Tournament();

        for (int i = 0; i < 10; i++) {
            tournament.resetAgents();
            tournament.playAllGames(10);
            tournament.describe();
            tournament.evolvePopulation();
        }
    }
}
