
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio6_ConflictosBelicos {

    public static void main(String[] args) {
        SimuladorConflicto simulador = new SimuladorConflicto();
        simulador.menu();
    }
}

abstract class Nacion {

    public String nacion;
    public int cantHabitantes;
    public double recursosEconomicos;
    public int poderMilitar; //Maximo 100
    public boolean conflicto;
    public boolean derrota;
    public boolean altaTecMilitar;
    public ArrayList<Nacion> aliados; 

    public Nacion() {
        aliados = new ArrayList<>();
    }

    public Nacion(String nacion, int cantHabitantes, double recursosEconomicos, int poderMilitar, boolean conflicto, boolean derrota, boolean altaTecMilitar, ArrayList<Nacion> aliados) {
        this.nacion = nacion;
        this.cantHabitantes = cantHabitantes;
        this.recursosEconomicos = recursosEconomicos;
        this.poderMilitar = poderMilitar;
        this.conflicto = conflicto;
        this.derrota = derrota;
        this.altaTecMilitar = altaTecMilitar;
        this.aliados = aliados;
    }
    
    //Metodos abstractos
    public abstract void calcularPoderMilitar();

    public abstract int calcularPoderEconomico();

    public abstract void desarrollarAltaTec();

    public abstract void obtenerAliados(ArrayList<Nacion> disponibles);

    @Override
    public String toString() {
        return "\nNacion: " + nacion
                + "\nHabitantes: " + cantHabitantes
                + "\nRecursos economicos: " + recursosEconomicos
                + "\nPoder militar: " + poderMilitar
                + "\nAlta tecnologia militar: " + (altaTecMilitar ? "Si" : "No")
                + "\nEstado: " + (conflicto ? "En conflicto" : "En paz")
                + (derrota ? " (Derrotada)" : "")
                + "\nAliados: " + aliados.stream().map(a -> a.nacion).toList();
    }
}

class Rusia extends Nacion {

    public Rusia(String nacion, int cantHabitantes, double recursosEconomicos, int poderMilitar, boolean conflicto, boolean derrota, boolean altaTecMilitar, ArrayList<Nacion> aliados) {
        super(nacion, cantHabitantes, recursosEconomicos, poderMilitar, conflicto, derrota, altaTecMilitar, aliados);
    }

    public void calcularPoderMilitar() {
        
        //Variables utilizadas en el metodo
        int bonusAliados, bonusTec;
        //En caso la nacion tenga aliados
        bonusAliados = aliados.size() * 3;
        
        //La tecnologia militar avanzada va a tener un valor de 20 puntos
        bonusTec = altaTecMilitar ? 20 : 0;
        
        //Se suman todos los datos
        poderMilitar = (cantHabitantes / 10000) + this.calcularPoderEconomico() + bonusAliados + bonusTec;
        
        //Maximo un poder militar de 100
        if (poderMilitar > 100) {
            poderMilitar = 100;
        }
    }

    public int calcularPoderEconomico() {
        return (int) (recursosEconomicos / 1000); //Se realiza un cast para evitar valores double
    }
    
    //En caso se quiera desarrollar una tecnologia se va a restar recursos economicos 
    public void desarrollarAltaTec() {
        
        //Se necesita que no tenga una tecnologia
        if ((altaTecMilitar == false) && recursosEconomicos > 50000) {
            altaTecMilitar = true;
            recursosEconomicos -= 50000;
        }
    }
    
    //Metodo para obtener aliados
    public void obtenerAliados(ArrayList<Nacion> disponibles) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nDesea agregar un aliado? (s/n): ");
        String respuesta = teclado.nextLine();
        while (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nombre de la nacion que desea asignar un aliado: ");
            String nombrePrincipal = teclado.nextLine();
            Nacion principal = null;
            for (Nacion n : disponibles) {
                if (n.nacion.equalsIgnoreCase(nombrePrincipal)) {
                    principal = n;
                    break;
                }
            }
            if (principal == null) {
                System.out.println("Nacion no encontrada.");
                return;
            }
            System.out.println("Ingrese el nombre del aliado a asignar para " + principal.nacion + ": ");
            String nombreAliado = teclado.nextLine();
            for (Nacion posible : disponibles) {
                if (posible.nacion.equalsIgnoreCase(nombreAliado) && !posible.equals(principal)) {
                    principal.aliados.add(posible);
                    System.out.println("Aliado asignado correctamente.");
                    break;
                }
            }
            System.out.println("Desea agregar otro aliado? (s/n): ");
            respuesta = teclado.nextLine();
        }
    }
}

