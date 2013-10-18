import java.util.Arrays;
public class Matrix
{
	private int rows;
	private int columns;
	
	private double[][] values;

	public Matrix(double[][] values)
	{
		this.values = Arrays.copyOf(values,values.length);
		rows = values.length;
		columns = values[0].length;
	}

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

    public double[][] getValues()
    {
        return values;
    }

	public int getRows()
	{
		return rows;
	}

	public int getColumns()
	{
		return columns;
	}

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
			ret+=(Math.pow(-1d,(double)i)*getMinorMatrix(0,i).determinent()*values[0][i]);
		}
		return ret;
	}

	private Matrix getMinorMatrix(int removeRow,int removeColumn)
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
                minorValues[p][q] = values[i][j];
                ++q;
            }
            ++p;
        }   

		return new Matrix(minorValues);
	}

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

	public static Matrix multiply(Matrix m1, Matrix m2)
	{
		if(m1.getRows()!=m2.getColumns())
		{
			throw new IllegalArgumentException("Can't multiply the matrices because M1\'s number of columns"+
					" do not match M2\'s number of rows");
		}
		
		double[][] newMatrix = new double[m1.getRows()][m2.getColumns()];
		double[][] m1Values = m1.getValues();
		double[][] m2Values = m2.getValues();
		for(int i=0;i<m1.getColumns();i++)
		{
			for(int j=0;j<m2.getRows();j++)
			{
				newMatrix[i][j]=0;
				//This can be a little confusing
				//The third one is for cycling the values of m1's row and m2's column
				for(int k=0;k<m1.getColumns();k++)
				{
					newMatrix[i][j] +=m1Values[i][k]*m2Values[k][i];
				}
			}
		}
		return new Matrix(newMatrix);
	}

    public Matrix getInverse()
    {
       return this.cofactorMatrix().transpose().scalarMultiply(1/this.determinent());
    }
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

    //TODO
    //Figure out why the positives and negatives are wrong
    public Matrix cofactorMatrix()
    {
        double[][] cofMat = new double[getRows()][getColumns()];
        for(int i=0;i<getRows();i++)
        {
            for(int j=0;j<getColumns();j++)
            {
                cofMat[i][j] = Math.pow(-1.0,(i+1)*(j+1))*getMinorMatrix(i,j).determinent();
            }
        }

        return new Matrix(cofMat);
    }

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
