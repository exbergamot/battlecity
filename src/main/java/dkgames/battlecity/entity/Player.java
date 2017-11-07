package dkgames.battlecity.entity;

import dkgames.battlecity.ai.TankAI;

public class Player {
    private int number;
    private int score;
    private int ticksFromDeath;
    private TankAI tankAI;

    public Player(int number, TankAI tankAI) {
        this.tankAI = tankAI;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void modifyScore(int add) {
        this.score += add;
    }

    public int getScore() {
        return score;
    }

    public int incrementAndGetTicksFromDeath() {
        this.ticksFromDeath += 1;
        return this.ticksFromDeath;
    }

    public void clearTickFromDeath() {
        this.ticksFromDeath = 0;
    }

    public TankAI getTankAI() {
        return tankAI;
    }
}
