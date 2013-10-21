package com.matrix;
import java.util.Arrays;

/**
 * This class represents a matrix as defined in mathematics.
 * It includes many methods related to matrices in math.
 * @Author Casey Barnette
 * @version 1.0
 */
public class Matrix
{
	private int rows;
	private int columns;
	
	private double[][] values;

    /**
     * This is the constructor for a matrix given a double array
     */
	public Matrix(double[][] values)
	{
		this.values = Arrays.copyOf(values,values.length);
		rows = values.length;
		columns = values[0].length;
	}

    /**
     * This is the constructor for a matrix given an integer array
     */
	public Matrix(int[][] values)
	{
		for(int i=0;i<values.length;i++)
		{
            for(int j=0;j<values[0].length;j++)
            {
                this.values[i][j] = (double) values[i][j];
            }
		}
		rows = values.length;
		columns = values[0].length;
	}

    /**
     * Returns a copy of the value array
     * @return values
     */
    public double[][] getValues()
    {
        return Arrays.copyOf(values,values.length);
    }

    /**
     * Setter for the number of columns
     * @return rows
     */
	public int getRows()
	{
		return rows;
	}

    /**
     * Getter for the number of columns
     * @return columns
     */
	public int getColumns()
	{
		return columns;
	}

    /**
     * This method calculates the determinent of the matrix
     * @return a double that is the determinent
     */
	public double determinent()
	{
		if(rows!=columns)
		{
			throw new IllegalArgumentException("Can't find the determinent of a non-square matrix.");
		}
        if(this.getColumns()==2)
        {
            return values[0][0]*values[1][1]-(values[0][1]*values[1][0]);
        }
		double ret=0;
		for(int i=0;i<values.length;i++)
		{
			ret+=(Math.pow(-1d,(double)i+1)*getMinorMatrix(0,i).determinent()*values[0][i]);
		}
		return ret;
	}

    //this method creates a minor matrix from 
    //the current matrix given the current minor
	protected Matrix getMinorMatrix(int removeRow,int removeColumn)
	{
		double[][] minorValues = new double[rows-1][columns-1];
        
        int p=0;

        for(int i=0;i<getRows();i++)
        {
            if(i==removeRow)
                continue;

            int q=0;

            for(int j=0;j<getColumns();j++)
            {
                if(j==removeColumn)
                    continue;
                minorValues[p][q] = values[j][i];
                ++q;
            }
            ++p;
        }   

		return new Matrix(minorValues);
	}

    /**
     * This method sums the diagonal
     * @return the sum of the diagonals
     */
	public double getTrace()
	{
		double trace=0;
		if(!isSquare())
			throw new IllegalArgumentException("Can only find the trace of a square matrix");
		for(int i=0;i<values.length;i++)
		{
			trace+=values[i][i];
		}

		return trace;
	}

    
    private boolean isSquare()
    {
        return getColumns()==getRows();
    }

    /**
     * This method multiplies two matrices.
     * The method is static and takes two matrices and multiplies them
     * @param the first Matrix
     * @param the second Matrix
     * @return returns the product
     */
    //TODO Fix this
	public static Matrix multiply(Matrix m1, Matrix m2)
	{
		if(m1.getColumns()!=m2.getRows())
		{
			throw new IllegalArgumentException("Can't multiply the matrices because M1\'s number of columns"+
					" do not match M2\'s number of rows");
		}
		
		double[][] newMatrix = new double[m1.getRows()][m2.getColumns()];
		double[][] m1Values = m1.getValues();
		double[][] m2Values = m2.getValues();
		
        for(int i=0;i<m1.getRows();i++)
        {
            for(int j=0;j<m2.getColumns();j++)
            {
                newMatrix[i][j] = multRowCol(m1Values,m2Values,i,j);
            }
        }
        return new Matrix(newMatrix);
	}
    
    //This is not treating rows correctly
    private static double multRowCol(double[][] m1,double[][] m2,int row,int column)
    {
        double ret = 0;
        for(int i=0;i<m1[0].length;i++)
        {
            ret+=m1[row][i]*m2[i][column];
        }
        return ret;
    }

    /**
     * Creates the inverse of the matrix
     * @return the inverse of the matrix
     */
    public Matrix getInverse()
    {
       return this.cofactorMatrix().transpose().scalarMultiply(1/this.determinent());
    }

    /**
     * Transposes the matrix
     * @return the transposed matrix
     */
	public Matrix transpose()
	{
		double[][] transposeValues = new double[this.getColumns()][this.getRows()];
		for(int i=0;i<getRows();i++)
		{
			for(int j=0;j<getColumns();j++)
			{
				transposeValues[j][i] = values[i][j];
			}
		}
		return new Matrix(transposeValues);
	}

    /*
     * This method finds the cofactor matrix of a matrix.
     * It is used to find the inverse matrix
     */
    public Matrix cofactorMatrix()
    {
        double[][] cofMat = new double[getRows()][getColumns()];
        for(int i=0;i<getRows();i++)
        {
            for(int j=0;j<getColumns();j++)
            {
                cofMat[i][j] = Math.pow(-1.0,(i+1)+(j+1))*getMinorMatrix(i,j).determinent();
            }
        }

        return new Matrix(cofMat);
    }

    /**
     * This method multiplies the Matrix by a scalar c.
     * @param a scalar C
     * @return The matrix after scalar multiply
     */
	public Matrix scalarMultiply(double c)
	{
		double[][] mValues = Arrays.copyOf(getValues(),getValues().length);;
		for(int i=0;i<getRows();i++)
		{
			for(int j=0;j<getColumns();j++)
			{
				mValues[i][j] = c*mValues[i][j];
			}
		}
		return new Matrix(mValues);
	}

    /**
     * String representation of a Matrix
     * @return Returns the string
     */
    public String toString()
    {
        String ret = "";
        for(int j=0;j<getRows();j++)
        {
            ret+="| ";
            for(int i=0;i<getColumns();i++)
            {
                ret+=values[j][i]+" ";
            }
            ret+="|\n";
        }
        return ret;
    }      
}
