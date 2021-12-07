import java.awt.*;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Snake {

    private int w = Game.width;
    private int h = Game.height;
    private int d = Game.dimension;


    private String move;//nothing up down left rihgt

    private ArrayList<Rectangle> body;

    public Snake(){
        body = new ArrayList<>();

        Rectangle temp = new Rectangle(Game.dimension,Game.dimension);
        temp.setLocation(Game.width/2*Game.dimension,Game.height/2 *Game.dimension);
        body.add(temp);

        temp = new Rectangle(d,d);
        temp.setLocation((w/2 - 1)*d,(h/2)*d);
        body.add(temp);

        temp = new Rectangle(d,d);
        temp.setLocation((w/2 - 2)*d,(h/2)*d);
        body.add(temp);

        move = "NOTHING";
    }
        public void move(){
            if(!move.equals("NOTHING")){
                Rectangle first = body.get(0);
                Rectangle temp = new Rectangle(Game.dimension,Game.dimension);

                if(move.equals("UP")) {
                    temp.setLocation(first.x, first.y - Game.dimension);
                }else if(move.equals("DOWN")){
                    temp.setLocation(first.x, first.y + Game.dimension);
                }else if(move.equals("LEFT")){
                    temp.setLocation(first.x-Game.dimension, first.y);
                }else{//rihgt
                    temp.setLocation(first.x+Game.dimension, first.y);
                }
                body.add(0,temp);
                body.remove(body.size()-1);

            }
        }
        public void grow(){
            Rectangle first = body.get(0);
            Rectangle second = body.get(1);
            Rectangle temp = new Rectangle(Game.dimension,Game.dimension);

            if(move.equals("UP")) {
                temp.setLocation(first.x, first.y - Game.dimension);
            }else if(move.equals("DOWN")){
                temp.setLocation(first.x, first.y + Game.dimension);
            }else if(move.equals("LEFT")){
                temp.setLocation(first.x-Game.dimension, first.y);
            }else if(move.equals("RIGHT")){//rihgt
                temp.setLocation(first.x+Game.dimension, first.y);
            }
            temp.setLocation(second.x, second.y);
            body.add(1,temp);
            System.out.println("Snake Grown to "+body.size());
        }


        public ArrayList<Rectangle> getBody(){
            return body;
        }
        public void setBody(ArrayList<Rectangle> body){
            this.body = body;
        }

        public int getX(){
        return body.get(0).x;
        }
        public int getX(int i){
        return body.get(i).x;
        }

        public int getY(){
        return body.get(0).y;
        }
    public int getY(int i){
        return body.get(i).y/20;
    }

        public void up() { if(!move.equals("DOWN")) move = "UP"; }
        public void down() {
            if(!move.equals("UP")) move = "DOWN";
        }
        public void left() {
            if(!move.equals("RIGHT"))move = "LEFT";
        }
        public void right() {
        if(!move.equals("LEFT"))move = "RIGHT";
        }

    public void forceUp() { move = "UP"; }
    public void forceDown() {
        move = "DOWN";
    }
    public void forceLeft() {
        move = "LEFT";
    }
    public void forceRight() {
        move = "RIGHT";
    }

        public String getMove(){
        return move;
    }
        public Point getNextMove(){
        if(move.equals("RIGHT")){
            return new Point(getX()/d+1,getY()/d);
        }else if(move.equals("LEFT")){
            return new Point(getX()/d-1,getY()/d);
        }else if(move.equals("UP")){
            return new Point(getX()/d,getY()/d-1);
        }else if(move.equals("DOWN")){
            return new Point(getX()/d,getY()/d+1);
        }
        return new Point(getX()/d,getY()/d);//move NOTHING
        }





}
