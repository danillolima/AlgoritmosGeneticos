/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danillolima.populacao;

import com.danillolima.cromossomo.Cromossomo;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Danillo Lima <danillo at alunos.utfpr.edu.br>
 */
public class Populacao {
    private double avaliacao;
    private int tamanho;
    private int qtd_geracoes;
    private ArrayList<Cromossomo> populacao;
    private ArrayList<Cromossomo> elite; 
    
    public Populacao(){
        //seta 
        tamanho = 100;
        qtd_geracoes = 400;
        this.populacao = new ArrayList();
    }
    public Populacao(int tamanho, int qtd_geracoes) {
        this.tamanho = tamanho;
        this.qtd_geracoes = qtd_geracoes;
        this.populacao = new ArrayList();
    }
    public void inicializa(){
        int i;
        for (i = 0; i<tamanho; i++){
           populacao.add(new Cromossomo());
           // System.out.println(populacao.get(i).getValores().get(1));
        }
    }
    public void avaliaTodos(){
        int i;
        for(i=0;i<this.populacao.size(); i++){
            avaliacao += populacao.get(i).calculaAvaliacao();
            //System.out.println("teste: " + populacao.get(i).getAvaliacao() );
        }
    }
    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getQtd_geracoes() {
        return qtd_geracoes;
    }

    public void setQtd_geracoes(int qtd_geracoes) {
        this.qtd_geracoes = qtd_geracoes;
    }

    public ArrayList<Cromossomo> getPopulacao() {
        return populacao;
    }

    public void setPopulacao(ArrayList<Cromossomo> populacao) {
        this.populacao = populacao;
    }
    public void novaGeracao(){
        ArrayList<Cromossomo> nova_geracao;
        System.out.println("Avaliação dessa população: "+ this.avaliacao/this.tamanho);
        nova_geracao = new ArrayList();
        Cromossomo paiA, paiB, filho;
	int i, j, indA, indB;
	for(i=0; i<this.populacao.size(); ++i) {
            
            indA = roleta();
        
               
                   indB = roleta();
                 
		paiA = populacao.get(indA);
                //paiA.imprime();
		paiB = populacao.get(indB);		
                //paiB.imprime();
                /*if(i%2==0)
                filho = paiA.singlePoint(paiA, paiB);
                else*/
                filho = paiA.crossoverFlat(paiB);
                //filho.imprime();
		//filho = paiA;
                //filho.mutacao(chance_mutacao);
                filho.calculaAvaliacao();
                //filho.mutacao();
		nova_geracao.add(filho);
	}
        this.populacao.removeAll(populacao);
        this.populacao.addAll(nova_geracao);
    }

    private int roleta(){
        
        ArrayList<Double> roleta;
        roleta = new ArrayList();
        double proporcao, maior=0, menor;
       
        for(int i = 0; i<populacao.size(); i++){
           // System.out.println("teste"+populacao.get(i).getAvaliacao());
            proporcao = (populacao.get(i).getAvaliacao()/this.avaliacao) * 100; 
            roleta.add(i,proporcao);
            if(maior<proporcao)
                maior = proporcao;
            //System.out.println("Proporção: " + proporcao);
           // System.out.println("Teste: " + roleta.get(i));
           // soma += roleta.get(i); 
        }
      //  System.out.println("soma: "+soma);
        
        Boolean removido[];
        removido = new Boolean[roleta.size()];
        for(int i=0; i<removido.length; i++ ){
            removido[i]=false;  
        }
        do{
            for(int i = 0;i<roleta.size(); i++){
                if((roleta.get(i)>Math.random()*maior) && contaNaoRemovidos(removido)>1 && removido[i] == false ){
                    removido[i] = true;
                }
            }
        }while (contaNaoRemovidos(removido)>1);
        
        if(contaNaoRemovidos(removido) == 1){
            for(int i = 0; i<removido.length; i++){
                if(removido[i] == false) 
                    return i;
            }
        }
        return -1;
    }
    public int contaNaoRemovidos(Boolean temp[]){
        int a=0;
        for(int i=0; i<temp.length; i++ ){
            if(temp[i]==false)
              a++;  
        }
        return a;
    }
    
    public Cromossomo avaliacaoMelhorIndividuo(){
        Cromossomo melhor = populacao.get(0);
        for(int i = 0; i<populacao.size();i++){
            if(i > 0){
             //   System.out.println("aaa" + populacao.get(i).getAvaliacao());
                if(melhor.getAvaliacao() > populacao.get(i).getAvaliacao())
                    melhor = populacao.get(i);
            }
        }
        return melhor;
    } 
    public Cromossomo avaliacaoPiorIndividuo(){
        Cromossomo pior = populacao.get(0);
        for(int i = 0; i<populacao.size();i++){
            if(i > 0){
             //   System.out.println("aaa" + populacao.get(i).getAvaliacao());
                if(pior.getAvaliacao() < populacao.get(i).getAvaliacao())
                    pior = populacao.get(i);
            }
        }
        return pior;
    }
    public void imprimeTodos(){
        for(int i = 0; i<populacao.size();i++){
            System.out.println("Individuo 1:" + populacao.get(i).calculaAvaliacao());
        }
    }
}
