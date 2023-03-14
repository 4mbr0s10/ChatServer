
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    static DataInputStream dis ;
    static DataOutputStream dos;
    static Socket sc ;
    InetSocketAddress addr;
    private static JFrame marco;
    private static JPanel panel;
    private static JTextArea textoRecibir;
    private static JTextField textoEscribir;
    private static JButton botonEnviar;
    private static String nombre;
    //Generamos la clave con sus parámetros
    //keytool -genkey -keyalg RSA -keysize 2048 -validity 360 -alias mykey -keystore myKeyStore.jks
    //exportamos el certificado
    //keytool -export -alias mykey -keystore myKeyStore.jks -file mykey.cert
    //importamos en el store le certificado
    // keytool -import -file mykey.cert -alias mykey -keystore myTrustStore.jts
    public static void main(String[] args) throws IOException {
//ViewManager vm = new ViewManager();
distribucion();
        int c = 0;
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 5555);
        ServerSocket sv = new ServerSocket(5555);

        Socket sc;
        while(true) {

            sc = sv.accept();

            System.out.println("Se conecta");
             dis = new DataInputStream(sc.getInputStream());
             dos = new DataOutputStream(sc.getOutputStream());
             dos.writeUTF("INTRODUZCA SU NOMBRE: ");
             nombre = dis.readUTF();

            HiloServer hs = new HiloServer(dis, dos, nombre,textoRecibir);
            hs.start();

        }





















//       int puerto = 5555;
//        // Añadimos el protocolo SSL a la clase Security
//        Security.addProvider(new Provider());
//
//        System.setProperty("javax.net.ssl.keystore","myKeyStore.jks");
//
//        System.setProperty("javax.net.ssl.keyStorePassword","abc123.");
//
//        System.setProperty("javax.net.debug","all");
//        try {
//            // NO LO SÉ (DUDA)
//            SSLServerSocketFactory sssf =(SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
//            //CREAMOS EL SSL SOCKET
//            SSLServerSocket ssls = (SSLServerSocket) sssf.createServerSocket(puerto);
//            System.out.println("SERVIDOR ENCENDIDO ESPERANDO CLIENTES");
//            SSLSocket sslSocket = (SSLSocket) ssls.accept();
//            // RECIBIR MENSAJE ENVIADO POR CLIENTE
//            DataInputStream dis = new DataInputStream(sslSocket.getInputStream());
//            //ENVIAR MENSAJE AL CLIENTE
//            DataOutputStream dos = new DataOutputStream(sslSocket.getOutputStream());
//            dos.writeUTF("ESPABILA!");
//            while(true){
//                String mensajeRecibido = dis.readUTF();
//                System.out.println("Non vai o tipo e di: "+mensajeRecibido);
//                if(mensajeRecibido.equals("fora")){
//                    dos.writeUTF("HASTA LOS HUEVOS");
//                    dos.close();
//                    dis.close();
//                    sslSocket.close();
//                    ssls.close();
//                    break;
//                }else{
//                    System.out.println("Respóndeslle ó tipo: "+mensajeRecibido);
//                }
//
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
    public static void comp() {
        marco = new JFrame("(SERVER) MSN MESSENGER");
        panel = new JPanel();
        panel.setLayout(null);
        botonEnviar = new JButton("ENVIAR");



        textoEscribir = new JTextField();
        textoRecibir = new JTextArea();
        panel.setSize(620, 480);
        marco.setSize(620, 480);
    }
    public static void closeWindow(){
        marco.setLocationRelativeTo(marco);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);
    }

        public static void botones(){
            botonEnviar.setBounds(480,380,80,50);
            panel.add(botonEnviar);
            botonEnviar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

//            cn.setMensajeEvento(auxMsg);
                    try {
                        String auxMsg= textoEscribir.getText();


                        dos.writeUTF(auxMsg);
                        textoRecibir.append("Servidor: "+auxMsg+"\n");
                        textoEscribir.setText("");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
    }



    public void mostrarRecibidos(String msg)  {




        textoRecibir.setText("Server: "+msg+"\n");



    }
    public static void distribucion()  {

        comp();
        botones();
        closeWindow();
        textoEscribir.setBounds(2, 380, 420, 50);
        textoRecibir.setBounds(1, 1, 600, 360);
        panel.add(textoEscribir);
        panel.add(textoRecibir);
        marco.add(panel);
//        this.cn = new Conexion();
//        cn.start();
//        cn.establecerConexion();

    }
}