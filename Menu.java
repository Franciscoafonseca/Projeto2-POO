/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.mavenproject2;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;



public class Menu 
{
    List<String> posicoespossiveis = new ArrayList<>(Arrays.asList("GUARDA-REDES", "DEFESA", 
                                     "MEDIO", "AVANCADO"));
    ArrayList<Equipa> equipas  = new ArrayList<Equipa>();
    ArrayList<Liga> ligas = new ArrayList<Liga>();
    ArrayList<Treinador> treinadores = new ArrayList<Treinador>();
    ArrayList<Arbitro> arbitros = new ArrayList<Arbitro>();
    
    
    public void abrirExplorerParaArquivo(String path) {
        try {
            Runtime.getRuntime().exec("explorer.exe /select," + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void imprimiropcoes()
    {
        System.out.println();
        System.out.println("Escolha a opcao que pretende realizar : ");
        System.out.println("1.Aceder informacoes de um jogador ");
        System.out.println("2.Aceder informacoes de um treinador ");
        System.out.println("3.Aceder as informacoes de uma equipa");
        System.out.println("4.Criar uma partida ");
        System.out.println("5.Ver uma liga de futebol");
        System.out.println("6.Exibir estatisticas de uma equipa");
        
        Scanner lerinput = new Scanner(System.in);
        int opcaoaaceder = lerinput.nextInt();
        switch (opcaoaaceder) 
        {
            case 1 -> {
                obterInformacoesJogador();
                imprimiropcoes();
                break;
            }
            case 2 -> { 
                obterInformacoesTreinador();
                imprimiropcoes();
                break;
            }
            case 3 -> {
                obterInformacoesEquipa();
                imprimiropcoes();
                break;
            }
            case 4 ->{
                novaPartida();
                imprimiropcoes();
                break;
            }
            case 5 -> {
                obterInformacoesLiga();
                imprimiropcoes();
                break;
            }
            case 6 -> {
                TeamStats();
                imprimiropcoes();
                break;
            }
            default -> {
                System.out.println("Selecione uma opção válida!!");
                imprimiropcoes();
                break;
            }    
        }
        
    }
    public void imprimirmenu() throws IOException
    {
        System.out.println("Escolha a opcao que prefere : ");
        System.out.println("1.Criar equipas manualmente ");
        System.out.println("2.Criar as equipas apartir do ficheiro ja importado no projeto ");
        Scanner lerinput = new Scanner(System.in);
        int opcao = lerinput.nextInt();
        switch (opcao) 
        {
            case 1 -> {
                gerarmanualmente();
                imprimiropcoes();
            }
            case 2 -> {
                lerFicheiro();
                gaurdarequipas();
                imprimiropcoes();
            }

        default -> System.out.println("Escolha uma opcao valida !!! ");
            
        }
    }
    
    public void gerarmanualmente()
    {
        ArrayList<Liga> variasligas = new ArrayList<Liga>();
        
        Scanner scannernumeroligas = new Scanner(System.in);
        System.out.println("Digite quantas ligas quer criar:");
        int numeroligas = scannernumeroligas.nextInt();
        while(numeroligas>0)
        {
            Liga novaliga= gerarmanualmenteligas();
            variasligas.add(novaliga);
            numeroligas--;
        }
        this.ligas = variasligas;
    }
    
    public Liga gerarmanualmenteligas()
    {
        ArrayList<Equipa> equipasmanualmente = new ArrayList<Equipa>();
        Scanner scannernumerodeequipas = new Scanner(System.in);
        System.out.println("Digite quantas equipas quer criar:");
        int numeroequipas = scannernumerodeequipas.nextInt();
        
        Scanner scannernomeliga = new Scanner(System.in);
        System.out.println("Digite o nome da liga:");
        String nomeliga = scannernomeliga.nextLine();
        
        while(numeroequipas>0)
        {
            Equipa equipaadicionada = gerarmanualmenteequipas();
            equipasmanualmente.add(equipaadicionada);
            numeroequipas=numeroequipas-1;
        }
        Liga liganova = new Liga(nomeliga,equipasmanualmente);
        return liganova;
        
    }
    
    public Equipa gerarmanualmenteequipas() 
    {
        ArrayList<Jogador> jogadoresequipas = new ArrayList<Jogador>();
        Scanner scannernome = new Scanner(System.in);
        System.out.println("Digite o nome da equipe:");
        String nomeEquipa = scannernome.nextLine();
        
        Scanner scannerestadio = new Scanner(System.in);
        System.out.println("Digite o estadio da equipe:");
        String estadioEquipa = scannerestadio.nextLine();
        
        Treinador treinador = gerartreinadormanualmente();
        
        int numjogadores = 11 ;
        while(numjogadores>0)
        {
            jogadoresequipas.add(gerarmanualmentejogadores());
            numjogadores= numjogadores-1;
        }

        Equipa equipa = new Equipa(nomeEquipa,estadioEquipa,jogadoresequipas,treinador);
        equipas.add(equipa);
        return equipa;
    }
    
    public Treinador gerartreinadormanualmente()
    {
        Scanner scannernome = new Scanner(System.in);
        System.out.println("Digite o nome do treinador :");
        String nometreinador = scannernome.nextLine();
        
        Scanner scanneridade = new Scanner(System.in);
        System.out.println("Digite a idade do treinador :");
        int idadetreinador = scanneridade.nextInt();
        int idadecerta = idadecerta(validaridade(idadetreinador),idadetreinador);
        
        ArrayList<String> especializacoestreinador = gerarespecializacoesmanualmente();
        
        Scanner scannerestilo = new Scanner(System.in);
        System.out.println("Digite o estilo do treinador :");
        String estilo = scannerestilo.nextLine();
    
        Treinador treinadorequipa = new Treinador(idadecerta,nometreinador,especializacoestreinador,estilo);
        return treinadorequipa;
    }
    public boolean validarstring(String caracteres){
        if(caracteres.matches("[\\p{L}\\p{M}]+"))
            return true;
        else 
            return false;
    }
    
    public String toString(boolean valido, String caracteresvalidos)
    {
        if(valido) return caracteresvalidos;
        else
        {
            Scanner scannercaracteres = new Scanner(System.in);
            System.out.println("Digite caracteres validos!!");
            String caracteresnovos = scannercaracteres.nextLine();
            return toString(validarstring(caracteresnovos),caracteresnovos);
        }
    }
    
    public ArrayList<String> gerarespecializacoesmanualmente()
    {
        ArrayList<String> especializacoes = new ArrayList<String>();
        Scanner scannernumespe = new Scanner(System.in);
        System.out.println("Digite o numero de especializacoes do treinador:");
        int numespecializacoes = scannernumespe.nextInt();
        while(numespecializacoes>0)
        {
            Scanner scanneresp = new Scanner(System.in);
            System.out.println("Especializacao:");
            String especializacoesinseridas = scanneresp.nextLine();
            especializacoes.add(especializacoesinseridas);
            numespecializacoes=numespecializacoes-1;
        }
        return especializacoes;
    }
    public boolean validarnumtshirt(int numero){
        if(numero < 1 || numero > 99) 
            return false;
        else 
            return true;
    }
    
    public boolean validaroverall(int numero){
        if(numero < 0 && numero > 100) 
            return false;
        else 
            return true;
    }
    
    public boolean validaridade(int numero){
        if(numero < 15 || numero > 60) 
            return false;
        else 
            return true;
    }
    
    public boolean validarposicao(String posicaojogador)
    {
        String POSICAO = posicaojogador.toUpperCase();
        int i =0;
        while(i<posicoespossiveis.size())
        {
            if( POSICAO.equals(posicoespossiveis.get(i)) )
                return true;
            else 
                i=i+1;
        }
        return false;
    }
        
    public int idadecerta(boolean valida, int idade){
        if(valida) return idade;
        else 
        {
            Scanner scanneridadecerta = new Scanner(System.in);
            System.out.println("Digite uma idade valido!!");
            int idadecerta = scanneridadecerta.nextInt();
            return idadecerta(validaridade(idadecerta),idadecerta);
        }
    }
    public String posicaocerta(boolean valida, String posicao)
    {
        if(valida) return posicao;
        else {
            Scanner scannerposicao = new Scanner(System.in);
            System.out.println("Digite uma posição valida!!");
            String novaposicao = scannerposicao.nextLine();
            return posicaocerta(validarposicao(novaposicao),novaposicao);
        }
    }
    
    public String gerarposicaointerna(String posicao)
    {
        if(posicao.equals(posicoespossiveis.get(0)))
            return "GR";
        if( posicao.equals(posicoespossiveis.get(1)) || posicao.equals(posicoespossiveis.get(2)) 
                || posicao.equals(posicoespossiveis.get(3)) )
            return "Defesa";
        if( posicao.equals(posicoespossiveis.get(4)) || posicao.equals(posicoespossiveis.get(5)))
            return "Medio";
        if(posicao.equals(posicoespossiveis.get(6)) || posicao.equals(posicoespossiveis.get(7)) 
                || posicao.equals(posicoespossiveis.get(8)))
            return "Atacante";
        else 
            return "Nenhuma";
    }
    
    public int numerotshirtcerto(boolean valido , int numerotshirt )
    {
        if(valido) return numerotshirt; 
        else 
        {
            Scanner scannernumerotshirt = new Scanner(System.in);
            System.out.println("Digite um numero de tshirt valido!!");
            int novonumero = scannernumerotshirt.nextInt();
            return numerotshirtcerto(validarnumtshirt(novonumero),novonumero);
        }
    }
    
    public int overallcerto(boolean valido , int numerotshirt )
    {
        if(valido) return numerotshirt; 
        else 
        {
            Scanner scanneroverallnovo = new Scanner(System.in);
            System.out.println("Digite um overall valido!!");
            int novooverall = scanneroverallnovo.nextInt();
            return overallcerto(validaroverall(novooverall),novooverall);
        }
    }
    
    public Jogador gerarmanualmentejogadores()
    {
        Scanner scannernome = new Scanner(System.in);
        System.out.println("Digite o nome do jogador :");
        String nomejogador = scannernome.nextLine();
        String nomecerto = toString(validarstring(nomejogador), nomejogador); 
        
        Scanner scannernumtshirt = new Scanner(System.in);
        System.out.println("Digite o numero de T-shirt do jogador (1-99) :");
        int numerosthirtjogador = scannernumtshirt.nextInt();
        int numerotshirtcerto = numerotshirtcerto(validarnumtshirt(numerosthirtjogador),numerosthirtjogador);
        
        Scanner scanneridade = new Scanner(System.in);
        System.out.println("Digite a idade do jogador :");
        int idadejogador = scanneridade.nextInt();
        int idadecerta = idadecerta(validaridade(idadejogador),idadejogador);
        
        Scanner scannerposicao = new Scanner(System.in);
        System.out.println("Digite a posicao do jogador:");
        String posicao = scannerposicao.nextLine();
        String posicaocerta = posicaocerta(validarposicao(posicao),posicao);
        
        String posicaointerna = gerarposicaointerna(posicaocerta);
        
        Scanner scanneroverall = new Scanner(System.in);
        System.out.println("Digite o overall do jogador (0-100) :");
        int overall = scanneroverall.nextInt();
        int overallcerto = overallcerto(validaroverall(overall),overall);
        
        Jogador jogadordaequipa = new Jogador(nomejogador,numerotshirtcerto,posicaocerta,posicaointerna,idadecerta,0,overallcerto);
        return jogadordaequipa;
        
    }

    private void lerFicheiro() {
        System.out.println("Digite o caminho do arquivo:");
        Scanner scanner = new Scanner(System.in);
        String caminhoArquivo = scanner.nextLine();
        
      
        
        LerArquivoScanner Ler= new LerArquivoScanner(caminhoArquivo);
        
        treinadores = Ler.carregaTreinadores();
        
        equipas = Ler.carregaEquipas(treinadores);
        //System.out.println(equipas.size());
        
        
        ligas = Ler.carregaLigas(equipas);
        
        arbitros= Ler.carregaArbitros();
        
        
          //System.out.println(arbitros.size());
          //Arbitro teste = arbitros.getFirst();
          //System.out.println(teste.getNome());
//        System.out.println(treinadores.getLast().getEstilo());
    }

    private void gaurdarequipas() {
        // Adicionar a lógica real para armazenar as equipas pode ser num base de dados, num arquivo, etc.
    }

    private void gerarapartirficheiros() {
//        System.out.println("Digite o caminho da pasta x:");
//        Scanner scanner = new Scanner(System.in);
//        String caminhoDiretorio = scanner.nextLine();
//
//        File diretorio = new File(caminhoDiretorio);
//        File[] arquivos = diretorio.listFiles();
//
//        if (arquivos != null) {
//            for (File arquivo : arquivos) {
//                // Adicionar a lógica real para processar cada arquivo no diretório
//                System.out.println("Processando arquivo: " + arquivo.getName());
//            }
//        } else {
//            System.out.println("O diretório está vazio ou não é um diretório válido.");
//        }
       }
    
    public void novaPartida(){
        Partida partida;
        boolean verifica= false;
        String nomeLiga = "";
        Equipa equipa=new Equipa("","");
        while(!verifica){
        System.out.println("Indique uma das equipas abaixo:");
        for(int i=0;i<equipas.size();i++){
            System.out.println(equipas.get(i).getNome());
        }
        Scanner lerinput = new Scanner(System.in);
        String texto = lerinput.nextLine();
//        System.out.println(texto);
        try{
            for(int n=0;n<equipas.size();n++){
                String nome=new String(equipas.get(n).getNome());
                if(nome.equalsIgnoreCase(texto)){
                    equipa=equipas.get(n);
                    break;
                }
            }
            if(equipa.getNome()!=""){
                nomeLiga = obterLiga(equipa);
                verifica=true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //gera nº aleatório para escolha de segunda equipa
    Random rand = new Random();
    int random = rand.nextInt(7);
    if (random <=7){
    Date data = new Date();
    String local = "Lisboa";
    Equipa equipa2 = new Equipa("","");
    String nome2 = "";
    //verifica se a variavel nomeLiga esta preenchida, ou seja, se encontrou a liga da primeira equipa
    if (nomeLiga=="")
    {
        System.out.println("Erro ao encontrar a liga da equipa 1");
    }
    else
    {
        //percorre a lista de ligas, obtem a liga da primeira equipa e respectivas equipas 
        for (Liga liga : ligas){
            if (liga.getNome()== nomeLiga)
            {
                nome2 = liga.getEquipas().get(random).getNome();
                break;
            }
        }
        for(int n=0;n<equipas.size();n++){
                String nome=new String(equipas.get(n).getNome());
                if(nome2.equalsIgnoreCase(nome)){
                    equipa2=equipas.get(n);
                    break;
                }
            }
        
        //variaveis utilizadas para verificar se equipa1 = a equipa 2
        String nomeEquipa1, nomeEquipa2 ="";
        if (equipa2.getNome()!="")
        {
        nomeEquipa2 =new String(equipa2.getNome());
        }
        nomeEquipa1=new String(equipa.getNome());
        
        //gera random para selecionar arbitro
        random = rand.nextInt(arbitros.size());
        Arbitro arbitro;
        if (random<arbitros.size())
        {
            arbitro = arbitros.get(random); 
        }   
        else
            arbitro = arbitros.get(0);
            //verifica se a segunda equipa é diferente da primeira e cria nova partida
            if(!nomeEquipa1.equalsIgnoreCase(nomeEquipa2)){
                System.out.println("Partida entre "+ nomeEquipa1 + "  " + nomeEquipa2);
                System.out.println("Deseja jogar em que campo? 0 para campo neutro, 1 para campo do " + nomeEquipa1 + " e 2 para o campo do "+ nomeEquipa2);
                Scanner lercampo = new Scanner(System.in);
                int campo = lercampo.nextInt();
                if (campo == 1){
                    local = equipa.getEstadio();
                }
                else if (campo == 2){
                    local = equipa2.getEstadio();
                }
                partida = new Partida(equipa, equipa2, data, local, arbitro);
                partida.EfetuarPartida(campo);
            }
            else
                System.out.println("A segunda equipa é igual à primeira! Tente novamente");

        }
    }
    }
    //obtem nome da liga a que pertence uma equipa (passando uma equipa)
    private String obterLiga(Equipa equipa)
    {
        String nomeLiga ="";
        //percorre as ligas
        for(Liga ligaAux : ligas){
            //percorre as equipas da liga
            ArrayList<Equipa> equipasLiga = ligaAux.getEquipas();
            for(Equipa eq : equipasLiga)
            {
                if (eq.getNome().equals(equipa.getNome()))
                {
                    nomeLiga = ligaAux.getNome();
                    break;
                }
            }
        }
        return nomeLiga;
    }
    
    public void obterInformacoesJogador() {
        // Criar um objeto Scanner para obter entrada do utilizador
        Scanner scanner = new Scanner(System.in);
        // Solicitar ao utilizador que insira o nome do jogador
        System.out.println("Digite o nome do jogador:");
        String nomeJogador = scanner.nextLine();

        // Procurar o jogador nas equipas
        Jogador jogadorEncontrado = null;
        for (Equipa equipa : equipas) {
            jogadorEncontrado = equipa.obterJogadorPorNome(nomeJogador);
            if (jogadorEncontrado != null) {
                break;
            }
        }

        // Exibir as informações do jogador, se encontrado
        if (jogadorEncontrado != null) {
            //coloca um espaço entre a informação anterior e a inforação do jogador
            System.out.println();
            System.out.println("Informações do jogador:");
            System.out.println("Nome: " + jogadorEncontrado.getNome());
            System.out.println("Idade: " + jogadorEncontrado.getIdade());
            System.out.println("Posição: " + jogadorEncontrado.getPosicao());
            System.out.println("Overall: " + jogadorEncontrado.getOverall());
            System.out.println("Agressividade: " + jogadorEncontrado.getAgressive());
            System.out.println("Golos: " + jogadorEncontrado.getGolos());
            // Verificar se o jogador está lesionado
            if (jogadorEncontrado.getLesionado() == true){
                System.out.println("Jogador encontra-se Lesionado.");
                lesao LesaoJ = jogadorEncontrado.getLesaoAtual();
                System.out.println(LesaoJ.getNome());
                System.out.println("Duração de " + LesaoJ.getGravidade() + " jogos.");
            }
            else{
                System.out.println("Jogador não encontra-se Lesionado.");
            }
            
        } else {
            // Informar que o jogador não foi encontrado
            System.out.println("Jogador não encontrado.");
        }
    }
    public void obterInformacoesEquipa() {
    // Criar um objeto Scanner para obter entrada do utilizador
    Scanner scanner = new Scanner(System.in);
    // Solicitar ao utilizador que insira o nome da equipa
    System.out.println("Digite o nome da equipa: ");
    String nomeEquipa = scanner.nextLine();

    // Procurar a equipa pelo nome
    Equipa equipaEncontrada = obterEquipa(nomeEquipa);

    // Exibir as informações da equipa, se encontrada
    if (equipaEncontrada != null) {
        System.out.println("Informações da equipa:");
        System.out.println("Nome: " + equipaEncontrada.getNome());
        System.out.println("Treinador: " + equipaEncontrada.getTreinador().getNome());
        System.out.println("Estádio: " + equipaEncontrada.getEstadio());
        System.out.println("Jogadores:");
        // Obter a lista de jogadores da equipa
        ArrayList<Jogador> jogadores = equipaEncontrada.getJogadores();
        // Procurar a lista de jogadores e exibir suas informações
        for (Jogador jogador : jogadores) {
            System.out.println("Numero: " + jogador.getNumeroCamisola() + " - " + jogador.getNome() + " - Idade:" + jogador.getIdade() + " - Posição: " + jogador.getPosicao() );
        }
    } else {
        System.out.println("Equipa não encontrada.");
    }
}
    
    public void obterInformacoesTreinador() {
        // Criar um objeto Scanner para obter entrada do utilizador
        Scanner scanner = new Scanner(System.in);
        // Solicitar ao utilizador que insira o nome do treinador
        System.out.println("Digite o nome do treinador: ");
        String nomeTreinador = scanner.nextLine();

        // Procurar a equipa pelo nome
        Treinador treinadorEncontrado = obterTreinador(nomeTreinador);

        // Exibir as informações do treinador, se encontrado
        if (treinadorEncontrado != null) {
            System.out.println("Informações do treinador:");
            System.out.println("Nome: " + treinadorEncontrado.getNome());
            System.out.println("Estilo: " + treinadorEncontrado.getEstilo());
            // Obter a lista de especializações do treinador
            ArrayList<String> especializacoes = treinadorEncontrado.getEspecializoes();
            // Verificar se o treinador possui especializações
            if (especializacoes.size()>0)
            {
                System.out.println("Especializações:");
                // Procurar a lista de especializações e exibir cada uma
                for(String especializacao : especializacoes)
                {
                    System.out.println(especializacao);
                }
            }
            else
                // Informar que o treinador não possui especializações
               System.out.println("Sem especializações");
            
        } else {
            // Informar que o treinador não foi encontrado
            System.out.println("Treinador não encontrado.");
        }
    }
    
    public Equipa obterEquipa(String nomeEquipa) {
         // Percorre a lista de equipas
        for (Equipa equipa : equipas) {
            // Verificar se o nome da equipa coincide, ignorando maiúsculas e minúsculas
            if (equipa.getNome().equalsIgnoreCase(nomeEquipa)) {
                return equipa;
            }
        }
        return null; // Se a equipe não for encontrada, retorne null
    }

    public Treinador obterTreinador(String nomeTreinador) {
        // Percorre a lista de treinadores
        for (Treinador treinador : treinadores) {
            // Verifica se o nome do treinador coincide, ignorando maiúsculas e minúsculas
            if (treinador.getNome().equalsIgnoreCase(nomeTreinador)) {
                return treinador;
            }
        }
        return null; // Se o treinador não for encontrado, retorna null
    }
    
    public Liga obterLiga(String nomeLiga) {
        // Percorre a lista de ligas
        for (Liga liga : ligas) {
            // Verificar se o nome da liga coincide, ignorando maiúsculas e minúsculas
            if (liga.getNome().equalsIgnoreCase(nomeLiga)) {
            return liga;
            }
        }
        return null; // Se a liga não for encontrada, retorne null
    }

    public void obterInformacoesLiga(){
    // Criar um objeto Scanner para obter entrada do utilizador
    Scanner scanner = new Scanner(System.in);
    System.out.println("Digite o nome da liga: ");
    String nomeLiga = scanner.nextLine();

    // Procurar a liga pelo nome
    Liga liga = obterLiga(nomeLiga);

    // Exibir as informações da liga, se encontrada
    if (liga != null) {
        System.out.println("Informações da liga:");
        System.out.println("Nome: " + liga.getNome());
        System.out.println("Equipas da liga:");
        // Obter a lista de equipas da liga
        ArrayList<Equipa> equipas = liga.getEquipas();
        // Procurar a lista de equipas e exibir seus nomes
        for (Equipa equipa : equipas) {
            System.out.println("Equipa" + "-"+ equipa.getNome());
        }
    } 
    else {
        System.out.println("Liga não encontrada.");
    }
    }
    
    public void TeamStats(){
       Scanner scanner = new Scanner(System.in);
       System.out.println("Digite o nome da equipa: ");
       String nomeEquipa = scanner.nextLine();

        // Procurar a equipa pelo nome
        Equipa equipaEncontrada = obterEquipa(nomeEquipa);

    // Exibir as informações da equipa, se encontrada
    if (equipaEncontrada != null) {
        System.out.println("Informações da equipa:");
        System.out.println("Nome: " + equipaEncontrada.getNome());
        System.out.println("Treinador: " + equipaEncontrada.getTreinador().getNome());
        System.out.println("Jogos: " + equipaEncontrada.getJogos());
        System.out.println("Vitorias: " + equipaEncontrada.getVitorias());
        System.out.println("Empates: " + equipaEncontrada.getEmpates());
        System.out.println("Derrotas: " + equipaEncontrada.getDerrotas());
        System.out.println("Forma dos ultimos 5 jogos: " + equipaEncontrada.getLast5());
        System.out.println("Golos Marcados: " + equipaEncontrada.getGolosMarcados());
        System.out.println("Golos Sofridos: " + equipaEncontrada.getGolosSofridos());
    } else {
        System.out.println("Equipa não encontrada.");
    } 
    }
}

    