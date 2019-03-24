/*
 * Copyright (c) 2019
 * @ClassName: xyz.uscnav.api.utils.net.UrlResp
 * @Description:
 * @Author: ZengBin
 * @Email: 271995865@qq.com
 * @Date: 2019/03/17 19:41:17
 */

package com.example.net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.security.Permission;
import java.util.List;
import java.util.Map;

public class UrlResp {
    private byte[] byteContent;
    private String errorMsg;
    private int responseCode;
    private HttpURLConnection conn;

    public UrlResp(HttpURLConnection conn, String encode) {
        this.conn = conn;
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;

        try {
            bis = new BufferedInputStream(conn.getInputStream());
            baos = new ByteArrayOutputStream();

            byte[] bytes = new byte[2048];
            int len = 0;
            while (-1 != (len = bis.read(bytes))) {
                baos.write(bytes, 0, len);
            }

            byteContent = baos.toByteArray();
            responseCode = conn.getResponseCode();

        } catch (IOException e) {
            e.printStackTrace();
            BufferedReader in = null;
//            错误信息
            try {
                StringBuffer msg = new StringBuffer(30);
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), encode));

                String line = null;
                while ((line = in.readLine()) != null) {
                    msg.append(line);
                }

                this.errorMsg = msg.toString();
                responseCode = -1;
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public byte[] getByteContent() {
        return byteContent;
    }

    public String getText(){
        return byteContent == null ? "" : new String(byteContent);
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        if (errorMsg != null)
            return errorMsg;

        if (byteContent != null) {
            return new String(byteContent);
        }
        return "";
    }

    public String getHeaderFieldKey(int n) {
        return conn.getHeaderFieldKey(n);
    }

