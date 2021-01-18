package com.anvy.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CarePetDTO extends BaseDTO {
    /**
     * 宠物姓名
     */
    private String petName;

    /**
     * 宠物性别
     */
    private Integer petSex;

    /**
     * 健康状况 0-较好；1-良好；2-一般；3-较差
     */
    private Integer petHealth;
    /**
     * 健康状况 0-较好；1-良好；2-一般；3-较差
     */
    private Integer petBirth;
    /**
     * 宠物类别
     */
    private String categoryCode;

    /**
     * 宠物介绍
     */
    private String petDesc;

}