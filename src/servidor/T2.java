package servidor;

import java.awt.Robot;

/**
 *
 * @author Mauricio
 */
public class T2 implements Runnable {

    Robot r;
    ThreadLeitura lt;
    int sensibilidade;
    public T2(ThreadLeitura con) {
        lt = con;
        sensibilidade = 25;
        try {
            r = new Robot();
        } catch (Exception e) {
            System.out.println("Falha robo T1");
        }

        r.setAutoDelay(sensibilidade);
    }

    @Override
    public void run() {
        boolean cima = false;
        boolean cimaGeral = false;
        boolean baixo = false;
        boolean baixoGeral = false;
        boolean poder1 = false;
        boolean poder1Geral = false;
        while (true) {
            //System.out.println("2222222222");

            for (int i = 1; i < lt.comandos.length; i++) {

                if (lt.comandos[2] == 0 && baixoGeral) {
                    r.keyRelease(lt.comandosPC[2]);
                }
                
                if (lt.comandos[1] == 0 && cimaGeral) {
                    r.keyRelease(lt.comandosPC[1]);
                }
                

                if (i == 2) {
                    if (lt.comandos[i] > 0) {
                        baixo = true;
                        baixoGeral = true;
                        if (cima) {
                            r.keyRelease(lt.comandosPC[1]);
                            cima = false;
                        }
                        r.keyPress(lt.comandosPC[i]);
                       // r.setAutoDelay(sensibilidade);
                    } else {
                         r.keyRelease(lt.comandosPC[i]);
                    }
                }

                if (i == 1) {
                    if (lt.comandos[i] > 0) {
                        cima = true;
                        cimaGeral = true;
                        if (baixo) {
                            r.keyRelease(lt.comandosPC[2]);
                            baixo = false;
                        }
                        r.keyPress(lt.comandosPC[i]);
                        //r.setAutoDelay(sensibilidade);
                    } else {
                        r.keyRelease(lt.comandosPC[i]);
                    }
                }
                
                 if (i == 5) {
                    if (lt.comandos[i] > 0) {      
                        r.keyPress(lt.comandosPC[i]);
                       
                    } else {
                        r.keyRelease(lt.comandosPC[i]);
                    }
                }

                 if (i == 6) {
                    if (lt.comandos[i] > 0) {      
                        r.keyPress(lt.comandosPC[i]);
                       
                    } else {
                        r.keyRelease(lt.comandosPC[i]);
                    }
                }
                 
                 if (i == 7) {
                    if (lt.comandos[i] > 0) {      
                        r.keyPress(lt.comandosPC[i]);
                       
                    } else {
                        r.keyRelease(lt.comandosPC[i]);
                    }
                }
                 
                 if (i == 8) {
                    if (lt.comandos[i] > 0) {      
                        r.keyPress(lt.comandosPC[i]);
                       
                    } else {
                        r.keyRelease(lt.comandosPC[i]);
                    }
                }
                 if (i == 9) {
                    if (lt.comandos[i] > 0) {      
                        r.keyPress(lt.comandosPC[i]);
                       
                    } else {
                        r.keyRelease(lt.comandosPC[i]);
                    }
                }
                 
                 
            }
        }
    }

}
