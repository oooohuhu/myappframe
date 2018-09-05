package com.wangy.mobile.klsk.data.commons;

import com.wangy.mobile.klsk.net.ApiStores;

/**
 * Created by xueqili on 2018/6/26.
 */

public class Constants {

    public static String getImageUrl(String id) {
        return ApiStores.BASE_URL + "indexImage/downloadIndexImg?id=" + id;
    }

    public static String getAppIconUrl(String appId) {
        return ApiStores.BASE_URL + "indexImage/downloadAppIcon?appId=" + appId;
    }
}
