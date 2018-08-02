package com.xiaohan.encrypt;

import com.xiaohan.utils.BcdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 12:46
 * @Description:
 */
public final class Xor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Xor.class);

    public static byte[] encode(byte[] content1,byte[] content2){
        int i=0;
        int len = content1.length;
        byte[] res=new byte[len];
        for( i=0; i<len; i++){
            res[i]=(byte) (content1[i]^content2[i]);
        }
        return res;
    }

    public static String encode(String content1, String content2){
        return BcdUtil.bytesToHexString(encode(BcdUtil.hexStringToByte(content1), BcdUtil.hexStringToByte(content2)));
    }
}
