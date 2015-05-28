public class SampleClient{
  public static void main[T](String[] args){
    try{
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Server name >");
      String serveName = reader.readLine();
      System.out.print("port number >");
      int portNumber = Integer.parseInt(reader.readLine());

      Socket socket = new Socket(serverName,portNumber);
      
      //サーバに送信するオブジェクトの生成
      T someObject = new Object();

      ObjcetOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      
      oos.writeObject(someObject);
      //バッファされる
      oos.flush();

      //サーバから結果が返ってくる。
      ObjcetInputStream ois = new ObjcetInputStream(socket.getInputStream());

      Object result = ois.readObject();
    }catch(Exception e){ e.printStackTrace();}
  }
}
