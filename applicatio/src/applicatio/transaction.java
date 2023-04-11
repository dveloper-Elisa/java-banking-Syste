/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicatio;

import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author IT Elisa
 */
public class transaction extends javax.swing.JFrame {

    /**
     * Creates new form transaction
     */
    public transaction() {
        initComponents();
        
    }
    Action act = new Action();
    
    String action;
    int CliID;
    LocalDateTime date = LocalDateTime.now();
    LocalDate date1 = LocalDate.now();
    LocalTime time = LocalTime.now();
    String name;
    String telphonee,email;
    
    
//    try{
//            Thread.sleep(1000);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
    
    class Action{

    void update(){
          if(jRadioButton1.isSelected()){
       try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/banking","root","");
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT clientID FROM client WHERE clientID = '"+jTextField1.getText()+"'");
        if(rs.next()){
        CliID = Integer.parseInt(rs.getString("clientID"));
        stmt.executeUpdate("UPDATE amount INNER JOIN client SET amount.balance = amount.balance + '"+Integer.parseInt(jTextField4.getText())+"' WHERE amount.clientID = '"+jTextField1.getText()+"' ");
        stmt.executeUpdate("INSERT INTO credit VALUES(null,'"+CliID+"','"+Integer.parseInt(jTextField4.getText())+"','DR','Debit','"+date+"')");
        }
        JOptionPane.showMessageDialog(null,"Deposting Done well!!!","Helloo!!",HEIGHT);
       }catch(SQLException | ClassNotFoundException ex){
           JOptionPane.showMessageDialog(null,ex,"Hello", HEIGHT);
       }
       
   }
   
    }
    
    /////////////////////////////////////////////////////
    
    void Withdraw(){
//        if(!jRadioButton1.isSelected() || !jRadioButton2.isSelected() || !jRadioButton3.isSelected() || !jRadioButton4.isSelected() || !jRadioButton5.isSelected()){
//            JOptionPane.showMessageDialog(null,"PLEASE SELECT \"Action\"","HELLO0!!",HEIGHT);
//        }else{
        int currentBalnce = Integer.parseInt(jTextField5.getText());
        if(jRadioButton2.isSelected()){
        if(currentBalnce < Integer.parseInt(jTextField4.getText())){
            JOptionPane.showMessageDialog(null,"You have no enough amount to with drow just brow!!!","HELLO!!!!",HEIGHT);
        }else{
            try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/banking","root","");
        
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE amount INNER JOIN client SET amount.balance = amount.balance - '"+Integer.parseInt(jTextField4.getText())+"' WHERE amount.clientID = '"+jTextField1.getText()+"' ");
        
        ResultSet rs = stmt.executeQuery("SELECT clientID FROM client WHERE clientID = '"+jTextField1.getText()+"'");
        if(rs.next()){
        CliID = Integer.parseInt(rs.getString("clientID"));
        stmt.executeUpdate("INSERT INTO credit VALUES(null,'"+CliID+"','"+Integer.parseInt(jTextField4.getText())+"','CashOut','withdrow','"+date+"')");
        }
        
        JOptionPane.showMessageDialog(null,"Withdrow Done well!!","Hello!!",HEIGHT);
       }catch(SQLException | ClassNotFoundException ex){
           JOptionPane.showMessageDialog(null,ex,"Hello", HEIGHT);
       }
             
        }
        }
    }
    
    
    //////////////////////////////////////////////////////
    
    void borow(){
        if(jRadioButton3.isSelected()){
            
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/banking","root","");
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT clientID from client WHERE clientID = '"+jTextField1.getText()+"'");
        
        
        if(rs.next()){
        CliID = Integer.parseInt(rs.getString("clientID"));
        stmt.executeUpdate("INSERT INTO credit VALUES(null,'"+CliID+"','"+Integer.parseInt(jTextField4.getText())+"','CR','Brow','"+date+"')");
        stmt.executeUpdate("UPDATE amount SET balance = balance - '"+jTextField4.getText()+"' WHERE clientID = '"+CliID+"'");
        
        JOptionPane.showMessageDialog(null,"On "+date+"Youhave been Accredited with"+jTextField4.getText(),"Hello!!",HEIGHT);
        }
             }catch(SQLException | ClassNotFoundException ex){
              JOptionPane.showMessageDialog(null,ex,"Hello", HEIGHT);   
             }
            
        }
    }
    
    /////////////////////////////////////////////////////
    
    void paye(){
        if(jRadioButton5.isSelected()){
            
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/banking","root","");
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT clientID from client WHERE clientID = '"+jTextField1.getText()+"'");
        
        
        if(rs.next()){
        CliID = Integer.parseInt(rs.getString("clientID"));
        stmt.executeUpdate("INSERT INTO credit VALUES(null,'"+CliID+"','"+Integer.parseInt(jTextField4.getText())+"','Paye','Paye credit','"+date+"')");
        stmt.executeUpdate("UPDATE amount SET balance = balance + '"+jTextField4.getText()+"' WHERE clientID = '"+CliID+"'");
        
        JOptionPane.showMessageDialog(null,"On "+date+"Youhave been Payed with"+jTextField4.getText(),"Hello!!",HEIGHT);
        }
             }catch(SQLException | ClassNotFoundException ex){
              JOptionPane.showMessageDialog(null,ex,"Hello", HEIGHT);   
             }
            
        }
        
    }
    
    ////////////////////////////////////////////////////
    
    void transfer(){
        
//        if(!jRadioButton1.isSelected() || !jRadioButton2.isSelected() || !jRadioButton3.isSelected() || !jRadioButton4.isSelected() || !jRadioButton5.isSelected()){
//            JOptionPane.showMessageDialog(null,"PLEASE SELECT \"Action\"","HELLO0!!",HEIGHT);
//        }
//        else{  
            if(jRadioButton4.isSelected()){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/banking","root","");
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("UPDATE amount INNER JOIN client SET amount.balance = amount.balance - '"+Integer.parseInt(jTextField4.getText())+"' WHERE client.clientID = amount.clientID AND client.clientID = '"+jTextField1.getText()+"'");
        stmt.executeUpdate("UPDATE amount INNER JOIN client SET amount.balance = amount.balance + '"+Integer.parseInt(jTextField4.getText())+"' WHERE amount.clientID = '"+jTextField5.getText()+"' ");
        
        JOptionPane.showMessageDialog(null,"Withdrow Done well!!","Hello!!",HEIGHT);
       }catch(SQLException | ClassNotFoundException ex){
           JOptionPane.showMessageDialog(null,ex,"Hello", HEIGHT);
       }
        }
        }
        
