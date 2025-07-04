
/** *
 *
 * @author Grupo 6 || Jorge Quito || David Gonzalez
 */
public class Ejercicio1_JuegoRoles {

    static Personaje guerrero;
    static Personaje mago;
    static Personaje arquero;   

    public static void main(String[] args) {
        guerrero = new Guerrero("Gigante", 30);
        mago = new Mago("Sigilo", 10);
        arquero = new Arquero("Veneno", 20);

        Personaje.batalla(guerrero, mago);

    }
}

//=========================================================================================================================================================
abstract class Personaje {

    public int vidas;
    public int experiencia;
    public int batallasGanadas;
    public int defensa;
    public int daño;

    //Constructor
    public Personaje() {
    }

    public Personaje(int defensa) {
        this.vidas = 100;
        this.defensa = defensa;
    }
    //Metodo para comprobar la batalla
    public static void batalla(Personaje personaje1, Personaje personaje2) {
        int dañoRealizado = 0;
        
        while (personaje1.vidas > 0 && personaje2.vidas > 0){
            
            
            //En cada iteracion se calcula un daño de ambos personaje maximo 20
            personaje1.daño = 10 + (int)(Math.random() * (31));
            personaje2.daño = 10 + (int)(Math.random() * (31));
           

            //Daño para personaje 1 al 2
            dañoRealizado = Math.max(0, personaje1.daño - personaje2.defensa);

            personaje2.vidas -= dañoRealizado;
            
            //Se imprime el toString para el que recibio el daño
            System.out.println(personaje2.toString());

            //Daño del personaje 2 al 1
            dañoRealizado = Math.max(0, personaje2.daño - personaje1.defensa);

            personaje1.vidas -= dañoRealizado;
            
            System.out.println(personaje1.toString());  
            
            if(personaje1.vidas > 0 && personaje2.vidas <= 0){
                System.out.println("Personaje 1 gana. ");
                personaje1.batallasGanadas += 1;
                personaje1.experiencia += 1;
                
                System.out.println(personaje1.toString());
                return;
            }else if(personaje2.vidas > 0 && personaje1.vidas <= 0){
                System.out.println("Personaje 2 gana. ");
                personaje2.batallasGanadas += 1;
                personaje2.experiencia += 1;
                
                System.out.println(personaje2.toString());
                return;
            }
        };

    }

    //toString
    @Override
    public String toString() {
        return "Personaje{" + "Vida:" + vidas + ", experiencia=" + experiencia + ", batallasGanadas=" + batallasGanadas + ", da\u00f1o=" + daño + '}';
    }
}

//=========================================================================================================================================================
class Guerrero extends Personaje {

    public String habilidades;
    //Constructores

    public Guerrero() {
    }

    public Guerrero(String habilidades, int defensa) {
        super(defensa);
        this.habilidades = habilidades;
    }
    
    //toString

    @Override
    public String toString() {
        return String.format(
                "Guerrero {\n  Habilidades: %s%s\n}",
                habilidades, super.toString()
        );
    }

}

//=========================================================================================================================================================
class Mago extends Personaje {

    public String estrategia;

    //Constructores
    public Mago() {
    }

    public Mago(String estrategia, int defensa) {
        super(defensa);
        this.estrategia = estrategia;
    }
    //toString

    @Override
    public String toString() {
        return String.format(
                "Mago {\n  Estrategia: %s%s\n}",
                estrategia, super.toString()
        );
    }

}

//=========================================================================================================================================================
class Arquero extends Personaje {

    public String atributo;
    //Constructores

    public Arquero() {
    }

    public Arquero(String atributo, int defensa) {
        super(defensa);
        this.atributo = atributo;
    }
    //toString

    @Override
    public String toString() {
        return String.format(
                "Arquero {\n  Atributo: %s%s\n}",
                atributo, super.toString()
        );
    }

}
