package services;

import dao.userDao;

public class LoginService {

    public boolean authenticate(String username, String password) {
        return userDao.fazerLogin(username, password);
    }
}
