package com.giants3.hd.data.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.UrlFormatter;

import java.net.URLEncoder;

/**
 * 网络常量
 */
public class HttpUrl {

    public static final String SHARE_FILE = "url_file";

    public static final String CLIENT_TYPE = "ANDROID";

    public static final String DEFAULT_IPAddress = "192.168.2.108";
    public static final String DEFAULT_IPPort = "8080";
    public static final String DEFAULT_ServiceName = "Server";
    public static final String API_BASE_URL = "";
    public static final String API_URL_GET_USER_LIST = API_BASE_URL + "users.json";
    /**
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     */
    public static final String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";

    public static final String API_URL_GET_QUOTATION_LIST = "/api/quotation/search?searchValue=%s&pageIndex=%d&pageSize=%d";
    public static final String API_URL_GET_MATERIAL_LIST = "/api/material/search?codeOrName=%s&pageIndex=%d&pageSize=%d";
    public static final String API_URL_GET_MATERIAL_LIST_IN_SERVICE = "/api/material/searchInService?codeOrName=%s&pageIndex=%d&pageSize=%d";
    public static final String API_URL_UPLOAD_MATERIAL_PICTURE = "/api/file/uploadMaterialPicture?materialId=%d";
    public static final String API_URL_GET_INITDATA = "/api/user/getAppInitData?userId=%d";
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
    private static String versionName = "";

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
            versionName = pi.versionName;
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

        UrlFormatter urlFormatter=new UrlFormatter(url).append ( "appVersion",versionCode)
                .append ( "client", CLIENT_TYPE)
                .append( "token",token)
                .append( "versionName",versionName);


