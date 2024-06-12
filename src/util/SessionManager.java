package util;
public class SessionManager {
    private static String loggedInUsername = null;

    public static boolean isLoggedIn() {
        return loggedInUsername != null;
    }

    public static String getLoggedInUsername() {
        return loggedInUsername;
    }

    public static void login(String username) {
        loggedInUsername = username;
    }

    public static void logout() {
        loggedInUsername = null;
    }
}