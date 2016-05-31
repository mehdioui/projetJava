/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.stri.tchat;

import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author aspy
 */
public class AjoutSuppClient extends javax.swing.JFrame {

    List<String> listeSalonAdministre; /* Liste des salons administrés par le user connecté */
    List<String> listeUserSalon; /* User autorise sur le salon */
    List<String> listeNonAutorise; /* Users qui n'appartiennent pas au salon */
            
    /**
     * Creates new form AjoutSuppClient
     * @param nomSalon
     */
    public AjoutSuppClient(String nomSalon) {
        initComponents();
        jLabelSalon.setText(nomSalon);
        /* Initialisation de listeSalonAdministre */
        salonAdministre();
        /* Initialisation de listeUserSalon */
        userAutorises();
        /* Initialisation de listeNonAutorise */
        userNonAutorises();
    }
    
    /**
     * Creates new form AjoutSuppClient
     */
    public AjoutSuppClient() {
        initComponents();
        /* Initialisation de listeSalonAdministre */
        salonAdministre(); 
        /* Initialisation de listeUserSalon */
        userAutorises();
        /* Initialisation de listeNonAutorise */
        userNonAutorises();
    }  

    /**
     * Mise à jour de la liste des salons administrés par le user
     */ 
    public void salonAdministre() {
        String nomsalon;
        int i;
        listeSalonAdministre = SGBDUtils.getSalonAdministre(SGBDUtils.iduser_connecte);
        DefaultListModel<String> listeS = new DefaultListModel();

        for (i = 0; i < listeSalonAdministre.size(); i++) {
            nomsalon = listeSalonAdministre.get(i);
            System.out.println(nomsalon);
            listeS.addElement(nomsalon);
        }
        jListSalon.setModel(listeS);
    }
    
    /**
     * Mise à jour de la liste des users autorisés sur un salon
     */
    public void userAutorises(){
        String nomUser;
        int i;
        listeUserSalon = SGBDUtils.recupUserSalon(jLabelSalon.getText());
        DefaultListModel<String> listeU = new DefaultListModel();
        
        for (i = 0; i < listeUserSalon.size(); i++){
            nomUser = listeUserSalon.get(i);
            listeU.addElement(nomUser);
        }
        jListPresents.setModel(listeU);
    }

    /**
     * Mise à jour de la liste des users non autorisés sur un salon
     */
    public void userNonAutorises(){
        String nomUser="";
        int i;
        listeNonAutorise = SGBDUtils.getUserNonPresents(SGBDUtils.getSalon(jLabelSalon.getText()).getID()); /* Modifier getAllUsers() */    
        DefaultListModel<String> listeI = new DefaultListModel();
        
        for (i = 0; i < listeNonAutorise.size(); i++){
            nomUser = listeNonAutorise.get(i);
             System.out.println(nomUser);
            listeI.addElement(nomUser);
        }
        jListNonPresents.setModel(listeI);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListSalon = new javax.swing.JList<>();
        jToggleButtonOK = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListPresents = new javax.swing.JList<>();
        jScrollPaneAjouter = new javax.swing.JScrollPane();
        jListNonPresents = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelSalon = new javax.swing.JLabel();
        jButtonAjouter = new javax.swing.JButton();
        jButtonEnlever = new javax.swing.JButton();
        jButtonValider = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();
        jRadioButtonLectEcrit = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Liste des Salons", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(85, 35, 243))); // NOI18N

        jScrollPane1.setViewportView(jListSalon);

        jToggleButtonOK.setText("OK");
        jToggleButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jToggleButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jToggleButtonOK)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        jSeparator1.setBackground(new java.awt.Color(85, 35, 243));
        jSeparator1.setForeground(new java.awt.Color(85, 35, 243));

        jScrollPane2.setViewportView(jListPresents);

        jScrollPaneAjouter.setViewportView(jListNonPresents);

        jLabel1.setText("Ajouter");

        jLabel2.setText("Présent(s) sur le salon");

        jLabelSalon.setText("salon");

        jButtonAjouter.setText("-->");
        jButtonAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterActionPerformed(evt);
            }
        });

        jButtonEnlever.setText("<--");
        jButtonEnlever.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnleverActionPerformed(evt);
            }
        });

        jButtonValider.setText("Valider");
        jButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderActionPerformed(evt);
            }
        });

        jButtonAnnuler.setText("Annuler");
        jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnulerActionPerformed(evt);
            }
        });

        jRadioButtonLectEcrit.setText("Lect & Ecrit");

        jLabel3.setText("Les droits sont la Lecture par défaut, cochez 'Lect & Ecrit' pour ajouter le droit d'écrire ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(53, 53, 53))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jButtonValider)
                        .addGap(136, 136, 136)
                        .addComponent(jButtonAnnuler))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPaneAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAjouter)
                                .addGap(117, 117, 117)
                                .addComponent(jButtonEnlever))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jRadioButtonLectEcrit)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelSalon, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelSalon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonValider)
                    .addComponent(jButtonAnnuler))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPaneAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButtonLectEcrit)
                        .addGap(81, 81, 81)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonEnlever)
                            .addComponent(jButtonAjouter))
                        .addGap(193, 193, 193)))
                .addComponent(jLabel3)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Ajouter un user autorise au salon
     * @param evt 
     */
    private void jButtonAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterActionPerformed
        int droit = 1;
        /* Ajout dans la base de données */
        if (jRadioButtonLectEcrit.isSelected()){
            droit = 0;
        }
        SGBDUtils.addUserSalon(jListNonPresents.getSelectedValue(), jLabelSalon.getText(), droit);
        /* Ajout à la liste des présents sur le salon */
        this.listeUserSalon.add(jListNonPresents.getSelectedValue());
        userAutorises();
        userNonAutorises();        
    }//GEN-LAST:event_jButtonAjouterActionPerformed

    /**
     * Retirer un user du salon
     * @param evt 
     */
    private void jButtonEnleverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnleverActionPerformed
        /* Mise à jour de la base de données */
        SGBDUtils.delUserSalon(jListPresents.getSelectedValue(), jLabelSalon.getText());
        /* Supprimer le user de la liste des users d'un salon */
        this.listeUserSalon.remove(jListPresents.getSelectedValue());
        userNonAutorises();
        userAutorises();
    }//GEN-LAST:event_jButtonEnleverActionPerformed

    /* Renvoyer vers la fenetre de creation */
    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed
        EspaceAdmin fenetreAdmin = new EspaceAdmin(SGBDUtils.getUserId(SGBDUtils.iduser_connecte));
        fenetreAdmin.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonValiderActionPerformed

    /**
     * Retour vers la fenetre de création de salon
     * @param evt 
     */
    private void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerActionPerformed
        EspaceAdmin fenetreAdmin = new EspaceAdmin(SGBDUtils.getUserId(SGBDUtils.iduser_connecte));
        fenetreAdmin.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonAnnulerActionPerformed

    private void jToggleButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonOKActionPerformed
        String nomSalon = jListSalon.getSelectedValue();
        jLabelSalon.setText(nomSalon);
        userAutorises();
        userNonAutorises();
    }//GEN-LAST:event_jToggleButtonOKActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonEnlever;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelSalon;
    private javax.swing.JList<String> jListNonPresents;
    private javax.swing.JList<String> jListPresents;
    private javax.swing.JList<String> jListSalon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonLectEcrit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneAjouter;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jToggleButtonOK;
    // End of variables declaration//GEN-END:variables
}
