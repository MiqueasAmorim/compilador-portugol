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
public class ExprHandler extends AbstractHandler {

    public ExprHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode expr = new DefaultMutableTreeNode("Expr");
        if (new ExprComparacaoHandler(tokens, expr).handle() && new Expr2Handler(tokens, expr).handle()) {
            this.noPai.add(expr);
            return true;
        }
        return false;
    }
    
}
