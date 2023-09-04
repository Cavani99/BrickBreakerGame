import java.awt.*;


public class MovingPlatform  {

    private int xPos=170;
    private int yPos=450;
    private int width=150;
    private int height=13;


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

    public void paintPlatform(Graphics g){
        g.setColor(Color.white);
        g.fillRoundRect(xPos,yPos,width,height,5,5);
    }


}



