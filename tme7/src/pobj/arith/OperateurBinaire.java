package pobj.arith;

/**
 * Classe de représentation d'une opération binaire
 * @author Charles-Emmanuel Dias
 * @author Marwan Ghanem
 *
 */
public class OperateurBinaire implements Expression {
	
	/**
	 * opérateur
	 */
	private Operator type;
	
	/**
	 * Expression/operande de gauche
	 */
	private Expression exprLeft;
	
	/**
	 * Expression/operande de droite
	 */
	private Expression exprRight;
	
	/**
	 * Construit une opération binaire
	 * @param type type de l'operation
	 * @param exprLeft operande de gauche
	 * @param exprRight operande de droite
	 */
	OperateurBinaire(Operator type, Expression exprLeft, Expression exprRight) {
		this.type = type;
		this.exprLeft = exprLeft;
		this.exprRight = exprRight;
	}

	/**
	 * retourne l'expression/operande de gauche
	 * @return Expression celle de gauche !
	 */
	public Expression getLeft() {
		return exprLeft;
	}
	
	/**
	 * retourne l'expression/operande de droite
	 * @return Expression celle de droite !
	 */
	public Expression getRight() {
		return exprRight;
	}
	
	/**
	 * Evalue une expression binaire en fonction d'un environnement
	 * @param envEval Environnement d'évaluation
	 * @return double résultat de l'évaluation (doh!)
	 */
	public double eval(EnvEval envEval) {
		double result = 0;
		switch(type) {
		
			case PLUS:
				result = exprLeft.eval(envEval) + exprRight.eval(envEval);
				break;
				
			case MINUS:
				result = exprLeft.eval(envEval) - exprRight.eval(envEval);
				break;
				
			case MULT:
				result = exprLeft.eval(envEval) * exprRight.eval(envEval);
				break;
				
			case DIV:
				result = exprLeft.eval(envEval) / exprRight.eval(envEval);
				break;
		}
		return result;
	}
	
	/**
	 * Operation binaire -> String panthère
	 */
	public String toString() {
		switch(type) {
			case PLUS:
				return exprLeft.toString() +'+'+ exprRight.toString();	
			case MINUS:
				return exprLeft.toString() +'-'+ exprRight.toString();
			case MULT:
				return exprLeft.toString() +'*'+ exprRight.toString();
			case DIV:
				return exprLeft.toString() +'/'+ exprRight.toString();
		}
		return "Erreur de Type";
	}

	@Override
	public Expression simplifier() {
		exprLeft = exprLeft.simplifier();
		exprRight = exprRight.simplifier();
		
		if (exprLeft instanceof Constante && exprRight instanceof Constante)
			return ExpressionFactory.createConstante(this.eval(null));			// constante n'a pas besoin d'environnement d'evaluation
		else
			return this;
	}
	
}
