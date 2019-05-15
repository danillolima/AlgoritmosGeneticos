/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danillolima.cromossomo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Danillo Lima <danillo at alunos.utfpr.edu.br>
 */
public class Cromossomo {
    
    ArrayList<Double> valores;
    int n;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
    private double avaliacao;

    public Cromossomo(){
        valores = new ArrayList();
        n = 30;
        inicializa();
    }
    private void inicializa(){
        Random random = new Random();
        double aux;
        for(int i = 0; i < n; i++ ){
            aux = random.nextFloat() * (-100 - 100) + 100;
            valores.add(aux);
        }
    }
    public double calculaAvaliacao(){
        int i;
        double somatorio = 0;    
        for(i = 0; i < n; i++){
            somatorio += Math.pow(this.valores.get(i), 2);
        }
        //System.out.println(somatorio);
        
        this.setAvaliacao(Math.abs(somatorio));
        return somatorio;
    }
    
    public Cromossomo singlePoint(Cromossomo paiA, Cromossomo paiB){
        
        Cromossomo descendente = new Cromossomo();
        int pontoCorte, i, size = paiA.getValores().size();
        Random r = new Random();
        //Pego o ponto de corte randomicamente o limite do rand é inclusivo por isso o n-1
  
        pontoCorte = r.nextInt(n);
        
       // System.out.println("ponto de corte: " + pontoCorte);
        for(i = 0; i < size; i++){
            
            if(i >= pontoCorte){
                descendente.getValores().set(i, paiA.getValores().get(i));
            }
            else{
                descendente.getValores().set(i, paiA.getValores().get(i));
            }
        }
        return descendente;
    }
    public Cromossomo crossoverFlat(Cromossomo outro){
        Cromossomo filho;
        filho = new Cromossomo();
        double numeroIntervalo,upper,lower;
   
        for(int i = 0; i<this.n; i++){
            if(this.getValores().get(i)>outro.getValores().get(i)){
                lower = outro.getValores().get(i);
                upper = this.getValores().get(i);
            }else{
                upper = outro.getValores().get(i);
                lower = this.getValores().get(i);
            }
      
            numeroIntervalo = Math.random()* (upper - lower) + lower;
            filho.getValores().set(i, numeroIntervalo);
        }

        return filho;
    }
    public void mutacao(){
        int pontoAleatorio = 0;
         Random r = new Random();
        //Pego o ponto de corte randomicamente o limite do rand é inclusivo por isso o n-1
    //    this.getValores().get(pontoAleatorio) 
               
        pontoAleatorio = r.nextInt(n);
        if(Math.random()<=0.05)
            this.valores.set(pontoAleatorio, this.valores.get(pontoAleatorio)*(-Math.random()));
        
    }
    public ArrayList<Double> getValores() {
        return valores;
    }

    public void setValores(ArrayList<Double> valores) {
        this.valores = valores;
    }
    
    public void imprime(){
        System.out.println("Valores do Cromossomo: ");
        for(int i=0; i<valores.size();i++){  
            System.out.println(valores.get(i)+ " ");
        }
        System.out.println("-----------------------");
    }
   
}
