package kr.co.m_n09.popup;

import kr.co.m_n09.dtos.PopupDto;
import kr.co.m_n09.vos.Popup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("popups")
public class PopupController {

    @Autowired
    private String imageDirectoryPath;

    @Autowired
    private PopupService pageAdService;

    @GetMapping("services/{serviceId}")
    public Popup getLatestPopup(@PathVariable int serviceId) {
        return pageAdService.getLatestPopup(serviceId);
    }

    @GetMapping("services/{serviceId}/all")
    public List<Popup> getAllPopupList(@PathVariable int serviceId) {
        return pageAdService.getAllPopupList(serviceId);
    }

    @PostMapping("services/{serviceId}")
    public Popup createPopup(@PathVariable int serviceId, PopupDto popup, HttpServletRequest request) throws Exception {
        popup.setServiceId(serviceId);
        return pageAdService.createPopup(popup, request.getSession().getServletContext().getRealPath(imageDirectoryPath));
    }

    @PutMapping("{id}")
    public Popup modifyPopup(@PathVariable int id, @RequestBody PopupDto popup, HttpServletRequest request) throws Exception {
        popup.setId(id);
        return pageAdService.modifyPopup(popup, request.getSession().getServletContext().getRealPath(imageDirectoryPath));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePopup(@PathVariable int id, HttpServletRequest request) throws Exception {
        pageAdService.deletePopup(id, request.getSession().getServletContext().getRealPath(imageDirectoryPath));
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
