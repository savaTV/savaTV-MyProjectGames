abstract class Potion{
    private String name;
    private int duration;

    public Potion (String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
    public abstract void applyEffect(Heroes heroes);// применение эффетка
    public abstract void removeEffect(Heroes heroes);// отмена эффекта
}
