// Здесь у нас коммандный центр, он же "клиент" для ракеты.
// Тестовый запуск в Саргассово море.

package Lesson_3;

import java.io.*;
import java.net.Socket;

public class RocketCommandCenter {
    private static String server = "127.0.0.1";
    private static int port = 12349;

    public static void main(String[] args) {
        //37.075584, -70.625106
        Target target = new Target(37.075584, -70.625106);
        try {
            send(server,port,target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void send(String server, int port, Target target) throws IOException {
        Socket socket= new Socket(server, port);
        if (socket.isConnected()) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream sendTarget = new ObjectOutputStream(out);
            sendTarget.writeObject(target);
            sendTarget.close();
            out.close();
        }
        socket.close();
    }
}
