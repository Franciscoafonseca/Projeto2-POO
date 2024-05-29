package com.mycompany.mavenproject2;
import java.util.*;

public class Jogador extends Pessoa  
{
    private String posicao;
    private String posicao_interna;  // defesa,medio ou atacante
    private int overall;
    private int attacking;
    private int defending;
    private int agressividade;
    private int golos;
    private ArrayList<lesao> lesoes;
    private boolean lesionado;
    private int cartoesA;
    private int cartoesV;
    private boolean amarelado;
    private boolean vermelho;
    private int numeroCamisola;
    
    public Jogador(int idade, String nome,String posicao,int overall,int attack, int defend, int agressive, ArrayList<lesao> lesoes, int numeroCamisola){
    super(idade,nome);
    this.posicao = posicao;
    this.overall = overall;
    this.attacking = attack;
    this.defending = defend;
    this.agressividade = agressive;
    this.lesoes = lesoes;
    this.cartoesA = 0;
    this.cartoesV = 0;
    this.golos = 0;
    this.lesionado = false;
    this.numeroCamisola = numeroCamisola;
    }
    
    public Jogador(String nome, int numeroCamisola, String posicao ,String posicaoint, int idade, int agressividade, int desempenho)
    {
        super(idade,nome);
        this.posicao = posicao;
        this.overall = desempenho;
        this.posicao_interna = posicaoint;
        switch (posicaoint){
            case "GR": this.attacking = 0;
                       this.defending = desempenho;
                       break;
            case "Defesa": this.attacking = desempenho - 30;           
                           this.defending = desempenho;
                           break;
            case "Medio": this.attacking = desempenho;
                          this.defending = desempenho;
                          break;
            case "Atacante": this.attacking = desempenho;           
                             this.defending = desempenho - 30;
                             break;
            default: this.attacking = 0;
                     this.defending = 0;
        }
        this.agressividade = agressividade;
        this.lesoes = new ArrayList<lesao>();
        this.golos = 0;
        this.numeroCamisola = numeroCamisola;
    }
    
    public String getPosicao(){
        return posicao;
    }
    
    public String getPosicaoInterna(){
        return posicao_interna;
    }
    
    public int getOverall(){
        return overall;
    }
    
    public int getAttacking(){
        return attacking;
    }
    
    public int getDefending(){
        return defending;
    }
    
    public int getAgressive(){
        return agressividade;
    }
    
    public boolean getLesionado(){
        return lesionado;
    }

    public ArrayList<lesao> getLesoes(){
        return lesoes;
    }
    
    public lesao getLesaoAtual(){
        
        for (lesao x:lesoes){
            if (x.getTratado() == false){
                return x;
            }
        }
        lesionado = false;
        return lesoes.get(0);
    }
    
    public void ficalesionado(lesao Lesao){
        lesionado = true;
        lesoes.add(Lesao);
    }
    
    public int getGolos(){
        return golos;
    }
    
    public int getNumeroCamisola()
    {
        return numeroCamisola;
    }
    
    public int getCartoesA()
    {
        return cartoesA;
    }
    
    public int getCartoesV()
    {
        return cartoesV;
    }
   
    public boolean getVermelho(){
        return vermelho;
    }
    
    public boolean getAmarelado(){
        return amarelado;
    }
    
    public void addGolos(int golos){
        this.golos += golos;
    }
    
    public int cartao(int caso){
        if (caso == 1 && amarelado == true){
            cartoesA += 1;
            vermelho = true;
            amarelado = false;
            return 2;
        }
        else if (caso == 1){
            cartoesA += 1;
            amarelado = true;
            return 1;
        }
        else if (caso == 2){
            cartoesV += 1;
            vermelho = true;
            amarelado = false;
            return 3;
        }
        else if (caso == 3){
            if (vermelho){
                vermelho = false;
                amarelado = false;
            }
            if (amarelado){
                amarelado = false;
            }
            return 4;
        }
        else {
            return 4;
        }
    }
}