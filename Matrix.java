public class Matrix
{
	private int rows;
	private int coloumns;
	
	private double[] values;

	public Matrix(double[] values)
	{
		this.values = Arrays.copyOf(values,values.length);
		rows = values.length;
		columns = values[0].length;
	}

	public Matrix(int[] values)
	{
		for(int i=0;i<values.length;i++)
		{
			this.values[i] = values[i];
		}
		rows = values.length;
		columns = values[0].length;
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
		if(rows!=column)
		{
			throw new IllegalArgumentException("Can't find the determinent of a non-square matrix.");
		}
		double ret=0;
		for(int i=0;i<values.length;i++)
		{
			ret+=(Math.exp(-1,i)*determinent(getMinorMatrix(0,i))*values[0][i]);
		}
		return ret;
	}

	private Matrix getMinorMatrix(int removeRow,int removeColumn)
	{
		double[][] minorValues = new double[rows-1][columns-1];

		for(int i=0;i<minorValues.length;i++)
		{
			if(i==removeRow)
				continue;
			for(int j=0;j<minorValues[0].length;j++)
			{
				if(j==removeColumn)
					continue;

				minorValues[i][j] = values[i][j];
			}
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

	public Matrix transpose()
	{
		double[][] transposeValues = new double[values.getColumns()][values.getRows()];
		for(int i=0;i<getRows();i++)
		{
			for(int j=0lj<getColumns();j++)
			{
				transposeValues[j][i] = values[i][j];
			}
		}
		return new Matrix(transposeValues);
	}

	public static Matrix scalarMultiply(double c, Matrix m)
	{
		double[][] mValues = m.getValues();
		for(int i=0;i<m.getRows();i++)
		{
			for(int j=0;j<m.getColumns();j++)
			{
				mValues[i][j] = c*mValues[i][j];
			}
		}
		return new Matrix(mValues);
	}
}
