package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Tables.User;
import com.example.demo.Tables.user_data;
import com.example.demo.mapping.UserDataMapper;
import com.example.demo.mapping.UserMapper;

public class UserDataImpl extends ServiceImpl<UserDataMapper, user_data> implements IUserDataService {
}
