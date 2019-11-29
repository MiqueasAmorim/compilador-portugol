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
public class FatorHandler extends AbstractHandler {

    public FatorHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode fator = new DefaultMutableTreeNode("Fator");
        nextToken();
        if (currentToken == Token.ABRE_PARENTESES) {
            fator.add(new DefaultMutableTreeNode("("));
            removeToken();
            if (new ExprHandler(tokens, fator).handle()) {
                if (currentToken == Token.FECHA_PARENTESES) {
                    fator.add(new DefaultMutableTreeNode(")"));
                    removeToken();
                    this.noPai.add(fator);
                    return true;
                }
            }
        }
        if (new IdentificadorHandler(tokens, fator).handle()) {
            this.noPai.add(fator);
            return true;
        }
        if (currentToken == null) {
            setCodError(15); // Esperado um identificador, número ou string, mas fim de arquivo encontrado
            return false;
        }
        if (currentToken == Token.INTEIRO || currentToken == Token.REAL || currentToken == Token.CARACTERE || currentToken == Token.STRING) {
            fator.add(new DefaultMutableTreeNode(getCurrentLexema()));
            removeToken();
            this.noPai.add(fator);
            return true;
        } else {
            setCodError(14); // Esperado um identificador, número ou string, mas outra coisa encontrada
            return false;
        }

    }

}
