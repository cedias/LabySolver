package agent.laby;

import java.awt.Point;

public class CaseDepartNonVideException extends LabyErroneException {

	private static final long serialVersionUID = 1L;

	public CaseDepartNonVideException(Point p) 
	{
		super(p, "La case de depart est non vide");
	}

}
