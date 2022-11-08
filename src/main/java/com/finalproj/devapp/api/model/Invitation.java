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
@TableName("invitation")
@ApiModel(value = "Invitation对象", description = "")
public class Invitation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("tid")
    private Integer tid;

    @TableField("t_type")
    private String tType;

    @TableField("content")
    private String content;

    @TableField("mid")
    private Integer mid;

    @TableField("t_pic")
    private String tPic;

    @TableField("`like`")
    private Integer like;

    @TableField("`comment`")
    private Integer comment;

    @TableField("collect")
    private Integer collect;

    @TableField("unlike")
    private Integer unlike;

    @TableField("`share`")
    private Integer share;


}
