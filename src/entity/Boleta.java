package entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Boleta {
	private int idBoleta;
	private Cliente cliente;
	private LocalDate fechaEmision;
	
	
}
