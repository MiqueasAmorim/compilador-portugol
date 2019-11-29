/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import analisador_sintatico.handlers.AbstractHandler;
import analisador_sintatico.handlers.UnarioHandler;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.Token;
import model.TokenModel;

/**
 *
 * @author andrei
 */
public class ValorHandler extends AbstractHandler {

    public ValorHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode valor = new DefaultMutableTreeNode("Valor");
        if (new UnarioHandler(tokens, valor).handle()) {
            this.noPai.add(valor);
            return true;
        } else {
            nextToken();
            if (currentToken == Token.STRING || currentToken == Token.PC_FALSO || currentToken == Token.PC_VERDADEIRO) {
                removeToken();
                return true;
            } else {
                setCodError(204);
                return false;
            }
        }
       // return true;
    }
}
