package com.mail.college.controller;

import com.mail.college.entity.SimpleUser;
import com.mail.college.repository.UserRepository;
import com.mail.college.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lemon
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @GetMapping("/myinfo")
    @ApiOperation("个人信息")
    public ResponseEntity<String> index(){
        return new ResponseEntity<>("My Info",HttpStatus.OK);
    }

    @GetMapping("/list")
    @ApiOperation("查询所有用户")
    public ResponseEntity<List<SimpleUser>> list(){
        List<SimpleUser> list = userRepository.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/sendmsg/{phone}")
    @ApiOperation("发送手机验证码")
    public ResponseEntity<String> sendMsg(HttpServletRequest request, @PathVariable String phone){
        String code = MessageUtil.sendMsg(phone);
        request.getSession().setAttribute("verifyCode", code);
        return new ResponseEntity<>(code,HttpStatus.OK);
    }

    @PostMapping("/signup")
    @ApiOperation("注册用户")
    public ResponseEntity<String> signup(HttpServletRequest request, @RequestBody SimpleUser simpleUser,@RequestParam(value="verifycode") String code){
        String verifycode = (String) request.getSession().getAttribute("verifyCode");
        if (code.equals(verifycode)){
            userRepository.insertUser(simpleUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
