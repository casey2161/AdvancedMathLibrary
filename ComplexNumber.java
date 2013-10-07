package com.AdvancedMath;

import java.util.ArrayList;

/*******************************************************************************************************************************
 *                                                                                                                             *
 * @author Casey Michael Barnette               																			   *
 * Date: 7/13/2013																											   *
 * Description: This is the complex number class which has a bunch of useful mathematics that comes along with complex numbers *
 * TODO: A string constructor, there are a lot of parsing exceptions and it is a lot harder than it seems                      *
 *   																														   *
 ******************************************************************************************************************************/

public class ComplexNumber{
	//Start private variables
	private double a;
	private double b;
	//end private variables
	//Start Constructors
	public ComplexNumber(double a,double b)
	{
		this.a = a;
		this.b = b;
	}
	
	public ComplexNumber(float a,float b)
	{
		this.a = (double)a;
		this.b = (double)b;
	}
	
	public ComplexNumber(int a,int b)
	{
		this.a = (double)a;
		this.b = (double)b;
	}
	
	public ComplexNumber(String expression) throws IllegalArgumentException
	{
		try
		{
			int[] number1 = findNumberRange(expression,0);
			int[] number2 = findNumberRange(expression,number1[1]+1);
			if(number2[0]!=-1 && number2[1]!=-1)
			{
				if(number1[0]!=0)
				{
					System.out.println(expression.charAt(number2[0]-number1[1]));
					a = Integer.parseInt(expression.substring(number1[0],number1[1]+1)) *(-1);
					if(expression.contains("+"))
					{
						b = Integer.parseInt(expression.substring(number2[0],number2[1]+1));
					}
					else
					{
						b = Integer.parseInt(expression.substring(number2[0],number2[1]+1))*(-1);
					}
				}
				else
				{
					a = Integer.parseInt(expression.substring(number1[0],number1[1]+1));
					if(expression.contains("+"))
					{
						b = Integer.parseInt(expression.substring(number2[0],number2[1]+1));
					}
					else
					{
						b = Integer.parseInt(expression.substring(number2[0],number2[1]+1))*(-1);
					}
				}
			}
			else	{
				if(expression.contains("i"))
				{
					a=0;
					b = Integer.parseInt(expression.split("i")[0]);
				}
				else
				{
					a= Integer.parseInt(expression);
					b=0;
				}
			}
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Expression given does not match proper mathematical form: \"" + expression + "\"");
		}
	}
	
	//End Constructors
	//Start math related methods
	public double abs()
	{
		return Math.sqrt(a*a + b*b*(-1));
	}
	
	public ComplexNumber getConjugate()
	{
		return new ComplexNumber(a,b*(-1));
	}
	
	//Simple addition
	public static ComplexNumber add(ComplexNumber...args)
	{
		if(args.length<2)
			throw new IllegalArgumentException("invalid number of arguments for: subtract. Please include 2 or more complex numbers");
		double tempA =0;
		double tempB = 0;
		for(ComplexNumber z : args)
		{
			tempA+=z.getA();
			tempB+=z.getB();
		}
		return new ComplexNumber(tempA,tempB);
	}
	
	//Subtraction
	public static ComplexNumber subtract(ComplexNumber...args) throws IllegalArgumentException
	{
		if(args.length<2)
			throw new IllegalArgumentException("invalid number of arguments for: subtract. Please include 2 or more complex numbers");
		double tempA =args[0].getA();
		double tempB =args[0].getB();
		for(int i=1;i<args.length;i++)
		{
			tempA-=args[i].getA();
			tempB-=args[i].getB();
		}
		return new ComplexNumber(tempA,tempB);
	}
	//Can only multiply 2 numbers due to complexity of multiple numbers and the likely hood of use
	public static ComplexNumber multiply(ComplexNumber z1, ComplexNumber z2)
	{
		double tempA = z1.getA()*z2.getA() - z1.getB()*z2.getB();
		double tempB = z1.getA()*z2.getB() + z1.getB()*z2.getA();
		return new ComplexNumber(tempA,tempB);
	}
	//Can only divide 2 numbers due to complexity again
	public static ComplexNumber divide(ComplexNumber z1, ComplexNumber z2)
	{
		ComplexNumber zRet = ComplexNumber.multiply(z1,z2.getConjugate());
		double tempA = zRet.getA()/(z2.getA()*z2.getA() + z2.getB()*z2.getB());
		double tempB = zRet.getB()/(z2.getA()*z2.getA() + z2.getB()*z2.getB());
		zRet.setA(tempA);
		zRet.setB(tempB);
		return zRet;
	}
	//polarForm
	public static double getPolarAngle(ComplexNumber z)
	{
		return Math.atan(z.getB()/z.getA());
	}
	
	public static double getPolarRadius(ComplexNumber z)
	{
		return Math.sqrt(z.getA()*z.getA()+z.getB()*z.getB());
	}
	//the boolean determines whether to treat the input as radians or degrees
	public static ComplexNumber fromPolar(double r, double input, boolean radians)
	{
		if(radians)
		{
			return new ComplexNumber(r*Math.cos(input),r*Math.sin(input));
		}
		else
		{
			return new ComplexNumber(r*Math.cos(Math.toDegrees(input)),r*Math.sin(Math.toDegrees(input)));
		}
	}
	
	//Nth roots
	public static ArrayList<ComplexNumber> getNRoots(ComplexNumber z, int n)
	{
		ArrayList<ComplexNumber> zList = new ArrayList<ComplexNumber>();
		double r = Math.sqrt(z.getA()*z.getA() + z.getB()*z.getB());
		System.out.println(z.getB()/z.getA());
		double radians = Math.atan((z.getB()/z.getA()));
		System.out.println(Math.toDegrees(radians));
		System.out.println("Radius: " + r + "\nRadians: " + radians);
		double temp = 1;
		temp/=n;
		double tempA;
		double tempB;
		for(int i=0;i<n;i++)
		{
			tempA = Math.cos((radians+(2*Math.PI*i))/n)*Math.pow(r, temp);
			tempB = Math.sin((radians+(2*Math.PI*i))/n)*Math.pow(r, temp);
			zList.add(new ComplexNumber(tempA,tempB));
		}
		return zList;
	}
	//End Math Methods
	//Start non math related utility methods
	public void setA(double a) {
		this.a = a;
	}

	public void setB(double b) {
		this.b = b;
	}
	
	public double getA()
	{
		return a;
	}
	
	public double getB()
	{
		return b;
	}
	
	@Override
	//simple to string
	public String toString()
	{
		if(b<0)
			return new String(a+"-"+(b*-1)+"i");
		else
			return new String(a+"+"+b+"i");
	}
	
	//This is some string trimming things to keep the parsing of a string version of a complex number from dying
	private String trim(String input)
	{
		String ret="";
		for(int i=0;i<input.length();i++)
		{
			if(input.charAt(i)!=' ')
			{
				ret+=input.charAt(i);
			}
		}
		return ret;
	}
	
	private int[] findNumberRange(String expression,int start)
	{
		int[] range = new int[2];
		range[0]=-1;
		range[1]=-1;
		for(int i=start;i<expression.length();i++)
		{
			if(range[0]==-1 && Character.isDigit(expression.charAt(i)))
			{
				range[0] = i;
			}
			if(range[0]!=-1 && !Character.isDigit(expression.charAt(i)))
			{
				range[1] = i-1;
				break;
			}
		}
		return range;
	}
	
}
