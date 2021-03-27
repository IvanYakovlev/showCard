import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<File> filesInFolder= new ArrayList<>();
        String pathStr= "C:\\Users\\Иван\\Desktop\\a";
        try (Stream<Path> paths = Files.walk(Paths.get(pathStr))) {
            filesInFolder = paths.filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toList());
        }
        catch (Exception e){
            System.out.println(e);
        }

        Map<int[][], String> cardMap= new HashMap<>();
        ClassLoader classLoader = Main.class.getClassLoader();
        File cardFile = new File(classLoader.getResource("card.txt").getFile());

        int[][] cardArray = new int[20][20];
        try (BufferedReader reader = new BufferedReader(new FileReader(cardFile)))
        {
          String line = reader.readLine();
          int i=0;
          while (line!=null){
              String[] arrStr = line.split(" ");

              if (arrStr.length>1){
                  int[] arr =  Arrays.asList(arrStr).stream().mapToInt(Integer::parseInt).toArray();
                  cardArray[i]=arr;
                  i++;
              } else {
                  cardMap.put(cardArray,arrStr[0]);
                  cardArray=new int[20][20];
                  i=0;
              }

              line=reader.readLine();
          }
        }
        catch (IOException | NumberFormatException e)
        {
            e.printStackTrace();
        }

        for (File file: filesInFolder) {
            System.out.println(getCards(file,cardMap));
        }
    }

    public static String getCards(File file, Map<int[][],String> cardMap) {

        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<BufferedImage> cardImages = new ArrayList<BufferedImage>();
        List<BufferedImage> suitImages = new ArrayList<BufferedImage>();
        if (getColor(new Color(img.getRGB(151, 588))).equals("white"))
            cardImages.add(img.getSubimage(151, 593, 20, 20));
            suitImages.add(img.getSubimage(176, 614, 15, 15));
        if (getColor(new Color(img.getRGB(223, 588))).equals("white"))
            cardImages.add(img.getSubimage(223, 593, 20, 20));
            suitImages.add(img.getSubimage(248, 614, 15, 15));
        if (getColor(new Color(img.getRGB(294, 588))).equals("white"))
            cardImages.add(img.getSubimage(294, 593, 20, 20));
            suitImages.add(img.getSubimage(319, 614, 15, 15));
        if (getColor(new Color(img.getRGB(366, 588))).equals("white"))
            cardImages.add(img.getSubimage(366, 593, 20, 20));
            suitImages.add(img.getSubimage(391, 614, 15, 15));
        if (getColor(new Color(img.getRGB(438, 588))).equals("white"))
            cardImages.add(img.getSubimage(438, 593, 20, 20));
            suitImages.add(img.getSubimage(463, 614, 15, 15));

        String nameCards = "";
        for (int k = 0; k < cardImages.size(); k++) {
            BufferedImage card = cardImages.get(k);

            int[][] cardArr = new int[card.getHeight()][card.getWidth()];
            for (int i = 0; i < card.getHeight(); i++) {
                for (int j = 0; j < card.getWidth(); j++) {
                    if (getColor(new Color(card.getRGB(j, i))).equals("black")||getColor(new Color(card.getRGB(j, i))).equals("red")) {
                        cardArr[i][j] = 1;
                    } else {
                        cardArr[i][j] = 0;
                    }
                }
            }

            Color suitPoint1 ;
            Color suitPoint2 ;

            switch (k){
                case 0:{
                    suitPoint1 = new Color(img.getRGB(184, 638));
                    suitPoint2 = new Color(img.getRGB(176, 645));
                }
                break;
                case 1:{
                    suitPoint1 = new Color(img.getRGB(256, 638));
                    suitPoint2 = new Color(img.getRGB(248, 644));
                }
                break;
                case 2:{
                    suitPoint1 = new Color(img.getRGB(327, 638));
                    suitPoint2 = new Color(img.getRGB(319, 644));
                }
                break;
                case 3:{
                    suitPoint1 = new Color(img.getRGB(399, 638));
                    suitPoint2 = new Color(img.getRGB(391, 644));
                }
                break;
                case 4:{
                    suitPoint1 = new Color(img.getRGB(471, 638));
                    suitPoint2 = new Color(img.getRGB(463, 644));
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

            nameCards += equalsArray(cardArr,cardMap) + suit+"";

        }

        return file.getName()+" - "+nameCards;
    }

    public static String equalsArray(int[][] b, Map<int[][], String> map){

/*        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(b[i][j]+", ");
            }
            System.out.println("");

        }
        System.out.println("//////////////////////////////////////////////////");*/

        String str="NULL";
        int error= 0;
        List<int[][]> keys = new ArrayList<int[][]>(map.keySet());
        Map<Integer, String> mapErrors = new HashMap<>();

        for (int l=0;l<keys.size();l++){
            int[][]currArr = keys.get(l);
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (currArr[i][j]!=b[i][j])
                        error++;
                }
            }
            String CARD = map.get(currArr);
            mapErrors.put(error,map.get(currArr));

            error=0;
        }
        str= mapErrors.get(Collections.min(mapErrors.keySet()));
        return str;

    }
    public static String getColor(Color color){
        if ((color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255)||
                color.getRed() == 120 && color.getGreen() == 120 && color.getBlue() == 120) return "white";
        if ((color.getRed() == 205 && color.getGreen() == 73 && color.getBlue() == 73)||
                (color.getRed() == 96 && color.getGreen() == 34 && color.getBlue() == 34)) return "red";
        if ((color.getRed() == 16 && color.getGreen() == 16 && color.getBlue() == 18)||
                (color.getRed() == 35 && color.getGreen() == 35 && color.getBlue() == 38)) return "black";
        return "empty";
    }
}
