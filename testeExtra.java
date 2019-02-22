/*
Autor: Henrique Fernandes Viana Mendes
*/
import java.util.regex.*;
import java.io.*;
public class testeExtra{

    public static void main(String[] args){
        try{
            chamada();
        }catch(Exception e){
            e.printStackTrace();
        }//end try/catch

    }//end main

    
    public static void chamada() throws Exception{
        organizador("teste1.in","teste1.out");
        organizador("teste2.in","teste2.out");
        organizador("teste3.in","teste3.out");
        organizador("teste4.in","teste4.out");
        organizador("teste5.in","teste5.out");
        organizador("teste6.in","teste6.out");
        organizador("teste7.in","teste7.out");
        organizador("teste8.in","teste8.out");

    }//end chamada


    //metodo para a chamada de metodos 
    public static void organizador(String nome_leitura, String nome_saida) throws Exception{
        String str = lerString(nome_leitura);                                                 //metodo para ler a string(formula) dada de uma arquivo
        str = redirecionador(str);                                                            //metodo para redirecionar a partir da existencia ou nao de (), [] ou {}
        str = mudarString(str);
        escrever(str, nome_saida);

    }//end organizador


    public static String mudarString(String str){
        String[] chvetor = new String[str.length()];
        String elemento = "", numero = "";
        int[] a  = new int[str.length()];
        for(int i = 0; i < str.length(); i++){
            elemento = "";
            numero = "";
                while(!(str.charAt(i) >= 48 && str.charAt(i) <= 57)){
                    elemento += str.charAt(i);
                    i++;
                }//end while
                while(i < str.length() && str.charAt(i) >= 48 && str.charAt(i) <= 57){
                    numero += str.charAt(i);
                    i++;
                }//end while
                int aux = Integer.parseInt(numero);

                boolean achou = false;
                int j = 0;
                for(j = 0; j < chvetor.length && chvetor[j] != null; j++){
                    if(chvetor[j].equals(elemento)){
                        achou = true;
                        break;
                    }//end if
                }//end for

                if(!achou) chvetor[j] = elemento;

                a[j] += aux;

                i--;
        }//end for

        String ret = "";
        for(int j = 0; j < chvetor.length && chvetor[j] != null; j++){
            ret += chvetor[j];
            ret += ""+a[j];
        }//end for

        return ret;
    }//end mudarString

    
    public static String formatar(String str){
        String str2 = "";
        for(int i = 0; i < str.length(); i++){
            if((str.charAt(i) >= 65 && str.charAt(i) <= 90) ){
                if((i+1)< str.length() && (str.charAt(i+1) >= 97 && str.charAt(i+1) <= 122)){
                    str2 += str.charAt(i) +""+ str.charAt(i+1);
                    i++;
                }else{ str2 += str.charAt(i); }
            }else if((i+1)< str.length() && (str.charAt(i+1) >= 48 && str.charAt(i+1) <= 57)){
                    str2 += ": " + str.charAt(i) +""+ str.charAt(i+1) + ", ";
                    i++;
                }else{  str2 += (": " + str.charAt(i) + ", ");}
        }//end for
        return stringCorreta(str2);
    }//end formatar

    public static String stringCorreta(String str){
        String str2 = "";
        for(int i = 0; i < str.length()-2; i++){
            str2 += str.charAt(i);
        }//end for
    return str2;
    }//end stringCorreta

    //método para escrever no arquivo a string ja formatada
    public static void escrever(String str, String nome_saida)throws Exception{
        BufferedWriter BW = new BufferedWriter(new FileWriter(nome_saida));                           //abrir/criar arquivo
        str = formatar(str);
        BW.write(str);                                                                                //escrever a string no arquivo 
        BW.close();                                                                                   //fechar arquivo
    }//end escrever


