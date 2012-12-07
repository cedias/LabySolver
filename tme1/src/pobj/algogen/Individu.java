package pobj.algogen;
import java.util.Random;
/**
 * Classe de représentation d'un individu
 */
public class Individu {
    /** valeur propre d'un individu */ 
    private double valeurPropre;
    private double fitness;
    /**
     * cree un nouvel individu au hasard
     */
    public Individu() {
	Random r = new Random();
	valeurPropre = r.nextDouble();
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
}
