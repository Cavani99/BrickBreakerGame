import javax.swing.*;
import java.awt.*;

public class Ball extends JComponent {

     private int xPos=200;
     private int yPos=250;
     private int width=30;
     private int height=30;



    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        g2d.fillOval(getxPos(),getyPos(),getWidth(),getHeight());

        Toolkit.getDefaultToolkit().sync();
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
