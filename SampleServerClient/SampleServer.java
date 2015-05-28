//Serverのサンプル
//クライアントとやり取りするオブジェクトの方はTで抽象化

import java.io.*;
import java.util.*;
import java.net.*;

public class SampleServer[T] {
  
  public static void main(String[] arg){
    try{
      ServerSocket serverSocket = new ServerSocket(8080);
      //この時点でポート監視はじまる。
      Socket socket = serverSocket.accept();

      ObjectInputStream ois = new ObjcetInputStream(socket.getInputStream());

      T someObject = (T)ois.readObject();

      //渡されたObjcetでなんらかの処理
      String someMessage = 'result';

      ObjcetOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      oos.writeObject(someMessage);
      //バッファされてすぐには送られない。
      oos.flush();
    }
    catch(Exception e){ e.printStackTrace};


