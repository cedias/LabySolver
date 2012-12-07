package pobj.algogen;

public class ValeurCible implements Environnement {

	private double value;
	
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
		super();
		this.value = value;
	}
	
	/**
	 * calcule le fitness d'un individu i dans l'environnement
	 * @param i Individu i
	 */
	public double eval(Individu i) {
		return 1/Math.pow((value - i.getValeurPropre()),2);
	}

	public double getValue() {
		return value;
	}

	public String toString() {
		return "ValeurCible :" + value;
	}
	
}
