package com.giants3.hd.entity;


import java.io.Serializable;

/**
 *
 * 咸康灯具部分 附加数据
 * Created by david on 2015/11/14.
 */

public class Xiankang_Dengju implements Serializable{



    private long id;



    //灯具类相关信息
    /**
     *
     */
    public String dengyongtu;
    /**
     *
     */
    public String dengtichangshang;
    public String dengdikuan;
    public String dengdichang;
    public String feiniuhao;
    public String feiniukuan;
    public String feiniuchang;
    public String feiniugao;
    public String washu;
    public String dengpaoleixing;
    public String dengtouleixing ;
    public String dengtoukaiguangleixing;
    public String caizhi;
    public String dengzhaochangshang;
    public String hafujia;
    public String hafujiayanse;
    public String dengzhaobuliao;
    public String dengzhaochicun;
    public String dengzhaobaozhuang;

    /**
     *  8 degree tile testing
     */
    public String _8dtt;

    /**
     * K/D(Y/N)
     */
    public String kdyn;


    /**
     *
     */
    public String ULApproval;
    private String memo;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDengyongtu() {
        return dengyongtu;
    }

    public void setDengyongtu(String dengyongtu) {
        this.dengyongtu = dengyongtu;
    }

    public String getDengtichangshang() {
        return dengtichangshang;
    }

    public void setDengtichangshang(String dengtichangshang) {
        this.dengtichangshang = dengtichangshang;
    }

    public String getDengdikuan() {
        return dengdikuan;
    }

    public void setDengdikuan(String dengdikuan) {
        this.dengdikuan = dengdikuan;
    }

    public String getDengdichang() {
        return dengdichang;
    }

    public void setDengdichang(String dengdichang) {
        this.dengdichang = dengdichang;
    }

    public String getFeiniuhao() {
        return feiniuhao;
    }

    public void setFeiniuhao(String feiniuhao) {
        this.feiniuhao = feiniuhao;
    }

    public String getFeiniukuan() {
        return feiniukuan;
    }

    public void setFeiniukuan(String feiniukuan) {
        this.feiniukuan = feiniukuan;
    }

    public String getFeiniuchang() {
        return feiniuchang;
    }

    public void setFeiniuchang(String feiniuchang) {
        this.feiniuchang = feiniuchang;
    }

    public String getFeiniugao() {
        return feiniugao;
    }

    public void setFeiniugao(String feiniugao) {
        this.feiniugao = feiniugao;
    }

    public String getWashu() {
        return washu;
    }

    public void setWashu(String washu) {
        this.washu = washu;
    }

    public String getDengpaoleixing() {
        return dengpaoleixing;
    }

    public void setDengpaoleixing(String dengpaoleixing) {
        this.dengpaoleixing = dengpaoleixing;
    }

    public String getDengtouleixing() {
        return dengtouleixing;
    }

    public void setDengtouleixing(String dengtouleixing) {
        this.dengtouleixing = dengtouleixing;
    }

    public String getDengtoukaiguangleixing() {
        return dengtoukaiguangleixing;
    }

    public void setDengtoukaiguangleixing(String dengtoukaiguangleixing) {
        this.dengtoukaiguangleixing = dengtoukaiguangleixing;
    }

    public String getCaizhi() {
        return caizhi;
    }

    public void setCaizhi(String caizhi) {
        this.caizhi = caizhi;
    }

    public String getDengzhaochangshang() {
        return dengzhaochangshang;
    }

    public void setDengzhaochangshang(String dengzhaochangshang) {
        this.dengzhaochangshang = dengzhaochangshang;
    }

    public String getHafujia() {
        return hafujia;
    }

    public void setHafujia(String hafujia) {
        this.hafujia = hafujia;
    }

    public String getHafujiayanse() {
        return hafujiayanse;
    }

    public void setHafujiayanse(String hafujiayanse) {
        this.hafujiayanse = hafujiayanse;
    }

    public String getDengzhaobuliao() {
        return dengzhaobuliao;
    }

    public void setDengzhaobuliao(String dengzhaobuliao) {
        this.dengzhaobuliao = dengzhaobuliao;
    }

    public String getDengzhaochicun() {
        return dengzhaochicun;
    }

    public void setDengzhaochicun(String dengzhaochicun) {
        this.dengzhaochicun = dengzhaochicun;
    }

    public String getDengzhaobaozhuang() {
        return dengzhaobaozhuang;
    }

    public void setDengzhaobaozhuang(String dengzhaobaozhuang) {
        this.dengzhaobaozhuang = dengzhaobaozhuang;
    }

    public String get_8dtt() {
        return _8dtt;
    }

    public void set_8dtt(String _8dtt) {
        this._8dtt = _8dtt;
    }

    public String getKdyn() {
        return kdyn;
    }

    public void setKdyn(String kdyn) {
        this.kdyn = kdyn;
    }

    public String getULApproval() {
        return ULApproval;
    }

