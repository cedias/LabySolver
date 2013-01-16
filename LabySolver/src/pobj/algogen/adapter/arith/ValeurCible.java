package pobj.algogen.adapter.arith;

import pobj.algogen.Environnement;
import pobj.algogen.Individu;
import pobj.arith.EnvEval;
import pobj.arith.Expression;
import pobj.arith.ExpressionFactory;

public class ValeurCible implements Environnement<Expression> {

	private double value;
	private EnvEval Environnement;
	
	/**
	 *  constructeur d'une ValeurCible au hasard
	 */
	public ValeurCible() {
		value = Math.random();
		Environnement = ExpressionFactory.createRandomEnvironment();
	}
	
	/**
	 * constructeur d'une ValeurCible prédéfinie (value)
	 * @param value valeurCible
	 */
	public ValeurCible(double value) {
		this.value = value;
		Environnement = ExpressionFactory.createRandomEnvironment();
	}
	public ValeurCible(double value, EnvEval Environnement) {
		this.value = value;
		this.Environnement = Environnement;
	}
	
	/**
	 * calcule le fitness d'un individu i dans l'environnement
	 * @param i Individu i
	 */
	public double eval(IndividuExpression i) {
		return 1/Math.pow((value - i.getValeurPropre().eval(Environnement)),2);
	}

	public double getValue() {
		return value;
	}

	public String toString() {
		return "ValeurCible :" + value;
	}

	public double eval(Individu<Expression> i) {
		if (i instanceof IndividuExpression) {
			IndividuExpression ind = (IndividuExpression) i;
			return eval(ind);
		}
		return 0;
	}
	
}
