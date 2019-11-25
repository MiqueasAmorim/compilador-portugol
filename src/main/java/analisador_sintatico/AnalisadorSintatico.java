/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico;

import analisador_sintatico.handlers.BlocoHandler;
import analisador_sintatico.handlers.DeclaracaoConstanteHandler;
import analisador_sintatico.handlers.DeclProcHandler;
import analisador_sintatico.handlers.DeclProcedimentoHandler;
import analisador_sintatico.handlers.InstHandler;
import analisador_sintatico.handlers.ParametrosHandler;
import analisador_sintatico.handlers.ProgramaHandler;
import model.ErrorModel;
import model.TokenModel;
import java.util.ArrayList;

/**
 *
 * @author Miqueas
 */
public class AnalisadorSintatico {

    private ArrayList<TokenModel> tokens;
    private ProgramaHandler handler;
    private ArrayList<ErrorModel> errorList;

    public AnalisadorSintatico(ArrayList<TokenModel> tokens) {
        this.tokens = tokens;
        this.handler = new ProgramaHandler(tokens);
        this.errorList = new ArrayList();
    }

    public boolean run() {
        ErrorModel.getInstance().limpar();
        return handler.handle();
    }

}
