package pobj.algogen.adapter.agent;

import agent.control.ControlFactory;
import agent.control.IControleur;
import pobj.algogen.AbstractIndividu;
import pobj.algogen.Individu;

public class ControleurIndividuAdapter extends AbstractIndividu {
	
	IControleur controleur;
	
	public ControleurIndividuAdapter(IControleur controleur)
	{
		this.controleur = controleur;
	}
	
	public ControleurIndividuAdapter(int i)
	{
		controleur = ControlFactory.createControleur(i);
	}
	
	public IControleur getValeurPropre() {
		return controleur;
	}

	@Override
	public ControleurIndividuAdapter muter() {
		ControleurIndividuAdapter mutant = this.clone();
		mutant.controleur.muter(0.5);
		return mutant;
	}

	@Override
	public ControleurIndividuAdapter croiser(Individu alea2) {
		if (alea2 instanceof ControleurIndividuAdapter) {
			ControleurIndividuAdapter mere = (ControleurIndividuAdapter) alea2;
			return new ControleurIndividuAdapter(controleur.creeFils(mere.controleur,0.5));
		}
		return null;
	}

	@Override
	public ControleurIndividuAdapter clone() {
		return new ControleurIndividuAdapter(controleur);
	}
	
	public String toString()
	{
		return (" FITNESS => " + getFitness()) ;
	}


}
