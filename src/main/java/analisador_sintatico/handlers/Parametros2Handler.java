/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import model.TokenModel;

/**
 *
 * @author Miqueas
 */
public class Parametros2Handler extends AbstractHandler {

    public Parametros2Handler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (new ExprHandler(tokens).handle() && new ContParametrosHandler(tokens).handle()) {
            return true;
        }
        return true;
    }
    
}
