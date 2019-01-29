package com.testing.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.testing.base.ConexionBD;
import com.testing.support.Requisito;

public class ConsultasBD {

	PreparedStatement ps = null;
	Connection con;
	ResultSet rs;
	String query;
	
	public List<Requisito> buscarClonTramiteRequisito(int idTramOriginal, int idTramNuevos) {
		con = ConexionBD.conectar();
		query = " select tr.idtramite as id_tram, tr.idrequisito, r.nversion as version_req, r.denominacion as denom_requisito, "
				+ "rf.idformulario as id_form, f.codigo as cod_form, f.nversion as vers_form"
				+ " from tramiterequisito tr "
				+ "inner join requisito r on tr.idrequisito=r.id "
				+ "inner join tramite t on tr.idtramite = t.id "
				+ "inner join requisitoformulario rf on tr.idrequisito=rf.idrequisito " + 
				"inner join formulario f on rf.idformulario = f.id "
				+ "where tr.idtramite=? or tr.idtramite=? "
				+ " order by denom_requisito, tr.idrequisito, t.nversion, id_form, vers_form asc";
		
		try {
			System.out.println("Llega hasta aqui, a llamar a BD.");
			ps = con.prepareStatement(query);
			ps.setInt(1, idTramOriginal);
			ps.setInt(2, idTramNuevos);
			rs = ps.executeQuery();
			List<Requisito> consulta = new ArrayList<>();
			
			while(rs.next()) {
				System.out.println("Se entro al while del ResultSet");
				Requisito filaResult = new Requisito();
				filaResult.setIdTramite(rs.getInt(1));
				filaResult.setIdReq(rs.getInt(2));
				filaResult.setVersionReq(rs.getInt(3));
				filaResult.setDenomReq(rs.getString(4));
				filaResult.setIdForm(rs.getInt(5));
				filaResult.setCodForm(rs.getString(6));
				filaResult.setVerForm(rs.getInt(7));
				consulta.add(filaResult);
				System.out.println("Se agrego a la lista de Resultados.");
			}
			
			return consulta;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}

	public boolean modificarTipoReq(int idReq, int idForm) {
		con = ConexionBD.conectar();
		query = " UPDATE requisitoformulario SET tipo = 'Provisional' where id = ? ";
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, idReq);
			ps.executeUpdate();
			con.commit();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
	}
	
}
