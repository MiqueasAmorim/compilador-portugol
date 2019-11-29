/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.TokenModel;

/**
 *
 * @author Miqueas
 */
public class ExprComparacaoHandler extends AbstractHandler {

    public ExprComparacaoHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode exprComparacao = new DefaultMutableTreeNode("ExprComparacao");
        if (new ExprOpHandler(tokens, exprComparacao).handle() && new ExprComparacao2Handler(tokens, exprComparacao).handle()) {
            this.noPai.add(exprComparacao);
            return true;
        }
        return false;
    }
    
}
