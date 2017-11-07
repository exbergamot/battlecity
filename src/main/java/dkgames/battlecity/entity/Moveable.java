package dkgames.battlecity.entity;

public abstract class Moveable {
    private Vector position;
    private Vector speed;

    public abstract void move();

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getSpeed() {
        return speed;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }
}
