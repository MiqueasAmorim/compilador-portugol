/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Miqueas
 */
public enum Token {  
        
    // Palavras reservadas
    PC_PROGRAMA, PC_INICIO, PC_FIM, PC_VARIAVEL, PC_CONSTANTE, PC_SE, 
    PC_ENTAO, PC_SENAO, PC_ENQUANTO, PC_FACA, PC_REPITA, PC_PARA, PC_ATE,
    PC_INTEIRO, PC_REAL, PC_STRING, PC_CHAR, PC_BOOLEANO,
    PC_LEIA, PC_ESCREVA, 
    PC_VERDADEIRO, PC_FALSO,
    PC_VETOR, PC_DE,
    PC_FUNCAO, PC_PROCEDIMENTO, 
    PC_CASO, PC_PARE, PC_CONTINUA,
    PC_RESTO, PC_QUOCIENTE,
    
    
    // Pontuacao
    PONTO, VIRGULA, DOIS_PONTOS, PONTO_VIRGULA, ASPAS_SIMPLES, ABRE_PARENTESES, FECHA_PARENTESES, ABRE_COLCHETES, FECHA_COLCHETES,
    
    // Operadores relacionais
    OP_IGUAL, OP_DIFERENTE, OP_MAIOR, OP_MENOR, OP_MAIOR_IGUAL, OP_MENOR_IGUAL,
    
    // Operadores aritmeticos
    OP_ADICAO, OP_SUBTRACAO, OP_MULTIPLICACAO, OP_DIVISAO,
    
    // Operadores de atribuicao
    OP_ATRIBUICAO,
    
    // Operadores logicos
    OP_E, OP_OU, OP_NAO,
    
    // Padroes
    IDENTIFICADOR, INTEIRO, REAL, CARACTERE, STRING, COMENTARIO,
        
    // Inclui todos caracteres invalidos e lexemas malformados
    ERRO
    
}
