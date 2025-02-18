class StrengthPotion extends Potion {
    private int strengthBoost;

    public StrengthPotion(String name, int duration, int strengthBoost) {
        super(name, duration);
        this.strengthBoost = strengthBoost;
    }

    @Override
    public void applyEffect(Heroes hero) {
        hero.setStrength(hero.getStrength() + strengthBoost);
        System.out.println(hero.getName() + " использует " + getName() + " и получает +" + strengthBoost + " к силе!");
    }

    @Override
    public void removeEffect(Heroes hero) {
        hero.setStrength(hero.getStrength() - strengthBoost);
        System.out.println(hero.getName() + " теряет эффект " + getName() + ". Сила уменьшена на " + strengthBoost + ".");
    }
}