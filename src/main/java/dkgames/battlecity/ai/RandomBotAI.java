package dkgames.battlecity.ai;

import dkgames.battlecity.entity.Vector;
import java.util.Random;

public class RandomBotAI implements TankAI {
    private static final Random random = new Random();
    public static final int CHANGE_DIRECTION_THRESHOLD = 3;
    public static final int SHOOT_THRESHOLD = 5;
    private int changeDirectionCounter;
    private int shootCounter;

    public Vector chooseDirection() {
        changeDirectionCounter++;
        if (changeDirectionCounter >= CHANGE_DIRECTION_THRESHOLD) {
            changeDirectionCounter = 0;
            return createRandomDirection();
        }
        return null;
    }

    public boolean shouldShot() {
        if (shootCounter > SHOOT_THRESHOLD) {
            shootCounter = 0;
            return true;
        }
        shootCounter++;
        return false;
    }

    private Vector createRandomDirection() {
        int seed = random.nextInt(4);
        int vx = 0;
        int vy = 0;
        if ((seed & 1) == 1) {
            vx = seed - 2;
        } else {
            vy = seed - 1;
        }
        return new Vector(vx, vy);
    }
}
