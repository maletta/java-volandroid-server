/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio
 */
public class T3 implements Runnable {

    Robot r;
    ThreadLeitura lt;
    int sensibilidade;
    int fator[];
    int direcao[];
    int pontoMorto;

    public T3(ThreadLeitura con) {
        lt = con;
        sensibilidade = 1100;
        //0, 1000, 1000, 1000, 600, 600, 600, 600, 100, 100
        fator = new int[]{0, 1000, 100, 100, 100, 600, 600, 600, 1000, 1000};
        direcao = new int[]{3,4};
        
        pontoMorto = 1;
        try {
            r = new Robot();
        } catch (Exception e) {
            System.out.println("Falha robo T1");
        }

        r.setAutoDelay(100);
    }
    
    
    
    @Override
    public void run() {

        int f=0;
        while (true) {
       
           //System.out.println(lt.comandos[0]);
                      
           for(int i=0; i<2;i++){          
            
             //verifica se o botao deve ser apertado
            if(lt.comandos[direcao[i]]>0){
                
                //Aperta a tecla
                r.keyPress(lt.comandosPC[direcao[i]]);
                
                //calcula sensibilidade
                f = 20;//sensibilidade - fator[lt.comandos[direcao[i]]];
                                
                //temp de espera
                try {
                        Thread.sleep(f);
                } catch (InterruptedException ex) {
                        System.out.println("Não foi possível botar pra dormir");
                }//try             
                
                //Solta a tecla
                r.keyRelease(lt.comandosPC[direcao[i]]);
                
                
            }//if
            
           }//For
        }//while
        
        
    }//run

}//T3111
