package lesson_f.Chat_Server;

import java.sql.*;
import java.util.logging.*;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;
    static Logger logger = Logger.getLogger(lesson_f.Chat_Server.Server.class.getName());
//add hw

    public static void connect() {
        try {
            Handler db = new FileHandler("Db_log.log", true);
            db.setLevel(Level.ALL);
            db.setFormatter(new SimpleFormatter());
            logger.addHandler(db);
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            stmt = connection.createStatement();
            logger.log(Level.SEVERE, "Соединение с базой данных установлено.");
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Соединение с базой не удалось установить: ", e.getStackTrace());
        }
    }

    public static void addUser(String login, String pass, String nick) {
        try {
            String query = "INSERT INTO main (login, password, nickname) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, nick);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname, password FROM main WHERE login = '" + login + "'");
            int myHash = pass.hashCode();
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);
                if (myHash == dbHash) {
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
