package pobj.algogen.adapter.agent;

import agent.Simulation;
import agent.laby.Labyrinthe;
import pobj.algogen.Environnement;
import pobj.algogen.Individu;

public class LabyEnvironnementAdapter implements Environnement {
	private int nbSteps;
	private Labyrinthe laby;
	
	
	public LabyEnvironnementAdapter(Labyrinthe laby, int nbSteps)
	{
		this.nbSteps = nbSteps;
		this.laby = laby;
	}


	@Override
	public double eval(Individu i) {
		if(i instanceof ControleurIndividuAdapter) {
			ControleurIndividuAdapter individu = (ControleurIndividuAdapter) i;
			Simulation simcity = new Simulation(laby.clone(),individu.controleur);
			return simcity.mesurePerf(nbSteps);
		}
		System.out.println("mauvais individu");
		return 0; 
	}

}
