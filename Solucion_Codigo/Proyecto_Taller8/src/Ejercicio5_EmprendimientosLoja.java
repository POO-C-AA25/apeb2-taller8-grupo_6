import java.util.Scanner;
import java.util.ArrayList;

public class Ejercicio5_EmprendimientosLoja {
    public static void main(String[] args) {
        ControladorEmprendimiento controlador = new ControladorEmprendimiento();
        controlador.iniciarSistema();
    }
}

//Clases abstractas
abstract class Emprendimiento {
    public String tipo;
    public String mision;
    public ArrayList<String> productoServicio;
    public String contacto;
    public ArrayList<Sede> sedes;
    public Mentor mentor;
    public VistaEmprendimiento vista;

    public Emprendimiento() {
        this.productoServicio = new ArrayList<>();
        this.sedes = new ArrayList<>();
    }

    public Emprendimiento(String tipo, String mision, ArrayList<String> productoServicio, String contacto, ArrayList<Sede> sedes, Mentor mentor, VistaEmprendimiento vista) {
        this.tipo = tipo;
        this.mision = mision;
        this.productoServicio = productoServicio;
        this.contacto = contacto;
        this.sedes = sedes;
        this.mentor = mentor;
        this.vista = vista;
    }

    // Metodo abstracto
    public abstract void crecimiento();

    @Override
    public String toString() {
        return "\nEmprendimiento:" +
               "\nTipo: " + tipo +
               "\nMision: " + mision +
               "\nProducto(s) o servicio(s): " + productoServicio +
               "\nContacto: " + contacto +
               "\nSedes: " + sedes.toString().replace(",", "") +
               "\n" + mentor;
    }
}

abstract class Evento {
    public String tipo;
    public String tematica;
    public ArrayList<Emprendimiento> participantes;

    public Evento() {
        this.participantes = new ArrayList<>();
    }

    public Evento(String tipo, String tematica, ArrayList<Emprendimiento> participantes) {
        this.tipo = tipo;
        this.tematica = tematica;
        this.participantes = participantes;
    }

    // Metodo abstracto
    public abstract void participantesEvento(ArrayList<Emprendimiento> EmpPostulados);

    @Override
    public String toString() {
        return "\nEvento:" +
               "\nTipo de evento: " + tipo +
               "\nTematica del evento: " + tematica;
    }
}

// Clases heredadas de Emprendimiento
class EmprendimientoTec extends Emprendimiento {
    public EmprendimientoTec() {}

    public EmprendimientoTec(String tipo, String mision, ArrayList<String> productoServicio, String contacto, ArrayList<Sede> sedes, Mentor mentor, VistaEmprendimiento vista) {
        super(tipo, mision, productoServicio, contacto, sedes, mentor, vista);
    }

    @Override
    public void crecimiento() {
        String tipo = vista.tipoCrecimiento();

        if (tipo.equalsIgnoreCase("1")) {
            System.out.println("Crecimiento: aumento de capital para ampliar operaciones.");
        } else if (tipo.equalsIgnoreCase("2")) {
            String nuevoProducto = vista.pedirNuevoProducto();
            productoServicio.add(nuevoProducto);
            System.out.println("Producto agregado exitosamente.");
        } else if (tipo.equalsIgnoreCase("3")) {
            Sede nueva = vista.pedirDatosSede();
            sedes.add(nueva);
            System.out.println("Nueva sede registrada correctamente.");
        } else {
            System.out.println(vista.mensaje());
        }
    }
}

class EmprendimientoAgro extends Emprendimiento {
    public EmprendimientoAgro() {}

    public EmprendimientoAgro(String tipo, String mision, ArrayList<String> productoServicio, String contacto, ArrayList<Sede> sedes, Mentor mentor, VistaEmprendimiento vista) {
        super(tipo, mision, productoServicio, contacto, sedes, mentor, vista);
    }

    @Override
    public void crecimiento() {
        String tipo = vista.tipoCrecimiento();

        if (tipo.equalsIgnoreCase("1")) {
            System.out.println("Crecimiento: inversión en infraestructura agrícola.");
        } else if (tipo.equalsIgnoreCase("2")) {
            String nuevoProducto = vista.pedirNuevoProducto();
            productoServicio.add(nuevoProducto);
            System.out.println("Nuevo cultivo agregado exitosamente.");
        } else if (tipo.equalsIgnoreCase("3")) {
            Sede nueva = vista.pedirDatosSede();
            sedes.add(nueva);
            System.out.println("Nueva sede agrícola registrada.");
        } else {
            System.out.println(vista.mensaje());
        }
    }
}

