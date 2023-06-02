package ar.edu.utn.frc.tup.lciii.Model;
import ar.edu.utn.frc.tup.lciii.Jugador;
import ar.edu.utn.frc.tup.lciii.Posicion;
import lombok.Data;
import lombok.ToString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@ToString
@Data
public class Caballo extends Pieza {
    public Caballo(String color, Posicion pos) {
        super(color, pos);
    }
    @Override
    public boolean validarMovimiento(Posicion nuevaPosicion, Jugador jugador) {
        boolean valido=false;
        int filaDif=Math.abs(nuevaPosicion.getFila()-this.posicion.getFila());
        int columnaDif=Math.abs(this.posicion.getColumna()-nuevaPosicion.getColumna());
        if(filaDif==2 && columnaDif==1 || filaDif==1 &&columnaDif==2) {
            List<Pieza> copiaPiezas = new ArrayList<>(todasLasPiezas); // Crear una copia de la lista
            for (Pieza pieza : copiaPiezas) {
                if (pieza.getPosicion().equals(nuevaPosicion)) {
                    if (comer(nuevaPosicion, jugador)) {
                        valido = true;
                    } else {
                        return valido;
                    }
                }
            }
            valido = true;
        }
        return valido;
    }
    @Override
    public boolean comer(Posicion nuevaPosicion,Jugador jugador) {
        for (Iterator<Pieza> iterator = todasLasPiezas.iterator(); iterator.hasNext(); ) {
            Pieza pieza = iterator.next();
                if (pieza.getPosicion().equals(nuevaPosicion)) {
                    if (pieza.getColor().equals(jugador.getColor())) {
                        return false;
                    } else {
                        iterator.remove();
                         return true;
                    }
                }
        }
        return false;
    }
    @Override
    public boolean validarObstaculo(Posicion nuevaPosicion) {
        return false;
    }
     @Override
    public String toString() {
        if (color.equals("BLANCO")) {
            return " CABALLO  ";
        } else {
            return " caballo  ";
        }
    }
}