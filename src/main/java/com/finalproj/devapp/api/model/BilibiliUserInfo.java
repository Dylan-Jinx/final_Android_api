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
 * @since 2022-11-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("bilibili_user_info")
@ApiModel(value = "BilibiliUserInfo对象", description = "")
public class BilibiliUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("mid")
    private Integer mid;

    @TableField("`name`")
    private String name;

    @TableField("sex")
    private String sex;

    @TableField("`rank`")
    private String rank;

    @TableField("face")
    private String face;

    @TableField("regtime")
    private String regtime;

    @TableField("spacesta")
    private String spacesta;

    @TableField("birthday")
    private String birthday;

    @TableField("sign")
    private String sign;

    @TableField("`level`")
    private String level;

    @TableField("OfficialVerifyType")
    private String officialVerifyType;

    @TableField("OfficialVerifyDesc")
    private String officialVerifyDesc;

    @TableField("vipType")
    private String vipType;

    @TableField("vipStatus")
    private String vipStatus;

    @TableField("toutu")
    private String toutu;

    @TableField("toutuId")
    private Integer toutuId;

    @TableField("coins")
    private Integer coins;

    @TableField("`following`")
    private Integer following;

    @TableField("fans")
    private Integer fans;

    @TableField("archiveview")
    private Integer archiveview;

    @TableField("article")
    private Integer article;


}
