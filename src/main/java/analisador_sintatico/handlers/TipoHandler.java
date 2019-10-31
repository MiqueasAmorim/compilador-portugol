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
public class TipoHandler extends AbstractHandler {

    public TipoHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (nextToken()) { // Se tiver um próximo token
            if (currentToken == Token.PC_INTEIRO || currentToken == Token.PC_REAL || currentToken == Token.PC_CHAR || currentToken == Token.PC_STRING || currentToken == Token.PC_BOOLEANO) {    
                removeToken();
            } else {
                this.errorCode = 2; // Token errado
                return false;
            }   
        } else {
            this.errorCode = 1; // Esperado token Tipo, mas nenhum token foi encontrado
            return false;
        }
        return true;
    }
    
}
