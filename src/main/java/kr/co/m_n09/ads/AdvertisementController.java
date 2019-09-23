package kr.co.m_n09.ads;

import kr.co.m_n09.dtos.AdvertisementDto;
import kr.co.m_n09.vos.Advertisement;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("api/advertisements")
public class AdvertisementController {

    @Autowired
    private String imageDirectoryPath;

    @Autowired
    private AdvertisementService adService;

    @GetMapping("time/{time}")
    public Advertisement selectAdByTime(@PathVariable("time") int time) {
        return adService.selectAdByTime(time);
    }

    @GetMapping("")
    public List<Advertisement> selectAllAd() {
        return adService.selectAllAd();
    }

    @PostMapping
    public ResponseEntity<Advertisement> insertAd(AdvertisementDto advertisementDto) throws Exception {
        return new ResponseEntity<Advertisement>(adService.insertAd(advertisementDto), HttpStatus.OK);
    }

    @PutMapping("ads/{id}")
    public ResponseEntity<Advertisement> modifyAd(@PathVariable("id") int id, @RequestBody Advertisement advertisement) throws Exception {
        advertisement.setId(id);
        return new ResponseEntity<Advertisement>(adService.modifyAd(advertisement), HttpStatus.OK);
    }

    @DeleteMapping("ads/{id}")
    public ResponseEntity<String> deleteAd(@PathVariable int id) {
        adService.deleteAd(id);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @GetMapping("images")
    @ResponseBody
    private byte[] getImage(@RequestParam String name) throws Exception {
        File file = new File(imageDirectoryPath, name);
        InputStream in = new FileInputStream(file);
        return IOUtils.toByteArray(in);
    }
}
