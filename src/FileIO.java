import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
    public static void main(String[] args) {


        String[] arr = {"juan", "valen", "lilian"};
        try {
            BufferedWriter writer = new BufferedWriter( new FileWriter("output.txt"));
            writer.write("writing");
            writer.write("\njeje");
            
            for(String name: arr){
                writer.write("\n" + name);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
            try {
                BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
                String line;
                while((line = reader.readLine()) != null)
                {System.out.println(line);}
                reader.close();
            } catch (IOException e) {
               
                e.printStackTrace();
            }
        
    }

    
}