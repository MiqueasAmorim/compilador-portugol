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
 * @author Jefferson
 */
public class DeclProcedimentoHandler extends AbstractHandler {

    public DeclProcedimentoHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        nextToken();
        DefaultMutableTreeNode declProcedimento = new DefaultMutableTreeNode("DeclProcedimento");
        if (currentToken == Token.PC_INICIO) {
            return true;
        }

        if (new DeclProcHandler(tokens, declProcedimento).handle()) {
            if (new DeclProcedimentoHandler(tokens, declProcedimento).handle()) {
                this.noPai.add(declProcedimento);
                return true;
            }
            return false;
        }
        return false;
    }

}
