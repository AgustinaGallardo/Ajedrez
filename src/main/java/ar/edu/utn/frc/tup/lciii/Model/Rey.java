package ar.edu.utn.frc.tup.lciii.Model;

import ar.edu.utn.frc.tup.lciii.Jugador;
import ar.edu.utn.frc.tup.lciii.Posicion;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@ToString
public class Rey extends Pieza {
    public Rey(String color, Posicion pos) {
        super(color, pos);
    }

    @Override
    public Posicion getPosicion() {
        return super.getPosicion();
    }

    public void setPosicion(Posicion pos) {
        super.setPosicion(pos);
    }

    /**
     *
     * ESTE ES EL METODO VIEJO LO DEJAMOS ACA POR LAS DUDAS--DESPUES BORRAR
     *   boolean valido=false;
     *
     *         int filaDif=Math.abs(nuevaPosicion.getFila()-this.posicion.getFila());
     *         int columnaDif=Math.abs(this.posicion.getColumna()-nuevaPosicion.getColumna());
     *
     *         if (filaDif <= 1 && columnaDif <= 1){
     *             if(nuevaPosicion.getColumna().equals(this.posicion.getColumna())){
     *                 valido=true;
     *             } else if(nuevaPosicion.getFila().equals(this.posicion.getFila())){
     *                 valido=true;
     *             } else if(columnaDif==filaDif){
     *                 valido=true;
     *             }
     *         }
     *         return valido;
     * @param nuevaPosicion
     * @return
     */
    @Override
    public boolean validarMovimiento(Posicion nuevaPosicion, Jugador jugador) {

        int filaActual = this.posicion.getFila();
        int columnaActual = this.posicion.getColumna();
        int filaDestino = nuevaPosicion.getFila();
        int columnaDestino = nuevaPosicion.getColumna();

        int filaDif = Math.abs(filaDestino - filaActual);
        int columnaDif = Math.abs(columnaDestino - columnaActual);
        boolean movimientoValido = false;

        if (filaDif <= 1 && columnaDif <= 1) {
            // Movimiento válido (máximo una casilla en cualquier dirección)
            List<Pieza> copiaPiezas = new ArrayList<>(todasLasPiezas);
            for (Pieza p : copiaPiezas) {
                if (p.getPosicion().equals(nuevaPosicion)) {
                    if (comer(nuevaPosicion, jugador)) {
                        movimientoValido = true;
                        break; // Salir del bucle ya que encontraste una pieza para comer
                    } else {
                        movimientoValido = false; // Movimiento inválido (más de una casilla de distancia)
                    }
                }
            }
        }
        return movimientoValido;
    }
    @Override
    public boolean validarObstaculo(Posicion nuevaPosicion) {
        return false;
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
    public boolean equals(Object obj) {
        return false;
    }
    @Override
    public String toString() {
        if (color.equals("BLANCO")) {
            return "    REY   ";
        } else {
            return "    rey   ";
        }
    }
}
