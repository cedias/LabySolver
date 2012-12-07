package pobj.arith;

/**
 * Classe représentant une variable dans un environnement d'éval.
 * @author Charles-Emmanuel Dias
 * @author Marwan Ghanem
 *
 */
public class Variable implements Expression {
	/**
	 * rang de la variable dans l'environnement
	 */
	private int rang;
	
	/**
	 * crée la réference vers l'index de l'environnement
	 * @param rang index dans l'environnement
	 */
	Variable (int rang) {
		this.rang = rang;
	}
	
	/**
	 * évalue la variable en fonction de l'environnement env
	 * @param env Environemment d'évaluation
	 * @return double valeur variable.
	 */
	public double eval(EnvEval env) {
	 return env.getValue(rang);	
	}
	
	/**
	 * Transforme une Variable en chaine de caractère (poil au derrière) !
	 * @return String
	 */
	public String toString() {
		return "X"+rang;
	}
	
}
