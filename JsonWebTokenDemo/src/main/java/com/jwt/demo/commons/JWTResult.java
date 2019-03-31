package com.jwt.demo.commons;

import io.jsonwebtoken.Claims;

public class JWTResult {
    // 失败代码
    private int errCode;
    // 是否成功
    private boolean success;
    // 验证过程中payload中的数据
    private Claims claims;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }
}
