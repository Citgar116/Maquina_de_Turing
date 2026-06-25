//NOMBRES: Citlali Garcia Espinoza
//         Lesly Suseth Hernández Luévanos
//BOLETA: 2025670133
//        Aquí pones tu boleta less kajska
//GRUPO:  4CM1 Sistemas Computacionales
//FECHA   23/06/2026
//PROYECTO "MAQUINA DE TURING"  Proyecto 1

import java.util.ArrayList;
class Cinta {
    // La clase cinta nos servirá como la memoria de la máquina de turing
    private ArrayList<Character> celdas; 
    private int checador;
    // La variable checador nos servirá para movernos a lo largo de las celdas
    private char vacio = '_';
    // La variable vacio nos servirá para saber que la celda de la cinta está vacía

    // Cuando se crea la cinta, se pasa el mensaje original 
    public Cinta(String mensajeinicial) {
        // Se pasa cada carácter del mensaje a la lista de celdas
        this.celdas = new ArrayList<>();
        for (char simbolo : mensajeinicial.toCharArray()) {
            this.celdas.add(simbolo);
        }
        this.checador = 0; // Aquí indica al primer carácter de la frase 
    }

    public char leer() {
        // Este método servira para leer el carácter 
        if (checador < 0 || checador >= celdas.size()) {
            return vacio; // Si el checador está fuera de los límites, devuelve el vacío
        }
        return celdas.get(checador); 
    }

    public void escribir(char simbolo) {
        // Este método servirá para escribir un carácter en la cinta
        if (checador >= celdas.size()) {
            celdas.add(simbolo);
        } else if (checador < 0) {
            celdas.add(0, simbolo);
            checador = 0;
        } else {
            celdas.set(checador, simbolo);
        }
    }

    public void moverIzquierda() {
        checador--;
    }

    public void moverDerecha() {
        checador++;
    }

    public void imprimirCinta() {
        // Este método servirá para imprimir la cinta y ver la evolución
        for (int i = 0; i < celdas.size(); i++) {
            if (i == checador) {
                System.out.print("[" + celdas.get(i) + "] "); // Cabezal actual
            } else {
                System.out.print(celdas.get(i) + " "); 
            }
        }
        System.out.println(); 
    }
}

class MaquinaTuring {
    private Cinta cinta;
    private String estadoActual;

    public MaquinaTuring(String mensaje) {
        this.cinta = new Cinta(mensaje);
        this.estadoActual = "q0"; // Estado inicial
    }

    public void ejecutar() {
        System.out.println("Inicio");
        cinta.imprimirCinta();

        // El ciclo while se ejecutará hasta que el estado actual sea el estado final
        while (!estadoActual.equals("qf")) {
            switch (estadoActual) {
                case "q0":
                    // El estado inicial valida que haya datos y pasa a codificar
                    estadoActual = "q1";
                    break;
                case "q1":
                    char simbolo = cinta.leer();
                    if (simbolo == '_') {
                        estadoActual = "qf"; // Si el símbolo es vacío, se pasa al estado final
                    } else {
                        // Si el símbolo no es vacío, se codifica, se escribe y se mueve a la derecha
                        char simboloCodificado = codificar(simbolo);
                        cinta.escribir(simboloCodificado);
                        cinta.moverDerecha(); 
                    }
                    break;
            }
            cinta.imprimirCinta(); // Se imprime la cinta después de cada cambio
        }
        System.out.println("Fin");
    }

  
    private char codificar(char simbolo) {
        if (simbolo >= 'a' && simbolo <= 'z') {
            return (char) (((simbolo - 'a' + 3) % 26) + 'a'); 
        }
        return simbolo; // Si es un espacio u otro símbolo, lo deja igual para no romper el mensaje
    }
}

public class Main {
    public static void main(String[] args) {
        String mensaje = "Nose que poner"; // Mensaje original
        System.out.println("Mensaje original: " + mensaje);
        
        MaquinaTuring maquina = new MaquinaTuring(mensaje);
        maquina.ejecutar(); // Se ejecuta la máquina de turing
    }
}