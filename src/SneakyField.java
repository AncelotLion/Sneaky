import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SneakyField extends JPanel implements ActionListener {
    private final int Size = 600;
    private final int dotSize = 16;
    private final int allDots = 400;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[allDots];
    private int[] y = new int[allDots];
    private int dots;
    private Timer timer;
    private boolean inGame = true;
    char direction = 'R';
    boolean running = false;
    Random random;


    public SneakyField() {
        random = new Random();
        setBackground(Color.BLACK);
        loadImages();
        initGame();
        this.addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    public void initGame() {
        dots = 3;
        /*for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * dotSize;
            y[i] = 48;
        }*/
        running = true;
        timer = new Timer(250, this);
        timer.start();
        createApple();

    }

    public void createApple() {
        appleX = new Random().nextInt(20) * dotSize;
        appleY = new Random().nextInt(20) * dotSize;

    }

    public void loadImages() {
        ImageIcon iia = new ImageIcon("src/dot.png");
        dot = iia.getImage();
        ImageIcon iia2 = new ImageIcon("src/apple.png");
        apple = iia2.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        } else {
            String str = "Game Over";
            //Font f = new Font("Arial",14,Font.BOLD);
            g.setColor(Color.WHITE);
            //g.setFont(f);
            g.drawString(str, 250, Size / 2);
        }
    }


    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - dotSize;
                break;
            case 'D':
                y[0] = y[0] + dotSize;
                break;
            case 'L':
                x[0] = x[0] - dotSize;
                break;
            case 'R':
                x[0] = x[0] + dotSize;
                break;

        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            {
                dots++;
                if (dots % 5 == 0) {
                    timer.setDelay(timer.getDelay() - 50);
                }
                createApple();
            }
        }
    }

    public void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if ((i > 4 && x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
            }
        }
        if (x[0] > Size) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (y[0] > Size) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollisions();
            move();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
