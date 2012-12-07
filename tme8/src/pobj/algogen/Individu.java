package pobj.algogen;

public interface Individu extends Comparable<Individu> {

	public String toString();

	public void setFitness(double i);

	public int compareTo(Individu o);

	public Individu muter();

	public Individu croiser(Individu alea2);

	public Individu clone();

	public double getFitness();

}