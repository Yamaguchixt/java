package socket;

import java.io.*;
import java.net.*;

public class Client {
    public  static void main(String[] args) throws Exception {
        try {Socket socket = new Socket("localhost",8001);
                FileInputStream fis = new FileInputStream("C:/temp/client.txt");
                FileOutputStream fos = new FileOutputStream("C:/temp/client_recv.txt");
                
                int ch;
                // client_send.txtの内容をサーバに送信
                OutputStream output = socket.getOutputStream();
                System.out.println("[Client]:socket open");
                while ((ch = fis.read()) != -1) {
                    output.write(ch);
                }
               
                //サーバからの返信をclient_recv.txtに出力
                InputStream input = socket.getInputStream();
                while ((ch = input.read()) != -1) {
                    fos.write(ch);
                }
                System.out.println("[Client]:end");
                //socketのoutputをcloseするとソケット自体が閉じてしまう
        } catch (Exception ex) {
                ex.printStackTrace();
        }
    }
}
            
 
