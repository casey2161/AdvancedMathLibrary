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

	public Vector crossPoduct(Vector u)
	{

	}
	public double getOrder()
	{
		return components.length;
	}

	public double getComponent(int index)
	{
		return components[index];
	}

}
