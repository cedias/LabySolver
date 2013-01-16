package pobj.algogen;

import pobj.util.Generateur;

public class SelecteurParFitness<T> implements IndivSelecteur<T> {

	@Override
	public Individu<T> getRandom(Population<T> pop) {
		Generateur rand = Generateur.getInstance();
		double x = rand.nextDouble()*pop.getSommeFitnesses();
		double cpt = 0;
		int i = 0;
		while(cpt < x)
		{
			cpt += pop.get(i).getFitness(); 
		}
		return pop.get(i);
	}

}
