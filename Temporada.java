/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Temporada {

    private static void exibirEquipas(ArrayList<String> equipas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static String escolherEquipa(ArrayList<String> equipas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private int ano;
    private ArrayList<Partida> partidas;

    public Temporada(int ano, ArrayList<Partida> partidas) {
        this.ano = ano;
        this.partidas = partidas;
    }

    //Lucas main porque?
    public static void main(String[] args) {
        // Leitura das equipas do ficheiro TXT
        ArrayList<String> equipas = lerEquipasDoFicheiro("equipa_.txt");
        exibirEquipas(equipas);
        String equipaEscolhida = escolherEquipa(equipas);
        Temporada temporada = criarTemporada(2023);
        temporada.exibirJogosDaEquipa(equipaEscolhida);
    }

    private static ArrayList<String> lerEquipasDoFicheiro(String ficheiro) {
        ArrayList<String> equipas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                equipas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return equipas;
    }

    private static Temporada criarTemporada(int ano) {
        // Criar partidas fictícias
        ArrayList<Partida> partidas = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String equipaCasa = "Equipa" + (random.nextInt(7) + 1);
            String equipaVisitante = "Equipa" + (random.nextInt(7) + 1);
            Partida partida = new Partida();
            partidas.add(partida);
        }

        return new Temporada(ano, partidas);
    }

    private void exibirJogosDaEquipa(String equipa) {
        System.out.println("Jogos da equipa " + equipa + " na temporada " + ano + ": ");
        for (Partida partida : partidas) {
            if (partida.getEquipa1().equals(equipa) || partida.getEquipa2().equals(equipa)) {
                System.out.println(partida);
            }
        }
    }
}
