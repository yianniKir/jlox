import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import jdk.nashorn.internal.parser.Token;

public class Lox{
    public static void main(String[] args)throws IOException{
        if(args.length > 1){
            System.out.println("Usage: jlox [script]");
            System.exit(64); 
        }else if (args.length == 1) {
            runFile(args[0]);
        }else{
            REPL();
        }
    }

    private static void runFile(String path)throws IOException{
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
    }

    private static void REPL()throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for(;;){
            System.out.println("jlox>");
            String line = reader.readLine();
            if(line == null);
            run(line);
        }
    }

    private static void run(String src){
        Scanner scanner = new Scanner(src);
        List<Token> tokens = scanner.scanTokens();

        for(Token token : tokens){
            System.out.println(token);
        }
    }
}