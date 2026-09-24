package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import entity.Concurso;
import util.MySqlDBConexion;

public class ConcursoModel {

	
	public int insertaConcurso(Concurso concurso) {
		int insertados = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
	
		try {
			//1 Se crea la conexion a la Base de Datos
			conn = MySqlDBConexion.getConexion();
			
			//2 Se crea la sentencia SQL a ejecutar
			String sql = "INSERT INTO concurso(nombre, fechaInicio, fechaFin, estado) VALUES(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, concurso.getNombre());
			ps.setDate(2, Date.valueOf(concurso.getFechaInicio()));
			ps.setDate(3, Date.valueOf(concurso.getFechaFin()));
			ps.setString(4, concurso.getEstado());

			//3 Se ejecuta la sentencia SQL
			insertados = ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("Error al insertar el concurso: " + e.getMessage());
		} finally {
			try {
				if (ps != null)	  ps.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar la conexion: " + e.getMessage());
			}
		}
		
		
		return insertados; 
	}
	
}
