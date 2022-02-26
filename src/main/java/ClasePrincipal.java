import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ClasePrincipal {
    public static void main(String[] args) {
        String ruta1 = "src/main/BTC-USD.csv";
        String ruta2 = "src/main/Punto1.txt";
      //String ruta3 = "src/main/Punto2.txt";
        try {
            escribirLineaPorLinea(ruta1 , ruta2);
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void escribirLineaPorLinea(String rutaLeer , String rutaEscribir ) throws IOException {

        Path miRutaLeer = Paths.get(rutaLeer);
        Path miRutaEscribir = Paths.get(rutaEscribir);
        List<String> lineasArchivoLeer;
        StringBuilder cadena = new StringBuilder();
        String[] palabras;
        String miConceptoCierre;


            lineasArchivoLeer = Files.readAllLines(miRutaLeer);

            for (int i = 1, lineasArchivoLeerSize = lineasArchivoLeer.size(); i < lineasArchivoLeerSize; i++) {
                String lineaActual = lineasArchivoLeer.get(i);
                palabras = lineaActual.split(",");
                miConceptoCierre = conceptoCierre(palabras[4]);
                cadena.append("Fecha: ").append(palabras[0]).append("\t").append("Concepto de cierre: ").append(miConceptoCierre).append("\n");
            }

        Files.writeString(miRutaEscribir, cadena.toString());
    }

    public static String conceptoCierre(String c) {
        double valor = Double.parseDouble(c);


        if (valor < 30000){
            return "MUY BAJO";
        }
        else if (valor < 40000){
            return "BAJO";
        }
        else if (valor < 50000){
            return "MEDIO";
        }
        else if (valor < 60000){
            return "ALTO";
        }
        else return "MUY ALTO";
    }

    public static float media(String[] datos){   //Itera sobre el Array palabras, tomo los elementos de la posicion 1 y calculo la media
        float sumaElementos=0;
        for (String dato : datos) {      //La lógica es como en el array palabras usado para el conceptoCierre
            sumaElementos += Float.parseFloat(dato);
        }
        return sumaElementos/ datos.length;
    }

    public static double desviacionEstandar(String[] datos){
        float mediaDeLosDatos = media(datos);
        float sumatoriaDistanciasCuadradas=0;
        float distanciaConLaMedia;
        /*El array datos usado para los metodos media y varianza contiene los valores de apertura
            de cada día, navegando en el solo se encuentran numeros en Strings

            Por lo tanto, cuando se lean los datos hay que crear un array aparte con los datos
            de la posicion 1 del vector palabras para cada línea(Aún falta hacerlo)
         */
        for(String valor:datos){
            distanciaConLaMedia = Float.parseFloat(valor) - mediaDeLosDatos;
            sumatoriaDistanciasCuadradas += Math.pow(distanciaConLaMedia,2);
        }
        return Math.sqrt(sumatoriaDistanciasCuadradas/datos.length);
    }
}
