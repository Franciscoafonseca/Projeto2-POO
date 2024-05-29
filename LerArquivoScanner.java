/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class LerArquivoScanner {
    //caminho da pasta onde estao os ficheiros
    String caminho="";
     
    //construtor que recebe o caminho da pasta
    public LerArquivoScanner(String caminho){
        this.caminho=caminho;
    }
    //metodo para carregar as equipas de um ficheiro
    public ArrayList<Equipa> carregaEquipas(ArrayList<Treinador> listaTreinadores){
        //inicializa as variaveis auxiliares
        ArrayList<Equipa> equipas=new ArrayList<Equipa>();
        Equipa equipa = new Equipa("","");
        String nomeEquipa ="";
        String nomeEstadio = "";
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        String nomeJogador="";
        int numeroCamisola =0;
        String posicao ="";
        String posicaoint = "";
        int idade =0;
        int agressividade =0;
        int desempenho = 0;
        Jogador jogador;
        String nomeTreinador = "";
        //nome do ficheiro onde estão guardadas as equipas
        String caminhoDoArquivo = caminho+"\\equipa_.txt";
        try {
            Scanner scanner = new Scanner(new File(caminhoDoArquivo));
            while (scanner.hasNext()) {
                String linha = scanner.nextLine();
                //System.out.println(linha);
                //verifica se a linha que está a ler é um comentário
                if (!linha.startsWith("//"))
                {
                    //se o nome da equipa nao estiver preenchido então é uma nova equipa
                    if(nomeEquipa=="")
                    {
                        nomeEquipa = linha;
                        
                    }
                    else if (nomeEquipa != "" && nomeEstadio == "")
                    {
                        nomeEstadio = linha;
                        equipa=new Equipa(nomeEquipa,nomeEstadio);
                    }
                    else
                    {
                        //se a variavel nomejogador nao estiver preenchida então preenche ( e quer dizer que é a primeira linha do jogador)
                        if (nomeJogador =="" && equipa.getJogadores().size()<11)
                        {
                            nomeJogador =linha;
                        }
                        //se o nomerodacamisola for gual a zero e o nome do jogador ja estiver valor então quer dizer que está na segunda linha dos dados do jogador e coloca o numero da camisola 
                        else if (numeroCamisola==0 && nomeJogador !="" && equipa.getJogadores().size()<11)
                        {
                            numeroCamisola = Integer.parseInt(linha);
                        }
                        //se a posicao nao tiver valor e o numerocamisola for diferente de zero e tiver nome entao esta na linha 3 dos dados de jogador. Coloca na variavel posicao o valor
                        else if (posicao =="" && numeroCamisola !=0 && nomeJogador!="" && equipa.getJogadores().size()<11)
                        {
                            posicao = linha;
                        }
                        else if (posicao !="" && numeroCamisola !=0 && nomeJogador!="" && posicaoint == "" && equipa.getJogadores().size()<11)
                        {
                            posicaoint = linha;
                        }
                        //se a variavel idade estiver a 0 e as outras 3 variaveis ja tiverem valor, entao esta na ultima linha do jogador. Coloca o valor da linha na variavel idade
                        else if (idade==0 && posicaoint != "" && posicao !="" && numeroCamisola !=0 && nomeJogador!="" && equipa.getJogadores().size()<11)
                        {
                                idade = Integer.parseInt(linha);
                        }
                        else if (idade!=0 && posicaoint != "" && posicao !="" && numeroCamisola !=0 && nomeJogador!="" && agressividade ==0 && equipa.getJogadores().size()<11)
                        {
                            agressividade = Integer.parseInt(linha);
                        }
                        else if (idade!=0 && posicaoint != "" && posicao !="" && numeroCamisola !=0 && nomeJogador!="" && agressividade !=0 && desempenho ==0 && equipa.getJogadores().size()<11)
                        {
                            desempenho = Integer.parseInt(linha);
                                              
                            //cria um novo jogador com os dados das variaveis
                            jogador = new Jogador(nomeJogador,numeroCamisola, posicao, posicaoint, idade,agressividade, desempenho );
                            //adiciona o jogador á lista de jogadores da equipa
                            equipa.AdicionaJogador(jogador);
                            //inicializa as variaveis idade, numerocamisola,nomejogador e posicao (para novo jogador)
                            idade=0;
                            numeroCamisola=0;
                            nomeJogador="";
                            posicao ="";
                            posicaoint ="";
                            agressividade = 0;
                            desempenho = 0;
                            
                        }
                        //verifica se a equipa já tem 11 jogadores, se sim então adicoona-a à lista de equipas e prepara para criar nova equipa
                        else if (equipa.getJogadores().size()==11 && nomeTreinador=="")
                        {
                            nomeTreinador = linha;
                            Treinador treinador = obterTreinador(nomeTreinador, listaTreinadores);
                            if(treinador== null){
                                System.out.println("Nao foi encontrado o treinador especificado para a equipa");
                                treinador = new Treinador(nomeTreinador);
                            }
                            equipa.setTreinador(treinador);
                            equipas.add(equipa);
                            nomeEquipa="";
                            nomeEstadio="";
                            nomeTreinador ="";
                                      
                        }
                    }
                }       
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return equipas;
    }

    public ArrayList<Liga> carregaLigas(ArrayList<Equipa> equipas){
        
        //inicializa as variaveis auxiliares
        ArrayList<Equipa> equipasPassadas = equipas;
        ArrayList<Liga> ligas=new ArrayList<Liga>();
        Liga liga;
        String nomeLiga ="";
        String nomeEquipa="";
        ArrayList<Equipa> listaEquipas = new ArrayList<Equipa>();
        
        //nome do ficheiro onde estão guardadas as equipas
        String caminhoDoArquivo = caminho+"\\ligas_.txt";
        try {
            Scanner scanner = new Scanner(new File(caminhoDoArquivo));
            while (scanner.hasNext()) {
                String linha = scanner.nextLine();
                //verifica se a linha que está a ler é um comentário
                if (!linha.startsWith("//")){
                    //verifica se a linha que está a ler comeca por (
                    if(!linha.startsWith("(")){
                        // Se o nome da liga ainda não foi preenchido, atribui o valor da linha
                        if(nomeLiga=="")
                        {
                            nomeLiga = linha;
                        }
                    else
                    {
                        // verifica se o nome da liga já foi preenchido e o nome da equipa ainda não foi preenchido
                        if (nomeLiga!="" && nomeEquipa==""){
                            nomeEquipa = linha;
                            // Compara o nome da equipa do ficheiro com as equipas passadas para obter os detalhes da equipa
                            for (Equipa equipa : equipasPassadas) {
                            
                                //System.out.println("equipa lista:"+equipa.getNome()+" equipa ficheiro: " + linha );
                                String nome = new String (equipa.getNome());
                                String nomeLinha = new String(linha);
                                // Se encontrar uma correspondência, cria uma nova instância de Equipa com os detalhes e adiciona à lista
                                if (nome.equalsIgnoreCase(nomeLinha))
                                {
                                   Equipa eq = new Equipa(equipa.getNome(),equipa.getEstadio(),equipa.getJogadores(),equipa.getTreinador());
                                   listaEquipas.add(eq);
                                }
                            };
                            // Reinicializa o nome da equipa após o processamento
                            nomeEquipa = "";
                        }
                        // Quando a lista de equipas atinge o tamanho desejado (7 equipas), cria uma nova instância de Liga e adiciona à lista de ligas
                        if (listaEquipas.size()==7)
                        {
                            liga = new Liga(nomeLiga,listaEquipas);
                            ligas.add(liga);
                            nomeLiga ="";
                            nomeEquipa ="";
                            listaEquipas = new ArrayList<Equipa>();
                        }
                    }
                    }
                }
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        
                
        return ligas;
        }
    
    public ArrayList<Arbitro> carregaArbitros(){
        
        //inicializa as variaveis auxiliares
        ArrayList<Arbitro> arbitros=new ArrayList<Arbitro>();
        Arbitro arbitro;
        String nomeArbitro ="";
        int idadeArbitro=0;
        int anosDeExperienciaArbitro=0;
        
        //nome do ficheiro onde estão guardadas as equipas
        String caminhoDoArquivo = caminho+"\\arbitro_.txt";
        try {
            // Cria um objeto Scanner para ler o ficheiro
            Scanner scanner = new Scanner(new File(caminhoDoArquivo));
            // Loop para percorrer as linhas do ficheiro
            while (scanner.hasNext()) {
                String linha = scanner.nextLine();
                // Verifica se o nome do árbitro ainda não foi preenchido
                if(nomeArbitro=="")
                {
                    nomeArbitro=  linha;
                }
                else
                {
                    // Se o nome do árbitro já foi preenchido e a idade ainda não foi preenchida
                     if (nomeArbitro!="" && idadeArbitro == 0)
                     {
                         idadeArbitro = Integer.parseInt(linha);
                     }
                     // Se o nome do árbitro e a idade já foram preenchidos e os anos de experiência ainda não foram preenchidos
                     else if (nomeArbitro!="" && idadeArbitro!=0 && anosDeExperienciaArbitro==0)
                     {
                         anosDeExperienciaArbitro = Integer.parseInt(linha);
                         // Cria uma nova instância de Arbitro com os detalhes e adiciona à lista de árbitros
                         arbitro = new Arbitro(idadeArbitro,nomeArbitro,anosDeExperienciaArbitro);
                         arbitros.add(arbitro);
                         // Reinicializa as variáveis após o processamento
                         nomeArbitro ="";
                         idadeArbitro =0;
                         anosDeExperienciaArbitro =0;
                     }
                }
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }      
        return arbitros;
        }
    

    public ArrayList<Treinador> carregaTreinadores(){
        
        //inicializa as variaveis auxiliares
        ArrayList<Treinador> treinadores=new ArrayList<Treinador>();
        Treinador treinador;
        String nomeTreinador ="";
        int idade = 0;
        String estilofutebol="";
        
        //nome do ficheiro onde estão guardadas as equipas
        String caminhoDoArquivo = caminho+"\\treinador_.txt";
        try {
            // Cria um objeto Scanner para ler o ficheiro
            Scanner scanner = new Scanner(new File(caminhoDoArquivo));
            // Loop para percorrer as linhas do ficheiro
            while (scanner.hasNext()) {
                String linha = scanner.nextLine();
                // Verifica se o nome do treinador ainda não foi preenchido
                if(nomeTreinador=="")
                {
                    nomeTreinador=  linha;
                }
                else
                {
                    // Se o nome do treinador já foi preenchido e a idade ainda não foi preenchida
                     if (nomeTreinador!="" && idade == 0)
                     {
                         idade = Integer.parseInt(linha);
                     }
                     // Se o nome do treinador, a idade e o estilo de futebol já foram preenchidos
                     else if (nomeTreinador!="" && idade!=0 && estilofutebol=="")
                     {
                         estilofutebol = linha;
                         // Cria uma nova instância de Treinador com os detalhes e adiciona à lista de treinadores
                         treinador = new Treinador(idade,nomeTreinador,new ArrayList<String>(),estilofutebol);
                         treinadores.add(treinador);
                         nomeTreinador ="";
                         idade =0;
                         estilofutebol ="";
                     }
                }
                    
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return treinadores;
    }    
    
    // Este método recebe o nome de um treinador e uma lista de treinadores, e retorna o treinador correspondente ao nome fornecido
    // Caso não seja encontrado nenhum treinador com o nome fornecido, retorna null
    public Treinador obterTreinador(String nomeTreinador, ArrayList<Treinador> treinadores) 
    {
        // Percorre a lista de treinadores para encontrar o treinador com o nome fornecido (ignorando maiúsculas/minúsculas)
        for (Treinador treinador : treinadores) {
            if (treinador.getNome().equalsIgnoreCase(nomeTreinador)) {
                return treinador;
            }
        }
        return null; // Se o treinador não for encontrado, retorne null
    }
}