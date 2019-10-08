package kr.co.m_n09.push;

import kr.co.m_n09.vos.Push;
import kr.co.m_n09.vos.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pushes")
public class PushController {

    @Autowired
    private PushService pushService;

    @PostMapping("")
    public ResponseEntity<Integer> push(@RequestBody Push push) throws Exception {
        pushService.push(push);
        return new ResponseEntity<Integer>(0, HttpStatus.OK);
    }
}
