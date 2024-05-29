/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.mavenproject2;
import java.io.*;
import java.util.*;

public class Lerficheiros {
     public static void main(String[] args) {
        String fileName = "equipa_.txt";
        int linesPerChunk = 4;
        int lineCount = 0;
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                lineCount++;
                if (lineCount % linesPerChunk == 0) {
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void colocarnoficheiro(String[] args) {
        String fileName = "Jogadoresnovosinput.txt";
        
        try (Scanner scanner = new Scanner(System.in);
             PrintWriter writer = new PrintWriter(new File(fileName))) {
             while (true) {
                System.out.print("Enter some text (or 'quit' to exit): ");
                String input = scanner.nextLine();
                if (input.equals("quit")) {
                    break;
                }
                writer.println(input);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
