import java.io.*;
import java.util.Scanner;

public class Polynomial{
    double[] coefficients;
    int[] exponents;

    public Polynomial(){
        coefficients = new double[0];
        exponents = new int[0];
    }

    public Polynomial(double[] coefficients_arg, int[] exponents_arg){
        coefficients = coefficients_arg;
        exponents = exponents_arg;
    }

    public Polynomial(File f) throws FileNotFoundException{
        Scanner input = new Scanner(f);
        String s = input.next();
        s = s.replace("-", "+-");
        String[] sp = s.split("\\+");
        int len = sp.length;

        coefficients = new double[len];
        exponents = new int[len];

        int count = 0;
        for (String str : sp){
            String[] term = str.split("x");
            coefficients[count] = Double.parseDouble(term[0]);
            if (term.length != 1){
                exponents[count] = Integer.parseInt(term[1]);
            } 
            count++;
        }

        input.close();
    }

    public Polynomial add(Polynomial p){
        int len = Math.max(p.coefficients.length, coefficients.length);
        double[] arr = new double[len];
        int[] arr2 = new int[len];
        Polynomial q = new Polynomial(arr, arr2);

        for (int i = 0; i < len; i++){
            if (i < p.coefficients.length) {
                q.coefficients[i] += p.coefficients[i];
                q.exponents[i] += p.exponents[i];
            }
            if (i < coefficients.length) {
                q.coefficients[i] += coefficients[i];
                q.exponents[i] += exponents[i];
            }
        }

        return q;
    }

    public double evaluate(double x){
        if (coefficients.length == 0) return 0.0;
        double d = 0;
        if (x == 0){
            for (int i = 0; i < exponents.length; i++){
                if (exponents[i] == 0){
                    return coefficients[i];
                }
            }
            return 0;
        }
        for(int i = 0; i < coefficients.length; i++){
            d += coefficients[i]*Math.pow(x, i);
        }
        return d;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial p){
        int len = p.coefficients.length * coefficients.length;
        double[] arr = new double[len];
        int[] arr2 = new int[len];

        int count = 0;
        for(int i = 0; i < p.coefficients.length; i++){
            if (p.coefficients[i] == 0) continue;
            for(int j = 0; j < coefficients.length; j++){
                if (coefficients[j] == 0) continue;
                int deg = p.exponents[i] + exponents[j];
                boolean check = true;
                for (int k = 0; k < len; k++){
                    if (arr2[k] == deg){
                        arr[k] += p.coefficients[i] * coefficients[j];
                        check = false;
                    }
                }
                if (check) {
                    arr[count] = p.coefficients[i] * coefficients[j];
                    arr2[count] = p.exponents[i] + exponents[j];
                }
                count++;
            }
        }

        count = 0;
        for (int i = 0; i < len; i++){
            if (arr[i] != 0 && arr2[i] != 0){
                count++;
            }
        }
        int q_len = count+1;
        double[] q_arr = new double[q_len];
        int[] q_arr2 = new int[q_len];

        for (int i = 0; i < len; i++){
            if (arr2[i] == 0){
                if (arr[i] != 0){
                    q_arr[q_len-1] = arr[i];
                }
                break;
            }
        }
        
        count = 0;
        for (int i = 0; i < len; i++){
            if (arr[i] != 0 && arr2[i] != 0){
                q_arr[count] = arr[i];
                q_arr2[count] = arr2[i];
                count++;
            }
        }

        Polynomial q = new Polynomial(q_arr, q_arr2);

        return q;
    }

    public void saveToFile(String fileName) throws IOException{
        FileWriter fw = new FileWriter(fileName);
        String s = "";
        for(int i = 0; i < coefficients.length; i++){
            s += coefficients[i];
            if (exponents[i] != 0){
                s += "x";
                s += exponents[i];
                s += "+";
            }
        }
        
        s = s.replace("+-", "-");
        s=s.substring(0, s.length()-1);
        fw.write(s);
        fw.close();
    }
}