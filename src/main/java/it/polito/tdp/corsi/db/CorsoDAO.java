package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class CorsoDAO {

	public boolean esisteCorso(String codins) {
		
		String sql = "select * from corso where codins = ?";
		
try {
			
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
	
			
			if(rs.next()) {
				conn.close();
				return true;
			}else {
				return false;			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}

		
	}

	
	
	public List<Corso> getCorsiByPeriodo(int pd){
		
		String sql = "SELECT * FROM corso WHERE pd = ?";
		
		List<Corso> result = new ArrayList<>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				
				result.add(c);
			}
			
			conn.close();
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public Map<Corso,Integer> getIscrittiByPeriodo(int pd){
		
		String sql = "SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot FROM corso AS c, iscrizione AS i WHERE c.codins = i.codins AND c.pd = ? GROUP BY c.codins,c.nome,c.crediti,c.pd";
		
		Map<Corso,Integer> result = new HashMap<>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				int n = rs.getInt("tot");
				result.put(c,n);
			}
			
			conn.close();
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
		
	}
	
	public List<Studente> getStudentiByCorso(Corso corso){
		
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.cds FROM studente AS s, iscrizione AS i " + 
																	
																"WHERE i.matricola = s.matricola AND i.codins =?";
		
		List <Studente> studenti = new LinkedList<>();
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, corso.getCodins());
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"),rs.getString("nome"),rs.getString("cds"));
				studenti.add(s);
			}
			
		}catch(SQLException e ) {
			throw new RuntimeException(e);
		}	
		
		return studenti;
	}
	
}
