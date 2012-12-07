package pobj.algogen;

import pobj.arith.EnvEval;
import pobj.arith.ExpressionFactory;

public class ValeurCible implements Environnement {

	private double value;
	private EnvEval Environnement = ExpressionFactory.createRandomEnvironment();
	
	/**
	 *  constructeur d'une ValeurCible au hasard
	 */
	public ValeurCible() {
		this.value = Math.random();
	}
	
	/**
	 * constructeur d'une ValeurCible prédéfinie (value)
	 * @param value valeurCible
	 */
	public ValeurCible(double value) {
		this.value = value;
	}
	
	/**
	 * calcule le fitness d'un individu i dans l'environnement
	 * @param i Individu i
	 */
	public double eval(Individu i) {
		return 1/Math.pow((value - i.getValeurPropre().eval(Environnement)),2);
	}

	public double getValue() {
		return value;
	}

	public String toString() {
		return "ValeurCible :" + value;
	}
	
}
