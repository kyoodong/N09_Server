package kr.co.m_n09.popup;

import kr.co.m_n09.vos.Popup;
import kr.co.m_n09.vos.Push;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("popups")
public class PopupController {

    @Autowired
    private PopupService popupService;

    @PostMapping("")
    public Popup createPopup(@RequestBody Popup popup) throws Exception {
        return popupService.createPopup(popup);
    }

    @GetMapping("service/{service}")
    public List<Popup> getPopupListByServiceId(@PathVariable int serviceId) {
        return popupService.getPopupListByServiceId(serviceId);
    }
}
