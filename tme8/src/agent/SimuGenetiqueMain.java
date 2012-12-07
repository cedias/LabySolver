package agent;

import java.io.IOException;

import pobj.algogen.Environnement;
import pobj.algogen.Population;
import pobj.algogen.adapter.agent.PopulationControleurFactory;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;

public class SimuGenetiqueMain {
	public static void main(String[] args)
	{
		String labyFile = "mazes/all_in.mze"; // args[0];
		int nbSteps = 103; // Integer.parseInt(args[1]);
		int nbRules = 10; // Integer.parseInt(args[2]);
		int size = 6;
		
		Labyrinthe laby;
		try {
			laby = ChargeurLabyrinthe.chargerLabyrinthe(labyFile);
		} catch (IOException e) {
			System.out.println("Problème de chargement du labyrinthe:"+e);
			laby = null;
			System.exit(1);
		}
		
		Population pop = PopulationControleurFactory.createRandomPopulation(size,nbRules);
		Environnement env = PopulationControleurFactory.createEnvironnement(laby,nbSteps);
		pop.evaluer(env);
		System.out.println("-------------original gen----------");
		System.out.println(pop.toString());
		
		for (int i = 1 ; i < 1000; i++) {
			System.out.println("------------- gen " + i + "----------");
			pop = pop.evoluer(env);
			System.out.println(pop.toString());
		}
	}
}



