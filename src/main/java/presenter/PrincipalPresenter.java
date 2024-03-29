/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import analisador_lexico.AnalisadorLexico;
import analisador_sintatico.AnalisadorSintatico;
import apoio.RetornaErro;
import editor.KeywordStyledDocument;
import editor.NumeredBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import model.ErrorModel;
import model.TokenModel;
import view.PrincipalView;

/**
 *
 * @author Miqueas
 */
public class PrincipalPresenter {

    private PrincipalView view;
    private AnalisadorLexico analisadorLexico;
    private AnalisadorSintatico analisadorSintatico;
    private Timer timer = null;
    private KeyListener keyListenerTextPane = null;
    private boolean alteracoes = false;
    private File arquivo = null;

    public PrincipalPresenter() {
        try {
            view = new PrincipalView();
            analisadorLexico = new AnalisadorLexico();

            StyleContext styleContext = new StyleContext();
            Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);
            Style cwStyle = styleContext.addStyle("ConstantWidth", null);
            StyleConstants.setForeground(cwStyle, Color.BLUE);
            StyleConstants.setBold(cwStyle, true);

            view.getjTextPaneCodigo().setBorder(new NumeredBorder());
            view.getjTextPaneCodigo().setStyledDocument(new KeywordStyledDocument(defaultStyle, cwStyle));
            //view.getjTextPaneCodigo().setFont(new Font("Courier New", Font.PLAIN, 12));

            view.getBtnCompilar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    analiseLexica();
                    if (analisadorLexico.getErros().isEmpty()) {
                        analiseSintatica();
                    } else {
                        DefaultTableModel tblModelOutput = (DefaultTableModel) view.getTblOutput().getModel();
                        tblModelOutput.addRow(new Object[]{"<html><font color=\"red\"><b>Não foi possível realizar a análise sintática devido a erro léxico.</b></font></html>"});
                    }
                }
            });

            view.getMenuItemAnaliseAutomatica().addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ie) {
                    if (view.getMenuItemAnaliseAutomatica().isSelected()) {
                        analiseLexica();
                        keyListenerTextPane = new KeyAdapter() {
                            @Override
                            public void keyReleased(KeyEvent ke) {
                                alteracoes = true;
                                if (alteracoes) {
                                    if (timer != null) {
                                        timer.stop();
                                        timer = null;
                                    }

                                    ActionListener action = new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent ae) {
                                            if (alteracoes) {
                                                alteracoes = false;
                                                analiseLexica();
                                                if (analisadorLexico.getErros().isEmpty()) {
                                                    analiseSintatica();
                                                } else {
                                                    DefaultTableModel tblModelOutput = (DefaultTableModel) view.getTblOutput().getModel();
                                                    tblModelOutput.addRow(new Object[]{"<html><font color=\"red\"><b>Não foi possível realizar a análise sintática devido a erro léxico.</b></font></html>"});
                                                }
                                            }
                                        }
                                    };

                                    timer = new Timer(1000, action);
                                    timer.start();

                                } else {
                                    if (timer != null) {
                                        timer.stop();
                                        timer = null;
                                    }
                                }
                            }
                        };
                        view.getjTextPaneCodigo().addKeyListener(keyListenerTextPane);

                    } else {
                        view.getjTextPaneCodigo().removeKeyListener(keyListenerTextPane);
                    }
                }
            });

            view.getMenuItemAnaliseAutomatica().setSelected(true);
            view.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.getBtnAbrir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    abrir();
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    salvar();
                } catch (Exception ex) {
                    Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public void analiseLexica() {
        try {
            String codigo = view.getjTextPaneCodigo().getText();
            analisadorLexico.analisar(codigo);
            DefaultTableModel tblModel = (DefaultTableModel) view.getTblTokens().getModel();
            tblModel.setNumRows(0);
            for (TokenModel token : analisadorLexico.getTokens()) {
                tblModel.addRow(new Object[]{token.getLinha(), token.getID(), token.getNome(), token.getLexema()});
            }
            DefaultTableModel tblModelOutput = (DefaultTableModel) view.getTblOutput().getModel();
            tblModelOutput.setNumRows(0);
            if (analisadorLexico.getErros().isEmpty()) {
                tblModelOutput.addRow(new Object[]{"<html><font color=\"green\"><b>Nenhum erro léxico.</b></font></html>"});
            } else {
                for (TokenModel erro : analisadorLexico.getErros()) {
                    tblModelOutput.addRow(new Object[]{"<html><font color=\"red\"><b>[ERRO LÉXICO] => Caractere ou palavra inválida " + erro.getLexema() + " na linha " + erro.getLinha() + " e coluna " + erro.getColuna() + "</b></font></html>"});
                }
            }
        } catch (RuntimeException | IOException ex) {

        } finally {
            DefaultTableModel tblModel = (DefaultTableModel) view.getTblTokens().getModel();
            tblModel.setNumRows(0);
            for (TokenModel token : analisadorLexico.getTokens()) {
                tblModel.addRow(new Object[]{token.getLinha(), token.getID(), token.getNome(), token.getLexema()});
            }
        }
    }

    public void analiseSintatica() {
        analisadorSintatico = new AnalisadorSintatico(analisadorLexico.getTokens());
        DefaultTableModel tblModelOutput = (DefaultTableModel) view.getTblOutput().getModel();
        if (!analisadorSintatico.run()) {
            //System.err.println("[ERRO SINTÁTICO] -> Linha " + ErrorModel.getInstance().getLinha() + " - " + RetornaErro.getError(ErrorModel.getInstance()) + "");
            view.getPanelArvoreSintatica().removeAll();
            view.getPanelArvoreSintatica().repaint();
            view.getPanelArvoreSintatica().validate();
            tblModelOutput.addRow(new Object[]{"<html><font color=\"red\"><b>[ERRO SINTÁTICO] => " + RetornaErro.getError(ErrorModel.getInstance()) + " na linha " + ErrorModel.getInstance().getLinha() + "</b></font></html>"});
        } else {
            tblModelOutput.addRow(new Object[]{"<html><font color=\"green\"><b>Nenhum erro sintático.</b></font></html>"});
            
//      Cria árvore sintática
//            DefaultMutableTreeNode programa = new DefaultMutableTreeNode("PROGRAMA");
//            DefaultMutableTreeNode id = new DefaultMutableTreeNode("ID");
//            id.add(new DefaultMutableTreeNode("fat"));
//            programa.add(id);
            JTree arvore = new JTree(analisadorSintatico.getArvoreSintatica());
            for (int i = 0; i < arvore.getRowCount(); i++) {
                arvore.expandRow(i);
            }
            view.getPanelArvoreSintatica().removeAll();
            //view.getPanelArvoreSintatica().add();
            view.getPanelArvoreSintatica().add(new JScrollPane(arvore));
            view.getPanelArvoreSintatica().repaint();
            view.getPanelArvoreSintatica().validate();
        }

//        ArrayList<ErrorModel> erros = analisadorSintatico.run();
//        if (!erros.isEmpty()) {
//            for (ErrorModel erro : erros) {
//                System.err.println("[ERRO SINTÁTICO] -> Linha " + erro.getLinha() + " - " + RetornaErro.getError(erro) + "");
//            }
//        } else {
//            System.out.println("Nenhum erro sintático encontrado.");
//        }
    }

    public void salvar() throws Exception {

        if (this.arquivo == null) {

            JFileChooser salvar = new JFileChooser();
            int i = salvar.showSaveDialog(view);

            if (i == JFileChooser.APPROVE_OPTION) {
                this.arquivo = salvar.getSelectedFile();
                FileWriter arq = new FileWriter(this.arquivo + ".ptl");
                view.getPaneCodigo().setTitleAt(0, this.arquivo.getName());
                arq.write(view.getjTextPaneCodigo().getText());
                arq.close();
            }
        } else {
            FileWriter fr = new FileWriter(this.arquivo);
            BufferedWriter bw = new BufferedWriter(fr);
            bw.write(view.getjTextPaneCodigo().getText());
            bw.flush();
            bw.close();
            fr.close();
        }
    }

    public void abrir() throws FileNotFoundException, IOException {
        JFileChooser abrir = new JFileChooser();
        int i = abrir.showOpenDialog(view);

        if (i == JFileChooser.APPROVE_OPTION) {
            this.arquivo = abrir.getSelectedFile();
            FileReader ler = new FileReader(this.arquivo);
            view.getPaneCodigo().setTitleAt(0, this.arquivo.getName());
            BufferedReader p;
            p = new BufferedReader(ler);
            String linha = null;
            String codigo = "";
            while ((linha = p.readLine()) != null) {
                codigo += linha + "\n";

            }

            view.getjTextPaneCodigo().setText(codigo);
            analiseLexica();
        }
    }

}
