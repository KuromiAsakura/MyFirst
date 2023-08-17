import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class NewSolve {
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        String s; s = in.nextLine(); int toi = 1;
        in.close();
        String [] h = s.split(" ");
        if(h.length != 3)
            throw new IOException("введено неподходящее количество чисел/знаков");
        else{
            char[] a = h[0].toCharArray(), b = h[2].toCharArray();
            int x = V(a), y = V(b);
            if(x != y) throw new IOException("неподходящие числа");
            else if (x == 0) {
                int aq = Lock(a), bq = Lock(b);
                if(aq > 10 || bq > 10)
                    throw new IOException("числа не входят в рамки от 1 до 10");
                switch (h[1]) {
                    case "+" ->{
                        aq += bq; break;}
                    case "-" ->{
                        aq -= bq; break;}
                    case "*" ->{
                        aq *= bq; break;}
                    case "/" ->{
                        aq /= bq; break;}
                    default ->{
                        throw new IOException("неизвестный арифметический знак");
                    }
                }
                if (aq < 1)
                    throw new IOException("результат меньше 1 -> нет римского числа");
                else unLock(aq);
            }
            else{
                x = se(a); y = se(b);
                if(x != 0 || y != 0) throw new IOException("неподходящие числа");
                else{
                    int aq = 0, bq = 0;
                    for(char i: a) aq = aq * 10 +(int)(i - '0');
                    for(char i: b) bq = bq * 10 +(int)(i - '0');
                    if(aq > 10 || aq < 1 || bq > 10 || bq < 1)
                        throw new IOException("числа не входят в рамки от 1 до 10");
                    switch (h[1]) {
                        case "+" ->{
                            aq += bq; System.out.println(aq); break;}
                        case "-" ->{
                            aq -= bq; System.out.println(aq); break;}
                        case "*" ->{
                            aq *= bq; System.out.println(aq); break;}
                        case "/" ->{
                            aq /= bq; System.out.println(aq); break;}
                        default ->{
                            throw new IOException("неизвестный арифметический знак");
                        }
                    }
                }// вывод арабских чиселок
            }
        }
    }
    static int V(char[] a){
        char[] A = {'M','D','C','L','X','V','I'};
        int ind = 0;
        for(char x: a){
            int u = 0;
            for(char i: A){
                if(x == i) u=1;
            }
            if(u != 1) {ind = 1;break;}
        }
        return ind;
    }//проверка на то, все ли символы числа римские
    static int se(char[] a){
        int ind = 0;
        for(char x: a){
            int u = 0;
            for(char i = '0'; i <= '9'; i++ ){
                if(x == i) u=1;
            }
            if(u != 1) {ind = 1;break;}
        }
        return ind;
    }////проверка на то, все ли символы числа арабские
    static int Lock(char[] a){
        int x = 0;
        char[] b = {'M','D','C','L','X','V','I'};
        int[] c = {1000, 500, 100, 50, 10, 5, 1};
        int k = 0;
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < b.length; j++ ){
                if(a[i] == b[j]){
                     x += c[j];
                     if(k > 0) if (k < c[j]) x -= 2*k;
                     k = c[j];
                }
            }
        }
        return x;
    } // перевод из римских в арабские
    static void unLock(int a){
        char[] b = {'M','D','C','L','X','V','I'};
        int[] c = {1000, 500, 100, 50, 10, 5, 1};
        char [] s = new char[20]; int i = 0, o = 0, ig = 0;
        while(a > 0){
            if (a - c[i] >= 0){
                a -= c[i];
                s[o] = b[i];
                if(ig != 0) if(s[o] != s[o-1]) ig = 0;
                if(i > 0) ig += 1;
                if(ig == 4){
                    if(o > 3){
                        if(s[o-4] == b[i-1]){
                            s[o-4] = b[i];
                            s[o-3] = b[i-2];
                            o -= 3;
                        }
                        else{
                            s[o-2] = b[i-1];
                            o -= 2;
                        }
                    }
                    else {
                        s[o-2] = b[i-1];
                        o -= 2;
                    }
                }
                o++;
            }
            else i++;
        }
        String jeees = "";
        for(int j = 0; j < o; j++) jeees = jeees + String.valueOf(s[j]);
        System.out.println(jeees);
    }//перевод из арабских в римские(+вывод в консоль)
}