package com.second.hand.transactions.service.impl;

import com.second.hand.transactions.exception.MyException;
import com.second.hand.transactions.mapper.UserMapper;
import com.second.hand.transactions.model.User;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/6/13 0013
 * Time:20:17
 */
@Service
public class ImportDataService {
    @Autowired
    private UserMapper userMapper;

    public String importData(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        List<User> userList = new ArrayList<User>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }

        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        User user;
        for (int r = 1; r <= sheet.getLastRowNum();r++){
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            String phone = row.getCell(0).getStringCellValue();
            String wechat = row.getCell(1).getStringCellValue();
            String password = row.getCell(2).getStringCellValue();
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            String username = row.getCell(3).getStringCellValue();
            String imagePath = row.getCell(4).getStringCellValue();
            String id = row.getCell(5).getStringCellValue();
            user = new User(id,phone,wechat,password,username,imagePath);
            userList.add(user);
        }

        for (User user1 : userList) {
            //System.out.println(user1);
            userMapper.insert(user1);
        }
        return "success";
    }
}
