package entity;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {

    private int idCliente;
    private String nombre;
    private String dni;
    private LocalTime fechaRegistro;
    private int estado;
  

}
