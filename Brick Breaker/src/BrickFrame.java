import javax.swing.*;

public class BrickFrame extends JFrame{

    BrickFrame(){
        this.add(new BrickPanel());
        this.setTitle("Brick Breaker Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);

    }


}
