import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class Servidor {
	public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Servidor de Chat iniciado.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                ChatHandler chatHandler = new ChatHandler(clientSocket);
                chatHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ChatHandler extends Thread {
    private Socket clientSocket;

    public ChatHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("Bienvenido al servidor de chat. Seleccione una opción:");
            out.println("1. Canta sarandonga");
            out.println("2. Canta tombola");
            out.println("3. Canta los coches de choque");
            out.println("4. Canta Los Lunnis");
            out.println("5. Canta Minero");
        
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equals("1")) {
                    out.println("Sarandonga cuchiviri cuchiviri\r\n"
                    		+ "Sarandonga cuchiviri cuchiviri\r\n"
                    		+ "Sarandonga cuchiviri cuchiviri");
                } else if (inputLine.equals("2")) {
                    out.println("La vida es una tómbola tom tom tómbola\r\n"
                    		+ "De luz y de color");
                } else if (inputLine.equals("3")) {
                    out.println("Y era un domingo la tarde\r\n"
                    		+ "Fui a los coches de choque\r\n"
                    		+ "Y estaban pinchando el\r\n"
                    		+ "Disco que a mi tanto me pone\r\n"
                    		+ "Fui a sacar 4 fichas\r\n"
                    		+ "Y compre un abono");
                } else if (inputLine.equals("4")) {
                    out.println("Buenas noches hasta mañana los Lunnis\r\n"
                    		+ "y los niños nos vamos a la cama");
                } else if (inputLine.equals("5")) {
                    out.println("Si hay que ser minero\r\n"
                    		+ "Romper el pico en el hierro\r\n"
                    		+ "No importa el creeper que venga pa que sepas que te quiero\r\n"
                    		+ "Como un buen minero, me juego la vida por tiiiii.");
                } else {
                    out.println("Opción inválida. Seleccione una opción del 1 al 5.");
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
