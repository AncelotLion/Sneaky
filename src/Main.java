import javax.swing.*;

public class Main extends JFrame{
    public Main () {
        setTitle("Sneaky");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocation(400,400);
        add(new SneakyField());
        setVisible(true);
    }

    public static void main(String[] args) {
        Main mw = new Main();
    }

}

