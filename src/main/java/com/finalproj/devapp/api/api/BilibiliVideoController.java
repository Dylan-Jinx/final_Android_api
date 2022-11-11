package com.finalproj.devapp.api.api;

import com.finalproj.devapp.api.utils.MinioUtils;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

    @WebLog(description = "首页内容")
    @GetMapping("/getVideoIntro")
    public ApiResponse getVideoIntro(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize) throws Exception {
        List<BilibiliVideo> datas = bilibiliVideoService.list();

        List<BilibiliVideo> tempData = new ArrayList<>();
        Page<BilibiliVideo> resultDatas = new Page<>(pageNum, pageSize);

        for (BilibiliVideo record : createRandoms(datas, 6)) {

            String ownerFace = record.getOwnerFace();
            ownerFace = MinioUtils.getResUrl("android", ownerFace);
            record.setOwnerFace(ownerFace);

            String pic = record.getPic();
            pic = MinioUtils.getResUrl("androidbrand", pic);
            record.setPic(pic);

            tempData.add(record);
        }
        resultDatas.setRecords(tempData);
        return ApiResponse.ok(resultDatas);
    }

    /**
     * 从集合中随机取出N个不重复的元素
     * @param list 需要被取出数据的集合
     * @param n 取出的元素数量
     * @return
     */
    private List<BilibiliVideo> createRandoms(List<BilibiliVideo> list, int n) {
        Map<Integer,String> map = new HashMap();
        List<BilibiliVideo> news = new ArrayList();
        if (list.size() <= n) {
            return list;
        } else {
            while (map.size() < n) {
                int random = (int)(Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    news.add(list.get(random));
                }
            }
            return news;
        }
    }

}

