public class Main {
    public static void main(String[] args) {
        ArchivoTexto archivo = new ArchivoTexto();

        archivo.escribirArchivo();
        archivo.leerArchivo();

        String buscarPalabra = "HOLA";
        System.out.println("\nLa palabra '"+buscarPalabra+"' tiene una frecuencia de: "+ archivo.getPalabraFrecuencia(buscarPalabra));
    }
}
