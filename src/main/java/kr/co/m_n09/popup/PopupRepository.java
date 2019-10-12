package kr.co.m_n09.popup;

import kr.co.m_n09.dtos.PopupDto;
import kr.co.m_n09.vos.Popup;

import java.util.List;

public interface PopupRepository {

    Popup createPopup(PopupDto popup, String imagePath) throws Exception;

    Popup getLatestPopup(int serviceId);

    List<Popup> getAllPopupList(int serviceId);

    Popup modifyPopup(Popup popup, String imageDirectoryPath) throws Exception;

    void deletePopup(int id, String imageDirectoryPath) throws Exception;
}
