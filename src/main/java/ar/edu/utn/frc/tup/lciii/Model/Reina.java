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
public class Reina extends Pieza {
    public Reina(String color, Posicion pos) {
        super(color, pos);
    }

    public boolean validarMovimiento(Posicion nuevaPosicion, Jugador jugador) {
        boolean valido=false;
        boolean obstaculo = validarObstaculo(nuevaPosicion);
        if(obstaculo) {
            int filaDif = Math.abs(nuevaPosicion.getFila() - this.posicion.getFila());
            int columnaDif = Math.abs(this.posicion.getColumna() - nuevaPosicion.getColumna());

            if (nuevaPosicion.getColumna().equals(this.posicion.getColumna()) ||
                    nuevaPosicion.getFila().equals(this.posicion.getFila()) ||
                    (columnaDif == filaDif)){
                List<Pieza> copiaPiezas = new ArrayList<>(todasLasPiezas);
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
        int filaDif = Math.abs(filaDestino - filaActual);
        int columnaDif = Math.abs(columnaDestino - columnaActual);
        if (filaDif == 0 && columnaDif == 0) {
            return false;
        }
        if (filaDif == 0 || columnaDif == 0) {
            // Movimiento en línea recta (misma fila o misma columna)
            int filaIncremento = (filaDestino - filaActual) / Math.max(filaDif, 1);
            int columnaIncremento = (columnaDestino - columnaActual) / Math.max(columnaDif, 1);
            int fila = filaActual + filaIncremento;
            int columna = columnaActual + columnaIncremento;
            while (fila != filaDestino || columna != columnaDestino) {
                for (Pieza pieza : todasLasPiezas) {
                    if (pieza.getPosicion().getFila() == fila && pieza.getPosicion().getColumna() == columna) {
                        return false;
                    }
                }
                fila += filaIncremento;
                columna += columnaIncremento;
            }
        } else if (filaDif == columnaDif) { // Movimiento en diagonal
            int filaIncremento = (filaDestino - filaActual) / Math.max(filaDif, 1);
            int columnaIncremento = (columnaDestino - columnaActual) / Math.max(columnaDif, 1);
            int fila = filaActual + filaIncremento;
            int columna = columnaActual + columnaIncremento;
            while (fila != filaDestino || columna != columnaDestino) {
                for (Pieza pieza : todasLasPiezas) {
                    if (pieza.getPosicion().getFila() == fila && pieza.getPosicion().getColumna() == columna) {
                        return false;
                    }
                }
                fila += filaIncremento;
                columna += columnaIncremento;
            }
        } else {
            return false; // Movimiento inválido (no es en línea recta ni en diagonal)
        }
        return true; // No hay obstáculos en el recorrido
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
            return "   REINA  ";
        } else {
            return "   reina  ";
        }
    }
}
