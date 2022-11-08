package com.finalproj.devapp.api.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("invitation_comment")
@ApiModel(value = "InvitationComment对象", description = "")
public class InvitationComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("tc_id")
    private String tcId;

    @TableField("content")
    private String content;

    @TableField("tc_pic")
    private String tcPic;

    @TableField("comment_time")
    private LocalDateTime commentTime;

    @TableField("mid")
    private Integer mid;

    @TableField("`like`")
    private Integer like;

    @TableField("unlike")
    private Integer unlike;


}
