package com.giants3.hd.entity;


import java.io.Serializable;

/**
 * 咸康镜子杂类部分 附加数据
 * Created by david on 2015/11/14.
 */

public class Xiankang_Jingza implements Serializable {



    private long id;


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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Xiankang_Jingza)) return false;

        Xiankang_Jingza jingza = (Xiankang_Jingza) o;

        if (id != jingza.id) return false;
        if (jingzi_kuan != null ? !jingzi_kuan.equals(jingza.jingzi_kuan) : jingza.jingzi_kuan != null) return false;
        if (beizhu != null ? !beizhu.equals(jingza.beizhu) : jingza.beizhu != null) return false;
        if (caokuan != null ? !caokuan.equals(jingza.caokuan) : jingza.caokuan != null) return false;
        if (caoshen != null ? !caoshen.equals(jingza.caoshen) : jingza.caoshen != null) return false;
        if (guaju != null ? !guaju.equals(jingza.guaju) : jingza.guaju != null) return false;
        if (huangui_gao != null ? !huangui_gao.equals(jingza.huangui_gao) : jingza.huangui_gao != null) return false;
        if (huangui_kuan != null ? !huangui_kuan.equals(jingza.huangui_kuan) : jingza.huangui_kuan != null)
            return false;
        if (boliguige_gao != null ? !boliguige_gao.equals(jingza.boliguige_gao) : jingza.boliguige_gao != null)
            return false;
        if (boliguige_kuan != null ? !boliguige_kuan.equals(jingza.boliguige_kuan) : jingza.boliguige_kuan != null)
            return false;
        if (caizhi != null ? !caizhi.equals(jingza.caizhi) : jingza.caizhi != null) return false;
        if (biankuang != null ? !biankuang.equals(jingza.biankuang) : jingza.biankuang != null) return false;
        if (mobian != null ? !mobian.equals(jingza.mobian) : jingza.mobian != null) return false;
        if (huaxinbianhao != null ? !huaxinbianhao.equals(jingza.huaxinbianhao) : jingza.huaxinbianhao != null)
            return false;
        if (huaxinchangshang != null ? !huaxinchangshang.equals(jingza.huaxinchangshang) : jingza.huaxinchangshang != null)
            return false;
        if (huaxinxiaoguo != null ? !huaxinxiaoguo.equals(jingza.huaxinxiaoguo) : jingza.huaxinxiaoguo != null)
            return false;
        return !(jingzi_gao != null ? !jingzi_gao.equals(jingza.jingzi_gao) : jingza.jingzi_gao != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (jingzi_kuan != null ? jingzi_kuan.hashCode() : 0);
        result = 31 * result + (beizhu != null ? beizhu.hashCode() : 0);
        result = 31 * result + (caokuan != null ? caokuan.hashCode() : 0);
        result = 31 * result + (caoshen != null ? caoshen.hashCode() : 0);
        result = 31 * result + (guaju != null ? guaju.hashCode() : 0);
        result = 31 * result + (huangui_gao != null ? huangui_gao.hashCode() : 0);
        result = 31 * result + (huangui_kuan != null ? huangui_kuan.hashCode() : 0);
        result = 31 * result + (boliguige_gao != null ? boliguige_gao.hashCode() : 0);
        result = 31 * result + (boliguige_kuan != null ? boliguige_kuan.hashCode() : 0);
        result = 31 * result + (caizhi != null ? caizhi.hashCode() : 0);
        result = 31 * result + (biankuang != null ? biankuang.hashCode() : 0);
        result = 31 * result + (mobian != null ? mobian.hashCode() : 0);
        result = 31 * result + (huaxinbianhao != null ? huaxinbianhao.hashCode() : 0);
        result = 31 * result + (huaxinchangshang != null ? huaxinchangshang.hashCode() : 0);
        result = 31 * result + (huaxinxiaoguo != null ? huaxinxiaoguo.hashCode() : 0);
        result = 31 * result + (jingzi_gao != null ? jingzi_gao.hashCode() : 0);
        return result;
    }
}
