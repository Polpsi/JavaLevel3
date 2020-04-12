package Lesson_3;

// Это одноразовый сервер баллистической ракеты.
// Он ждет подключения, получает координаты и отправляет ракету по данным координатам.
// У него одна цель - получить координаты от командного центра и обработать их.
// Потому не будем его в отдельный поток оборачивать.
//
// "Держи ластик, стирай свою Америку!" (С) Анекдот

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RocketNavigator {
    static Socket socket;
    static DataInputStream stream;

    public static void main(String[] args) {
        try (ServerSocket serSock = new ServerSocket(12349)) {
            socket = serSock.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stream = new DataInputStream(socket.getInputStream());
            ObjectInputStream getTarget = new ObjectInputStream(stream);
            Target target = (Target) getTarget.readObject();
            launch(target);
            getTarget.close();
            stream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void launch(Target target) {
        double[] coords = target.getCoordinates();
        System.out.println("Target coordinates: " + coords[0] + ", " + coords[1]);
        System.out.println("Missile started!");
    }
}