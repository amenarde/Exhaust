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
        
        this.shape = shape;
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
        if (!inRange(obj.getLeftX(), obj.getRightX(), this.leftX, this.rightX) &&
            !inRange(obj.getUpperY(), obj.getLowerY(), this.upperY, this.lowerY)) {
            return false;
        } else {
            int myj = Math.max(obj.getLeftX(), this.leftX) - this.leftX;
            int myi = this.upperY - Math.min(obj.getUpperY(), this.upperY);
            
            int objj = Math.max(obj.getLeftX(), this.leftX) - obj.getLeftX();
            int obji = obj.getUpperY() - Math.min(obj.getUpperY(), this.upperY);
            
            System.out.println(this.lowerY);
            
            for(int i = 0; i < Math.min(obj.getUpperY(), this.upperY) - Math.max(obj.getLowerY(), this.lowerY); ) {
                for(int j = 0; j < Math.min(obj.getRightX(), this.rightX) - Math.max(obj.getLeftX(), this.leftX); ) {
                    try{
                    boolean[][] objshape = obj.getShape();
                    if(this.shape[myi][myj] && objshape[obji][objj]) {
                        return true;
                    }
                    }
                    catch (Exception e) {
                        System.out.println("myi myj: " + myi + " " + myj);
                        System.out.println("obji objj: " + obji + " " + objj); // + obj.shape.length + " " + obj.shape[0].length
                        throw new NullPointerException();
                    }
                    myj++; objj++; j++;
                }
                myi++; obji++; i++;
            }
        }
        return false;
    }
    
    public void draw(Graphics g) {
        g.drawImage(image, leftX, upperY, width, height, null);
    }
    
    public static boolean[][] imgToBool(BufferedImage image) {
        if (image.getType() != BufferedImage.TYPE_INT_ARGB) {
            throw new IllegalArgumentException();
        }
        
        int width = image.getWidth();
        int height = image.getHeight();
        boolean[][] returnAr = new boolean[height][width];
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; i < width; j++) {
                int alpha = (image.getRGB(j, i) >> 24) & 0x000000FF;
                if (alpha == 0) { returnAr[i][j] = false; } 
                else { returnAr[i][j] = true; }
            }
        }
        return returnAr; //TODO
        
    }
}
