import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow(){
        setTitle("Simple java game");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GamePanel panel = new GamePanel();
        add(panel);

    }



}
