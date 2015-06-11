package socket;
import java.io.*;

public class FilepathTest {
    public static void main(String[] args)
    {
        try 
        {
            String path = "C:/temp/client.txt";
            File abspath = new File(path).getAbsoluteFile();
            File canpath = new File(path).getCanonicalFile();
            File abspathpare = abspath.getParentFile();
            File canpathpare = canpath.getParentFile();
            System.out.println("調査対象: " + path);
            System.out.println("絶対パス: " + abspath);
            System.out.println("正規パス: " + canpath);
            System.out.println("絶対パスのファイルは存在するか: " + abspath.exists());
            System.out.println("絶対パスのファイル名: " + abspath.getName());
            System.out.println("絶対パスの親ディレクトリ: " + abspathpare);
            System.out.println("正規パスの親ディレクトリ: " + canpathpare);
            System.out.println("絶対パスの親ディレクトリの正規パス: " + abspathpare.getCanonicalPath());
        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
    }
}

