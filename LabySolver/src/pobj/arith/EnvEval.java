package pobj.arith;

import java.util.Arrays;
/**
 * Classe représentant un environnement d'évaluation
 * 
 * @author Charles-Emmanuel Dias
 * @author Marwan Ghanem
 *
 */
public class EnvEval {
	/**
	 * tableau des variables de l'environnement
	 */
	private double[] variables;
	
	/**
	 * construit un environnement de taille nbVariable
	 * @param nbVariables
	 */
	public EnvEval(int nbVariables) {
		variables = new double[nbVariables];
	}
	
	/**
	 * Met la variable n°Index de l'environnement à la valeur 'variable'
	 * @param index
	 * @param variable
	 */
	public void setVariable(int index, double variable) {
		variables[index] = variable;
	}
	
	/**
	 * récupère la valeur index de l'environnement
	 * @param index indice de l'environnement
	 * @return value valeur de env[index]
	 */
	public double getValue(int index) {
		return variables[index];
	}
	
	/**
	 * EnvEval -> Chaine de Caractère (YOU DON'T SAY ?)
	 */
	public String toString() {
		return Arrays.toString(variables);
	}
}
