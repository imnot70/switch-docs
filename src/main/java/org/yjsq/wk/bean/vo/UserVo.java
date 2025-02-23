package org.yjsq.wk.bean.vo;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.yjsq.wk.bean.entity.User;
import org.yjsq.wk.common.StringConstant;

@Data
public class UserVo {
    private Long id;
    private String loginName;
    private String nickName;
    private String email;
    private String phone;
    private String gender;
    private String avatar;
    private String source;
    private String status;

    private boolean resetPwd;

    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;

    public UserVo() {
    }

    public UserVo(User user){
        this.id = user.getId();
        this.loginName= user.getLoginName();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.source = user.getSource();
        this.gender = User.convertGender(user.getGender());
        this.status = String.valueOf(user.getStatus());

        this.createBy = user.getCreateBy();
        this.updateBy = user.getUpdateBy();
        if(user.getCreateTime() != null){
            this.createTime = DateUtil.format(user.getCreateTime(), StringConstant.DATE_ONLY_PATTERN);
        }
        if(user.getUpdateTime() != null){
            this.updateTime = DateUtil.format(user.getUpdateTime(),StringConstant.DATE_ONLY_PATTERN);
        }
    }
}
