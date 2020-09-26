package serviceapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Nxy
 * @title: UserDTO
 * @projectName lanka
 * @description: TODO
 */
@Data
public class UserDTO {

    @ApiModelProperty(value = "账号")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

}
