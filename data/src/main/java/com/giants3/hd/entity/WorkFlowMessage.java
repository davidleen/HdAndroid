
package com.giants3.hd.entity;



/**
 *  流程跳转确认信
 *
 * Created by davidleen29 on 2016/9/3.
 */

public class WorkFlowMessage
{

    public long id;


    public int fromFlowStep;
    public String  fromFlowName;
    public String  fromFlowCode;


    /**
     * 接受流程id
     */
    public int toFlowStep;
    public  String  toFlowName;
    public  String  toFlowCode;







    /**
     * 订单数量
     */
    public int orderItemQty;




    public String orderName;

    /**
     * 产品在订单中序号
     */
    public int itm;
    public String productName;
    public String pVersion;
    /**
     * 排厂单号
     */
    public String mrpNo;




    /**
     * 移交数量
     */
    public int transportQty;


    /**
     * 消息内容
     */
    public String name;




    /**
     * 当前状态  0 未处理  1 已经接受  2 审核通过 3  审核拒绝  4 返工
     */
    public int  state;




    public String createTimeString;
    public long createTime;

    public   long receiveTime;
    public String receiveTimeString;


    public long checkTime;
    public String checkTimeString;
    public String  thumbnail;
    public String url;


    /**
     *发送备注
     */
    public  String sendMemo;

    public  String memo;



    /**
     * 订单项目流程状态id
     */
    public long orderItemProcessId;
    ;



    //冗余字段
    public String factoryName;
    /**
     * 流程递交的图片信息， 三张图片， 由流程接收者拍摄
     */
    public String pictures;


    /**
     * 交接区域
     */
    public String area;

    /**
     * 返工
     */
    public static final int STATE_REWORK =4;

    /**
     * 审核不通过
     */
    public static final int STATE_REJECT=3;
    /**
     * 已接收
     */
    public static final int STATE_PASS=2;

    /**
     * 已接收
     */
    public static final int STATE_RECEIVE=1;

    /**
     * 发送
     */
    public static final int STATE_SEND=0;


    public static  final  String NAME_SUBMIT="提交";
    public static  final  String NAME_REWORK="返工";


}
