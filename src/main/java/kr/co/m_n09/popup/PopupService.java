package kr.co.m_n09.popup;

import kr.co.m_n09.daos.PopupDao;
import kr.co.m_n09.daos.UserDao;
import kr.co.m_n09.vos.Popup;
import kr.co.m_n09.vos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PopupService implements PopupRepository {

    @Autowired
    private PopupDao popupDao;

    @Transactional
    public Popup createPopup(Popup popup) {
        popupDao.createPopup(popup);
        return popupDao.selectPopupById(popup.getId());
    }

    public List<Popup> getPopupListByServiceId(int serviceId) {
        return popupDao.selectPopupListByServiceId(serviceId);
    }
}