//    }
    
    /////////////////////////////////////////////////////
    
    void clean(){

        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        
    }
    
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);
        setLocationByPlatform(true);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setText("MAKE THE TRANSACTION TO USER");

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setText("Search Account");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(51, 51, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 51));
        jLabel2.setText("First Name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 40, -1, -1));

        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 37, 341, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Last Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 133, -1, -1));

        jTextField3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 341, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setText("Depost");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 226, -1, -1));

        jTextField4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 223, 341, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Balance");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 331, -1, -1));

        jTextField5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 328, 341, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bank builiding.jfif222222222222222.PNG"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 440));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton2.setText("Make transaction");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton3.setText("Cancel transaction");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton4.setText("Back to dashboard");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton5.setText("Logout");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton6.setText("Print transaction");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(48, 48, 48)
                .addComponent(jButton3)
                .addGap(47, 47, 47)
                .addComponent(jButton4)
                .addGap(55, 55, 55)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jRadioButton1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(153, 255, 255));
        jRadioButton1.setText("Deposit");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jRadioButton2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(153, 255, 255));
        jRadioButton2.setText("Withdraw");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jRadioButton3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(153, 255, 255));
        jRadioButton3.setText("Brow");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        jRadioButton4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(153, 255, 255));
        jRadioButton4.setText("Transfer");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jRadioButton5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jRadioButton5.setForeground(new java.awt.Color(153, 255, 255));
        jRadioButton5.setText("Pay debit");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stm machin2.PNG"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 320));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 26)); // NOI18N
        jLabel6.setText("Actions");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(203, 203, 203)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(48, 48, 48)
                            .addComponent(jButton1))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            //Searchig
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/banking","root","");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM client INNER JOIN amount WHERE client.clientID = amount.clientID AND client.clientID = '"+jTextField1.getText()+"'");
        
        if(rs.next()){
            jTextField2.setText(rs.getString("FirstName"));
            jTextField3.setText(rs.getString("LastName"));
            jTextField5.setText(rs.getString("amount.balance"));
        }
        
        
        ResultSet tel = stmt.executeQuery("SELECT Telephone FROM client WHERE clientID = '"+jTextField1.getText()+"'");
        if(tel.next()){
            telphonee = tel.getString("Telephone");
        }
        
        ResultSet eml = stmt.executeQuery("SELECT Email FROM client WHERE clientID = '"+jTextField1.getText()+"'");
        if(eml.next()){
            email = eml.getString("Email");
        }
        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(jRadioButton1.isSelected()){
            jLabel4.setText("Depost");
            jLabel5.setText("Balance");
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
            if(jRadioButton2.isSelected()){
            jLabel4.setText("Withdrow");
            jLabel5.setText("Balance");
            jRadioButton1.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);
        }        
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
if(jRadioButton3.isSelected()){
            action = "browing";
            jLabel4.setText("Brow");
            jLabel5.setText("Balance");
            jRadioButton2.setSelected(false);
            jRadioButton1.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);
        }        
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
if(jRadioButton4.isSelected()){
            jLabel5.setText("Receiver");
            jTextField5.setText("");
            jLabel4.setText("Send");
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton1.setSelected(false);
            jRadioButton5.setSelected(false);
        }        
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
if(jRadioButton5.isSelected()){
            jLabel4.setText("Depost");
            jLabel5.setText("Balance");
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton1.setSelected(false);
        }        
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 
   act.update();
   
   act.Withdraw();
   
   act.borow();
   
   act.transfer();
   
   act.paye();
//   act.clean();

    dashboard dash = new dashboard();
   dash.active();
//   newdashboard.active();
   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    act.clean();
    jTextField1.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        dashboard dash = new dashboard();
        dash.setVisible(false);
        login log = new login();
        log.setVisible(true);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
    dashboard dash = new dashboard();
        dash.active();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
   print p = new print();
   p.jLabel8.setText(date.toString());
   p.jLabel10.setText("Bank of kigali");
   p.jLabel11.setText(jLabel4.getText());
   p.jLabel12.setText("0"+telphonee);
   p.jLabel13.setText(email);
   p.jLabel15.setText(jTextField4.getText());
   p.jLabel17.setText(time.toString());
   p.jLabel18.setText(jTextField1.getText());
   p.jLabel21.setText(jTextField2.getText() +" "+ jTextField3.getText());
   p.jLabel23.setText(jTextField5.getText());
   p.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
