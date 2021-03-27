import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

class MainTest {

    @Test
    void cardsAll() {
        ClassLoader classLoader = Main.class.getClassLoader();
        BufferedReader cardBufferedReader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("templates/card.txt")));
        BufferedReader suitBufferedReader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("templates/suit.txt")));
        Map<int[][], String> cardTemplateMap = Main.getTemplateMap(cardBufferedReader, 20,20);
        Map<int[][], String> suitTemplateMap = Main.getTemplateMap(suitBufferedReader, 24,30);

        Properties prop = new Properties();
        try {
            prop.load(classLoader.getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int cardHeight=Integer.parseInt((String) prop.get("cardHeight"));
        int cardWidth=Integer.parseInt((String) prop.get("cardWidth"));
        int suitHeight=Integer.parseInt((String) prop.get("suitHeight"));
        int suitWidth=Integer.parseInt((String) prop.get("suitWidth"));

        String val1= Main.getCards(new File("src/test/resources/20180821_064009.827_0x1934025E.png"),
                cardTemplateMap, suitTemplateMap, cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_064009.827_0x1934025E.png", val1.equals("20180821_064009.827_0x1934025E.png - JsAh10sQsKc"));

        String val2= Main.getCards(new File("src/test/resources/20180821_084134.865_0x240C023E.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_084134.865_0x240C023E.png", val2.equals("20180821_084134.865_0x240C023E.png - Kh3d2d10c"));

        String val3= Main.getCards(new File("src/test/resources/20180821_110443.013_0x1FE201D8.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_110443.013_0x1FE201D8.png", val3.equals("20180821_110443.013_0x1FE201D8.png - 6cKc2c8s8h"));

        String val4= Main.getCards(new File("src/test/resources/20180821_113501.953_0x1CFF023A.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_113501.953_0x1CFF023A.png", val4.equals("20180821_113501.953_0x1CFF023A.png - 3h9h5d"));

        String val5= Main.getCards(new File("src/test/resources/20180821_120619.905_0x1FE201D8.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_120619.905_0x1FE201D8.png", val5.equals("20180821_120619.905_0x1FE201D8.png - 4s4h7hKc"));

        String val6= Main.getCards(new File("src/test/resources/kingAceTen/20180821_093622.029_0x0EDA02B0.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_093622.029_0x0EDA02B0.png", val6.equals("20180821_093622.029_0x0EDA02B0.png - 10c3s9s"));

        String val7= Main.getCards(new File("src/test/resources/kingAceTen/20180821_093714.230_0x1FE201D8.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_093714.230_0x1FE201D8.png", val7.equals("20180821_093714.230_0x1FE201D8.png - Qs10h9s"));

        String val8= Main.getCards(new File("src/test/resources/kingAceTen/20180821_093736.244_0x1FE201D8.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_093736.244_0x1FE201D8.png", val8.equals("20180821_093736.244_0x1FE201D8.png - 10sAc10d"));

        String val9= Main.getCards(new File("src/test/resources/kingAceTen/20180821_094236.839_0x1FE201D8.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_094236.839_0x1FE201D8.png", val9.equals("20180821_094236.839_0x1FE201D8.png - AhAdQc"));

        String val10= Main.getCards(new File("src/test/resources/kingAceTen/20180821_094732.846_0x1FE201D8.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_094732.846_0x1FE201D8.png", val10.equals("20180821_094732.846_0x1FE201D8.png - Jd10dAc"));

        String val11= Main.getCards(new File("src/test/resources/kingAceTen/20180821_095616.438_0x1FE201D8.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_095616.438_0x1FE201D8.png", val11.equals("20180821_095616.438_0x1FE201D8.png - 5sKcKs"));

        String val12= Main.getCards(new File("src/test/resources/kingAceTen/20180821_095755.396_0x0EDA02B0.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_095755.396_0x0EDA02B0.png", val12.equals("20180821_095755.396_0x0EDA02B0.png - 5dKhAs"));

        String val13= Main.getCards(new File("src/test/resources/kingAceTen/20180821_110027.430_0x1FE201D8.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_110027.430_0x1FE201D8.png", val13.equals("20180821_110027.430_0x1FE201D8.png - 8s10h8dAs"));

        String val14= Main.getCards(new File("src/test/resources/kingAceTen/20180821_120241.032_0x1CFF023A.png"),
                cardTemplateMap, suitTemplateMap,cardHeight,cardWidth,suitHeight,suitWidth);
        Assert.assertTrue("20180821_120241.032_0x1CFF023A.png", val14.equals("20180821_120241.032_0x1CFF023A.png - 4sJdKdKc"));
    }

}