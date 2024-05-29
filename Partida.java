/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;
import java.util.*;

public class Partida 
{
    private final Equipa equipa1;
    private final Equipa equipa2;
    private final Date data;
    private final String local;
    private int golosEquip1;
    private int golosEquip2;
    private boolean realizada;
    private Random rand = new Random();
    private Arbitro arbitro;

    public Partida(Equipa equipa1, Equipa equipa2, Date data, String local, Arbitro arbitro) {
        this.equipa1 = equipa1;
        this.equipa2 = equipa2;
        this.data = data;
        this.local = local;
        this.golosEquip1 = 0;
        this.golosEquip2 = 0;
        this.realizada = false;
        this.arbitro=arbitro;
    }

    public Partida() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void EfetuarPartida(int fatorcasa) {

        if (realizada) {
            System.out.println("A partida já foi realizada.");
            return;
        }
        
        double percentagem = calcularProbabilidade(equipa1,equipa2,fatorcasa);
        // Utilizar as probabilidades para determinar o resultado da partida
        if (-0.15 <= percentagem && percentagem <= 0.15) 
        {
            golosEquip1 = rand.nextInt(4); // Número aleatório de gols para equipa1 (0 a 3)
            golosEquip2 = golosEquip1;
        } 
        else if (0.15 < percentagem && percentagem <= 0.4) 
        {
            golosEquip2 = rand.nextInt(4);
            golosEquip1 = golosEquip2 + 1;  //vitoria de margem pequena
        }
        else if (percentagem > 0.4)
        {
            golosEquip2 = rand.nextInt(4);
            golosEquip1 = golosEquip2 + rand.nextInt(2) + 2;
        
        }
        else if (-0.15 > percentagem && percentagem >= -0.4) 
        {
            golosEquip1 = rand.nextInt(4);
            golosEquip2 = golosEquip1 + 1;  //vitoria de margem pequena
        }
        else if (percentagem < -0.4)
        {
            golosEquip1 = rand.nextInt(4);
            golosEquip2 = golosEquip1 + rand.nextInt(3) + 2;
        
        }
        realizada = true;
        
        ArrayList<Jogador> marcadores1 = golos(equipa1,golosEquip1);
        ArrayList<Jogador> marcadores2 = golos(equipa2,golosEquip2);
        
        if (percentagem > 0.15){
            atualizaStats(equipa1,1,golosEquip1,golosEquip2);
            atualizaStats(equipa2,2,golosEquip2,golosEquip1);
        }
        else if (percentagem < -0.15){
            atualizaStats(equipa1,2,golosEquip1,golosEquip2);
            atualizaStats(equipa2,1,golosEquip2,golosEquip1);
        }
        else{
            atualizaStats(equipa1,3,golosEquip1,golosEquip2);
            atualizaStats(equipa2,3,golosEquip2,golosEquip1);
        }
        // Adicionar lógica para registrar o resultado, estatísticas, etc.
        System.out.println("Partida realizada em " + data + " no local " + local + " pelo arbitro " + arbitro.getNome());
        System.out.println(equipa1.getNome() + " " + golosEquip1 + " - " + golosEquip2 + " " + equipa2.getNome());
        golosOutput(equipa1,marcadores1);
        golosOutput(equipa2,marcadores2);
        calculaCartoes(equipa1);
        calculaCartoes(equipa2);
        calculaLesoes(equipa1);
        calculaLesoes(equipa2);
    }

