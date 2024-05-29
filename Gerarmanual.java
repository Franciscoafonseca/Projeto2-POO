/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.mavenproject2;
import java.io.*;
import java.util.*;

/**
 *
 * @author Huawei
 */
public class Gerarmanual 
{

    public void gerarmanualmenteliga()
    {
        System.out.println("Qual a liga da equipa: ");
        System.out.println("1.Liga Espanhola");
        System.out.println("2.Liga Portuguesa ");
        System.out.println("3.Liga Inglesa ");
        System.out.println("4.Liga Italiana");
        System.out.println("5.Liga Alema");
        Scanner lerliga = new Scanner(System.in);
        int liga = lerliga.nextInt();
        if( liga>5 || liga<=0)
        {
            System.out.println("Insira uma opcao valida !!!");
            gerarmanualmenteliga();
        }
        else 
        {
            System.out.println("Quantas equipas pretende inserir? ");
            Scanner lerinput = new Scanner(System.in);
            int numerodeequipas = lerinput.nextInt();
            
        }
    }
    
    public void procurarLiga(String nomejogador, Gerirligas conjuntodeligas)
    {
        int i =0;
        int quantidadedeligas = conjuntodeligas.getLigas().size();
        while (i<quantidadedeligas)
        {
            int f = 0;
            int numeroequipasliga = conjuntodeligas.getLigas().get(i).getEquipas().size();
            while(f<numeroequipasliga)
            {
                
            }
        }
    }
    public void gerarequipasmanualmente(int numeroequipas )
    {
        
        if( numeroequipas > 8)
        {
            System.out.println("So pode inserir ate 8 equipas ");
            Scanner lerinput = new Scanner(System.in);
            int numerodeequipas = lerinput.nextInt();
            gerarequipasmanualmente(numerodeequipas);
        }
        else if(numeroequipas <= 0)
        {
            System.out.println("Tem que inserir no minimo uma equipa !!!");
            Scanner lerinput = new Scanner(System.in);
            int numerodeequipas = lerinput.nextInt();
            gerarequipasmanualmente(numerodeequipas);
        }
        else
        {
            while( numeroequipas<=0 )
            {
                System.out.println("Equipa:");
                Scanner lerinput = new Scanner(System.in);
                String  equipa = lerinput.nextLine();
                //arranjar forma de validar a equipa que foi inserida 
                gerarjogadormanualmente(11, equipa);
                numeroequipas = numeroequipas - 1 ;
            }
        }
    }
    public void gerarjogadormanualmente(int numerodejogadores, String equipa)
    {
        System.out.println("Nome do jogador:");
        Scanner lernome = new Scanner(System.in);
        String  nome = lernome.nextLine();
        System.out.println("Posicao:");
        Scanner lerposicao = new Scanner(System.in);
        String  posicao = lerposicao.nextLine();
        System.out.println("Historico de posicao:");
        Scanner lerlesoes = new Scanner(System.in);
        String historicodelesoes = lerlesoes.nextLine();
        System.out.println("Equipa:");
        System.out.println("Equipa:");
        System.out.println("Equipa:");
        System.out.println("Equipa:"); 
    }
}
