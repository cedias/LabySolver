package pobj.util;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

public class MyArrayList<T>  extends AbstractList<T> implements Iterable<T> { 
	private static final int DEFAULT_SIZE = 20;
	private LinkedList<Vector<T>> liste;
	private int capacite;
	
	public MyArrayList (int taille)
	{
		liste = new LinkedList<Vector<T>>();
		capacite = taille;
	}
	
	public MyArrayList()
	{
		this(DEFAULT_SIZE);
	}
	
	public boolean add(T object)
	{
		try{
			Vector<T> last = liste.getLast();
			
			if (last.size() == capacite)
				throw new NoSuchElementException(); //tricky bastard
			
			last.add(object);
			
		} catch (NoSuchElementException e)
		{
			Vector<T> newOne = new Vector<T>(capacite);
			liste.add(newOne);
			newOne.add(object);
		}
		return true;

	}
	
	public T get(int location)
	{
		return liste.get((int)location/capacite).get(location%capacite);
	}
	
	public T set(int location, T object)
	{
		return liste.get((int)location/capacite).set(location%capacite, object);
	}
	
	public int size()
	{
		try{
			Vector<T> last = liste.getLast();
			return (liste.size()-1)*capacite+last.size();
		} catch (NoSuchElementException e)
		{
			return 0;
		}
	}

	@Override
	public Iterator<T> iterator()
	{
		return new MyArrayListIterator<T>(liste);
	}

}

class MyArrayListIterator<T> implements Iterator<T> {

	private Iterator<Vector<T>> listIT;
	private Iterator<T> vectorIT = new Vector<T>().iterator();
	
	public MyArrayListIterator(List<Vector<T>> liste)
	{
		this.listIT = liste.iterator();
	}
	@Override
	public boolean hasNext() {
		return (vectorIT.hasNext() || listIT.hasNext() );
	}

	@Override
	public T next() {
		if(vectorIT == null || !vectorIT.hasNext())
			vectorIT = listIT.next().iterator();
		return vectorIT.next();
	}
	

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
		
	}
	
}
