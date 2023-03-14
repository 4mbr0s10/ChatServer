import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion extends Thread  {
    ServerSocket sv;
    Socket sc;
    int puerto;
    DataInputStream dis ;
    DataOutputStream dos;

    InetSocketAddress isa ;
    private JFrame marco;
    private JPanel panel;
    private JTextArea textoRecibir;
    private JTextField textoEscribir;
    private JButton botonEnviar;
    private String nombreCliente;


//    ViewManager vm= new ViewManager();
    String mensajeEvento;

public Conexion()  {
    this.sc= new Socket();
    this.nombreCliente=nombreCliente;
    this.puerto=5555;

    this.isa=new InetSocketAddress("127.0.0.1",puerto);
    try {
        this.sv= new ServerSocket();
        sv.bind(isa);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
//public void establecerConexion (){
//
//    while(true){
//        try {
//            sc=sv.accept();
//
//            dis = new DataInputStream(sc.getInputStream());
//            this.dos = new DataOutputStream(this.sc.getOutputStream());
//
////            String idCliente= dis.readUTF();
//            HiloServer hs = new HiloServer(sc);
//            String mensajitoDos = dis.readUTF();
//            System.out.println(mensajitoDos);
////            hs.start();
//            System.out.println("Cliente conectado. ");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
    @Override
    public void run(){

        try {
            distribucion();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        ViewManager vm= new ViewManager();
//        try {
//            vm.distribucion();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
        while(true){
            try {
                sc=sv.accept();
                System.out.println("Seconecta");
                this.dis = new DataInputStream(sc.getInputStream());
                this.dos = new DataOutputStream(this.sc.getOutputStream());
                nombreCliente=dis.readUTF();
                System.out.println(nombreCliente);
//            String idCliente= dis.readUTF();
                while(true) {
                    String msg = dis.readUTF();
                System.out.println(msg);


//                    vm.mostrarRecibidos(msg);
                    textoRecibir.append("Cliente: "+msg+"\n ");

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }






        }
    public void comp() {
        marco = new JFrame("(SERVER) MSN MESSENGER ");
        panel = new JPanel();
        panel.setLayout(null);
        botonEnviar = new JButton("ENVIAR");



        textoEscribir = new JTextField();
        textoRecibir = new JTextArea();

        panel.setSize(620, 480);
        marco.setSize(620, 480);
    }
    public void closeWindow(){
        marco.setLocationRelativeTo(marco);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);
    }
    public void botones(){
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

    public void mostrarRecibidos(String msg){

//            try {
        System.out.println(msg+"ESTO SE PRINTEA EN MOSTRARRECIBIDOS ");
//                  msg=cn.dis.readUTF();
        textoRecibir.setText("Cliente: "+msg+"\n");

//            }catch (IOException e){
//                throw new RuntimeException();
//
//            }


    }
    public void distribucion() throws IOException {

        this.comp();
        this.botones();
        this.closeWindow();
        textoEscribir.setBounds(2, 380, 420, 50);
        textoRecibir.setBounds(1, 1, 600, 360);
        panel.add(textoEscribir);
        panel.add(textoRecibir);
        marco.add(panel);
//        this.cn = new Conexion();
//        cn.start();
//        this.accionEnviar();

    }

        public DataInputStream getDis () {
            return dis;
        }

        public void setDis (DataInputStream dis){
            this.dis = dis;
        }

        public DataOutputStream getDos () {
            return dos;
        }

        public void setDos (DataOutputStream dos){
            this.dos = dos;
        }

    public String getMensajeEvento() {
        return mensajeEvento;
    }

    public void setMensajeEvento(String mensajeEvento) {
        this.mensajeEvento = mensajeEvento;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
}
