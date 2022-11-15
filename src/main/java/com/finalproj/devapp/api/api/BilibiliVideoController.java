package com.finalproj.devapp.api.api;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finalproj.devapp.api.utils.MinioUtils;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    @RequestParam Integer pageSize) throws Exception {

        Page<BilibiliVideo> pageData = bilibiliVideoService.page(new Page<>(pageNum, pageSize));

        List<BilibiliVideo> result = new ArrayList<>();
        for (BilibiliVideo data : pageData.getRecords()) {
            String video_url = data.getVideoUrl();
            video_url = MinioUtils.getResUrl("androidvideo", video_url);
            data.setVideoUrl(video_url);
            String ownerFace = data.getOwnerFace();
            ownerFace = MinioUtils.getResUrl("android", ownerFace);
            data.setOwnerFace(ownerFace);
            String pic = data.getPic();
            pic = MinioUtils.getResUrl("androidbrand", pic);
            data.setPic(pic);
            String ctime = data.getCtime();
            ctime = String.valueOf(Long.parseLong(ctime) * 1000);
            ctime = stampToTime(ctime);
            data.setCtime(ctime);
            result.add(data);
        }
        pageData.setRecords(result);
        return ApiResponse.ok(pageData);
    }

    @WebLog(description = "首页内容")
    @GetMapping("/getVideoIntro")
    public ApiResponse getVideoIntro(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize) throws Exception {
        List<BilibiliVideo> datas = bilibiliVideoService.list();

        List<BilibiliVideo> tempData = new ArrayList<>();

        for (BilibiliVideo record : createRandoms(datas, 6)) {

            String ownerFace = record.getOwnerFace();
            ownerFace = MinioUtils.getResUrl("android", ownerFace);
            record.setOwnerFace(ownerFace);

            String pic = record.getPic();
            pic = MinioUtils.getResUrl("androidbrand", pic);
            record.setPic(pic);

            String video_url = record.getVideoUrl();
            video_url = MinioUtils.getResUrl("androidvideo", video_url);
            record.setVideoUrl(video_url);
            tempData.add(record);
        }
        Page<BilibiliVideo> resultDatas = new Page<>(pageNum, tempData.size());
        resultDatas.setRecords(tempData);
        return ApiResponse.ok(resultDatas);
    }

    @WebLog(description = "视频名称模糊查找")
    @GetMapping("findInfoByLikeName")
    public ApiResponse findInfoByLikeName(String findInfoByLikeName) throws Exception {
        QueryWrapper<BilibiliVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", findInfoByLikeName);
        List<BilibiliVideo> datas = bilibiliVideoService.list(queryWrapper);
        List<BilibiliVideo> result = new ArrayList<>();
        for (BilibiliVideo data : datas) {
            String video_url = data.getVideoUrl();
            video_url = MinioUtils.getResUrl("androidvideo", video_url);
            data.setVideoUrl(video_url);
            String ownerFace = data.getOwnerFace();
            ownerFace = MinioUtils.getResUrl("android", ownerFace);
            data.setOwnerFace(ownerFace);
            String pic = data.getPic();
            pic = MinioUtils.getResUrl("androidbrand", pic);
            data.setPic(pic);
            String ctime = data.getCtime();
            ctime = String.valueOf(Long.parseLong(ctime) * 1000);
            ctime = stampToTime(ctime);
            data.setCtime(ctime);
            result.add(data);
        }
        return ApiResponse.ok(result);
    }

    @WebLog(description = "视频详细信息")
    @GetMapping("findVideoDetailByBV")
    public ApiResponse findVideoDetailByBV(String bv) throws Exception {
        QueryWrapper<BilibiliVideo>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bvid", bv);
        BilibiliVideo data = bilibiliVideoService.getOne(queryWrapper);
        String video_url = data.getVideoUrl();
        video_url = MinioUtils.getResUrl("androidvideo", video_url);
        data.setVideoUrl(video_url);
        String ownerFace = data.getOwnerFace();
        ownerFace = MinioUtils.getResUrl("android", ownerFace);
        data.setOwnerFace(ownerFace);
        String pic = data.getPic();
        pic = MinioUtils.getResUrl("androidbrand", pic);
        data.setPic(pic);
        String ctime = data.getCtime();
        ctime = String.valueOf(Long.parseLong(ctime) * 1000);
        ctime = stampToTime(ctime);
        data.setCtime(ctime);

        return ApiResponse.ok(data);
    }

    @WebLog(description = "返回首页banner图片地址")
    @GetMapping("getBannerImageUrl")
    public ApiResponse getBannerImageUrl() throws Exception {
        String imageNames[] = {"banner1.webp","banner2.webp","banner3.webp"};
        List<String> imageUrls = new ArrayList<>();
        for (String imageName : imageNames) {
            String imageUrl = MinioUtils.getResUrl("androidbrand", imageName);
            imageUrls.add(imageUrl);
        }
        return ApiResponse.ok(imageUrls);
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
    public static String stampToTime(String s) throws Exception{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        //将时间戳转换为时间
        Date date = new Date(lt);
        //将时间调整为yyyy-MM-dd HH:mm:ss时间样式
        res = simpleDateFormat.format(date);
        return res;
    }


}

