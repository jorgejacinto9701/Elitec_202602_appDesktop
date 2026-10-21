package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleBoleta {
	private Boleta boleta;
	private Producto producto;
	private int cantidad;
	private double precio;

}
