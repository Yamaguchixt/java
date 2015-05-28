package socket;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;


public class Server {
    
    private static final String DOCUMENT_ROOT = "C:/temp/temp/resource";
    
    
    //渡されたInputStreamから1行読んで、Stringで返す。
    private static String readLine(InputStream inputStream) throws Exception {
        int buff;
        String ret = "";
        while( (buff = inputStream.read()) != -1) {
            //windowsは\r\nなので改行処理は\nでのみ行えばよい。
            if( buff == '\r') {
                //なにもしないで、次の文字(byte)読む。
            } else if ( buff == '\n'){
                break;
            } else {
                ret += (char)buff;
            }
        }
        //readLine()は失敗すると-1返すので、そのときはnullを返す。
        if (buff == -1){
            System.out.println("[Server] method readLine return null");
            return null;
        } else {
            System.out.println("[Server]method readLine return someString :buff is :"+buff);
            return ret;
        }
    }
    
    //StringとOutputStream渡して、バイト列として書き込ませる。
    private static void writeLine(OutputStream outputStream,String string) throws Exception {
        for( char ch : string.toCharArray()){
            outputStream.write( (int)ch);
        }
        outputStream.write((int)'\r');
        outputStream.write((int)'\n');
    }
    
    //現在時刻からHTTP標準に合わせてフォーマットされた日付文字列を返す。
    private static String getDateStringUtc() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        DateFormat dateFormat = new SimpleDateFormat("EEE,dd MMM yyyy HH:mm:ss", Locale.US);
        dateFormat.setTimeZone(calendar.getTimeZone());
        return dateFormat.format(calendar.getTime());
    }
    
    
    public static void main(String[] argv) throws Exception {
        
            ServerSocket serverSocket = new ServerSocket(8001);
            System.out.println("SoverSocket created");
            //acceptはblockメソッド。connectionが確立されるまで以下のコードは実行されない。
            Socket socket = serverSocket.accept();
            System.out.println("connection is established");
            
            InputStream inputStream = socket.getInputStream();
            System.out.println("[Server]:got inputStrem by socket"); 
            String line;
            String path = null;
            //helperメソッドを利用してsocketのinputStreamから1行読んでもらう。
            while( (line = readLine(inputStream)) != null) {
                System.out.println("[Server]:"+line);
                if( line == ""){
                     System.out.println("[Server]:parse request heaer break");
                     break;
                }
                if (line.startsWith("GET")) {
                    path = line.split(" ")[1];   //GET /foo/index.html のようになっているので/foo/index.htmlを[1]で取得。
                    System.out.println("[Server]:getting file path:"+path);
                }
            }
            OutputStream outputStream = socket.getOutputStream();
            System.out.println("[Server]:got outputstream");
            //response headerを作っていく
            writeLine(outputStream, "HTTP/1.1 200 OK");
            writeLine(outputStream, "Date: "+ getDateStringUtc());
            writeLine(outputStream, "Server: "+System.getProperty("user.dir"));
            writeLine(outputStream, "Connection: close");
            writeLine(outputStream, "Content-type: text/html");
            writeLine(outputStream, "");
            
            try(FileInputStream fis = new FileInputStream(DOCUMENT_ROOT + path);){
                System.out.println("[Server]:resouce file path :"+DOCUMENT_ROOT + path);
                int buff;
                while ( (buff = fis.read()) != -1) {
                    outputStream.write(buff);               
                 }
             }
            socket.close();
            System.out.println("[Server]:socket closed");
            
    }//close main
}//close class
   
            
            
                
 
    
