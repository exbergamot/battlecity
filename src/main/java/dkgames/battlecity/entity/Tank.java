package dkgames.battlecity.entity;

import dkgames.battlecity.interaction.CollisionChecker;

public class Tank extends Moveable {
    private Player player;
    private GameBoard gameBoard;
    private Bullet bullet;

    public Tank(GameBoard gameBoard, Player player) {
        this.gameBoard = gameBoard;
        this.player = player;
        this.setSpeed(getInitialDirection());
        this.setPosition(getInitialPosition());
    }

    private Vector getInitialDirection() {
        int number = player.getNumber();
        int x = -1 + (number / 2) * 2;
        int y = -1 + (number % 2) * 2;
        return new Vector(x, y);
    }

    private Vector getInitialPosition() {
        int number = player.getNumber();
        int x = 6 + (number / 2) * (GameBoard.FIELD_SIZE - 10);
        int y = 6 + (number % 2) * (GameBoard.FIELD_SIZE - 10);
        return new Vector(x, y);
    }

    public void move() {
        Vector newPosition = new Vector(this.getPosition());
        newPosition.addVector(this.getSpeed());
        CollisionChecker collisionChecker = gameBoard.getCollisionChecker();
        if (!collisionChecker.isObstacle(newPosition, gameBoard) && collisionChecker.isTank(newPosition, gameBoard) == -1) {
            int bulletIndex = -1;
            if ((bulletIndex = collisionChecker.isBullet(newPosition, gameBoard)) != -1) {
                gameBoard.getTanks()[bulletIndex].destroyBullet();
                this.destroyTank(player.getNumber());
            }
            this.setPosition(newPosition);
        }
    }

    public void shot() {
        if (bullet == null) {
            Vector bulletPosition = new Vector(this.getPosition());
            Vector bulletSpeed = new Vector(this.getSpeed());
            bullet = new Bullet(bulletPosition, bulletSpeed, gameBoard);
            gameBoard.getBullets()[player.getNumber()] = bullet;
        }
    }

    public void destroyBullet() {
        gameBoard.getBullets()[player.getNumber()] = null;
        this.bullet = null;
    }

    public void destroyTank(int index) {
        gameBoard.getBullets()[index] = null;
        gameBoard.getTanks()[index] = null;
    }
}
