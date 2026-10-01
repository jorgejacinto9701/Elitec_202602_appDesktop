package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entity.Director;

public class DirectorModel {

	public int insertaDirector(Director director) {
		int insertados = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1. Crear conexion
			conn = util.MySqlDBConexion.getConexion();

			// 2. Crear sentencia SQL
			String sql = "INSERT INTO director(nombres, dni, email, fechaNacimiento, idTipoDirector) VALUES(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, director.getNombres());
			ps.setString(2, director.getDni());
			ps.setString(3, director.getEmail());
			ps.setDate(4, java.sql.Date.valueOf(director.getFechaNacimiento()));
			ps.setInt(5, director.getTipoDirector().getIdTipoDirector());

			System.out.println("SQL: " + ps.toString());
			
			// 3. Ejecutar sentencia SQL
			insertados = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {}
		 }
		return insertados;
	}
}
