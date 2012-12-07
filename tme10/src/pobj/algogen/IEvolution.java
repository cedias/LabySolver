package pobj.algogen;

public interface IEvolution<T> {

	Population<T> reproduire(Population<T> pop, double ratio);
}
