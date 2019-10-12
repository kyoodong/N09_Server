package kr.co.m_n09;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
public class MainController {

    @Autowired
    private String imageDirectoryPath;

    @GetMapping("")
    private String main() throws Exception {
        return "index";
    }

    @GetMapping("images")
    @ResponseBody
    public byte[] getImage(@RequestParam String fileName, HttpServletRequest request) throws Exception {
        File file = new File(request.getSession().getServletContext().getRealPath(imageDirectoryPath) + File.separator + fileName);
        InputStream is = new FileInputStream(file);
        return IOUtils.toByteArray(is);
    }
}
