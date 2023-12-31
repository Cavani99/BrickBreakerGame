import java.awt.Graphics2D;
import java.awt.*;

 public class BricksMap {
     public int[][] map;
     public int brickWidth;
     public int brickHeight;
     public int xSpace=40;
     public int ySpace=50;

     public BricksMap(int row,int col){
         map=new int[row][col];

         //make it visible if 1
         for(int i=0;i<map.length;i++){
             for(int j=0;j<map[0].length;j++){
                 map[i][j]=1;

             }
         }
         brickWidth=400/col;
         brickHeight=150/row;

     }

     public  void draw(Graphics2D g){

         for(int i=0;i<map.length;i++){
             for(int j=0;j<map[0].length;j++){
                 if(map[i][j]>0){
                   g.setColor(Color.orange);
                   g.fillRect(j*brickWidth+xSpace,i*brickHeight+ySpace,brickWidth,brickHeight);


                   //border
                     g.setStroke(new BasicStroke(3));
                     g.setColor(Color.black);
                     g.drawRect(j*brickWidth+xSpace,i*brickHeight+ySpace,brickWidth,brickHeight);
                 }

             }
         }

     }

    public void setBrickValue(int value,int row,int col){
         map[row][col]=value;
    }

     public int getxSpace() {
         return xSpace;
     }
     public int getySpace() {
         return ySpace;
     }

 }
