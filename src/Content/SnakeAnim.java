package Content;

import java.util.ArrayList;

public class SnakeAnim {
    public int go_x;
    public int go_y;
    public int lenght;
    public ArrayList<Coord> list;
    boolean up = false;
    Coord last_go;

    SnakeAnim(){
        go_x = 0;
        go_y = 1;
        lenght = 2;
        list = new ArrayList<Coord>();
        list.add(new Coord(2,2));
        list.add(new Coord(2,1));
        last_go = new Coord(go_x,go_y);
    }

    public void play(){
        int x = list.get(0).x + go_x;
        int y = list.get(0).y + go_y;
        if(up){
            list.add(new Coord(0,0));
            lenght++;
            up = false;
        }
        for(int i = lenght-2;i >= 0;i--){
            Coord q = list.get(i);
            list.set(i+1,q);
        };
        list.set(0, new Coord(x,y));
    }
    void check_apple(Coord q){
        if(q.x == list.get(0).x && q.y == list.get(0).y){
            up = true;

            int a = (int) (Math.random() * 14);
            int b = (int) (Math.random() * 14);

            for(int i = 0;i < lenght;i++){
                if(a == list.get(i).x && b == list.get(i).y){
                    a = (int) (Math.random()*14);
                    b = (int) (Math.random()*14);
                    i = 0;
                }
            }
            q.x = a;
            q.y = b;

        }
    };

    void changeLast(int x,int y){
        last_go.x = x;
        last_go.y = y;
    }

    boolean check_snake(){
        for(int i = 1;i < lenght;i++){
            if(list.get(0).x == list.get(i).x && list.get(0).y == list.get(i).y){
                return false;
            }
        }
        return true;
    };
    boolean check_field(){
        if(list.get(0).x < 0 || list.get(0).x > 14 || list.get(0).y < 0 || list.get(0).y > 14)return false;
        return true;
    }

    public void change_x_y(int x,int y){
        go_x = x;
        go_y = y;
    }


}
