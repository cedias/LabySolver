package pobj.algogen.adapter.arith;

import pobj.algogen.Environnement;
import pobj.algogen.Population;

/**
 * Classe créatrice de population au hasard
 */
public class PopulationExpressionFactory{
	
	/**
     * cree une nouvelle population de size size
     * @param size taille le la population crée
     */
	public static Population createRandomPopulation(int size) {
		Population pop = new Population();
		for (int i = 0; i<size; i++) {
		    pop.add(new IndividuExpression());
		}
		return pop;
    }

	public static Environnement createEnvironnement() {
		return (new ValeurCible(Math.random()));
	} 
   
}
