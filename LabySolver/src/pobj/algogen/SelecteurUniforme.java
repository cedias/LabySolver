package pobj.algogen;

import pobj.util.Generateur;

public class SelecteurUniforme<T> implements IndivSelecteur<T> {

	@Override
	public Individu<T> getRandom(Population<T> pop) {
		Generateur random = Generateur.getInstance();
		return pop.get(random.nextInt(pop.size()));
	}

}
