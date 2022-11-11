package com.finalproj.devapp.api.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-11-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("bilibili_video")
@ApiModel(value = "BilibiliVideo对象", description = "")
public class BilibiliVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("bvid")
    private String bvid;

    @TableField("aid")
    private Integer aid;

    @TableField("tname")
    private String tname;

    @TableField("pic")
    private String pic;

    @TableField("title")
    private String title;

    @TableField("ctime")
    private String ctime;

    @TableField("`desc`")
    private String desc;

    @TableField("danmaku")
    private Integer danmaku;

    @TableField("`view`")
    private Integer view;

    @TableField("favorite")
    private Integer favorite;

    @TableField("coin")
    private Integer coin;

    @TableField("`share`")
    private Integer share;

    @TableField("`like`")
    private String like;

    @TableField("video_url")
    private String videoUrl;

    @TableField("owner_name")
    private String ownerName;

    @TableField("owner_face")
    private String ownerFace;

    @TableField("tag")
    private String tag;


}
