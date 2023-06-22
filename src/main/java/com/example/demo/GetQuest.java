package com.example.demo;

import com.example.demo.Tables.employees;
import com.example.demo.mapping.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;

@WebServlet(urlPatterns = "/get/quest",name = "httpquest")
@RestController
public class GetQuest extends HttpServlet {
    private final UserMapper userMapper;

    public GetQuest(@Qualifier("userMapper") UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream out = request.getInputStream();
        String sr=new String(out.readAllBytes());
        String[] strings=sr.split("\r\n\r\n");
        for(int i=1;i<strings.length;i++)
        {
            strings[i]=strings[i].split("\r\n")[0];
        }
        List<String> str= List.of(strings);
        ArrayList<String> strr=new ArrayList<>(str);
        strr.remove(0);
        employees el=new employees(strr);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
