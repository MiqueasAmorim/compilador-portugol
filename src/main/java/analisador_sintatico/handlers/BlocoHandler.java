/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import model.Token;
import model.TokenModel;

/**
 *
 * @author Miqueas
 */
public class BlocoHandler extends AbstractHandler {

    public BlocoHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (nextToken()) {
            if (currentToken == Token.PC_INICIO) {
                removeToken();
                if (new InstrucoesHandler(tokens).handle()) {
                    if (nextToken()) {
                        if (currentToken == Token.PC_FIM) {
                            removeToken();
                            if (nextToken()) {
                                if (currentToken == Token.PONTO_VIRGULA) {
                                    removeToken();
                                    return true;
                                } else {
                                    setCodError(10);// Esperado ";", mas encontrou outra coisa
                                }
                            } else {
                                setCodError(9);// Esperado ";", mas encontrou "fim de arquivo"
                            }
                        } else {
                            setCodError(21);// Esperado "FIM", mas encontrou outra coisa
                        }
                    } else {
                        setCodError(20);// Esperado "FIM", mas "fim de arquivo" encontrado
                    }
                }
            } else {
                setCodError(19);// Esperado "INICIO", mas outra coisa encontrada
            }
        } else {          
            setCodError(18);// Esperado "INICIO", mas encontrou "fim de arquivo"
        }
        return false;
    }

}