    public String getHeaderField(int n) {
        return conn.getHeaderField(n);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage(){
        try {
            return conn.getResponseMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getHeaderFieldDate(String name, long Default) {
        return conn.getHeaderFieldDate(name, Default);
    }

    public Permission getPermission(){
        try {
            return conn.getPermission();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getContentLength() {
        return conn.getContentLength();
    }

    public long getContentLengthLong() {
        return conn.getContentLengthLong();
    }

    public String getContentType() {
        return conn.getContentType();
    }

    public String getContentEncoding() {
        return conn.getContentEncoding();
    }

    public long getExpiration() {
        return conn.getExpiration();
    }

    public long getDate() {
        return conn.getDate();
    }


    public String getHeaderField(String name) {
        return conn.getHeaderField(name);
    }

    public Map<String, List<String>> getHeaderFields() {
        return conn.getHeaderFields();
    }


    public int getHeaderFieldInt(String name, int Default) {
        return conn.getHeaderFieldInt(name, Default);
    }

    public long getHeaderFieldLong(String name, long Default) {
        return conn.getHeaderFieldLong(name, Default);
    }

    public HttpURLConnection getConn() {
        return conn;
    }

    /**
     * HTTP Status-Code 200: OK.
     */
    public static final int HTTP_OK = 200;

    /**
     * HTTP Status-Code 201: Created.
     */
    public static final int HTTP_CREATED = 201;

    /**
     * HTTP Status-Code 202: Accepted.
     */
    public static final int HTTP_ACCEPTED = 202;

    /**
     * HTTP Status-Code 203: Non-Authoritative Information.
     */
    public static final int HTTP_NOT_AUTHORITATIVE = 203;

    /**
     * HTTP Status-Code 204: No Content.
     */
    public static final int HTTP_NO_CONTENT = 204;

    /**
     * HTTP Status-Code 205: Reset Content.
     */
    public static final int HTTP_RESET = 205;

    /**
     * HTTP Status-Code 206: Partial Content.
     */
    public static final int HTTP_PARTIAL = 206;

    /* 3XX: relocation/redirect */

    /**
     * HTTP Status-Code 300: Multiple Choices.
     */
    public static final int HTTP_MULT_CHOICE = 300;

    /**
     * HTTP Status-Code 301: Moved Permanently.
     */
    public static final int HTTP_MOVED_PERM = 301;

    /**
     * HTTP Status-Code 302: Temporary Redirect.
     */
    public static final int HTTP_MOVED_TEMP = 302;

    /**
     * HTTP Status-Code 303: See Other.
     */
    public static final int HTTP_SEE_OTHER = 303;

    /**
     * HTTP Status-Code 304: Not Modified.
     */
    public static final int HTTP_NOT_MODIFIED = 304;

    /**
     * HTTP Status-Code 305: Use Proxy.
     */
    public static final int HTTP_USE_PROXY = 305;

    /* 4XX: client error */

    /**
     * HTTP Status-Code 400: Bad Urllib.
     */
    public static final int HTTP_BAD_REQUEST = 400;

    /**
     * HTTP Status-Code 401: Unauthorized.
     */
    public static final int HTTP_UNAUTHORIZED = 401;

    /**
     * HTTP Status-Code 402: Payment Required.
     */
    public static final int HTTP_PAYMENT_REQUIRED = 402;

    /**
     * HTTP Status-Code 403: Forbidden.
     */
    public static final int HTTP_FORBIDDEN = 403;

    /**
     * HTTP Status-Code 404: Not Found.
     */
    public static final int HTTP_NOT_FOUND = 404;

    /**
     * HTTP Status-Code 405: Method Not Allowed.
     */
    public static final int HTTP_BAD_METHOD = 405;

    /**
     * HTTP Status-Code 406: Not Acceptable.
     */
    public static final int HTTP_NOT_ACCEPTABLE = 406;

    /**
     * HTTP Status-Code 407: Proxy Authentication Required.
     */
    public static final int HTTP_PROXY_AUTH = 407;

    /**
     * HTTP Status-Code 408: Urllib Time-Out.
     */
    public static final int HTTP_CLIENT_TIMEOUT = 408;

    /**
     * HTTP Status-Code 409: Conflict.
     */
    public static final int HTTP_CONFLICT = 409;

    /**
     * HTTP Status-Code 410: Gone.
     */
    public static final int HTTP_GONE = 410;

    /**
     * HTTP Status-Code 411: Length Required.
     */
    public static final int HTTP_LENGTH_REQUIRED = 411;

    /**
     * HTTP Status-Code 412: Precondition Failed.
     */
    public static final int HTTP_PRECON_FAILED = 412;

    /**
     * HTTP Status-Code 413: Urllib Entity Too Large.
     */
    public static final int HTTP_ENTITY_TOO_LARGE = 413;

    /**
     * HTTP Status-Code 414: Urllib-URI Too Large.
     */
    public static final int HTTP_REQ_TOO_LONG = 414;

    /**
     * HTTP Status-Code 415: Unsupported Media Type.
     */
    public static final int HTTP_UNSUPPORTED_TYPE = 415;

    /* 5XX: server error */

    /**
     * HTTP Status-Code 500: Internal Server Error.
     * @deprecated   it is misplaced and shouldn't have existed.
     */
    @Deprecated
    public static final int HTTP_SERVER_ERROR = 500;

    /**
     * HTTP Status-Code 500: Internal Server Error.
     */
    public static final int HTTP_INTERNAL_ERROR = 500;

    /**
     * HTTP Status-Code 501: Not Implemented.
     */
    public static final int HTTP_NOT_IMPLEMENTED = 501;

    /**
     * HTTP Status-Code 502: Bad Gateway.
     */
    public static final int HTTP_BAD_GATEWAY = 502;

    /**
     * HTTP Status-Code 503: Service Unavailable.
     */
    public static final int HTTP_UNAVAILABLE = 503;

    /**
     * HTTP Status-Code 504: Gateway Timeout.
     */
    public static final int HTTP_GATEWAY_TIMEOUT = 504;

    /**
     * HTTP Status-Code 505: HTTP Version Not Supported.
     */
    public static final int HTTP_VERSION = 505;

}
