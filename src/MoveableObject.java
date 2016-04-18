
public interface MoveableObject {
    public void move();
    public int getXVelocity();
    public int getYVelocity();
    public void force(int x, int y);
    public void stop();
}
