package edu.rit.csc.butterdick.lambda;

import java.util.Map;

public class Pair implements Expression
{
	private Expression first;
	private Expression second;

	public Pair(Expression first, Expression second)
	{
		this.first = first;
		this.second = second;
	}

	public Expression getFirst()
	{
		return first;
	}

	public Expression getSecond()
	{
		return second;
	}
	
	@Override
	public Expression eval(Map<LambdaColor, Expression> ctxt)
	{
		Expression f = first.eval(ctxt);
		Expression s = second.eval(ctxt);
		if (f instanceof Lambda)
			return ((Lambda)f).apply(ctxt, s);
		else
			return new Pair(f, s);
	}

	@Override
	public String toString()
	{
		return String.format("(%s %s)", first.toString(), second.toString());
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Pair)
		{
			Pair p = (Pair)obj;
			return first.equals(p.first) && second.equals(p.second);
		}
		else
			return false;
	}
}
