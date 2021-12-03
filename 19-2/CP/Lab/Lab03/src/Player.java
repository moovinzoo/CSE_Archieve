public class Player {
    String userId;

    int health = 50;
    char[] tactics;

    Player(String userId) {
        this.userId = userId;
        generateRandomTactics();
    }

    public void attack(Player opponent) {
        opponent.health -= (int)(Math.random() * 5) + 1;
        if (opponent.health < 0) {
            opponent.health = 0;
        }
    }

    public void heal() {
        health += (int)(Math.random() * 3) + 1;
        if (health > 50) {
            health = 50;
        }
    }

    public char getTactics(int round) {
        return tactics[round];
    }

    public boolean alive() {
        return health > 0;
    }

    public void generateRandomTactics() {
        tactics = new char[200];
        for (int i = 0; i < 200; i++) {
            double r = Math.random();
            if (r > 0.3) {
                tactics[i] = 'a';
            } else {
                tactics[i] = 'h';
            }
        }
    }
}
