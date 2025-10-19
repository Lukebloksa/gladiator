package org.example;

public class Fighter {
    private int hp;
    private int maxHp;
    private int dmg;
    private int critChance;
    private boolean isBlocking = false;

    public Fighter(int hp, int maxHp, int dmg, int critChance, boolean isBlocking) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.dmg = dmg;
        this.critChance = critChance;
        this.isBlocking = isBlocking;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
    }
}
