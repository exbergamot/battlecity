package dkgames.battlecity.entity;

import dkgames.battlecity.ai.RandomBotAI;
import dkgames.battlecity.ai.TankAI;
import dkgames.battlecity.interaction.CollisionChecker;
import dkgames.battlecity.maps.BasicMaps;

import java.util.Arrays;

public class Game {
    public static final int SCORE_FOR_FRAG = 100;
    public static final int SCORE_FOR_DEATH = -100;

    private static final int BULLET_SPEED = 2;

    private GameBoard gameBoard;

    public Game() {
        this.gameBoard = new GameBoard(new CollisionChecker());
        gameBoard.setField(BasicMaps.round1);

        Player[] players = new Player[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new Player(i, new RandomBotAI());
        }
        gameBoard.setPlayers(players);

        Tank[] tanks = new Tank[4];
        for (int i = 0; i < 4; i++) {
            tanks[i] = new Tank(gameBoard, players[i]);
        }
        gameBoard.setTanks(tanks);
    }


    private void mainLoop() throws InterruptedException {
        makeDecisions();
        moveBullets();
        moveTanks();
        checkRespawn();

        printField();
        Thread.sleep(500);
    }

    private void printField() {
        int[][] field = gameBoard.getField();
        char[][] tempField = new char[field.length][field.length];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == 1) {
                    tempField[i][j] = 'O';
                } else {
                    tempField[i][j] = ' ';
                }
            }
        }

        Bullet[] bullets = gameBoard.getBullets();
        for (int i = 0; i < 4; i++) {
            if (bullets[i] != null) {
                Vector position = bullets[i].getPosition();
                tempField[position.getY()][position.getX()] = '*';
            }
        }

        Tank[] tanks = gameBoard.getTanks();
        for (int i = 0; i < 4; i++) {
            if (tanks[i] != null) {
                Vector position = tanks[i].getPosition();
                tempField[position.getY()][position.getX()] = 'Ж';
            }
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(tempField[i][j]);
            }
            System.out.println();
        }
    }

    private void checkRespawn() {
        Tank[] tanks = gameBoard.getTanks();
        for (int i = 0; i < tanks.length; i++) {
            if (tanks[i] == null) {
                Player player = gameBoard.getPlayers()[i];
                int cooldawn = player.incrementAndGetTicksFromDeath();
                if (cooldawn > 10) {
                    player.clearTickFromDeath();
                    tanks[i] = new Tank(gameBoard, player);
                }
            }
        }
    }

    private void moveTanks() {
        Tank[] tanks = gameBoard.getTanks();
        for (int i = 0; i < tanks.length; i++) {
            if (tanks[i] != null) {
                tanks[i].move();
            }
        }
    }

    private void moveBullets() {
        Bullet[] bullets = gameBoard.getBullets();
        for (int i = 0; i < BULLET_SPEED; i++) {
            for (int j = 0; j < bullets.length; j++) {
                if (bullets[j] != null) {
                    bullets[j].move();
                }
            }
            gameBoard.getCollisionChecker().checkBulletsCollisions(gameBoard);
        }
    }

    private void makeDecisions() {
        Player[] players = gameBoard.getPlayers();
        Tank[] tanks = gameBoard.getTanks();
        for (int i = 0; i < players.length; i++) {
            if (tanks[i] != null) {
                TankAI tankAI = players[i].getTankAI();
                Vector direction = tankAI.chooseDirection();
                if (direction != null) {
                    tanks[i].setSpeed(direction);
                }
                if (tankAI.shouldShot()) {
                    tanks[i].shot();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        for (int i = 0; i < 100; i++) {
            game.mainLoop();
        }
    }
}
