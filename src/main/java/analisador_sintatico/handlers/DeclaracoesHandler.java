/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import static analisador_sintatico.handlers.AbstractHandler.tokens;
import java.util.ArrayList;
import model.TokenModel;

/**
 *
 * @author Miqueas
 */
public class DeclaracoesHandler extends AbstractHandler {

    public DeclaracoesHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        return (new DeclConstanteHandler(tokens).handle() && new DeclaracaoVariavelHandler(tokens).handle());
    }
    
}
