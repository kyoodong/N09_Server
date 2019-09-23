package kr.co.m_n09;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
public class MainController {

    @GetMapping("")
    private String main() {
        System.out.println("bye");
        return "index";
    }
}
