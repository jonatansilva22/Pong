import java.awt.geom.Rectangle2D;

public class Paddle {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 80;

    private final int[] yPositions = { 10,70,130,200,260,320,390};
    private Rectangle2D.Double rectangle;
    private int pos = 4;

    public Paddle(int x) {
        super();
        rectangle = new Rectangle2D.Double(x,200,WIDTH,HEIGHT);
    }

    public void moveDown() {
        if( pos < 6 ) {
            pos++;
            rectangle.y = yPositions[pos];
        }
    }

    public void moveUp() {
        if( pos > 0 ) {
            pos--;
            rectangle.y = yPositions[pos];
        }
    }

    // regresa el rectangulo que se va a dibujar
    public Rectangle2D.Double getRectangle() {
        return rectangle;
    }

    // verifica si la pelota toco la raqueta
    public boolean check( int x, int y) {
        return rectangle.contains(x,y);
    }
}