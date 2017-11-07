package dkgames.battlecity.entity;

public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector (Vector origin) {
        this.x = origin.x;
        this.y = origin.y;
    }

    public void addVector(Vector add) {
        this.x += add.x;
        this.y += add.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
