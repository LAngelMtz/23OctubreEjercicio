import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class ArchivoTexto {
    private String ruta;
    private HashMap<String,Integer> frecuenciaPalabras;

    public ArchivoTexto() {
        ruta = "archivo.txt";
        frecuenciaPalabras = new HashMap<>();
    }

    public void leerArchivo() {

        try (BufferedReader leer = new BufferedReader(new FileReader(ruta))) {
            String linea; // Atributo donde se guardara cada linea de texto

            while ((linea = leer.readLine()) != null) {
                System.out.println(linea); // Muestra todos las lineas del documento de texto
            }

            leer.close();
        } catch (FileNotFoundException e){
            System.out.println("No se encontro el archivo");
        }
        catch (IOException e) {
            System.out.println("No se puede leer el archivo");
        }
    }

    public void escribirArchivo() {
        Scanner teclado = new Scanner(System.in);
        String texto;

        actualizarMap();

        // Usar BufferedWriter para escribir el texto en el archivo
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter(ruta, false))) { // true = modo apend ; false = sobrescribir
            System.out.println("Escribe la palabra:");
            texto = teclado.nextLine();
            texto = texto.toLowerCase(); // Siempre convertir todas las letras a minusculas

            // Poner palabra en el hash o subir el contador de frecuencia
            if(frecuenciaPalabras.containsKey(texto)){frecuenciaPalabras.put(texto,frecuenciaPalabras.get(texto)+1);} // Si ya esta la palabra en el hash
            else{frecuenciaPalabras.put(texto,1);} // Si no esta en el hash

            // Recorre todo el hashmap
            frecuenciaPalabras.forEach((k,v)->{
                try {
                    escribir.write(k+","+v); // Escribir en el doc
                    escribir.newLine();  // Agregar una nueva línea al final del texto
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            escribir.close();

        } catch (FileNotFoundException e){
            System.out.println("No se encontro el archivo");
        }
        catch (IOException e) {
            System.out.println("No se puede escribir en el archivo");
        }
    }

    public  int getPalabraFrecuencia(String palabra) {
        actualizarMap();
        palabra.toLowerCase();

        if(frecuenciaPalabras.containsKey(palabra)){return frecuenciaPalabras.get(palabra);}
        else {return 0;}
    }

    private void actualizarMap(){
        String[] partesPalabra; // Donde se guarda las dos partes de la linea

        try (BufferedReader leer = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = leer.readLine()) != null) {
                partesPalabra = linea.split(","); // La linea se divide en 2 por la ","
                frecuenciaPalabras.put(partesPalabra[0],Integer.parseInt(partesPalabra[1]));
            }

            leer.close();
        } catch (FileNotFoundException e){
            System.out.println("No se encontro el archivo");
        }
        catch (IOException e) {
            System.out.println("No se puede leer el archivo");
        }
    }
}
