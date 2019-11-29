/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.ErrorModel;
import model.Token;
import model.TokenModel;

/**
 *
 * @author Miqueas
 */
public class DeclaracaoVariavelHandler extends AbstractHandler {

    public DeclaracaoVariavelHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode declaracaoVariavel = new DefaultMutableTreeNode("DeclaracaoVariavel");
        if (nextToken()){
            if (currentToken == Token.PC_VARIAVEL) {
                declaracaoVariavel.add(new DefaultMutableTreeNode("variavel"));
                removeToken();
                //return new DeclVarListHandler(tokens, declaracaoVariavel).handle();
                if (new DeclVarListHandler(tokens, declaracaoVariavel).handle()) {
                    this.noPai.add(declaracaoVariavel);
                    return true;
                } else {
                    return false;
                }
            } else {
                setCodError(4);
                return false;
            }
        } else {
            setCodError(3);
            return false;
        }
 
    }
    
}
