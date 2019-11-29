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
public class DeclVarList2Handler extends AbstractHandler {

    public DeclVarList2Handler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode declVarList2 = new DefaultMutableTreeNode("DeclVarList2");
        if (nextToken()) {
            if (currentToken == Token.PC_CONSTANTE || currentToken == Token.PC_FUNCAO || currentToken == Token.PC_PROCEDIMENTO || currentToken == Token.PC_INICIO) {
                return true;
            }
            if (new DeclVarHandler(tokens, declVarList2).handle() && new DeclVarList2Handler(tokens, declVarList2).handle()) {
                this.noPai.add(declVarList2);
                return true;
            } else {
                return false;
            }
        } else {
            setCodError(11);
            return false;
        }
    }
    
}
