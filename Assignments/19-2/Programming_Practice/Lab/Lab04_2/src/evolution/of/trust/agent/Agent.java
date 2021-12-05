package evolution.of.trust.agent;

import kiroong.Individual;

//player
abstract public class Agent extends Individual {
    private int score;
    private String name;

    protected Agent(String name) {
        this.name = name;
    }

    public int sortKey() {
        return getScore();
    }

    @Override
    public String toString() {
        return name + ": " + getScore();
    }

    public int getScore(){
        return score;
    }

    public void setScore(int newScore) {
        score = newScore;
    }

    abstract public int choice(int previousOpponetChoice);
}
