/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;
import java.util.Random;


public class Moeda {
    private final String cara;
    private final String coroa;
    private Random random;

    public Moeda(String cara, String coroa) 
    {
        this.cara = cara;
        this.coroa = coroa;
        this.random = new Random();
    }

    public String getFace()
    {
        int escolha = random.nextInt(2);
        if(escolha == 0)
        {
            return cara;
        }
        else
        {
            return coroa;
        }
    }
}
