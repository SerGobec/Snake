package Content;

import javax.swing.*;
import java.awt.*;

public class PaintField extends JPanel {
    int x = 25;
    int y = 25;
    PaintField(){
        setLayout(null);
        setSize(500, 500);
        setLocation(0, 0);
        setOpaque(false);
    }
    public void paintComponent(Graphics g){
        Graphics2D graphics = (Graphics2D) g;
        g.setColor(Color.BLACK);
        for(int i = 0;i <= 15;i++){
            graphics.drawLine(25,y+30*i,475,y+30*i);
            graphics.drawLine(x + i*30,25,x+i*30,475);
        }
    }
}
