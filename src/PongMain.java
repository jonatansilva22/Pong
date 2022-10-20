import javax.swing.*;
import java.awt.*;

public class PongMain {
    public static void main(String[] args) {

        JFrame f = new JFrame("Pong");

        JPanel p = new JPanel(new BorderLayout());
        Pong pong = new Pong();
        p.add(pong, BorderLayout.CENTER);

        f.addKeyListener( pong );
        f.setContentPane(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}