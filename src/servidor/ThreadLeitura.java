package servidor;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.microedition.io.StreamConnection;

public class ThreadLeitura implements Runnable {

    TelaInicial tela;
    private StreamConnection mConnection;
    int comandosPC[] = {
        KeyEvent.VK_0,
        KeyEvent.VK_UP,
        KeyEvent.VK_DOWN,
        KeyEvent.VK_LEFT,
        KeyEvent.VK_RIGHT,
        KeyEvent.VK_1,
        KeyEvent.VK_2,
        KeyEvent.VK_3,
        KeyEvent.VK_4};
    int comandos[];
    Robot robo;

    public ThreadLeitura(StreamConnection connection, TelaInicial tl) {

        this.tela = tl;
        comandos = new int[]{0,0,0,0,0,0,0,0};
        mConnection = connection;

        Thread t1 = new Thread(new T3(ThreadLeitura.this));
        t1.start();
        Thread t2 = new Thread(new T2(ThreadLeitura.this));
        t2.start();

        try {
            robo = new Robot();
        } catch (Exception e) {
            System.out.println("Erro criação do robo");
        }

    }

    @Override
    public void run() {

        //prepare to receive data
        //InputStream inputStream = mConnection.openInputStream();
        // DataInputStream  dis = mConnection.openDataInputStream();
        System.out.println("esperando entrada de dados");
        tela.getTxtMensagem().setText(tela.getTxtMensagem().getText() + "\nEsperando entrada de dados...");
        InputStream inputStream = null;
        byte[] buffer = new byte[1024];  // buffer armazena mensagem recebida
        int bytes; // bytes retornados do read()
        try {
            inputStream = mConnection.openInputStream();
        } catch (Exception e) {
            System.out.println("Erro abrir");
        }
        
        boolean sair = true;
        
        while (sair) {

            try {
                //receber bytes
                bytes = inputStream.read(buffer);
                String msg = new String(buffer, 0, bytes);
                //converter recebimento em array
                for (int n = 0; n < msg.length(); n++) {
                    if (n < comandos.length) {
                        comandos[n] = Character.getNumericValue(msg.charAt(n));
                    } else {
                        //caso a mensagem seja maior que o array de comandos me
                        //mostrar o que está vindo a mais pra ver se é importante
                        System.out.println(msg);
                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
                sair = false;
            }

        }

    }//run


}
