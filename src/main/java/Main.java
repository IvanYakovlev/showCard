import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

   static HashMap<String, String> cardsMap = new HashMap();
    static int x1=11;
    static int y1=18;
    static int x2=15;
    static int y2=17;
    static int x3=1;
    static int y3=17;
    static int x4=19;
    static int y4=14;
    static int x5=19;
    static int y5=8;
    static int x6=21;
    static int y6=13;
    static int x7=4;
    static int y7=14;
    static int x8=13;
    static int y8=13;
    static int x9=11;
    static int y9=22;
    static int x10=2;
    static int y10=20;

    public static void main(String[] args) throws IOException {
        cardsMap.put("0001110100","2");
        cardsMap.put("0110111000","3");
        cardsMap.put("1110001100","4");
        cardsMap.put("1110110000","5");
        cardsMap.put("1010110000","6");
        cardsMap.put("0010100100","7");
        cardsMap.put("0100110000","8");
        cardsMap.put("1110110100","9");
        cardsMap.put("0010001101","10");
        cardsMap.put("0001111000","J");
        cardsMap.put("0110110001","Q");
        cardsMap.put("0100100101","K");
        cardsMap.put("0101001100","A");
        List<File> filesInFolder= new ArrayList<>();
        String pathStr= "C:\\Users\\Иван\\Desktop\\a";
        try (Stream<Path> paths = Files.walk(Paths.get(pathStr))) {
            filesInFolder = Files.walk(Paths.get(pathStr))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        }
        catch (Exception e){
            System.out.println(e);
        }
        for (File file: filesInFolder) {

            BufferedImage img = ImageIO.read(file);
    /*    BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.SCALE_DEFAULT);
        RescaleOp rescaleOp = new RescaleOp(2f, -250, null);
        rescaleOp.filter(image, img);*/
   /*     BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = img.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();*/




            List<BufferedImage> cardImages = new ArrayList<BufferedImage>();
            if (!getColor(new Color(img.getRGB(149, 592))).equals("black"))
            cardImages.add(img.getSubimage(149, 592, 23, 23));
            if (!getColor(new Color(img.getRGB(220, 592))).equals("black"))
            cardImages.add(img.getSubimage(220, 592, 23, 23));
            if (!getColor(new Color(img.getRGB(292, 592))).equals("black"))
            cardImages.add(img.getSubimage(292, 592, 23, 23));
            if (!getColor(new Color(img.getRGB(363, 592))).equals("black"))
            cardImages.add(img.getSubimage(363, 592, 23, 23));
            if (!getColor(new Color(img.getRGB(435, 592))).equals("black"))
            cardImages.add(img.getSubimage(435, 592, 23, 23));


            String nameCards = "";
            for (int k = 0; k < cardImages.size(); k++) {
                BufferedImage card = cardImages.get(k);


                int[][] arr = new int[card.getHeight()][card.getWidth()];
                for (int i = 0; i < card.getHeight(); i++) {
                    for (int j = 0; j < card.getWidth(); j++) {
                        if (getColor(new Color(card.getRGB(j, i))).equals("red") || getColor(new Color(card.getRGB(j, i))).equals("black")) {
                            arr[i][j] = 1;
                        } else {
                            arr[i][j] = 0;
                        }
                    }
                }

                Color suitPoint1 ;
                Color suitPoint2 ;

                switch (k){
                    case 0:{
                        suitPoint1 = new Color(img.getRGB(184, 638));
                        suitPoint2 = new Color(img.getRGB(175, 644));
                    }
                    break;
                    case 1:{
                        suitPoint1 = new Color(img.getRGB(256, 638));
                        suitPoint2 = new Color(img.getRGB(247, 644));
                    }
                    break;
                    case 2:{
                        suitPoint1 = new Color(img.getRGB(327, 638));
                        suitPoint2 = new Color(img.getRGB(318, 644));
                    }
                    break;
                    case 3:{
                        suitPoint1 = new Color(img.getRGB(399, 638));
                        suitPoint2 = new Color(img.getRGB(390, 644));
                    }
                    break;
                    case 4:{
                        suitPoint1 = new Color(img.getRGB(471, 638));
                        suitPoint2 = new Color(img.getRGB(462, 644));
                    }
                    break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + k);
                }

                String suit="";

                if (!(getColor(suitPoint1).equals("white") && getColor(suitPoint2).equals("white"))) {
                    if (getColor(suitPoint1).equals("white")) {
                        suit="h";
                    } else {
                        if (getColor(suitPoint2).equals("white")) {
                            suit="c";
                        } else if (getColor(suitPoint2).equals("red")) {
                            suit="d";
                        } else {
                            suit="s";
                        }
                    }
                }

                nameCards += equalsArray(arr) + suit+"";

            }

        /*    System.out.println(file.getName()+" - "+nameCards);
            JFrame myJFrame = new JFrame("Image pane");
            myJFrame.setSize(200, 200);
            myJFrame.setVisible(true);
            myJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            myJFrame.setContentPane(new ImagePanel(cardImages.get(0), cardImages.get(1), cardImages.get(2), cardImages.get(2), cardImages.get(2)));
*/
        }
    }
    public static String equalsArray(int[][] b){

 /*       for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 23; j++) {
                System.out.print(b[i][j]+", ");
            }
            System.out.println("");

        }
        System.out.println("//////////////////////////////////////////////////");*/

        String str="NULL";
        int errors= 0;
        double err=0;
        Map<int[][],String> map = Templates.getList();
        List<int[][]> keys = new ArrayList<int[][]>(map.keySet());
        for (int l=0;l<keys.size();l++){
            int[][]currArr = keys.get(l);
            for (int i = 0; i < 23; i++) {
                for (int j = 0; j < 23; j++) {
                    if (currArr[i][j]!=b[i][j])
                        errors++;
                        err+=(currArr[i][j]-b[i][j])^2;
                }
            }
            String CARD = map.get(currArr);
            if (errors<60){
                str=map.get(currArr);
                break;

            }
            errors=0;
        }

        return str;

    }
    public static String getColor(Color color){
        if ((color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255)||
                color.getRed() == 120 && color.getGreen() == 120 && color.getBlue() == 120) return "white";
        if ((color.getRed() == 205 && color.getGreen() == 73 && color.getBlue() == 73)||
                (color.getRed() == 96 && color.getGreen() == 34 && color.getBlue() == 34)) return "red";
        if ((color.getRed() == 16 && color.getGreen() == 16 && color.getBlue() == 18)||
                (color.getRed() == 35 && color.getGreen() == 35 && color.getBlue() == 38)) return "black";
        return "black";
    }
}
