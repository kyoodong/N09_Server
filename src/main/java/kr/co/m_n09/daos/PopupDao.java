package kr.co.m_n09.daos;

import kr.co.m_n09.vos.Popup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopupDao {

    void insertPopup(Popup popup);

    Popup selectLatestPopup(int serviceId);

    Popup selectPopup(int id);

    List<Popup> selectAllPopupList(int serviceId);

    void updatePopup(Popup popup);

    void deletePopup(int id);
}
