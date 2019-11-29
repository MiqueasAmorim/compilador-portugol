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
public class ExprOpHandler extends AbstractHandler {

    public ExprOpHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode exprOp = new DefaultMutableTreeNode("ExprOp");
        if (new TermoHandler(tokens, exprOp).handle() && new ExprOp2Handler(tokens, exprOp).handle()) {
            this.noPai.add(exprOp);
            return true;
        }
        return false;
    }
    
}
