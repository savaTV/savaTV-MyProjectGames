import javax.naming.InsufficientResourcesException;
import java.util.Random;
import java.util.Scanner;

public class GameWorld {
    private static final int MAX_HEALTH = 100;
    private static final int MAX_LEVEL = 10;
    private static final int CRIT_CHANCE = 20;
    private static final int ATTACK_CHANCE = 3;

    private Scanner scanner;
    private Heroes heroes;
    private Goblin goblin;
    private Skelet skelet;
    private Random random;



    public GameWorld() {
        scanner = new Scanner(System.in);
        random = new Random();
        goblin = new Goblin("Гоблин", MAX_HEALTH, 15, 26, 100, 9);
        skelet = new Skelet("Скелет", MAX_HEALTH, 5, 8, 50, 7);
    }

    public void heroName() {
        System.out.println("Введите имя героя!");
        String name = scanner.nextLine();
        heroes = new Heroes(name, 100, 0, 20, 0, 15, 0);


    }


    public void startGame() throws InterruptedException {
        scanner = new Scanner(System.in);
        System.out.println("Выберите куда пойдет герой ");
        System.out.println("1:Темный лес");
        System.out.println("2:Заглянуть к торговцу");
        System.out.println("3:Информация о герое");
        System.out.println("4:Выйти из игры");
        int choiceAction = getIntInput();

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
                System.out.println("Информация о герое");
                printHeroInfo();
                break;
            case 4:
                System.out.println("Выйти из игры");
                return;
            default:
                System.out.println("Неверное действие повторите еще раз");
        }
    }
    private void selectBattleMode() throws InterruptedException {
        System.out.println("Выбери режим боя.");
        System.out.println("1: Автоматический");
        System.out.println("2: Ручной");
        System.out.println("3: Пропустить бой");
        int battleMode = scanner.nextInt();

        switch (battleMode) {
            case 1,2:
                theFight(battleMode == 1);
                break;
            case 3:
                System.out.println("Бой пропущен!");
                endBattle(true);
                break;
            default:
                System.out.println("Неверный выбор, попробуй снова");
                selectBattleMode();
        }
    }
    private void automaticBattle(boolean fight) throws InterruptedException {
        theBattle(fight);
        if (checkEnemyHealth(fight)) {
            endBattle(fight);
            return;
        }
        Thread.sleep(2000);
        if (!checkEnemyHealth(fight)) {
            heroAttack(getEnemy(fight));
        }
    }
    private void manualBattle(boolean fight) throws InterruptedException {
        System.out.println("Ваш ход! Выберите действие:");
        System.out.println("1: Атаковать");
        System.out.println("2: Использовать зелье");
        System.out.println("3: Уйти в город");
        int choiceAction = scanner.nextInt();

        switch (choiceAction) {
            case 1:
                theBattle(fight);

                if (checkEnemyHealth(fight)) {
                    endBattle(fight);
                    return;
                }

                Thread.sleep(2000);
                heroAttack(getEnemy(fight));
                break;
            case 2:
                System.out.println("Ваш инвентарь:");
                System.out.println("Выберите зелье:");
                usePotionInBattle();
                selectPotion();

                break;
            case 3:
                System.out.println("Бежать в город...");
                startGame();
                break;
            default:
                System.out.println("неверный выбор, попробуй еще раз.");
                manualBattle(fight);
        }
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) { // Проверяем, что введено число
            System.out.println("Пожалуйста, введите число.");
            scanner.next(); // Очищаем неправильный ввод
        }
        return scanner.nextInt(); // Возвращаем число
    }
    private void visitMerchant() throws InterruptedException {
        Merchant merchant = new Merchant();
        while (true) {
            System.out.println("Вы пришли к торговцу");
            System.out.println("У вас " + heroes.getGold() + " золота");
            System.out.println("1: Купить Зелье Силы (20 золота)");
            System.out.println("2: Купить Зелье Здоровья (30 золота)");
            System.out.println("3: Вернуться в город");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    merchant.sellPotion("Зелье силы", heroes);
                    break;
                case 2:
                    merchant.sellPotion("Зелье здоровья", heroes);
                    break;
                case 3:
                    System.out.println("Вы вернулись в город.");
                    startGame();
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте еще раз.");
            }

        }
    }
    private void selectPotion() {
        System.out.println("Выберите зелье:");
        System.out.println("1: Зелье Силы");
        System.out.println("2: Зелье Здоровья");
        System.out.println("3: Вернуться в меню");
        int choicePotion = getIntInput();

        switch (choicePotion) {
            case 1:
                if (heroes.getPotionStrength() != null) {
                    System.out.println("Выпить зелье Силы");
                    heroes.setStrength(heroes.getStrength() + heroes.getPotionStrength().getStrength());
                    heroes.setPotionStrength(null);
                    System.out.println("Урон повышен до " + heroes.getStrength());
                } else {
                    System.out.println("У вас нет зелья силы");
                }
                break;
            case 2:
                if (heroes.getPotionHealth() != null) {
                    System.out.println("Выпить зелье Здоровья");
                    heroes.setHealth(heroes.getMaxHealth());
                    heroes.setPotionHealth(null);
                    System.out.println("Здоровье восстановлено до полного");
                } else {
                    System.out.println("У вас нет зелья здоровья");
                }
                break;
            case 3:
                System.out.println("Возврат в меню.");
                return; // Выход из метода
            default:
                System.out.println("Неверный выбор, попробуйте еще раз.");
                selectPotion(); // Повторный выбор
        }
    }

    private void usePotionInBattle() {
        heroes.showInventory();
        if (!heroes.getInventory().isEmpty()) {
            System.out.println("Выберите зелье:");
            int choicePotion = getIntInput() - 1;
            heroes.usePotion(choicePotion);
        }
    }






    public void theBattle(boolean fight) {
        int damage = critAttack(fight);
        Temper enemy = fight ? goblin : skelet;
        enemy.takeDamage(damage);
        if (damage > 0) {
            System.out.println(enemy.getName() + " Получил урон. Осталось здоровья " + enemy.getHealth());
        }

        if (enemy.getHealth() <= 0) {
            System.out.println(enemy.getName() + "Повержен");
        }
    }

    private boolean checkEnemyHealth(boolean fight) {
        Temper enemy = getEnemy(fight);
        return enemy.getHealth() <= 0;
    }

    private void heroAttack(Temper enemy) {
        if (random.nextInt(100) < enemy.getStrength() * ATTACK_CHANCE) {
            int damage = enemy.getStrength();
            heroes.takeDamage(damage);
            System.out.println(heroes.getName() + " получил урон от " + enemy.getName() + ". Осталось здоровья: " + heroes.getHealth());
            } else {
            System.out.println(enemy.getName() + " промахнулся!");
        }
    }

    private int critAttack(boolean fight) {
        int damage = 0;
        if (random.nextInt(100) < heroes.getAgility() * ATTACK_CHANCE) {
            damage = heroes.getStrength();
            if (random.nextInt(100) < CRIT_CHANCE) {
                damage *= 2;
                System.out.println("Критичиский удар " + damage);
            } else {
                System.out.println(heroes.getName() + " Нанес" + " Урон: " + damage + " Здоровье " + heroes.getHealth());
            }
        } else {
            System.out.println(heroes.getName() + " промахнулся!");

        }
        return damage;
    }

    private Temper getEnemy(boolean fight) {
        return fight ? goblin : skelet;
    }

    private void restoreEnemyHealth(boolean fight) {
        Temper enemy = getEnemy(fight);
        enemy.setHealth(enemy.getMaxHealth());

    }

    public void theFight(boolean isAutomatic) throws InterruptedException {
        boolean fight = random.nextBoolean();
        restoreEnemyHealth(fight);
        Temper enemy = getEnemy(fight);
        System.out.println("Вы сражаетесь с " + enemy.getName());
        System.out.println("Битва началась");
        int turnCount = 1;
        // бой идет пока герой жив
        while (heroes.getHealth() > 0) {
            System.out.println("----Ход" + turnCount++ + "----");
            if (isAutomatic) {
                automaticBattle(fight);
            } else {
                manualBattle(fight);
            }
            if (checkEnemyHealth(fight)) {
                endBattle(fight);
                return;
            }
        }
        endBattle(fight);

    }

    private void endBattle(boolean fight) throws InterruptedException {
        if (heroes.getHealth() > 0) {
            System.out.println("Победа");
            heroReward(fight);

        } else {
            System.out.println("Поражение");
            reviveHero();
        }
        choiceAfterFight();
    }

    //Получение награды после победы героя
    private void heroReward(boolean fight) {
        Temper enemy = fight ? goblin : skelet;
        heroes.setGold(enemy.getGold());
        heroes.setXp(enemy.getXp());
        System.out.println(heroes.getName() + " получает " + enemy.getGold() + " золота и " + enemy.getXp() + " опыта.");
        levelUp();
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

    private void levelUp() {
        int[] xpLevels = {20, 50, 100, 150, 200, 250, 300, 350, 400, 500};
        int[] healthIncrements = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int[] strengthIncrements = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};

        if (heroes.getLevel() >= xpLevels.length) {
            System.out.println("герой достин максимального уровня");

        }

        for (int i = heroes.getLevel(); i < xpLevels.length; i++) {
            if (heroes.getXp() >= xpLevels[i]) {
                heroes.setLevel(heroes.getLevel() + 1);
                int healthIncrement = healthIncrements[heroes.getLevel() - 1];
                int strengthIncrement = strengthIncrements[heroes.getLevel() - 1];
                heroes.setHealth(heroes.getHealth() + healthIncrement);
                heroes.setStrength(heroes.getStrength() + strengthIncrement);
                System.out.println("Уровень повышен до " + heroes.getLevel());
            }
        }
    }
    private void printHeroInfo() throws InterruptedException {
        System.out.println("Количесво опыта " + heroes.getXp());
        System.out.println("Здоровье " + heroes.getHealth());
        System.out.println("Уровень героя " + heroes.getLevel());
        Thread.sleep(5000);
        startGame();
    }
    private void reviveHero(){
        if (heroes.getHealth() <= 0) {
            System.out.println("Таймер возрождения");
            System.out.println("Возрождение через 60сек");
            for (int i = 59; i >= 0; i--) {
                System.out.print("\rВозрождение через " + i + " секунд");
                System.out.flush();
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            heroes.setHealth(heroes.getMaxHealth());
            System.out.println("Герой возродился");
        }
    }
}












