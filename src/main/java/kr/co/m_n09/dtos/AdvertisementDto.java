package kr.co.m_n09.dtos;

import kr.co.m_n09.vos.Advertisement;
import org.springframework.web.multipart.MultipartFile;

public class AdvertisementDto extends Advertisement {

    private MultipartFile image;

    public AdvertisementDto(int id, int startTime, int endTime, String name, String imageUrl, String linkUrl, MultipartFile image) {
        super(id, startTime, endTime, name, imageUrl, linkUrl);
        this.image = image;
    }

    public AdvertisementDto() {

    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
