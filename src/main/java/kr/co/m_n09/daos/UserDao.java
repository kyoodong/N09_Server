package kr.co.m_n09.daos;

import kr.co.m_n09.vos.Service;
import kr.co.m_n09.vos.Token;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    void createToken(Token token);
}
