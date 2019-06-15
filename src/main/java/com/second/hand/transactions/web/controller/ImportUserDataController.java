package com.second.hand.transactions.web.controller;

import com.second.hand.transactions.service.impl.ImportDataService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/13 0013
 * Time:20:10
 */
@RestController
public class ImportUserDataController {
    @Autowired
    private ImportDataService importDataService;

    @PostMapping("/import")
    public String importData(@RequestParam("file")MultipartFile file,@RequestParam("username")String username,@RequestParam("password") String password){
        String result = "error";
        if(username.equals("liujiachen") && password.equals("l573324982")){
            boolean a = false;
            String fileName = file.getOriginalFilename();
            try {
                result = importDataService.importData(fileName, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
