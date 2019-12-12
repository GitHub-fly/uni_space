package com.scs.web.uni_space.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小黑
 * @ClassNamere
 * @Description TODO
 * @Date 2019/11/17
 * @Version 1.0
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(1, "成功"),


    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户错误：20001-29999*/
    USER_NOT_SIGN_IN(20001, "用户未登录"),
    USER_PASSWORD_ERROR(20002, "密码错误"),
    USER_ACCOUNT_ERROR(20003, "账号错误"),
    USER_VERIFY_CODE_ERROR(20004, "验证码错误"),

    USER_ACCOUNT_FORBIDDEN(20005, "账号已被禁用"),
    USER_NOT_EXIST(20006, "用户不存在"),
    USER_HAS_EXISTED(20007, "用户已存在"),
    USER_MOBILE_NOT_EXIST(20008,"手机号不存在"),
    USER_ACCOUNT_NOT_EXIST(20009,"账号不存在"),
    USER_EMAIL_NOT_EXIST(20010,"邮箱不存在"),
    PORT_PARAM(20011,"输入不能为空"),
    USER_NOT_IMPORT_MOBILE(20012,"输入手机号不能为空"),
    USER_ADD_FAILURE(20013,"注册失败"),
    USER_PASSWORD_REPEAT(20014,"两次密码不能重复"),
    USER_VERIFY_CODE_NULL(20015, "此手机未发送验证码"),
    USER_LOGIN_FAILURE(20016,"登录失效"),
    USER_PASSWORD_NULL(20017,"密码不存在"),
    USER_VERIFY_ERROR(20018,"验证码错误"),

    /*用户好友模块错误：20101-20199*/
    USER_NOT_INSERT_OWN(20101,"不能添加自己为好友"),
    USER_HAS_FRIEND(20102,"好友已存在"),
    USER_INSERT_FRIEND_ERROR(20103,"请求添加错误"),
    USER_HAS_APPLICANT(20104,"已发过请求,请等待对方回复"),
    USER_FIND_ALL_APPLICANT(20105,"查找所有请求错误"),
    USER_CONFIRM_ERROR(20106,"确认请求错误"),
    USER_CONFIRM_ADD_ERROR(20107,"确认请求添加错误"),
    USER_DELETE_FRIEND_ERROR(20108,"删除好友错误"),
    USER_FIND_ALL_FRIEND_ERROR(20109,"查找所有好友错误"),
    USER_REJECT_CONFIRM_ERROR(20110,"拒绝请求错误"),
    USER_NOT_IMPORT_ERROR(20111,"图片未上传"),
    USER_RETURN_DATA_ERROR(20111,"好友操作返回数据错误"),


    /*用户密保模块错误：20201-20299*/
    USER_HAS_ACCOUNT_SECURITY(20201,"账号密保已设定"),
    USER_ADD_ACCOUNT_SECURITY_ERROR(20202,"添加账号密保错误"),
    USER_ADD_PHOTO_ALBUM_SECURITY_ERROR(20203,"添加相册密保错误"),
    USER_FIND_ACCOUNT_SECURITY_ERROR(20204,"查找账号密保错误"),
    USER_FIND_PHOTO_ALBUM_SECURITY_ERROR(20205,"查找相册密保错误"),
    USER_UPDATE_ACCOUNT_SECURITY_ERROR(20206,"更改账号密保错误"),
    USER_UPDATE_PHOTO_ALBUM_SECURITY_ERROR(20207,"更改相册密保错误"),
    USER_DELETE_ACCOUNT_SECURITY_ERROR(20208,"删除账号密保错误"),
    USER_DELETE_PHOTO_ALBUM_SECURITY_ERROR(20209,"删除相册密保错误"),
    USER_HAS_PHOTO_ALBUM_SECURITY(20210,"相册密保已设定"),
    USER_SECURITY_DATA_ERROR(20211,"密保操作返回数据错误"),



    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 数据错误：50001-599999 */
    RESULT_CODE_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }


    /**
     * 校验重复的code值
     *
     * @param args
     */
    public static void main(String[] args) {
        ResultCode[] ApiResultCodes = ResultCode.values();
        List<Integer> codeList = new ArrayList<Integer>();
        for (ResultCode ApiResultCode : ApiResultCodes) {
            if (codeList.contains(ApiResultCode.code)) {
                System.out.println(ApiResultCode.code);
            } else {
                codeList.add(ApiResultCode.code());
            }
        }
    }
}