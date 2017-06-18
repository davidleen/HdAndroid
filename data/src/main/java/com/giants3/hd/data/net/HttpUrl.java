package com.giants3.hd.data.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.StringUtils;

import java.net.URLEncoder;

/**
 * 网络常量
 */
public class HttpUrl {

    public static final String SHARE_FILE = "url_file";

    public static final String CLIENT_TYPE = "ANDROID";

    public static final String DEFAULT_IPAddress = "192.168.2.108";
    public static final String DEFAULT_IPPort = "8080";
    public static final String DEFAULT_ServiceName = "Service";
    public static final String API_BASE_URL = "";
    public static final String API_URL_GET_USER_LIST = API_BASE_URL + "users.json";
    /**
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     */
    public static final String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";
    public static final String API_URL_GET_PRODUCT_LIST = "/api/product/appSearch?name=%s&pageIndex=%d&pageSize=%d";
    public static final String API_URL_GET_QUOTATION_LIST = "/api/quotation/search?searchValue=%s&pageIndex=%d&pageSize=%d";
    public static final String API_URL_GET_MATERIAL_LIST = "/api/material/search?codeOrName=%s&pageIndex=%d&pageSize=%d";
    public static final String API_URL_GET_MATERIAL_LIST_IN_SERVICE = "/api/material/searchInService?codeOrName=%s&pageIndex=%d&pageSize=%d";
    public static final String API_URL_UPLOAD_MATERIAL_PICTURE = "/api/file/uploadMaterialPicture?materialId=%d";
    public static final String API_URL_GET_INITDATA = "/api/user/getInitData?userId=%d";
    public static final String API_URL_SAVE_PRODUCTDETAIL = "/api/product/save";
    public static final String API_URL_GET_QUOTATION_DETAIL = "/api/quotation/detail?id=%d";
    public static final String API_URL_GET_ORDER_LIST = "/api/order/list?key=%s&pageIndex=%d&pageSize=%d";
    public static final String API_URL_GET_ORDER_DETAIL = "/api/order/detail?os_no=%s";
    public static final String API_URL_GET_PRODUCT_DETAIL = "/api/product/detail?id=%d";
    public static final String API_URL_GET_PRODUCT_PROCESS_LIST = "api/process/search?name=%s&pageIndex=%d&pageSize=%d";
    public static final String API_URL_GET_UN_HANDLE_WORK_FLOW_LIST = "api/order/unHandleWorkFlowMessage";

    public static final String API_URL_CHECK_WORK_FLOW_MESSAGE = "api/order/checkWorkFlowMessage?workFlowMsgId=%d";

    public static final String API_URL_RECEIVE_WORK_FLOW_MESSAGE = "api/erpWork/receiveWorkFlowMessage?workFlowMsgId=%d";
    public static final String API_URL_GET_ORDER_ITEM_FOR_TRANSFORM = "api/erpWork/getOrderItemForTransform";

    public static final String API_URL_SEND_WORK_FLOW_MESSAGE = "api/erpWork/sendWorkFlowMessage?&tranQty=%d&areaId=%d&memo=%s";
    public static final String API_URL_REJECT_WORK_FLOW_MESSAGE = "api/erpWork/rejectWorkFlowMessage?workFlowMsgId=%d&&memo=%s";
    public static final String API_URL_MY_SEND_WORK_FLOW_MESSAGE = "api/order/getSendWorkFlowMessageList";


    public static String IPAddress = DEFAULT_IPAddress;
    public static String IPPort = DEFAULT_IPPort;
    public static String ServiceName = DEFAULT_ServiceName;


    public static String KEY_IPAddress = "_IPAddress";
    public static String KEY_IPPort = "_IPPort";
    public static String KEY_ServiceName = "_ServiceName";


    public static final String BASE_URL_FORMAT = "http://%s:%s/%s/";
    public static String BASE_URL = "";
    static final String API_LOGIN = "/api/authority/aLogin2";
    public static String token = "";

    private static Context mContext;
    private static String versionCode = "111";

