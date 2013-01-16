package agent.laby;

import java.io.IOException;

import pobj.util.Configuration;

import agent.laby.interf.LabyViewer;

public class LabyMainGUI {

	public static void main(String[] args) {
		
		Configuration config = Configuration.getInstance();
		config.setParameterValue(AlgoGenParameter.LABY,"complex.mze");
		String labyFile = "data/mazes/"+config.getParameterValue(AlgoGenParameter.LABY); // args[0];
		Labyrinthe laby;
		try {
			laby = ChargeurLabyrinthe.chargerLabyrinthe(labyFile);
		} catch (IOException e) {
			laby = null;
			System.out.println("Impossible d'ouvrir le fichier "+ labyFile);
			System.exit(1);
		}
		
		new LabyViewer(laby);
	}
}

