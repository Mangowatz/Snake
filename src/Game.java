//created by Zachary Mankowitz (@Mangowatz) 2021-2022
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game
        implements KeyListener {


    private Snake player;
    private Food food;
    private AutoLogic autoLogic;
    private PointData pointData;
    private Graphics graphics;


    private int xDisplacement;
    private int yDisplacement;
    private int bodyXMax = 0;
    private int bodyXMin = 0;
    private int bodyYMax = 0;
    private int bodyYMin = 0;


    public static final int width = 30;
    public static final int height = 30;
    public static final int dimension = 20;

    private JFrame window;

    public Game() {

        window = new JFrame();
        player = new Snake();
        food = new Food(player);
        autoLogic = new AutoLogic(this);
        pointData = new PointData(this);
        graphics = new Graphics(this);



        window.add(graphics);

        window.setTitle("Snake");
        window.setSize(width * dimension + 2, height * dimension + dimension + 4);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void start() {
        graphics.state = "RUNNING";


    }

    private void restart() {
        //graphics.state = "START";
        this.window = new JFrame();
        this.player = new Snake();
        this.food = new Food(player);
        graphics = new Graphics(this);
        window.add(graphics);
        window.setVisible(true);

    }

    public void update() {


        if (graphics.state.equals("RUNNING")) {
            System.out.println("Head cords: "+player.getX()/dimension+", "+player.getY()/dimension + " "+player.getMove());
            System.out.println("Next move cords: "+player.getNextMove());
            if (checkFoodCollision()) {
                System.out.println("Food Collision");
                player.grow();
                food.randomSpawn(player);
            } else if (checkWallCollision()) {
                System.out.println("Wall Collision");
                graphics.state = "END";
            } else if (checkSelfCollision()) {
                System.out.println("Self Collision");
                graphics.state = "END";
            } else {
                /*averages score on 5 run test
                1 11.6
                2 8.2
                3 46
                */
                pointData.pointDataRefresh();
                autoLogic.v3(food.getX(), food.getY());
                autoLogic.v4(food.getX(), food.getY());


                //player.move(); //for manual
            }

            System.out.println();//blank line
        }

    }


    private boolean checkWallCollision() {
        return (player.getX() < 0 || player.getX() >= width * dimension) || player.getY() < 0 || player.getY() >= height * dimension;
    }

    private boolean checkFoodCollision() {
        return player.getX() == food.getX() * dimension && player.getY() == food.getY() * dimension;
    }

    private boolean checkSelfCollision() {
        for (int i = 1; i < player.getBody().size(); i++) {
            if (player.getX() == player.getBody().get(i).x && player.getY() == player.getBody().get(i).y) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (graphics.state.equals("RUNNING")) {

            if (keyCode == KeyEvent.VK_UP) {
                player.up();
                System.out.println("W");
            } else if (keyCode == KeyEvent.VK_DOWN) {
                player.down();
                System.out.println("S");
            } else if (keyCode == KeyEvent.VK_LEFT) {
                player.left();
                System.out.println("A");
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                player.right();
                System.out.println("D");
            }
        } else {
            this.start();
        }
    }


    public Snake getPlayer() {
        return player;
    }

    public void setPlayer(Snake player) {
        this.player = player;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public AutoLogic getAutoLogic(){return autoLogic;}

    public PointData getPointData(){return pointData;}

    public JFrame getWindow() {
        return window;
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }
    public Game getGame(){
    return this;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
