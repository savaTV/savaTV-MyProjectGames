public class Temper {

    private String name;
    private int health;
    private int gold;
    private int agility;
    private int xp;
    private int strength;
    private int maxHealth;


    public Temper(String name, int health,int gold, int agility, int xp, int strength) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.agility = agility;
        this.xp = xp;
        this.strength = strength;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public void takeDamage(int damage) {
        health -=damage;
        if (health < 0) {
            health = 0;
        }
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp += xp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Temper{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", gold=" + gold +
                ", agility=" + agility +
                ", xp=" + xp +
                ", strength=" + strength +
                '}';
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}



