package pt.ulusofona.deisi.aedProj2020;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class FunctionsQuery {

    public static int countMoviesMonthYear(int mes,int ano){
        int countMovies=0;//variavel para contar a quantidade de filmes realizados em um mes/ano

        if (mes>12 || mes<1){return 0;}//verifica se o mês esta entre 1 e 12

        for (int id: Main.dicionarioAno.get(ano)){//percorre os id's do ano passado

            if (Main.dicionarioMovies.get(id).mesLancamento==mes){//verifica se o mês correspode

                countMovies++;//se sim soma ao count
            }
        }
        return countMovies;
    }

    public static String topMonthMovieCount(int ano){
        int topMonth=1;//guarda o mês com maior countMovie
        int countMovie= countMoviesMonthYear(1,ano);//count primeiro mes

        for (int i=2; i<=12; i++){//percorre os meses

            int countMoviesMonth = countMoviesMonthYear(i,ano);//conta quantos filmes aquele mes tem

            if (countMoviesMonth>countMovie){//compara para ver se é maior do que já tinhamos

                topMonth=i;//se for grava no novo mes

                countMovie=countMoviesMonth;//e atualiza valiável countMovie
            }
        }
        return topMonth+":"+countMovie;
    }

    public static String getActorsByDirector(int numero,String nomeRealizador){

        HashMap<String,Node> countWorksDoneTogether= new HashMap<>();//conta os trabalhos feitos juntos
        StringBuilder resposta= new StringBuilder();//variavel para concatenar strings da resposta

        for (Movie movie: Main.dicionarioMovies.values()){//percorre todos os filmes

            if (movie.directors.containsKey(nomeRealizador)){//verifica se esse filme possui o diretor passado

                for (Actor actor: movie.actores.values()){//percorre dicionario de atores

                    if (countWorksDoneTogether.containsKey(actor.nome)){//se já existir um ator inserido

                        countWorksDoneTogether.get(actor.nome).count++;//acrescentar 1 ao count
                    }else {
                        //caso contrario colocar actor com contagem 1
                        countWorksDoneTogether.put(actor.nome,new Node(actor.nome,1));
                    }
                }
            }
        }

        //percorrer todos os atores que ja fizeram trabalho com o diretor passado
        for (Node node: countWorksDoneTogether.values()){

            if (node.count>=numero){//verifica se tem o minimo de realizações juntos
                //acrescenta o ator na resposta
                resposta.append(node.nome).append(":").append(node.count).append("\n");
            }
        }
        return resposta.toString();
    }

    public static String topMoviesWithGenderBias(int numero, int ano){

        ArrayList<Node> filmesDoAno = new ArrayList<>();//guarda o nome do filme/genero predominante/porcentagem
        StringBuilder resposta = new StringBuilder();//para concatenar resposta

        for (int id: Main.dicionarioAno.get(ano)) {//percorre os id's do ano passado

            Movie movie = Main.dicionarioMovies.get(id);//pega o filmes daquele ano

            if (movie.actores.size()>10){//valida se tem pelo menos 10 atores

                int[] porcentagem = FunctionsAuxiliary.porcentagem(movie.actores);//retorna porcentagem

                //acrecenta o nome do filme/genero predominante/porcentagem
                filmesDoAno.add(new Node(movie.titulo,(porcentagem[0]==1)?'F':'M',porcentagem[1]));
            }
        }

        //coloca em ordem de acordo com a porcentagem
        filmesDoAno.sort(Comparator.comparing((Node movie) -> movie.porcentagem));

        //concatena os top filmes com maior discrepancia percentual
        for (int i=filmesDoAno.size()-1; i>filmesDoAno.size()-numero-1; i--) {
            resposta.append(filmesDoAno.get(i).nome).append(":").append(filmesDoAno.get(i).genero)
                    .append(":").append(filmesDoAno.get(i).porcentagem).append("\n");
        }

        return resposta.toString();
    }

    public static String insertDirector(int idDirector, String nomeDirector,int idMovie){

        Movie movie = Main.dicionarioMovies.get(idMovie);

        if (movie==null){return "Erro";}//verifica se encontrou algum filme com o id passado

        if (!movie.directors.containsKey(nomeDirector)){//se o idDirector não existir
            //coloca novo diretor
            movie.directors.put(nomeDirector.trim(), new Director(idDirector,nomeDirector.trim()));
            return "OK";
        }
        return "Erro";
    }

    public static String getMovieWithActorContaining(String subEstring){

        //guarda a lista com os nomes dos filmes que possuirem actores com uma substring no nome
        ArrayList<String> listNameMovie = new ArrayList<>();
        StringBuilder resposta = new StringBuilder();

        for (Movie movie: Main.dicionarioMovies.values()){//percorre todos os filmes
            for (Actor actor: movie.actores.values()){//percorre todos os atores
                if (actor.nome.contains(subEstring)){//verifica se possui a substring
                    listNameMovie.add(movie.titulo);//se possuir acrecentar nome do filme no arraylist
                }
            }
        }

        Collections.sort(listNameMovie);//Ordena em ordem alfabética

        //concatena o resultado
        for (String titulo:listNameMovie){
            resposta.append(titulo).append("\n");
        }

        return resposta.toString().trim();
    }

    public static String top6DirectorsWithFamily(int anoInicial,int anoFinal){
        //guarda todos os nomes de diretores que fizeram trabaho com familiares
        //bem como a quantidade de vezes
        HashMap<String,Node> dicionarioDirectors = new HashMap<>();
        StringBuilder resposta = new StringBuilder();//para concatenar resposta

        for (int i=anoInicial; i<=anoFinal;i++){//percorre intervalo de anos passados

            ArrayList<Integer> idfilmesDoAno = Main.dicionarioAno.get(i);

            if (idfilmesDoAno!=null){//varifica se o ano teve filmes

                for (int idFilme : idfilmesDoAno){//percorre os filmes apenas daquele ano
                    //coloca no dicionarioDirectors se houver familiares
                    FunctionsAuxiliary.achievementsTogether(dicionarioDirectors,
                            Main.dicionarioMovies.get(idFilme).directors);
                }
            }
        }

        //passa para arraylist para ordenar
        ArrayList<Node> nomesNode = new ArrayList<>(dicionarioDirectors.values());

        //ordena de acordo com a quantidade de trabalhos feitos com familiares
        nomesNode.sort(Comparator.comparing((Node node) -> node.count));

        if (nomesNode.size()>=6){//verifica se possui pelo menos 6 directors com realização com familiares
            //concatena 6 maiores contadores de realizações juntas a familiares
            for (int i=nomesNode.size()-1; i>nomesNode.size()-7; i--){
                resposta.append(nomesNode.get(i).nome).append(":").append(nomesNode.get(i).count).append("\n");
            }
        }else{return "\n\n\n\n\n\n";}

        return resposta.toString();
    }
}
