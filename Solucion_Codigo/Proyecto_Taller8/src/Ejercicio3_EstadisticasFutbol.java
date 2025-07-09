/***
 * Se desea realizar una aplicación que permita a un periodista deportivo llevar las estadísticas de los jugadores 
 * de un equipo de fútbol para poder valorar su actuación en el partido.
 * 
 * Cada jugador se identifica por su nombre, número de dorsal y Rut
 * Los jugadores se dividen en tres categorías:
 * 
 * Atacantes
 * Defensores
 * Porteros
 * Para todos los jugadores se desea contabilizar el número de goles marcados, además en el caso de los jugadores 
 * de campo se contabilizan los pases realizados con éxito y el número de balones recuperados. En el caso de los 
 * porteros se contabilizan las atajadas realizadas.
 * 
 * Valoración del jugador
 * Cálculo base para todos los jugadores:
 * valor_goles = goles * 30
 * Valor adicional según tipo de jugador:
 * 
 * Atacantes
 * valor += recuperaciones * 3
 * 
 * Defensores
 * valor += recuperaciones * 4
 * 
 * Porteros
 * valor += atajadas * 5
 * @author Grupo 6 || Jorge Quito || David Gonzalez
 */
public class Ejercicio3_EstadisticasFutbol {
    public static void main(String[] args) {
        Delantero delantero = new Delantero("Mateo Valencia", 11, "18.456.789-0");
        delantero.registrarEstadisticas(3, 12, 6);
        System.out.println(" Delantero ");
        System.out.println(delantero);

        Defensor defensor = new Defensor("Álvaro Torres", 4, "13.987.654-3");
        defensor.registrarEstadisticas(1, 18, 10);
        System.out.println("\n Defensor ");
        System.out.println(defensor);

        Arquero arquero = new Arquero("Emilio Cárdenas", 12, "10.111.222-6");
        arquero.registrarEstadisticas(0, 8);
        System.out.println("\n Arquero ");
        System.out.println(arquero);
    }

    static class Jugador {
        protected String nombre;
        protected int dorsal;
        protected String rut;
        protected int goles;

        public Jugador(String nombre, int dorsal, String rut) {
            this.nombre = nombre;
            this.dorsal = dorsal;
            this.rut = rut;
        }
        public void registrarEstadisticas(int goles) {
            this.goles = goles;
        }
        public int calcularValoracion() {
            return goles * 30;
        }
        @Override
        public String toString() {
            return "Nombre: " + nombre +
                   "\nDorsal: " + dorsal +
                   "\nRUT: " + rut +
                   "\nGoles: " + goles +
                   "\nValoración: " + calcularValoracion();
        }
    }

    static class Delantero extends Jugador {
        protected int pasesExitosos;
        protected int recuperaciones;

        public Delantero(String nombre, int dorsal, String rut) {
            super(nombre, dorsal, rut);
        }

        public void registrarEstadisticas(int goles, int pasesExitosos, int recuperaciones) {
            super.registrarEstadisticas(goles);
            this.pasesExitosos = pasesExitosos;
            this.recuperaciones = recuperaciones;
        }

        @Override
        public int calcularValoracion() {
            return super.calcularValoracion() + recuperaciones * 3;
        }

        @Override
        public String toString() {
            return super.toString() +
                   "\nPases Exitosos: " + pasesExitosos +
                   "\nRecuperaciones: " + recuperaciones;
        }
    }

    static class Defensor extends Jugador {
        protected int pasesExitosos;
        protected int recuperaciones;

        public Defensor(String nombre, int dorsal, String rut) {
            super(nombre, dorsal, rut);
        }

        public void registrarEstadisticas(int goles, int pasesExitosos, int recuperaciones) {
            super.registrarEstadisticas(goles);
            this.pasesExitosos = pasesExitosos;
            this.recuperaciones = recuperaciones;
        }

        @Override
        public int calcularValoracion() {
            return super.calcularValoracion() + recuperaciones * 4;
        }

        @Override
        public String toString() {
            return super.toString() +
                   "\nPases Exitosos: " + pasesExitosos +
                   "\nRecuperaciones: " + recuperaciones;
        }
    }
    static class Arquero extends Jugador {
        protected int atajadas;

        public Arquero(String nombre, int dorsal, String rut) {
            super(nombre, dorsal, rut);
        }
        public void registrarEstadisticas(int goles, int atajadas) {
            super.registrarEstadisticas(goles);
            this.atajadas = atajadas;
        }
        @Override
        public int calcularValoracion() {
            return super.calcularValoracion() + atajadas * 5;
        }
        @Override
        public String toString() {
            return super.toString() +
                   "\nAtajadas: " + atajadas;
        }
    }
}