package hsos;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Application {

    /**
     * Scanner Objekt zum lesen von Konsolen Eingaben.
     */
    private final Scanner consoleScanner;

    /**
     * Der Application Constructor wird von der main aufgerufen und führt einige
     * beispielhaft Operationen durch. Es wird etwas in der Konsole ausgegeben,
     * eine Datei wird gelesen und Konsolen-Eingabe wird gelesen.
     *
     */
    public Application() {
        System.out.println("Hello, Sailor!");

        // Es ist nicht empfohlen, die Datei "data_100k" einfach in die Konsole
        // auszugeben, da dies recht lange dauert kann.
        readNumbersFromFile("data_20");

        // Um Eingaben von der Konsole zu lesen, kann die Scanner Klasse verwendet
        // und System.in als Input Stream übergeben werden. Es darf nur eine Instanz
        // eines Scanners mit System.in im Programm geben!
        consoleScanner = new Scanner(System.in);
        readLineFromConsole();
    }

    /**
     * Dies ist eine beispielhafte Methode, die Zeigt, wie die Scanner Instanz benutzt
     * werden kann. Das Programm wartet hier auf die Eingabe einer Zahl.
     */
    private void readLineFromConsole() {
        // Das Programm wartet hier auf die Eingabe einer Zahl.
        int number = consoleScanner.nextInt();
        System.out.println("Zahl: " + number);
    }

    /**
     * MUSS MODIFIZIERT WERDEN!
     *
     * Diese Funktion liest Zahlen aus Dateien im "resources"-Verzeichnis und
     * gibt sie in der Konsole aus. Sie dient als Vorlage und muss so modifiziert
     * werden, dass die Zahlen zum Sortieren in eine von Ihnen gewählte
     * Datenstruktur geladen wird. Außerdem sollte der Rückgabetyp dahingehend
     * angepasst werden, dass die Funktion die Zahlen auch zurückgibt.
     *
     * @param fileName Der Name der Datei aus dem "resources"-Verzeichnis
     */
    public static void readNumbersFromFile(String fileName) {
        Path path;
        Scanner scanner;
        try {
            // Diese 2 Zeilen können ignoriert werden. Sie dienen lediglich dem Finden des
            // "resources"-Verzeichnisses und dem Auswählen der richtigen Datei.
            path = Paths.get(Objects.requireNonNull(Application.class.getClassLoader().getResource(fileName)).toURI());
            scanner = new Scanner(path, StandardCharsets.UTF_8);

            // Die erste Zahl einer jeden Datei gibt an, wie viele Elemente sich in der
            // Datei befinden. Dabei wird die erste Zahl nicht mitgerechnet.
            int elemAnzahl = scanner.nextInt();
            System.out.println("Anzahl an Elementen: " + elemAnzahl);

            // Dieser while loop läuft, bis alle Integer in der Datei gelesen wurden und
            // gibt jede einzelne Zahl in der Konsole aus. Die index Variable zählt mit
            // bei welchem Element der loop sich befindet.
            int index = 0;
            while (scanner.hasNextInt()) {
                System.out.println(index + ". Zahl: " + scanner.nextInt());
                ++index;
            }

            scanner.close();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Entry Point für das Progamm. Hier startet alles!
     *
     * @param args Command Line Argumente in einem String[]
     */
    public static void main(String[] args) {
        //Create UI-Instance
        new SortAlgoInterface();
    }

}
