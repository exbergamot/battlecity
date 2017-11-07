package dkgames.battlecity.entity;

import dkgames.battlecity.interaction.CollisionChecker;

public class GameBoard {
    private static int MAX_PLAYTERS = 4;
    public static int FIELD_SIZE = 13 + 2;
    public static int OBSTACLE = 1;

    private int[][] field;
    private Tank[] tanks = new Tank[MAX_PLAYTERS];
    private Bullet[] bullets = new Bullet[MAX_PLAYTERS];
    private Player[] players = new Player[MAX_PLAYTERS];

    private CollisionChecker collisionChecker;

    public GameBoard(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }


    public Tank[] getTanks() {
        return tanks;
    }

    public Bullet[] getBullets() {
        return bullets;
    }

    public int[][] getField() {
        return field;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public void setTanks(Tank[] tanks) {
        this.tanks = tanks;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
