package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Cliente;
import util.MySqlDBConexion;

public class ClienteModel {

    public List<Cliente> listaClientePorNombreLike(String nombre) {
        List<Cliente> lista = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            // 1. Crear conexion
            conn = MySqlDBConexion.getConexion();

            // 2. Crear sentencia SQL con LIKE
            String sql = "SELECT * FROM cliente WHERE nombre LIKE ? order by nombre desc";
            pstm = conn.prepareStatement(sql);
            
            // Asignar el parámetro con los comodines '%' para la búsqueda parcial
            pstm.setString(1, "%" + nombre + "%");
            
            System.out.println("SQL: " + pstm.toString());

            // 3. Ejecutar sentencia SQL
            rs = pstm.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDni(rs.getString("dni"));
                cliente.setEstado(rs.getInt("estado"));
                lista.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)     rs.close();
                if (pstm != null)   pstm.close();
                if (conn != null)   conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lista;
    }
}