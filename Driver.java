import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {

    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double[] c1 = { 6, 5 };
        int[] e1 = {0, 3};
        Polynomial p1 = new Polynomial(c1, e1);
        double[] c2 = { -2, -9 };
        int[] e2 = {1, 4};
        Polynomial p2 = new Polynomial(c2, e2);

        //multiply test
        /* 
        double[] c3 = { 4, 4, 3, 5, 4 };
        int[] e3 = {0, 1, 2, 3, 7};
        Polynomial p3 = new Polynomial(c3, e3);
        double[] c4 = { -3, -2, 3, 5, 8, -9 };
        int[] e4 = {0, 2, 1, 3, 5, 9};
        Polynomial p4 = new Polynomial(c4, e4);
        Polynomial q = p3.multiply(p4);
        for (int i = 0; i < q.coefficients.length; i++){
            System.out.println("coefficients: " + q.coefficients[i] + "exponents: " + q.exponents[i]);
        }
        */
        //

        //add test
        double[] c3 = { 4, 4, 3, 5, 4 };
        int[] e3 = {0, 1, 2, 3, 7};
        Polynomial p3 = new Polynomial(c3, e3);
        double[] c4 = { -4, -4, -3, -5, -4 };
        int[] e4 = {0, 1, 2, 3, 7};
        Polynomial p4 = new Polynomial(c4, e4);
        Polynomial q = p3.add(p4);
        for (int i = 0; i < q.coefficients.length; i++){
            System.out.println("coefficients: " + q.coefficients[i] + "exponents: " + q.exponents[i]);
        }

        // file test
        /* 
        File txt = new File("file.txt");
        try {
            Polynomial r = new Polynomial(txt);
            for (int i = 0; i < r.coefficients.length; i++){
                System.out.println("coefficients: " + r.coefficients[i] + "exponents: " + r.exponents[i]);
            }
            try {
                r.saveToFile("save.txt");
            } catch (IOException e) {
            }
        } catch (FileNotFoundException e) {
        }
        */
        Polynomial s = p1.add(p2);
        for (int i = 0; i < s.coefficients.length; i++){
            System.out.println("coefficients: " + s.coefficients[i] + "exponents: " + s.exponents[i]);
        }
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
    }
}
