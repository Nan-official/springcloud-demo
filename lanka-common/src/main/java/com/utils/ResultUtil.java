package com.utils;


import com.entity.Result;

/**
 * 返回结果工具类
 */
public class ResultUtil<T> {

    private volatile static ResultUtil resultUtil;
//    private ResultUtil(){}
    public static ResultUtil getInstance() {
        if (resultUtil == null) {
            synchronized (ResultUtil.class) {
                if (resultUtil == null) {
                    resultUtil = new ResultUtil();
                }
            }
        }
        return resultUtil;
    }
    /**
     * 结果对象
     */
    private Result<T> result;

    /**
     * 构造函数初始化结果对象，以免其它方法都要初始化结果对象
     */
    public ResultUtil() {
        result = new Result<>();
        result.setSuccess(true);
        result.setMessage("success");
        result.setCode(200);
    }

    public Result<T> setData(T t) {
        this.result.setResult(t);
        this.result.setCode(200);
        return this.result;
    }

    public Result<T> setSuccessMsg(String msg) {
        this.result.setSuccess(true);
        this.result.setMessage(msg);
        this.result.setCode(200);
        this.result.setResult(null);
        return this.result;
    }

    public Result<T> setData(T t, String msg) {
        this.result.setResult(t);
        this.result.setCode(200);
        this.result.setMessage(msg);
        return this.result;
    }

    public Result<T> setErrorMsg(String msg) {
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(500);
        return this.result;
    }

    public Result<T> setErrorMsg(Integer code, String msg) {
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(code);
        return this.result;
    }

    public Result<T> setErrorMsg(Integer code, String msg, T result) {
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(code);
        this.result.setResult(result);
        return this.result;
    }

    public Result<T> setMsg(Integer code, String msg, T result) {
        this.result.setMessage(msg);
        this.result.setCode(code);
        this.result.setResult(result);
        return this.result;
    }
}
