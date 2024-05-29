package com.mycompany.mavenproject2;
import java.util.*;

public class Treinador extends Pessoa 
{
    private ArrayList<String> especializacoes;
    private String estilofutebol;
        
    
    public Treinador(int idade, String nome,ArrayList<String> especializacoes, String estilo){
        super(idade,nome);
        this.especializacoes = especializacoes;
        this.estilofutebol = estilo;
    }
    
    public Treinador(){
        super(0,"");
        this.especializacoes = new ArrayList<String>();
        this.estilofutebol = "";
    }
    
    public Treinador(String nomeTreinador)
    {
        super(0,nomeTreinador);
        this.especializacoes = new ArrayList<String>();
        this.estilofutebol = "";
    }
    
    public ArrayList<String> getEspecializoes(){
        return especializacoes;
    }
    
    public String getEstilo(){
        return estilofutebol;
    }
    
   
}

