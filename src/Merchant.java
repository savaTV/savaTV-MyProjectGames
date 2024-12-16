public class Merchant {
    private int powerPotion;

    public Merchant() {
        this.powerPotion = 20; // цена зелья
    }
    public int getPotionPrice() {
        return powerPotion;
    }
    public void sellPotion(Heroes hero) {
        if (hero.getGold() >= powerPotion) {
            hero.setGold(-powerPotion);
            System.out.println(hero.getName() + "купил зелье силы!");
        }else {
            System.out.println("У вас недостаточно золота для покупки зелья силы");
        }
    }

}

