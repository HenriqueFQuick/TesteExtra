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

    public static void organizador(String nome_leitura, String nome_saida) throws Exception{
        String str = lerString(nome_leitura);
        System.out.print(str + "                      ");
        str = b(str);
        System.out.println(str);
        escrever(str, nome_saida);

    }//end organizador

    public static void escrever(String str, String nome_saida)throws Exception{
        BufferedWriter BW = new BufferedWriter(new FileWriter(nome_saida));
        Bw.write(str);
        BW.close();
    }//end escrever


    public static String lerString(String nome_leitura) throws Exception{
        BufferedReader BR = new BufferedReader(new FileReader(nome_leitura));
        String str = BR.readLine();
        String str2 = "";
        for(byte i = 0; i < str.length(); i++){
            if((((str.charAt(i) >= 65 && str.charAt(i) <= 90) && !(str.charAt(i+1) >= 97 && str.charAt(i+1) <= 122) ) || (str.charAt(i) >= 97 && str.charAt(i) <= 122)) && !(str.charAt(i+1) >= 48 && str.charAt(i+1) <= 57) || (str.charAt(i)== ')') && !(str.charAt(i+1) >= 48 && str.charAt(i+1) <= 57 ) || (str.charAt(i)== '}') && !(str.charAt(i+1) >= 48 && str.charAt(i+1) <= 57 )){
                str2 += str.charAt(i) + "1";
            }else str2 += str.charAt(i);
        }//end for
        return str2;
    }//end lerString

    public static int a(String str, String c){
        int a = 0;
        Pattern p = Pattern.compile(c);
        Matcher m = p.matcher(str);
        while(m.find()){
            a  = m.start();
        }
        return a;
    }

    public static String replace(String str, char a, char b){
        str = str.replace(""+a, ".");
        str = str.replace(""+b, ".");
        return str;
    }

    public static String b(String str)throws Exception{
        if(str.indexOf('{')>=0){      
            str = verificarCh(str);
        }else if(str.indexOf('[')>=0){
                str = verificarCo(str);
            }else if(str.indexOf('(')>=0){
                    str = verificar(str);
                }   
        return str;
    }

    public static String verificar(String str)throws Exception{
            String str2 = "";
            int a = a(str, "\\("), b = a(str, "\\)");
            int n = Integer.parseInt(""+str.charAt(b+1));
            for(int i = 0; i < str.length(); i++){
                if((str.charAt(i) >= 48 && str.charAt(i) <= 57) && (i > a) && (i < b)){
                    str2 += (Integer.parseInt(""+str.charAt(i)) * n);     
                }else{
                    str2 += str.charAt(i);
                }
            }
            //System.out.println(str2 + "       ");
            str2 = replace(str2, '(', ')');
            return str2;     
    }//end verificar

    public static String verificarCh(String str)throws Exception{
        str = verificarCo(str);
        String str2 = "";
        int a = a(str, "\\{"), b = a(str, "\\}");
        int n = Integer.parseInt(""+str.charAt(b+1));
        for(int i = 0; i < str.length(); i++){
            if((str.charAt(i) >= 48 && str.charAt(i) <= 57) && (i > a) && (i < b)){
                str2 += (Integer.parseInt(""+str.charAt(i)) * n);     
            }else str2 += str.charAt(i);
        }
        str2 = replace(str2, '{', '}');
        return str2;     
    }//end verificar

    public static String verificarCo(String str)throws Exception{
        str = verificar(str);
        String str2 = "";
        int a = a(str, "\\["), b = a(str, "\\]");
        int n = Integer.parseInt(""+str.charAt(b+1));
        for(int i = 0; i < str.length(); i++){
            if((str.charAt(i) >= 48 && str.charAt(i) <= 57) && (i > a) && (i < b)){
                if((str.charAt(i+1) >= 48) && (str.charAt(i+1) <= 57)){
                    str2 += ((Integer.parseInt(""+str.charAt(i)) * 10 * n) + (Integer.parseInt(""+str.charAt(i+1)) * n));
                    i++;
                }else str2 += (Integer.parseInt(""+str.charAt(i)) * n); 
            }else str2 += str.charAt(i);
        }
        str2 = replace(str2, '[', ']');
        return str2;     
    }//end verificar

}//end testeExtra