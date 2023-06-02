package ar.edu.utn.frc.tup.lciii.Model;
import ar.edu.utn.frc.tup.lciii.Jugador;
import ar.edu.utn.frc.tup.lciii.Posicion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pieza {

    protected String color;
    protected Posicion posicion;
    protected List<Pieza> todasLasPiezas;
    public enum Estado {
        ACTIVO,
        INACTIVO
    }
    public Pieza(String color, Posicion pos){
        this.color=color;
        this.posicion=pos;
    }
    public abstract boolean validarMovimiento(Posicion nuevaPosicion, Jugador jugador);
    public abstract boolean validarObstaculo(Posicion nuevaPosicion);

    /**
     * Si la pieza en la posicion de destino es != a nuetsro color eliminamos de la pieza general
     * y en la lista correspondiente a su color
     */
    public abstract boolean comer(Posicion nuevaPosicion, Jugador jugador);

    /**
     *
     * @param obj
     * @return
     */

    // public abstract boolean equals(Object obj);

    public void mover(Posicion nuevaPosicion, Jugador jugador){
        if(validarMovimiento(nuevaPosicion, jugador)){
            this.posicion=nuevaPosicion;
        }else{
            System.out.println("Movimiento invalido");
        }
    }


}
