package Content;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class MainGame extends JFrame implements KeyListener{
    public JPanel main_panel;
    public Menu menu;
    public GameField gameField;
    public SnakeAnim snakeAnim;
    public PaintField paintfield;
    public PaintSnake paintSnake;
    public Coord apple;
    public int quic = 100;
    Thread game;
    Result rez;
    boolean go;

    MainGame(){
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setSize(600,700);
      setLocation(400,200);
      main_panel = new JPanel();

      main_panel.setBackground(Color.orange);
      menu = new Menu();
      main_panel.add(menu);
      main_panel.setLayout(null);
      setContentPane(main_panel);
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      setVisible(true);
    };
    public static void main(String[] args){
        MainGame mainGame = new MainGame();
    }
    ///
    class Result extends JPanel{
      JLabel rezul;
      JButton go_to_menu;
      Result(int point){
          setLayout(null);
          setSize(500,500);
          setLocation(48,48);
          setBorder(new LineBorder(new Color(156,196,69), 2));
          setBackground(new Color(252,228,86));
          ///
          rezul = new JLabel();
          rezul.setSize(400,200);
          rezul.setLocation(25,20);
          rezul.setText("Yout result: " + point + " points!");
          add(rezul);
          ///
          go_to_menu = new JButton();
          go_to_menu.setSize(100,50);
          go_to_menu.setLocation(200,250);
          go_to_menu.setText("MENU");
          go_to_menu.addActionListener(new ListenerMENU());
          add(go_to_menu);
      };
    };
    ///
    class Menu extends JPanel{
        JButton start;
        JButton exit;
        Menu(){
            setLayout(null);
            setSize(500,500);
            setLocation(48,48);
            setBorder(new LineBorder(Color.BLUE, 2));
            setBackground(new Color(238, 89,94));
            //

            start = new JButton("START");
            Font BigFontTR = new Font("TimesRoman", Font.BOLD, 30);//Тут все про шрифт)
            start.setFont(BigFontTR);
          //  start.set;
            start.setSize(300,100);
            start.setLocation(100,100);
            start.setBackground(Color.BLUE);
            start.addActionListener(new ListenerSTART());
            add(start);
            //
            exit = new JButton("EXIT");
            exit.setSize(300,100);
            exit.setLocation(100,300);
            exit.setFont(BigFontTR);
            exit.setBackground(new Color(89,0, 15));
            exit.addActionListener(new ListenerExit());
            add(exit);
            //

            //
        }

    };
    ///
    class GameField extends JPanel {

        GameField() {

            int a = (int) (Math.random()*14);
            int b = (int) (Math.random()*14);
            apple = new Coord(a,b);
            setLayout(null);
            setSize(500, 500);
            setLocation(48, 48);
            setBorder(new LineBorder(Color.RED, 2));
            setBackground(Color.GREEN);
            paintfield = new PaintField();
            add(paintfield);
            snakeAnim = new SnakeAnim();
            paintSnake = new PaintSnake(snakeAnim, apple);
            //
            JPanel pan = new JPanel();
            pan.setLocation(0,0);
            pan.setSize(2,2);

            add(paintSnake);
            add(pan);
            game = new Thread(new game_go());
            go = true;
            game.start();
        }


    };

    class game_go implements Runnable{
        @Override
        public void run() {
            try {
                while (true) {
                    go = true;
                    snakeAnim.changeLast(snakeAnim.go_x, snakeAnim.go_y);
                    snakeAnim.play();
                    gameField.remove(paintSnake);
                    paintSnake = new PaintSnake(snakeAnim, apple);
                    gameField.add(paintSnake);
                    snakeAnim.check_apple(apple);

                    if(!snakeAnim.check_snake() || !snakeAnim.check_field()){
                        Thread.sleep(1000);
                        rez = new Result(snakeAnim.lenght - 2);
                        main_panel.remove(gameField);

                        main_panel.add(rez);
                        main_panel.repaint();
                        break;
                    }

                    gameField.repaint();

                    Thread.sleep(quic);
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    ///
    class ListenerMENU implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            main_panel.remove(rez);
            main_panel.add(menu);
            main_panel.repaint();
        }
    }
    class ListenerSTART implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            main_panel.remove(menu);
            gameField = new GameField();
            main_panel.add(gameField);
            main_panel.repaint();
        }
    }
    class ListenerExit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    ///
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(go) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && snakeAnim.last_go.x != -1)
                snakeAnim.change_x_y(1, 0);
            else if (e.getKeyCode() == KeyEvent.VK_LEFT && snakeAnim.last_go.x != 1)
                snakeAnim.change_x_y(-1, 0);
            else if (e.getKeyCode() == KeyEvent.VK_DOWN && snakeAnim.last_go.y != -1)
                snakeAnim.change_x_y(0, 1);
            else if (e.getKeyCode() == KeyEvent.VK_UP && snakeAnim.last_go.y != 1)
                snakeAnim.change_x_y(0, -1);
        go = false;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
