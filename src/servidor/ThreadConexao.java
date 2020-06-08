package servidor;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class ThreadConexao implements Runnable {

    /**
     * Construtor
     */
    
     Thread threadLeitura;
     TelaInicial tela;
    public ThreadConexao(TelaInicial tl) {
        
         Thread threadLeitura = null;
         this.tela = tl;
         tela.getTxtMensagem().setText("Servidor iniciado com sucesso!");
         
    }

    @Override
    public void run() {
        esperandoCoenxao();
    }

    /**
     * Esperando por conexões de dispositivos
     */
    private void esperandoCoenxao() {
        // recebe o objeto do dispositivo bluetooth local
        LocalDevice local = null;

        StreamConnectionNotifier notifier;
        StreamConnection connection = null;

        // configura o servidor para esperar a conexao
        try {
            local = LocalDevice.getLocalDevice();
            local.setDiscoverable(DiscoveryAgent.GIAC);

            UUID uuid = new UUID("04c6093b00001000800000805f9b34fb", false);
            System.out.println(uuid.toString());

            String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier) Connector.open(url);
        } catch (BluetoothStateException e) {
            System.out.println("Bluetooth não está ligado.");
            
            //tela.getEpMensagem().setText(tela.getEpMensagem().getText() + "Bluetooth não está ligado.");
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        boolean sair = true;
        // esperando por conexao
        while (sair) {
            try {
                System.out.println("esperando conexao...");
                 tela.getTxtMensagem().setText(tela.getTxtMensagem().getText()+"\nEsperando conexão do dispositivo...");
                connection = notifier.acceptAndOpen();

                threadLeitura = new Thread(new ThreadLeitura(connection, tela));
                threadLeitura.start();

            } catch (Exception e) {
                e.printStackTrace();
                sair = false;
            }
        }
    }
}
