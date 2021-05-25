package wangkaisheng.weChat.web.controller.provider;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Imgs;
import wangkaisheng.weChat.po.Moments;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.service.contants.ServiceMessage;
import wangkaisheng.weChat.util.UUIDutils;
import wangkaisheng.weChat.web.controller.annotation.Action;
import wangkaisheng.weChat.web.controller.annotation.ActionProvider;
import wangkaisheng.weChat.web.controller.contants.RequestMethod;
import wangkaisheng.weChat.web.controller.contants.Status;
import wangkaisheng.weChat.web.controller.contants.WebPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import static wangkaisheng.weChat.util.JSONutil.responseByJson;

/**
 * @author Administrator
 */
@ActionProvider(path = "/moment")
public class MomentProvider extends Provider {
    @Action(method = RequestMethod.ADDMOMENT_DO)
    public void addmoment(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        if (!ServletFileUpload.isMultipartContent(request)){
            throw new RuntimeException("不支持该文件上传！");
        }
        User user = (User) request.getSession().getAttribute("wechat");
        Moments moments = new Moments();
        moments.setUid(user.getWechatId());
        moments.setTalk(request.getParameter("moments_word"));
        String uu = UUIDutils.getUUID();
        moments.setUuid(uu);
        ServiceResult result = momentService.addmoment(moments);
        if (Status.ERROR.equals(result.getStatus())){
            request.setAttribute("message",result.getMessage());
            request.setAttribute("data",user);
            request.getRequestDispatcher("/"+WebPage.HOME_JSP.toString()).forward(request,resp);
            return;
        }
        moments  = (Moments) result.getData();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(new java.io.File(request.getServletContext().getRealPath("/temp")));
            ServletFileUpload upload = new ServletFileUpload(factory);
            factory.setSizeThreshold(1024*1024);
            upload.setHeaderEncoding("UTF-8");
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    System.out.println("name:"+name);
                    System.out.println("value:"+value);
                } else {
                    String fileName = String.valueOf(System.currentTimeMillis());
                    String[] split1 = item.getName().split("\\.");
                    fileName = fileName+"."+split1[split1.length-1];
                    System.out.println(fileName);
                    InputStream inputStream = item.getInputStream();
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH)+1;
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    String path =request.getServletContext().getRealPath("/images")+ "/"+year+"/"+month+"/"+day;
                    java.io.File file = new java.io.File(path);
                    if (!file.exists()){
                        file.mkdirs();
                    }
                    java.io.File files = new java.io.File(path,fileName);
                    OutputStream outputStream = new FileOutputStream(files);
                    int len = -1;
                    byte[] bytes = new byte[1024];
                    while ((len=inputStream.read(bytes))!=-1){
                        outputStream.write(bytes,0,len);
                    }
                    outputStream.close();
                    inputStream.close();
                    String s = path+"/"+fileName;
                    String[] split = s.split("images");
                    s = "/images"+split[1];
                    Imgs imgs = new Imgs();
                    imgs.setImg(s);
                    imgs.setMid(moments.getId());
                    result = momentService.insertImgs(imgs);
                }
                item.delete();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        request.setAttribute("message",result.getMessage());
        request.setAttribute("data",user);
        request.getRequestDispatcher("/"+ WebPage.HOME_JSP.toString()).forward(request,resp);
    }

    @Action(method = RequestMethod.LISTMOMENT_DO)
    public void listmoment(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("wechat");
        List<User> friends = friendService.findFriends(user);
        friends.add(user);
        List<Moments> moments=  momentService.listmoments(friends);
        List<Moments> allmoments = momentService.listmoment(moments);
        ServiceResult requestMethod;
        if (allmoments!=null){
            requestMethod = new ServiceResult(Status.SUCCESS, ServiceMessage.MOMENT_LIST_SUCCESS.message,allmoments);
        }else {
            requestMethod = new ServiceResult(Status.SUCCESS, ServiceMessage.MOMENT_LIST_ERROR.message,null);
        }
        responseByJson(resp,requestMethod);
    }
}
