package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Producto {

    private int idProducto;
    private String nombre;
    private String marca;
    private double precio;
    private int stock;


}