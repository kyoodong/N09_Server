package kr.co.m_n09.ads;

import kr.co.m_n09.FileManager;
import kr.co.m_n09.dtos.AdvertisementDto;
import kr.co.m_n09.vos.Advertisement;
import kr.co.m_n09.daos.ads.AdvertisementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class AdvertisementService implements AdvertisementRepository {

    @Autowired
    private String imageDirectoryPath;

    @Autowired
    private AdvertisementDao adDao;

    public Advertisement selectAdByTime(long time) {
        return adDao.selectAdByTime(time);
    }

    public List<Advertisement> selectAllAd() {
        return adDao.selectAllAd();
    }

    @Transactional
    public Advertisement insertAd(AdvertisementDto advertisementDto) throws Exception {
        advertisementDto.setImageUrl(saveImage(advertisementDto.getImage()));
        adDao.insertAd(advertisementDto);
        return adDao.selectAdById(advertisementDto.getId());
    }

    @Transactional
    public Advertisement modifyAd(Advertisement advertisement) throws Exception {
        adDao.modifyAd(advertisement);
        return adDao.selectAdById(advertisement.getId());
    }

    public void deleteAd(int id) {
        adDao.deleteAd(id);
    }

    public String saveImage(MultipartFile imageFile) throws Exception {
        String fileName = imageFile.getResource().getFilename();
        fileName = fileName.substring(fileName.lastIndexOf("."));
        File newImageFile = new File(imageDirectoryPath, UUID.randomUUID().toString() + fileName);
        FileManager.makeParentDirectory(newImageFile);
        newImageFile.createNewFile();
        FileCopyUtils.copy(imageFile.getBytes(), newImageFile);
        return newImageFile.getName();
    }
}
