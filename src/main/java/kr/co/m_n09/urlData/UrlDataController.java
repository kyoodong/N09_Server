package kr.co.m_n09.urlData;

import kr.co.m_n09.vos.UrlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("urls")
public class UrlDataController {

    @Autowired
    private UrlDataService urlDataService;

    @PostMapping("")
    public UrlData createUrlData(UrlData urlData) {
        return urlDataService.createUrlData(urlData);
    }

    @PutMapping("{id}")
    public UrlData modifyUrlData(@PathVariable int id, UrlData urlData) {
        urlData.setId(id);
        return urlDataService.modifyUrlData(urlData);
    }

    @DeleteMapping("{id}")
    public int deleteUrlData(@PathVariable int id) {
        return urlDataService.deleteUrlData(id);
    }
}