        return urlFormatter.toUrl();




    }

    public static String additionInfo(UrlFormatter urlFormatter) {



        urlFormatter.append ( "appVersion",versionCode)
                .append ( "client", CLIENT_TYPE)
                .append( "token",token)
                .append( "versionName",versionName);



        return  urlFormatter.toUrl();


    }


    public static String login() {
        return completeUrl(API_LOGIN);
    }

    public static String getProductList(String name, int pageIndex, int pageSize,boolean withCopy) {


        String apiUrl=BASE_URL + "/api/product/appSearch";
        UrlFormatter formatter=new UrlFormatter(apiUrl)
                .append("name",name)
                .append("pageIndex",pageIndex)
                .append("pageSize",pageSize)
                .append("withCopy",withCopy)
                ;
        return additionInfo(formatter);



    }

    public static String getOrderList(String name, int pageIndex, int pageSize) {
        return completeUrl(String.format(API_URL_GET_ORDER_LIST, UrlFormatter.encode(name), pageIndex, pageSize));
    }

    public static String getOrderDetail(String orderNo) {
        return completeUrl(String.format(API_URL_GET_ORDER_DETAIL, UrlFormatter.encode(orderNo)));
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
    public static String getUnHandleWorkFlowList(String key) {

        String apiUrl=BASE_URL + API_URL_GET_UN_HANDLE_WORK_FLOW_LIST;
        UrlFormatter formatter=new UrlFormatter(apiUrl).append("key",key)
                 ;
        return additionInfo(formatter);


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


        String apiUrl=BASE_URL + "api/erpWork/findOrderItemReport";
        UrlFormatter formatter=new UrlFormatter(apiUrl).append("os_no",os_no).append("itm",itm);
        return additionInfo(formatter);

//        return completeUrl("api/erpWork/findOrderItemReport?os_no=" + os_no + "&itm=" + itm);
    }

    /**
     * 查询订单款项项目
     *
     * @param key
     * @return
     */
    public static String searchErpOrderItem(String key, final int pageIndex, final int pageSize) {

//        String apiUrl=BASE_URL + "api/order/searchOrderItems";
//        UrlFormatter formatter=new UrlFormatter(apiUrl).append("key",key).append("pageIndex",pageIndex).append("pageSize",pageSize);
//        return additionInfo(formatter);


        return completeUrl(String.format("api/order/searchOrderItems?key=%s&pageIndex=%d&pageSize=%d", key, pageIndex, pageSize));
    }

    public static String getAvailableOrderItemProcess(final String osNo, final int itm, int workFlowStep) {


        return completeUrl("api/erpWork/getAvailableOrderItemProcess?os_no=" + UrlFormatter.encode(osNo) + "&itm=" + itm + "&flowStep=" + workFlowStep);
    }

    public static String getOrderItemWorkFlowMessage(String os_no, int itm, int workFlowStep) {
        return completeUrl(String.format("api/workFlow/workFlowMessage?os_no=" + UrlFormatter.encode(os_no) + "&itm=" + itm + "&workFlowStep=" + workFlowStep));
    }


    public static String loadUsers() {
        return completeUrl("api/user/list");


    }

    public static String getUnCompleteWorkFlowOrderItems(String key, int workFlowStep, int pageIndex, int pageSize) {
        return completeUrl("api/erpWork/searchUnCompleteOrderItems?key=" + UrlFormatter.encode(key) + "&workFlowStep=" + workFlowStep + "&pageIndex=" + pageIndex + "&pageSize=" + pageSize);

    }


    public static String getCompleteWorkFlowOrderItems(String key, int pageIndex, int pageSize) {
        return completeUrl("api/erpWork/searchCompleteOrderItems?key=" +UrlFormatter.encode( key) + "&pageIndex=" + pageIndex + "&pageSize=" + pageSize);


    }

    public static String getOrderItemWorkMemoList(String os_no, int itm) {

        String apiUrl=BASE_URL + "api/erpWork/getOrderItemWorkMemos";
        UrlFormatter formatter=new UrlFormatter(apiUrl).append("os_no",os_no).append("itm",itm);
        return additionInfo(formatter);

    }

    public static String getProductWorkMemoList(String productName, String pversion) {

        return completeUrl("api/erpWork/getProductWorkMemos?productName=" +UrlFormatter.encode( productName) + "&pVersion=" + pversion);
    }

    public static String saveWorkMemo() {
        return completeUrl("api/erpWork/saveWorkMemo"

        );
    }


    public static String getWorkFlowAreaList() {


        return completeUrl("api/workFlow/area");

    }

    public static String getNewMessageInfo() {

        return completeUrl("/api/user/newMessage");
    }

    public static String getWorkFlowMaterials(String osNo, int itm, String workFlowCode) {

        return completeUrl("/api/erpWork/workFlowMaterials?osNo=" + UrlFormatter.encode(osNo) + "&itm=" + itm + "&workFlowCode=" + workFlowCode);
    }

    public static String getWorkFlowMessageByOrderItem(String osNo, int itm) {


        String apiUrl=BASE_URL + "api/order/workFlowMessageByOrderItem";
        UrlFormatter formatter=new UrlFormatter(apiUrl).append("osNo",osNo).append("itm",itm);
        return additionInfo(formatter);



    }

    public static String getWorkFlowMemoAuth() {
        return completeUrl("api/workFlow/memoAuth");


    }

    public static String getMyWorkFlowMessage(String key, int pageIndex, int pageSize) {
        return completeUrl("api/order/myWorkFlowMessage?key=" +UrlFormatter.encode( key) + "&pageIndex=" + pageIndex + "&pageSize=" + pageSize);


    }

    public static String checkWorkFlowMemo(long orderItemWorkMemoId, boolean check) {

        return completeUrl("api/workFlow/checkMemo?orderItemWorkMemoId=" + orderItemWorkMemoId + "&check=" + check);
    }

    public static String updatePassword(String oldPasswordMd5, String newPasswordMd5) {
        return completeUrl("api/user/updatePassword2?oldPassword=" + oldPasswordMd5 + "&newPassword=" + newPasswordMd5);
    }

    public static String loginByUserId(long userId, String passwordMd5, String deviceToken) {


        return completeUrl(String.format("/api/authority/login?userId=%d&password=%s&device_token=%s", userId, passwordMd5, deviceToken));

    }

    public static String searchSampleData(String prdNo, String pVersion) {


        return completeUrl(String.format("/api/erpWork/findSampleState?prdNo=%s&pVersion=%s", UrlFormatter.encode(prdNo), pVersion));


    }


    public static String clearWorkFlow(String os_no, int itm) {
        return completeUrl(String.format("/api/erpWork/clear?osNO=%s&itm=%d", UrlFormatter.encode(os_no), itm));
    }

    public static String getAppQotations(String key, int pageIndex, int pageSize) {
        return completeUrl(String.format("/api/app/quotation/search?searchValue=%s&pageIndex=%d&pageSize=%d", UrlFormatter.encode(key), pageIndex, pageSize));
    }

    public static String getAppQuotationDetail(long quotationId) {
        return completeUrl(String.format("/api/app/quotation/detail?id=%d", quotationId));
    }

    public static String createTempAppQuotation() {
        return completeUrl(String.format("/api/app/quotation/create" ));
    }

    public static String addProductToQuotation(long quotationId, long productId) {
        return completeUrl(String.format("/api/app/quotation/addItem?quotationId=%d&productId=%d" ,quotationId,productId));
    }

    public static String removeItemFromQuotation(long quotationId, int item) {
        return completeUrl(String.format("/api/app/quotation/removeItem?quotationId=%d&itemIndex=%d", quotationId, item));
    }

    public static String updateQuotationItemPrice(long quotationId, int itm, float price) {

        return completeUrl(String.format("/api/app/quotation/updateItemPrice?quotationId=%d&itemIndex=%d&price=%f", quotationId, itm,price));
    }

    public static String updateQuotationItemQty(long quotationId, int itm, int newQty) {
        return completeUrl(String.format("/api/app/quotation/updateItemQuantity?quotationId=%d&itemIndex=%d&quantity=%d", quotationId, itm,newQty));

    }

    public static String updateQuotationItemDiscount(long quotationId, int itm, float newDisCount) {
        return completeUrl(String.format("/api/app/quotation/updateItemDiscount?quotationId=%d&itemIndex=%d&discount=%f", quotationId, itm,newDisCount));

    }

    public static String updateQuotationDiscount(long quotationId, float newDisCount) {
        return completeUrl(String.format("/api/app/quotation/updateQuotationDiscount?quotationId=%d&discount=%f", quotationId, newDisCount));

    }

    public static String saveAppQuotation(long quotationId) {

        return completeUrl(String.format("/api/app/quotation/save?quotationId=%d", quotationId));
    }

    public static String printQuotation(long quotationId) {

        return completeUrl(String.format("/api/app/quotation/print?quotationId=%d", quotationId));
    }

    public static String getCustomerList(String key) {


        String apiUrl=BASE_URL + "/api/customer/list";
        UrlFormatter formatter=new UrlFormatter(apiUrl).append("key",key) ;
        return additionInfo(formatter);




    }

    public static String updateQuotationCustomer(long quotationId, long customerId) {
        return completeUrl(String.format("/api/app/quotation/updateCustomer?quotationId=%d&customerId=%d", quotationId,customerId));
    }

    public static String saveCustomer() {
        return completeUrl( "/api/customer/saveOne" );
    }

    public static String updateQuotationItemMemo(long quotationId, int itemIndex, String memo) {


        String apiUrl=BASE_URL + "api/app/quotation/updateItemMemo";
        UrlFormatter formatter=new UrlFormatter(apiUrl).append("quotationId",quotationId).append("itemIndex",itemIndex).append("memo",memo);
        return additionInfo(formatter);


    }

    public static String deleteQuotation(long quotationId) {
        String apiUrl=BASE_URL + "api/app/quotation/delete";
        UrlFormatter formatter=new UrlFormatter(apiUrl).append("quotationId",quotationId) ;
        return additionInfo(formatter);

    }

    public static String updateQuotationFieldValue(long quotationId, String field, String data) {
        String apiUrl=BASE_URL + "api/app/quotation/updateField";
        UrlFormatter formatter=new UrlFormatter(apiUrl).append("quotationId",quotationId).append("field",field).append("data",data) ;
        return additionInfo(formatter);

    }

    public static String getWorkFlowMessageById(long workFlowMessageId) {
        String apiUrl=BASE_URL + "api/workFlow/findMessageById";
        UrlFormatter formatter=new UrlFormatter(apiUrl).append("workFlowMessageId",workFlowMessageId)  ;
        return additionInfo(formatter);
    }
}
