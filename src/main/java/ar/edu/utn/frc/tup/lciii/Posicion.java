package ar.edu.utn.frc.tup.lciii;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posicion {
    private Integer fila;
    private Integer columna;

    //Validacion de que en la posicion haya una pieza
    public boolean equals(Object obj){
        Posicion position = (Posicion) obj;
      return this.fila.equals(position.getFila())&&this.columna.equals(position.getColumna());
   }
}
