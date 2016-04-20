
public interface MoveableObject {
    public void move();
    public double getXVelocity();
    public double getYVelocity();
    public void force(double x, double y);
    public void stop();
}
