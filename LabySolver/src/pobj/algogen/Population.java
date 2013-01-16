package pobj.algogen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import agent.laby.AlgoGenParameter;

import pobj.util.Configuration;

/**
 * Classe de représentation d'une population d'individus
 */
public class Population<T> implements Iterable<Individu<T>>{

    /** ArrayList des individus, ensemble de la population*/
    private ArrayList<Individu<T>> individus;
    /** Strategie d'évolution **/
    private IEvolution<T> stratEvolution ;
    
     /**
     * Construit une population vide
     */
    public Population() {
    	
    	Configuration config = Configuration.getInstance();
    	String stratEvo = config.getParameterValue(AlgoGenParameter.EVOGENERATIONNEL);
    	String stratSelection = config.getParameterValue(AlgoGenParameter.SELECT_UNIFORME);
    	IndivSelecteur<T> select = (stratSelection == "true" || stratSelection == null ) ? new SelecteurUniforme<T>() : new SelecteurParFitness<T>();

    	individus = new ArrayList<Individu<T>>();
    	stratEvolution = (stratEvo == "true" || stratEvo == null ) ? new EvolutionGenerationnelle<T>(select) : new EvolutionProgressive<T>(select);
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
     * transforme une population en chaine de caractere
     */
    public String toString() {
    	return individus.toString();
    }
    /**
     * evalue la population dans l'environnement cible
     * @param cible
     */
    public void evaluer(Environnement<T> cible) {
    	for (Individu<T> i : individus) {
    		i.setFitness(cible.eval(i));
    	}
    	Collections.sort(individus);
    	Collections.reverse(individus);
    }
    
    /**
     * retourne la nouvelle génération
     * @return pop nouvelle generation
     */
    public Population<T> reproduire() {
    	return stratEvolution.reproduire(this, 0.05);
    }
 

    /**
     * fait evoluer une population
     * @param cible
     * @return pop l'évolution de population
     */
	public Population<T> evoluer(Environnement<T> cible) {
		Population<T> newGen = reproduire();
    	newGen.evaluer(cible);
    	return newGen;
    }
	
	/**
	 * iterateur sur la population
	 */
	public Iterator<Individu<T>> iterator() {
		return individus.iterator();
	}
	
	/**
	 * récupère l'individu numero i
	 * @param i individu
	 * @return	individu i
	 */
	public Individu<T> get(int i) {
		return individus.get(i);
	}
	
	/**
	 * set l'individu numero i
	 * @param i individu
	 * @return	individu i
	 */
	public void set(int i, Individu<T> ind)
	{
		individus.set(i, ind);
	}
	
	/**
	 * rend la somme des fitness de la population
	 * @return
	 */
	public double getSommeFitnesses() {
		double somme = 0;
		
		for(Individu<T> individu : individus) {
			somme+= individu.getFitness();
		}
		return somme;
		
	}
	
	public Population<T> clone(){
		Population<T> clone = new Population<T>();
		clone.individus = new ArrayList<Individu<T>>();
		for(Individu<T> individu : individus) {
			clone.add(individu.clone());
		}
		return clone;
	}
}
