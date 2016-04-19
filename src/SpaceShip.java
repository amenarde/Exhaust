import java.awt.Color;

public class SpaceShip extends SpaceObject implements MoveableObject{
        private static String filename = "swordfish.png";
        private int velocityX = 0;
        private int velocityY = 0;
        
    public SpaceShip (int leftX, int upperY) {
        super(leftX, upperY, filename);
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
    
    public void force (int dx, int dy) {
        velocityX += dx;
        velocityY += dy;
        
        int maxv = 30;
        
        if (velocityX > maxv) { velocityX = maxv; }
        if (velocityX < -maxv) {velocityX = -maxv; }
        if (velocityY > maxv) { velocityY = maxv; }
        if (velocityY < -maxv) {velocityY = -maxv; }
    }
    
    public void stop() {
        velocityX = 0; velocityY = 0;
    }
}
