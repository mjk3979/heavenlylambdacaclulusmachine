package edu.rit.csc.butterdick.lambda;

import java.util.Map;

public class Variable implements Expression
{
	private LambdaColor color;

	public Variable(LambdaColor color)
	{
		this.color = color;
	}

	public LambdaColor getColor()
	{
		return color;
	}

	@Override
	public Expression eval(Map<LambdaColor, Expression> ctxt)
	{
		return ctxt.get(color);
	}

	@Override
	public String toString()
	{
		return Lambda.lambdaColorToString(color);
	}

	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof Variable && ((Variable)obj).color == color;
	}
}
