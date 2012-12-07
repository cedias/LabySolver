package pobj.algogen.adapter.arith;

import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.arith.Expression;

/**
 * Classe créatrice de population au hasard
 */
public class PopulationExpressionFactory{
	
	/**
     * cree une nouvelle population de size size
     * @param size taille le la population crée
     */
	public static Population<Expression> createRandomPopulation(int size) {
		Population<Expression> pop = new Population<Expression>();
		for (int i = 0; i<size; i++) {
		    pop.add(new IndividuExpression());
		}
		return pop;
    }

	public static Environnement<Expression> createEnvironnement() {
		return (new ValeurCible(Math.random()));
	} 
   
}
