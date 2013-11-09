package edu.rit.csc.butterdick.lambda;

import java.util.Map;

public class Lambda implements Expression
{
	private LambdaColor color;
	private Expression body;

	public Lambda(LambdaColor color, Expression body)
	{
		this.color = color;
		this.body = body;
	}

	@Override
	public Expression eval(Map<LambdaColor, Expression> ctxt)
	{
		return this;
	}

	public Expression apply(Map<LambdaColor, Expression> ctxt, Expression arg)
	{
		if (ctxt.containsKey(color))
		{
			int x = 5/0;
		}
		ctxt.put(color, arg);
		return body.eval(ctxt);
	}

	public static String lambdaColorToString(LambdaColor color)
	{
		switch(color)
		{
			case BLUE:
				return "x";
			case RED:
				return "y";
			default:
				return "wtf?";
		}
	}

	@Override
	public String toString()
	{
		return String.format("(A. %s %s)", lambdaColorToString(color), body.toString());
	}
}
