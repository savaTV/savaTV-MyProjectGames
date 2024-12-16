public class Goblin extends Temper{
    public Goblin(String name, int health, int gold, int agility, int xp, int strength) {
        super(name, health, gold, agility, xp, strength);
    }

    @Override
    public int getGold() {
        return 15;
    }

    @Override
    public int getXp() {
        return 10;
    }
}
