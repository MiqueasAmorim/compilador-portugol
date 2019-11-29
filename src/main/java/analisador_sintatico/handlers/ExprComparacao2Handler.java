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
public class ExprComparacao2Handler extends AbstractHandler {

    public ExprComparacao2Handler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode exprComparacao2 = new DefaultMutableTreeNode("ExprComparacao2");
        nextToken();
        if (currentToken == Token.OP_IGUAL || currentToken == Token.OP_DIFERENTE || currentToken == Token.OP_MENOR ||
        currentToken == Token.OP_MENOR_IGUAL || currentToken == Token.OP_MAIOR || currentToken == Token.OP_MAIOR_IGUAL) {
            exprComparacao2.add(new DefaultMutableTreeNode(getCurrentLexema()));
            removeToken();
            if (new ExprOpHandler(tokens, exprComparacao2).handle() && new ExprComparacao2Handler(tokens, exprComparacao2).handle()) {
                this.noPai.add(exprComparacao2);
                return true;
            }
            return false;
        }
        return true;
    }
    
}
