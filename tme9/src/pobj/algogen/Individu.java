package pobj.algogen;

public interface Individu<T> extends Comparable<Individu<T>> {

	public String toString();

	public void setFitness(double i);

	public int compareTo(Individu<T> o);

	public Individu<T> muter();

	public Individu<T> croiser(Individu<T> alea2);

	public Individu<T> clone();

	public double getFitness();

}