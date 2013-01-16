package agent.laby;

import java.io.IOException;

import agent.control.Controleur;
import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.PopulationControleurFactory;
import pobj.util.Configuration;

public class LabyMainConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration config = Configuration.getInstance();
		config.setParameterValue(AlgoGenParameter.LABY, "all_in.mze");
		config.setParameterValue(AlgoGenParameter.NB_GEN, "500");
		config.setParameterValue(AlgoGenParameter.NB_PAS, "103");
		config.setParameterValue(AlgoGenParameter.NB_RULES, "10");
		config.setParameterValue(AlgoGenParameter.TAILLE_POP, "5");
		config.setParameterValue(AlgoGenParameter.TYPE, "controlleur");
		config.setParameterValue(AlgoGenParameter.EVOGENERATIONNEL, "true");
		config.setParameterValue(AlgoGenParameter.SELECT_UNIFORME, "true");
		
		
		String labyFile = "mazes/"+config.getParameterValue(AlgoGenParameter.LABY); // args[0];
		Labyrinthe laby;
		try {
			laby = ChargeurLabyrinthe.chargerLabyrinthe(labyFile);
		} catch (IOException e) {
			laby = null;
			System.exit(1);
		}
		Environnement<Controleur> env = PopulationControleurFactory.createEnvironnement(laby,Integer.parseInt(config.getParameterValue(AlgoGenParameter.NB_PAS)));
	

		int somme = 0;
		for(int i=0 ; i<1000 ; i++)
		{
			System.out.println("iteration :  "+i);
			Population<Controleur> pop = PopulationControleurFactory.createRandomPopulation(
					Integer.parseInt(config.getParameterValue(AlgoGenParameter.TAILLE_POP)),
					Integer.parseInt(config.getParameterValue(AlgoGenParameter.NB_RULES))
					);
			
				pop.evaluer(env);
			
			int cpt = 1;
			while(pop.get(0).getFitness()<103)
			{
				pop = pop.evoluer(env);
				cpt++;
			}
		somme += cpt;
		}
		System.out.println("moyenne du nombre de generations sur 1000 essai pour maximiser fitness");
		System.out.println("moyenne: "+ somme/1000);

	}
}
