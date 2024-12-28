import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameWindow window = new GameWindow();
        window.setVisible(true);
        GameWorld gameWorld = new GameWorld();
        gameWorld.heroName();
        gameWorld.startGame();




    }
}
