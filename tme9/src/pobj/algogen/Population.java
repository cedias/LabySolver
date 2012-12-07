package pobj.algogen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import pobj.util.Generateur;
/**
 * Classe de représentation d'une population d'individus
 */
public class Population<T> implements Iterable<Individu<T>> {

    /** ArrayList des individus, ensemble de la population*/
    private ArrayList<Individu<T>> individus;
     /**
     * Construit une population vide
     */
    public Population() {
    	individus = new ArrayList<Individu<T>>();
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
    public void add (Individu<T> individu) {
    	individus.add(individu);
    }
    
    /**
     * change un individu dans la population
     * @param i l'individu a changer
     */
    private void set(int i, Individu<T> ind) {
    	individus.set(i,ind);
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
    public void evaluer(Environnement<T> cible) {
    	for (Individu<T> i : individus) {
    		i.setFitness(cible.eval(i));
    	}
    	Collections.sort(individus);
    	Collections.reverse(individus);
    }
    
    
    public Population<T> reproduire() {
    	boolean mutation = true ;
    	int nbExs = (int) (individus.size() * 0.2);
    	if (nbExs == 0)
    		nbExs = 1;
    	
    	Generateur rand = Generateur.getInstance();
    	Population<T> newPop = new Population<T>();
    	
    	for (int i=0; i<nbExs; i++) {
    		Individu<T> chosenOne = individus.get(i);
    		newPop.add(chosenOne.clone());
    	}
    		
    	for (int i = nbExs ; i < individus.size() ; i++) {
    		Individu<T> alea1 = individus.get(rand.nextInt((int)nbExs));
    		Individu<T> alea2 = individus.get(rand.nextInt((int)nbExs));
    		newPop.add(alea1.croiser(alea2));
    		
    	}
    	
    	for (int i = nbExs ; i < individus.size() ; i++) {
    		double taux = rand.nextDouble();
    		if (taux < 0.05) {
    			if(!mutation){
    				try{
    					newPop.set(i,individus.get(i).croiser(individus.get(i+1)));
    				}catch(IndexOutOfBoundsException e){
    					newPop.set(i,individus.get(i).croiser(individus.get(i-1)));
    				}
    				mutation = true;
    			} else {
    				newPop.set(i,individus.get(i).muter());
    				mutation = false;
    			}
    		}
    	}
    	return newPop;
    }

	public Population<T> evoluer(Environnement<T> cible) {
		Population<T> newGen = reproduire();
    	newGen.evaluer(cible);
    	return newGen;
    }

	public Iterator<Individu<T>> iterator() {
		return individus.iterator();
	}

	public Individu<T> get(int i) {
		return individus.get(i);
	}
}
