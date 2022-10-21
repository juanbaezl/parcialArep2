package co.edu.escuelaing;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String... args){
        port(getPort());
        staticFiles.location("/public");
        get("collatz", (req,res) -> { 
            res.type("application/json");
            return secuencia(Integer.parseInt(req.queryParams("value")));
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

    private static String secuencia(Integer num){
        String res = "{\"operation\":\"collatzsequence\",\n"+
                        "\"input\":\"" + num + "\",\n" +
                        "\"output\":\"";
        res += num;
        while (num != 1) {
            if (num % 2 == 0){
                num = num / 2;
            } else {
                num = (num * 3) + 1;
            }
            res += " -> " + num;
        }    
        res += "\"}";
        return res;
    }
}
