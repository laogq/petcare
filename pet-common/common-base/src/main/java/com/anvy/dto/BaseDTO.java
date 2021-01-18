package com.anvy.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* @Description: 通用实体类。
* @author Anvy Lao
* @date 2020/5/12 15:59
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseDTO {

    // 页码
    private Integer page;
    // 一页展示条数
    private Integer limit;
    // 操作员标识
    private Long opId;
    /**
     * 宠物状态 0-下架；1-上架
     */
    private Integer state;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    // 更新时间
    private Date updateTime;
}