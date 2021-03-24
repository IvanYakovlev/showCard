import javax.swing.*;
import java.awt.*;

class ImagePanel extends JComponent {
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private Image image5;
    public ImagePanel(Image image1,Image image2,Image image3,Image image4,Image image5) {
        this.image1= image1;
        this.image2= image2;
        this.image3= image3;
        this.image4= image4;
        this.image5= image5;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image1, 0, 0, this);
        g.drawImage(image2, 30, 0, this);
        g.drawImage(image3, 60, 0, this);
        g.drawImage(image4, 90, 0, this);
        g.drawImage(image5, 120, 0, this);
    }
}