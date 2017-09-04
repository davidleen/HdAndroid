package com.giants3.hd.entity;

import java.io.Serializable;


public class Xiankang implements Serializable {



    private long id;



    public  long productId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQitahuohao() {
        return qitahuohao;
    }

    public void setQitahuohao(String qitahuohao) {
        this.qitahuohao = qitahuohao;
    }

    public String getCaizhibaifenbi() {
        return caizhibaifenbi;
    }

    public void setCaizhibaifenbi(String caizhibaifenbi) {
        this.caizhibaifenbi = caizhibaifenbi;
    }

    public String getJiaquan() {
        return jiaquan;
    }

    public void setJiaquan(String jiaquan) {
        this.jiaquan = jiaquan;
    }

    public float getPack_front() {
        return pack_front;
    }

    public void setPack_front(float pack_front) {
        this.pack_front = pack_front;
    }

    public float getPack_front_back() {
        return pack_front_back;
    }

    public void setPack_front_back(float pack_front_back) {
        this.pack_front_back = pack_front_back;
    }

    public float getPack_middle() {
        return pack_middle;
    }

    public void setPack_middle(float pack_middle) {
        this.pack_middle = pack_middle;
    }

    public float getPack_cube() {
        return pack_cube;
    }

    public void setPack_cube(float pack_cube) {
        this.pack_cube = pack_cube;
    }

    public float getPack_perimeter() {
        return pack_perimeter;
    }

    public void setPack_perimeter(float pack_perimeter) {
        this.pack_perimeter = pack_perimeter;
    }

    public String getPack_memo() {
        return pack_memo;
    }

    public void setPack_memo(String pack_memo) {
        this.pack_memo = pack_memo;
    }

    public Xiankang_Dengju getXiankang_dengju() {
        return xiankang_dengju;
    }

    public void setXiankang_dengju(Xiankang_Dengju xiankang_dengju) {
        this.xiankang_dengju = xiankang_dengju;
    }

    public Xiankang_Jiaju getXiankang_jiaju() {
        return xiankang_jiaju;
    }

    public void setXiankang_jiaju(Xiankang_Jiaju xiankang_jiaju) {
        this.xiankang_jiaju = xiankang_jiaju;
    }

    public Xiankang_Jingza getXiankang_jingza() {
        return xiankang_jingza;
    }

    public void setXiankang_jingza(Xiankang_Jingza xiankang_jingza) {
        this.xiankang_jingza = xiankang_jingza;
    }

    /**
     * 其他同产品货号
     */
    private String qitahuohao;
    /**
     * 材质百分比
     */
    private String caizhibaifenbi;
    /**
     * 甲醛标记
     */
    private String jiaquan="NO";



    /**
     * 包装描述额外信息 会参与计算
     *
     * 前
     *
     */
    public float pack_front;

    /**
     * 包装描述额外信息 会参与计算
     *
     * 前后
     *
     */
    public float pack_front_back;

    /**
     * 包装描述额外信息 会参与计算
     *
     * 中间
     *
     */
    public float pack_middle;

    /**
     * 包装描述额外信息 会参与计算
     *
     * 六面
     *
     */
    public float pack_cube;

    /**
     * 包装描述额外信息 会参与计算
     *
     * 四周
     *
     */
    public float pack_perimeter;
    /**
     * 包装描述额 说明文字
     *
     * 描述
     *
     */
    public String pack_memo;








    public Xiankang_Dengju xiankang_dengju;



    public Xiankang_Jiaju xiankang_jiaju;




