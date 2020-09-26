package serviceapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台管理用户表
 * </p>
 *
 * @author Nxy
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("lk_manager")
@ApiModel(value="LkManager对象", description="后台管理用户表")
public class LkManager extends Model<LkManager> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "账号")
    @TableField("user_account")
    private String userAccount;

    @ApiModelProperty(value = "名字")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "md5密码盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "联系电话")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "备注")
    @TableField("tips")
    private String tips;

    @ApiModelProperty(value = "状态 1:正常 2:禁用")
    @TableField("state")
    private Boolean state;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
