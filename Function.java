package com.AdvancedMath;

public class Function {
	
	private String function;
	private String variable;
	public Function(String function, String variableName) throws IllegalArgumentException
	{
		this.function = function;
		this.variable = variableName;
		if(!valid())
		{
			throw new IllegalArgumentException("Parenthesis do no match up, or too many operators for the number of operands");
		}
	}
	
	private boolean valid() {
		// TODO Auto-generated method stub
		return true;
	}

	public double solve(double x)
	{
		String t = "";
		t+=x;
		String temp = function.replace(variable,t);
		return calculate(temp);
	}
	
	//Utility method that is kinda ugly
	//TODO still working on this something ain't right here
	private double calculate(String input)
	{
		double ret=0;
		if(input.contains("("))
		{
			String t="";
			t+=calculate(input.substring(input.indexOf("("),input.indexOf(")")));
			return calculate(t.replace(input.substring(input.indexOf("("),input.indexOf(")")+1), t));
		}
		int index =-1;
		double a;
		double b;
		int start;
		int end;
		String temp="";
		while(input.contains("*") || input.contains("\\"))
		{
			index = input.indexOf("*")>input.indexOf("\\") ? input.indexOf("*") : input.indexOf("\\");
			if(input.charAt(index)=='*')
			{
				a = getNumber(index,-1,input)[0];
				start = (int)getNumber(index,-1,input)[1];
				b = getNumber(index,1,input)[0];
				end = (int)getNumber(index,1,input)[1];
				temp += a*b;
				input.replace( input.substring(start,end), temp);
				temp = "";
				System.out.println(input);
			}
		}
		return ret;
	}

	private double[] getNumber(int index, int ticker, String input) {
		index--;
		System.out.println(index);
		double ret[] = new double[2];
		for(int i=index;i>0 && i<input.length() && !(Character.isDigit(input.charAt(i)) || input.charAt(i)=='.');i+=ticker)
		{
			ret[1] = i;
		}
		System.out.println(input);
		ret[0] = Double.parseDouble(input.substring((int) ret[1],index));		
		return ret;
	}
	//More utility YAY
	
}
