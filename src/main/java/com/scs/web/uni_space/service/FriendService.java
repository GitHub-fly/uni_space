package com.scs.web.uni_space.service;


import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.util.Result;

/**
 * @author suyuxi
 * @className FriendService
 * @Description 好友操作接口
 * @Date 2019/12/3
 * @Version 1.0
 **/
public interface FriendService  {

    /**
     * 查找所有好友
     * @param friend
     * @return
     */
    Result findAllFriend(Friend friend);

    /**
     * 发起添加好友请求
     * @param friend
     * @return
     */
    Result addFriend(Friend friend);


    /**
     * 查找所有申请信息
     * @param friend
     * @return Result
     */
    Result findAllApplicant(Friend friend);

    /**
     * 确认请求
     * @param friend
     * @return Result
     */
    Result confirmAdd(Friend friend);

    /**
     * 拒绝请求
     * @param friend
     * @return Result
     */
    Result rejectConfirm(Friend friend);

    /**
     * 删除好友
     * @param friend
     * @return Result
     */
    Result deleteFriend(Friend friend);
}
