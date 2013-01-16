package pobj.algogen;

public interface IndivSelecteur<T> {

		Individu<T> getRandom(Population<T> pop);
}
