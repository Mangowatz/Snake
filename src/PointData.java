import java.awt.*;

public class PointData {

    int i,x,y;
    boolean isBlocked;
    private final int gd = Game.dimension;
    private Game game;
    private AutoLogic a;
    /*
    this assigns every point in the game a status and i value
     */
    public PointData(int x, int y,int i, boolean isBlocked){
        this.x = x;
        this.y = y;
        this.i = i;
        this.isBlocked = isBlocked;
    }
    public PointData(Game g){
        game= g;
        a = game.getAutoLogic();
    }


    public void pointDataRefresh(){

           System.out.println("Clearing dataPoint:");
           //System.out.println(a.dataPoint);
            a.dataPointBlocked.clear();
            a.dataPoint.clear();

/*
        for(int j =0; j<30;j++){
            for(int k = 0; k<30;k++){
                if(a.checkCollision(j,k)){
                    a.dataPoint.add(new PointData(j,k,0,true));
                    System.out.println("New positive: "+j+", "+k);
                }
                a.dataPoint.add(new PointData(j,k,0,false));
                //System.out.println("New negative: "+j+", "+k);
            }
        }
*/
    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setScanned(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }


}
