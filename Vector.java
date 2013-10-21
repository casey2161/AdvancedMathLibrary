import java.util.Arrays;
public class Vector
{
	double[] components;

	public Vector(double...components)
	{
		this.components = Arrays.copyOf(components,components.length);
	}

	public double getMagnitude()
	{
		double magnitude=0;
		for( double component : components)
		{
			magnitude+=(component*component);
		}
		return Math.sqrt(magnitude);
	}

	public Vector getUnitVector()
	{
		double[] componentCopy = Arrays.copyOf(components,components.length);
		double magnitude = this.getMagnitude();
		for(int i=0;i<components.length;i++)
		{
			componentsCopy[i] = components[i]/magnitude;
		}
		return new Vector(componentCopy);
	}

	public double dotProduct(Vector u)
	{
		double dotProduct=0;
		int length = -1;
		if(u.getOrder()>this.getOrder())
		{
			length=this.getOrder();
		}
		else
		{
			length = u.getOrder();
		}

		for(int i=0;i<length;i++)
		{
			dotProduct += (u.getComponent(i)*this.getComponent(i));
		}
		return dotProduct;
	}

	public double getAngleBetween(Vector u)
	{
		double angle = this.dotProduct(u)/(u.getMagnitude()*this.getMagnitude());
		double angle = Math.acos(angle);
		return angle;
	}

	public boolean isOrthogonal(Vector u)
	{
		double dotProduct = this.dotProduct(u);
		if(Math.abs(dotProduct-0)<0.000001)
			return true;
		else
			return false;
	}

	public boolean isParallel(Vector u)
	{
		//TODO
	}

    //TODO test
	public Vector crossPoduct(Vector...u)
	{
        if(u[0].getOrder()-1!=u.length)
        {
            throw new IllegalArgumentException("Can't find the CrossProduct for these values");
        }
        double[][] mValues = new double[u.length+1][u[0].getOrder()];
        
        for(int i=0;i<u.length;i++)
        {
            mValues[i+1] = u[i].getAllComponents()
        }
        for(int i=0;i<mValues[0].length;i++)
        {
            mValues[0][i]=1;
        }
        
        Matrix m= new Matrix(mValues);

        Vector ret;
        double[] vectorValues = new double[u[0].getOrder()];
        for(int i=0;i<u[0].getOrder();i++)
        {
            vectorValues[i] = Math.pow(-1,(i+2))*m.getMinorMatrix(0,i).determinent();
        }
        ret = new Vector(vectorValues);        
	}
	public double getOrder()
	{
		return components.length;
	}

	public double getComponent(int index)
	{
		return components[index];
	}

    public double getAllComponents()
    {
        return Arrays.copyOf(components,components.length);
    }

}
