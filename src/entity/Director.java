package entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Director {

	private int idDirector;
	private String nombres;
	private String dni;
	private String email;
	private LocalDate fechaNacimiento;
	private TipoDirector tipoDirector;
}
