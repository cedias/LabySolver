package agent.control;

import java.util.Random;


/**
 * Composant qui est responsable de produire des Controleurs et règles.
 * 
 * 
 * * Les controleurs utilisent une base de règles basées sur la
 * reconnaissance d'un motif autour de l'agent.
 *
 * On crée des Observation basés sur une chaine de caractères composée de 8 caractère :
 * # : mur, ? : ANY, . : point, ' ' : vide, A : Agent
 * 
 * Les caractères sont interprétés dans la convention horaire: 0 = Nord,
 * 1 = NE, 2 = E, 3 = SE, 4 = S, 5 = SO, 6 = O, 7 = NO
 * 
 * @author Yann
 * 
 */
public class ControlFactory {

	/**
	 * Crée un controleur basé sur la politique Sensor.
	 * 
	 * Une règle Sensor associe à une condition locale (état des cases
	 * environnantes) une direction à prendre. La condition locale est définie
	 * avec des joker ou les élements du labyrinthe (murs, espace, dot).
	 * 
	 * Les règles sont matchées en ordre, la première règle qui reconnait la
	 * situation est déclenchée.
	 * 
	 * Exemple : 
	 * 
	 * @param nbRules
	 *            nombre de règles du controleur (peu d'effet au-delà de 10)
	 * @return un controleur aléatoire muni du nombre de règles demandées.
	 */
	public static IControleur createControleur(int nbRules) {
		Controleur c = new Controleur();
		for (int i = 0; i < nbRules; i++) {
			c.add(generateRandomRule());
		}
		return c;
	}

	/**
	 * Cree un controleur qui court vers la droite.
	 * @return un controleur qui fonce toujours à droite.
	 */
	public static IControleur createControleurDroitier () {
		Controleur c = new Controleur();
		c.add(new Regle(new Observation("????????"),Direction.DROITE));
		return c;
	}
	
	/**
	 * Cree un controleur "smart"
	 * @return un controleur louche
	 */
	public static IControleur createControleurSmart()
	{
		Controleur c = new Controleur();
		//toujours aller vers le point !
		c.add(new Regle(new Observation(".? ? ? ?"),Direction.HAUT));
		c.add(new Regle(new Observation(" ?.? ? ?"),Direction.DROITE));
		c.add(new Regle(new Observation(" ? ?.? ?"),Direction.BAS));
		c.add(new Regle(new Observation(" ? ? ?.?"),Direction.GAUCHE));
		//si bloqué
		c.add(new Regle(new Observation("?#######"),Direction.HAUT));
		c.add(new Regle(new Observation("##?#####"),Direction.DROITE));
		c.add(new Regle(new Observation("####?###"),Direction.BAS));
		c.add(new Regle(new Observation("######?#"),Direction.GAUCHE));
		//sinon on longe le mur
		c.add(new Regle(new Observation("#? ?????"),Direction.DROITE));
		c.add(new Regle(new Observation("??#? ???"),Direction.BAS));
		c.add(new Regle(new Observation("????#? ?"),Direction.GAUCHE));
		c.add(new Regle(new Observation(" ?????#?"),Direction.HAUT));
		
		return c;
	}
	
	/**
	 * Cree un controleur qui longe les murs en grignotant les bonus.
	 * @return un controleur qui essaie de longer les murs.
	 */
	public static IControleur createControleurLongeMur () {
		Controleur c = new Controleur();
		c.add(new Regle(new Observation("#?.?????"),Direction.DROITE));
		c.add(new Regle(new Observation("??#?.???"),Direction.BAS));
		c.add(new Regle(new Observation("????#?.?"),Direction.GAUCHE));
		c.add(new Regle(new Observation(".?????#?"),Direction.HAUT));
//		System.out.println(c);
		return c;
	}
	
	
	/*******************************************
	 * FIN DE LA SECTION PUBLIC *****************
	 ********************************************/
	
	// un generateur aléatoire (pour nextInt())
	private static Random generateur = new Random();

	/**
	 * Crée une règle aléatoire  de type Sensor basée sur un motif aléatoire.
	 * @return : la règle créée
	 */
	static Regle generateRandomRule() {

		Direction direction = Direction.values()[generateur.nextInt(4)];
		
		StringBuffer obs = new StringBuffer();

		for (int i = 0; i < 8; i++) {
			double rand = Math.random();
			if (rand > 0.20)
				// 80% joker
				obs.append('?'); // ContenuCase.ANY
			else if (rand > 0.15)
				// 5% mur
				obs.append('#'); // ContenuCase.MUR
			else if (rand > 0.05)
				// 10% bonus
				obs.append('.'); //  ContenuCase.POINT
			else
				// 5% blanc
				obs.append(' '); //  ContenuCase.VIDE
		}
		Observation observation = new Observation(obs.toString());
		
		return new Regle (observation, direction);
	}


}
