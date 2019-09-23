package kr.co.m_n09.ads;

import kr.co.m_n09.dtos.AdvertisementDto;
import kr.co.m_n09.vos.Advertisement;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdvertisementRepository {

    Advertisement selectAdByTime(long time);

    List<Advertisement> selectAllAd();

    Advertisement insertAd(AdvertisementDto advertisementDto) throws Exception;

    Advertisement modifyAd(Advertisement advertisementDto) throws Exception;

    void deleteAd(int id);

    String saveImage(MultipartFile imageFile) throws Exception;
}
