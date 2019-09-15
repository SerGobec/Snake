package Content;

import javax.swing.*;
import java.awt.*;

public class PaintSnake extends JPanel {
    SnakeAnim snakeAnim;
    Coord apple;
    PaintSnake(SnakeAnim s, Coord q){
        apple = q;
        snakeAnim = s;
        setLayout(null);
        setSize(500, 500);
        setLocation(25, 25);
        setOpaque(false);
    }
    public void paintComponent(Graphics g){
       Graphics2D gra = (Graphics2D) g;
       g.setColor(new Color(51,19, 89));
       g.fillOval(snakeAnim.list.get(0).x*30+2, snakeAnim.list.get(0).y*30+2,26,26 );
       if(snakeAnim.list.size() > 1) {
           g.setColor(Color.BLUE);
           for (int i = 1; i < snakeAnim.list.size(); i++) {
               g.fillOval(snakeAnim.list.get(i).x*30+2, snakeAnim.list.get(i).y*30+2,26,26 );
           };

       }
       //
       g.setColor(Color.red);
       g.fillOval(apple.x*30 + 2,apple.y*30+2,26,26);
    }

}
