import java.util.HashMap;
import java.util.Map;

class Merchant {
    private Map<String, Potion> potions;

    public Merchant() {
        potions = new HashMap<>();
        potions.put("Зелье силы", new StrengthPotion("Зелье силы", 3, 10));
        potions.put("Зелье здоровья", new HealthPotion("Зелье здоровья", 1, 50));
    }

    public void sellPotion(String potionName, Heroes hero) {
        Potion potion = potions.get(potionName);
        if (potion != null && hero.getGold() >= getPotionPrice(potionName)) {
            hero.setGold( - getPotionPrice(potionName));
            hero.addPotion(potion);
            System.out.println(hero.getName() + " купил " + potionName + "!");
        } else {
            System.out.println("У вас недостаточно золота или зелье недоступно.");
        }
    }

    public int getPotionPrice(String potionName) {
        return potionName.equals("Зелье силы") ? 20 : 30;
    }
}