    public void setULApproval(String ULApproval) {
        this.ULApproval = ULApproval;
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
        if (!(o instanceof Xiankang_Dengju)) return false;

        Xiankang_Dengju that = (Xiankang_Dengju) o;

        if (id != that.id) return false;
        if (dengyongtu != null ? !dengyongtu.equals(that.dengyongtu) : that.dengyongtu != null) return false;
        if (dengtichangshang != null ? !dengtichangshang.equals(that.dengtichangshang) : that.dengtichangshang != null)
            return false;
        if (dengdikuan != null ? !dengdikuan.equals(that.dengdikuan) : that.dengdikuan != null) return false;
        if (dengdichang != null ? !dengdichang.equals(that.dengdichang) : that.dengdichang != null) return false;
        if (feiniuhao != null ? !feiniuhao.equals(that.feiniuhao) : that.feiniuhao != null) return false;
        if (feiniukuan != null ? !feiniukuan.equals(that.feiniukuan) : that.feiniukuan != null) return false;
        if (feiniuchang != null ? !feiniuchang.equals(that.feiniuchang) : that.feiniuchang != null) return false;
        if (feiniugao != null ? !feiniugao.equals(that.feiniugao) : that.feiniugao != null) return false;
        if (washu != null ? !washu.equals(that.washu) : that.washu != null) return false;
        if (dengpaoleixing != null ? !dengpaoleixing.equals(that.dengpaoleixing) : that.dengpaoleixing != null)
            return false;
        if (dengtouleixing != null ? !dengtouleixing.equals(that.dengtouleixing) : that.dengtouleixing != null)
            return false;
        if (dengtoukaiguangleixing != null ? !dengtoukaiguangleixing.equals(that.dengtoukaiguangleixing) : that.dengtoukaiguangleixing != null)
            return false;
        if (caizhi != null ? !caizhi.equals(that.caizhi) : that.caizhi != null) return false;
        if (dengzhaochangshang != null ? !dengzhaochangshang.equals(that.dengzhaochangshang) : that.dengzhaochangshang != null)
            return false;
        if (hafujia != null ? !hafujia.equals(that.hafujia) : that.hafujia != null) return false;
        if (hafujiayanse != null ? !hafujiayanse.equals(that.hafujiayanse) : that.hafujiayanse != null) return false;
        if (dengzhaobuliao != null ? !dengzhaobuliao.equals(that.dengzhaobuliao) : that.dengzhaobuliao != null)
            return false;
        if (dengzhaochicun != null ? !dengzhaochicun.equals(that.dengzhaochicun) : that.dengzhaochicun != null)
            return false;
        if (dengzhaobaozhuang != null ? !dengzhaobaozhuang.equals(that.dengzhaobaozhuang) : that.dengzhaobaozhuang != null)
            return false;
        if (_8dtt != null ? !_8dtt.equals(that._8dtt) : that._8dtt != null) return false;
        if (kdyn != null ? !kdyn.equals(that.kdyn) : that.kdyn != null) return false;
        if (ULApproval != null ? !ULApproval.equals(that.ULApproval) : that.ULApproval != null) return false;
        return !(memo != null ? !memo.equals(that.memo) : that.memo != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (dengyongtu != null ? dengyongtu.hashCode() : 0);
        result = 31 * result + (dengtichangshang != null ? dengtichangshang.hashCode() : 0);
        result = 31 * result + (dengdikuan != null ? dengdikuan.hashCode() : 0);
        result = 31 * result + (dengdichang != null ? dengdichang.hashCode() : 0);
        result = 31 * result + (feiniuhao != null ? feiniuhao.hashCode() : 0);
        result = 31 * result + (feiniukuan != null ? feiniukuan.hashCode() : 0);
        result = 31 * result + (feiniuchang != null ? feiniuchang.hashCode() : 0);
        result = 31 * result + (feiniugao != null ? feiniugao.hashCode() : 0);
        result = 31 * result + (washu != null ? washu.hashCode() : 0);
        result = 31 * result + (dengpaoleixing != null ? dengpaoleixing.hashCode() : 0);
        result = 31 * result + (dengtouleixing != null ? dengtouleixing.hashCode() : 0);
        result = 31 * result + (dengtoukaiguangleixing != null ? dengtoukaiguangleixing.hashCode() : 0);
        result = 31 * result + (caizhi != null ? caizhi.hashCode() : 0);
        result = 31 * result + (dengzhaochangshang != null ? dengzhaochangshang.hashCode() : 0);
        result = 31 * result + (hafujia != null ? hafujia.hashCode() : 0);
        result = 31 * result + (hafujiayanse != null ? hafujiayanse.hashCode() : 0);
        result = 31 * result + (dengzhaobuliao != null ? dengzhaobuliao.hashCode() : 0);
        result = 31 * result + (dengzhaochicun != null ? dengzhaochicun.hashCode() : 0);
        result = 31 * result + (dengzhaobaozhuang != null ? dengzhaobaozhuang.hashCode() : 0);
        result = 31 * result + (_8dtt != null ? _8dtt.hashCode() : 0);
        result = 31 * result + (kdyn != null ? kdyn.hashCode() : 0);
        result = 31 * result + (ULApproval != null ? ULApproval.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}
