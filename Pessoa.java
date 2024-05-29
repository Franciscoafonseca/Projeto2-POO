package com.mycompany.mavenproject2;
import java.util.*;
import java.io.File;

public class Pessoa 
{
    private int idade;
    private String nome;
    
    public Pessoa(int idade, String nome) {
        this.idade = idade;
        this.nome = nome;
    }
    
    protected int getIdade(){
        return idade;
    }
    
    protected String getNome(){
        return nome;
    }
    
}