// Clases heredadas de Evento
class FeriaTecnologica extends Evento {
    public FeriaTecnologica() {}

    public FeriaTecnologica(String tipo, String tematica, ArrayList<Emprendimiento> participantes) {
        super(tipo, tematica, participantes);
    }

    @Override
    public void participantesEvento(ArrayList<Emprendimiento> EmpPostulados) {
        for (Emprendimiento e : EmpPostulados) {
            if (e.tipo.equalsIgnoreCase("tecnologico")) {
                participantes.add(e);
            }
        }
    }
    
}

class FeriaAgricola extends Evento {
    public FeriaAgricola() {}

    public FeriaAgricola(String tipo, String tematica, ArrayList<Emprendimiento> participantes) {
        super(tipo, tematica, participantes);
    }

    @Override
    public void participantesEvento(ArrayList<Emprendimiento> EmpPostulados) {
        for (Emprendimiento e : EmpPostulados) {
            if (e.tipo.equalsIgnoreCase("agricola")) {
                participantes.add(e);
            }
        }
    }
}

class Mentor {
    public String nombre;
    public String areaTrabajo;

    public Mentor() {}

    public Mentor(String nombre, String areaTrabajo) {
        this.nombre = nombre;
        this.areaTrabajo = areaTrabajo;
    }

    @Override
    public String toString() {
        return "Mentor:" +
               "\nNombre: " + nombre +
               "\nArea de trabajo: " + areaTrabajo + ".";
    }
}

class Sede {
    public String nombre;
    public String direccion;
    public String gerente;
    public int cantidadEmpleados;

    public Sede() {}

    public Sede(String nombre, String direccion, String gerente, int cantidadEmpleados) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.gerente = gerente;
        this.cantidadEmpleados = cantidadEmpleados;
    }

    @Override
    public String toString() {
        return "Sede:" +
               "\nNombre de la sede: " + nombre +
               "\nDireccion: " + direccion +
               "\nGerente a cargo: " + gerente +
               "\nCantidad de empleados: " + cantidadEmpleados + ".";
    }
}

class VistaEmprendimiento {
    Scanner teclado = new Scanner(System.in);

    public String tipoCrecimiento() {
        System.out.println("""
            Posibles crecimientos:
            1) Aumentar el tamaño del emprendimiento.
            2) Agregar nuevo producto o servicio.
            3) Apertura de sedes.
            """);
        System.out.print("Selecciona el número de la opción de crecimiento: ");
        return teclado.nextLine();
    }

    public String mensaje() {
        return "Opcion no disponible";
    }

    public String pedirNuevoProducto() {
        System.out.print("Ingrese el nuevo producto o servicio: ");
        return teclado.nextLine();
    }

    public Sede pedirDatosSede() {
        System.out.println("\n--- Registro de nueva sede ---");
        System.out.print("Nombre de la sede: ");
        String nombre = teclado.nextLine();
        System.out.print("Dirección: ");
        String direccion = teclado.nextLine();
        System.out.print("Gerente a cargo: ");
        String gerente = teclado.nextLine();
        System.out.print("Cantidad de empleados: ");
        int empleados = Integer.parseInt(teclado.nextLine());
        return new Sede(nombre, direccion, gerente, empleados);
    }

    public int mostrarMenu() {
        System.out.println("""
        ------------------------------
        SISTEMA DE EMPRENDIMIENTOS
        1) Ver información del emprendimiento
        2) Aplicar crecimiento al emprendimiento
        3) Salir
        ------------------------------
        """);
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(teclado.nextLine());
    }
}

class ControladorEmprendimiento {
    ArrayList<Emprendimiento> lista = new ArrayList<>();
    VistaEmprendimiento vista = new VistaEmprendimiento();

    public void iniciarSistema() {
        // Crear emprendimiento tecnológico de ejemplo
        ArrayList<String> productos = new ArrayList<>();
        productos.add("App móvil");
        ArrayList<Sede> sedes = new ArrayList<>();
        sedes.add(new Sede("Matriz Loja", "Av. Loja", "Luis Perez", 10));
        Mentor m = new Mentor("Ana Torres", "Software");

        EmprendimientoTec emp = new EmprendimientoTec("tecnologico", "Mejorar conectividad", productos, "contacto@app.com", sedes, m, vista);
        lista.add(emp);

        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println(emp);
                    break;
                case 2:
                    emp.crecimiento();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3);
    }
}
