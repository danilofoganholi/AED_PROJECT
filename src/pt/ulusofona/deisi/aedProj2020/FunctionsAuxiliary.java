package pt.ulusofona.deisi.aedProj2020;
import java.util.HashMap;

public class FunctionsAuxiliary {

    public static String[] arrumaStringComVirgula(String[] dadosLine){
        if (!dadosLine[1].startsWith("\"")){//verificando se possui começa com aspas
            return dadosLine;
        }
        String[] stringCorrigida= new String[3];
        stringCorrigida[0]= dadosLine[0];
        stringCorrigida[1]= dadosLine[1]+","+dadosLine[2];
        stringCorrigida[2]= dadosLine[3];
        stringCorrigida[1] = stringCorrigida[1].substring(1,stringCorrigida[1].length()-1);
        return stringCorrigida;
    }

    public static String[] tiraAspas(String[] dadosLine){
        if (dadosLine[1].startsWith("\"")){//verificando se possui começa com aspas
            dadosLine[1]=dadosLine[1].substring(1,dadosLine[1].length()-1);//retira aspas do começo e do fim
            dadosLine[1]=dadosLine[1].replace("\"\"", "\"");//troca duas aspas por uma
        }
        return dadosLine;
    }

    public static int[] porcentagem(HashMap<Integer,Actor> actores){
        int countM=0, countF=0;
        int[] resposta = new int[2];
        float valorUnitario = (actores.size()!=0)?100f/actores.size():0;
        int female;
        int male;

        for (Actor actor: actores.values()){
            switch (actor.genero){
                case 'M': countM++; break;
                case 'F': countF++;
                default:break;
            }
        }

        female=Math.round(valorUnitario*countF);
        male=Math.round(valorUnitario*countM);

        if (female>=male){
            resposta[1]=female;
        }else{
            resposta[0]=1;
            resposta[1]=male;
        }

        return resposta;
    }

    public static boolean haveDirector(String name, HashMap<Integer,Director> directors){
        for (Director director : directors.values()){
            if (director.nome.equals(name)){return true;}
        }
        return false;
    }
}
