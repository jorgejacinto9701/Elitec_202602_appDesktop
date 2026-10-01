package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.TipoDirector;
import util.MySqlDBConexion;

public class TipoDirectorModel {

	
	public List<TipoDirector> listarTipoDirector() {
		List<TipoDirector> lista = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			// 1. Crear conexion
			conn = MySqlDBConexion.getConexion();

			// 2. Crear sentencia SQL
			String sql = "SELECT * FROM tipodirector";
			pstm = conn.prepareStatement(sql);
			
			System.out.println("SQL: " + pstm.toString());

			// 3. Ejecutar sentencia SQL
			rs = pstm.executeQuery();

			while (rs.next()) {
				TipoDirector tipoDirector = new TipoDirector();
				tipoDirector.setIdTipoDirector(rs.getInt("idTipoDirector"));
				tipoDirector.setDescripcion(rs.getString("descripcion"));
				lista.add(tipoDirector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)		rs.close();
				if (pstm != null)	pstm.close();
				if (conn != null)	conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}
}
