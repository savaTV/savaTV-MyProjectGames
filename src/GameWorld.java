import java.util.Random;
import java.util.Scanner;

public class GameWorld {
    private Scanner scanner;
    private Heroes heroes;
    private Goblin goblin;
    private Skelet skelet;

    public GameWorld() {
        scanner = new Scanner(System.in);
        goblin = new Goblin("Гоблин", 100, 15, 26, 10, 9);
        skelet = new Skelet("Скелет", 100, 5, 8, 5, 7);
    }

    public void heroName() {
        System.out.println("Введите имя героя!");
        String name = scanner.nextLine();
        heroes = new Heroes(name, 100, 12, 30, 10, 24);


    }

    public void heroMant() {

    }

    public void startGame() throws InterruptedException {
        scanner = new Scanner(System.in);
        System.out.println("Выберите куда пойдет герой ");
        System.out.println("1:Темный лес");
        System.out.println("2:Заглянуть к торговцу");
        System.out.println("3:Выйти из игры");
        int choiceAction = scanner.nextInt();

        switch (choiceAction) {
            case 1:
                System.out.println("Темный лес");
                theFight();
                break;
            case 2:
                System.out.println("Заглянуть к торговцу");
                break;
            case 3:
                System.out.println("Выйти из игры");
                break;
            default:
                System.out.println("Неверное действие повторите еще раз");
        }
    }


    public void theFight() throws InterruptedException {
        Random random = new Random();
        int criticalHitDamage = 20;
        int criticalDamageMultiplier = 3;
        boolean fight = random.nextBoolean();
        int heroHitChacne = heroes.getAgility() * 3;
        int goblinHitChance = goblin.getAgility() * 3;
        int skeletHitChance = skelet.getAgility() * 3;

        Thread fightThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (fight) {
                System.out.println("Вы сражаетесь с Гоблином");
            } else {
                System.out.println("Вы сражаетесь со Скелетом");
            }
        });
        fightThread.start();
        fightThread.join();

        //пауза перед началом битвы.
        Thread.sleep(2000);
        System.out.println("Битва началась");
        Thread.sleep(1000);


        // бой
        while (heroes.getHealth() > 0 && (fight ? goblin.getHealth() > 0 : skelet.getHealth() > 0)) {
                int damage = 0;
                // проеверка попадания героев
            if (random.nextInt(100) < heroHitChacne) {
                //елси попал, вычисляем урон.
                damage = heroes.getAttack();
            } else

                //проверка крит удара
                if (random.nextInt(100) < criticalHitDamage) {
                    damage *= criticalDamageMultiplier;
                    System.out.println("Критический урон! Урон: " + damage);
                } else {
                    System.out.println("урон" + damage);
                }


                // наносим врагу урон
                if (fight) {
                    if (random.nextInt(100) > hitChacne)
                    goblin.takeDamage(damage);
                    System.out.println("Гоблин получил урон. Осталось здоровья: " + goblin.getHealth());

                    if (goblin.getHealth() <= 0) {
                        System.out.println("Гоблин повержен");
                        break;
                    }
                    damage = goblin.getAttack();
                    heroes.takeDamage(damage);
                    System.out.println(heroes.getName() + " получил урон. Осталось здоровья: " + heroes.getHealth());

                } else {
                    skelet.takeDamage(damage);
                    System.out.println("Скелет получил урон. Осталось здоровья: " + skelet.getHealth());

                    if (skelet.getHealth() <= 0) {
                        System.out.println("Cкелет повержен!");
                        break;
                    }
                    damage = skelet.getAttack();
                    heroes.takeDamage(damage);
                    System.out.println(heroes.getName() + " получил урон. Осталось здоровья: " + heroes.getHealth());

                    if (heroes.getHealth() <= 0) {
                        System.out.println(heroes.getName() + "  повержен!");
                        }
                    }
                }
            }
        }



