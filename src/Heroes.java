public class Heroes extends Temper{
    private int gold;
    private int xp;
    private int strength;

    public Heroes(String name, int health, int gold, int agility, int xp, int strength) {
        super(name, health, gold, agility, xp, strength);
        this.gold = gold;
        this.xp = xp;
        this.strength = strength;
    }

    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public void setGold(int amount) {
        this.gold += amount;
    }

    @Override
    public int getXp() {
        return xp;
    }

    @Override
    public void setXp(int xp) {
        this.xp += xp;
    }
    public void usePotion(Potion potion) {
        potion.use(this);
        this.strength += potion.getStrengthBoost();
        System.out.println("Теперь сила " + getName() + " составляет " + strength);

    }

    @Override
    public int getStrength() {
        return strength;
    }
}

