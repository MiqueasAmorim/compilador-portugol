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
public class MaisConteudoHandler extends AbstractHandler {

    public MaisConteudoHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode maisConteudo = new DefaultMutableTreeNode("MaisConteudo");
        nextToken();
        if (currentToken == Token.VIRGULA) {
            maisConteudo.add(new DefaultMutableTreeNode(","));
            removeToken();
            if (new ConteudoHandler(tokens, maisConteudo).handle() && new MaisConteudoHandler(tokens, maisConteudo).handle()) {
                this.noPai.add(maisConteudo);
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
    
}
