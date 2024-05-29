package com.mycompany.mavenproject2;

public class Arbitro extends Pessoa
{
    int anosExperiencia;
    
    public Arbitro(int idade, String nome, int anosExp){
        super(idade,nome);
        this.anosExperiencia = anosExp;
    }
    
    private int getAnosExp(){
        return anosExperiencia;
    }
}
