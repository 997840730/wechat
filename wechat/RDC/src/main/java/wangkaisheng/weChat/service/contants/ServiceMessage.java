package wangkaisheng.weChat.service.contants;

public enum ServiceMessage {
    /**
     *获取群聊未读信息失败
     */
    FINDMESSAGE_READ_ERROR("获取群聊未读信息失败"),
    /**
     *获取群聊未读信息成功
     */
    FINDMESSAGE_READ_SUCCESS("获取群聊未读信息成功"),
    /**
     *获取好友未读消息列表失败
     */
    FRIEND_MESSAGE_ERROR("获取好友未读消息列表失败"),
    /**
     *获取好友未读消息列表成功
     */
    FRIEND_MESSAGE_SUCCESS("获取好友未读消息列表成功"),
    /**
     *朋友圈列表
     */
    MOMENT_LIST_SUCCESS("朋友圈列表!"),
    /**
     *朋友圈列表为空
     */
    MOMENT_LIST_ERROR("朋友圈列表为空!"),
    /**
     *朋友圈发布失败
     */
    IMGS_ADD_ERROR("朋友圈发布失败!"),
    /**
     *朋友圈发布成功
     */
    IMGS_ADD_SUCCESS("朋友圈发布成功!"),
    /**
     *朋友圈上传失败
     */
    MOMENT_ADD_ERROR("朋友圈上传失败！"),
    /**
     *朋友圈上传成功
     */
    MOMENT_ADD_SUCCESS("朋友圈上传成功！"),
    /**
     *删除通知失败
     */
    NOTICED_DELETE_ERROR("删除通知失败!"),
    /**
     *删除通知成功
     */
    NOTICED_DELETE_SUCCESS("删除通知成功!"),
    /**
     *拒绝进群申请
     */
    MEMBER_ADD_ERROR("拒绝进群申请"),
    /**
     *同意进群申请
     */
    MEMBER_ADD_SUCCESS("同意进群申请"),
    /**
     *删除申请信息失败
     */
    NOTICE_DELETE_ERROR("删除申请信息失败"),
    /**
     *删除申请信息成功
     */
    NOTICE_DELETE_SUCCESS("删除申请信息成功"),
    /**
     *好友已同意添加失败
     */
    NOTICED_ADD_ERROR("好友已同意添加失败"),
    /**
     *好友已同意添加成功
     */
    NOTICED_ADD_SUCCESS("好友已同意添加成功"),
    /**
     *好友同意添加失败
     */
    ADDFRIEND_ERROR("好友同意添加失败"),
    /**
     *好友拒绝添加失败
     */
    OFFFRIEND_ERROR("好友拒绝添加失败"),
    /**
     *好友拒绝添加成功
     */
    OFFFRIEND_SUCCESS("好友拒绝添加成功"),
    /**
     *好友同意添加成功
     */
    ADDFRIEND_SUCCESS("好友同意添加成功"),
    /**
     *响应消息列表不为空
     */
    NOTICED_NOEMPTY("响应消息列表不为空"),
    /**
     *响应消息列表为空
     */
    NOTICED_EMPTY("响应消息列表为空"),
    /**
     *请求消息列表不为空
     */
    NOTICE_NOEMPTY("请求消息列表不为空"),
    /**
     *请求消息列表为空
     */
    NOTICE_EMPTY("请求消息列表为空"),
    /**
     *发送申请失败
     */
    NOTICE_ERROR("发送申请失败"),
    /**
     *发送申请成功
     */
    NOTICE_SUCCESS("发送申请成功"),
    /**
     *您已发送了请求
     */
    NOTICE_EXIST("您已发送了请求!"),
    /**
     *群聊创建失败
     */
    GROUR_CREATE_ERROR("群聊创建失败"),
    /**
     *群聊创建成功
     */
    GROUR_CREATE_SUCCESS("群聊创建成功"),
    /**
     *已找到相关群
     */
    GROUP_YES("已找到相关群！"),
    /**
     *未找到相关群
     */
    GROUP_NO("未找到相关群！"),
    /**
     *参数不足
     */
    PARAMETER_NOT_ENOUGHT("请求参数不足，无法执行服务"),
    /**
     * 账号不存在
     */
    ACCOUNT_NOT_FOUND("该微信账号不存在，请检查您的输入是否正确"),
    /**
     * 密码错误
     */
    PASSWORD_INCORRECT("密码错误，请核对您的密码是否正确"),
    /**
     * 登录成功
     */
    LOGIN_SUCCESS("登陆成功，欢迎进入wechat在线聊天系统"),
    /**
     * 数据库错误
     */
    DATABASE_ERROR("发现未知名错误！"),
    /**
     * 验证码错误
     */
    VERIFIED_ISERROR("验证码错误，请重新输入！"),
    /**
     * 账号不得为空
     */
    USERNAME_ISEMPTY("账号不得为空"),
    /**
     * 密码不得为空
     */
    PASSWORD_ISEMPTY("密码不得为空"),
    /**
     * 电话不得为空
     */
    PHONE_ISEMPTY("电话不得为空"),
    /**
     * 名称不得为空
     */
    NAME_ISEMPTY("名称不得为空"),
    /**
     * 账号已存在
     */
    USERNAME_EMPTY("账号已存在"),
    /**
     * 注册成功
     */
    REGIST_SUCCESS("注册成功"),
    /**
     * 邮箱不得为空
     */
    EMAIL_ISEMPTY("邮箱不得为空"),
    /**
     * 检查两次密码不相同
     */
    PASSWORD_NOSAME("两次输入密码不同，请仔细检查！"),
    /**
     * 信息修改成功
     */
    USERMODIFY_SUCCESS("信息修改成功！"),
    /**
     * 信息修改失败
     */
    USERMODIFY_ERROR("信息修改失败"),
    /**
     * 黑名单添加成功
     */
    ADDBLACK_SUCCESS("黑名单添加成功!"),
    /**
     * 黑名单添加失败
     */
    ADDBLACK_ERROR("黑名单添加失败!"),
    /**
     * 黑名单取消成功
     */
    OFFBLACK_SUCCESS("黑名单取消成功！"),
    /**
     * 黑名单取消失败
     */
    OFFBLACK_ERROR("黑名单取消失败！"),
    /**
     * 未找到相关用户与群
     */
    USER_NO("未找到相关用户与群！"),
    /**
     * 已找到相关用户
     */
    USER_YES("已找到相关用户！")
    ;

    public String message;

    ServiceMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
