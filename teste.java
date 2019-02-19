import java.util.regex.*;
public class teste{

    public static void main(String[] args){
        String str = "Na{[Au(CN)2]3}4";
        String str2 = "";
        String str3 = "";
        for(byte i = 0; i < str.length(); i++){
            if((str.charAt(i) >= 65 && str.charAt(i) <= 90) && !(str.charAt(i+1) >= 48 && str.charAt(i+1) <= 57)){
                str2 += str.charAt(i) + "1";
            }else str2 += str.charAt(i);
        }
        System.out.println(str + " ... " + str2);
        str3 = lerP(str2);
        System.out.println(str3);
        str3 = lerCo(str3);
        System.out.println(str3);
        str3 = lerCh(str3);
        System.out.println(str3);
    }
    
    public static String lerCh(String str){
        String pattern = "\\{", str2 = "";
        int a = 0, b = 0, c = 0;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        while(m.find()){
            a = m.start();       
        }
        pattern = "\\}";
        p = Pattern.compile(pattern);
        m = p.matcher(str);
        while(m.find()){
            b = m.start();       
        }
        int n = Integer.parseInt(""+str.charAt(b+1));
        for(int i = 0; i <= a; i++){
         str2 += str.charAt(i);        
        }
        for(int i = a+1; i < b; i++){
            if(str.charAt(i) >= 48 && str.charAt(i) <= 57){
                str2 += (Integer.parseInt(""+str.charAt(i)) * n);
                
            }else str2 += str.charAt(i);
        }
        for(int i = b; i < str.length(); i++){
             str2 += str.charAt(i);        
        }
        str2 = str2.replace("(", ".");
        str2 = str2.replace(")", ".");
        return str2;
    }
    
    public static String lerCo(String str){
        String pattern = "\\[", str2 = "";
        int a = 0, b = 0, c = 0;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        while(m.find()){
            a = m.start();       
        }
        pattern = "\\]";
        p = Pattern.compile(pattern);
        m = p.matcher(str);
        while(m.find()){
            b = m.start();       
        }
        int n = Integer.parseInt(""+str.charAt(b+1));
        for(int i = 0; i <= a; i++){
         str2 += str.charAt(i);        
        }
        for(int i = a+1; i < b; i++){
            if(str.charAt(i) >= 48 && str.charAt(i) <= 57){
                str2 += (Integer.parseInt(""+str.charAt(i)) * n);
                
            }else str2 += str.charAt(i);
        }
        for(int i = b; i < str.length(); i++){
             str2 += str.charAt(i);        
        }
        str2 = str2.replace("(", ".");
        str2 = str2.replace(")", ".");
        return str2;
    }

    public static String lerP(String str){
        String pattern = "[(]", str2 = "";
        int a = 0, b = 0, c = 0;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        while(m.find()){
            a = m.start();       
        }
        pattern = "[)]";
        p = Pattern.compile(pattern);
        m = p.matcher(str);
        while(m.find()){
            b = m.start();       
        }
        int n = Integer.parseInt(""+str.charAt(b+1));
        for(int i = 0; i <= a; i++){
         str2 += str.charAt(i);        
        }
        for(int i = a+1; i < b; i++){
            if(str.charAt(i) >= 48 && str.charAt(i) <= 57){
                str2 += (Integer.parseInt(""+str.charAt(i)) * n);
                
            }else str2 += str.charAt(i);
        }
        for(int i = b; i < str.length(); i++){
             str2 += str.charAt(i);        
        }
        str2 = str2.replace("(", ".");
        str2 = str2.replace(")", ".");
        return str2;

    }

}