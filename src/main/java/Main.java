import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {

        ClassLoader classLoader = Main.class.getClassLoader();
        List<File> filesInFolder= new ArrayList<>();
        String pathStr= args[0];
        Properties prop = new Properties();

        try (Stream<Path> paths = Files.walk(Paths.get(pathStr))) {
            filesInFolder = paths.filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toList());
            prop.load(classLoader.getResourceAsStream("config.properties"));
        }
        catch (Exception e){
            System.out.println(e);
        }

        int cardHeight=Integer.parseInt((String) prop.get("cardHeight"));
        int cardWidth=Integer.parseInt((String) prop.get("cardWidth"));
        int suitHeight=Integer.parseInt((String) prop.get("suitHeight"));
        int suitWidth=Integer.parseInt((String) prop.get("suitWidth"));

        File cardFile = new File(classLoader.getResource("templates/card.txt").getFile());
        File suitFile = new File(classLoader.getResource("templates/suit.txt").getFile());

        Map<int[][], String> cardTemplateMap = getTemplateMap(cardFile, cardHeight, cardWidth);
        Map<int[][], String> suitTemplateMap = getTemplateMap(suitFile, suitHeight, suitWidth);

        for (File file: filesInFolder) {
            System.out.println(getCards(file,cardTemplateMap ,suitTemplateMap, cardHeight, cardWidth, suitHeight, suitWidth));
        }
    }
    //Метод читает из файла шаблона данные и преобразует их в Map
    public static Map<int[][], String> getTemplateMap(File cardFile, int h, int w) {

        Map<int[][], String> cardTemplateMap = new HashMap<>();
        int[][] cardArray = new int[h][w];
        try (BufferedReader reader = new BufferedReader(new FileReader(cardFile))){

            String line = reader.readLine();
            int i=0;
            while (line!=null){
                String[] arrStr = line.split(" ");
                if (arrStr.length>1){ //Если длина прочитанной из фала строки > 1 преобразуем в одномерный массив
                    int[] arr =  Arrays.asList(arrStr).stream().mapToInt(Integer::parseInt).toArray();
                    cardArray[i]=arr; //формируем двумерный массив из одномерных
                    i++;
                } else { //если длина > 1 то перед нами достоинство либо масть карты из шаблона
                    cardTemplateMap.put(cardArray,arrStr[0]); //записываем в map
                    cardArray=new int[h][w];
                    i=0;
                }
                line=reader.readLine();
            }
        }
        catch (IOException | NumberFormatException e)
        {
            e.printStackTrace();
        }
        return cardTemplateMap;
    }
    //Метод возвращает достоинства и масти карт из переданного файла
    public static String getCards(File file, Map<int[][],String> cardTemplate, Map<int[][],String> suitTemplate, int cardHeight,int cardWidth,int suitHeight,int suitWidth) {

        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String nameCards = "";
        if (img!=null) {
            List<BufferedImage> cardImages = new ArrayList<BufferedImage>();
            List<BufferedImage> suitImages = new ArrayList<BufferedImage>();
            if (getColor(new Color(img.getRGB(151, 588))).equals("white")) //если в указанной точке белый фон то перед нами карта
                cardImages.add(img.getSubimage(151, 593, cardWidth, cardHeight)); // задаем координаты достоинства карты
            suitImages.add(img.getSubimage(169, 641, suitWidth, suitHeight)); // задаем координаты масьи карты
            if (getColor(new Color(img.getRGB(223, 588))).equals("white"))
                cardImages.add(img.getSubimage(223, 593, cardWidth, cardHeight));
            suitImages.add(img.getSubimage(241, 641, suitWidth, suitHeight));
            if (getColor(new Color(img.getRGB(294, 588))).equals("white"))
                cardImages.add(img.getSubimage(294, 593, cardWidth, cardHeight));
            suitImages.add(img.getSubimage(312, 641, suitWidth, suitHeight));
            if (getColor(new Color(img.getRGB(366, 588))).equals("white"))
                cardImages.add(img.getSubimage(366, 593, cardWidth, cardHeight));
            suitImages.add(img.getSubimage(384, 641, suitWidth, suitHeight));
            if (getColor(new Color(img.getRGB(438, 588))).equals("white"))
                cardImages.add(img.getSubimage(438, 593, cardWidth, cardHeight));
            suitImages.add(img.getSubimage(456, 641, suitWidth, suitHeight));


            for (int k = 0; k < cardImages.size(); k++) {
                BufferedImage card = cardImages.get(k);
                BufferedImage suit = suitImages.get(k);

                nameCards += recognize(card, cardTemplate) + recognize(suit, suitTemplate);
            }
        }
        return file.getName()+" - "+(nameCards.equals("")?"Файл не распознан":nameCards);
    }
    //Метод возвращает наиболее похожее на img значение из шаблона
    private static String recognize(BufferedImage img, Map<int[][], String> map) {
        //преобразуем картинку в двумерный массив где 0 - фон 1 - значимый пиксель
        int[][] arr = new int[img.getHeight()][img.getWidth()];
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                if (getColor(new Color(img.getRGB(j, i))).equals("black")||getColor(new Color(img.getRGB(j, i))).equals("red")) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = 0;
                }
            }
        }

        String str="NULL";
        int error= 0;
        List<int[][]> keys = new ArrayList<int[][]>(map.keySet());
        Map<Integer, String> mapErrors = new HashMap<>();

        for (int l=0;l<keys.size();l++){
            int[][]currArr = keys.get(l);
            for (int i = 0; i < img.getHeight(); i++) {
                for (int j = 0; j < img.getWidth(); j++) {
                    //System.out.print(arr[i][j]+" ");  Использовалось для печати шаблонов
                    if (currArr[i][j]!=arr[i][j])
                        error++; // складываем ошибки полученные при сравнении массива из шаблона и  массива картинки из файла
                }
                //System.out.println("");  Использовалось для печати шаблонов
            }
            mapErrors.put(error,map.get(currArr));//кладем error в файл напротив значения из шаблона

            error=0;
        }
        str= mapErrors.get(Collections.min(mapErrors.keySet())); // Возвращаем значение из map с ошибками с наименьшим количеством ошибок, то есть наиболее схожее
        return str;
    }
    //метод возвращает цвет пикселя
    public static String getColor(Color color){

        if ((color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255)|| // если карта активна
                color.getRed() == 120 && color.getGreen() == 120 && color.getBlue() == 120) return "white"; // или если карта затемнена
        if ((color.getRed() == 205 && color.getGreen() == 73 && color.getBlue() == 73)||
                (color.getRed() == 96 && color.getGreen() == 34 && color.getBlue() == 34)) return "red";
        if ((color.getRed() == 16 && color.getGreen() == 16 && color.getBlue() == 18)||
                (color.getRed() == 35 && color.getGreen() == 35 && color.getBlue() == 38)) return "black";
        return "empty";
    }
}
