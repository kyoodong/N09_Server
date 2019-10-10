package kr.co.m_n09.daos;

import kr.co.m_n09.vos.Popup;
import kr.co.m_n09.vos.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopupDao {

    void createPopup(Popup popup);

    List<Popup> selectPopupListByServiceId(int serviceId);

    Popup selectPopupById(int id);
}
