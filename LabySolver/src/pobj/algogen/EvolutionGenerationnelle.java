package pobj.algogen;


import pobj.util.Generateur;

/**
 * 
 * @author Charles-Emmanuel Dias
 * @author Marwan Ghanem
 * Strategie d'évolution générationnelle d'une population d'individus T
 * @see {@Population}
 * @param <T>
 */
public class EvolutionGenerationnelle<T> implements IEvolution<T> {
	
	IndivSelecteur<T> selecteur;
	
	
	public EvolutionGenerationnelle(IndivSelecteur<T> selecteur) {
		super();
		this.selecteur = selecteur;
	}


	@Override
	public Population<T> reproduire(Population<T> pop, double ratio) {
		
		boolean mutation = true ;
    	int nbExs = (int) (pop.size() * 0.2);
    	Generateur rand = Generateur.getInstance();
    	Population<T> newPop = new Population<T>();
    	
    	if (nbExs == 0)
    		nbExs = 1;
    	
    	for (int i=0; i<nbExs; i++) {
    		Individu<T> chosenOne = pop.get(i);
    		newPop.add(chosenOne.clone());
    	}
    		
    	for (int i = nbExs ; i < pop.size() ; i++) {
    		Individu<T> alea1 = pop.get(rand.nextInt((int)nbExs));
    		Individu<T> alea2 = pop.get(rand.nextInt((int)nbExs));
    		newPop.add(alea1.croiser(alea2));
    	}
    	
    	for (int i = nbExs ; i < pop.size() ; i++) {
    		double taux = rand.nextDouble();
    		if (taux < ratio) {
    			if(!mutation){
    				try{
    					newPop.set(i,pop.get(i).croiser(pop.get(i+1)));
    				}catch(IndexOutOfBoundsException e){
    					newPop.set(i,pop.get(i).croiser(pop.get(i-1)));
    				}
    				mutation = true;
    			} else {
    				newPop.set(i,pop.get(i).muter());
    				mutation = false;
    			}
    		}
    	}
    	return newPop;
	}

}
