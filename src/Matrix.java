import java.util.ArrayList;
import java.util.List;

class Matrix {
    double[][] data;
    int rows, cols;


    public Matrix(int rows, int cols) {
        data = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    public void add(double scalar) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j]+=scalar;
            }
        }
    }

    public void add(Matrix m) {
        if(cols!=m.cols||rows!=m.rows){
            System.out.println("Shape Mismatch");

        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j]+=m.data[i][j];
            }
        }
    }

    public static Matrix subtract(Matrix a, Matrix b){
        Matrix temp = new Matrix(a.rows,a.cols);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                temp.data[i][j]=a.data[i][j]-b.data[i][j];
            }
        }
        return temp;
    }

    public static Matrix transpose(Matrix a){
        Matrix temp = new Matrix(a.cols,a.rows);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                temp.data[j][i]=a.data[i][j];
            }
        }
        return temp;
    }

    public static Matrix multiply(Matrix a,Matrix b){//dot prodoct
        Matrix temp = new Matrix(a.rows,a.cols);
        for (int i = 0; i < temp.rows; i++) {
            for (int j = 0; j < temp.cols; j++) {
                double sum =0;
                for(int k =0;k<a.cols;k++){
                    sum+=a.data[i][k]*b.data[k][j];
                }
                temp.data[i][j]=sum;
            }
        }
        return temp;
    }

    public void multiply(Matrix a){//element-wise
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                this.data[i][j]*=a.data[i][j];
            }
        }
    }

    public void multiply(double a){//whole matrix by scalar

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j]*=a;
            }
        }
    }

    public void sigmoid() {
        for(int i =0; i<rows;i++){
            for(int j =0; j<cols;j++){
                this.data[i][j] = 1/(1+Math.exp(-this.data[i][j]));
            }
        }
    }
    public Matrix dsigmoid(){
        Matrix temp = new Matrix(rows,cols);
        for(int i =0; i<rows;i++) {
            for (int j = 0; j < cols; j++) {
                temp.data[i][j] = this.data[i][j] * (1 - this.data[i][j]);//couldn't be shotened to *=?
            }
        }
            return temp;
    }

    public static Matrix fromArray(double[] x){
        Matrix temp = new Matrix(x.length,1);
        for(int i =0;i<x.length;i++){
            temp.data[i][0]=x[i];
        }return temp;
    }

    public List<Double> toArray(){
        List<Double> temp = new ArrayList<Double>();
        for(int i =0;i<rows;i++){
            for(int j = 0; j<cols;j++){
                temp.add(data[i][j]);
            }
        }

        return temp;
    }






}