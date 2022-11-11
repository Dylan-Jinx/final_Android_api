package com.finalproj.devapp.api.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.finalproj.devapp.api.common.ApiResponse;
import com.finalproj.devapp.api.log.annotation.WebLog;
import com.finalproj.devapp.api.service.BilibiliVideoService;
import com.finalproj.devapp.api.domain.model.BilibiliVideo;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-11-11
 */
@RestController
@RequestMapping("/bilibiliVideo")
public class BilibiliVideoController {

    final Logger logger = LoggerFactory.getLogger(BilibiliVideoController.class);
    @Autowired
    private BilibiliVideoService bilibiliVideoService;

    @WebLog(description = "添加")
    @PostMapping
    public ApiResponse save(@RequestBody BilibiliVideo bilibiliVideo) {
        return ApiResponse.ok(bilibiliVideoService.saveOrUpdate(bilibiliVideo));
    }

    @WebLog(description = "用id删除")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(bilibiliVideoService.removeById(id));
    }

    @WebLog(description = "查询全部")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(bilibiliVideoService.list());
    }

    @WebLog(description = "用id查找")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(bilibiliVideoService.getById(id));
    }

    @WebLog(description = "分页")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize) {
        return ApiResponse.ok(bilibiliVideoService.page(new Page<>(pageNum, pageSize)));
    }
}

