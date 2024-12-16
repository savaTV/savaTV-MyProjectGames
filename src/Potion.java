public class Potion {
    private String name;
    private int strengthBoost;

    public Potion(String name, int strengthBoost) {
        this.name = name;
        this.strengthBoost = strengthBoost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrengthBoost() {
        return strengthBoost;
    }

    public void setStrengthBoost(int strengthBoost) {
        this.strengthBoost = strengthBoost;
    }
    public void use(Heroes hero) {
        System.out.println(hero.getName() + " использует " + name +" и получает " + strengthBoost + "к силе");
        hero.setStrength(hero.getStrength() + strengthBoost);
    }
}