    private double calcularProbabilidade(Equipa equipaA, Equipa equipaB) {
        double percentagem1 = 0;
        double percentagem2 = 0;
        int vitorias1 = 0;
        int derrotas1 = 0;
        int empates1 = 0;
        int jogos1 = 0;
        int vitorias2 = 0;
        int derrotas2 = 0;
        int empates2 = 0;
        int jogos2 = 0;
        int suspensos1 = 0;
        int suspensos2 = 0;
        int difOVR = equipaA.getOVR() - equipaB.getOVR();
        int difATK = equipaA.getATK() - equipaB.getATK();
        int difDEF = equipaA.getDEF() - equipaB.getDEF();
        char[] formaA  = equipaA.getLast5().toCharArray();
        char[] formaB = equipaB.getLast5().toCharArray();
        Treinador treinadorA = equipaA.getTreinador();
        Treinador treinadorB = equipaB.getTreinador();

        if (difOVR > 0) {            // diferença de qualidade
            if (difOVR < 10) {
                percentagem1 += 0.1;
            } else if (difOVR < 20) {
                percentagem1 += 0.15;
            } else {
                percentagem1 += 0.20;
            }
        } else if (difOVR < 0) {
            if (difOVR > -10) {
                percentagem2 += 0.1;
            } else if (difOVR > -20) {
                percentagem2 += 0.15;
            } else {
                percentagem2 += 0.20;
            }
        }

        if (difATK > 0) {           // diferença ofensiva
            if ("Ofensivo".equals(treinadorA.getEstilo())) {
                percentagem1 += 0.05;
            }
            if (difATK < 10) {
                percentagem1 += 0.1;
            } else if (difATK < 20) {
                percentagem1 += 0.15;
            } else {
                percentagem1 += 0.20;
            }
        } else if (difATK < 0) {
            if ("Ofensivo".equals(treinadorB.getEstilo())) {
                percentagem2 += 0.05;
            }
            if (difATK > -10) {
                percentagem2 += 0.1;
            } else if (difATK > -20) {
                percentagem2 += 0.15;
            } else {
                percentagem2 += 0.20;
            }
        }

        if (difDEF > 0) {               //diferença defensiva
            if ("Defensivo".equals(treinadorA.getEstilo())) {
                percentagem1 += 0.05;
            }
            if (difDEF < 10) {
                percentagem1 += 0.1;
            } else if (difDEF < 20) {
                percentagem1 += 0.15;
            } else {
                percentagem1 += 0.20;
            }
        } else if (difDEF < 0) {
            if ("Defensivo".equals(treinadorB.getEstilo())) {
                percentagem2 += 0.05;
            }
            if (difDEF > -10) {
                percentagem2 += 0.1;
            } else if (difDEF > -20) {
                percentagem2 += 0.15;
            } else {
                percentagem2 += 0.20;
            }
        }
        
        for (char x: formaA)        // fator forma equipa1
        {
            if (x == 'V'){
                 vitorias1++;
                 jogos1++;
            }
            else if(x == 'E'){
                empates1++;
                jogos1++;
            }
            else if (x == 'D'){
                derrotas1++;
                jogos1++;
            }
        }
        if (jogos1 > 2 ){
            if ((vitorias1 > (empates1 + derrotas1))){
                percentagem1 +=0.1;
            }
            else if (derrotas1 > (empates1 + vitorias1)){
                percentagem1 -=0.1;
            }
            else if (empates1 > vitorias1 && vitorias1 > derrotas1){
                percentagem1 +=0.05;
            }
            else if (empates1 > derrotas1 && derrotas1 > vitorias1){
                percentagem1 -=0.05;
            }
        }
        
        for (char x: formaB)                // fator forma equipa2
        {
            if (x == 'V'){
                 vitorias2++;
                 jogos2++;
            }
            else if(x == 'E'){
                empates2++;
                jogos2++;
            }
            else if (x == 'D'){
                derrotas2++;
                jogos2++;
            }
        }
        if (jogos2 > 2 ){
            if ((vitorias2 > (empates2 + derrotas2))){
                percentagem2 +=0.1;
            }
            else if (derrotas2 > (empates2 + vitorias2)){
                percentagem2 -=0.1;
            }
            else if (empates2 > vitorias2 && vitorias2 > derrotas2){
                percentagem2 +=0.05;
            }
            else if (empates2 > derrotas2 && derrotas2 > vitorias2){
                percentagem2 -=0.05;
            }
        }
        
        for (Jogador jogador : equipa1.getJogadores()) {
             if (jogador.getLesionado() == true || jogador.getVermelho() == true){
                 suspensos1++;
             }
        }
        
        for (Jogador jogador : equipa2.getJogadores()) {
             if (jogador.getLesionado() == true || jogador.getVermelho() == true){
                 suspensos2++;
             }
        }
        
        if (suspensos1 > suspensos2){
            percentagem1 -=0.1;
        }
        else if (suspensos2 > suspensos1){
            percentagem2 -=0.1;
        }
        
        int random = rand.nextInt(8);               //fator random
        switch (random) {
            case 0 -> percentagem1 += 0.05;
            case 1 -> percentagem1 += 0.1;
            case 2 -> percentagem1 += 0.15;
            case 3 -> percentagem1 += 0.2;
            case 4 -> percentagem2 += 0.05;
            case 5 -> percentagem2 += 0.1;
            case 6 -> percentagem2 += 0.15;
            case 7 -> percentagem2 += 0.2;
            default -> System.out.println("Erro no random, dando default para 0");
        }

        
        double percentagem = percentagem1 - percentagem2;
        return percentagem;
    }
    
    private double calcularProbabilidade(Equipa equipaA, Equipa equipaB,int C){
        double probabilidade = calcularProbabilidade(equipaA,equipaB);
        if (C == 1){
            probabilidade +=0.5;
        }
        else if (C == 2){
            probabilidade -= 0.5;
        }
        return probabilidade;
    }
    
    public void atualizaStats(Equipa equipaA,int caso, int golosA, int golosB){
        if (caso == 1){
            System.out.println(equipaA.getVitorias());
            equipaA.addVitorias();
            System.out.println(equipaA.getVitorias());
            equipaA.addLast5(1);
        }
        if (caso == 2){
            System.out.println(equipaA.getDerrotas());
            equipaA.addDerrotas();
            System.out.println(equipaA.getDerrotas());
            equipaA.addLast5(3);
        }
        if (caso == 3){
            equipaA.addEmpates();
            equipaA.addLast5(2);
        }
        equipaA.addGolosMarcados(golosA);
        equipaA.addGolosSofridos(golosB);
    }
    
