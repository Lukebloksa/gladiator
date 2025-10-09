package org.example;

import javax.swing.*;
import java.awt.*;

public class Ui {
    public void start() {
        Logic player = new Logic();
        Logic enemy = new Logic();

        System.out.println("\n--- investmensts ---");
        player.investments();

        System.out.println("\n--- enemystats ---");
        enemy.generateEnemyStats();

        System.out.println("\n FIGHT STARTS");
        player.fight(enemy);
    }
}