import java.util.ArrayList;
import java.util.List;

public class Heroes extends Temper{
    private int gold;
    private int xp;
    private int strength;
    private int level;
    private int maxHealth;
    private List<Potion> inventory;
    private String name;
    private Temper potionStrength;
    private Temper potionHealth;


    public Heroes(String name, int health, int gold, int agility, int xp, int strength, int level) {
        super(name, health, gold, agility, xp, strength );
        this.gold = gold;
        this.xp = xp;
        this.strength = strength;
        this.level = level;
        this.maxHealth = health;
        this.inventory = new ArrayList<>();
        this.name = name;

    }


    public void addPotion(Potion potion) {
        inventory.add(potion);
        System.out.println(name + " получил " + potion.getName() + "!");
    }

    public void usePotion(int index) {
        if (index >= 0 && index < inventory.size()) {
            Potion potion = inventory.get(index);
            potion.applyEffect(this);
            inventory.remove(index);
        } else {
            System.out.println("Неверный выбор зелья.");
        }
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Инвентарь пуст.");
        } else {
            System.out.println("Инвентарь:");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println((i + 1) + ": " + inventory.get(i).getName());
            }
        }
    }

    public List<Potion> getInventory() {
        return inventory;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }


    public Temper getPotionStrength() {
        return potionStrength;
    }

    public void setPotionStrength(Temper potionStrength) {
        this.potionStrength = potionStrength;
    }

    public Temper getPotionHealth() {
        return potionHealth;
    }

    public void setPotionHealth(Temper potionHealth) {
        this.potionHealth = potionHealth;
    }
}



