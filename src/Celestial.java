import java.awt.Color;

public class Celestial extends SpaceObject{
    private final static boolean[][] shape =   {{true, true, true, true, true, true, true, true, true, true},
                                                {true, true, true, true, true, true, true, true, true, true},
                                                {true, true, true, true, true, true, true, true, true, true},
                                                {true, true, true, true, true, true, true, true, true, true},
                                                {true, true, true, true, true, true, true, true, true, true},
                                                {true, true, true, true, true, true, true, true, true, true},
                                                {true, true, true, true, true, true, true, true, true, true},
                                                {true, true, true, true, true, true, true, true, true, true},
                                                {true, true, true, true, true, true, true, true, true, true},
                                                {true, true, true, true, true, true, true, true, true, true}};
    private int gravity;
    
    public Celestial (int leftX, int upperY, Color color, int gravity) {
        super(leftX, upperY, shape, color);
        this.gravity = gravity;
    }
    
    public int getGravity() { return gravity; }

}
