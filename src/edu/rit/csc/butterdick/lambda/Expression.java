package edu.rit.csc.butterdick.lambda;

import java.util.Map;

public interface Expression
{
	Expression eval(Map<LambdaColor, Expression> ctxt);
}
