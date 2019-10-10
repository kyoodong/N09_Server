package kr.co.m_n09.popup;

import kr.co.m_n09.vos.Popup;
import kr.co.m_n09.vos.Token;
import kr.co.m_n09.vos.User;

import java.util.List;

public interface PopupRepository {

    Popup createPopup(Popup popup);

    List<Popup> getPopupListByServiceId(int serviceId);
}
