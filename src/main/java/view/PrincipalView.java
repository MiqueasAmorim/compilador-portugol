package view;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miqueas
 */
public class PrincipalView extends javax.swing.JFrame {

    /**
     * Creates new form PrincipalView
     */
    public PrincipalView() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.tblOutput.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.tblOutput.getColumnModel().getColumn(1).setPreferredWidth(600);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator5 = new javax.swing.JSeparator();
        paneCodigo = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaCodigo = new javax.swing.JTextArea();
        paneAnalises = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();
        paneOutput = new javax.swing.JTabbedPane();
        panelOutput = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblOutput = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btnNovo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnDesfazer = new javax.swing.JButton();
        btnRefazer = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnCompilar = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItemNovo = new javax.swing.JMenuItem();
        menuItemAbrir = new javax.swing.JMenuItem();
        menuItemSalvar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuItemDesfazer = new javax.swing.JMenuItem();
        menuItemRefazer = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuItemCompilar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IDE Portugol");

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        txtAreaCodigo.setColumns(20);
        txtAreaCodigo.setRows(5);
        jScrollPane1.setViewportView(txtAreaCodigo);

        jPanel1.add(jScrollPane1);

        paneCodigo.addTab("semtitulo.ptl", jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linha", "ID", "Token", "Lexema"
            }
        ));
        jScrollPane.setViewportView(tblTokens);

        jPanel2.add(jScrollPane, java.awt.BorderLayout.CENTER);

        paneAnalises.addTab("Tabela de tokens", jPanel2);

        panelOutput.setLayout(new java.awt.BorderLayout());

        tblOutput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linha", "Mensagem"
            }
        ));
        jScrollPane3.setViewportView(tblOutput);
        if (tblOutput.getColumnModel().getColumnCount() > 0) {
            tblOutput.getColumnModel().getColumn(0).setHeaderValue("Linha");
            tblOutput.getColumnModel().getColumn(1).setHeaderValue("Mensagem");
        }

        panelOutput.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        paneOutput.addTab("Output", panelOutput);

        jToolBar1.setRollover(true);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new-file.png"))); // NOI18N
        btnNovo.setFocusable(false);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setPreferredSize(new java.awt.Dimension(30, 30));
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnNovo);

        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/open-file.png"))); // NOI18N
        btnAbrir.setFocusable(false);
        btnAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrir.setPreferredSize(new java.awt.Dimension(30, 30));
        btnAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnAbrir);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save-file.png"))); // NOI18N
        btnSalvar.setFocusable(false);
        btnSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnSalvar);
        jToolBar1.add(jSeparator1);

        btnDesfazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/undo.png"))); // NOI18N
        btnDesfazer.setFocusable(false);
        btnDesfazer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDesfazer.setPreferredSize(new java.awt.Dimension(30, 30));
        btnDesfazer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnDesfazer);

        btnRefazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redo.png"))); // NOI18N
        btnRefazer.setFocusable(false);
        btnRefazer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefazer.setPreferredSize(new java.awt.Dimension(30, 30));
        btnRefazer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnRefazer);
        jToolBar1.add(jSeparator2);

        btnCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compile.png"))); // NOI18N
        btnCompilar.setFocusable(false);
        btnCompilar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompilar.setPreferredSize(new java.awt.Dimension(30, 30));
        btnCompilar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnCompilar);

        jMenu1.setText("Arquivo");

        menuItemNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new-file.png"))); // NOI18N
        menuItemNovo.setText("Novo");
        jMenu1.add(menuItemNovo);

        menuItemAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/open-file.png"))); // NOI18N
        menuItemAbrir.setText("Abrir");
        jMenu1.add(menuItemAbrir);

        menuItemSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save-file.png"))); // NOI18N
        menuItemSalvar.setText("Salvar");
        jMenu1.add(menuItemSalvar);

        menuBar.add(jMenu1);

        jMenu2.setText("Editar");

        menuItemDesfazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/undo.png"))); // NOI18N
        menuItemDesfazer.setText("Desfazer");
        jMenu2.add(menuItemDesfazer);

        menuItemRefazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redo.png"))); // NOI18N
        menuItemRefazer.setText("Refazer");
        jMenu2.add(menuItemRefazer);

        menuBar.add(jMenu2);

        jMenu3.setText("Executar");

        menuItemCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/compile.png"))); // NOI18N
        menuItemCompilar.setText("Compilar");
        jMenu3.add(menuItemCompilar);

        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(paneOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paneAnalises, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(paneCodigo)))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paneCodigo)
                    .addComponent(paneAnalises, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(paneOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnDesfazer;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRefazer;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemAbrir;
    private javax.swing.JMenuItem menuItemCompilar;
    private javax.swing.JMenuItem menuItemDesfazer;
    private javax.swing.JMenuItem menuItemNovo;
    private javax.swing.JMenuItem menuItemRefazer;
    private javax.swing.JMenuItem menuItemSalvar;
    private javax.swing.JTabbedPane paneAnalises;
    private javax.swing.JTabbedPane paneCodigo;
    private javax.swing.JTabbedPane paneOutput;
    private javax.swing.JPanel panelOutput;
    private javax.swing.JTable tblOutput;
    private javax.swing.JTable tblTokens;
    private javax.swing.JTextArea txtAreaCodigo;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnAbrir() {
        return btnAbrir;
    }

    public JButton getBtnCompilar() {
        return btnCompilar;
    }

    public JButton getBtnDesfazer() {
        return btnDesfazer;
    }

    public JButton getBtnNovo() {
        return btnNovo;
    }

    public JButton getBtnRefazer() {
        return btnRefazer;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JMenuItem getMenuItemAbrir() {
        return menuItemAbrir;
    }

    public JMenuItem getMenuItemCompilar() {
        return menuItemCompilar;
    }

    public JMenuItem getMenuItemDesfazer() {
        return menuItemDesfazer;
    }

    public JMenuItem getMenuItemNovo() {
        return menuItemNovo;
    }

    public JMenuItem getMenuItemRefazer() {
        return menuItemRefazer;
    }

    public JMenuItem getMenuItemSalvar() {
        return menuItemSalvar;
    }

    public JTable getTblOutput() {
        return tblOutput;
    }

    public JTable getTblTokens() {
        return tblTokens;
    }

    public JTextArea getTxtAreaCodigo() {
        return txtAreaCodigo;
    }

    


}
