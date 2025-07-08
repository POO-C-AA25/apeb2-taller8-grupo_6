
import java.util.ArrayList;

public class Ejercicio4_MonitoreoCambioClima {

    public static void main(String[] args) {
        //Metodo de la clase gestionReporte para utilizar los metodos de la clase
        GestionReporte controlador = new GestionReporte();

        //Creación de dispositivos de prueba
        DispositivoCosta costa = new DispositivoCosta(75, 10, 30, 5, "Mala", 20, "Costa");
        DispositivoSierra sierra = new DispositivoSierra("Muy alta", 60, 18, 3, "Buena", 15, "Sierra");
        DispositivoOriente oriente = new DispositivoOriente("Alta", "Media", "Alta", 5, 80, 22, 6, "Regular", 30, "Oriente");

        //Agregado al sistema
        controlador.agregarDispositivo(costa);
        controlador.agregarDispositivo(sierra);
        controlador.agregarDispositivo(oriente);

        //Generar reportes
        controlador.generarReportes();

        //Mostrar reportes
        controlador.mostrarDispositivos(controlador.obtenerDispositivos());
    }
}

//Clase abstracta que va a contener el metodo 'generarReporte',
//que va a ser trabajado en las subclases.
abstract class Dispositivo {

    // Atributos comunes
    public double temperatura;
    public int precipitacion; //Cantidad de precipitaciones
    public String calidadAire;
    public double humedadSuelo;
    public String region;
    public String mensaje;

    //Constructor vacío
    public Dispositivo() {
    }

    //Constructor con parámetros
    public Dispositivo(double temperatura, int precipitacion, String calidadAire, double humedadSuelo, String region) {
        this.temperatura = temperatura;
        this.precipitacion = precipitacion;
        this.calidadAire = calidadAire;
        this.humedadSuelo = humedadSuelo;
        this.region = region;
    }

    //Método abstracto para ser sobrescrito por subclases
    public abstract void generarReporte();

    //Método toString para mostrar información general
    @Override
    public String toString() {
        return "Temperatura: " + temperatura + "°C\n"
                + "Precipitación: " + precipitacion + " mm\n"
                + "Calidad del Aire: " + calidadAire + "\n"
                + "Humedad del Suelo: " + humedadSuelo + "%\n"
                + "Región: " + region + "\n"
                + "Mensaje: " + mensaje;
    }

}

//Clase para representar dispositivos en la región Costa
class DispositivoCosta extends Dispositivo {

    public double humedadAmbiente;
    public double intensidadLluvia;

    //Constructor vacío
    public DispositivoCosta() {
    }

    //Constructor con parámetros
    public DispositivoCosta(double humedadAmbiente, double intensidadLluvia, double temperatura, int precipitacion,
            String calidadAire, double humedadSuelo, String region) {
        super(temperatura, precipitacion, calidadAire, humedadSuelo, region);
        this.humedadAmbiente = humedadAmbiente;
        this.intensidadLluvia = intensidadLluvia;
    }

    //Genera un reporte dependiendo de la humedad del ambiente
    public void generarReporte() {
        if (humedadAmbiente >= 60 && humedadAmbiente < 70) {
            calidadAire = "Baja";
            mensaje = "La humedad alta está deteriorando la calidad del aire en la región " + region;
        } else if (humedadAmbiente > 70) {
            calidadAire = "Muy baja";
            mensaje = "La humedad excesiva está generando condiciones críticas de calidad del aire en la región " + region;
        } else {
            calidadAire = "Buena";
            mensaje = "Condiciones estables en la región " + region;
        }
    }

    //Método toString con información adicional
    @Override
    public String toString() {
        return "Dispositivo Costa\n"
                + "Humedad Ambiente: " + humedadAmbiente + "%\n"
                + "Intensidad de Lluvia: " + intensidadLluvia + " mm/h\n"
                + super.toString();
    }

}

//Clase para representar dispositivos en la región Sierra
class DispositivoSierra extends Dispositivo {

    public String intensidadLluvia;
    public double probabilidadLluvia;

    //Constructores
    public DispositivoSierra() {
    }

