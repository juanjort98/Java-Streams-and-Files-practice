import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReading {

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultingStringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = br.readLine()) != null) {
                resultingStringBuilder.append(line).append("\n");
            }
        }

        return resultingStringBuilder.toString();
    }

    // Reading from the CLassPath

    // using Standard Java

    public void readFromClassPath() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream InputStream = classLoader.getResourceAsStream("test.txt");
        String data = readFromInputStream(InputStream);

        System.out.println(data);
    }

    // Reading using BufferedReader

    public void readWithBufferedReader() throws IOException {

        String file = "FileNameNew.txt";

        List<String> listOfNames = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String currentLine;

        while ((currentLine = br.readLine()) != null) {
            listOfNames.add(currentLine);
        }
        br.close();

        for (String name : listOfNames) {
            System.out.println("Name: " + name);
        }

    }

    // Using NIO package

    public void readingWithFilesSmall() throws IOException {
        Path path = Paths.get("FileNameNew.txt");

        List<String> list = Files.readAllLines(path);

        for (String line : list) {

            int index = list.indexOf(line);
            System.out.println("line" + "[" + index + "]: " + line);
        }

    }

    public void readingWithFilesLarge() throws IOException {

        Path path = Paths.get("FileNameNew.txt");

        BufferedReader br = Files.newBufferedReader(path);

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();

    }

    public void readingWithFilesLines() throws IOException, URISyntaxException {

        ClassLoader cl = getClass().getClassLoader();

        Path path = Paths.get(cl.getResource("FileNameNew.txt").toURI());

        Stream<String> lines = Files.lines(path);

        String st = lines.collect(Collectors.joining("\n"));

        System.out.println(st);

        lines.close();

    }

    public void readingWithScanner() throws IOException{
       
        String file = "C:\\Users\\Juan Ramirez\\IdeaProjects\\Practicaa\\Practica\\src\\FileNameNew.txt";
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter("\n");

        

        while(scanner.hasNext()){
            System.out.println(scanner.next());
        }

        scanner.close();

    }

    public void readingWithDataInputStream() throws IOException{
        String file = "C:\\Users\\Juan Ramirez\\IdeaProjects\\Practicaa\\Practica\\src\\FileNameNew.txt";
        String result = null;

        DataInputStream reader = new DataInputStream(new FileInputStream(file));
        int nBytesToRead = reader.available();

        if(nBytesToRead > 0){
            byte[] bytes = new byte[nBytesToRead];
            reader.read(bytes);
            result = new String(bytes);
        }

         System.out.println(result);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        FileReading fr = new FileReading();

        fr.readingWithDataInputStream();
    }

}
