public class Skelet extends Temper {
    public Skelet(String name, int health, int gold, int agility, int xp, int strength) {
        super(name, health, gold, agility, xp, strength);
    }
    @Override
    public int getGold() {
        return 5;
    }

    @Override
    public int getXp() {
        return 5;
    }
}

