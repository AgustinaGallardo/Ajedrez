package ar.edu.utn.frc.tup.lciii.Model;
import ar.edu.utn.frc.tup.lciii.Jugador;
import ar.edu.utn.frc.tup.lciii.Posicion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
public class Torre extends Pieza {
    public Torre(String color, Posicion pos) {
        super(color, pos);
    }

    /**
     *  Validar que no haya obstaculos en el recorrerido q tiene q hacer hasta la pos de destino con validarObs()
     *  Si hay obtaculos en el recorrido valido=false;
     *  si en la posicion de destino la pieza es enemiga metodo comer()
     */
    @Override
    public boolean validarMovimiento(Posicion nuevaPosicion, Jugador jugador) {
        boolean obstaculo = validarObstaculo(nuevaPosicion);
        boolean valido = false;
        if (obstaculo) {
            int filaDif = Math.abs(nuevaPosicion.getFila() - this.posicion.getFila());
            int columnaDif = Math.abs(this.posicion.getColumna() - nuevaPosicion.getColumna());
            // Validar si la posición de destino está a una distancia de una casilla del origen
            if (filaDif >= 1 && columnaDif == 0 || columnaDif >= 1 && filaDif == 0) {
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
        }
        return valido;
    }
    @Override
    public boolean validarObstaculo(Posicion nuevaPosicion) {
        int filaActual = this.posicion.getFila();
        int columnaActual = this.posicion.getColumna();
        int filaDestino = nuevaPosicion.getFila();
        int columnaDestino = nuevaPosicion.getColumna();
        int filaIncremento = 0;
        int columnaIncremento = 0;
        if (filaDestino != filaActual) {
            filaIncremento = (filaDestino - filaActual) / Math.abs(filaDestino - filaActual);
        }
        if (columnaDestino != columnaActual) {
            columnaIncremento = (columnaDestino - columnaActual) / Math.abs(columnaDestino - columnaActual);
        }
        int fila = filaActual + filaIncremento;
        int columna = columnaActual + columnaIncremento;
        while (fila != filaDestino || columna != columnaDestino) {
            for (Pieza pieza : todasLasPiezas) {
                if (pieza.getPosicion().getFila() == fila && pieza.getPosicion().getColumna() == columna) {
                    return false; // Hay un obstáculo en el recorrido
                }
            }
            fila += filaIncremento;
            columna += columnaIncremento;
        }
        return true;
    }
    @Override
    public boolean comer(Posicion nuevaPosicion,Jugador jugador) {
        for (Iterator<Pieza> iterator = todasLasPiezas.iterator(); iterator.hasNext(); ) {
            Pieza pieza = iterator.next();
            if (pieza.getPosicion().equals(nuevaPosicion)) {
                if (pieza.getColor().equals(jugador.getColor())) {
                    return false; // No se puede comer una pieza del mismo color
                } else {
                    iterator.remove(); // Eliminar la pieza de la lista
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public String toString() {
        if (color.equals("BLANCO")) {
            return "  TORRE   ";
        } else {
            return "  torre   ";
        }
    }
}
