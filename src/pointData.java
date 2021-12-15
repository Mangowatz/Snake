import java.awt.*;

public class pointData {

    int i,x,y;
    boolean scanned;
    /*
    this assigns every point in the game a status and i value
     */
    public pointData(int x, int y,int i, boolean scanStatus){
        this.x = x;
        this.y = y;
        this.i = i;
        this.scanned = scanStatus;

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

    public boolean isScanned() {
        return scanned;
    }

    public void setScanned(boolean scanned) {
        this.scanned = scanned;
    }


}
