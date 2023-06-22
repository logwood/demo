package com.example.demo.controller;

import com.example.demo.Tables.Course;
import com.example.demo.Tables.Grades;
import com.example.demo.Tables.User;
import com.example.demo.service.CourseImpl;
import com.example.demo.service.GradeImpl;
import com.example.demo.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;
import java.util.List;

import static com.example.demo.Tables.methods.bytesToString;

@Controller
@ServletComponentScan

public class QueryController {
    @Autowired
    private GradeImpl gradeService;
    @Autowired
    private CourseImpl course;
    @RequestMapping("/query")
    public String index(ModelMap map) {
        // return模板文件的名称，对应src/CheckService/resources/templates/welcome.html
        return "hel.html";
    }
    @RequestMapping("user/login")
    public String login(String username, HttpSession session,ModelMap model) throws IOException, ServletException {
        List<Grades>results;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(bos);
            User user = new User();
            user.setUsercode(username);
            oos.writeObject(user);

            byte[] bytes = bos.toByteArray();
            //2.登录成功,保存用户登录标记
            Cookie cookie=new Cookie("user", bytesToString(bytes));
            cookie.setMaxAge(60*60*24);
            cookie.setPath("/");
        results=gradeService.getgrade(username);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/answer.html?msg="+ URLEncoder.encode(e.getMessage(),"UTF-8");
        }
        model.addAttribute("Grades",results);
        return "answer.html";
    }
}