import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BallRunner implements Runnable {

    public static final int MAX_X = 640;
    public static final int MAX_Y = 480;
    public static final int SIGN = -1;

    public static int DX = -10;
    public static int DY = 10;

    private static Ellipse2D.Double ball;

    public static int point = 0;
    public static int point2 = 0;

    private Paddle paddle;
    private Paddle paddle2;

    private static int ballX;
    private static int ballY;

    public BallRunner(Ellipse2D.Double shape, Paddle p1, Paddle p2) {
        ball = (Ellipse2D.Double) shape;
        paddle = p1;
        paddle2 = p2;
        ballX = 320;
        ballY = 240;
        ball.x = ballX;
        ball.y = ballY;
    }

    public static int getCurrentX() {
        return (int) ball.x;
    }

    public static int getCurrentY() {
        return (int) ball.y;
    }

    public static int trackPoint() {
        return point;
    }

    public static int trackPoint2() {
        return point2;
    }

    public static void gameOver() {
        DX = 0;
        DY = 0;
        ballX = 320;
        ball.x = ballX;
        ballY = 240;
        ball.y = ballY;
    }

    @Override
    public void run() {
        int directionY = 1;
        int directionX = 1;
        int xmin, xmax, ymin, ymax;

        while (true) {

            xmin = (int) ball.getMinX();
            xmax = (int) ball.getMaxX();
            ymin = (int) ball.getMinY();
            ymax = (int) ball.getMaxY();

            if (paddle.check(xmin, ymin) || paddle.check(xmin, ymax)) {
                directionY = directionY * SIGN;
                ballX = ballX + (DX * directionX);
                ballY = ballY + (DY * directionY);
                ball.x = ballX;
                ball.y = ballY;
                continue;
            }
            if (paddle2.check(xmax, ymin) || paddle2.check(xmax, ymax)) {
                // sX = sX * SIGN;
                directionX = directionX * SIGN;
                ballX = ballX + (DX * directionX);
                ballY = ballY + (DY * directionY);
                ball.x = ballX;
                ball.y = ballY;
                continue;
            }
            if (ball.x == MAX_X) {
                ballX = 320;
                ball.x = ballX;
                ballY = 240;
                ball.y = ballY;
                point++;
            }
            if (ball.y == MAX_Y || ball.y == 0) {
                directionY = directionY * SIGN;
            }
            ballX = ballX + (DX * directionX);
            ballY = ballY + (DY * directionY);
            ball.x = ballX;
            ball.y = ballY;

            ballX = ballX + (DX * directionX);
            ballY = ballY + (DY * directionY);
            ball.x = ballX;
            ball.y = ballY;
            try {
                Thread.sleep(135L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}