package hh;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import javax.swing.JOptionPane;
public class ClientUI extends javax.swing.JFrame {
    private Socket clientSocket=null; //客户机套接字
    private BufferedReader in; //网络输入流
    private PrintWriter out; //网络输出流
    public ClientUI() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRemoteName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRemotePort = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();
        midPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        txtInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        topPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("连接服务器"));

        jLabel1.setText("服务器名：");

        txtRemoteName.setText("localhost");

        jLabel2.setText("服务器端口：");

        txtRemotePort.setText("5000");

        btnConnect.setText("连接服务器");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(txtRemoteName, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnConnect)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRemoteName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        midPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("对话面板"));

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        txtInput.setText("输入。。");
        txtInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtInputFocusGained(evt);
            }
        });
        txtInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout midPanelLayout = new javax.swing.GroupLayout(midPanel);
        midPanel.setLayout(midPanelLayout);
        midPanelLayout.setHorizontalGroup(
            midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
            .addComponent(txtInput)
        );
        midPanelLayout.setVerticalGroup(
            midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(midPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtInput))
        );

        getContentPane().add(midPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        // TODO add your handling code here:
        try {
            //获取参数
            String remoteName=txtRemoteName.getText();
            int remotePort=Integer.parseInt(txtRemotePort.getText());
            //构建套接字格式地址
            SocketAddress remoteAddr=new InetSocketAddress(remoteName,remotePort);
            //1. 创建套接字clientSocket（Socket）并连接到远程服务器
            clientSocket=new Socket();            
            clientSocket.connect(remoteAddr);
            //2. 创建绑定到套接字clientSocket上的网络输入流与输出流
            in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),"UTF-8"));
            out=new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),"UTF-8"),true);      
            String from=in.readLine();
            txtArea.append("小可爱 "+from+"\n");        
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "连接错误", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        txtRemoteName.setEnabled(false);
        txtRemotePort.setEnabled(false);
        btnConnect.setEnabled(false);
    }//GEN-LAST:event_btnConnectActionPerformed

    private void txtInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInputActionPerformed
        // TODO add your handling code here:
         //3. 根据服务器协议，在网络流上进行读写操作    
        String fromGod;
        String fromCute;
        if (clientSocket==null) {
            JOptionPane.showMessageDialog(null, "请先连接服务器！", "连接错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            fromCute=txtInput.getText();
            if (!fromCute.equals("")) {
                out.println(fromCute);
                txtArea.append("Cute："+fromCute+"\n");
                txtInput.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "问话内容为空，请重新输入！", "输入错误", JOptionPane.ERROR_MESSAGE);
                return;
            }//end if
            fromGod=in.readLine();
            txtArea.append("God："+fromGod+"\n");
            if (fromGod.endsWith("Goodbye!")) {
                txtRemoteName.setEnabled(true);
                txtRemotePort.setEnabled(true);
                btnConnect.setEnabled(true);
                //4. 关闭并销毁网络流
                //5. 关闭并销毁套接字
                try { 
                    if (in!=null) in.close();
                    if (out!=null) out.close();
                    if (clientSocket!=null)clientSocket.close();    
                } catch (IOException ex) {  }         
            }//end if
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "接收数据错误", JOptionPane.ERROR_MESSAGE);

        }//end try
    }//GEN-LAST:event_txtInputActionPerformed

    private void txtInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtInputFocusGained
        // TODO add your handling code here:
        txtInput.setText("");
    }//GEN-LAST:event_txtInputFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel midPanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtInput;
    private javax.swing.JTextField txtRemoteName;
    private javax.swing.JTextField txtRemotePort;
    // End of variables declaration//GEN-END:variables
}
