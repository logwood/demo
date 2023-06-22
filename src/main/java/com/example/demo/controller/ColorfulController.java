package com.example.demo.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.example.demo.RunningMain;
import com.example.demo.Tables.User;
import com.example.demo.mapping.UserMapper;
import com.sun.mail.util.MailSSLSocketFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.UUID;

import static com.example.demo.classofGreat.main.operate;
import static com.example.demo.Tables.methods.bytesToString;
import static com.example.demo.Tables.methods.stringToByte;

@RestController
@Controller
@ServletComponentScan
public class ColorfulController {
    @Value("${static-locations}")
    String locations;
    private final HttpServletResponse response;
    private final HttpServletRequest request;

//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private RestTemplate restTemplate;
    public ColorfulController(HttpServletResponse response, HttpServletRequest request) {
        this.response = response;
        this.request = request;
    }
    /**
     *
     * @date 2023年8月7日下午3:20:30
     * @author Main
     * @todo TODO  提醒短信
     */
    @RequestMapping("/SendMesg")
    public static void sendMsg(String mobilephone) throws Exception {
        String accessKeyId = "LTAI5tF3mcFsUEMhEWRycznQ";
        String accessKeySecret = "9dUk4B2Mkfzvlt0KiRgRp9hOFZwIeG";
        String signName = "wangsanggood";
        String templateCode = "SMS_154950909";
        //自定义的短信模板编码，在阿里云短信平台中心配置
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId, accessKeySecret);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobilephone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串
        String templateParam="{\"code\":\"114514\"}";
        request.setTemplateParam(templateParam);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("11123345");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
            SendSmsResponse a = sendSmsResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/get/goodbye")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("name");
        map.addAttribute("bookTitle");
        // return模板文件的名称，对应src/CheckService/resources/templates/welcome.html
        RunningMain.context.close();
        return "closed";
    }
    @RequestMapping("post/mail")
    public void in() throws MessagingException, GeneralSecurityException, FileNotFoundException {
        Properties properties = new Properties();

        properties.setProperty("mail.host","smtp.qq.com");

        properties.setProperty("mail.transport.protocol","smtp");

        properties.setProperty("mail.smtp.auth","true");


        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("9990928@qq.com","gteoacmcabuucaef");
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect("smtp.qq.com","9990928@qq.com","gteoacmcabuucaef");

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress("9990928@qq.com"));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress("9990928@qq.com"));

        //邮件标题
        mimeMessage.setSubject("RunningMain Mail");
        //邮件内容
        //发送邮件
        MimeMultipart msgMultipart = new MimeMultipart("mixed");
        mimeMessage.setContent(msgMultipart);
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent("你的验证码是114514543543","text/html;charset=UTF-8");
        msgMultipart.addBodyPart(htmlPart);

        MimeBodyPart filePart = new MimeBodyPart();
        String all=ResourceUtils.getFile(locations+"11.docx").getAbsolutePath();
        FileDataSource dataSource = new FileDataSource(all);
        DataHandler dataHandler = new DataHandler(dataSource);
        // 文件处理
        filePart.setDataHandler(dataHandler);
        // 附件名称
        filePart.setFileName(dataSource.getName());
        // 放入正文（有先后顺序，所以在正文后面）
        msgMultipart.addBodyPart(filePart);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();
    }
    //@RequestMapping("user/login")
    //public String login(String username, HttpSession session) throws IOException, ServletException {
    //    try {
    //        ByteArrayOutputStream bos = new ByteArrayOutputStream();
    //        ObjectOutputStream oos=new ObjectOutputStream(bos);
    //        User user = new User();
    //        user.setUsercode(username);
    //        oos.writeObject(user);
    //        byte[] bytes = bos.toByteArray();
    //        //2.登录成功,保存用户登录标记
    //        Cookie cookie=new Cookie("user", bytesToString(bytes));
    //        cookie.setMaxAge(60*60*24);
    //        cookie.setPath("/");
    //        response.addCookie(cookie);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //        return "redirect:/login.jsp?msg="+ URLEncoder.encode(e.getMessage(),"UTF-8");
    //    }
    //    return "hel";
    //}


    @RequestMapping("user/list")
    public String user_list(HttpSession session) throws UnsupportedEncodingException {
        try {

            Cookie[] cookies=request.getCookies();
            Cookie user=cookies[0];
            for(Cookie cookie:cookies)
            {
                String a=cookie.getName();
             if(a.equals("user"))
             {
                 user=cookie;
             }
            }
            String json=user.getValue();
            ByteArrayInputStream bai=new ByteArrayInputStream(stringToByte(json));
            ObjectInputStream ois=new ObjectInputStream(bai);
            User user1=(User) ois.readObject();
            //2.登录成功,保存用户登录标记
            System.out.println(user1.getUsercode()+user1.getStaffId());
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login.jsp?msg="+ URLEncoder.encode(e.getMessage(),"UTF-8");
        }
        return "redirect:/employee/list";
    }
    @RequestMapping("sort")
    public void getsortPage() throws Exception {
        operate("123",0);
    }
    @RequestMapping("/image")
    public String sendImage(@RequestParam MultipartFile image) throws Exception
    {
        String multifilename = image.getOriginalFilename();
//取文件名下标，给文件重命名的时候使用
        String suffix = multifilename.substring(multifilename.indexOf("."));
//取一个随机id给文件重命名使用
        String uuid= UUID.randomUUID().toString().replaceAll("\\.","");
//你的接收的文件新的名字
        String filename = uuid+suffix;
//获取项目的绝对路径
        String uri = request.getSession().getServletContext().getRealPath("/");

//在项目新建一个 你重新生成名称的文件
        File file =new File(new String(uri+"/"+filename)) ;
        if(!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
//将接收的到的 multipartFile 类型的文件 转为 file
        image.transferTo(file);
        file.renameTo(new File(uri+"/"+multifilename));
        file.delete();
//获取接收到的并存在项目本地的文件，这样你就可以拿着这个文件随意处理啦
        String filePath = file.getAbsolutePath();
        return filePath ;
    }
}