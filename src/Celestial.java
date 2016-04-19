import java.awt.Color;

public class Celestial extends SpaceObject{
    private static String filename = "earthlike.png";
    private int gravity;
    
    public Celestial (int leftX, int upperY, int gravity) {
        super(leftX, upperY, filename);
        this.gravity = gravity;
    }
    
    public int getGravity() { return gravity; }

}
