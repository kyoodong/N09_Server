package kr.co.m_n09.daos;

import kr.co.m_n09.vos.Token;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {

    int getAdminCount(String password);

}
