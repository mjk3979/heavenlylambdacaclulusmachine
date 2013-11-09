package edu.rit.csc.butterdick.lambda;

import java.util.Map;

public class Variable implements Expression
{
	private LambdaColor color;

	public Variable(LambdaColor color)
	{
		this.color = color;
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
}
