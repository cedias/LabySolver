package agent.laby;

import java.awt.Point;

public class LabyErroneException extends Exception {

	private static final long serialVersionUID = 1L;
	private Point point;
	/**
	 * Constructor 
	 * @param p
	 * @param s
	 */
	public LabyErroneException(Point p,String s)
	{
		super(s);
		this.point = p;
	}
	public Point getPoint()
	{
		return this.point;
	}

}
