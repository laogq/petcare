package com.anvy.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CarePet对象", description="")
public class CarePet implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "宠物姓名")
    private String petName;

    @ApiModelProperty(value = "宠物性别")
    private Integer petSex;

    @ApiModelProperty(value = "健康状况 0-较好；1-良好；2-一般；3-较差")
    private Integer petHealth;

    @ApiModelProperty(value = "生日")
    private Date petBirth;

    @ApiModelProperty(value = "宠物类别")
    private String categoryCode;

    @ApiModelProperty(value = "宠物介绍")
    private String petDesc;

    @ApiModelProperty(value = "宠物状态 0-下架；1-上架")
    private Integer state;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


}
