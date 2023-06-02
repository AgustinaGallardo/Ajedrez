package ar.edu.utn.frc.tup.lciii.Model;

import ar.edu.utn.frc.tup.lciii.Jugador;
import ar.edu.utn.frc.tup.lciii.Posicion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Alfil extends Pieza {

    public Alfil(String color, Posicion pos) {
        super(color, pos);
    }

    @Override
    public Posicion getPosicion() {
        return super.getPosicion();
    }

    /**
     * Antes de validar el movimiento tengo q validar el jaque,
     * si mi lista esta situacion de jaque solo puedo mvoer el rey y ponerlo seguro
     *
     * @param nuevaPosicion
     * @param jugador
     * @return
     */



    public boolean validarMovimiento(Posicion nuevaPosicion, Jugador jugador) {
        //primero validar que vaya en diagonal
        //despues validar si hay obstaculo

        boolean obstaculo = validarObstaculo(nuevaPosicion);
        boolean valido = false;

        if(obstaculo) {
            int filaDif = Math.abs(nuevaPosicion.getFila() - this.posicion.getFila());
            int columnaDif = Math.abs(nuevaPosicion.getColumna() - this.posicion.getColumna());

            if (filaDif == columnaDif) {
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

        int filaIncremento = (filaDestino - filaActual) / Math.max(Math.abs(filaDestino - filaActual), 1);
        int columnaIncremento = (columnaDestino - columnaActual) / Math.max(Math.abs(columnaDestino - columnaActual), 1);

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

        return true;
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
    public String toString() {
        if (color.equals("BLANCO")) {
            return "  ALFIL   ";
        } else {
            return "  alfil   ";
        }
    }
}
