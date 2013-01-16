package pobj.util;


import pobj.arith.Expression;
import pobj.arith.ExpressionFactory;

public class GenerateurTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Expression a = (Expression) ExpressionFactory.createRandomExpression(2);
		System.out.println(a.toString());

	}

}
