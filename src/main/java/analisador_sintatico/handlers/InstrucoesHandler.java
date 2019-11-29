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
public class InstrucoesHandler extends AbstractHandler {

    public InstrucoesHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode instrucoes = new DefaultMutableTreeNode("Instrucoes");
        nextToken();
        if (currentToken == Token.PC_FIM) {
            return true;
        }
        if (new InstHandler(tokens, instrucoes).handle() && new InstrucoesHandler(tokens, instrucoes).handle()) {
            noPai.add(instrucoes);
            return true;
        } 
        return false;
    }

}
