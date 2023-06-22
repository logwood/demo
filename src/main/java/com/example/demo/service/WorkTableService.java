package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Tables.Shop;
import com.example.demo.Tables.Work_table;
import com.example.demo.mapping.EmployeeShopMapper;
import com.example.demo.mapping.WorkTableMapper;
import org.springframework.stereotype.Service;

@Service
public class WorkTableService extends ServiceImpl<WorkTableMapper, Work_table> implements IWorkTableImpl{

}
