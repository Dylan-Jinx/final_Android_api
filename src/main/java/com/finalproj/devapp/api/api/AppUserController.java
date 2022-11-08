package com.finalproj.devapp.api.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.finalproj.devapp.api.common.ApiResponse;
import com.finalproj.devapp.api.log.annotation.WebLog;
import com.finalproj.devapp.api.service.AppUserService;
import com.finalproj.devapp.api.domain.model.AppUser;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-11-07
 */
@RestController
@RequestMapping("/appUser")
public class AppUserController {

    final Logger logger = LoggerFactory.getLogger(AppUserController.class);
    @Autowired
    private AppUserService appUserService;

    @WebLog(description = "用户登录")
    @PostMapping("/login")
    public ApiResponse login(@RequestBody AppUser appUser){
        QueryWrapper<AppUser> appUserQueryWrapper = new QueryWrapper<>();
        appUserQueryWrapper.eq("phone", appUser.getPhone())
                .eq("passwd",appUser.getPasswd());
        long checkUser = appUserService.count(appUserQueryWrapper);
        if(checkUser > 0){
            AppUser user = appUserService.getOne(appUserQueryWrapper);
            return ApiResponse.ok(new AppUser().setMid(user.getMid()));
        }else{
            return ApiResponse.error("用户名或密码错误");
        }
    }

    @WebLog(description = "添加")
    @PostMapping
    public ApiResponse save(@RequestBody AppUser appUser) {
        return ApiResponse.ok(appUserService.saveOrUpdate(appUser));
    }

    @WebLog(description = "用id删除")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(appUserService.removeById(id));
    }

    @WebLog(description = "查询全部")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(appUserService.list());
    }

    @WebLog(description = "用id查找")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(appUserService.getById(id));
    }

    @WebLog(description = "分页")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize) {
        return ApiResponse.ok(appUserService.page(new Page<>(pageNum, pageSize)));
    }
}

