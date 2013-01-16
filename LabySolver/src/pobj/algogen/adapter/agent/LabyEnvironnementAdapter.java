package pobj.algogen.adapter.agent;

import agent.Simulation;
import agent.control.Controleur;
import agent.laby.Labyrinthe;
import pobj.algogen.Environnement;
import pobj.algogen.Individu;

public class LabyEnvironnementAdapter implements Environnement<Controleur> {
	private int nbSteps;
	private Labyrinthe laby;
	
	
	public LabyEnvironnementAdapter(Labyrinthe laby, int nbSteps)
	{
		this.nbSteps = nbSteps;
		this.laby = laby;
	}


	@Override
	public double eval(Individu<Controleur> i) {
		
		if (i instanceof ControleurIndividuAdapter) {
			ControleurIndividuAdapter individu = (ControleurIndividuAdapter) i;
			Simulation simcity = new Simulation(laby.clone(),individu.controleur);
			return simcity.mesurePerf(nbSteps);
		}
		System.out.println("mauvais individu");
		return 0; 
	}

}
