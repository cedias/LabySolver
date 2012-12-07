package pobj.algogen;


public abstract class AbstractIndividu implements Individu {
	
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
	
	public int compareTo(Individu o){
		return ((fitness == o.getFitness())? 0 : (fitness > o.getFitness())? 1 : -1 );
	}

	public abstract Individu muter();

	public abstract Individu croiser(Individu alea2);

	public abstract Individu clone();
	
}
