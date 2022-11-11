package com.finalproj.devapp.api.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finalproj.devapp.api.utils.MinioUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.finalproj.devapp.api.common.ApiResponse;
import com.finalproj.devapp.api.log.annotation.WebLog;
import com.finalproj.devapp.api.service.BilibiliUserInfoService;
import com.finalproj.devapp.api.domain.model.BilibiliUserInfo;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-11-07
 */
@RestController
@RequestMapping("/bilibiliUserInfo")
public class BilibiliUserInfoController {

    final Logger logger = LoggerFactory.getLogger(BilibiliUserInfoController.class);
    @Autowired
    private BilibiliUserInfoService bilibiliUserInfoService;

    @WebLog(description = "添加")
    @PostMapping
    public ApiResponse save(@RequestBody BilibiliUserInfo bilibiliUserInfo) {
        return ApiResponse.ok(bilibiliUserInfoService.saveOrUpdate(bilibiliUserInfo));
    }

    @WebLog(description = "用id删除")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(bilibiliUserInfoService.removeById(id));
    }

    @WebLog(description = "查询全部")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(bilibiliUserInfoService.list());
    }

    @WebLog(description = "用id查找")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(bilibiliUserInfoService.getById(id));
    }

    @WebLog(description = "用mid查找")
    @GetMapping("findInfoByMid")
    public ApiResponse findInfoByMid(Integer mid) throws Exception {
        QueryWrapper<BilibiliUserInfo> bilibiliUserInfoQueryWrapper = new QueryWrapper<>();
        bilibiliUserInfoQueryWrapper.eq("mid", mid);
        BilibiliUserInfo info = bilibiliUserInfoService.getOne(bilibiliUserInfoQueryWrapper);
        String res_url = info.getFace();
        res_url = MinioUtils.getResUrl("android", res_url);
        info.setFace(res_url);
        return ApiResponse.ok(info);
    }

    @WebLog(description = "分页")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize) throws Exception {
        Page<BilibiliUserInfo> datas = bilibiliUserInfoService.page(new Page<>(pageNum, pageSize));
        List<BilibiliUserInfo> newDatas = new ArrayList<>();
        for (BilibiliUserInfo record : datas.getRecords()) {
            String res_url = record.getFace();
            res_url = MinioUtils.getResUrl("android", res_url);
            record.setFace(res_url);
            newDatas.add(record);
        }
        datas.setRecords(newDatas);
        return ApiResponse.ok(datas);
    }
}

