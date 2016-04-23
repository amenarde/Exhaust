import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test {
      public static void main (String[] args) {
//        BufferedImage img;
//        try {
//            img = ImageIO.read(new File("earthlike.png"));
//            System.out.print(img.getType());
//            
//            boolean[][] bools = SpaceObject.imgToBool(img);
//            
//            for (int i = 0; i < bools.length; i++) {
//                for (int j = 0; j < bools[0].length; j++) {
//                    if (bools[i][j]) {
//                        System.out.print("1 ");
//                    }
//                    else { System.out.print("0 "); }
//                }
//                System.out.println();
//            }
//        } catch (IOException e) {
//            System.out.println("Internal Error:" + e.getMessage());
//        }
        
        if (Math.random() < 0.5) {
            System.out.println("heads");
        }
    }
}
