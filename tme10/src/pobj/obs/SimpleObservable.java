package pobj.obs;

import java.util.ArrayList;

public class SimpleObservable implements ISimpleObservable {
	private ArrayList<ISimpleObserver> listObserver ;
	
	public SimpleObservable()
	{
		listObserver = new ArrayList<ISimpleObserver>();
	}
	
	@Override
	public void addObserver(ISimpleObserver iSimple)
	{
		listObserver.add(iSimple);
		
	}
	
	@Override
	public void deleteObserver(ISimpleObserver iSimple)
	{
		listObserver.remove(iSimple);
	}
	
	public void notifyObservers()
	{
		for(ISimpleObserver o: listObserver)
			o.update();
	}

}
