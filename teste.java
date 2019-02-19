import java.util.regex.*;
public class teste{

    public static void main(String[] args){
        String str = "H2(SO)4";
        String str2 = "";
        for(byte i = 0; i < str.length(); i++){
            if((str.charAt(i) >= 65 && str.charAt(i) <= 90) && !(str.charAt(i+1) >= 48 && str.charAt(i+1) <= 57)){
                str2 += str.charAt(i) + "1";
            }else str2 += str.charAt(i);
        }
        System.out.println(str + " ... " + str2);

        String str3 = ler(str2);
        System.out.println(str3);
    }

    public static String ler(String str){
        String pattern = "[(]";
        int a = 0, b = 0, c = 0;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        while(m.find()){
            a = m.start();       
        }
        System.out.println(str.charAt(a));
        pattern = "[)]";
        p = Pattern.compile(pattern);
        m = p.matcher(str);
        while(m.find()){
            b = m.start();       
        }
        System.out.println(str.charAt(b));
       // int n = Integer.parseInt(""+str.charAt(b+1));
        //System.out.println(n);
        for(int i = a; i < b; i++){
            if(str.charAt(i) >= 48 && str.charAt(i) <= 57){
                //c = Integer.parseInt(""+str.charAt(i)) * n;
            }
        }
        return str;

    }

}