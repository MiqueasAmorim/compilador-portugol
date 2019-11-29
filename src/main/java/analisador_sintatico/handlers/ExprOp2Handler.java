/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.Token;
import model.TokenModel;

/**
 *
 * @author Miqueas
 */
public class ExprOp2Handler extends AbstractHandler {

    public ExprOp2Handler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode exprOp2 = new DefaultMutableTreeNode("ExprOp2");
        nextToken();
        if (currentToken == Token.OP_ADICAO || currentToken == Token.OP_SUBTRACAO) {
            exprOp2.add(new DefaultMutableTreeNode(getCurrentLexema()));
            removeToken();
            if (new TermoHandler(tokens, exprOp2).handle() && new ExprOp2Handler(tokens, exprOp2).handle()) {
                this.noPai.add(exprOp2);
                return true;
            }
            return false;
        }
        return true;
    }
    
}
