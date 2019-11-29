/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import static analisador_sintatico.handlers.AbstractHandler.tokens;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.Token;
import model.TokenModel;

/**
 *
 * @author Miqueas
 */
public class DeclaracoesHandler extends AbstractHandler {

    public DeclaracoesHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        nextToken();
        DefaultMutableTreeNode declaracoes = new DefaultMutableTreeNode("Declaracoes");
        if (currentToken == Token.PC_CONSTANTE) {
            if (new DeclaracaoConstanteHandler(tokens, declaracoes).handle()) {
                nextToken();
                if (currentToken == Token.PC_VARIAVEL) {
                    if (new DeclaracaoVariavelHandler(tokens, declaracoes).handle()) {
                        nextToken();
                        if (currentToken == Token.PC_PROCEDIMENTO || currentToken == Token.PC_FUNCAO) {
                            if (new DeclProcedimentoHandler(tokens, declaracoes).handle()) {
                                this.noPai.add(declaracoes);
                                return true;
                            }
                            return false;
                        }
                        this.noPai.add(declaracoes);
                        return true;
                    } else {
                        return false;
                    }
                }
                this.noPai.add(declaracoes);
                if (currentToken == Token.PC_PROCEDIMENTO || currentToken == Token.PC_FUNCAO) {
                    if (new DeclProcedimentoHandler(tokens, declaracoes).handle()) {
                        this.noPai.add(declaracoes);
                        return true;
                    }
                    return false;
                }
                return true;
            } else {
                return false;
            }
        }
        if (currentToken == Token.PC_VARIAVEL) {
            if (new DeclaracaoVariavelHandler(tokens, declaracoes).handle()) {
                nextToken();
                if (currentToken == Token.PC_PROCEDIMENTO || currentToken == Token.PC_FUNCAO) {
                    if (new DeclProcedimentoHandler(tokens, declaracoes).handle()) {
                        this.noPai.add(declaracoes);
                        return true;
                    }
                    return false;
                }
                this.noPai.add(declaracoes);
                return true;
            } else {
                return false;
            }
        }
        if (currentToken == Token.PC_PROCEDIMENTO || currentToken == Token.PC_FUNCAO) {
            if (new DeclProcedimentoHandler(tokens, declaracoes).handle()) {
                this.noPai.add(declaracoes);
                return true;
            }
            return false;
        }

        return true;
    }

}
