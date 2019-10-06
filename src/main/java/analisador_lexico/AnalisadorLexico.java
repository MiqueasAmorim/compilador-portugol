/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_lexico;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import model.TokenModel;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Token;

/**
 *
 * @author Miqueas
 */

public class AnalisadorLexico {
    
    ArrayList<TokenModel> tokens;
    ArrayList<TokenModel> erros;
    
    public AnalisadorLexico() throws FileNotFoundException, IOException {

        tokens = new ArrayList<>();
        erros = new ArrayList<>();
        
        //String rootPath = Paths.get("").toAbsolutePath(). toString();
        //String subPath = "/src/main/java/analisador_lexico/";

        //String sourceCode = rootPath + subPath + "programa.ptl";
 
    }
    
    public void analisar(String codigo) throws FileNotFoundException, IOException {
        //Lexer lexical = new Lexer(new FileReader(sourceCode));
        this.tokens.clear();
        this.erros.clear();
        File arquivoTemp = new File("semtitulo.ptl");
        FileWriter fw = new FileWriter(arquivoTemp);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(codigo);
        bw.flush();
        bw.close();
        fw.close();
        Lexer lexical = new Lexer(new FileReader(arquivoTemp));
        
        TokenModel token;

        while ((token = lexical.yylex()) != null) {
            if (!token.getNome().equals(Token.ERRO))
                this.tokens.add(token);
            else
                this.erros.add(token);
            //System.out.println("<" + token.getID() + ", " + token.getNome() + ", " + token.getLexema() + "> (" + token.getLinha() + " - " + token.getColuna() + ")");
        }
        
    }

    public ArrayList<TokenModel> getTokens() {
        return tokens;
    }

    public ArrayList<TokenModel> getErros() {
        return erros;
    }
    
}
