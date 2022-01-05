package com.nicasia.rfc.util;

import com.nicasia.rfc.security.jwt.AuthUtil;

public class ReferenceCodeUtil {

    public static String getRefCode(){
        String dateTime=System.currentTimeMillis()+"";
        return dateTime.substring(8)+"/"+ AuthUtil.getCurrentUser().getId();
    }
}
