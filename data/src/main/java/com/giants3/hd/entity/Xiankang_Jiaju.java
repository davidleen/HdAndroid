package com.giants3.hd.entity;


import java.io.Serializable;

/**
 *
 * 咸康家具部分 附加数据
 * Created by david on 2015/11/14.
 */

public class Xiankang_Jiaju implements Serializable{




    private long id;



    //灯具类相关信息
    /**
     *玻璃/镜子
     */
    public String bolijingzi;
    /**
     *玻璃/镜子规格 宽
     */
    public String bolijingziguige_kuan;



    /**
     *玻璃/镜子规格 高
     */
    public String bolijingziguige_gao;

    /**
     * 槽宽
     */
    public String caokuan;
    /**
     * 槽深
     */
    public String caoshen;
    /**
     * 木材料
     */
    public String mucailiao;
    /**
     * 木皮
     */
    public String   mupi;
    /**
     * 大理石
     */
    public String  dalishi;
    /**
     * 干度
     */
    public String gandu;

    /**
     * 抽屉尺寸
     */
    public String choutichicun;

    /**
     * 门开口尺寸
     */
    public String menkaikouchicun;


    /**
     * 储藏尺寸
     */
    public String chucangchicun;

    /**
     * 备注
     */
    private String memo;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBolijingzi() {
        return bolijingzi;
    }

    public void setBolijingzi(String bolijingzi) {
        this.bolijingzi = bolijingzi;
    }

    public String getBolijingziguige_kuan() {
        return bolijingziguige_kuan;
    }

    public void setBolijingziguige_kuan(String bolijingziguige_kuan) {
        this.bolijingziguige_kuan = bolijingziguige_kuan;
    }

    public String getBolijingziguige_gao() {
        return bolijingziguige_gao;
    }

    public void setBolijingziguige_gao(String bolijingziguige_gao) {
        this.bolijingziguige_gao = bolijingziguige_gao;
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

    public String getMucailiao() {
        return mucailiao;
    }

    public void setMucailiao(String mucailiao) {
        this.mucailiao = mucailiao;
    }

    public String getMupi() {
        return mupi;
    }

    public void setMupi(String mupi) {
        this.mupi = mupi;
    }

    public String getDalishi() {
        return dalishi;
    }

    public void setDalishi(String dalishi) {
        this.dalishi = dalishi;
    }

    public String getGandu() {
        return gandu;
    }

    public void setGandu(String gandu) {
        this.gandu = gandu;
    }

    public String getChoutichicun() {
        return choutichicun;
    }

    public void setChoutichicun(String choutichicun) {
        this.choutichicun = choutichicun;
    }

    public String getMenkaikouchicun() {
        return menkaikouchicun;
    }

    public void setMenkaikouchicun(String menkaikouchicun) {
        this.menkaikouchicun = menkaikouchicun;
    }

    public String getChucangchicun() {
        return chucangchicun;
    }

    public void setChucangchicun(String chucangchicun) {
        this.chucangchicun = chucangchicun;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(final String memo) {
        this.memo = memo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Xiankang_Jiaju)) return false;

        Xiankang_Jiaju that = (Xiankang_Jiaju) o;

        if (id != that.id) return false;
        if (bolijingzi != null ? !bolijingzi.equals(that.bolijingzi) : that.bolijingzi != null) return false;
        if (bolijingziguige_kuan != null ? !bolijingziguige_kuan.equals(that.bolijingziguige_kuan) : that.bolijingziguige_kuan != null)
            return false;
        if (bolijingziguige_gao != null ? !bolijingziguige_gao.equals(that.bolijingziguige_gao) : that.bolijingziguige_gao != null)
            return false;
        if (caokuan != null ? !caokuan.equals(that.caokuan) : that.caokuan != null) return false;
        if (caoshen != null ? !caoshen.equals(that.caoshen) : that.caoshen != null) return false;
        if (mucailiao != null ? !mucailiao.equals(that.mucailiao) : that.mucailiao != null) return false;
        if (mupi != null ? !mupi.equals(that.mupi) : that.mupi != null) return false;
        if (dalishi != null ? !dalishi.equals(that.dalishi) : that.dalishi != null) return false;
        if (gandu != null ? !gandu.equals(that.gandu) : that.gandu != null) return false;
        if (choutichicun != null ? !choutichicun.equals(that.choutichicun) : that.choutichicun != null) return false;
        if (menkaikouchicun != null ? !menkaikouchicun.equals(that.menkaikouchicun) : that.menkaikouchicun != null)
            return false;
        if (chucangchicun != null ? !chucangchicun.equals(that.chucangchicun) : that.chucangchicun != null)
            return false;
        return !(memo != null ? !memo.equals(that.memo) : that.memo != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (bolijingzi != null ? bolijingzi.hashCode() : 0);
        result = 31 * result + (bolijingziguige_kuan != null ? bolijingziguige_kuan.hashCode() : 0);
        result = 31 * result + (bolijingziguige_gao != null ? bolijingziguige_gao.hashCode() : 0);
        result = 31 * result + (caokuan != null ? caokuan.hashCode() : 0);
        result = 31 * result + (caoshen != null ? caoshen.hashCode() : 0);
        result = 31 * result + (mucailiao != null ? mucailiao.hashCode() : 0);
        result = 31 * result + (mupi != null ? mupi.hashCode() : 0);
        result = 31 * result + (dalishi != null ? dalishi.hashCode() : 0);
        result = 31 * result + (gandu != null ? gandu.hashCode() : 0);
        result = 31 * result + (choutichicun != null ? choutichicun.hashCode() : 0);
        result = 31 * result + (menkaikouchicun != null ? menkaikouchicun.hashCode() : 0);
        result = 31 * result + (chucangchicun != null ? chucangchicun.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}
