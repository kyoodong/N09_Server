package kr.co.m_n09.daos;

import kr.co.m_n09.vos.Token;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenDao {

    void createToken(Token token);

    List<Token> selectAllUsersByServiceId(int serviceId);

    Token selectUserById(String id);

    void updateUser(Token token);
}
