package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.mapper.FriendMapper;
import com.scs.web.uni_space.service.FriendService;
import com.scs.web.uni_space.util.Result;
import com.scs.web.uni_space.util.ResultCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author suyuxi
 * @className FriendServiceImpl
 * @Description 好友操作类
 * @Date 2019/12/4
 * @Version 1.0
 **/
@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    private FriendMapper friendMapper;

    @Override
    public Result findAllFriend(Friend friend) {
        List<User> users = friendMapper.selectByFromId(friend.getFromId());
        if (users != null){
            return Result.success(users);
        }else {
            return Result.failure(ResultCode.USER_FIND_ALL_FRIEND_ERROR);
        }
    }

    @Override
    public Result addFriend(Friend friend) {
        if (friend.getFromId().equals(friend.getToId())){
            return Result.failure(ResultCode.USER_NOT_INSERT_OWN);
        }else {
            Friend searchFriend = friendMapper.selectFriendFlag(friend.getFromId(),friend.getToId());
            if (searchFriend == null){
                int j = friendMapper.insertOther(friend.getFromId(),friend.getToId());
                if (j != 0){
                    return Result.success(ResultCode.SUCCESS);
                }else {
                    return Result.failure(ResultCode.USER_INSERT_FRIEND_ERROR);
                }
            }else {
                return Result.failure(ResultCode.USER_HAS_APPLICANT);
            }
        }
    }

    @Override
    public Result findAllApplicant(Friend friend) {
        List<User> users= friendMapper.selectByToId(friend.getToId());
        if (users != null){
            return Result.success(users);
        }else {
            return Result.failure(ResultCode.USER_FIND_ALL_APPLICANT);
        }
    }

    @Override
    public Result confirmAdd(Friend friend) {
        int i = friendMapper.updateFriendFlag(friend.getFromId(),friend.getToId());
        if (i != 0){
            int j = friendMapper.insertEachOther(friend.getFromId(),friend.getToId());
            if (j != 0){
                return Result.success(ResultCode.SUCCESS);
            }else {
                return Result.failure(ResultCode.USER_CONFIRM_ADD_ERROR);
            }
        }else {
            return Result.failure(ResultCode.USER_CONFIRM_ERROR);
        }
    }

    @Override
    public Result rejectConfirm(Friend friend) {
        int i = friendMapper.deleteReject(friend.getFromId(),friend.getToId());
        if (i != 0){
            return Result.success(ResultCode.SUCCESS);
        }else {
            return Result.failure(ResultCode.USER_REJECT_CONFIRM_ERROR);
        }
    }

    @Override
    public Result deleteFriend(Friend friend) {
        int i = friendMapper.deleteFriend(friend.getFromId(),friend.getToId());
        if (i != 0){
            return Result.success(ResultCode.SUCCESS);
        }else {
            return Result.failure(ResultCode.USER_DELETE_FRIEND_ERROR);
        }
    }
}