//Clases hijas de rusia que ya aplica los metodos abstractos de manera correspondiente
class EEUU extends Rusia {

    public EEUU(String nacion, int cantHabitantes, double recursosEconomicos, int poderMilitar, boolean conflicto, boolean derrota, boolean altaTecMilitar, ArrayList<Nacion> aliados) {
        super(nacion, cantHabitantes, recursosEconomicos, poderMilitar, conflicto, derrota, altaTecMilitar, aliados);
    }
}

class China extends Rusia {

    public China(String nacion, int cantHabitantes, double recursosEconomicos, int poderMilitar, boolean conflicto, boolean derrota, boolean altaTecMilitar, ArrayList<Nacion> aliados) {
        super(nacion, cantHabitantes, recursosEconomicos, poderMilitar, conflicto, derrota, altaTecMilitar, aliados);
    }
}

class CoreaNorte extends Rusia {

    public CoreaNorte(String nacion, int cantHabitantes, double recursosEconomicos, int poderMilitar, boolean conflicto, boolean derrota, boolean altaTecMilitar, ArrayList<Nacion> aliados) {
        super(nacion, cantHabitantes, recursosEconomicos, poderMilitar, conflicto, derrota, altaTecMilitar, aliados);
    }
}


//Clase para simular el conflicto
class SimuladorConflicto {

    public Random random = new Random();
    public Scanner teclado = new Scanner(System.in);

    public void menu() {
        
        //Lista de naciones
        ArrayList<Nacion> naciones = new ArrayList<>();
        
        //Se declaran las naciones
        Rusia rusia = new Rusia("Rusia", 144000000, 100000, 0, false, false, false, new ArrayList<>());
        EEUU eeuu = new EEUU("EEUU", 331000000, 200000, 0, false, false, false, new ArrayList<>());
        China china = new China("China", 1439000000, 180000, 0, false, false, false, new ArrayList<>());
        CoreaNorte corea = new CoreaNorte("Corea del Norte", 26000000, 50000, 0, false, false, false, new ArrayList<>());
        
        //Se agrega todos los objetos en una sola linea de codigo
        naciones.addAll(Arrays.asList(rusia, eeuu, china, corea));

        //For rach para generar los datos de todas las naciones
        for (Nacion n : naciones) {
            n.desarrollarAltaTec();
            n.calcularPoderMilitar();
        }
        
        //Menu interactivo
        int opcion;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Mostrar estado de naciones");
            System.out.println("2. Simular conflicto aleatorio");
            System.out.println("3. Asignar aliados");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    for (Nacion n : naciones) {
                        System.out.println(n);
                    }
                    break;
                case 2:
                    Nacion a = naciones.get(random.nextInt(naciones.size()));
                    Nacion b = naciones.get(random.nextInt(naciones.size()));
                    while (a == b) {
                        b = naciones.get(random.nextInt(naciones.size()));
                    }
                    simularConflicto(a, b);
                    System.out.println("\n--- Resultado del conflicto ---");
                    System.out.println(a);
                    System.out.println(b);
                    break;
                case 3:
                    new Rusia("", 0, 0, 0, false, false, false, new ArrayList<>()).obtenerAliados(naciones);
                    break;
                case 4:
                    System.out.println("Saliendo del simulador.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 4);
    }

    //Se simula el conflicto de dos naciones
    public void simularConflicto(Nacion n1, Nacion n2) {
        n1.conflicto = true;
        n2.conflicto = true;
        
    //Math.abs sirve para realizar una resta y el resultado se hace positivo
        int diff = Math.abs(n1.poderMilitar - n2.poderMilitar);

        if (n1.poderMilitar > n2.poderMilitar) {
            n2.derrota = true;
            n2.cantHabitantes -= (int) (n2.cantHabitantes * 0.05 * diff);
            n2.recursosEconomicos *= 0.90;
        } else if (n2.poderMilitar > n1.poderMilitar) {
            n1.derrota = true;
            n1.cantHabitantes -= (int) (n1.cantHabitantes * 0.05 * diff);
            n1.recursosEconomicos *= 0.90;
        } else {
            n1.recursosEconomicos *= 0.95;
            n2.recursosEconomicos *= 0.95;
        }
    }
}
