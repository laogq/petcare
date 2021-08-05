package com.anvy.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * @author Anvy Lao
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PetUser对象", description="")
public class PetUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "USER_ID",type = IdType.ASSIGN_ID)
    private Long userId;

    @TableField("USER_NAME")
    private String userName;

    @TableField("USER_PASSWORD")
    private String userPassword;

    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    @TableField("UPDATE_DATE")
    private LocalDateTime updateDate;

    public PetUser(){

    }
    public PetUser(String userName){
        this.userName = userName;
    }

    public PetUser(String userName, String userPassword){
        this.userName = userName;
        this.userPassword = userPassword;
    }


}
