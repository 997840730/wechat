package wangkaisheng.weChat.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
public class JSONutil {
    public static void setJsonUtf8(HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");
    }

    /**
     * 将数据与信息通过json格式发送给页面执行回调函数
     * @param response 响应
     * @author
     * @date 2021/5/11
     */
    public static void responseByJson(HttpServletResponse response,Object object) throws IOException, IOException {
        setJsonUtf8(response);
        JSON json = (JSON) JSON.toJSON(object);
        response.getWriter().write(json.toJSONString());
    }
}
