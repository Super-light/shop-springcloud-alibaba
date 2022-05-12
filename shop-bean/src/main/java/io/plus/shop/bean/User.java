package io.plus.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import io.plus.shop.utils.id.SnowFlakeFactory;
import io.plus.shop.utils.pwd.PasswordUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author Super_light
 * @date 5/10/22 6:09 PM
 */
@Data
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -7032479567987350240L;

    /**
     * 数据id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @TableField(value = "id", fill = FieldFill.INSERT)
    private Long id;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String username;

    /**
     * 密码
     */
    @TableField("t_password")
    private String password;

    /**
     * 手机号
     */
    @TableField("t_phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("t_address")
    private String address;

    public User(){
        this.id = SnowFlakeFactory.getSnowFlakeFromCache().nextId();
        //默认密码
        this.password = PasswordUtils.base64Encode("123456");
    }
}