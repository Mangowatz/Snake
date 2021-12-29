import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//created by Zachary Mankowitz (@Mangowatz) 2021-2022
public class AutoLogic{

    private Snake player;
    private Game game;

    private int xDisplacement;
    private int yDisplacement;
    private int bodyXMax = 0;
    private int bodyXMin = 0;
    private int bodyYMax = 0;
    private int bodyYMin = 0;
    ArrayList<PointData> dataPoint = new ArrayList<PointData>(); //contain point data
    ArrayList<PointData> dataPointBlocked = new ArrayList<PointData>(); //contain point data
    String[] moves; //contain all moves snake needs to make


    public static final int width = 30;
    public static final int height = 30;
    public static final int dimension = 20;

    public AutoLogic(Game g){
        game = g;
        player = game.getPlayer();
    }



    /*
    @autoLogic
    This method takes into account the snake head positions and food position.
    Based on these values, the snake will move towards the target food position

    current issue is when moving opposite direction

    I am discontinuing this because I am trying new algorithm that will work on solving a path before acting.

     */
    public void v1() {
        System.out.println("Current move: " + player.getMove() + ", snake position: " + game.getPlayer().getX() / 20 + ", " + game.getPlayer().getY() / 20);
        //auto x axis
        if (!(game.getFood().getX() == game.getPlayer().getX() / 20)/*&&player.getMove().equals("RIGHT")||player.getMove().equals("LEFT")*/) {
            if (game.getFood().getX() > game.getPlayer().getX() / 20) {
                System.out.println("Go right");
                player.right();
            } else if (game.getFood().getX() < game.getPlayer().getX() / 20) {//food to the left
                System.out.println("Go left");
                player.left();
            }
        }
        //auto y axis
        if (!(game.getFood().getY() == game.getPlayer().getY() / 20)/*&&player.getMove().equals("UP")||player.getMove().equals("DOWN")*/) {
            if (game.getFood().getY() < game.getPlayer().getY() / 20) {//food to the left
                System.out.println("Go up");
                player.up();
            } else if (game.getFood().getY() > game.getPlayer().getY() / 20) {
                System.out.println("Go down");
                player.down();
            }
        }
        //edge case: if same row w/ direction opposite food, go up/down
        if (game.getFood().getY() == game.getPlayer().getY() / 20
                && ((game.getFood().getX() < game.getPlayer().getX() / 20 && game.getPlayer().getMove().equals("RIGHT"))
                || (game.getFood().getX() > game.getPlayer().getX() / 20 && game.getPlayer().getMove().equals("LEFT")))) {
            System.out.println("Edge case X axis");
            player.down();
            if (player.getY() / 20 >= 30) {
                player.up();
            }

        } else if (game.getFood().getX() == game.getPlayer().getX() / 20
                && ((game.getFood().getY() < game.getPlayer().getY() / 20 && game.getPlayer().getMove().equals("DOWN"))
                || (game.getFood().getY() > game.getPlayer().getY() / 20 && game.getPlayer().getMove().equals("UP")))) {
            System.out.println("Edge case Y axis");
            player.right();
            if (player.getX() / 20 >= 30) {
                player.left();
            }
        }
        player.move();
    }



    /*
    autoLogicV2
    @param node will get the x cords of food
    I want to get the driving directions to the node.
    this is about 30 lines shorter than previous one and scores higher overall
     */
    public void v2(int x, int y) {

        //get how much the snake needs to move
        xDisplacement = x - player.getX() / 20;
        yDisplacement = player.getY() / 20 - y;

        if (xDisplacement != 0) {
            if (xDisplacement < 0) {
                player.left();
            } else {
                player.right();
            }
            xDisplacement--;
        }
        if (yDisplacement != 0) {
            if (yDisplacement < 0) {
                player.down();
            } else {
                player.up();
            }

            yDisplacement--;
        }

        player.move();
    }


