package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import entity.Boleta;
import entity.DetalleBoleta;
import util.MySqlDBConexion;

public class BoletaModel {

	public int inserta(Boleta boletaBean, List<DetalleBoleta> lstDetalle){
	    int salida = -1;
	    Connection conn = null;
	    PreparedStatement pstm1 = null, pstm2 = null, pstm3 = null, pstm4 = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = MySqlDBConexion.getConexion();
	        // Se anula el auto envio
	        conn.setAutoCommit(false);
	        
	        // 1. Se crea el sql de la cabecera (usando LocalDate y Cliente)
	        String sql1 = "insert into boleta values(null, ?, ?)"; // Ajusta según las columnas reales de tu tabla boleta
	        pstm1 = conn.prepareStatement(sql1);
	        pstm1.setTimestamp(1, java.sql.Timestamp.valueOf(LocalDateTime.now()));
	        pstm1.setInt(2, boletaBean.getCliente().getIdCliente());
	        pstm1.executeUpdate();
	    	System.out.println("SQL 1 : " + pstm1);
	    	
	        
	        // 2. Se obtiene el idBoleta insertado
	        String sql2 = "select max(idBoleta) from boleta";
	        pstm2 = conn.prepareStatement(sql2);
	    	System.out.println("SQL 2 : " + pstm2);
	        rs = pstm2.executeQuery();
	        
	        int idBoleta = 0;
	        if (rs.next()) {
	            idBoleta = rs.getInt(1);
	        }
	        
	        // 3. Se inserta el detalle de boleta y se actualiza el stock
	        String sql3 = "insert into boleta_has_producto values(?,?,?,?)";
	        pstm3 = conn.prepareStatement(sql3);
	        
	        String sql4 = "update producto set stock = stock - ? where idproducto = ?";
	        pstm4 = conn.prepareStatement(sql4);
	        
	        for (DetalleBoleta aux : lstDetalle) {
	            // Actualiza el stock
	            pstm4.setInt(1, aux.getCantidad());
	            pstm4.setInt(2, aux.getProducto().getIdProducto()); // Acceso mediante objeto Producto
	            pstm4.executeUpdate();

		    	System.out.println("SQL 4 : " + pstm4);
	            // Inserta el detalle
	            pstm3.setInt(1, aux.getProducto().getIdProducto()); // Acceso mediante objeto Producto
	            pstm3.setInt(2, idBoleta);
	            pstm3.setDouble(3, aux.getPrecio());
	            pstm3.setInt(4, aux.getCantidad());
	            pstm3.executeUpdate();
	            
	            System.out.println("SQL 3 : " + pstm3);
	        }
	        
	        conn.commit();
	        salida = 1; // Éxito
	        
	    } catch (Exception e) {
	        try {
	            if (conn != null) {
	                conn.rollback();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null)   rs.close();
	            if (pstm1 != null) pstm1.close();
	            if (pstm2 != null) pstm2.close();
	            if (pstm3 != null) pstm3.close();
	            if (pstm4 != null) pstm4.close();
	            if (conn != null)  conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return salida;
	}
}
