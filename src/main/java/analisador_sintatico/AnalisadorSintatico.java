/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico;

import analisador_sintatico.handlers.ProgramaHandler;
import model.ErrorModel;
import model.TokenModel;
import java.util.ArrayList;
import java.util.TreeSet;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Miqueas
 */
public class AnalisadorSintatico {

    private ArrayList<TokenModel> tokens;
    private ProgramaHandler handler;
    private ArrayList<ErrorModel> errorList;
    private DefaultMutableTreeNode arvoreSintatica;

    public AnalisadorSintatico(ArrayList<TokenModel> tokens) {
        this.tokens = tokens;
        this.arvoreSintatica = new DefaultMutableTreeNode("Programa");
        this.handler = new ProgramaHandler(tokens, arvoreSintatica);
        this.errorList = new ArrayList();
    }

    public boolean run() {
        ErrorModel.getInstance().limpar();
        return handler.handle();
    }

    public DefaultMutableTreeNode getArvoreSintatica() {
        return arvoreSintatica;
    }

}
