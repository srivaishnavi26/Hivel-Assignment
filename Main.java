import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        try {
            JSONObject data = new JSONObject(new JSONTokener(new FileReader("input.json")));

            int n = data.getInt("n");
            int k = data.getInt("k");

            JSONArray arr = data.getJSONArray("arr");

            int[] X = new int[k];
            BigInteger[] Y = new BigInteger[k];

            for (int i = 0; i < k; i++) {
                JSONObject obj = arr.getJSONObject(i);

                int x = obj.getInt("x");
                int base = obj.getInt("base");
                String valueStr = obj.getString("value");

                BigInteger decoded = new BigInteger(valueStr, base);

                X[i] = x;
                Y[i] = decoded;

                System.out.println("x=" + x + ", base=" + base + ", y=" + decoded);
            }

            BigInteger C = BigInteger.ZERO;

            for (int i = 0; i < k; i++) {
                BigInteger term = Y[i];
                BigInteger num = BigInteger.ONE;
                BigInteger den = BigInteger.ONE;

                for (int j = 0; j < k; j++) {
                    if (i != j) {
                        num = num.multiply(BigInteger.valueOf(-X[j]));
                        den = den.multiply(BigInteger.valueOf(X[i] - X[j]));
                    }
                }
                term = term.multiply(num).divide(den);
                C = C.add(term);
            }

            System.out.println("\nComputed Constant C =");
            System.out.println(C);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
