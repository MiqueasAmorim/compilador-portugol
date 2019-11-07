/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import model.ErrorModel;

/**
 *
 * @author Miqueas
 */
public class RetornaErro {

    // Aqui ele retorna um erro em formato de string de acordo com
    // o seu identificador, ele tambem recebe um token caso ele
    // precise pegar alguma informação, por exemplo, este primeiro
    // "case" retorna a mensagem de erro "Operador inválido + nome do token".
    // A medida que novos erros forem aparecendo, a gente seta um novo "case" pra ele.
    public static String getError(ErrorModel error) {

        switch (error.getCodigo()) {
            case 1:
                return "Esperado \"tipo\", mas \"fim de arquivo\" encontrado.";
            case 2:
                return "Esperado \"tipo\", mas \"" + error.getLexema() + "\" encontrado.";
            case 3:
                return "Esperado \"VARIAVEL\", mas \"fim de arquivo\" encontrado.";
            case 4:
                return "Esperado \"VARIAVEL\", mas \"" + error.getLexema() + "\" encontrado.";
            case 5:
                return "Esperado \"identificador\", mas \"fim de arquivo\" encontrado.";
            case 6:
                return "Esperado \"identificador\", mas \"" + error.getLexema() + "\" encontrado.";
            case 7:
                return "Esperado \":\", mas \"fim de arquivo\" encontrado.";
            case 8:
                return "Esperado \":\", mas \"" + error.getLexema() + "\" encontrado.";
            case 9:
                return "Esperado \";\", mas \"fim de arquivo\" encontrado.";
            case 10:
                return "Esperado \";\", mas \"" + error.getLexema() + "\" encontrado.";
            case 11:
                return "Esperado \"INICIO\", mas \"fim de arquivo\" encontrado.";
            case 12:
                return "Esperado \"OU\" ou \"E\", mas \"fim de arquivo\" encontrado.";
            case 13:
                return "Esperado \"OU\" ou \"E\", mas \"" + error.getLexema() + "\" encontrado.";
            case 14:
                return "Esperado \"número\" ou \"string\", mas \"" + error.getLexema() + "\" encontrado.";
            case 15:
                return "Esperado \"número\" ou \"string\", mas \"fim de arquivo\" encontrado.";
            case 16:
                return "Instrução mal formada";
            case 17:
                return "Esperado \"instrução\", mas \"fim de arquivo\" encontrado.";
            case 18:
                return "Esperado \"INICIO\", mas \"fim de arquivo\" encontrado.";
            case 19:
                return "Esperado \"INICIO\", mas \"" + error.getLexema() + "\" encontrado.";
            case 20:
                return "Esperado \"FIM\", mas \"fim de arquivo\" encontrado.";
            case 21:
                return "Esperado \"FIM\", mas \"" + error.getLexema() + "\" encontrado.";
            case 22:
                return "Esperando \";\", mas \"fim de arquivo\" encontrado.";
            case 23:
                return "Esperando \"PROGRAMA\", mas \"fim de arquivo\" encontrado.";
            case 24:
                return "Esperada \"PROGRAMA\", mas \"" + error.getLexema() + "\" encontrado.";
            case 25:
                return "Esperando \".\", mas \"fim de arquivo\" encontrado.";
            case 26:
                return "Esperada \".\", mas \"" + error.getLexema() + "\" encontrado.";
            case 200:
                return "Esperado \"CONSTANTE\", mas \"fim de arquivo\" encontrado.";
            case 201:
                return "Esperado \"CONSTANTE\", mas \"" + error.getLexema() + "\" encontrado.";
            case 203:
                return "Esperado \"=\",mas \"" + error.getLexema() + "\" encontrado.";
            case 204:
                return "Esperado \"valor\",mas \"" + error.getLexema() + "\" encontrado.";
            case 205:
                return "Esperado \"= ou :\",mas \"" + error.getLexema() + "\" encontrado.";
            case 206:
                return "Esperado \"=\",mas \"fim de arquivo\" encontrado.";
            case 207:
                return "Esperado um \"identificador\",mas \"" + error.getLexema() + "\" encontrado.";
            case 208:
                return "Esperado um \"valor\",mas \"fim de arquivo\" encontrado.";
            case 209:
                return "Esperado uma \"string\",mas \"fim de arquivo\" encontrado.";
            case 210:
                return "Esperado um \"caractere\",mas \"" + error.getLexema() + "\" encontrado.";
            case 100:
                return "Esperado \"PROCEDIMENTO\" ou \"FUNÇÃO\", mas mas \"fim de arquivo\" encontrado.";
            case 101:
                return "Esperado \"PROCEDIMENTO\", mas \"fim de arquivo\" encontrado.";
            case 102:
                return "Esperado \"(\", mas \"fim de arquivo\" encontrado.";
            case 103:
                return "Esperado \"(\", mas \"" + error.getLexema() + "\" encontrado.";
            case 104:
                return "Esperado \")\", mas \"" + error.getLexema() + "\" encontrado.";
            case 105:
                return "Esperado um parametro, mas \"" + error.getLexema() + "\" encontrado.";
            case 106:
                return "Esperado \"FUNÇÃO\", mas \"" + error.getLexema() + "\" encontrado.";
                
            default:
                return "Erro não tratado";

        }

    }

}
