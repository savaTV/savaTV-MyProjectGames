import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;

public class GameWorld {
    private Scanner scanner;
    private Heroes heroes;
    private Goblin goblin;
    private Skelet skelet;
    private Random random;

    public GameWorld() {
        scanner = new Scanner(System.in);
        random = new Random();
        goblin = new Goblin("Гоблин", 100, 15, 26, 10, 9);
        skelet = new Skelet("Скелет", 100, 5, 8, 5, 7);
    }

    public void heroName() {
        System.out.println("Введите имя героя!");
        String name = scanner.nextLine();
        heroes = new Heroes(name, 100, 0, 20, 0,15);


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
                selectBattleMode();
                break;
            case 2:
                System.out.println("Заглянуть к торговцу");
                visitMerchant();
                break;
            case 3:
                System.out.println("Выйти из игры");
                break;
            default:
                System.out.println("Неверное действие повторите еще раз");
        }
    }

    private void visitMerchant() throws InterruptedException {
        Merchant merchant = new Merchant();
        System.out.println("Вы пришли к торговцу");
        System.out.println("У вас " + heroes.getGold() + " золота");
        System.out.println("Цена зелья силы " + merchant.getPotionPrice() + " золота.");
        System.out.println("Хотите купит зелье силы? (1: ДА, 2: Нет)");
        int choice = scanner.nextInt();

        if (choice == 1) {
            merchant.sellPotion(heroes);
            visitMerchant();
        }else {
            System.out.println("Уйти от торговца");
            startGame();
        }

    }

    private void selectBattleMode() throws InterruptedException {
        System.out.println("Выбери режим боя.");
        System.out.println("1: Автоматический");
        System.out.println("2: Ручной");
        int battleMode = scanner.nextInt();

        if (battleMode == 1 || battleMode == 2) {
            theFight(battleMode == 1);
        } else {
            System.out.println("Неверный выбор, попробуй снова");
        }
    }



    public void theFight(boolean isAutomatic) throws InterruptedException {
        boolean fight = random.nextBoolean();
        System.out.println("Вы сражаетесь с " + (fight ? goblin.getName() : skelet.getName()));
        System.out.println("Битва началась");


        // бой
        while (heroes.getHealth() > 0 && (fight ? goblin.getHealth() > 0 : skelet.getHealth() > 0)) {
            if (isAutomatic) {
                automaticBattle(fight);
            } else {
                manualBattle(fight);
            }
        }
        if (heroes.getHealth() > 0) {
            System.out.println("Победа");
            heroReward(fight);
            choiceAfterFight();


        }else {
            System.out.println("Поражение");
        }
    }
    private void heroReward(boolean fight){
        if (fight) {
            heroes.setGold((goblin.getGold()));
            heroes.setXp(goblin.getXp());
            System.out.println(heroes.getName() + " Получает " + goblin.getGold() + " Золота и " + goblin.getXp() + " Опыта");
        }else {
            heroes.setGold((skelet.getGold()));
            heroes.setXp(skelet.getXp());
            System.out.println(heroes.getName() + " Получает " + skelet.getGold() + " Золота и " + skelet.getXp() + " Опыта");
        }
    }

    private void automaticBattle(boolean fight) throws InterruptedException {
        int damage = attack(fight);
        if (damage > 0) {
            if (fight) {
                goblin.takeDamage(damage);
                System.out.println(goblin.getName() + " Получил урон. Осталось здоровья " + goblin.getHealth());
                if (goblin.getHealth() <= 0) {
                    System.out.println(goblin.getName() + " повержен");
                    return;
                }
            } else {
                skelet.takeDamage(damage);
                System.out.println(skelet.getName() + " Получил урон. Осталось здоровья " + skelet.getHealth());
            }if (skelet.getHealth() <= 0) {
                System.out.println(skelet.getName() + " повержен");
                return;
            }
        }

        Thread.sleep(2000);
        if (fight) {
            enemyAttack(goblin);
        } else {
            enemyAttack(skelet);
        }
    }

    private void manualBattle(boolean fight) throws InterruptedException {
        System.out.println("Ваш ход! Выберите действие:");
        System.out.println("1: Атаковать");
        System.out.println("2: Уйти в город");
        int choiceAction = scanner.nextInt();

        if (choiceAction == 1) {
            int damage = attack(fight);
            if (damage > 0) {
                goblin.takeDamage(damage);
                System.out.println(goblin.getName() + " Получил урон. Осталось здоровья " + goblin.getHealth());
                if (goblin.getHealth() <= 0) {
                    System.out.println(goblin.getName() + " повержен");
                    return;
                }
            } else {
                skelet.takeDamage(damage);
                System.out.println(skelet.getName() + " Получил урон. Осталось здоровья " + skelet.getHealth());
            }if (skelet.getHealth() <= 0) {
                System.out.println(skelet.getName() + " повержен");
                return;
            }
            Thread.sleep(2000);

            enemyAttack(fight ? goblin : skelet);
        } else if (choiceAction == 2) {
            System.out.println("Вы ушли в город.");
        } else {
            System.out.println("неверный выбор, попробуй еще раз.");
        }
    }

    private int attack(boolean fight) {
        int damage = 0;
        if (random.nextInt(100) < heroes.getAgility() * 3) {
            damage = heroes.getStrength();
            if (random.nextInt(100) < 20) {
                System.out.println("Критичиский удар " + damage);
            } else {
                System.out.println(heroes.getName() + " Нанес" + " Урон: " + damage + " Здоровье " + heroes.getHealth());
                }
            } else {
                System.out.println(heroes.getName() + " промахнулся!");
            }
            return damage;
        }


    private void enemyAttack(Temper enemy) {
        if (random.nextInt(100) < enemy.getStrength() * 3) {
            int damage = enemy.getStrength();
            heroes.takeDamage(damage);
            System.out.println(heroes.getName() + " получил урон от " + enemy.getName() + ". Осталось здоровья: " + heroes.getHealth());
        } else {
            System.out.println(enemy.getName() + " промахнулся!");
        }
    }

    private void choiceAfterFight() throws InterruptedException {
        System.out.println("Битва окончена. Вы хотите вернуться в город (1: ДА, 2: НЕТ)");
        int returnChoice = scanner.nextInt();
        if (returnChoice == 1) {
            System.out.println("Вы вернулись в город");
            startGame();
        } else {
            System.out.println("Вы остаетесь на поле битвы");
            selectBattleMode();
        }
    }
}












