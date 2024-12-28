import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {
    private int x, y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 50);
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            move(-10, 0);
        } else if (key == KeyEvent.VK_RIGHT) {
            move(10, 0);
        } else if (key == KeyEvent.VK_UP) {
            move(0, -10);
        } else if (key == KeyEvent.VK_DOWN) {
            move(0, 10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
