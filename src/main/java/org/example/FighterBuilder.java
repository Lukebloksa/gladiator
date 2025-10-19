package org.example;

public class FighterBuilder {
    private int hp;
    private int maxHp;
    private int dmg;
    private int critChance;
    private boolean isBlocking = false;

    public FighterBuilder(int hp, int maxHp, int dmg, int critChance) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.dmg = dmg;
        this.critChance = critChance;
    }

    public FighterBuilder() {

    }

    public FighterBuilder hp(int val) { this.hp = val; return this; }
    public FighterBuilder maxHp(int val) { this.maxHp = val; return this; }
    public FighterBuilder dmg(int val) { this.dmg = val; return this; }
    public FighterBuilder critChance(int val) { this.critChance = val; return this; }

    public Fighter build() {
        return new Fighter(hp, maxHp, dmg, critChance, isBlocking);
    }
}
