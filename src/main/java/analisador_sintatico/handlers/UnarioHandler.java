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
public class UnarioHandler extends AbstractHandler {

    public UnarioHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode unario = new DefaultMutableTreeNode("Unario");
        nextToken();
        if (currentToken == Token.OP_ADICAO || currentToken == Token.OP_SUBTRACAO) {
            unario.add(new DefaultMutableTreeNode(getCurrentLexema()));
            removeToken();
            if (new FatorHandler(tokens, unario).handle()) {
                this.noPai.add(unario);
                return true;
            } else {
                return false;
            }
        }
        if (new FatorHandler(tokens, unario).handle()) {
            this.noPai.add(unario);
            return true;
        } else {
            return false;
        }

    }

}