    //metodo para ler a formula de um arquivo e colocar o numero 1 depois de moleculas, chaves e parentesis, sem numero posterior
    public static String lerString(String nome_leitura) throws Exception{
        BufferedReader BR = new BufferedReader(new FileReader(nome_leitura));                         //abrir arquivo
        String str        = BR.readLine();                                                            //ler a formula
        String elemento       = "";
        for(byte i = 0; i < str.length(); i++){
            if((((str.charAt(i) >= 65 && str.charAt(i) <= 90) && !(str.charAt(i+1) >= 97 && str.charAt(i+1) <= 122) ) || (str.charAt(i) >= 97 && str.charAt(i) <= 122)) && !(str.charAt(i+1) >= 48 && str.charAt(i+1) <= 57) || (str.charAt(i)== ')') && !(str.charAt(i+1) >= 48 && str.charAt(i+1) <= 57 ) || (str.charAt(i)== '}') && !(str.charAt(i+1) >= 48 && str.charAt(i+1) <= 57 )){
                elemento += str.charAt(i) + "1";
            }else elemento += str.charAt(i);
        }//end for
        BR.close();
        return elemento;                                                                               //retornar a string ja formatada com todos os valores 1's
    }//end lerString

    
    //metodo para retornar a posicao na string que contem determinado caracter
    public static int posPattern(String str, String c){
        int a     = 0;
        Pattern p = Pattern.compile(c);
        Matcher m = p.matcher(str);
        while(m.find()){
            a  = m.start();
        }//end while
        return a;
    }//end posPattern


    //metodo para trocar determinado caracter( no caso, (), [] e {}) por . apos a utilizacao dos mesmos
    public static String replace(String str, char a, char b){
        str = str.replace(""+a, "");
        str = str.replace(""+b, "");
        return str;
    }//end replace


    //metodo para redirecionar a partir da existencia ou nao de (), [] ou {}
    public static String redirecionador(String str)throws Exception{
        if(str.indexOf('{')>=0){      
            str = multiplicaChaves(str);
        }else if(str.indexOf('[')>=0){
                str = multiplicaColchetes(str);
              }else if(str.indexOf('(')>=0){
                    str = multiplicaParentesis(str);
                    }//end if
        return str;
    }//end redirecionador


    //metodo para realizar a multiplicacao dentro de parentesis
    public static String multiplicaParentesis(String str)throws Exception{
            String elemento = "", str3 = "";
            int a = posPattern(str, "\\("), b = posPattern(str, "\\)");
            int n = Integer.parseInt(""+str.charAt(b+1));
            for(int i = 0; i < str.length(); i++){
                if(!(i == (b+1))){
                    str3 += str.charAt(i);
                }//end if
            }//end for
            for(int i = 0; i < str3.length(); i++){
                if((str3.charAt(i) >= 48 && str3.charAt(i) <= 57) && (i > a) && (i < b)){
                    elemento += (Integer.parseInt(""+str3.charAt(i)) * n);  
                }else{
                    elemento += str3.charAt(i);
                }//end else
            }//end for
            
            return replace(elemento, '(', ')');     
    }//end verificar


    //metodo para realizar a multiplicacao dentro de chaves
    public static String multiplicaChaves(String str)throws Exception{
        str = multiplicaColchetes(str);
        String elemento = "";
        int a = posPattern(str, "\\{"), b = posPattern(str, "\\}");
        int n = Integer.parseInt(""+str.charAt(b+1));
        for(int i = 0; i < str.length(); i++){
            if((str.charAt(i) >= 48 && str.charAt(i) <= 57) && (i > a) && (i < b)){
                elemento += (Integer.parseInt(""+str.charAt(i)) * n);     
            }else elemento += str.charAt(i);
        }//end for
        String str3 = "";
        for(int i = 0; i < elemento.length(); i++){
                if(!(i == (b+1))){
                    str3 += elemento.charAt(i);
                }//end if
        }//end for
            str3 = replace(str3, '{', '}');
            return str3;     
    }//end verificar


    //metodo para realizar a multiplicacao dentro de colchetes
    public static String multiplicaColchetes(String str)throws Exception{
        str = multiplicaParentesis(str);
        String elemento = "", str3 = "";
        int a = posPattern(str, "\\["), b = posPattern(str, "\\]");
        int n = Integer.parseInt(""+str.charAt(b+1));
        for(int i = 0; i < str.length(); i++){
            if(!(i == (b+1))){
                str3 += str.charAt(i);
            }//end if
        }//end for
        for(int i = 0; i < str3.length(); i++){
            if((str3.charAt(i) >= 48 && str3.charAt(i) <= 57) && (i > a) && (i < b)){
                if((str3.charAt(i+1) >= 48) && (str3.charAt(i+1) <= 57)){
                    elemento += ((Integer.parseInt(""+str3.charAt(i)) * 10 * n) + (Integer.parseInt(""+str3.charAt(i+1)) * n));
                    i++;
                }else elemento += (Integer.parseInt(""+str3.charAt(i)) * n); 
            }else elemento += str3.charAt(i);
        }//end for
            return replace(elemento, '[', ']');   
    }//end verificar

}//end testeExtra