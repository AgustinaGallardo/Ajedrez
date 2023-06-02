package ar.edu.utn.frc.tup.lciii.Model;
import ar.edu.utn.frc.tup.lciii.Jugador;
import ar.edu.utn.frc.tup.lciii.PiezaColor;
import ar.edu.utn.frc.tup.lciii.Posicion;
import lombok.Data;
import lombok.ToString;
import java.util.Iterator;
@ToString
@Data
public class Peon extends Pieza {
    public Peon(String color, Posicion pos) {
        super(color, pos);
    }
    @Override
    public boolean validarMovimiento(Posicion nuevaPosicion, Jugador jugador) {
        int filaDif = Math.abs(nuevaPosicion.getFila() - this.posicion.getFila());
        int columnaDif = Math.abs(this.posicion.getColumna() - nuevaPosicion.getColumna());
        if (filaDif == 1 && columnaDif == 0) {
            if (getColor().equals("NEGRO") && posicion.getFila() < nuevaPosicion.getFila()) {
                return true;
            } else if (getColor().equals("BLANCO") && posicion.getFila() > nuevaPosicion.getFila()) {
                return true;
            }
        }
        if (columnaDif == 1 && filaDif == 1) {
            for (Pieza pieza : todasLasPiezas) {
                if (pieza.getPosicion().equals(nuevaPosicion)) {
                    if (!pieza.getColor().equals(this.getColor())) {
                        comer(nuevaPosicion,jugador);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }
        return false;
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
    public String toString() {
        if (color.equals("BLANCO")) {
            return "   PEON   ";
        } else {
            return "   peon   ";
        }
    }

}
