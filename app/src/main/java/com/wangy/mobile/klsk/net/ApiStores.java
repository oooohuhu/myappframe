package com.wangy.mobile.klsk.net;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangy.mobile.klsk.data.entity.BaseModel;
import com.wangy.mobile.klsk.data.entity.UserInfo;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface

ApiStores {


    //周锐
//    String BASE_HTTP_URL1 = "http://192.168.217.48:8081/";
//    String BASE_HTTP_URL = "http://192.168.217.42:8080/";
    //生产
    String BASE_HTTP_URL = "http://60.174.249.204:30002/";

    String BASE_URL = BASE_HTTP_URL + "soical-hefei/static/index/api/";
//        String BASE_URL = BASE_HTTP_URL + "social-hefi/static/index/api/";

    String IMAGE_URL = BASE_HTTP_URL + "soical-hefei/";

    String PUSH_URL = BASE_HTTP_URL + "jpush-server/static/index/api/";

    String APK_BASE_URL = BASE_HTTP_URL + "apps/";

    @POST("hefeitong/getInfoByToken")
    Observable<BaseModel<UserInfo>> login(@Query("token") String token, @Query("appCode") String appCode);

    //
    @FormUrlEncoded
    @POST("service/getAppCategoryList")
    Observable<BaseModel<JSONObject>> getAppCategory(@Field("userId") String userId);

    @POST("getNewsCategoryAllNews")
    Observable<BaseModel<JSONObject>> getNewsCategoryAllNews(@Query("newsCategoryId") String newsCategoryId, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("userid") String userid);

    @POST("getNewsCategroy")
    Observable<BaseModel<JSONObject>> getNewsCategroy();

    @POST("hefeitong/getIndexBannerList")
    Observable<BaseModel<JSONObject>> getIndexBannerList();

    @FormUrlEncoded
    @POST("service/getIndexServiceList")
    Observable<BaseModel<JSONObject>> getAppListInfo(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("service/getServiceList")
    Observable<BaseModel<JSONObject>> getAppMore(@Field("userId") String userId);

    @Multipart
    @POST("saveFile/save")
    Observable<BaseModel<String>> uploadImgs( @Part List<MultipartBody.Part> partList);

    @POST("govInteractor/saveGovInteractor")
    Observable<BaseModel<JSONObject>> uploadNoImgs(@Query("parentName") String parentName, @Query("parentId") String parentId, @Query("userId") String userId, @Query("userName") String userName, @Query("title") String title, @Query("context") String context, @Query("deptName") String deptName, @Query("type") int type);

    @FormUrlEncoded
    @POST("getAppGovInteractor")
    Observable<BaseModel<JSONObject>> getGovernListInfo(@Field("type") String type,
                                                        @Field("parentId") String parentId,
                                                        @Field("pageNo") int pageNo,
                                                        @Field("pageSize") int pageSize,
                                                        @Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("hefeitong/savePlServiceList")
    Observable<BaseModel<Object>> saveServiceList(@Field("userId") String userId, @Field("appList") String appList);

    @POST("getAllAppGovInteractorCategory")
    Observable<BaseModel<JSONObject>> getAllAppGovInteractorCategory();

    @POST("getAppGovInteractor")
    Observable<BaseModel<JSONObject>> getAppGovInteractor(@Query("type") int type,
                                                          @Query("parentId") String parentId,
                                                          @Query("pageNo") int pageNo,
                                                          @Query("pageSize") int pageSize,
                                                          @Query("userId") String userId);


    @POST("getAllDept")
    Observable<BaseModel<JSONArray>> getAllDept();

    @FormUrlEncoded
    @POST("govInteractor/saveGovInteractor")
    Observable<BaseModel<JSONObject>> uploadGovInfo(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("hefeitong/visitHFT")
    Observable<BaseModel<JSONObject>> visitHFT(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("hefeitong/getAllServicewithCategory")
    Observable<BaseModel<JSONObject>> getAllServicewithCategory(@Field("userId") String userId);


    @FormUrlEncoded
    @POST("hefeitong/getIndexServiceAll")
    Observable<BaseModel<JSONObject>> getIndexServiceAll(@Field("userId") String userId);

    @FormUrlEncoded
    @POST("jpush/syncPushRegister")
    Observable<BaseModel<String>> pushRegister(@Field("userId") String userId,@Field("deviceId") String deviceId,@Field("flag") String flag);

    @FormUrlEncoded
    @POST("hefeitong/selectByName")
    Observable<BaseModel<JSONArray>> getKeyword(@Field("type")String type, @Field("name")String name);

    @FormUrlEncoded
    @POST("userRefAccount/saveUserRefAccount ")
    Observable<BaseModel<JSONObject>> saveAccount(@Field("userId")String userId, @Field("userSocialSecurityAc")String userSocialSecurityAc,@Field("userAccumulationFoundAc")String userAccumulationFoundAc ,@Field("remarks")String remarks);

    @POST("hefeitong/getKeyWord")
    Observable<BaseModel<JSONArray>> getTipKey();

    @FormUrlEncoded
    @POST("userRefAccount/getUserRefAccount")
    Observable<BaseModel<JSONObject>> queryAccount(@Field("userId")String userId);

    @FormUrlEncoded
    @POST("hefeitong/savePreferences")
    Observable<BaseModel<String>> preSetting(@Field("userId")String userId,@Field("usePrefer")String type,@Field("preferName")String preferName);

    @FormUrlEncoded
    @POST("hefeitong/savePortrait")
    Observable<BaseModel<String>> saveIconUrl(@Field("userId")String userId,@Field("iconUrl")String iconUrl);

    @FormUrlEncoded
    @POST("hefeitong/platformAppUpdate")
    Observable<BaseModel<JSONObject>> getAppVersion(@Field("appVersion")String appVersion,@Field("appType")String appType);



}