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
public class ConteudoHandler extends AbstractHandler {

    public ConteudoHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode conteudo = new DefaultMutableTreeNode("Conteudo");
        nextToken();
        if (new IdentificadorHandler(tokens, conteudo).handle()) {
            this.noPai.add(conteudo);
            return true;
        }
        if (currentToken == Token.STRING || currentToken == Token.CARACTERE) {
            conteudo.add(new DefaultMutableTreeNode(getCurrentLexema()));
            removeToken();
            this.noPai.add(conteudo);
            return true;
        }
        return false;
    }
    
}
