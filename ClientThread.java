
package hh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import javax.swing.SwingWorker;


public class ClientThread extends Thread {
    private final Socket toClientSocket; //与客户机对话的套接字
    private BufferedReader in; //网络输入流

    private PrintWriter out; //网络输出流
    private Protocol protocol; //会话协议    
    private int clientCounts; //客户机数量   
    public ClientThread(Socket toClientSocket,int clientCounts) { //构造函数

        this.toClientSocket=toClientSocket;
        this.clientCounts=clientCounts;

    }

   public void run() {

        try {

            //创建绑定到套接字toClientSocket上的网络输入流与输出流
            in=new BufferedReader(new InputStreamReader(toClientSocket.getInputStream(),"UTF-8"));
            out=new PrintWriter(new OutputStreamWriter(toClientSocket.getOutputStream(),"UTF-8"),true);
            long currentId=Thread.currentThread().getId();
            ServerUI.txtArea.append("当前会话线程ID："+currentId+"\n"); //发布到process
            //根据服务器协议，在网络流上进行读写操作

            protocol=new Protocol(); //生成协议对象
            String godStr; //god的回答
            String cuteStr; //cute的问话
            godStr=protocol.protocolWorking(null); //根据协议生成god的问话
            out.println(godStr); //向客户机发起会话
            ServerUI.txtArea.append("God"+clientCounts+"："+godStr+"\n"); //发布god的话到process处理        
            while ((cuteStr=in.readLine())!=null) { //只要客户机不断开连接则反复读
                ServerUI.txtArea.append("Cute"+clientCounts+"："+cuteStr+"\n");  //发布cute的话到process处理
                //根据协议生成回答消息
                godStr=protocol.protocolWorking(cuteStr);
                out.println(godStr); //向客户机发送回答
                ServerUI.txtArea.append("God"+clientCounts+"："+godStr+"\n"); //发布god的话到process处理
                if (godStr.endsWith("Goodbye!")) //结束游戏
                    break;
            }//end while 
            ServerUI.clientCounts--;  //客户机总数减1
            //因为客户机断开了连接，所以释放资源
            if (in!=null) in.close();
            if (out!=null) out.close();
            if (toClientSocket!=null) toClientSocket.close();
        } catch (IOException ex) {}
    }//end run 
}//end class ClientThread