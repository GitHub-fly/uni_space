package com.scs.web.uni_space.service.serviceImpl;

import com.scs.web.uni_space.common.Result;
import com.scs.web.uni_space.common.ResultCode;
import com.scs.web.uni_space.domain.dto.FriendDto;
import com.scs.web.uni_space.domain.entity.Friend;
import com.scs.web.uni_space.domain.entity.Journal;
import com.scs.web.uni_space.domain.entity.User;
import com.scs.web.uni_space.domain.vo.FriendVo;
import com.scs.web.uni_space.domain.vo.UserVo;
import com.scs.web.uni_space.mapper.FriendMapper;
import com.scs.web.uni_space.mapper.UserMapper;
import com.scs.web.uni_space.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author suyuxi
 * @className FriendServiceImpl
 * @Description 好友服务类
 * @Date 2019/12/4
 * @Version 1.0
 **/
@Service
@Slf4j
public class FriendServiceImpl implements FriendService {

    @Resource
    private FriendMapper friendMapper;

    @Resource
    private UserMapper userMapper;

//    @Resource
//    private CommonMapper commonMapper;

    /**
     * 推荐好友
     *
     * @param friendDto
     * @return Result
     */
    @Override
    public Result recommendFriend(FriendDto friendDto) {
        List<UserVo> list = new ArrayList<>(10);
        Long fromId = friendDto.getFromId();
        try {
            if (fromId != null) {
                // 取出所有用户的id
                List<Long> idList = friendMapper.selectAllId();
                // 遍历idList集合 返回非好友的集合
                int size = idList.size();
                for (int i = 0; i < size; i++) {
                    Long id = idList.get(i);
                    //排除自己id
                    if (!fromId.equals(id)) {
                        try {
                            //排除已是好友的id
                            if (friendMapper.selectFriendFlag(friendDto.getFromId(), id) == null) {
                                list.add(userMapper.selectUserById(id));
                            }
                        } catch (SQLException e) {
                            log.error("通过id查找指定用户信息出现异常");
                        }
                        if (list.size() == 10) {
                            return Result.success(list);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log.error("推荐好友出现异常");
        }
        return Result.failure(ResultCode.USER_FIND_ALL_FRIEND_ERROR);
    }

    @Override
    public Result searchJournal(FriendDto friendDto) {
        try {
            //非空判断
            if (friendDto.getToId() != null) {
                //调用通过id查找日志方法
                List<Journal> list = friendMapper.searchJournalByUserId(friendDto.getToId());
                if (list.size() != 0) {
                    //返回数据
                    return Result.success(list);
                } else {
                    return Result.failure(ResultCode.USER_NOT_JOURNAL);
                }
            }
        } catch (SQLException e) {
            log.error("查找指定用户的日志信息出错");
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    @Override
    public Result findAllByKey(FriendDto friendDto) {
        try {
            if (friendDto.getFromId() != null) {
                //当getKeyWords=null时，查找的是全部好友信息
                List<FriendVo> friendVoList = friendMapper.selectAll(friendDto.getFromId(), friendDto.getKeyWords());
                if (friendVoList.size() != 0) {
                    return Result.success(friendVoList);
                } else {
                    return Result.failure(ResultCode.USER_RETURN_DATA_ERROR);
                }
            }
        } catch (SQLException e) {
            log.error("查找好友异常");
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    @Override
    public Result searchFriendByKey(FriendDto friendDto) {
        try {
            if (friendDto.getFromId() != null && !friendDto.getKeyWords().equals("")) {
                //通过关键字搜索用户信息
                List<FriendVo> list = friendMapper.searchUserByKey(friendDto.getFromId(), friendDto.getKeyWords());
                if (list.size() != 0) {
                    return Result.success(list);
                } else {
                    return Result.failure(ResultCode.USER_FIND_ALL_FRIEND_ERROR);
                }
            }
        } catch (SQLException e) {
            log.error("通过关键字查找用户信息出错");
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    @Override
    public Result addFriend(FriendDto friendDto) {
        if (friendDto.getFromId().equals(friendDto.getToId())) {
            return Result.failure(ResultCode.USER_NOT_INSERT_OWN);
        } else {
            if (friendDto.getFromId() != null && friendDto.getToId() != null) {
                try {
                    //查看对方是否发过请求
                    Friend searchFriend = friendMapper.selectFriendFlag(friendDto.getToId(), friendDto.getFromId());
                    if (searchFriend == null) {
                        //查看自己是否发过请求
                        Friend friend = friendMapper.selectFriendFlag(friendDto.getFromId(), friendDto.getToId());
                        if (friend == null) {
//                            commonMapper.returnId("t_friend");
                            //如未发起请求，调用添加好友方法
                            friendMapper.insertOther(friendDto.getFromId(), friendDto.getToId());
                            return Result.success(ResultCode.SUCCESS);
                        } else if (friend.getFriendFlag() == 0) {
                            return Result.failure(ResultCode.USER_HAS_APPLICANT);
                        } else if (friend.getFriendFlag() == 1) {
                            return Result.failure(ResultCode.USER_HAS_FRIEND);
                        }
                    } else {
                        if (searchFriend.getFriendFlag() == 0) {
                            //如果对方已发起请求，调用更改好友状态放方法
                            friendMapper.updateFriendFlag(friendDto.getFromId(), friendDto.getToId());
//                            commonMapper.returnId("t_friend");
                            //再插入一条好友数据
                            friendMapper.insertEachOther(friendDto.getFromId(), friendDto.getToId());
                            return Result.success(ResultCode.SUCCESS);
                        } else if (searchFriend.getFriendFlag() == 1) {
                            return Result.failure(ResultCode.USER_HAS_FRIEND);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.error("添加好友异常");
                }
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);

    }


    @Override
    public Result findAllApplicant(FriendDto friendDto) {
        if (friendDto.getFromId() != null) {
            try {
                //查找所有添加请求信息
                List<User> users = friendMapper.selectByToId(friendDto.getFromId());
                if (users.size() != 0) {
                    return Result.success(users);
                } else {
                    return Result.failure(ResultCode.USER_NOT_FIND_ADD);
                }
            } catch (SQLException e) {
                log.error("查找所有添加请求异常");
            }
        }
        return Result.failure(ResultCode.USER_RETURN_DATA_ERROR);
    }


    @Override
    public Result confirmAdd(FriendDto friendDto) {
        if (friendDto.getFromId() != null && friendDto.getToId() != null) {
            try {
                //同意添加，更改状态
                friendMapper.updateFriendFlag(friendDto.getFromId(), friendDto.getToId());
            } catch (SQLException e) {
                log.error("更改请求状态异常");
                return Result.failure(ResultCode.USER_CONFIRM_ERROR);
            }
            try {
//                commonMapper.returnId("t_friend");
                //插入一条好友数据
                friendMapper.insertEachOther(friendDto.getFromId(), friendDto.getToId());
                return Result.success(ResultCode.SUCCESS);
            } catch (SQLException e) {
                log.error("同意添加异常");
                return Result.failure(ResultCode.USER_CONFIRM_ADD_ERROR);
            }
        }
        return Result.failure(ResultCode.USER_RETURN_DATA_ERROR);
    }


    @Override
    public Result rejectConfirm(FriendDto friendDto) {
        if (friendDto.getFromId() != null && friendDto.getToId() != null) {
            try {
                //拒绝请求，调用删除请求方法
                friendMapper.deleteReject(friendDto.getFromId(), friendDto.getToId());
                return Result.success(ResultCode.SUCCESS);
            } catch (SQLException e) {
                log.error("拒绝请求异常");
                return Result.failure(ResultCode.USER_REJECT_CONFIRM_ERROR);
            }
        }
        return Result.failure(ResultCode.USER_RETURN_DATA_ERROR);
    }


    @Override
    public Result deleteFriend(FriendDto friendDto) {
        if (friendDto.getFromId() != null && friendDto.getToId() != null) {
            try {
                //删除好友，两方都会删除
                friendMapper.deleteFriend(friendDto.getFromId(), friendDto.getToId());
                return Result.success(ResultCode.SUCCESS);
            } catch (SQLException e) {
                log.error("删除好友异常");
                return Result.failure(ResultCode.USER_DELETE_FRIEND_ERROR);
            }
        }
        return Result.failure(ResultCode.USER_RETURN_DATA_ERROR);
    }

    @Override
    public Result updateFriendCollectionFlag(FriendDto friendDto) {
        if(friendDto.getFromId() != null && friendDto.getToId() != null && friendDto.getCollectionFlag() != null){
            try {
                //修改对方访问权限
                friendMapper.updateCollectionFlag(friendDto.getFromId(),friendDto.getToId(),friendDto.getCollectionFlag());
                return Result.success(ResultCode.SUCCESS);
            } catch (SQLException e) {
                log.error("修改好友权限异常");
            }
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

}