package pobj.arith;

import java.util.Random;
/**
 * Factory d'Expression
 * @author Charles-Emmanuel Dias
 * @author Marwan Ghanem
 *
 */
public class ExpressionFactory {

	/**
	 * taille maximal de l'environnement d'évaluation
	 */
	private static int MAXVARIABLES = 2;
	/**
	 * generateur de hasard !
	 */
	private static Random generator = new Random();
	
	/**
	 * Un constructeur pour des expressions binaires usuelles: +,-,*,/
	 * @param op le type de l'opérande, {@link Operator}, PLUS,MOINS,MULT,DIV
	 * @param exprLeft operande gauche
	 * @param exprRight operande droite
	 * @return une expression binaire
	 */
	public static Expression createOperateurBinaire(Operator op, Expression exprLeft, Expression exprRight) {
		return new OperateurBinaire(op,exprLeft,exprRight);
	}
	
	/**
	 * Un constructeur d'expression constantes
	 * @param constante sa valeur
	 * @return une constante
	 */
	public static Expression createConstante(double constante) {
		return new Constante(constante);
	}
	
	/**
	 * Un constructeur de variables, identifiées par un entier compris entre O et MAXVARIABLES
	 * La demande de création de variables d'indice plus grand entraine un accroissement de MAXVARIABLES
	 * @param id indice de la variable
	 * @return une variable
	 */
	public static Expression createVariable(int id) {
		if (id >= MAXVARIABLES) {
			MAXVARIABLES = id+1;
		}
		return new Variable(id);
	}
	
	/**
	 * Génère un environnement d'évaluation aléatoire, en supposant qu'il n'a pas plus de MAXVARIABLES
	 * @return un environnement généré aléatoirement
	 */
	public static EnvEval createRandomEnvironment() {
		EnvEval env = new EnvEval(MAXVARIABLES);
		for(int i = 0; i < MAXVARIABLES ; i++){
			env.setVariable(i, generator.nextDouble());
		}
		return env;
	}
	
	/**
	 * Génère une expression aléatoire de profondeur p au maximum
	 * @param profondeur
	 * @return
	 */
	public static Expression createRandomExpression(int profondeur) {
		Operator[] operations = {Operator.PLUS,Operator.MULT,Operator.MINUS};
		
		if(profondeur == 0){
			if (1 == generator.nextInt(2)){
				/*constante creation*/
				return(createConstante(generator.nextDouble()));
			}else {
				/*variable creation*/
				return(createVariable(generator.nextInt(MAXVARIABLES)));
			}
		} else {
			if (3 <= generator.nextInt(10)) {
				/*70% chances to continue*/
				return (createOperateurBinaire(
							operations[generator.nextInt(3)],
							createRandomExpression(profondeur-1),
							createRandomExpression(profondeur-1))
							);
			} else {
				/*30% chances to stop*/
				if (1 == generator.nextInt(2)){
					/*constante creation*/
					return(createConstante(generator.nextDouble()));
				}else {
					/*variable creation*/
					return(createVariable(generator.nextInt(MAXVARIABLES)));
				}
			}
		}
	}

}
