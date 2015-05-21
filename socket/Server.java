package socket;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] argv) throws Exception {
        
        try {ServerSocket server = new ServerSocket(8001);
                FileOutputStream fos = new FileOutputStream("C:/temp/server_recv.txt");
                FileInputStream fis = new FileInputStream("C:/temp/server_send.txt");
                
 
            System.out.println("クライアントからの接続を待ちます");
            Socket socket = server.accept();
            System.out.println("クライアント接続");
            
            int ch;
            //クライアントから受け取った内容をserver_recv.txtに出力
            InputStream input = socket.getInputStream();
            //クライアントは終了 のマークとして０を送付してくる
            while ((ch = input.read() )!= 0 ){
                fos.write(ch);
            }
            //server_send.txtの内容をクライアントに送付
            OutputStream output = socket.getOutputStream();
            while ((ch = fis.read()) != -1) {
                output.write(ch);
            }
            socket.close();
            //compilerにwarningだされたので追加。必要性検討。
            server.close();
            fos.close();
            fis.close();
        
        }catch (Exception ex) {
            ex.printStackTrace();
     }
        
    }
    
}
