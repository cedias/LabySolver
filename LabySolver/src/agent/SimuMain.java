package agent;

import java.io.IOException;

import agent.control.ControlFactory;
import agent.control.IControleur;
import agent.laby.ChargeurLabyrinthe;
import agent.laby.Labyrinthe;

/**
 * Classe principale pour tester le comportement des agents dans
 *         le labyrinthe.
 *  @author sigaud 
 */
public class SimuMain {

	/**
	 * @param args[0] : nom du fichier contenant le labyrinthe
	 * @param args[1] : nombre de pas d'évaluation
	 * @param args[2] : nombre de règles par controleur
	 */
	public static void main(String[] args) {

		String labyFile = "erreur.mze"; // args[0];
		int nbSteps = 50; // Integer.parseInt(args[1]);
		int nbRules = 10; // Integer.parseInt(args[2]);
		try {
			Labyrinthe laby = ChargeurLabyrinthe.chargerLabyrinthe(labyFile);

			IControleur sc = ControlFactory.createControleur(nbRules);
			Simulation sim = new Simulation(laby, sc);
			System.out.println(sim.mesurePerf(nbSteps));
			System.out.println(sc);

		} catch (IOException e) {
			System.out.println("Problème de chargement du labyrinthe:"+e);
			System.exit(1);
		}
	}
}
