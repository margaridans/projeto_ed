/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.Enumeracoes.TipoMensagem;
import Database.SqlConnection;
import arrayList.ArrayUnorderedList;
import iterator.ArrayIterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import projeto_ed.Projeto_ed;

/**
 *
 * @author Bernardino
 */
public class Menu {
        SqlConnection sql = Projeto_ed.connection;
    
    public void Menu(String user_logado) throws IOException {
        System.out.println("Bem vindo " + user_logado);

        System.out.println("\n \n");
        System.out.println("* * * * * * * * * * Menu * * * * * * * * * * ");
        System.out.println("*                                          * ");
        System.out.println("*                                          * ");
        System.out.println("*             1- Escrever mensagem         * ");
        System.out.println("*             2- Ver utilizadores          * ");
        System.out.println("*             3- Terminar Sessão           * ");
        System.out.println("*                                          * ");
        System.out.println("*                                          * ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * *  * ");
        System.out.println("O que pretende fazer? ");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String escolha = in.readLine();

        if ("1".equals(escolha)) {
            System.out.println("\n \n");
            System.out.println("* * * * * * * * * * Menu Mensagem * * * * * * * * * * ");

           String conteudo= escreverMensagem();

            System.out.println("\n \n");
            System.out.println("A sua mensagem vai ser de que tipo: (1 - Privada/ 2 - Pública) ");

            

            int lerTipoMensagem = Integer.parseInt(in.readLine());

            Integer IdTipoMensagem = null;
            if (1 == lerTipoMensagem) {
                IdTipoMensagem = sql.verTipoMensagem(TipoMensagem.PRIVADA.getDescricao());
            } else if (2 == lerTipoMensagem) {
                IdTipoMensagem = sql.verTipoMensagem(TipoMensagem.PUBLICA.getDescricao());
            }

            //FALTA GUARDAR O TIPO DE MENSAGEM
            System.out.println("\n");

            System.out.println("Pretende guardar a mensagem? S/N");
            String lerOpcaoMensagem = in.readLine();

            if ("S".equals(lerOpcaoMensagem)) {
                 Date d = new Date();
        
        
                Pessoa p = sql.getPessoa(user_logado);
                Mensagem msg = new Mensagem(conteudo,d, IdTipoMensagem, p);

                sql.inserirMensagem(msg);
                System.out.println("Mensagem guardada");
            } else if ("N".equals(lerOpcaoMensagem)) {
                System.out.println("A sua mensagem não foi guardada");
                escreverMensagem();

            }
        } else if("2".equals(escolha)) {
           ArrayUnorderedList<Pessoa> p = new ArrayUnorderedList<>();
           p = sql.getAllPessoas(user_logado);
            if(p != null){
                //System.out.println("Existem " + p.size() + " pessoas");
                printAllUsers(p);
                Pessoa pEscolhida =  escolherUser(p);
                
            }else{
                System.out.println("É nulo");
            }
            
        }
        else if ("3".equals(escolha)) {
            System.out.println("A sua sessão foi terminada. Até à próxima");
            user_logado = null;
            Projeto_ed inicio = new Projeto_ed();
        } else if (!"1".equals(escolha) || !"3".equals(escolha)) {
            System.out.println("Essa escolha não é válida. Escolha apenas algo válido perante o menu");
        }
    }

    public String escreverMensagem() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n");
        System.out.println("Escreva aqui a sua mensagem: ");
        System.out.println(" /!\\ Esta sua mensagem irá aparecer na sua página inicial");
        System.out.println("\n");
        String lerMensagem = in.readLine();
        String mensagem_conteudo = lerMensagem;
        
        return mensagem_conteudo;
    }
    
    private void printAllUsers(ArrayUnorderedList<Pessoa> u){
         
        int counter = 0;
        ArrayIterator it = (ArrayIterator)u.iterator();
        System.out.println();
        System.out.println("Escolha um utilizador através do seu índice");
        while(it.hasNext()){
            counter++;
            Pessoa p = (Pessoa)it.next();
            System.out.println( counter + " -> "+ p.getUser_email());
        }
        
    }
    
    private Pessoa escolherUser(ArrayUnorderedList<Pessoa> u) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String escolha = in.readLine();
        ArrayIterator it = (ArrayIterator)u.iterator();
        Integer counter = 0;
         while(it.hasNext()){
            counter++;
            Pessoa p = (Pessoa)it.next();
            if(escolha.equals(counter.toString())){
               System.out.println("Escolheu o utilizador " + p.getUser_nome());
               return p;
            }                   
        }
        return null;
    }
    
    public void MenuPessoa() {
        
    }
}
