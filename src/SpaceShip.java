
public class SpaceShip extends SpaceObject implements MoveableObject{
        private static String filename = "swordfish.png";
        private double velocityX = 0;
        private double velocityY = 0;
        
    public SpaceShip (int leftX, int upperY) {
        super(leftX, upperY, filename);
    }
    
    //moves piece and removes whether a wall conflict has occured.
    public boolean move () {
        
        super.leftX += (int)velocityX;
        super.rightX += (int)velocityX;
        super.lowerY += (int)velocityY;
        super.upperY += (int)velocityY;
        
        int extramoveX = 0;
        int extramoveY = 0;
      
        if (Math.random() < Math.abs(velocityX) - Math.abs((int)velocityX)) {
            extramoveX = 1;
            if (velocityX < 0) { extramoveX = -extramoveX; }
        }
        
        
        if (Math.random() < Math.abs(velocityY) - Math.abs((int)velocityY)) {
            extramoveY = 1;
            if (velocityY < 0) { extramoveY = -extramoveY; }
        }
        
        super.leftX += extramoveX;
        super.rightX += extramoveX;
        super.lowerY += extramoveY;
        super.upperY += extramoveY;
        
        super.leftX = constrain(super.leftX, 0, GameCourt.COURT_WIDTH - super.getWidth());
        super.rightX = constrain(super.rightX, super.getWidth(), GameCourt.COURT_WIDTH);
        super.upperY = constrain(super.upperY, super.getHeight(), GameCourt.COURT_HEIGHT);
        super.lowerY = constrain(super.lowerY, 0, GameCourt.COURT_HEIGHT - super.getHeight());
        
        super.centerX = (int)(super.rightX - super.leftX)/2 + super.leftX;
        super.centerY = (int)(super.upperY - super.lowerY)/2 + super.lowerY;
        
        if (super.leftX == 0 || super.rightX == GameCourt.COURT_WIDTH || super.lowerY == 0 || super.upperY == GameCourt.COURT_HEIGHT) {
            return true;
        }
        else{
            return false;
        }
       
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
