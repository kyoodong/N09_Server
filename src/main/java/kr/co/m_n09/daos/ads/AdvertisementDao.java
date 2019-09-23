package kr.co.m_n09.daos.ads;

import kr.co.m_n09.vos.Advertisement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementDao {

    Advertisement selectAdByTime(long time);

    List<Advertisement> selectAllAd();

    void insertAd(Advertisement advertisement);

    void modifyAd(Advertisement advertisement);

    void deleteAd(int id);

    Advertisement selectAdById(int id);
}
