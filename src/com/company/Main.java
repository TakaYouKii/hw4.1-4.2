package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 1000;
    public static int bossDamage = 500;
    public static String bossDefence = "";
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medical", "Lucky", "Golem", "Berserk", "Thor"};
    public static int[] heroesHealth = {270, 260, 250, 300, 220, 1000, 200, 240};
    public static int[] heroesDamage = {15, 20, 10, 0, 15, 5, 10, 15};


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
            lucKy();
            goLem();
            berSerk();
            tHor();
        }
    }

    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss choose defence: " + bossDefence);
    }

    public static void round() {
        if (bossHealth > 0) {
            changeBossDefence();
            bossHits();
        }
        medicsTreatment();
        heroesHit();
        printStatistics();
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }

            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("__________________");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        System.out.println("__________________");
    }

    public static void medicsTreatment() {
        int index = 0;
        for (String name : heroesAttackType) {
            if (name == "Medical") {
                if (heroesHealth[index] > 0) {
                    for (int i = 0; i < heroesHealth.length; i++) {
                        if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && i != index) {
                            heroesHealth[i] = heroesHealth[i] + 30;
                            System.out.println("Medic save  " + heroesAttackType[i] + " " + heroesHealth[i]);
                            break;

                        }
                    }
                }
            }
            index++;
        }
    }

    public static void lucKy() {
        int index = 0;
        Random random = new Random();
        boolean lck = random.nextBoolean();
        for (String name : heroesAttackType) {
            if (name.equals("Lucky")) {
                if (lck = true)
                    bossDamage = 0;
                System.out.println("Laci uklon");
            }
        }
        index++;
    }

    public static void goLem() {
        int index = 0;
        int b = bossDamage / 5;
        Random random = new Random();
        boolean glm = random.nextBoolean();
        for (String name : heroesAttackType) {
            if (name.equals("Golem")) {
                if (glm = true) {
                    bossDamage = bossDamage - b;
                    heroesHealth[5] = heroesHealth[5] + b;
                }
                if (!glm) {
                    bossDamage = bossDamage;
                }
            }
        }
    }

    public static void berSerk() {
        int index = 0;
        int a = bossDamage / 2;
        Random random = new Random();
        boolean brsk = random.nextBoolean();
        for (String name : heroesAttackType) {
            if (name.equals("Berserk")) {
                if (brsk = true) {
                    bossDamage = bossDamage - a;
                    heroesHealth[6] = heroesHealth[6] - a;
                    heroesDamage[6] = heroesDamage[6] + a;
                }
                if (!brsk) {
                    bossDamage = bossDamage;
                }
            }
            index++;
        }
    }

    public static void tHor() {
        int index = 0;
        for (String name : heroesAttackType) {
            if (name == "Thor") {
                bossDamage = 0;
            }
            index++;
        }
    }
}

 
