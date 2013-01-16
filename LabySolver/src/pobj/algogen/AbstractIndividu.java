package pobj.algogen;


public abstract class AbstractIndividu<T> implements Individu<T> {
	
	private double fitness;

	public AbstractIndividu()
	{
		this.fitness = 0;
	}
	
	public void setFitness(double i) {
		fitness = i;
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public int compareTo(Individu<T> o){
		return ((fitness == o.getFitness())? 0 : (fitness > o.getFitness())? 1 : -1 );
	}

	public abstract Individu<T> muter();

	public abstract Individu<T> croiser(Individu<T> alea2);

	public abstract Individu<T> clone();
	
}
