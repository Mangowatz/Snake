import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Graphics
    extends JPanel
    implements ActionListener{
        private Timer t = new Timer(10, this);
        public String state;

        private Snake s;
        private Food f;
        private Game game;
        private AutoLogic a;
        private int red, blue, green;
        private int gd = Game.dimension;
        private int w = Game.width;
        private int h = Game.height;



        public Graphics(Game g){
            t.start();
            state= "START";

            game = g;
            s = g.getPlayer();
            f = g.getFood();
            a = g.getAutoLogic();


            //add listener
            this.addKeyListener(g);
            this.setFocusable(true);
            this.setFocusTraversalKeysEnabled(false);


        }
        public void paintComponent(java.awt.Graphics g){
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.black);
            g2d.fillRect(0,0,Game.width*Game.dimension+5,Game.height*Game.dimension+5);
            g2d.setColor(Color.CYAN);
            //g2d.drawLine(game.getFood().getX()*Game.dimension,game.getFood().getY()*Game.dimension,
            //        game.getPlayer().getX(),game.getPlayer().getY());

            if(state.equals("START")){
                g2d.setColor(Color.white);
                g2d.drawString("Press any key", Game.width/2 * Game.dimension -40, Game.height/2*Game.dimension-20);

            }else if(state.equals("RUNNING")){
                //paint blocked squares
                for(int j =0; j<a.getPointData().size();j++){
                    if(j==0){
                        g2d.setColor(Color.CYAN);
                    }else if(a.getPointData().get(j).isBlocked()){//head
                        g2d.setColor(Color.red);
                    }else{
                        g2d.setColor(new Color(0,0,255/(a.getPointData().get(j).getI()+1),255));
                    }
                    //System.out.println("Coloring "+g2d.getPaint()+": "+ a.getPointData().get(j).x/gd+", "+a.getPointData().get(j).y/gd);
                    g2d.fillRect(a.getPointData().get(j).x, a.getPointData().get(j).y,gd,gd);
                }

                for(PointData pd : a.foodPath){
                    g2d.setColor(Color.MAGENTA);
                    g2d.fillRect(pd.getX()*gd,pd.getY()*gd,gd,gd);
                }
                g2d.setColor(Color.red);//food
                g2d.fillRect(f.getX()*Game.dimension,f.getY() * Game.dimension,Game.dimension,Game.dimension);

                for(Rectangle r:s.getBody()){
                    g2d.setColor(Color.GREEN);
                    g2d.fill(r);
                }

            }else{
                g2d.setColor(Color.LIGHT_GRAY);//food
                g2d.fillRect(f.getX()*Game.dimension,f.getY() * Game.dimension,Game.dimension,Game.dimension);

                red=0; blue=0; green=255;//snake
                for(Rectangle r : s.getBody()){
                    if(blue<245){
                        blue+=10;
                    }else if(green>10){
                        green-=10;
                    }else if(red<245){
                        red+=10;
                    }else{
                        red=0;
                        blue=0;
                        green = 255;
                    }
                    g2d.setColor(new Color(0,255,blue));
                    g2d.fill(r);
                }

                g2d.setColor(Color.white);
                g2d.drawString("Game Over \n Your Score: " + (s.getBody().size()-3), Game.width/2 * Game.dimension -40, Game.height/2*Game.dimension-20);
                g2d.drawString("next point: "+ s.getNextMove().x+", "+s.getNextMove().y , s.getNextMove().x * Game.dimension , s.getNextMove().y * Game.dimension);
            }

        }

        public String getState(){
            return state;
        }




    @Override
    public void actionPerformed(ActionEvent e) {
            repaint();
            game.update();
    }
}
