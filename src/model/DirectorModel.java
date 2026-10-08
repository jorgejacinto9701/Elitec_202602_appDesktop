package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Director;
import entity.TipoDirector;

public class DirectorModel {

	public int insertaDirector(Director director) {
		int insertados = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1. Crear conexion
			conn = util.MySqlDBConexion.getConexion();

			// 2. Crear sentencia SQL
			String sql = "INSERT INTO director(nombres, dni, email, fechaNacimiento, idTipoDirector, estado) VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, director.getNombres());
			ps.setString(2, director.getDni());
			ps.setString(3, director.getEmail());
			ps.setDate(4, java.sql.Date.valueOf(director.getFechaNacimiento()));
			ps.setInt(5, director.getTipoDirector().getIdTipoDirector());
			ps.setInt(6, director.getEstado());
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
	
	public int acualizarDirector(Director director) {
		int actualizados = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1. Crear conexion
			conn = util.MySqlDBConexion.getConexion();

			// 2. Crear sentencia SQL
			String sql = "UPDATE director SET nombres=?, dni=?, email=?, fechaNacimiento=?, idTipoDirector=?, estado=? WHERE idDirector=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, director.getNombres());
			ps.setString(2, director.getDni());
			ps.setString(3, director.getEmail());
			ps.setDate(4, java.sql.Date.valueOf(director.getFechaNacimiento()));
			ps.setInt(5, director.getTipoDirector().getIdTipoDirector());
			ps.setInt(6, director.getEstado());
			ps.setInt(7, director.getIdDirector());
			System.out.println("SQL: " + ps.toString());

			// 3. Ejecutar sentencia SQL
			actualizados = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return actualizados;
	}
	
	public List<Director> listaTodos() {
		ArrayList<Director> lista = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 1. Crear conexion
			conn = util.MySqlDBConexion.getConexion();
			
			//2. Crear sentencia SQL con Inner Join para obtener los datos del director y su tipo
			String sql = "SELECT d.*, td.descripcion FROM director d INNER JOIN tipoDirector td ON d.idTipoDirector = td.idTipoDirector";
			
			//3. Preparar la sentencia SQL
			ps = conn.prepareStatement(sql);
			System.out.println("SQL: " + ps.toString());
			
			//4. Ejecutar la sentencia SQL
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Director director = new Director();
				director.setIdDirector(rs.getInt("idDirector"));
				director.setNombres(rs.getString("nombres"));
				director.setDni(rs.getString("dni"));
				director.setEmail(rs.getString("email"));
				director.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());

				TipoDirector tipoDirector = new TipoDirector();
				tipoDirector.setIdTipoDirector(rs.getInt("idTipoDirector"));
				tipoDirector.setDescripcion(rs.getString("descripcion"));

				director.setTipoDirector(tipoDirector);
				director.setEstado(rs.getInt("estado"));

				lista.add(director);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}
	
	public List<Director> listaPorNombreLike(String filtro) {
		ArrayList<Director> lista = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 1. Crear conexion
			conn = util.MySqlDBConexion.getConexion();
			
			//2. Crear sentencia SQL con Inner Join para obtener los datos del director y su tipo
			String sql = "SELECT d.*, td.descripcion FROM director d INNER JOIN tipoDirector"
					+ " td ON d.idTipoDirector = td.idTipoDirector where d.nombres like ?";
			
			//3. Preparar la sentencia SQL
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + filtro + "%");
			System.out.println("SQL: " + ps.toString());
			
			//4. Ejecutar la sentencia SQL
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Director director = new Director();
				director.setIdDirector(rs.getInt("idDirector"));
				director.setNombres(rs.getString("nombres"));
				director.setDni(rs.getString("dni"));
				director.setEmail(rs.getString("email"));
				director.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());

				TipoDirector tipoDirector = new TipoDirector();
				tipoDirector.setIdTipoDirector(rs.getInt("idTipoDirector"));
				tipoDirector.setDescripcion(rs.getString("descripcion"));

				director.setTipoDirector(tipoDirector);
				director.setEstado(rs.getInt("estado"));

				lista.add(director);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}
	
	public int eliminarFisico(int idDirector) {
		int eliminados = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1. Crear conexion
			conn = util.MySqlDBConexion.getConexion();

			// 2. Crear sentencia SQL
			String sql = "DELETE FROM director WHERE idDirector=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idDirector);
			System.out.println("SQL: " + ps.toString());

			// 3. Ejecutar sentencia SQL
			eliminados = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return eliminados;
	}
	
	public int actualizaEstado(int idDirector, int estado) {
		int actualizados = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 1. Crear conexion
			conn = util.MySqlDBConexion.getConexion();

			// 2. Crear sentencia SQL
			String sql = "UPDATE director SET estado=? WHERE idDirector=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, estado);
			ps.setInt(2, idDirector);
			System.out.println("SQL: " + ps.toString());

			// 3. Ejecutar sentencia SQL
			actualizados = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return actualizados;
	}
	
	public List<Director> listaPorPk(int idDirector) {
		ArrayList<Director> lista = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 1. Crear conexion
			conn = util.MySqlDBConexion.getConexion();
			
			//2. Crear sentencia SQL con Inner Join para obtener los datos del director y su tipo
			String sql = "SELECT d.*, td.descripcion FROM director d INNER JOIN tipoDirector"
					+ " td ON d.idTipoDirector = td.idTipoDirector where d.idDirector = ?";
			
			//3. Preparar la sentencia SQL
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idDirector);
			System.out.println("SQL: " + ps.toString());
			
			//4. Ejecutar la sentencia SQL
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Director director = new Director();
				director.setIdDirector(rs.getInt("idDirector"));
				director.setNombres(rs.getString("nombres"));
				director.setDni(rs.getString("dni"));
				director.setEmail(rs.getString("email"));
				director.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());

				TipoDirector tipoDirector = new TipoDirector();
				tipoDirector.setIdTipoDirector(rs.getInt("idTipoDirector"));
				tipoDirector.setDescripcion(rs.getString("descripcion"));

				director.setTipoDirector(tipoDirector);
				director.setEstado(rs.getInt("estado"));

				lista.add(director);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}
}





