package lesson_f.Chat_Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.*;
//add hw1
public class Server {
    private Vector<ClientHandler> clients;

    Logger logger = Logger.getLogger(lesson_f.Chat_Server.Server.class.getName());

    public Server() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            //task1
            Handler admin = new FileHandler("Server_log.log", true);
            admin.setLevel(Level.ALL);
            admin.setFormatter(new SimpleFormatter());
            logger.addHandler(admin);
            logger.log(Level.SEVERE, "Сервер запущен.");
            while (true) {
                socket = server.accept();
                logger.log(Level.INFO, "Клиент подключился.");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
               logger.log(Level.SEVERE, "При подключении возникла ошибка: ", e.getStackTrace());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE,"При закрытии соединения возникла ошибка: ", e.getStackTrace());
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "При закрытии сервера возникла ошибка: ", e.getStackTrace());
            }
            AuthService.disconnect();
        }
    }

    public void sendPersonalMsg(ClientHandler from, String nickTo, String msg) throws IOException {
        Handler chatLog = new FileHandler("Chat_log.log", true);//думаю неверно каждый раз при отправке
        chatLog.setLevel(Level.ALL);                                          //сообщения создавать хэндлер, необходимо
        chatLog.setFormatter(new SimpleFormatter());                          //вынести в поле класса?
        logger.addHandler(chatLog);
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickTo)) {
                o.sendMsg("from " + from.getNick() + ": " + msg);
                logger.log(Level.INFO, "from " + from.getNick() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                logger.log(Level.INFO, "from " + from.getNick() + ": " + msg);
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + " не найден в чате");
        logger.log(Level.INFO, "Клиент с ником " + nickTo + " не найден в чате");
    }

    public void broadcastMsg(ClientHandler from, String msg) {
        for (ClientHandler o : clients) {
            if (!o.checkBlackList(from.getNick())) {
                o.sendMsg(msg);
            }
        }
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastClientsList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientslist ");
        for (ClientHandler o : clients) {
            sb.append(o.getNick() + " ");
        }
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientsList();
    }
}
