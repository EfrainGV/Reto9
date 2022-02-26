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

}
