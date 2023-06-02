package ar.edu.utn.frc.tup.lciii;
import ar.edu.utn.frc.tup.lciii.Model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@ToString
public class Tablero {
    private String[][] tablero;
    private Integer fila = 8;
    private Integer columna = 8;
    private static final String NEGRO = "\u001B[47m ";
    private static final String BLANCO = "\u001B[40m ";
    private static final String RESET = "\u001B[0m";
    private List<Pieza> piezas;

    public Tablero() {
        tablero = new String[fila][columna];
        piezas = new ArrayList<>();
    }
    public void dibujarTablero() {
        StringBuilder sb = new StringBuilder();
        String espacio = "          ";
        sb.append("       0         1          2          3           4          5         6         7  \n");
        for (int f = 0; f < 8; f++) {
            sb.append(f);
            for (int c = 0; c < 8; c++) {
                if ((f + c) % 2 == 0) {
                    sb.append(NEGRO);
                } else {
                    sb.append(BLANCO);
                }
                String valorCelda = tablero[f][c];
                if (valorCelda == null) {
                    valorCelda = espacio; // Cambiar el valor de celda null a una cadena vacía
                }
                sb.append(valorCelda);
            }
            sb.append(RESET); // Restablecer el color al final de cada fila del tablero
            sb.append("  " + f + "\n");
        }
        // Agregar espacios en blanco después de completar el tablero
        for (int i = 8; i < 8; i++) {
            sb.append(i).append(espacio).append("\n");
        }
        sb.append("       0         1          2          3           4          5         6         7  \n");
        System.out.println(sb);
    }
    public void inicializarTablero(List<Pieza> piezasBlancas, List<Pieza> piezasNegras) {
        for (Pieza pieza : piezasBlancas) {
            Posicion pos = pieza.getPosicion();
            int row = pos.getFila();
            int col = pos.getColumna();
            tablero[row][col]= pieza.toString();
        }
        for (Pieza pieza : piezasNegras) {
            Integer row = pieza.getPosicion().getFila();
            Integer col = pieza.getPosicion().getColumna();
            tablero[row][col]=pieza.toString();
        }
    }
    public void borraDelTablero(int fila, int col){
        tablero[fila][col] = "          ";
    }
}
