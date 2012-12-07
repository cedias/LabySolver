package pobj.algogen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * Classe de représentation d'une population d'individus
 */
public class Population {

    /** ArrayList des individus, ensemble de la population*/
    private ArrayList<Individu> individus;
     /**
     * Construit une population vide
     */
    public Population() {
    	individus = new ArrayList<Individu>();
    }
    
     /**
     * Retourne la taille de la population
     */
    public int size() {
    	return individus.size();
    }
    
     /**
     * ajoute un individu dans la population
     * @param individu un individu
     */
    public void add (Individu individu) {
    	individus.add(individu);
    }
    
     /**
     * transforme une population en chaine de caractere
     */
    public String toString() {
    	return individus.toString();
    }
    /**
     * @param cible
     */
    public void evaluer(Environnement cible) {
    	for (Individu i : individus) {
    		i.setFitness(cible.eval(i));
    	}
    	Collections.sort(individus);
    	Collections.reverse(individus);
    }
    
    
    public void reproduire() {
    	boolean mutation = true ;

    	int nbExs = (int) (individus.size() * 0.2);
    	Random rand = new Random();
    	
    	ArrayList<Individu> newPop = new ArrayList<Individu>();
    	
    	for (int i=0; i<nbExs; i++) {
    		Individu chosenOne = individus.get(i);
    		newPop.add(chosenOne.cloner());
    	}
    		
    	for (int i = nbExs ; i < individus.size() ; i++) {
    		Individu alea1 = individus.get(rand.nextInt((int)nbExs));
    		Individu alea2 = individus.get(rand.nextInt((int)nbExs));
    		newPop.add(alea1.croiser(alea2));
    		
    	}
    	
    	for (int i = nbExs ; i < individus.size() ; i++) {
    		double taux = Math.random();
    		if (taux < 0.05) {
    			if(!mutation){
    				newPop.set(i,individus.get(i).croiser(individus.get(i+1)));
    			} else {
    				newPop.get(i).muter();
    			}
    		}
    	}
    	individus = newPop;
    }
    
    public void evoluer(Environnement cible) {
    	reproduire();
    	evaluer(cible);
    }
}
