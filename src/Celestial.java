
public class Celestial extends SpaceObject{
    
    public static final double GRAVITY_COEF = 0.042;
    private double gravity;
    
    public Celestial (int leftX, int upperY, String filename, boolean positiveGravity) {
        super(leftX, upperY, filename);
        this.gravity = GRAVITY_COEF * (Math.pow(super.getWidth(), 3)) / 8;
        if (!positiveGravity) {
            this.gravity = -this.gravity;
        }
    }
    
    public double getGravity() { return gravity; }

}
