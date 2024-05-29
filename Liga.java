package com.mycompany.mavenproject2;
import java.util.*;

public class Liga 
{
    private String nome;
    //private ArrayList<Equipa> equipasdaliga;
    private ArrayList<Equipa> equipas;
    
    public Liga(String nome, ArrayList<Equipa> equipas){
        this.nome = nome;
        this.equipas = equipas;
    }
    
    public String getNome(){
        return nome;
    }
    
    public ArrayList<Equipa> getEquipas(){
        return equipas;
    } 
}