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
public class Expr2Handler extends AbstractHandler {

    public Expr2Handler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode expr2 = new DefaultMutableTreeNode("Expr2");
        nextToken();
        if (currentToken == Token.OP_OU || currentToken == Token.OP_E) {
            expr2.add(new DefaultMutableTreeNode(getCurrentLexema()));
            removeToken();
            if (new ExprComparacaoHandler(tokens, expr2).handle() && new Expr2Handler(tokens, expr2).handle()) {
                this.noPai.add(expr2);
                return true;
            }
            return false;
        }
        return true;
    }
}
