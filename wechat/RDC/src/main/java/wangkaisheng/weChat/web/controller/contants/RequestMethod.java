package wangkaisheng.weChat.web.controller.contants;

/**
 * @author Administrator
 */
public enum RequestMethod {
    /**
     * 群聊未读消息列表
     */
    GROUPSMESSAGE_DO,
    /**
     * 好友未读消息列表
     */
    FRIENDMESSAGE_DO,
    /**
     * 加载朋友圈
     */
    LISTMOMENT_DO,
    /**
     * 发布朋友圈
     */
    ADDMOMENT_DO,
    /**
     * 删除通知
     */
    OFFNOTICED_DO,
    /**
     * 拒绝进群申请
     */
    OFFGROUP_DO,
    /**
     * 同意进群申请
     */
    ADDGROUP_DO,
    /**
     * 拒绝好友申请
     */
    OFFFRIEND_DO,
    /**
     * 查找已处理通知表单
     */
    NOTICEDLIST_DO,
    /**
     * 查找未处理通知表单
     */
    NOTICELIST_DO,
    /**
     * 发送群聊申请操作
     */
    ADDGROUPNOTICE_DO,
    /**
     * 发送好友申请操作
     */
    ADDFRIENDNOTICE_DO,
    /**
     * 创建群聊操作
     */
    CREATEGROUP_DO,
    /**
     * 查找群聊列表操作
     */
    LISTGROUP_DO,
    /**
     * 查找黑名单列表操作
     */
    LISTBLACK_DO,
    /**
     * 查找好友列表操作
     */
    LISTFRIEND_DO,
    /**
     * 执行登陆操作
     */
    LOGIN_DO,
    /**
     * 执行注册操作
     */
    REGIST_DO,
    /**
     * 无效请求
     */
    INVALID_REQUEST,
    /**
     * 执行修改信息操作
     */
    MODIFY_DO,
    /**
     * 添加黑名单
     */
    ADDBLACK_DO,
    /**
     * 删除黑名单
     */
    OFFBLACK_DO,
    /**
     * 添加好友
     */
    ADDFRIEND_DO,
    /**
     * 搜索用户
     */
    SEARCHFRIEND_DO,
    /**
     * 搜索用户
     */
    SEARCHGROUP_DO;

    @Override
    public String toString() {
        return super.toString().toLowerCase().replaceAll("_", ".");
    }

}