     public Xiankang_Jingza xiankang_jingza;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Xiankang)) return false;

        Xiankang xiankang = (Xiankang) o;

        if (id != xiankang.id) return false;
        if (Float.compare(xiankang.pack_front, pack_front) != 0) return false;
        if (Float.compare(xiankang.pack_front_back, pack_front_back) != 0) return false;
        if (Float.compare(xiankang.pack_middle, pack_middle) != 0) return false;
        if (Float.compare(xiankang.pack_cube, pack_cube) != 0) return false;
        if (Float.compare(xiankang.pack_perimeter, pack_perimeter) != 0) return false;
        if (qitahuohao != null ? !qitahuohao.equals(xiankang.qitahuohao) : xiankang.qitahuohao != null) return false;
        if (caizhibaifenbi != null ? !caizhibaifenbi.equals(xiankang.caizhibaifenbi) : xiankang.caizhibaifenbi != null)
            return false;
        if (jiaquan != null ? !jiaquan.equals(xiankang.jiaquan) : xiankang.jiaquan != null) return false;
        if (pack_memo != null ? !pack_memo.equals(xiankang.pack_memo) : xiankang.pack_memo != null) return false;
        if (xiankang_dengju != null ? !xiankang_dengju.equals(xiankang.xiankang_dengju) : xiankang.xiankang_dengju != null)
            return false;
        if (xiankang_jiaju != null ? !xiankang_jiaju.equals(xiankang.xiankang_jiaju) : xiankang.xiankang_jiaju != null)
            return false;
        return !(xiankang_jingza != null ? !xiankang_jingza.equals(xiankang.xiankang_jingza) : xiankang.xiankang_jingza != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (qitahuohao != null ? qitahuohao.hashCode() : 0);
        result = 31 * result + (caizhibaifenbi != null ? caizhibaifenbi.hashCode() : 0);
        result = 31 * result + (jiaquan != null ? jiaquan.hashCode() : 0);
        result = 31 * result + (pack_front != +0.0f ? Float.floatToIntBits(pack_front) : 0);
        result = 31 * result + (pack_front_back != +0.0f ? Float.floatToIntBits(pack_front_back) : 0);
        result = 31 * result + (pack_middle != +0.0f ? Float.floatToIntBits(pack_middle) : 0);
        result = 31 * result + (pack_cube != +0.0f ? Float.floatToIntBits(pack_cube) : 0);
        result = 31 * result + (pack_perimeter != +0.0f ? Float.floatToIntBits(pack_perimeter) : 0);
        result = 31 * result + (pack_memo != null ? pack_memo.hashCode() : 0);
        result = 31 * result + (xiankang_dengju != null ? xiankang_dengju.hashCode() : 0);
        result = 31 * result + (xiankang_jiaju != null ? xiankang_jiaju.hashCode() : 0);
        result = 31 * result + (xiankang_jingza != null ? xiankang_jingza.hashCode() : 0);
        return result;
    }








    /**
     * 镜子宽
     */
    private String jingzi_kuan;
    /**
     * 備註
     */
    private String beizhu;
    /**
     * 槽宽
     */
    private String caokuan;
    /**
     * 槽深
     */
    private String caoshen;
    /**
     * 挂距
     */
    private String guaju;
    /**
     * 画规  高
     */
    private String huangui_gao;
    /**
     * 画规  宽
     */
    private String huangui_kuan;

    /**
     * 玻璃规格  高
     */
    private String boliguige_gao;
    /**
     * 玻璃规格 宽
     */
    private String boliguige_kuan;

    /**
     * 材质
     */
    private String caizhi;
    /**
     * 边框
     */
    private String biankuang;
    /**
     * 磨边
     */
    private String mobian;
    /**
     * 画芯编号
     */
    private String huaxinbianhao;
    /**
     * 画芯厂商
     */
    private String huaxinchangshang;
    /**
     * 画芯效果
     */
    private String huaxinxiaoguo;
    /**
     * 镜子规格	高
     */
    private String jingzi_gao;


    public String getJingzi_kuan() {
        return jingzi_kuan;
    }

    public void setJingzi_kuan(String jingzi_kuan) {
        this.jingzi_kuan = jingzi_kuan;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getCaokuan() {
        return caokuan;
    }

    public void setCaokuan(String caokuan) {
        this.caokuan = caokuan;
    }

    public String getCaoshen() {
        return caoshen;
    }

    public void setCaoshen(String caoshen) {
        this.caoshen = caoshen;
    }

    public String getGuaju() {
        return guaju;
    }

    public void setGuaju(String guaju) {
        this.guaju = guaju;
    }

    public String getHuangui_gao() {
        return huangui_gao;
    }

    public void setHuangui_gao(String huangui_gao) {
        this.huangui_gao = huangui_gao;
    }

    public String getHuangui_kuan() {
        return huangui_kuan;
    }

    public void setHuangui_kuan(String huangui_kuan) {
        this.huangui_kuan = huangui_kuan;
    }

    public String getBoliguige_gao() {
        return boliguige_gao;
    }

    public void setBoliguige_gao(String boliguige_gao) {
        this.boliguige_gao = boliguige_gao;
    }

    public String getBoliguige_kuan() {
        return boliguige_kuan;
    }

    public void setBoliguige_kuan(String boliguige_kuan) {
        this.boliguige_kuan = boliguige_kuan;
    }

    public String getCaizhi() {
        return caizhi;
    }

    public void setCaizhi(String caizhi) {
        this.caizhi = caizhi;
    }

    public String getBiankuang() {
        return biankuang;
    }

    public void setBiankuang(String biankuang) {
        this.biankuang = biankuang;
    }

    public String getMobian() {
        return mobian;
    }

    public void setMobian(String mobian) {
        this.mobian = mobian;
    }

    public String getHuaxinbianhao() {
        return huaxinbianhao;
    }

    public void setHuaxinbianhao(String huaxinbianhao) {
        this.huaxinbianhao = huaxinbianhao;
    }

    public String getHuaxinchangshang() {
        return huaxinchangshang;
    }

    public void setHuaxinchangshang(String huaxinchangshang) {
        this.huaxinchangshang = huaxinchangshang;
    }

    public String getHuaxinxiaoguo() {
        return huaxinxiaoguo;
    }

    public void setHuaxinxiaoguo(String huaxinxiaoguo) {
        this.huaxinxiaoguo = huaxinxiaoguo;
    }

    public String getJingzi_gao() {
        return jingzi_gao;
    }

    public void setJingzi_gao(String jingzi_gao) {
        this.jingzi_gao = jingzi_gao;
    }
}