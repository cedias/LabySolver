package pobj.algogen;
import java.util.Random;
/**
 * Classe de représentation d'un individu
 */
public class Individu implements Comparable<Individu>{
    /** valeur propre d'un individu */ 
    private double valeurPropre;
    private double fitness;
    /**
     * cree un nouvel individu au hasard
     */
    public Individu() {
	valeurPropre = new Random().nextDouble();
	fitness = 0;
    }
     /**
     * cree un nouvel individu avec de le numéro num
     * @param num numero de l'individu
     */
    public Individu(double num) {
	valeurPropre = num;
    }
     /**
     * transforme un individu en chaine de caractere
     */
    public String toString() {
	return "Valeur propre : " + valeurPropre + "\n Fitness :" + fitness;
    }
    /**
     * changer la fitness 
     * @param i nouvelle fitness
     */
    public void setFitness(double i) {
    	fitness = i;
    }
	public double getValeurPropre() {
		return valeurPropre;
	}
    
	public int compareTo(Individu o) {
		return Double.compare(fitness, o.fitness);
	}

	public void muter() {
		valeurPropre = valeurPropre + ((Math.random()>0.5)? valeurPropre * 0.1 : -valeurPropre * 0.1) ;
	}
	
	public Individu croiser(Individu autre){
		return new Individu((valeurPropre + autre.valeurPropre )/ 2);
	}
	
	public Individu cloner(){
		Individu ind = new Individu(valeurPropre);
		ind.setFitness(fitness);
		return ind;
	}
}
