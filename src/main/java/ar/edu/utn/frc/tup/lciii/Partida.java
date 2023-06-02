package ar.edu.utn.frc.tup.lciii;
import ar.edu.utn.frc.tup.lciii.Model.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
public class Partida {
    private Jugador blanco;
    private Jugador negro;
    private Tablero tablero;
    private List<Pieza> piezasBlancas;
    private List<Pieza> piezasNegras;
    private List<Pieza> todasLasPiezas;
    private Scanner scanner = new Scanner(System.in);

    private String piezaColorMarron(String s) {
        return "\u001B[0;33m" + s + "\u001B[0m";
    }

    private String piezaColorClaro(String s) {
        return "\u001B[0;33;93m" + s + "\u001B[0m";
    }

    public Partida(Jugador blanco, Jugador negro) {
        this.blanco = blanco;
        this.negro = negro;
        this.tablero = new Tablero();
        this.piezasBlancas = null;
        this.piezasNegras = null;
        this.todasLasPiezas = new ArrayList<>();
    }

    public void iniciarPartida() {

        this.piezasBlancas = inicializarPiezas(PiezaColor.Color.BLANCO.toString());
        this.piezasNegras = inicializarPiezas(PiezaColor.Color.NEGRO.toString());
        this.todasLasPiezas = cargarTodasLasPiezas(piezasBlancas, piezasNegras);
        this.tablero.inicializarTablero(this.piezasBlancas, this.piezasNegras);
        this.tablero.dibujarTablero();
        System.out.println("Recorda que si tus piezas son BLANCAS vas a ver tus piezas en MAYUSCULA y las piezas negras en minuscula");
    }

    public List<Pieza> inicializarPiezas(String color) {

        List<Pieza> piezas = new ArrayList<>();

        if (color.equals(PiezaColor.Color.NEGRO.toString())) {
            // PIEZAS NEGRAS
            piezas.add(new Torre(PiezaColor.Color.NEGRO.toString(), new Posicion(0, 0)));
            piezas.add(new Caballo(PiezaColor.Color.NEGRO.toString(), new Posicion(0, 1)));
            piezas.add(new Alfil(PiezaColor.Color.NEGRO.toString(), new Posicion(0, 2)));
            piezas.add(new Reina(PiezaColor.Color.NEGRO.toString(), new Posicion(0, 3)));
            piezas.add(new Rey(PiezaColor.Color.NEGRO.toString(), new Posicion(0, 4)));
            piezas.add(new Alfil(PiezaColor.Color.NEGRO.toString(), new Posicion(0, 5)));
            piezas.add(new Caballo(PiezaColor.Color.NEGRO.toString(), new Posicion(0, 6)));
            piezas.add(new Torre(PiezaColor.Color.NEGRO.toString(), new Posicion(0, 7)));

           for (int i = 0; i < 8; i++) {
               piezas.add(new Peon(PiezaColor.Color.NEGRO.toString(), new Posicion(1, i)));
            }
        } else {
            // PIEZAS BLANCAS
            piezas.add(new Torre(PiezaColor.Color.BLANCO.toString(), new Posicion(7, 0)));
            piezas.add(new Caballo(PiezaColor.Color.BLANCO.toString(), new Posicion(7, 1)));
            piezas.add(new Alfil(PiezaColor.Color.BLANCO.toString(), new Posicion(7, 2)));
            piezas.add(new Reina(PiezaColor.Color.BLANCO.toString(), new Posicion(7, 3)));
            piezas.add(new Rey(PiezaColor.Color.BLANCO.toString(), new Posicion(7, 4)));
            piezas.add(new Alfil(PiezaColor.Color.BLANCO.toString(), new Posicion(7, 5)));
            piezas.add(new Caballo(PiezaColor.Color.BLANCO.toString(), new Posicion(7, 6)));
            piezas.add(new Torre(PiezaColor.Color.BLANCO.toString(), new Posicion(7, 7)));

            for (int i = 0; i < 8; i++) {
                piezas.add(new Peon(PiezaColor.Color.BLANCO.toString(), new Posicion(6, i)));
            }
        }
        return piezas;
    }

