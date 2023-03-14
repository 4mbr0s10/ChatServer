import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class HiloServer extends Thread {
    private String nombre;
    private DataInputStream dis;
    private DataOutputStream dos;

    private JTextArea textoRecibir;

    public HiloServer(DataInputStream dis, DataOutputStream dos, String nombre, JTextArea textoRecibir) {
        this.dis = dis;

        this.dos = dos;
        this.nombre = nombre;
        this.textoRecibir = textoRecibir;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            try {

                String msg = dis.readUTF();
                System.out.println(msg);
                textoRecibir.append("Servidor: " + msg + "\n");


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
