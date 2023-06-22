package com.example.demo.checkIDService;

import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/checkID")
public class CheckService {
    @GetMapping("{UserID}")
    public Result<Boolean> checkID(@PathVariable("UserID")String UserID) throws Exception {
        String idcard15 = "142431199001145";//15位
        String idcard18 = "230103201310233912";//18位
        IdcardValidator iv = new IdcardValidator();
        System.out.println(iv.isValidatedAllIdcard(idcard15));
        System.out.println(iv.isValidatedAllIdcard(idcard18));

        IdcardInfoExtractor ext = new IdcardInfoExtractor(idcard18);
        ext.toString();
        return Result.success(iv.isValidatedAllIdcard(UserID));
    }
}
