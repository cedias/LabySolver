package agent.laby;

import java.io.IOException;

import pobj.util.Configuration;

import agent.laby.interf.LabyViewer;

public class LabyMain {

	public static void main(String[] args) {
		Configuration config = Configuration.getInstance();
		config.setParameterValue(AlgoGenParameter.LABY,"all_in.mze");
		String labyFile = "mazes/"+config.getParameterValue(AlgoGenParameter.LABY); // args[0];
		Labyrinthe laby;
		try {
			laby = ChargeurLabyrinthe.chargerLabyrinthe(labyFile);
		} catch (IOException e) {
			laby = null;
			System.exit(1);
		}
		
		new LabyViewer(laby);
	}
}

