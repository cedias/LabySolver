package pobj.util;


import pobj.algogen.Individu;
import pobj.algogen.Population;
import pobj.algogen.PopulationMyArrayList;
import pobj.algogen.adapter.arith.IndividuExpression;

public class MyArrayListMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PopulationMyArrayList popMyArray = new PopulationMyArrayList();
		Population popNorm = new Population();
		
		for(int i = 0; i<100000;i++) {
			popMyArray.add(new IndividuExpression());
			popNorm.add(new IndividuExpression());
		}
		
		Chrono chr = new Chrono();
		for(Individu ind : popMyArray){};
		chr.stop();
		
		chr = new Chrono();
		for(Individu ind : popNorm){};
		chr.stop();
		
			
		
		

	}

}
