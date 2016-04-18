/**
 * Antonio Menarde
 * Space @version 0.1, 
 * 
 * Credit: Base Structure
 *         CIS 120 Game HW
 *         (c) University of Pennsylvania
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class SpaceObject{
    
    //specified by upper left corner
    protected int leftX, rightX;
    protected int upperY, lowerY;
    private int width;
    private int height;
    protected int centerX;
    protected int centerY;
    private boolean[][] shape;
    private BufferedImage image;
    
    private Color color;
    
    public int getLeftX() {return leftX;}
    public int getRightX() {return rightX;}
    public int getUpperY() {return upperY;}
    public int getLowerY() {return lowerY;}
    public boolean[][] getShape() {return shape;}
    public int getCenterX() {return centerX;}
    public int getCenterY() {return centerY;}
    
    public SpaceObject(int leftX, int upperY, boolean[][] shape, Color color){
        if (leftX < 0 || upperY < 0 || shape.length == 0 || color == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < shape.length; i++) {
            if (shape[i].length != shape[0].length) {
                throw new IllegalArgumentException();
            }
        }
        
        this.color = color;
        this.height = shape.length;
        this.width = shape[0].length;
        this.leftX = leftX; this.rightX = leftX + width;
        this.upperY = upperY; this.lowerY = upperY - height;
        this.centerY = upperY - (height / 2);
        this.centerX = leftX + (width / 2);
        
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[j][i]) {
                    image.setRGB(i, j, color.getAlpha()<<24 | this.color.getRGB());
                }
                else{
                    image.setRGB(i, j, (0<<24) | (0<<16) | (0<<8) | 0);
                }
            }
        }
    }
    
    private boolean inRange (int x1, int x2, int a, int b) {
        return ((x1 <= b) && (x1 >= a)) ||  ((x2 <= b) && (x2 >= a));
    }
    
    public boolean intersects(SpaceObject obj){
        if (!inRange(obj.leftX, obj.rightX, this.leftX, this.rightX) &&
            !inRange(obj.upperY, obj.lowerY, this.lowerY, this.upperY)) {
            return false;
        } else {
            int myi = Math.max(obj.leftX, this.leftX) - this.leftX;
            int myj = Math.max(obj.lowerY, this.lowerY) - this.upperY;
            
            int obji = Math.max(obj.leftX, this.leftX) - obj.leftX;
            int objj = Math.max(obj.lowerY, this.lowerY) - this.upperY;
            
            for(int i = 0; i < Math.min(obj.rightX, this.rightX) - Math.max(obj.leftX, this.leftX); ) {
                for(int j = 0; j < Math.max(obj.upperY, this.upperY) - Math.min(obj.lowerY, this.lowerY); ) {
                    if(this.shape[myi][myj] && obj.shape[obji][objj]) {
                        return true;
                    }
                    myj++; objj++;
                }
                myi++; obji++;
            }
        }
        return false;
    }
    
    public void draw(Graphics g) {
        g.drawImage(image, leftX, upperY, width, height, null);
    }
}
