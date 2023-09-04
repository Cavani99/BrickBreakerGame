import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BrickPanel extends JPanel implements ActionListener,MouseMotionListener, KeyListener {

    public MovingPlatform movePlatform=new MovingPlatform();

    public Ball ball=new Ball();
    boolean move_up,move_left,move_mid;
    private final int panelWidth=500;
    private final int panelHeight=500;
    private int totalBricks=28;

    private boolean play=false;
    private boolean start=false;
    private int ballSpeed=3;

    private final Timer timer;
    private BricksMap map;

    BrickPanel(){

        map=new BricksMap(4,7);
        this.setPreferredSize(new Dimension(panelWidth,panelHeight));
        this.setBackground(Color.YELLOW);
        this.setFocusable(true);
        SpringLayout layout=new SpringLayout();
        this.setLayout(layout);
        this.addMouseMotionListener(this);
        addKeyListener(this);


        timer=new Timer(3,this);

    }

    public void paintComponent(Graphics graphics){


        super.paintComponent(graphics);
        movePlatform.paintPlatform(graphics);
        ball.paintComponent(graphics);
        map.draw((Graphics2D)graphics);

        //to start
        if(!play  && ball.getyPos()<movePlatform.getyPos()){
            start=true;
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("serif",Font.BOLD,30));
            graphics.drawString("Press ENTER to Start!",150,350);


        }

        if(totalBricks<=0 && start ){
            play=false;
            graphics.setColor(Color.red);
            graphics.setFont(new Font("serif",Font.BOLD,25));
            graphics.drawString("Bravo!You Won!",170,300);

//            graphics.setFont(new Font("serif",Font.BOLD,22));
//            graphics.drawString("Press Enter to play again!",120,380);
        }

        //ball fall
        if(ball.getyPos()>movePlatform.getyPos()+movePlatform.getHeight()){

            play=false;
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("serif",Font.BOLD,30));
            graphics.drawString("Game Over!The Ball fell!",120,350);

            graphics.setFont(new Font("serif",Font.BOLD,20));
            graphics.drawString("Press Enter to Restart",130,390);
        }

        graphics.dispose();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();

        movePlatform.setxPos(x);
        if (movePlatform.getxPos() >= 350) {
            movePlatform.setxPos(350);
        } else
            movePlatform.setxPos(x);

        super.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    timer.start();
    if(play) {

    int x = ball.getxPos();
    int y = ball.getyPos();
    int w = ball.getWidth();
    int h = ball.getHeight();
    int xPlatform = movePlatform.getxPos();
    int yPlatform = movePlatform.getyPos();
    int hPlatform = movePlatform.getHeight();
    int wPlatform = movePlatform.getWidth();


    int PlatformMiddle = (wPlatform) / 2;
    int division=wPlatform/3;

    //move by X
    if (x > panelWidth - w) {
        move_left = true;
    }


    if (x <= 0) {
        move_left = false;
    }

    //move by Y
    if (y > panelHeight - h) {
        move_up = true;


    }
    if (y <= 0) {
        move_up = false;
    }


        if(new Rectangle(x,y,w,h).intersects(new Rectangle(xPlatform,yPlatform,wPlatform,hPlatform))){
            move_up = true;

            //LEFT
            if(new Rectangle(x,y,w,h).intersects(new Rectangle(xPlatform,yPlatform,wPlatform-PlatformMiddle-division,hPlatform))){
                move_left=true;
                move_mid=false;
            }
            //RIGHT
            else if(new Rectangle(x,y,w,h).intersects(new Rectangle(xPlatform+PlatformMiddle+division,yPlatform,wPlatform-PlatformMiddle-division,hPlatform))){
                move_left=false;
                move_mid=false;
            }else if(new Rectangle(x,y,w,h).intersects(new Rectangle(xPlatform+PlatformMiddle-division,yPlatform,wPlatform-PlatformMiddle-division,hPlatform))){
                move_mid=true;
            }
        }

        //contact with the Bricks
        A:for(int i=0;i<map.map.length;i++){
            for(int j=0;j<map.map[0].length;j++){
                if(map.map[i][j]>0){
                    int brickX=j*map.brickWidth+80;
                    int brickY=i*map.brickHeight+50;
                    int brickWidth=map.brickWidth;
                    int brickHeight=map.brickHeight;

                    Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                    Rectangle ballRect=new Rectangle(x,y,w,h);

                    if(ballRect.intersects(rect)){
                        map.setBrickValue(0,i,j);
                        totalBricks--;

                        movePlatform.setWidth(wPlatform-4);
                        if(totalBricks<20 && totalBricks>=10) ballSpeed=4;
                        else if(totalBricks<10 && totalBricks>=5) ballSpeed=6;
                        else if(totalBricks<5) ballSpeed=12;


                        if(x+w-1 <= rect.x || x+1>=rect.x+rect.width){
                            move_left= !move_left;
                        }else{
                            move_up= !move_up;
                        }
                        break A;
                    }
                }


            }
        }

    if (move_left && !move_mid) {
        x -= ballSpeed;
        ball.setxPos(x);
    } else if (!move_left && !move_mid) {
        x += ballSpeed;
        ball.setxPos(x);
    }
    if(move_mid){
        ball.setxPos(x);
    }



    if (move_up) {
        y -= ballSpeed;
        ball.setyPos(y);
    } else {
        y += ballSpeed;
        ball.setyPos(y);
    }


    if (y > movePlatform.getyPos() + movePlatform.getHeight() &&
            (x < movePlatform.getxPos() || x > movePlatform.getxPos() + movePlatform.getWidth())) {

        timer.stop();
     }
    }
        repaint();

    }



    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                play=true;
                ball.setxPos(200);
                ball.setyPos(250);
                ball.setWidth(30);
                ball.setHeight(30);
                ballSpeed=2;
                movePlatform.setxPos(170);
                movePlatform.setyPos(450);
                movePlatform.setWidth(150);
                //score=0;
                totalBricks=28;
                map=new BricksMap(4,7);
                timer.restart();

                repaint();
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}

