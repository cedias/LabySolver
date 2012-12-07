package pobj.algogen;

/**
 * @author Charles-Emmanuel Dias
 * @author Marwan Ghanem
 * 
 * Strategie d'évolution progressive d'une population d'individus T
 * @see {@Population}
 * @param <T>
 */

public class EvolutionProgressive<T> implements IEvolution<T> {

	IndivSelecteur<T> selecteur;
	
	public EvolutionProgressive(IndivSelecteur<T> selecteur) {
		super();
		this.selecteur = selecteur;
	}
	
	@Override
	public Population<T> reproduire(Population<T> pop, double ratio) {
		
		Individu<T> parent1 = selecteur.getRandom(pop);
		Individu<T> parent2 = selecteur.getRandom(pop);
		Individu<T> enfant = parent1.croiser(parent2);
		Population<T> newPop = pop.clone();
		newPop.set(newPop.size()-1, enfant);
		return newPop;
	}


}
