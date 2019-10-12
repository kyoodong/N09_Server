package kr.co.m_n09.dtos;

import kr.co.m_n09.vos.Popup;
import kr.co.m_n09.vos.Service;
import kr.co.m_n09.vos.UrlData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PopupDto extends Popup {

    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
