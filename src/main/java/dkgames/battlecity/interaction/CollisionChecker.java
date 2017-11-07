package dkgames.battlecity.interaction;

import dkgames.battlecity.entity.*;

public class CollisionChecker {
    public boolean isObstacle(Vector point, GameBoard gameBoard) {
        return gameBoard.getField()[point.getY()][point.getX()] == GameBoard.OBSTACLE;
    }

    public int isTank(Vector point, GameBoard gameBoard) {
        Tank[] tanks = gameBoard.getTanks();
        for(int i = 0; i < tanks.length; i++) {
            if (tanks[i] != null &&
                    tanks[i].getPosition().getY() == point.getY() &&
                    tanks[i].getPosition().getX() == point.getX()) {
                return i;
            }
        }
        return -1;
    }

    public int isBullet(Vector point, GameBoard gameBoard) {
        Bullet[] bullets = gameBoard.getBullets();
        for(int i = 0; i < bullets.length; i++) {
            if (bullets[i] != null &&
                    bullets[i].getPosition().getY() == point.getY() &&
                    bullets[i].getPosition().getX() == point.getX()) {
                return i;
            }
        }
        return -1;
    }

    public void checkBulletsCollisions(GameBoard gameBoard) {
        Bullet[] bullets = gameBoard.getBullets();
        Tank[] tanks = gameBoard.getTanks();
        for (int i = 0; i < bullets.length - 1; i++) {
            for (int j = i + 1; j < bullets.length; j++) {
                if (i != j && bullets[i] != null && bullets[j] != null) {
                    if (bullets[i].getPosition().equals(bullets[j].getPosition())) {
                        tanks[i].destroyBullet();
                        tanks[j].destroyBullet();
                    }
                }
            }
        }

        for (int i = 0; i < bullets.length; i++) {
            int tankIndex = -1;
            if (bullets[i] != null) {
                if (isObstacle(bullets[i].getPosition(), gameBoard)) {
                    tanks[i].destroyBullet();
                } else if ((tankIndex = isTank(bullets[i].getPosition(), gameBoard)) != -1) {
                    tanks[i].destroyBullet();
                    tanks[tankIndex].destroyTank(tankIndex);
                    gameBoard.getPlayers()[i].modifyScore(Game.SCORE_FOR_FRAG);
                    gameBoard.getPlayers()[tankIndex].modifyScore(Game.SCORE_FOR_DEATH);
                }
            }
        }

    }
}
