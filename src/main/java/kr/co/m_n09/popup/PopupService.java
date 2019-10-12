package kr.co.m_n09.popup;

import kr.co.m_n09.FileManager;
import kr.co.m_n09.daos.PopupDao;
import kr.co.m_n09.dtos.PopupDto;
import kr.co.m_n09.vos.Popup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PopupService implements PopupRepository {

    @Autowired
    private PopupDao popupDao;

    public Popup createPopup(PopupDto popup, String imagePath) throws Exception {
        popup.setImageUrl(FileManager.saveImage(popup.getImage(), imagePath));
        if (popup.getImageUrl() == null)
            throw new Exception();

        popupDao.insertPopup(popup);
        return popupDao.selectPopup(popup.getId());
    }

    public Popup getLatestPopup(int serviceId) {
        return popupDao.selectLatestPopup(serviceId);
    }

    public List<Popup> getAllPopupList(int serviceId) {
        return popupDao.selectAllPopupList(serviceId);
    }

    public Popup modifyPopup(PopupDto popup, String imageDirectoryPath) throws Exception {
        Popup existedPopup = popupDao.selectPopup(popup.getId());
        if (popup.getImage() != null) {
            popup.setImageUrl(FileManager.saveImage(popup.getImage(), imageDirectoryPath));
            File imageFile = new File(imageDirectoryPath + File.separator + existedPopup.getImageUrl());
            imageFile.deleteOnExit();
        }

        popupDao.updatePopup(popup);
        return popup;
    }

    public void deletePopup(int id, String imageDirectoryPath) throws Exception {
        Popup existedPopup = popupDao.selectPopup(id);
        File imageFile = new File(imageDirectoryPath + File.separator + existedPopup.getImageUrl());
        imageFile.deleteOnExit();

        popupDao.deletePopup(id);
    }
}
