package org.example;

import org.jodconverter.local.office.LocalOfficeManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LocalOfficeManager officeManager = LocalOfficeManager.builder()
                .install() // Or .officeHome("your/path")
                .portNumbers(8100)
                .build();

        try {
            System.out.println("Starting OpenOffice server on port 8100...");
            officeManager.start();
            System.out.println("SERVER IS ONLINE.");
            System.out.println(">>> Press ENTER in this console to shut down the server <<<");

            // This blocks the main thread so the program doesn't exit
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

        } catch (Exception e) {
            System.err.println("Could not start office server: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                officeManager.stop();
                System.out.println("Server stopped.");
            } catch (Exception e) {
                System.err.println("Error during shutdown.");
            }
        }
    }
}