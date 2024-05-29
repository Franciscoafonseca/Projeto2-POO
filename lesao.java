package com.mycompany.mavenproject2;

public class lesao 
{
    String nome;
    int gravidade;
    boolean tratado;
    
    public lesao(String nome, int gravidade, boolean tratado){
        this.nome = nome;
        this.gravidade = gravidade;
        this.tratado = tratado;
              
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getGravidade(){
        return gravidade;
    }
    
    public boolean getTratado(){
        return tratado;
    }
    
    public void tratalesao(){
        if (tratado == false){
            gravidade--;
            if (gravidade == 0){
                tratado = true;
            }
        }
    }
}

