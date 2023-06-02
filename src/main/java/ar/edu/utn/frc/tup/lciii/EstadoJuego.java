package ar.edu.utn.frc.tup.lciii;

public class EstadoJuego {

    /**
     *
     * Cuando una pieza termina el movmientoValido tiene q comprobar (llamar al verificarJaque())
     * si deja en situacion de jaque al enemigo.- (Si es asi el enemigo solo deberia poder mover el rey.)
     * cuando termina validarJaque() (en el caso q el enemigo no pueda moverse) se dispara un sout de mate y se indica
     * el jugador ganador.-

     */
    public  void verificarJaque()
    {

    }
    /**
     * El jaque mate tiene que verificar que el rey NO pueda moverse a ningun lado sin ser comido por una pieza enemiga
     */
    public  void verificarJaqueMate()
    {

    }
    /**
     * Este metodo se dispara si una pieza come al rey enemigo y dispara un sout con un mensaje
     * que indique el ganador, el tema de los puntos, historial de partidas y llame a jugarDeNuevo().-
     *
     * */
    public void vefificarFinDJuego(){

    }
/**
 * jugarDeNuevo() Se llama desde jaqueMate() true o vefificarFinDJuego()
 * sera un sout que pregunte si se quiere jugar nuevamente, si es false, con un mensaje de despedida,
 * si es true si intercambian los colores e inicia una partida nueva.
 *
 */
public void jugarDeNuevo(){

    }
}
