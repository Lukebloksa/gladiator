package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Logic {
    private int hp;
    private int maxHp;
    private int dmg;
    private int critChance;
    private boolean isBlocking = false;

    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);

    public Logic() {
        this.hp = 50;
        this.maxHp = 50;
        this.dmg = 10;
        this.critChance = 10;
    }

    public void investments() {
        int points = 5;
        System.out.println("point for extra stats" + points);

        while (points > 0) {
            System.out.println("\n" + points + " points.");
            System.out.println("1. HP (" + maxHp + ")");
            System.out.println("2. Damage (" + dmg + ")");
            System.out.println("3. Crit Chance (" + critChance + "%)");
            System.out.print("choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> { maxHp += 5; hp = maxHp; points--; }
                case 2 -> { dmg += 2; points--; }
                case 3 -> { critChance += 2; points--; }
                default -> System.out.println("Neplatná volba!");
            }
        }

        System.out.println("\nTvoje finální statistiky:");
        showStats();
    }

    public void showStats() {
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("DMG: " + dmg);
        System.out.println("Crit chance: " + critChance + "%");
    }

    public void generateEnemyStats() {
        this.hp = rand.nextInt(40, 120);
        this.maxHp = this.hp;
        this.dmg = rand.nextInt(1, 40);
        this.critChance = rand.nextInt(1, 100);

        System.out.println("HP: " + hp);
        System.out.println("DMG: " + dmg);
        System.out.println("Crit chance: " + critChance + "%");
    }

    public void fight(Logic enemy) {
        while (this.hp > 0 && enemy.hp > 0) {
            System.out.println("\n--- Tvoje kolo ---");
            System.out.println("HP: " + hp + "/" + maxHp);
            System.out.println("1. attack");
            System.out.println("2. Block");
            System.out.print("choose ");
            int action = sc.nextInt();

            if (action == 2) {
                this.isBlocking = true;
                System.out.println("you blocked!");
            } else {
                attack(enemy);
            }

            if (enemy.hp <= 0) {
                System.out.println("\n BIG W");
                break;
            }


            enemyTurn(enemy, this);
            if (this.hp <= 0) {
                System.out.println("\n lost");
                break;
            }
        }
    }

    private void attack(Logic enemy) {
        int finalDmg = dmg;
        if (rand.nextInt(100) < critChance) {
            finalDmg *= 2;
            System.out.println("crit");
        }

        if (enemy.isBlocking) {
            finalDmg /= 2;
            enemy.isBlocking = false;
            System.out.println("blocked " + finalDmg);
        }

        enemy.hp -= finalDmg;
        System.out.println("dmg done " + finalDmg);
        if (enemy.hp < 0) enemy.hp = 0;
        System.out.println("enemy " + enemy.hp + " HP.");
    }


    private void enemyTurn(Logic enemy, Logic player) {
        System.out.println("\n--- enemy turn");
        int enemyChoice = rand.nextInt(100);
        if (enemyChoice < 25) {
            enemy.isBlocking = true;
            System.out.println("Enemy is blocking");
        } else {
            enemy.attack(player);
        }
    }
}
