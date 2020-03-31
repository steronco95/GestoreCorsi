package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {

	private CorsoDAO dao = new CorsoDAO();
	
	public List<Corso> getCorsiByPeriodo(int pd){
		
		
		
		return dao.getCorsiByPeriodo(pd);
	}
	
	public Map<Corso,Integer> getIscrittiByPeriodo(int pd){
		
		
		return dao.getIscrittiByPeriodo(pd);
	}
	
	
	
}
