
public class SpaceShip extends SpaceObject implements MoveableObject{
        private static String filename = "swordfish.png";
        private double velocityX = 0;
        private double velocityY = 0;
        
    public SpaceShip (int leftX, int upperY) {
        super(leftX, upperY, filename);
    }
    
    public void move () {
        
        super.leftX += (int)velocityX;
        super.rightX += (int)velocityX;
        super.lowerY += (int)velocityY;
        super.upperY += (int)velocityY;
        
        int extramoveX = 0;
        int extramoveY = 0;
        
        
        
//      double chance = Math.pow(Math.pow(Math.abs(velocityX - (int)velocityX), 2) + Math.pow(Math.abs(velocityY - (int)velocityY), 2), 0.5);
//      if (Math.random() < chance) {   
//          if (Math.abs(velocityX) > Math.abs(velocityY)) { extramoveX = (int)(velocityX / velocityY); extramoveY = 1; }
//          else { extramoveY = (int)(velocityX / velocityY); extramoveX = 1; }
//          
//          if (velocityX < 0) { extramoveX -= 2*extramoveX; } else if (velocityX == 0) {} else { extramoveX++; }
//          if (velocityY < 0) { extramoveY -= 2*extramoveY; } else if (velocityY == 0) {} else { extramoveY++; }
//      }
      

        
        if (Math.random() < Math.abs(velocityX) - Math.abs((int)velocityX)) {
            extramoveX = 1;
            if (velocityX < 0) { extramoveX = -extramoveX; }
            //else { velocityX += extramoveX; }
        }
        
        
        if (Math.random() < Math.abs(velocityY) - Math.abs((int)velocityY)) {
            extramoveY = 1;
            if (velocityY < 0) { extramoveY = -extramoveY; }
            //else { velocityY += extramoveY; }
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
