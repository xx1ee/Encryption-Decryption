package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class AlgFactory {

    public static Alg Choice(String alg, String cond, String msg, int key, String path) {
        if (cond.equals("enc")) {
            Alg enc = new Enc(alg, msg, key, path);
            enc.getMsg();
            return enc;
        } else {
            Alg dec = new Dec(alg, msg, key, path);
            dec.getMsg();
            return dec;
        }
    }
}

abstract class Alg {
    String alg;
    String msg;
    String path;
    int key;
    public Alg(String alg, String msg, int key, String path) {
        this.alg = alg;
        this.msg = msg;
        this.key = key;
        this.path = path;
    }
    abstract void getMsg();
    public static void FileOut(String pathToFileOut, String message) {
        File fileOut = new File(pathToFileOut);
        try (FileWriter writer = new FileWriter(fileOut)) {
            System.out.println(message);
            writer.write(message);
        } catch (IOException e) {
            System.out.printf("Error, an exception occurred %s", e.getMessage());
        }
    }
}

class Enc extends Alg {

    public Enc(String alg, String msg, int key, String path) {
        super(alg, msg, key, path);
    }
    @Override
    public void getMsg() {
        StringBuilder message = new StringBuilder(msg);
        if (alg.equals("unicode")) {
            for (int i = 0 ; i < message.length() ; i++)
            {
                int ascii = message.charAt(i);
                char ch = ' ';
                ascii+=key;
                ch = (char) (ascii);
                message.setCharAt(i, ch);
            }
        } else {
            for (int i = 0 ; i < message.length() ; i++)
            {
                int ascii = message.charAt(i);
                if (ascii >= 97 && ascii <= 122)
                {
                    char ch = ' ';
                    ascii+=key;
                    if (ascii > 122) {
                        ch = (char) (ascii - 122 + 96);
                    } else {
                        ch = (char) (ascii);
                    }
                    message.setCharAt(i, ch);
                }
            }
        }
        if (!path.equals(""))
        {
            super.FileOut(path, message.toString());
        } else System.out.println(message);
    }
}

class Dec extends Alg {

    public Dec(String alg, String msg, int key, String path) {
        super(alg, msg, key, path);
    }
    @Override
    public void getMsg() {
        StringBuilder message = new StringBuilder(msg);
        if (alg.equals("unicode")) {
            for (int i = 0 ; i < message.length() ; i++)
            {
                int ascii = message.charAt(i);
                char ch = ' ';
                ascii-=key;
                ch = (char) (ascii);
                message.setCharAt(i, ch);
            }
        } else {
            for (int i = 0 ; i < message.length() ; i++)
            {
                int ascii = message.charAt(i);
                if (ascii >= 97 && ascii <= 122)
                {
                    char ch = ' ';
                    ascii-=key;
                    if (ascii < 97) {
                        ch = (char) (122 + ascii - 96);
                    } else {
                        ch = (char) (ascii);
                    }
                    message.setCharAt(i, ch);
                }
            }
        }
        if (!path.equals(""))
        {
            super.FileOut(path, message.toString());
        } else System.out.println(message);
    }
}
public class Main {
    public static String FileIn(String pathToFileIn, String msg) {
        File fileIn = new File(pathToFileIn);
        try (Scanner scanner = new Scanner(fileIn)) {
            while (scanner.hasNext()) {
                msg = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, no file found: " + pathToFileIn);
        }
        return msg;
    }
    public static void main(String[] args) {
        String alg = "shift";
        String cond = "enc";
        String msg = "";
        String pathToFileIn = "";
        String pathToFileOut = "";
        int key = 0;
        for (int i = 0; i < args.length; i++){
            if (args[i].equals("-mode")) {
                cond = args[i+1];
            } else if (args[i].equals("-key")) {
                key = Integer.parseInt(args[i+1]);
            } else if (args[i].equals("-data")) {
                msg = args[i+1];
            } else if (args[i].equals("-alg")) {
                alg = args[i + 1];
            } else if (args[i].equals("-in"))
            {
                pathToFileIn = args[i+1];
                msg = FileIn(pathToFileIn, msg);
            } else if (args[i].equals("-out"))
            {
                pathToFileOut = args[i+1];
            }
        }
        AlgFactory fctry = new AlgFactory();
        fctry.Choice(alg, cond, msg, key, pathToFileOut);
    }
}
