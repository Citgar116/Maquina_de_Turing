//NOMBRES: Citlali Garcia Espinoza
//         Lesli Suseth Hernández Luévanos
//BOLETA: 2025670133
//        2025670161
//GRUPO:  4CM1 Sistemas Computacionales
//PROYECTO "MAQUINA DE TURING"  Proyecto 1
import java.util.ArrayList;

class Cinta {
 
    private ArrayList<Character> celdas;
    private int cabezal;  
    private char vacio = '_'; 
 
    public Cinta(String mensaje) {
        celdas = new ArrayList<Character>();
        for (int i = 0; i < mensaje.length(); i++) {
            celdas.add(mensaje.charAt(i));
        }
        cabezal = 0; 
    }

    public char leer() {
        if (cabezal >= celdas.size()) {
            return vacio; 
        }
        return celdas.get(cabezal);
    }
 
    public void escribir(char simbolo) {
        celdas.set(cabezal, simbolo);
    }
 
    public void moverDerecha() {
        cabezal++;
    }

    public String obtenerContenido() {
        String resultado = "";
        for (int i = 0; i < celdas.size(); i++) {
            resultado = resultado + celdas.get(i);
        }
        return resultado;
    }
 
    public void imprimirCinta() {
        for (int i = 0; i < celdas.size(); i++) {
            if (i == cabezal) {
                System.out.print("[" + celdas.get(i) + "] ");
            } else {
                System.out.print(celdas.get(i) + " ");
            }
        }
        System.out.println();
    }
}
 
// Tiene 3 estados:
//   q0 = estado inicial 
//   q1 = estado de codificación que va procesando letra por letra
//   qf = estado final
class MaquinaTuringCodificadora {
 
    private Cinta cinta;
    private String estado;
 
    public MaquinaTuringCodificadora(String mensaje) {
        cinta = new Cinta(mensaje);
        estado = "q0"; 
    }
 
    public String ejecutar() {
        System.out.println(">>> Maquina Codificadora");
        System.out.println("Estado: " + estado);
        cinta.imprimirCinta();
        System.out.println();
     
        while (!estado.equals("qf")) { // La maquina sigue mientras no llegue al estado final
 
            if (estado.equals("q0")) {
                System.out.println("[q0] Entrada recibida. Pasando a codificar...");
                estado = "q1";
 
            } else if (estado.equals("q1")) {
                char leido = cinta.leer();
 
                if (leido == '_') {
                    System.out.println("[q1] Fin del mensaje. Pasando a estado final (qf).");
                    estado = "qf";
 
                } else {
                    // Codificamos la letra y la escribimos en la misma celda
                    char codificado = codificar(leido);
                    System.out.println("[q1] Leyo: '" + leido + "'  ->  Escribe: '" + codificado + "'");
                    cinta.escribir(codificado);
                    cinta.moverDerecha();
                    cinta.imprimirCinta();
                }
            }
        }
 
        String resultado = cinta.obtenerContenido();
        System.out.println("\nMensaje codificado: " + resultado);
        return resultado;
    }

    private char codificar(char letra) {
        if (letra >= 'a' && letra <= 'z') {
            int posicion = letra - 'a';    
            int nuevaPosicion = (posicion + 3) % 26;
            return (char)('a' + nuevaPosicion);
        }
        return letra;
    }
}
 
class MaquinaTuringDecodificadora {
 
    private Cinta cinta;
    private String estado;
 
    public MaquinaTuringDecodificadora(String mensajeCodificado) {
        cinta = new Cinta(mensajeCodificado);
        estado = "q0";
    }
 
    public String ejecutar() {
        System.out.println(">>> Maquina Decodificadora");
        System.out.println("Estado: " + estado);
        cinta.imprimirCinta();
        System.out.println();
 
        while (!estado.equals("qf")) {
 
            if (estado.equals("q0")) {
                System.out.println("[q0] Entrada recibida pasa a decodificar...");
                estado = "q1";
 
            } else if (estado.equals("q1")) {
                char leido = cinta.leer();
 
                if (leido == '_') {
                    System.out.println("[q1] Fin del mensaje. (qf).");
                    estado = "qf";
 
                } else {
                    char decodificado = decodificar(leido);
                    System.out.println("[q1] Leyo: '" + leido + "'  ->  Escribe: '" + decodificado + "'");
                    cinta.escribir(decodificado);
                    cinta.moverDerecha();
                    cinta.imprimirCinta();
                }
            }
        }
 
        String resultado = cinta.obtenerContenido();
        System.out.println("\nMensaje decodificado: " + resultado);
        return resultado;
    }

    private char decodificar(char letra) {
        if (letra >= 'a' && letra <= 'z') {
            int posicion = letra - 'a';
            int nuevaPosicion = (posicion - 3 + 26) % 26;
            return (char)('a' + nuevaPosicion);
        }
        return letra;
    }
}
 
public class Main {
 
    public static void main(String[] args) {
 
        String mensajeOriginal = "hola";

        System.out.println(" MAQUINA DE TURING");
        System.out.println("Mensaje original: " + mensajeOriginal);
        System.out.println();
 
        // --Codifica--
        MaquinaTuringCodificadora maquina1 = new MaquinaTuringCodificadora(mensajeOriginal);
        String mensajeCodificado = maquina1.ejecutar();
 
        // --Decodifica--
        MaquinaTuringDecodificadora maquina2 = new MaquinaTuringDecodificadora(mensajeCodificado);
        String mensajeRecuperado = maquina2.ejecutar();
 
        System.out.println();
        System.out.println("Resumen:");
        System.out.println("  Original:   " + mensajeOriginal);
        System.out.println("  Codificado: " + mensajeCodificado);
        System.out.println("  Recuperado: " + mensajeRecuperado);
    }
}
