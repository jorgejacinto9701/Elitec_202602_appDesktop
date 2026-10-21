package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Producto;
import util.MySqlDBConexion;

public class ProductoModel {

    public List<Producto> listaProductoPorNombreLike(String nombre) {
        List<Producto> lista = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            // 1. Crear conexion
            conn = MySqlDBConexion.getConexion();

            // 2. Crear sentencia SQL con LIKE
            String sql = "SELECT * FROM producto WHERE nombre LIKE ?";
            pstm = conn.prepareStatement(sql);
            
            // Asignar el parámetro con los comodines '%' para la búsqueda parcial
            pstm.setString(1, "%" + nombre + "%");
            
            System.out.println("SQL: " + pstm.toString());

            // 3. Ejecutar sentencia SQL
            rs = pstm.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setMarca(rs.getString("marca"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                
                lista.add(producto);
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