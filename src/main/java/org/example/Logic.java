package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Logic {

    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);

    private Fighter you = new FighterBuilder().hp(100).maxHp(100).dmg(35).critChance(10).build();
    private Fighter enemy;

    public void investments() {
        int points = 5;
        System.out.println("point for extra stats" + points);

        while (points > 0) {
            System.out.println("\n" + points + " points.");
            System.out.println("1. HP (" + you.getMaxHp() + ")");
            System.out.println("2. Damage (" + you.getDmg() + ")");
            System.out.println("3. Crit Chance (" + you.getCritChance() + "%)");
            System.out.print("choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> { you.setMaxHp(you.getMaxHp()+5); you.setHp(you.getMaxHp()); points--; }
                case 2 -> { you.setDmg(you.getDmg()+2); points--; }
                case 3 -> { you.setCritChance(you.getCritChance()+5); points--; }
                default -> System.out.println("Neplatná volba!");
            }
        }

        System.out.println("\nTvoje finální statistiky:");
        showStats();
    }

    public void showStats() {
        System.out.println("HP: " + you.getHp() + "/" + you.getMaxHp());
        System.out.println("DMG: " + you.getDmg());
        System.out.println("Crit chance: " + you.getCritChance() + "%");
    }

    public void generateEnemyStats() {
        int h = rand.nextInt(40, 120);
        int d = rand.nextInt(1, 40);
        int c = rand.nextInt(1, 100);

        enemy = new FighterBuilder()
                .hp(h)
                .maxHp(h)
                .dmg(d)
                .critChance(c)
                .build();

        System.out.println("HP: " + enemy.getHp());
        System.out.println("DMG: " + enemy.getDmg());
        System.out.println("Crit chance: " + enemy.getCritChance() + "%");
    }

    public void fight(Fighter enemy) {
        while (you.getHp() > 0 && enemy.getHp() > 0) {
            System.out.println("\n--- Tvoje kolo ---");
            System.out.println("HP: " + you.getHp() + "/" + you.getMaxHp());
            System.out.println("1. attack");
            System.out.println("2. Block");
            int action = readInt("choose ");

            if (action == 2) {
                you.setBlocking(true);
                System.out.println("you blocked!");
            } else {
                attack(you, enemy);
            }

            if (enemy.getHp() <= 0) {
                System.out.println("\n BIG W");
                break;
            }

            enemyTurn(enemy, you);

            if (you.getHp() <= 0) {
                System.out.println("\n lost");
                break;
            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                sc.nextLine(); // consume invalid token
            }
        }
    }

    private void attack(Fighter attacker, Fighter defender) {
        int finalDmg = attacker.getDmg();
        if (rand.nextInt(100) < attacker.getCritChance()) {
            finalDmg *= 2;
            System.out.println("crit");
        }

        if (defender.isBlocking()) {
            finalDmg /= 2;
            defender.setBlocking(false);
            System.out.println("blocked " + finalDmg);
        }

        defender.setHp(Math.max(0, defender.getHp() - finalDmg));
        System.out.println("dmg done " + finalDmg);
        System.out.println("enemy " + defender.getHp() + " HP.");
    }

    private void enemyTurn(Fighter enemy, Fighter player) {
        System.out.println("\n--- enemy turn");
        int enemyChoice = rand.nextInt(100);
        if (enemyChoice < 25) {
            enemy.setBlocking(true);
            System.out.println("Enemy is blocking");
        } else {
            attack(enemy, player);
        }
    }
}
