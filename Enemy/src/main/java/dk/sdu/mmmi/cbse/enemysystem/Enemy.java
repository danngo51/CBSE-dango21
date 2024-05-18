package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {
    private int shootInterval;
    private int shootCounter;

    public int getShootInterval() {
        return shootInterval;
    }

    public void setShootInterval(int shootInterval) {
        this.shootInterval = shootInterval;
    }

    public int getShootCounter() {
        return shootCounter;
    }

    public void setShootCounter(int shootCounter) {
        this.shootCounter = shootCounter;
    }
}