    public DispositivoSierra(String intensidadLluvia, double probabilidadLluvia, double temperatura, int precipitacion,
            String calidadAire, double humedadSuelo, String region) {
        super(temperatura, precipitacion, calidadAire, humedadSuelo, region);
        this.intensidadLluvia = intensidadLluvia;
        this.probabilidadLluvia = probabilidadLluvia;
    }

    //Evalúa la intensidad y probabilidad de lluvia para determinar riesgos
    public void generarReporte() {
        if (intensidadLluvia.equalsIgnoreCase("Muy alta") && probabilidadLluvia >= 50) {
            mensaje = "Probabilidades de lluvia muy altas en la Sierra. Precaución.";
        } else if (intensidadLluvia.equalsIgnoreCase("Normal") && (probabilidadLluvia <= 50 && probabilidadLluvia >= 15)) {
            mensaje = "Lluvias moderadas esperadas en la Sierra.";
        } else {
            mensaje = "Condiciones estables en la región Sierra.";
        }
    }

    @Override
    public String toString() {
        return "Dispositivo Sierra\n"
                + "Intensidad de Lluvia: " + intensidadLluvia + "\n"
                + "Probabilidad de Lluvia: " + probabilidadLluvia + "%\n"
                + super.toString();
    }

}

//Clase para representar dispositivos en la región Oriente
class DispositivoOriente extends Dispositivo {

    public String densidadAcuatica;
    public String densidadBoscosa;
    public String cantidadPrecipitacion;
    public double duracionPrecipitacion;
    public int probabilidadDerrumbe;

    //Constructores
    public DispositivoOriente() {
    }

    public DispositivoOriente(String densidadAcuatica, String densidadBoscosa, String cantidadPrecipitacion,
            double duracionPrecipitacion, int probabilidadDerrumbe,
            double temperatura, int precipitacion, String calidadAire, double humedadSuelo, String region) {
        super(temperatura, precipitacion, calidadAire, humedadSuelo, region);
        this.densidadAcuatica = densidadAcuatica;
        this.densidadBoscosa = densidadBoscosa;
        this.cantidadPrecipitacion = cantidadPrecipitacion;
        this.duracionPrecipitacion = duracionPrecipitacion;
        this.probabilidadDerrumbe = probabilidadDerrumbe;
    }

    //Lógica para evaluar riesgo ambiental por derrumbes
    public void generarReporte() {
        if (cantidadPrecipitacion.equalsIgnoreCase("Alta") && duracionPrecipitacion > 4 && probabilidadDerrumbe > 70) {
            mensaje = "Alerta de posibles derrumbes en la región " + region + ".";
        } else if (cantidadPrecipitacion.equalsIgnoreCase("Moderada") && probabilidadDerrumbe > 40) {
            mensaje = "Monitoreo activo en la región " + region + " por riesgo medio de derrumbes.";
        } else {
            mensaje = "Sin riesgos detectados en la región " + region + ".";
        }
    }

    @Override
    public String toString() {
        return "Dispositivo Oriente\n"
                + "Densidad Acuática: " + densidadAcuatica + "\n"
                + "Densidad Boscosa: " + densidadBoscosa + "\n"
                + "Cantidad de Precipitación: " + cantidadPrecipitacion + "\n"
                + "Duración de la Precipitación: " + duracionPrecipitacion + " horas\n"
                + "Probabilidad de Derrumbe: " + probabilidadDerrumbe + "%\n"
                + super.toString();
    }

}

class GestionReporte {

    //Lista para almacenar los dispositivos registrados
    ArrayList<Dispositivo> listaDispositivos = new ArrayList<>();

    //Método para agregar al listado de dispositivos
    public void agregarDispositivo(Dispositivo dispositivo) {
        listaDispositivos.add(dispositivo);
    }

    //Método para generar todos los reportes de los dispositivos registrados
    public void generarReportes() {
        for (Dispositivo d : listaDispositivos) {
            d.generarReporte();
        }
    }

    //Devuelve la lista de dispositivos para que la vista los imprima
    public ArrayList<Dispositivo> obtenerDispositivos() {
        return listaDispositivos;
    }

    //Muestra los reportes de todos los dispositivos
    public void mostrarDispositivos(ArrayList<Dispositivo> lista) {
        for (Dispositivo d : lista) {
            System.out.println(d);
            System.out.println("--------------------------------------------------");
        }
    }
}
