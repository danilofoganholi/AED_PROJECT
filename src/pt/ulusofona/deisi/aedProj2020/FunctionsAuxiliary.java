package pt.ulusofona.deisi.aedProj2020;
import java.util.HashMap;

public class FunctionsAuxiliary {

    public static String[] arrumaStringComVirgula(String[] dadosLine){
        if (!dadosLine[1].startsWith("\"")){//verificando se possui começa com aspas
            return dadosLine;
        }
        //concatenação e colocando a virgula novamente, pois como fiz o split na função askAmbrosio
        String[] stringCorrigida= {dadosLine[0],(dadosLine[1]+","+dadosLine[2]),dadosLine[3]};

        //retira aspas do começo e do fim do nome
        stringCorrigida[1] = stringCorrigida[1].substring(1,stringCorrigida[1].length()-1);
        return stringCorrigida;
    }

    public static void tiraAspas(String[] dadosLine){
        if (dadosLine[1].startsWith("\"")){//verificando se começa com aspas

            dadosLine[1]=dadosLine[1].substring(1,dadosLine[1].length()-1);//retira aspas do começo e do fim

            dadosLine[1]=dadosLine[1].replace("\"\"", "\"");//troca duas aspas por uma
        }
    }

    public static int[] porcentagem(HashMap<Integer,Actor> actores){
        int female=0,male=0;//count para guardar numero de ocorrências

        int[] resposta = new int[2];//primeiro int representa o sexo predominante, o segundo a porcentagem

        float valorUnitario = (actores.size()!=0)?100f/actores.size():0;//quanto vale cada unidade do count

        //conta quantas ocorrencias tem em cada sexo
        for (Actor actor: actores.values()){
            switch (actor.genero){
                case 'M': female++; break;
                case 'F': male++;
                default:break;
            }
        }

        female=Math.round(valorUnitario*female);//calcula a porcentagem de mulheres
        male=Math.round(valorUnitario*male);//calcula a porcentagem de homens

        if (female>=male){//se a porcentagem de mulher for maior que de homem
            resposta[1]=female;//retorna 0 no primeiro parametro (significa 'F') e a porcentagem no segundo
        }else{
            resposta[0]=1;//retorna 1 no primeiro parametro (significa 'M')
            resposta[1]=male;//e a porcentagem no segundo
        }
        return resposta;
    }

    public static void achievementsTogether(HashMap<String,Node> worksDirector,
                                            HashMap<String,Director> directors){
        for (Director director: directors.values()){//percorre os diretores

            String[] nome = director.nome.split(" ");//divide o nome no espaço

            String apelido = nome[nome.length-1];//pega apenas o apelido

            if (worksDirector.containsKey(director.nome)){//verifica se esse diretor já foi inserido anteriormente

                for (Director director1: directors.values()){//percorre diretores comparando um a um

                    String[] nameDirector = director1.nome.split(" ");//divide o nome no espaço

                    String apelido2 = nameDirector[nameDirector.length-1];//pega apenas o apelido

                    //verifica se não é ele mesmo e se não for verifica se os apelidos são iguais
                    if (!director1.nome.equals(director.nome) && apelido.equals(apelido2)){

                        worksDirector.get(director.nome).count++;//se for ++ no contador
                        break;
                    }
                }
            }else {
                for (Director director1: directors.values()){//percorre diretores comparando um a um

                    String[] nameDirector = director1.nome.split(" ");//divide o nome no espaço

                    String apelido2 = nameDirector[nameDirector.length-1];//pega apenas o apelido

                    //verifica se não é ele mesmo e se não for verifica se os apelidos são iguais
                    if (!director1.nome.equals(director.nome)  && apelido.equals(apelido2)){

                        //acrescenta novo diretor com contagem 1
                        worksDirector.put(director.nome,new Node(director.nome,1));
                        break;
                    }
                }
            }
        }
    }
}
