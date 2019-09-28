package kr.co.m_n09.admin;

import kr.co.m_n09.daos.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class AdminService implements AdminRepository {

    @Autowired
    private AdminDao adminDao;

    public int login(String password) {
        return adminDao.getAdminCount(password);
    }
}
