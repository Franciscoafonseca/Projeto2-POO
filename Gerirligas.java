/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;
import java.io.*;
import java.util.*;


public class Gerirligas {
    private ArrayList<Liga> ligas;
    
    public Gerirligas(ArrayList<Liga> ligas)
    {
        this.ligas=ligas;
    }
    
    public ArrayList<Liga> getLigas()
    {
       return ligas; 
    }
}
