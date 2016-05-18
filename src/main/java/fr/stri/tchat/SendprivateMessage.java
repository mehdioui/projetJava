package fr.stri.tchat;

import static fr.stri.tchat.SGBDUtils.sendPrivatemessage;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stef
 */
public class SendprivateMessage extends javax.swing.JFrame {

	/**
 	* Creates new form SendprivateMessage
 	*/
	public SendprivateMessage() {
    	initComponents();
	}

	/**
 	* This method is called from within the constructor to initialize the form.
 	* WARNING: Do NOT modify this code. The content of this method is always
 	* regenerated by the Form Editor.
 	*/
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">                     	 
	private void initComponents() {

    	jButton1 = new javax.swing.JButton();
    	jScrollPane1 = new javax.swing.JScrollPane();
    	jTextArea1 = new javax.swing.JTextArea();
    	jTextField2 = new javax.swing.JTextField();
    	jLabel1 = new javax.swing.JLabel();

    	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    	jButton1.setText("envoyer");
    	jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent evt) {
            	jButton1MouseClicked(evt);
        	}
    	});
    	jButton1.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButton1ActionPerformed(evt);
        	}
    	});

    	jTextArea1.setColumns(20);
    	jTextArea1.setRows(5);
    	jScrollPane1.setViewportView(jTextArea1);

    	jLabel1.setText("destinataire");

    	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    	getContentPane().setLayout(layout);
    	layout.setHorizontalGroup(
        	layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        	.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            	.addContainerGap(73, Short.MAX_VALUE)
            	.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            	.addGap(73, 85, Short.MAX_VALUE))
        	.addGroup(layout.createSequentialGroup()
            	.addGap(68, 68, 68)
            	.addComponent(jLabel1)
            	.addGap(33, 33, 33)
            	.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
            	.addGap(0, 0, Short.MAX_VALUE))
        	.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            	.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            	.addComponent(jButton1)
            	.addGap(63, 63, 63))
    	);
    	layout.setVerticalGroup(
        	layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        	.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            	.addGap(16, 16, 16)
            	.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                	.addComponent(jLabel1)
                	.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            	.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
            	.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            	.addGap(37, 37, 37)
            	.addComponent(jButton1)
            	.addGap(27, 27, 27))
    	);

    	pack();
	}// </editor-fold>                   	 

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                    	 
    	// TODO add your handling code here:
	}                                   	 

	private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {                                 	 
            try {
                sendPrivatemessage(jTextField2.getText(),jTextArea1.getText());
            } catch (SQLException ex) {
                Logger.getLogger(SendprivateMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
	}                                	 

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
        	java.util.logging.Logger.getLogger(SendprivateMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    	} catch (InstantiationException ex) {
        	java.util.logging.Logger.getLogger(SendprivateMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    	} catch (IllegalAccessException ex) {
        	java.util.logging.Logger.getLogger(SendprivateMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
        	java.util.logging.Logger.getLogger(SendprivateMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    	}
    	//</editor-fold>
    	//</editor-fold>
    	//</editor-fold>
    	//</editor-fold>

    	/* Create and display the form */
    	java.awt.EventQueue.invokeLater(new Runnable() {
        	public void run() {
            	new SendprivateMessage().setVisible(true);
        	}
    	});
	}

	// Variables declaration - do not modify                	 
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField2;
	// End of variables declaration              	 
}





