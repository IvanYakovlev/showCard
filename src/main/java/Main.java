import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


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

        File file = new File("C:\\Users\\Иван\\Downloads\\java_test_task\\20180821_110443.013_0x1FE201D8.png");
        BufferedImage img = ImageIO.read(file);
    /*    BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.SCALE_DEFAULT);
        RescaleOp rescaleOp = new RescaleOp(2f, -250, null);
        rescaleOp.filter(image, img);*/
   /*     BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = img.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();*/


        Color mycolor1 = new Color(img.getRGB(184, 638));
        Color mycolor2 = new Color(img.getRGB(177, 645));

        if (!(getColor(mycolor1).equals("white")&&getColor(mycolor2).equals("white"))) {
            if (getColor(mycolor1).equals("white")) {
                System.out.println("Черви");
            } else {
                if (getColor(mycolor2).equals("white")) {
                    System.out.println("Крести");
                } else if (getColor(mycolor2).equals("red")) {
                    System.out.println("Бубны");
                } else {
                    System.out.println("Пики");
                }
            }
        }

        List<BufferedImage> cardImages = new ArrayList<BufferedImage>();

        cardImages.add(img.getSubimage(148, 592, 23, 23));
        cardImages.add(img.getSubimage(220, 592, 23, 23));
        cardImages.add(img.getSubimage(291, 592, 23, 23));
        cardImages.add(img.getSubimage(363, 592, 23, 23));
        cardImages.add(img.getSubimage(434, 592, 23, 23));



        String nameCards="";
        for (int k = 0; k < cardImages.size(); k++) {
            BufferedImage card = cardImages.get(k);


            int[][] arr = new int[card.getHeight()][card.getWidth()];
            for (int i = 0;i<card.getHeight();i++){
                for (int j=0;j<card.getWidth();j++)
                {
                    if (getColor(new Color(card.getRGB(j,i))).equals("red")||getColor(new Color(card.getRGB(j,i))).equals("black")){
                        arr[i][j]=1;
                    } else {
                        arr[i][j]=0;
                    }
                }
            }



                for (int i = 0;i<23;i++){
                    for (int j = 0;j<23;j++){
                        if (i==0||i==22){
                            arr[i][j]=9;
                        } else {
                            if (j<22){
                                if (arr[i][j]==0&&arr[i][j+1]==1){
                                    arr[i][j+1]=0;
                                    if (j<21)
                                        j++;
                                } else {
                                    if (arr[i][j]==1&&arr[i][j+1]==0) {
                                        arr[i][j] = 0;
                                        if (j < 21)
                                            j++;
                                    }
                                }
                            }
                        }
                    }
                }


            nameCards+=equalsArray(arr)+",";

        }

        System.out.println(nameCards);
        JFrame myJFrame = new JFrame("Image pane");
        myJFrame.setSize(200,200);
        myJFrame.setVisible(true);
        myJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myJFrame.setContentPane(new ImagePanel(cardImages.get(0),cardImages.get(1),cardImages.get(2),cardImages.get(3),cardImages.get(4)));


    }
    public static String equalsArray(int[][] b){

        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 23; j++) {
                System.out.print(b[i][j]+" ");
            }
            System.out.println("");

        }
        System.out.println("//////////////////////////////////////////////////");

        String currentValue =""+ b[x1][y1]+b[x2][y2]+b[x3][y3]+b[x4][y4]+b[x5][y5]+b[x6][y6]+b[x7][y7]+b[x8][y8]+b[x9][y9]+b[x10][y10];

        return cardsMap.get(currentValue);

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
