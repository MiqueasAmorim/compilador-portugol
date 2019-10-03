/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import analisador_lexico.AnalisadorLexico;
import editor.KeywordStyledDocument;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import model.TokenModel;
import view.PrincipalView;

/**
 *
 * @author Miqueas
 */
public class PrincipalPresenter {

    private PrincipalView view;
    private AnalisadorLexico analisadorLexico;

    public PrincipalPresenter() {
        try {
            view = new PrincipalView();
            analisadorLexico = new AnalisadorLexico();

            StyleContext styleContext = new StyleContext();
            Style defaultStyle = styleContext.getStyle(StyleContext.DEFAULT_STYLE);
            Style cwStyle = styleContext.addStyle("ConstantWidth", null);
            StyleConstants.setForeground(cwStyle, Color.BLUE);
            StyleConstants.setBold(cwStyle, true);
            
            view.getjTextPaneCodigo().setStyledDocument(new KeywordStyledDocument(defaultStyle, cwStyle));
            view.getjTextPaneCodigo().setFont(new Font("Courier New", Font.PLAIN, 12));

            view.getBtnCompilar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
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
                        tblModelOutput.addRow(new Object[]{"<html><font color=\"green\"><b>Compilado com sucesso!</b></font></html>"});
                    } catch (RuntimeException | IOException ex) {
                        DefaultTableModel tblModelOutput = (DefaultTableModel) view.getTblOutput().getModel();
                        tblModelOutput.setNumRows(0);
                        tblModelOutput.addRow(new Object[]{"<html><font color=\"red\"><b>"+ex.getMessage()+"</b></font></html>"});
                    } finally {
                        DefaultTableModel tblModel = (DefaultTableModel) view.getTblTokens().getModel();
                        tblModel.setNumRows(0);
                        for (TokenModel token : analisadorLexico.getTokens()) {
                            tblModel.addRow(new Object[]{token.getLinha(), token.getID(), token.getNome(), token.getLexema()});
                        }
                    }
                }
            });

            view.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
