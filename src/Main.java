import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static int lines = 0;
    public static void main(String args[]) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new FileReader("./names.txt"));
        BufferedReader brlenght = new BufferedReader(new FileReader("./names.txt"));
        int lenght = 0;
        while (brlenght.readLine() != null) {
            lenght++;
        }
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        brlenght.close();

        String line;
        int currentLine = 1;
        while((line = br.readLine()) != null){
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + line);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseMessage().equals("No Content")) {
                System.out.println("Name " + line + " Found in line " + currentLine + "/" + lenght);
                writer.println(line);
                writer.flush();
            } else {
                System.out.println("Line " + currentLine + "/" + lenght);
            }
            Thread.sleep(1005);
            currentLine++;
        }
        br.close();
        writer.close();
        System.exit(1);
    }
}