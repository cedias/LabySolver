package agent.laby;

import java.awt.Point;

/**
 * Classe de test de la classe laby
 * @author Charles-Emmanuel Dias
 * @author Marwan Ghanem
 *
 */
public class VerificationLaby {
	
	/**
	 * Verifie si le labyrinthe
	 * 1- est entouré de mur 
	 * 2- la case d'apparition est vide
	 * @param l
	 * @throws LabyErroneException
	 */
	public static void verifierConditions(Labyrinthe l) throws LabyErroneException
	{
		estEntoureDeMurs(l); 
		estCaseInitialeVide(l);
	}
	/*
	 * verifie si la labyrinte est entoure par murs
	 * si c'est le cas il retourner un exception avec le point
	 * @param l
	 * @throws LabyMalEntoureException
	 */
	private static void estEntoureDeMurs(Labyrinthe l) throws LabyMalEntoureException
	{
		// boucle pour parcourir par rapport ou x aka horizontal lines	
		for(int i = 0 ; i < l.Xsize() ; i++ ){
			
			if (l.getContenuCase(i, 0) != ContenuCase.MUR){
				throw new LabyMalEntoureException(new Point(i,0));
			}
			
			if (!(l.getContenuCase(i, l.Ysize()-1)== ContenuCase.MUR)){
				throw new LabyMalEntoureException(new Point(i,l.Ysize()-1));
			}
		}
		// boucle pour parcourir par rapport ou y aka vertical lines
		for(int i = 0 ; i < l.Ysize() ; i++ ) {	

			if (!(l.getContenuCase(0, i)== ContenuCase.MUR)){
				throw new LabyMalEntoureException(new Point(0,i));
			}
			
			if (!(l.getContenuCase(l.Xsize()-1,i)== ContenuCase.MUR)){
				throw new LabyMalEntoureException(new Point(l.Xsize()-1,i));
			}
		}

	}
	/*
	 * verifie si le cas initial est vide
	 * @param l
	 * @throws CaseDepartNonVideException
	 */
	private static void estCaseInitialeVide(Labyrinthe l) throws CaseDepartNonVideException
	{
		if (l.getContenuCase(l.getPositionInitiale()) != ContenuCase.VIDE)
			throw new CaseDepartNonVideException(l.getPositionInitiale());
	}
	/*
	 * corrige le labyrinthe et retourne le nombre d'erreurs trouvées
	 * @param l labyrinthe
	 */
	public static int corrigerLabyrinthe(Labyrinthe l)
	{
		int nberreurs = 0;
		
		while(true){
			
			try {
				verifierConditions(l);
				return(nberreurs);
				
			} catch(LabyErroneException e) {
	
				if(e instanceof LabyMalEntoureException){
					System.out.println("wall");
					l.setContenuCase(e.getPoint(), ContenuCase.MUR);
					nberreurs++;
				} else {
					System.out.println("vide");
					l.setContenuCase(e.getPoint(), ContenuCase.VIDE);
					nberreurs++;
				}
			}
		}
	}
}

