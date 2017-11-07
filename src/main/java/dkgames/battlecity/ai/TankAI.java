package dkgames.battlecity.ai;

import dkgames.battlecity.entity.Vector;

public interface TankAI {

    Vector chooseDirection();
    boolean shouldShot();

}
