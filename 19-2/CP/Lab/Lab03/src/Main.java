public class Main {
    public static void main(String[] args) {
        Player me = new Player("Do Il");
        Player you = new Player("Hyunseok");

        Fight fight = new Fight(me, you);

        while (!fight.isFinished()) {
            fight.proceed();
            fight.printPlayerHealth();;
        }

        String winnerName = Fight.getWinner
    }
}