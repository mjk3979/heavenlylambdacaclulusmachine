package edu.rit.csc.butterdick.test;

import edu.rit.csc.butterdick.lambda.*;

public class LambdaTest
{
	public static boolean test1()
	{
		Expression test = new Pair(new Lambda(LambdaColor.BLUE, new Variable(LambdaColor.BLUE)), new Lambda(LambdaColor.RED, new Variable(LambdaColor.RED)));
		Expression result = test.eval(Lambda.getEmptyContext());
		return result.equals(new Lambda(LambdaColor.RED, new Variable(LambdaColor.RED)));
	}

	public static boolean test2()
	{
		Expression test = new Pair(new Lambda(LambdaColor.BLUE, new Pair(new Lambda(LambdaColor.RED, new Variable(LambdaColor.RED)), new Variable(LambdaColor.BLUE))), new Lambda(LambdaColor.YELLOW, new Variable(LambdaColor.YELLOW)));
		Expression result = test.eval(Lambda.getEmptyContext());
		return result.equals(new Lambda(LambdaColor.YELLOW, new Variable(LambdaColor.YELLOW)));
	}

	public static void main(String[] args)
	{
		System.out.printf("Test 1: %s\n", test1() ? "PASS" : "FAIL");
		System.out.printf("Test 2: %s\n", test2() ? "PASS" : "FAIL");
	}
}
