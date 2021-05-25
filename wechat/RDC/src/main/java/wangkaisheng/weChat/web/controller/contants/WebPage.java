package wangkaisheng.weChat.web.controller.contants;

public enum WebPage {
    /**
     * 跳转到error界面
    */
    ERROR_JSP,
    /**
     * 跳转到login界面
     */
    LOGIN_JSP,
    /**
     * 跳转到home界面
     */
    HOME_JSP,
    /**
     * 跳转到regist界面
     */
    REGIST_JSP;
    @Override
    public String toString() {
        return super.toString().toLowerCase().replaceAll("_", ".");
    }
}
