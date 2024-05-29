package com.mycompany.mavenproject2;
import java.util.*;

public class Equipa 
{
    private String nome;
    private String estadio;
    private int jogos;
    private int pontos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golosMarcados;
    private int golosSofridos;
    private String last5;
    private ArrayList<Jogador> jogadores;
    private Treinador treinador;
    
    public Equipa(String nome, String estadio, ArrayList<Jogador> jogadores, Treinador treinador){
        this.nome = nome;
        this.estadio = estadio;
        this.pontos = 0;
        this.jogos = 0;
        this.vitorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        this.last5 = "NNNNN";
        this.golosMarcados = 0;
        this.golosSofridos = 0;
        this.jogadores = jogadores;
        this.treinador = treinador;
    }

    public Equipa(String nomeEquipa, String nomeEstadio) {
        this.nome=nomeEquipa;
        this.estadio = nomeEstadio;
        this.pontos = 0;
        this.vitorias = 0;
        this.empates = 0;
        this.jogos = 0;
        this.derrotas = 0;
        this.golosMarcados = 0;
        this.golosSofridos = 0;
        this.last5 = "NNNNN";
        this.jogadores = new ArrayList<Jogador>();
        this.treinador = new Treinador();
    }
    public int getOVR(){
        int sum = 0;
        for (int i = 0; i<(jogadores.size());i++){
            Jogador j = jogadores.get(i);
            if (j.getLesionado() == false && j.getVermelho() == false){
                sum += j.getOverall();
            }
        }
        return sum;
    }
    
    public int getATK(){
        int sum = 0;
        for (int i = 0; i<(jogadores.size());i++){
            Jogador j = jogadores.get(i);
            if (j.getLesionado() == false && j.getVermelho() == false){
                sum += j.getAttacking();
            }
        }
        return sum;
    }
    
    public int getDEF(){
        int sum = 0;
        for (int i = 0; i<(jogadores.size());i++){
            Jogador j = jogadores.get(i);
            if (j.getLesionado() == false && j.getVermelho() == false){
                sum += j.getDefending();
            }
        }
        return sum;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getEstadio(){
        return estadio;
    }
    
    public int getPontos(){
        return pontos;
    }
    
    public int getVitorias(){
        return vitorias;
    }
    
    public int getDerrotas(){
        return derrotas;
    }
    
    public int getEmpates(){
        return empates;
    }
    
    public String getLast5(){
        return last5;
    }
    
    public int getGolosMarcados(){
        return golosMarcados;
    }
    
    public int getGolosSofridos(){
        return golosSofridos;
    }
    
    public ArrayList<Jogador> getJogadores(){
        return jogadores;
    }
    
    public Treinador getTreinador(){
        return treinador;
    }
    
    public int getJogos(){
        return jogos;
    }
    
    public void AdicionaJogador(Jogador jogador) {
        this.jogadores.add(jogador);
    }

    public Jogador obterJogadorPorNome(String nomeJogador) {
        for (Jogador jogador : jogadores) {
            if (jogador.getNome().equalsIgnoreCase(nomeJogador)) {
                return jogador;
            }
        }
        return null; // Se o jogador não for encontrado, retorne null
    }
    
    public Equipa obterEquipa(String nomeJogador) {
        Iterable<Equipa> equipas = null;
        for (Equipa equipa : equipas) {
            if (equipa.getNome().equalsIgnoreCase(nomeJogador)) {
                return equipa;
            }
        }
        return null; // Se o jogador não for encontrado, retorne null
    }
    
    //altera treinador
    public void setTreinador (Treinador treinador)
    {
        this.treinador = treinador;
    }
    
    public void addVitorias(){
        vitorias++;
        jogos++;
        pontos += 3;
    }
    
    public void addEmpates(){
        empates++;
        jogos++;
        pontos += 1;
    }
    
    public void addDerrotas(){
        derrotas++;
        jogos++;
    }
    
    public void addGolosMarcados(int i){
        golosMarcados += i;
    }
    
    public void addGolosSofridos(int i){
        golosSofridos += i;
    }
    
    public void addLast5(int caso){
        switch (caso){
            case 1:
               last5 = last5.substring(0,last5.length()-1);
               last5 = "V" + last5;
               break;
            case 2:
               last5 = last5.substring(0,last5.length()-1);
               last5 = "E" + last5;
               break;
            case 3:
               last5 = last5.substring(0,last5.length()-1);
               last5 = "D" + last5;
               break;
            default:
                break;
        }   
    }
}
