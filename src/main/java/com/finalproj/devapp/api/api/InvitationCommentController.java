package com.finalproj.devapp.api.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.finalproj.devapp.api.common.ApiResponse;
import com.finalproj.devapp.api.log.annotation.WebLog;
import com.finalproj.devapp.api.service.InvitationCommentService;
import com.finalproj.devapp.api.domain.model.InvitationComment;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-11-07
 */
@RestController
@RequestMapping("/invitationComment")
public class InvitationCommentController {

    final Logger logger = LoggerFactory.getLogger(InvitationCommentController.class);
    @Autowired
    private InvitationCommentService invitationCommentService;

    @WebLog(description = "添加")
    @PostMapping
    public ApiResponse save(@RequestBody InvitationComment invitationComment) {
        return ApiResponse.ok(invitationCommentService.saveOrUpdate(invitationComment));
    }

    @WebLog(description = "用id删除")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(invitationCommentService.removeById(id));
    }

    @WebLog(description = "查询全部")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(invitationCommentService.list());
    }

    @WebLog(description = "用id查找")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(invitationCommentService.getById(id));
    }

    @WebLog(description = "分页")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize) {
        return ApiResponse.ok(invitationCommentService.page(new Page<>(pageNum, pageSize)));
    }
}