    public List<Pieza> cargarTodasLasPiezas(List<Pieza> piezasBlancas, List<Pieza> piezasNegras) {
        //setearle a cada pieza de cada lista su atributo de lista
        List<Pieza> todasPiezas = new ArrayList<>();
        todasPiezas.addAll(piezasBlancas);
        todasPiezas.addAll(piezasNegras);

        for (Pieza pieza : todasPiezas) {
            pieza.setTodasLasPiezas(todasPiezas);
        }
        return todasPiezas;
    }

    public void tomarJugada(Jugador jugador) {
        Posicion posDestino = new Posicion(0, 0);
        Posicion posOrigen = new Posicion(0, 0);
        boolean movimientoValido = false;
        boolean coordenadaValida = true;
        boolean coordenadaValidaDestino = true;
        int filaOrigen = 0;
        int columnaOrigen = 0;
        int filaDestino = 0;
        int columnaDestino = 0;

        while (!movimientoValido) {
           do{
               System.out.println("Ingrese las coordenadas de la pieza que desea mover " + jugador.getNombre());
                 filaOrigen = scanner.nextInt();
                 columnaOrigen = scanner.nextInt();
                    if(filaOrigen < 0 || filaOrigen > 7 || columnaOrigen < 0 || columnaOrigen > 7){
                        System.out.println("La coordenada ingresada es invalida. Intende de nuevo");
                        coordenadaValida =false;
                    }
           }while(!coordenadaValida);
            posOrigen.setFila(filaOrigen);
            posOrigen.setColumna(columnaOrigen);
            tablero.borraDelTablero(filaOrigen, columnaOrigen);
            List<Pieza> piezas = jugador.getColor().equals(PiezaColor.Color.BLANCO.toString()) ? piezasBlancas : piezasNegras;
            for (Pieza pieza : piezas) {
                if (posOrigen.equals(pieza.getPosicion())) {
                   do {
                       System.out.println("Ingrese las coordenadas a donde quiere mover la pieza: " + pieza);
                       filaDestino = scanner.nextInt();
                       columnaDestino = scanner.nextInt();
                       if(filaDestino < 0 || filaDestino > 7 || columnaDestino < 0 || columnaDestino > 7){
                           System.out.println("La coordenada ingresada es invalida. Intende de nuevo");
                           coordenadaValidaDestino =false;
                       }
                   }while(!coordenadaValidaDestino);
                    posDestino.setFila(filaDestino);
                    posDestino.setColumna(columnaDestino);
                    if (pieza.validarMovimiento(posDestino, jugador)) {
                        pieza.setPosicion(posDestino);
                        piezasNegras = cargarListasActualizadasNegras(pieza);
                        piezasBlancas = cargarListasActualizadasBlanca(pieza);
                        movimientoValido = true;
                        break;
                    } else {
                        System.out.println("Movimiento inválido.");
                        break;
                    }
                }
            }
            if (!movimientoValido) {
                System.out.println("Por favor, ingrese las coordenadas de la pieza que quiere mover nuemavente.");
            }
        }
        this.tablero.inicializarTablero( this.piezasBlancas, this.piezasNegras);
        System.out.println( this.piezasBlancas.size());
        System.out.println( this.piezasNegras.size());
        this.tablero.dibujarTablero();
    }
    public List<Pieza> cargarListasActualizadasBlanca(Pieza pieza) {
        piezasBlancas.clear();
        for (Pieza p : pieza.getTodasLasPiezas()) {
            if (p.getColor().equals("BLANCO")) {
                piezasBlancas.add(p);
            }
        }
        return piezasBlancas;
    }
    public List<Pieza> cargarListasActualizadasNegras(Pieza pieza) {
        piezasNegras.clear();
        for (Pieza p : pieza.getTodasLasPiezas()) {
            if (p.getColor().equals("NEGRO")) {
                piezasNegras.add(p);
            }
        }
        return piezasNegras;
    }
}