    /*
    autoLogicV3
    Same navigation as V2 except immediate obstacle avoidance
    current issue is finding a balance between obstacle avoidance and progressing to food
     */
    public void v3(int x, int y) {

        //get how much the snake needs to move
        xDisplacement = x - player.getX() / 20;
        yDisplacement = player.getY() / 20 - y;

        if (xDisplacement != 0) {
            if (xDisplacement < 0) {
                player.left();
            } else {
                player.right();
            }
            xDisplacement--;
        } else if (yDisplacement != 0) {
            if (yDisplacement < 0) {
                player.down();
            } else {
                player.up();
            }

            yDisplacement--;
        }


        //collision detection
        //Interesting to note that it is checking where the snake wants to go, not where it is going
        int count = 0;
        while (checkCollision(player.getNextMove().x, player.getNextMove().y) && count < 10) {
            count++;
            System.out.println("Next piont: " + player.getNextMove().x + ", " + player.getNextMove().y);
            System.out.print("eminent collision: ");

            switch (player.getMove()) {
                case "UP" -> {
                    System.out.println("UP");
                    player.left();
                    if (checkCollision(player.getNextMove().x, player.getNextMove().y)) {
                        player.forceRight();
                        System.out.println("reassessed: right. Verif: " + player.getMove());
                    }
                }

                case "RIGHT" -> {
                    System.out.println("RIGHT");
                    player.down();
                    if (checkCollision(player.getNextMove().x, player.getNextMove().y)) {
                        player.forceUp();
                        System.out.println("reassessed: up. Verif: " + player.getMove());
                    }
                }
                case "DOWN" -> {
                    System.out.println("DOWN");
                    player.right();
                    if (checkCollision(player.getNextMove().x, player.getNextMove().y)) {
                        player.forceLeft();
                        System.out.println("reassessed: left. Verif: " + player.getMove());
                    }
                }
                case "LEFT" -> {
                    System.out.println("LEFT");
                    player.up();
                    if (checkCollision(player.getNextMove().x, player.getNextMove().y)) {
                        player.forceDown();
                        System.out.println("reassessed: down. Verif: " + player.getMove());
                    }
                }
            }
        }
        count = 0;
        player.move();
    }

/*
This wil hopefully be utilizing the lee algorithm
Every point will be assigned an i value starting from snake and increasing the more hops it takes
Points already scanned or obstructed will be ignored
Points will continue from points just created

 */
    public void v4(int x, int y) {

        //store all barriers on field
        for(Rectangle r :player.getBody()){
            dataPointBlocked.add(new PointData(r.x, r.y,-1,true));
        }

        //add head of snake at i=0
        dataPoint.add(new PointData(player.getX(),player.getY(),0,true));

        //start from head (i=0). add dataPoint all adjacent points as i+1. scan i+1 points and add adjacent...

        for(int i = 0; i<10;i++) {//length of path
            for (int j = 0; j < getPointData().size(); j++) {//pass through all existing points
                if (getPointData().get(j).i == j) {//if i value of current point being checked equals i value being searched for
                    //add new surrounding points
                    dataPoint.add(new PointData(getPointData().get(j).x, getPointData().get(j).y - 1, i + 1, false));
                    dataPoint.add(new PointData(getPointData().get(j).x, getPointData().get(j).y + 1, i + 1, false));
                    dataPoint.add(new PointData(getPointData().get(j).x - 1, getPointData().get(j).y, i + 1, false));
                    dataPoint.add(new PointData(getPointData().get(j).x + 1, getPointData().get(j).y, i + 1, false));
                }
            }

            //delay in scanning
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            //if element in dataPoint has same coordinates as one in dataPointBlocked, remove that element in dataPoint
            //also if repeated point
            for(int k =0; k<getPointDataBlocked().size();k++){
                for(int l =0; l<getPointData().size();l++){
                    if((getPointDataBlocked().get(k).x==getPointData().get(l).x
                        &&getPointDataBlocked().get(k).y==getPointData().get(l).y)
                    ){
                        System.out.println("removing "+getPointData().get(l).x/20+", "+getPointData().get(l).y/20);
                        getPointData().remove(l);
                        l--;
                    }
                }
            }

        System.out.println(getPointData());
    }

    public void v5(int x, int y) {
        //get the net distance of the snake to the food.
        //NOTE: Min and Max are in reference to axis
        //ArrayList<String>
        String[] move = new String[100];
        xDisplacement = x - player.getX() / 20;
        yDisplacement = player.getY() / 20 - y;
        bodyXMax = 0;
        bodyXMin = 30;
        bodyYMax = 30;
        bodyYMin = 0;
        int stepCount = 0;


        for (int i = 0; i < player.getBody().size(); i++) {
            if (bodyXMax < player.getBody().get(i).x / 20) bodyXMax = player.getBody().get(i).x / 20;
            if (bodyXMin > player.getBody().get(i).x / 20) bodyXMin = player.getBody().get(i).x / 20;
            if (bodyYMax > player.getBody().get(i).y / 20) bodyYMax = player.getBody().get(i).y / 20;
            if (bodyYMin < player.getBody().get(i).y / 20) bodyYMin = player.getBody().get(i).y / 20;
        }
        boolean inTheZone = (player.getX() < bodyXMax && player.getX() > bodyXMin) &&
                (player.getY() < bodyYMax && player.getY() > bodyYMin);
        /*
        find two directions closest to food and distance to void exit
         */
        if (inTheZone) {
            if (player.getMove().equals("RIGHT")) {
                //check all blocks in up direction
                for (int i = bodyYMax - player.getY(); i > 0; i--) {
                    if (checkCollision(player.getX(), i)) {//there is an impedance
                        stepCount = 0;
                        inTheZone = true;
                        break;
                    }
                    stepCount++;
                    inTheZone = false;
                }
                if (inTheZone) {
                    for (int i = 0; i < stepCount; i++) {//add moves to step counter
                        move[i] = "UP";
                    }

                }

                //check all blocks in down direction
                for (int i = player.getY(); i < bodyYMin; i++) {
                    if (checkCollision(player.getX(), i)) {//there is an impedance
                        stepCount = 0;
                        break;
                    }
                    stepCount++;
                }
                for (int i = 0; i < stepCount; i++) {//add moves to step counter
                    move[i] = "DOWN";
                }
            }

        }
        //navigate to food avoiding zone

    }

    public boolean checkCollision(int x, int y) {
        for (int i = 1; i < player.getBody().size(); i++) {
            if (x == player.getBody().get(i).x/20 && y == player.getBody().get(i).y/20) {
                System.out.println("checkCollision() self collision");
                return true;
            }
        }

        if ((x < 0 || x >= width) || y < 0 || y >= height) {
            System.out.println("checkCollision() wall collision at "+ x+", "+y);
            return true;
        }
        return false;
    }
    public ArrayList<PointData> getPointData(){return dataPoint;}
    public ArrayList<PointData> getPointDataBlocked(){return dataPointBlocked;}

}