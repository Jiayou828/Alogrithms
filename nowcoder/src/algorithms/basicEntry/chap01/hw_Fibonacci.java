package algorithms.basicEntry.chap01;

public class hw_Fibonacci {
    public static void main(String[] args) {
        System.out.println(Fibonacci(23));
    }
    public static int Fibonacci(int n) {
        if(n == 0)
            return 0;
        if(n == 1 || n == 2)
            return 1;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }
}