    public lesao crialesao(){
        String[] lesoesgraves = {"Ligamento Cruzado Anterior","Tornozelo Fraturado","Flexor do Quadril Rasgado","Músculo Quadríceps Rasgado","Cotovelo Fraturado","Costela Fraturada","Metatarso Fraturado"};
        String[] lesoesleves = {"Joelho Hiperextendido","Isquiotibiais Puxados","Quadríceps Puxado","Joelho Torcido","Costela Magoada","Ombro Magoado","Virilha Puxada"};
        int random = rand.nextInt(7);
        String nomelesao = "";
        int tempolesao = rand.nextInt(9)+1;
        if (tempolesao < 4){
            nomelesao = lesoesleves[random];
        }
        else{
            nomelesao = lesoesgraves[random];
        }
        lesao Lesao = new lesao(nomelesao,tempolesao,false);
        return Lesao;
    }

    Object getEquipa1() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getEquipa2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private ArrayList<Jogador> golos(Equipa equipa, int golos){
        ArrayList<Jogador> jogadores = equipa.getJogadores();
        ArrayList<Jogador> marcadores = new ArrayList<Jogador>();
        int chance = 0;
        while (golos > 0){
            for (int i = 0; i<(jogadores.size());i++){
                Jogador j = jogadores.get(i);
                if (j.getLesionado() == false && j.getVermelho() == false){
                    switch(j.getPosicaoInterna()){
                        case "Guarda-Redes": 
                            chance = 1;
                            int random = rand.nextInt(99);
                            if (random < chance && golos > 0){
                                j.addGolos(1);
                                golos--;
                                marcadores.add(j);
                            }    
                            break;
                        case "Defesa": 
                            chance = 5;
                            random = rand.nextInt(99);
                            if (random < chance && golos > 0){
                                j.addGolos(1);
                                golos--;
                                marcadores.add(j);
                            }    
                            break;
                        case "Medio":
                            chance = 10;
                            random = rand.nextInt(99);
                            if (random < chance && golos > 0){
                                j.addGolos(1);
                                golos--;
                                marcadores.add(j);
                            }
                            break;
                        case "Avancado":
                            chance = 20;
                            random = rand.nextInt(99);
                            if (random < chance && golos > 0){
                                j.addGolos(1);
                                golos--;
                                marcadores.add(j);
                            }
                            break;
                    }
                }
            }    
        }
        return marcadores;
    }
    
    private void golosOutput(Equipa equipa, ArrayList<Jogador> marcadores){
        String output;
        if (marcadores.isEmpty()){
            System.out.println("");
        }
        else{
            System.out.println("Golos da equipa " + equipa.getNome() + " :");
            for (int i = 0; i<(marcadores.size());i++){
                Jogador j = marcadores.get(i);
                System.out.println(j.getNome());
            }
        }
            
    }
    
    private void LesaoOutput(Equipa equipa, Jogador jogador, lesao Lesao){
        System.out.println("Lesão do jogador " + jogador.getNome() + " da equipa " + equipa.getNome() );
        System.out.println(Lesao.getNome() + " e ficará fora de partidas durante " + Lesao.getGravidade());
    }
    
    private void CartoesOutput(Equipa equipa, Jogador jogador, int caso){
        switch (caso){
            case 1: System.out.println("Amarelo para o jogador " + jogador.getNome() + " da equipa " + equipa.getNome()); break;
            case 2: System.out.println("Amarelo para o jogador " + jogador.getNome() + " da equipa " + equipa.getNome() + " como viu amarelo no jogo passado, falha o próximo."); break;
            case 3: System.out.println("Vermelho direto para o jogador " + jogador.getNome() + " da equipa " + equipa.getNome()); break;
        }
    }
    
    private void calculaLesoes(Equipa equipa){
        for (Jogador jogador : equipa.getJogadores()) {
            int iflesao = rand.nextInt(99);
            if (iflesao < 3 && jogador.getLesionado() == false){
                lesao Lesao;
                Lesao = crialesao();
                jogador.ficalesionado(Lesao);
                LesaoOutput(equipa1,jogador,Lesao);
            }
            else if (jogador.getLesionado() == true){
                lesao Lesaoatual = jogador.getLesaoAtual();
                if (Lesaoatual.getTratado() == false){
                    Lesaoatual.tratalesao();
                }
            }
        }
    }
    
     private void calculaCartoes(Equipa equipa){
        for (Jogador jogador : equipa.getJogadores()) {
            int iflesao = rand.nextInt(99);
            if (jogador.getLesionado() == false && iflesao*2 < jogador.getAgressive()){
                int caso = jogador.cartao(2);
                CartoesOutput(equipa,jogador,caso);
            }
            else if (jogador.getLesionado() == false && iflesao < jogador.getAgressive()){
                int caso = jogador.cartao(1);
                CartoesOutput(equipa,jogador,caso);
            }
            else{
                jogador.cartao(3);
            }
        }
     }
}