    public static void init(Context context) {
        mContext = context;
        SharedPreferences sf = context.getSharedPreferences(SHARE_FILE, Context.MODE_PRIVATE);
        String ip = sf.getString(KEY_IPAddress, "");
        if (ip == "") {
            SharedPreferences.Editor text = sf.edit();
            text.putString(KEY_IPAddress, DEFAULT_IPAddress);
            text.putString(KEY_IPPort, DEFAULT_IPPort);
            text.putString(KEY_ServiceName, DEFAULT_ServiceName);
            text.commit();

        }


        IPAddress = sf.getString(KEY_IPAddress, DEFAULT_IPAddress);
        IPPort = sf.getString(KEY_IPPort, DEFAULT_IPPort);
        ServiceName = sf.getString(KEY_ServiceName, DEFAULT_ServiceName);
        generateBaseUrl();
        PackageManager pm = context.getPackageManager();//context为当前Activity上下文
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = String.valueOf(pi.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }


    public static void reset(String ip, String port, String service) {


        IPAddress = ip;
        IPPort = port;
        ServiceName = service;
        SharedPreferences sf = mContext.getSharedPreferences(SHARE_FILE, Context.MODE_PRIVATE);


        SharedPreferences.Editor text = sf.edit();
        text.putString(KEY_IPAddress, IPAddress);
        text.putString(KEY_IPPort, IPPort);
        text.putString(KEY_ServiceName, ServiceName);
        text.commit();

        generateBaseUrl();


    }

    private static final void generateBaseUrl() {
        BASE_URL = String.format(BASE_URL_FORMAT, IPAddress, IPPort, ServiceName);
    }


    public static final String getBaseUrl() {

        return BASE_URL;
    }

    public static void setToken(String mToken) {

        token = mToken;

    }


    public static String completeUrl(String url) {
        if (StringUtils.isEmpty(url)) return "";
        return additionInfo(BASE_URL + url);
    }


    public static String additionInfo(String url) {


        if (StringUtils.isEmpty(token)) {
            return url;
        }

        if (url.contains("?")) {
            url += "&token=" + token;
        } else {
            url += "?token=" + token;
        }
        if (url.contains("?")) {
            url += "&appVersion=" + versionCode;
        } else {
            url += "?appVersion=" + versionCode;
        }

        if (url.contains("?")) {
            url += "&client=" + CLIENT_TYPE;
        } else {
            url += "?client=" + CLIENT_TYPE;
        }

        return url;
    }

    public static String login() {
        return completeUrl(API_LOGIN);
    }

    public static String getProductList(String name, int pageIndex, int pageSize) {
        return completeUrl(String.format(API_URL_GET_PRODUCT_LIST, name, pageIndex, pageSize));
    }

    public static String getOrderList(String name, int pageIndex, int pageSize) {
        return completeUrl(String.format(API_URL_GET_ORDER_LIST, name, pageIndex, pageSize));
    }

    public static String getOrderDetail(String orderNo) {
        return completeUrl(String.format(API_URL_GET_ORDER_DETAIL, orderNo));
    }

    public static String getProductDetail(long productId) {
        return completeUrl(String.format(API_URL_GET_PRODUCT_DETAIL, productId));
    }

    public static String getQuotationList(String name, int pageIndex, int pageSize) {
        return completeUrl(String.format(API_URL_GET_QUOTATION_LIST, URLEncoder.encode(name), pageIndex, pageSize));
    }

    public static String getQuotationDetail(long quotationId) {
        return completeUrl(String.format(API_URL_GET_QUOTATION_DETAIL, quotationId));
    }

    public static String getMaterialList(String name, int pageIndex, int pageSize) {
        return completeUrl(String.format(API_URL_GET_MATERIAL_LIST, URLEncoder.encode(name), pageIndex, pageSize));
    }

    public static String getMaterialListInService(String name, int pageIndex, int pageSize) {
        return completeUrl(String.format(API_URL_GET_MATERIAL_LIST_IN_SERVICE, URLEncoder.encode(name), pageIndex, pageSize));
    }

    /**
     * 上传材料图片
     *
     * @param materialId
     * @return
     */
    public static String uploadMaterialPicture(long materialId) {


        return completeUrl(String.format(API_URL_UPLOAD_MATERIAL_PICTURE, materialId));
    }

    public static String loadInitData(long userId) {
        return completeUrl(String.format(API_URL_GET_INITDATA, userId));
    }

    public static String saveProductDetail() {

        return completeUrl(API_URL_SAVE_PRODUCTDETAIL);
    }

    public static String getProductProcessList(String name, int pageIndex, int pageSize) {
        return completeUrl(String.format(API_URL_GET_PRODUCT_PROCESS_LIST, URLEncoder.encode(name), pageIndex, pageSize));

    }

    /**
     * 获取未处理的流程信息
     *
     * @return
     * @throws HdException
     */
    public static String getUnHandleWorkFlowList() {
        return completeUrl(API_URL_GET_UN_HANDLE_WORK_FLOW_LIST);

    }

    /**
     * 审核流程传递
     */
    public static String checkWorkFlowMessage(long workFlowMessageId) {

        return completeUrl(String.format(API_URL_CHECK_WORK_FLOW_MESSAGE, workFlowMessageId));
    }

    /**
     * 接受流程传递
     */
    public static String receiveWorkFlowMessage(long workFlowMessageId) {

        return completeUrl(String.format(API_URL_RECEIVE_WORK_FLOW_MESSAGE, workFlowMessageId));
    }

    public static String getAvailableOrderItemForTransform() {

        return completeUrl(API_URL_GET_ORDER_ITEM_FOR_TRANSFORM);
    }

    public static String sendWorkFlowMessage(int tranQty, long area, String memo) {
        return completeUrl(String.format(API_URL_SEND_WORK_FLOW_MESSAGE, tranQty, area, memo == null ? "" : memo));
    }

    public static String mySendWorkFlowMessage() {

        return completeUrl(API_URL_MY_SEND_WORK_FLOW_MESSAGE);
    }

    public static String rejectWorkFlowMessage(long workFlowMessageId, String reason) {
        return completeUrl(String.format(API_URL_REJECT_WORK_FLOW_MESSAGE, workFlowMessageId, reason));
    }

    /**
     * 读取未出库订单货款列表
     *
     * @return
     */
    public static String loadUnCompleteOrderItemWorkFlowReport() {
        return completeUrl("api/order/unCompleteOrderItem");

    }

    /**
     * 根据关键字查询生产进度报表
     *
     * @param key
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static String loadOrderWorkFlowReport(String key, int pageIndex, int pageSize) {

        return completeUrl(String.format("api/order/getWorkFlowOrderItem?key=%s&pageIndex=%d&pageSize=%d", key, pageIndex, pageSize));
    }

    public static String loadAppUpgradeInfo() {

        return completeUrl(String.format("api/update/getNewAndroidApk"));
    }


    /**
     * 查询订单的报表
     *
     * @param os_no
     * @param itm
     * @return
     */
    public static String getOrderItemWorkFlowReport(final String os_no, int itm) {
        return completeUrl("api/erpWork/findOrderItemReport?os_no=" + os_no + "&itm=" + itm);
    }

    /**
     * 查询订单款项项目
     *
     * @param key
     * @return
     */
    public static String searchErpOrderItem(String key,final int pageIndex, final int pageSize) {
        return completeUrl(String.format("api/order/searchOrderItems?key=%s&pageIndex=%d&pageSize=%d", key ,pageIndex,pageSize));
    }

    public static String getAvailableOrderItemProcess(final String osNo, final int itm, int workFlowStep) {
        return completeUrl("api/erpWork/getAvailableOrderItemProcess?os_no=" + osNo + "&itm=" + itm + "&flowStep=" + workFlowStep);
    }

    public static String getOrderItemWorkFlowMessage(String os_no, int itm, int workFlowStep) {
        return completeUrl(String.format("api/workFlow/workFlowMessage?os_no=" + os_no + "&itm=" + itm + "&workFlowStep=" + workFlowStep));
    }


    public static String loadUsers() {
        return completeUrl("api/user/list");


    }

    public static String getUnCompleteWorkFlowOrderItems(String key) {
        return completeUrl( "api/erpWork/searchUnCompleteOrderItems?key=" + key  );

    }

    public static String getOrderItemWorkMemoList(String os_no, int itm) {
        return completeUrl( "api/erpWork/getOrderItemWorkMemos?os_no=" + os_no+ "&itm=" + itm   );
    }

    public static String getProductWorkMemoList(String productName, String pversion) {

        return completeUrl( "api/erpWork/getProductWorkMemos?productName=" + productName+ "&pVersion=" + pversion  );
    }

    public static String saveWorkMemo( ){
        return completeUrl( "api/erpWork/saveWorkMemo"

        );
    }


    public static String getWorkFlowAreaList() {


        return completeUrl( "api/workFlow/area");

    }
}
