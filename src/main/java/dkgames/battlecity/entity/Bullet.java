package dkgames.battlecity.entity;

public class Bullet extends Moveable {
    private GameBoard gameBoard;

    public Bullet(Vector position, Vector speed, GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.setSpeed(speed);
        this.setPosition(position);
    }

    public void move() {
        this.getPosition().addVector(this.getSpeed());
    }
}
