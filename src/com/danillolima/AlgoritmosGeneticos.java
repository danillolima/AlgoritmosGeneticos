/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danillolima;

import com.danillolima.populacao.Populacao;

/**
 *
 * @author Danillo Lima <danillo at alunos.utfpr.edu.br>
 */
public class AlgoritmosGeneticos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Gerar população
        Populacao pop = new Populacao(500,400);
        pop.inicializa();
        for(int i=0; i<pop.getQtd_geracoes(); i++){
            //System.out.println("Geração: "+ i+1);
            pop.avaliaTodos();
          
            pop.novaGeracao();
           // pop.imprimeTodos();
             System.out.println("Melhor individuo população: " + pop.avaliacaoMelhorIndividuo().getAvaliacao());
            //  System.out.println("Pior individuo população: " + pop.avaliacaoPiorIndividuo().getAvaliacao());
           // pop.novaGeracao();
        }       
    }
}
