/**
 * Exhaust
 * (c) Antonio Menarde
 * @version 1.0, Apr 2016
 */

public interface MoveableObject {
    public boolean move();
    public double getXVelocity();
    public double getYVelocity();
    public void force(double x, double y);
    public void stop();
}
