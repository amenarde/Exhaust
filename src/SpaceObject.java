/**
 * Exhaust
 * (c) Antonio Menarde
 * @version 1.0, Apr 2016
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpaceObject implements Comparable<SpaceObject>{
    
    //specified by upper left corner
    private String filename;
    protected int leftX, rightX;
    protected int upperY, lowerY;
    private int width;
    private int height;
    protected int centerX;
    protected int centerY;
    private boolean[][] shape;
    private BufferedImage image;
    
    public SpaceObject(int leftX, int upperY, String filename){
        if (leftX < 0 || upperY < 0 || filename == null) {
            throw new IllegalArgumentException();
        }
        
        try {
            this.image = ImageIO.read(this.getClass().getResource(filename));
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
            throw new Error();
        }
        
        this.filename = filename;
        this.shape = imgToBool(image);
        this.height = shape.length;
        this.width = shape[0].length;
        this.leftX = leftX; this.rightX = leftX + width;
        this.upperY = upperY; this.lowerY = upperY - height;
        this.centerY = upperY - (height / 2);
        this.centerX = leftX + (width / 2);
    }
    
    public boolean intersects(SpaceObject obj){
        //completely inside
        if (this.leftX <= obj.getLeftX() && this.rightX >= obj.getRightX() && 
            this.upperY >= obj.getUpperY() && this.lowerY <= obj.getLowerY()) {
            return true;
        }
        //completely outside
        else if (obj.getLeftX() <= this.leftX && obj.getRightX() >= this.rightX && 
                obj.getUpperY() >= this.upperY && obj.getLowerY() <= this.lowerY) {
           return true;
        }
        //not intersecting at all
        else if (this.upperY < obj.getLowerY() || this.lowerY > obj.getUpperY() ||
                 this.rightX < obj.getLeftX()  || this.leftX > obj.getRightX()) {
            return false;
        }
        //check for conflicts within shared boundary
        else {
            int myi, myj, obji, objj, irange, jrange;
            if (this.upperY > obj.getUpperY()) {
                myi = this.upperY - obj.getUpperY();
                obji = 0;
            } else {
                myi = 0;
                obji = obj.getUpperY() - this.upperY;
            }
            
            if (this.leftX > obj.getLeftX()) {
                myj = 0;
                objj = this.leftX - obj.getLeftX();
            } else {
                myj = obj.getLeftX() - this.leftX;
                objj = 0;
            }
            
            irange = Math.min(this.upperY, obj.getUpperY()) - Math.max(this.lowerY, obj.getLowerY());
            jrange = Math.min(this.rightX, obj.getRightX()) - Math.max(this.leftX, obj.getLeftX());
            
            boolean[][] objShape = obj.getShape();
            
            for (int i = 0; i < irange; i++) {
                for (int j = 0; j < jrange; j++) {
                    if (shape[i + myi][j + myj] && objShape[i + obji][j + objj]) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(image, leftX, GameCourt.COURT_HEIGHT - upperY, width, height, null);
    }
    
    public int getLeftX() {return leftX;}
    public int getRightX() {return rightX;}
    public int getUpperY() {return upperY;}
    public int getLowerY() {return lowerY;}
    public boolean[][] getShape() {return shape;}
    public int getCenterX() {return centerX;}
    public int getCenterY() {return centerY;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public String getFilename() {return new String(this.filename);}
    
    public static boolean[][] imgToBool(BufferedImage image) {
        if (image.getType() != BufferedImage.TYPE_4BYTE_ABGR) {
            throw new IllegalArgumentException();
        }
        
        int width = image.getWidth();
        int height = image.getHeight();
        boolean[][] returnAr = new boolean[height][width];
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int alpha = (image.getRGB(j, i) >> 24) & 0x000000FF;
                if (alpha == 0) { returnAr[i][j] = false; } 
                else { returnAr[i][j] = true; }
            }
        }
        return returnAr;
        
    }
    
    @Override
    //used only for treeset sorting
    public int compareTo (SpaceObject obj) {
        if (filename.equals(obj.getFilename())) {
            if (this.upperY == obj.getUpperY()) {
                if (this.rightX == obj.getRightX()) {
                    return 0;
                }
                else {
                    return this.rightX - obj.getRightX();
                }
            }
            else {
                return this.upperY - obj.getUpperY();
            }
        }
        else {
            return this.filename.compareTo(obj.getFilename());
        }
    }
}
