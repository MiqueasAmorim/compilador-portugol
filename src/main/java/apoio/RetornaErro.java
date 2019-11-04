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
                return "Esperada \"número\" ou \"string\", mas \"" + error.getLexema() + "\" encontrado.";
            case 15:
                return "Esperado \"número\" ou \"string\", mas \"fim de arquivo\" encontrado.";
            case 16:
                return "Instrução mal formada";
            default:
                return "Erro não tratado";
        }

    }

}
