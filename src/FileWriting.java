import java.beans.Transient;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriting {
   public File createFile() throws IOException {
        File newFile = new File("FileNameNew.txt");

        newFile.createNewFile();

        return newFile;
    }


    public void writingFileconPrintWriter() throws IOException {

        File file = createFile();

        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.print("String");
        printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
        printWriter.close();

    }

    public void writingFileBufferedWriter() throws IOException {
        String[] st = {"Juan", "Maria", "Valen", "Lilian", "Sofi"};
    
        File file = createFile();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

        for(String name : st){
          bufferedWriter.write("\n" +  name);
        }
        bufferedWriter.close();

    }

    public void writingFilecconFileOutputStream() throws IOException {

      String st = "Hello";
      File file = createFile();
      FileOutputStream outputStream = new FileOutputStream(file);
      byte[] strToBytes = st.getBytes();
      outputStream.write(strToBytes);
      outputStream.close();

    }

     public void writingFilecconDataOutputStream() throws IOException {

      String st = "Hello  World";
      File file = createFile();
      FileOutputStream outputStream = new FileOutputStream(file);
      DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(outputStream));
      outStream.writeUTF(st);
      outStream.close();

    }


    private void writeToPosition(String filename, int data, long position) throws IOException{
    
    
        RandomAccessFile writer = new RandomAccessFile(filename, "rw");
        writer.seek(position);
        writer.writeInt(data);
        writer.close();
        
    }


    private int readFromPosition(String filename, long position) throws IOException{
        int result = 0;
        RandomAccessFile reader = new RandomAccessFile(filename, "r");
        reader.seek(position);
        result = reader.readInt();
        reader.close();
        return result;
    }

    private void writeFileChannel() throws IOException{

       String fileName =  createFile().toString();
       RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
       FileChannel channel = stream.getChannel();
       String value = "Hello from FileChannel method";
       byte[] strToBytes = value.getBytes();
       ByteBuffer buffer = ByteBuffer.allocate(strToBytes.length);
       buffer.put(strToBytes);
       buffer.flip();
       channel.write(buffer);
       stream.close();
       channel.close();
    }

   private void writeUsingFilesClass() throws IOException{
    String str = "Hello using Files Class";
    File fileName = createFile();

    Path path = Paths.get(fileName.toString());
    byte[] strToBytes = str.getBytes();

    Files.write(path,strToBytes);
    
   
}


private void writeUsingTemporaryFile() throws IOException{
    String toWrite = "Hello";
    File tmpFile = File.createTempFile("test", ".tmp");
    FileWriter writer = new FileWriter(tmpFile);
    writer.write(toWrite);
    writer.close();
}


private void lockingBeforeWrite() throws IOException{

  

  RandomAccessFile stream = new RandomAccessFile(createFile().toString(),"rw");
  FileChannel channel = stream.getChannel();
  FileLock lock = null;

  try {
    lock = channel.tryLock();
    
  } catch (final OverlappingFileLockException e) {
     stream.close();
     channel.close();
  }

  stream.writeChars("test lock");
  lock.release();

  stream.close();
  channel.close();
}


    public static void main(String[] args) throws IOException {
        FileWriting fl = new FileWriting();

        fl.createFile();
        fl.writingFileBufferedWriter();
      

}
}