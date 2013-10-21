public class Test
{
    public static void main(String[] args)
    {
        double[][] a = new double[2][3];
        double counter=0;
       
        a[0][0] = 1;
        a[0][1] = 0;
        a[0][2] = -2;
        a[1][0] = 0;
        a[1][1] = 3;
        a[1][2] = -1;

        double[][] b = new double[3][2];
        b[0][0] = 0;
        b[0][1] = 3;
        b[1][0] = -2;
        b[1][1] = -1;
        b[2][0] = 0;
        b[2][1] = 4;

        Matrix matA = new Matrix(a);
        Matrix matB = new Matrix(b);

        System.out.println(Matrix.multiply(matA,matB));
    }
}
