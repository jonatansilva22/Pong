import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

public class Pong extends JComponent implements Runnable, KeyListener {

    private Dimension preferredSize = null;
    private Ellipse2D.Double ball;
    Font pointFont;

    private Paddle paddle;
    private Paddle paddle2;

    public Pong() {
        setOpaque(true);
        pointFont = new Font("Monospaced", Font.PLAIN, 45);
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.WHITE));
        ball = new Ellipse2D.Double(20, 320, 20, 20);

        paddle = new Paddle(20);
        paddle2 = new Paddle(600);
        BallRunner ballRunner = new BallRunner(ball, paddle, paddle2);
        Thread t1 = new Thread(ballRunner);
        t1.start();
        Thread t2 = new Thread(this);
        t2.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isOpaque()) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(getForeground());
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(pointFont);

        g2.setStroke(new BasicStroke(5.0f));

        g2.drawLine(320, 0, 320, 480);
        g2.drawString(Integer.toString(BallRunner.trackPoint()), (int) getBounds().getCenterX() - 50, 35);
        g2.drawString(Integer.toString(BallRunner.trackPoint2()), (int) getBounds().getCenterX() + 25, 35);
        if (BallRunner.trackPoint() == 5) {
            g2.drawString("Jugador 1 gana", (float) getBounds().getCenterX() - 180, (float) getBounds().getCenterY());
            BallRunner.gameOver();
        }
        if (BallRunner.trackPoint2() == 5) {
            g2.drawString("Jugador 2 gana", (float) getBounds().getCenterX() - 180, (float) getBounds().getCenterY());
            BallRunner.gameOver();
        }

        g2.setColor(Color.WHITE);
        g2.fill(ball);
        g2.setColor(Color.WHITE);
        g2.fill(paddle.getRectangle());
        g2.setColor(Color.WHITE);
        g2.fill(paddle2.getRectangle());
    }

    public Dimension getPreferredSize() {
        if (preferredSize == null) {
            return new Dimension(640, 480);
        } else {
            return super.getPreferredSize();
        }
    }

    public void setPreferredSize(Dimension newPrefSize) {
        preferredSize = newPrefSize;
        super.setPreferredSize(newPrefSize);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        switch (key) {
            case 'w':
                paddle.moveUp();
                break;
            case 's':
                paddle.moveDown();
                break;
            case 'i':
                paddle2.moveUp();
                break;
            case 'k':
                paddle2.moveDown();
                break;
        }
        // System.out.println("K");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}