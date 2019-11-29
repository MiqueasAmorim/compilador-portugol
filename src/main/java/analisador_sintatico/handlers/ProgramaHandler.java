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
public class ProgramaHandler extends AbstractHandler {
    
    public ProgramaHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        //DefaultMutableTreeNode programa = new DefaultMutableTreeNode("programa");
        if (!nextToken()) {
            setCodError(23);
            return false;
        }
        if (!(currentToken == Token.PC_PROGRAMA)) {
            setCodError(24);
            return false;
        }
        this.noPai.add(new DefaultMutableTreeNode("programa"));
        removeToken();
        if (!(new IdentificadorHandler(tokens, noPai).handle())) {
            return false;
        }
        if (!nextToken()) {
            setCodError(9);
            return false;
        }
        if (!(currentToken == Token.PONTO_VIRGULA)) {
            setCodError(10);
            return false;
        }
        this.noPai.add(new DefaultMutableTreeNode(";"));
        removeToken();
        if (!(new DeclaracoesHandler(tokens, noPai).handle())) {
            return false;
        }
        if (!nextToken()) {
            setCodError(18);
            return false;
        }
        if (!(currentToken == Token.PC_INICIO)) {
            setCodError(19);
            return false;
        }
        this.noPai.add(new DefaultMutableTreeNode("inicio"));
        removeToken();
        if (!(new InstrucoesHandler(tokens, noPai).handle())) {
            return false;
        }
        if (!nextToken()) {
            setCodError(20);
            return false;
        }
        if (!(currentToken == Token.PC_FIM)) {
            setCodError(21);
            return false;
        }
        this.noPai.add(new DefaultMutableTreeNode("fim"));
        removeToken();
        if (!nextToken()) {
            setCodError(25);
            return false;
        }
        if (!(currentToken == Token.PONTO)) {
            setCodError(26);
            return false;
        }
        this.noPai.add(new DefaultMutableTreeNode("."));
        removeToken();
        if (nextToken()) {
            setCodError(41);
            return false;
        }
        return true;
    }
    
}
