public class Polynomial{
    double[] coefficients;

    public Polynomial(){
        coefficients = new double[0];
    }

    public Polynomial(double[] coefficients_arg){
        coefficients = coefficients_arg;
    }

    public Polynomial add(Polynomial p){
        int len = Math.max(p.coefficients.length, coefficients.length);
        double[] arr = new double[len];
        Polynomial q = new Polynomial(arr);

        for (int i = 0; i < len; i++){
            if (i < p.coefficients.length) q.coefficients[i] += p.coefficients[i];
            if (i < coefficients.length) q.coefficients[i] += coefficients[i];
        }

        return q;
    }

    public double evaluate(double x){
        if (coefficients.length == 0) return 0.0;
        double d = coefficients[0];
        for(int i = 1; i < coefficients.length; i++){
            d += coefficients[i]*Math.pow(x, i);
        }
        return d;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}