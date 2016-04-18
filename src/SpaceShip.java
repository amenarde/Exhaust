import java.awt.Color;

public class SpaceShip extends SpaceObject implements MoveableObject{
        private final static boolean[][] shape =   {{false, false, false, false, true, true, false, false, false, false},
                                                    {false, false, false, true, true, true, true, false, false, false},
                                                    {false, false, true, true, true, true, true, true, false, false},
                                                    {false, true, true, true, true, true, true, true, true, false},
                                                    {true, true, true, false, true, true, false, true, true, true},
                                                    {false, false, false, false, true, true, false, false, false, false},
                                                    {false, false, false, true, false, false, true, false, false, false},
                                                    {false, false, false, true, false, false, true, false, false, false},
                                                    {false, false, false, true, false, false, true, false, false, false},
                                                    {false, false, false, true, false, false, true, false, false, false}};
        private int velocityX = 0;
        private int velocityY = 0;
        
    public SpaceShip (int leftX, int upperY, Color color) {
        super(leftX, upperY, shape, color);
    }
    
    public void move () {
        super.leftX += velocityX;
        super.rightX += velocityX;
        super.lowerY += velocityY;
        super.upperY += velocityY;
        super.centerX += velocityX;
        super.centerY += velocityY;
    }
    
    public int getXVelocity() { return velocityX; }
    public int getYVelocity() { return velocityY; }
    
    public void force (int x, int y) {
        velocityX += x;
        velocityY += y;
        
        if (velocityX > 10) { velocityX = 10; }
        if (velocityX < -10) {velocityX = -10; }
        if (velocityY > 10) { velocityY = 10; }
        if (velocityY < -10) {velocityY = -10; }
    }
    
    public void stop() {
        velocityX = 0; velocityY = 0;
    }
}
