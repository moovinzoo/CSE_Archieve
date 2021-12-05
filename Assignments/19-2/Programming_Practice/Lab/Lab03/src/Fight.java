public class Fight {
    int timeLimit = 100;
    int currRound = 0;

    Player p1;
    Player p2;

    Fight(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void proceed() {
        System.out.println("Round " + currRound);
        attackHeal();
        currRound++;
    }

    public void attackHeal() {
        char p1Tactic = p1.getTactics(currRound);
        char p2Tactic = p2.getTactics(currRound);

        if (p1Tactic == 'a') {
            System.out.println(p1.userId + " attacks" + p2.userId);
            p1.attack(p2);
        } else {
            System.out.println(p1.userId + " healed");
            p1.heal();
        }
    }

    public int getCurrRound(){
        return currRound;
    }

    public boolean isFinished() {
        boolean limitReached = currRound >= timeLimit;
        boolean p1Alive = p1.alive();
        boolean p2Alive = p2.alive();

        return limitReached || !p1Alive || !p2Alive;
    }

    public  Player getWinner() {
        if (p1.health > p2.health) {
            return p1;
        } else {
            return p2;
        }
    }

    public void printPlayerHealth() {
        System.out.println(p1.userId + " health: " + p1.health);
        System.out.println(p2.userId + " health: " + p2.health);
    }
}