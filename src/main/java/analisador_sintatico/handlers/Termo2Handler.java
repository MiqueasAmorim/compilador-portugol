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
public class Termo2Handler extends AbstractHandler {

    public Termo2Handler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode termo2 = new DefaultMutableTreeNode("Termo2");
        nextToken();
        if (currentToken == Token.OP_MULTIPLICACAO || currentToken == Token.OP_DIVISAO || currentToken == Token.PC_RESTO || currentToken == Token.PC_QUOCIENTE) {
            termo2.add(new DefaultMutableTreeNode(getCurrentLexema()));
            removeToken();
            if (new UnarioHandler(tokens, termo2).handle() && new Termo2Handler(tokens, termo2).handle()) {
                this.noPai.add(termo2);
                return true;
            }
            return false;
        }
        return true;
    }
    
}
