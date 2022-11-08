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
@TableName("app_user")
@ApiModel(value = "AppUser对象", description = "")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("phone")
    private String phone;

    @TableField("passwd")
    private String passwd;

    @TableField("mid")
    private Integer mid;


}
