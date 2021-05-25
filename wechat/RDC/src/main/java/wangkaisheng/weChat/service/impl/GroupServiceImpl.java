package wangkaisheng.weChat.service.impl;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Groups;
import wangkaisheng.weChat.po.Member;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.service.GroupService;
import wangkaisheng.weChat.service.Service;
import wangkaisheng.weChat.service.contants.ServiceMessage;
import wangkaisheng.weChat.web.controller.contants.Status;

import java.util.LinkedList;
import java.util.List;

import static wangkaisheng.weChat.service.contants.ServiceMessage.MEMBER_ADD_ERROR;
import static wangkaisheng.weChat.service.contants.ServiceMessage.MEMBER_ADD_SUCCESS;

/**
 * @author Administrator
 */
public class GroupServiceImpl extends Service implements GroupService {
    @Override
    public List<Groups> findGroups(User user) {
        Member member = new Member();
        member.setUid(user.getWechatId());
        List<Member> members = memberDao.getMember(member);
        List<Groups> groups = new LinkedList<>();
        for (Member m:members){
            groups.add(groupDao.getGroups(m));
        }
        return groups;
    }

    @Override
    public ServiceResult searchGroup(String parameter,User myuser) {
        Groups groups = new Groups();
        groups.setGroupname(parameter);
        List<Groups> list =  groupDao.searchGroup(groups);
        groups.setGroupname(null);
        List<Groups> groupsList = findGroups(myuser);
        for (int i = 0;i<list.size();i++){
            for (Groups temp:groupsList){
                if (myuser.getWechatId().equals(list.get(i).getGroupUser())||temp.getGroupId().equals(list.get(i).getGroupId())){
                    list.remove(i);
                    i--;
                    break;
                }
            }

        }
        if (list.isEmpty()){
            return new ServiceResult(Status.ERROR, ServiceMessage.GROUP_NO.message,null);
        }else {
            return new ServiceResult(Status.SUCCESS, ServiceMessage.GROUP_YES.message,list);
        }
    }

    @Override
    public ServiceResult creatdGroup(Groups groups) {
        if (groups.getIntroduce()==null){
            groups.setIntroduce("群主很懒什么都没有留下！");
        }
        if (groupDao.findGroup(groups)){
            return new ServiceResult(Status.ERROR,ServiceMessage.GROUR_CREATE_ERROR.message,null);
        }
        if (groupDao.insertGroup(groups)){
            groups = groupDao.findGroupByid(groups);
            Member member = new Member();
            member.setGid(groups.getGroupId());
            member.setUid(groups.getGroupUser());
            groupDao.insertMember(member);
            return new ServiceResult(Status.SUCCESS,ServiceMessage.GROUR_CREATE_SUCCESS.message,groups);
        }else {
            return new ServiceResult(Status.ERROR,ServiceMessage.GROUR_CREATE_ERROR.message,null);
        }
    }

    @Override
    public ServiceResult insertGroup(Member member) {
        if(groupDao.insertMember(member)){
            return new ServiceResult(Status.SUCCESS,MEMBER_ADD_SUCCESS.message,null);
        }else {
            return new ServiceResult(Status.ERROR, MEMBER_ADD_ERROR.message, null);
        }
    }
}
