class HealthPotion extends Potion {
    private int healthBoost;

    public HealthPotion(String name, int duration, int healthBoost) {
        super(name, duration);
        this.healthBoost = healthBoost;
    }

    @Override
    public void applyEffect(Heroes hero) {
        hero.setHealth(hero.getHealth() + healthBoost);
        System.out.println(hero.getName() + " использует " + getName() + " и восстанавливает " + healthBoost + " здоровья!");
    }

    @Override
    public void removeEffect(Heroes hero) {
        // Зелье здоровья не требует отмены эффекта
        System.out.println(hero.getName() + " теряет эффект " + getName() + ".");
    }
}