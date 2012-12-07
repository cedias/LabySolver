package pobj.arith;

/**
 * classe représentant une constante
 * @author Charles-Emmanuel Dias
 * @author Marwan Ghanem
 */
public class Constante implements Expression {
	/**
	 * valeur de la constante
	 */
	private double value;
	
	/**
	 * construit une constante de valeur value
	 * @param value valeur de la constante
	 */
	Constante(double value) {
		super();
		this.value = value;
	}
	
	/**
	 * retourne la valeur de la constante
	 * @return double valeur de la constante
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * évalue la constante
	 */
	public double eval(EnvEval envEval) {
		return value;
	}
	/**
	 * transforme une constante en string 
	 */
	public String toString() {
		return ""+value;
	}


}
