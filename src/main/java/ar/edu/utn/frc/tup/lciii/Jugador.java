package ar.edu.utn.frc.tup.lciii;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Jugador {
    private String nombre;
    private String color;
    private Integer puntaje;
    private String historialPartidas;
    public Jugador(String nombre, String color)
    {
        this.nombre = nombre;
        this.color = color;
    }
}
