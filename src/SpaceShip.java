
public class SpaceShip extends SpaceObject implements MoveableObject{
        private static String filename = "swordfish.png";
        private double velocityX = 0;
        private double velocityY = 0;
        
    public SpaceShip (int leftX, int upperY) {
        super(leftX, upperY, filename);
    }
    
    public void move () {
        
        int extramoveX = 0;
        if (Math.random() < Math.abs(velocityX - (int)velocityX)) {
            extramoveX = 1;
            if (velocityX < 0) {
                extramoveX = -extramoveX;
            }
        }
        
        int extramoveY = 0;
        if (Math.random() < Math.abs(velocityY - (int)velocityY)) {
            extramoveY = 1;
            if (velocityY < 0) {
                extramoveY = -extramoveY;
            }
        }
        
        super.leftX += (int)velocityX + extramoveX;
        super.rightX += (int)velocityX + extramoveX;
        super.lowerY += (int)velocityY + extramoveY;
        super.upperY += (int)velocityY + extramoveY;
        
        super.leftX = constrain(super.leftX, 0, GameCourt.COURT_WIDTH - super.getWidth());
        super.rightX = constrain(super.rightX, super.getWidth(), GameCourt.COURT_WIDTH);
        super.upperY = constrain(super.upperY, super.getHeight(), GameCourt.COURT_HEIGHT);
        super.lowerY = constrain(super.lowerY, 0, GameCourt.COURT_HEIGHT - super.getHeight());
        
        super.centerX = (int)(super.rightX - super.leftX)/2 + super.leftX;
        super.centerY = (int)(super.upperY - super.lowerY)/2 + super.lowerY;
    }
    
    public static int constrain (int x, int a, int b) {
        if (x > b) { return b; }
        else if (x < a) { return a; }
        else { return x; }
    }
    
    public double getXVelocity() { return velocityX; }
    public double getYVelocity() { return velocityY; }
    
    public void force (double dx, double dy) {
        velocityX += dx;
        velocityY += dy;
        
//        int maxv = 30;
//        
//        if (velocityX > maxv) { velocityX = maxv; }
//        if (velocityX < -maxv) {velocityX = -maxv; }
//        if (velocityY > maxv) { velocityY = maxv; }
//        if (velocityY < -maxv) {velocityY = -maxv; }
    }
    
    public void stop() {
        velocityX = 0; velocityY = 0;
    }
}
