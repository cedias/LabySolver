package agent.laby;

import java.awt.Point;

public class LabyMalEntoureException extends LabyErroneException {

	private static final long serialVersionUID = 1L;

	public LabyMalEntoureException(Point p) {
		super(p, "Le labyrinthe n'est pas entierement entoure");
	}

}
