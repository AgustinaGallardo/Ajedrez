package ar.edu.utn.frc.tup.lciii;
import java.util.Scanner;

public class App
{
    static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args ) {
        Boolean finPartida = false;//Aca se tiene que llamar al estado del juego para comprobar los jaque y si quiere jugar de nuevo
        Jugador jugadorBlanco= new Jugador();
        Jugador jugadorNegro = new Jugador();
        pedirNombreJugador(jugadorBlanco, jugadorNegro);
        Partida partida = new Partida(jugadorBlanco,jugadorNegro);
        partida.iniciarPartida();
        do{
            partida.tomarJugada(jugadorBlanco);
            partida.tomarJugada(jugadorNegro);
        }while(!finPartida);
    }
    public static void pedirNombreJugador(Jugador blanco, Jugador negro){
        System.out.println("Buen comienzo de juego!!!");
        String b="";
        String n ="";
        System.out.println("Ingrese el nombre del jugador con piezas blancas");
        b = scanner.next();
        System.out.println("Ingrese el nombre del jugador con piezas negras");
        n = scanner.next();
        blanco.setNombre(b);
        negro.setNombre(n);
        blanco.setColor("BLANCO");
        negro.setColor("NEGRO");
    }
}
