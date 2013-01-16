package pobj.arith;
/**
 * Factory d'Expression
 * @author Charles-Emmanuel Dias
 * @author Marwan Ghanem
 *
 */
public class arithmethiqueMain {
	/**
	 * test du package pobj.arith
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		/*EnvEval env = new EnvEval(3);
		Constante constante1 = new Constante(0.2);
		Constante constante2 = new Constante(0.4);
		Variable var1 = new Variable(0);
		Variable var2 = new Variable(1);
		Variable var3 = new Variable(2);
		
		env.setVariable(0,0.8);
		env.setVariable(1,0.4);
		env.setVariable(2,1);
		
		OperateurBinaire ope1 = new OperateurBinaire(Operator.PLUS,var1,constante1); //0,2 + 0,8
		OperateurBinaire ope2 = new OperateurBinaire(Operator.MINUS,var2,constante2); //0,4 - 0,4
		OperateurBinaire ope3 = new OperateurBinaire(Operator.MULT,var1,var3); //0,8 * 1
		OperateurBinaire ope4 = new OperateurBinaire(Operator.DIV,var3,constante1); //1 : 0,2
		System.out.println("----------TEST MANUEL------------");
		System.out.println(env);
		System.out.println(ope1 + " = " + ope1.eval(env));
		System.out.println(ope2 + " = " + ope2.eval(env));
		System.out.println(ope3 + " = " + ope3.eval(env));
		System.out.println(ope4 + " = " + ope4.eval(env));*/
		System.out.println("----------TEST AUTO------------");
		
		Expression randomExpr = null;
		EnvEval	randomEnv = null;
		for(int i = 0; i < 20 ; i++){
			randomExpr = ExpressionFactory.createRandomExpression(3);
			randomEnv = ExpressionFactory.createRandomEnvironment();
			System.out.println("----------------");
			System.out.println("env: " + randomEnv);
			System.out.println("expr: " + randomExpr + " = " + randomExpr.eval(randomEnv));
		}
	
		
		
		
		
		
		

	}

}
