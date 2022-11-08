package com.finalproj.devapp.api.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalTime;
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
@TableName("video_barrage")
@ApiModel(value = "VideoBarrage对象", description = "")
public class VideoBarrage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("barrage_id")
    private String barrageId;

    @TableField("content")
    private String content;

    @TableField("font_color")
    private String fontColor;

    @TableField("font_size")
    private Integer fontSize;

    @TableField("font_weight")
    private String fontWeight;

    @TableField("v_id")
    private String vId;

    @TableField("video_appear_time")
    private LocalTime videoAppearTime;


}
