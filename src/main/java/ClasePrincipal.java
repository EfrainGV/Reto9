/*
 Reto 9
 Hecho por:
 Efraín García(CC.1001370984)
 Samuel Acevedo(CC.1001016099)
*/


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
            System.out.println("Media: "+media(ruta1));
            System.out.println("Desviación estándar: "+desviacionEstandar(ruta1));
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void escribirLineaPorLinea(String rutaLeer , String rutaEscribir ) throws IOException , NullPointerException{

        Path miRutaEscribir = Paths.get(rutaEscribir);
        StringBuilder cadena = new StringBuilder();
        String miConceptoCierre;
        String[] palabras;
        int i = 1;
        while (LeerLineaPorLinea(rutaLeer, i) != null) {

            palabras = LeerLineaPorLinea(rutaLeer, i);
            assert palabras != null;
            miConceptoCierre = conceptoCierre(palabras[4]);
            cadena.append("Fecha: ").append(palabras[0]).append("\t").append("Concepto de cierre: ").append(miConceptoCierre).append("\n");
            i++;
        }

        Files.writeString(miRutaEscribir, cadena.toString());
    }

    public static String[] LeerLineaPorLinea(String rutaALeer , int x) throws IOException {

        Path miRutaLeer = Paths.get(rutaALeer);
        List<String> lineasArchivoLeer;
        String[] palabrasSeparadas;
        lineasArchivoLeer = Files.readAllLines(miRutaLeer);
        try {
            String lineaActual = lineasArchivoLeer.get(x);
            palabrasSeparadas = lineaActual.split(",");
        } catch (IndexOutOfBoundsException k) {
            return null;
        }

        return palabrasSeparadas;

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

    public static float media(String rutaLeer) throws IOException {
        int i = 1;
        float sumaElementos=0;
        String[] palabras;
        while (LeerLineaPorLinea(rutaLeer, i) != null) {
            palabras = LeerLineaPorLinea(rutaLeer , i);
            assert palabras != null;
            sumaElementos += Float.parseFloat(palabras[1]);
            i++;
        }
        return sumaElementos/ i-1;
    }

    public static double desviacionEstandar(String rutaLeer) throws IOException {
        float mediaDeLosDatos = media(rutaLeer);
        float sumatoriaDistanciasCuadradas=0;
        float distanciaConLaMedia;

        int i = 1;
        String[] palabras;
        while (LeerLineaPorLinea(rutaLeer, i) != null) {
            palabras = LeerLineaPorLinea(rutaLeer , i);
            assert palabras != null;
            distanciaConLaMedia = Float.parseFloat(palabras[1]) - mediaDeLosDatos;
            sumatoriaDistanciasCuadradas += Math.pow(distanciaConLaMedia,2);
            i++;
        }
        return Math.sqrt(sumatoriaDistanciasCuadradas/i-1);
    }

    public static String[] valorMax(List<String> lineasConPrecios){
        int i=1;
        int j=i+1;
        double datoMax = 0;
        String fechaDato = null;
        while (i < lineasConPrecios.size() && j < lineasConPrecios.size()-1) {
            String lineaActual = lineasConPrecios.get(i);
            String lineaSiguiente = lineasConPrecios.get(j);
            String[] datosDeLaLinea = lineaActual.split(",");
            String[] datosDeLaLineaSiguiente = lineaSiguiente.split(",");
            if(Double.parseDouble(datosDeLaLinea[2]) > Double.parseDouble(datosDeLaLineaSiguiente[2])){
                fechaDato = datosDeLaLinea[0];
                datoMax = Double.parseDouble(datosDeLaLinea[2]);
            }else{
                fechaDato = datosDeLaLineaSiguiente[0];
                datoMax = Double.parseDouble(datosDeLaLineaSiguiente[2]);
                i=j;
            }
            j++;
        }
        return new String[]{fechaDato,Double.toString(datoMax)};
    }

    public static String[] valorMin(List<String> lineasConPrecios){
        int i=1;
        int j=i+1;
        double datoMin = 0;
        String fechaDato = null;
        while (i < lineasConPrecios.size() && j < lineasConPrecios.size()-1) {
            String lineaActual = lineasConPrecios.get(i);
            String lineaSiguiente = lineasConPrecios.get(j);
            String[] datosDeLaLinea = lineaActual.split(",");
            String[] datosDeLaLineaSiguiente = lineaSiguiente.split(",");
            if(Double.parseDouble(datosDeLaLinea[3]) < Double.parseDouble(datosDeLaLineaSiguiente[3])){
                fechaDato = datosDeLaLinea[0];
                datoMin = Double.parseDouble(datosDeLaLinea[3]);
            }else{
                fechaDato = datosDeLaLineaSiguiente[0];
                datoMin = Double.parseDouble(datosDeLaLineaSiguiente[3]);
                i=j;
            }
            j++;
        }
        return new String[]{fechaDato,Double.toString(datoMin)};
    }
}

