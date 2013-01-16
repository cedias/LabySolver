package agent.laby.interf;

import java.awt.event.ActionEvent;

import agent.laby.Labyrinthe;
import pobj.obs.ISimpleObserver;

public class LabyActivePanel extends LabyPanel implements ISimpleObserver {

	private static final long serialVersionUID = 1L;

	public LabyActivePanel(Labyrinthe laby)
	{
		super(laby);
	}

	@Override
	public void update()
	{
		try { 
			Thread.sleep(100);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		updateGraphics();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//aucun effet !
		return;
	}
}
