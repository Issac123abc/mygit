package cn.com.nexwise.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {

    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("image")
    private String image;

    @TableField("open_id")
    private String openId;

    @TableField("create_time")
    private Date createTime;

    @TableField("is_del")
    private Integer isDel;
}
