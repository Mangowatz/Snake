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
        if(x>=0&&x/gd<=30&&y>=0&&y/gd<=30) {
            this.x = x;
            this.y = y;
            this.i = i;
            this.isBlocked = isBlocked;
        }
    }
    public PointData(int x, int y){
        if(x>=0&&x/gd<=30&&y>=0&&y/gd<=30) {
            this.x = x;
            this.y = y;
        }
    }
    public PointData(Game g){
        game= g;
        a = game.getAutoLogic();
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

    public Point getPoint(){return new Point(x,y);}

}
