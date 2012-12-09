package agent.laby.interf;


import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.ControleurIndividuAdapter;
import pobj.algogen.adapter.agent.PopulationControleurFactory;
import agent.control.Controleur;
import agent.laby.Labyrinthe;
import agent.laby.ProgressPanel;


public class CalculThread extends Thread {

		int nbSteps , nbRules , nbGen , nbInv , reachedPointsValue ;
		double efficacityValue;
		int x = 0;
		ControleurIndividuAdapter controleur;
		ProgressPanel progressPanel;
		Labyrinthe laby;
		
	public CalculThread(int nbSteps, int nbRules, int nbGen, int nbInv,
				ProgressPanel progressPanel, Labyrinthe laby) {
			super();
			this.nbSteps = nbSteps;
			this.nbRules = nbRules;
			this.nbGen = nbGen;
			this.nbInv = nbInv;
			this.progressPanel = progressPanel;
			this.laby = laby;
		}


	public void run(){
		progressPanel.setNbMax(nbGen);
		progressPanel.setValue(0);
		Population<Controleur> pop = PopulationControleurFactory.createRandomPopulation(nbInv,nbRules);
		Environnement<Controleur> env = PopulationControleurFactory.createEnvironnement(laby,nbSteps);
		pop.evaluer(env);
		for (int i = 1 ; i < nbGen; i++) {
			progressPanel.setValue(i);
			pop = pop.evoluer(env);
		}
		controleur =  (ControleurIndividuAdapter)pop.get(0);
		reachedPointsValue = ((int)pop.get(0).getFitness());
		efficacityValue = (pop.get(0).getFitness()/laby.getNbPoints()*100) ;
	}

	public int getReachedPointsValue() {
		return reachedPointsValue;
	}


	public double getEfficacityValue() {
		return efficacityValue;
	}


	public ControleurIndividuAdapter getControleur() {
		return controleur;
	}


	public ProgressPanel getProgressPanel() {
		return progressPanel;
	}


	public Labyrinthe getLaby() {
		return laby;
	}
	
	
}
