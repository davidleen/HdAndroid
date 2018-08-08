package com.giants3.hd.android.helper;


import com.giants3.hd.appdata.AUser;
import com.giants3.hd.entity.Authority;
import com.giants3.hd.entity.Module;
import com.giants3.hd.entity.User;
import com.giants3.hd.noEntity.ModuleConstant;

import java.util.List;

/** 权限判断
 * Created by davidleen29 on 2015/8/8.
 */
public class AuthorityUtil {



    private static AuthorityUtil authorityUtil=new AuthorityUtil();
    public static AuthorityUtil getInstance()
    {
        return authorityUtil;
    }

    private AUser loginUser;
    public  void setLoginUser(AUser aUser)
    {
        loginUser=aUser;

    }



    /**
     * 查看模块是否有查看权限
     * @param moduleName
     * @return
     */
    public boolean isViewable(String moduleName)
    {
        if(loginUser==null) return false;

        if( loginUser.name.equals(User.ADMIN))
            return true;
        for(Authority authority:getAuthority())
        {
            if(moduleName.equals(authority.module.name)&&authority.viewable)
            {
                return true;
            }

        }


        return false;
    }



    /**
     * 查看模块是否有审核权限
     * @param moduleName
     * @return
     */
    private boolean isVerifiable(String moduleName)
    {
        if(loginUser==null) return false;
        if( loginUser.name.equals(User.ADMIN))
            return true;
        for(Authority authority:getAuthority())
        {
            if(moduleName.equals(authority.module.name)&&authority.checkable)
            {
                return true;
            }

        }


        return false;
    }



    /**
     * 判断是否可以查看价格信息
     *
     *
     * @return
     */
    public  boolean canViewPrice( String module) {
        if (isAdmin())
            return true;
        for (Authority authority : getAuthority()) {
            if (module.equals(authority.module.name) && authority.viewPrice) {
                return true;
            }
        }
        return false;
    }

    private boolean isAdmin() {
        AUser loginUser = SharedPreferencesHelper.getLoginUser();
        if(loginUser==null)return false;
        return loginUser.name.equals(User.ADMIN);
    }


    /**
     * 查看模块是否有添加的权限
     * @param moduleName
     * @return
     */
    private boolean isAddable(String moduleName)
    {
        if(loginUser==null) return false;
        if( loginUser.name.equals(User.ADMIN))
            return true;
        for(Authority authority:getAuthority())
        {
            if(moduleName.equals(authority.module.name)&&authority.addable)
            {
                return true;
            }

        }
        return false;
    }


    /**
     * 查看模块是否有查看权限
     * @param moduleName
     * @return
     */
    private boolean editable(String moduleName)
    {
        if(loginUser==null) return false;
        if( loginUser.name.equals(User.ADMIN))
            return true;
        for(Authority authority:getAuthority())
        {
            if(moduleName.equals(authority.module.name)&&authority.editable)
            {
                return true;
            }

        }


        return false;
    }


    /**
     * 查看模块是否有查看权限
     * @param moduleName
     * @return
     */
    private boolean deletable(String moduleName)
    {
        if(loginUser==null) return false;
        if( loginUser.name.equals(User.ADMIN))
            return true;
        for(Authority authority:getAuthority())
        {
            if(moduleName.equals(authority.module.name)&&authority.deletable)
            {
                return true;
            }

        }


        return false;
    }

    /**
     * 查看模块是否有查看权限
     * @param moduleName
     * @return
     */
    private boolean exportable(String moduleName)
    {
        if(loginUser==null) return false;
        if( loginUser.name.equals(User.ADMIN))
            return true;
        for(Authority authority:getAuthority())
        {
            if(moduleName.equals(authority.module.name)&&authority.exportable)
            {
                return true;
            }

        }


        return false;
    }


    public List<Authority> getAuthority()
    {
        return  loginUser.authorities;
    }

    public boolean viewProductModule()
    {


        return viewProductList()||viewMaterialList()||viewMaterialPicture()||viewProductPicture()||viewProductDelete();
    }

    public  boolean viewMaterialPicture() {

       return isViewable(ModuleConstant.NAME_MATERIAL_PICTURE);
    }

    public boolean viewProductPicture() {

        return isViewable(ModuleConstant.NAME_PRODUCT_PICTURE);
    }

    public boolean viewProductList()
    {



        return isViewable(ModuleConstant.NAME_PRODUCT);

    }


        public boolean viewMaterialList()
        {



            return isViewable(ModuleConstant.NAME_MATERIAL);
        }






    /**
     * 查看报价模块
     * @return
     */
    public boolean viewQuotationModule() {



        return viewQuotationList()||viewQuotationDeleteList();
    }


    public boolean viewQuotationList() {



        return isViewable(ModuleConstant.NAME_QUOTATION) ;
    }

    public boolean viewQuotationDeleteList() {



        return isViewable(ModuleConstant.NAME_QUOTATION_DELETE) ;
    }


    /**
     * 查看基础数据模块
     * @return
     */
    public boolean viewBaseDataModule() {
        return viewProcessList()||viewMaterialClassList()||viewCustomerList()||viewPackMaterialClassList();

    }


    /**
     * 查看工序列表
     * @return
     */
    public boolean viewProcessList()
    {

        return isViewable(ModuleConstant.NAME_PROCESS);
    }



    /**
     * 查看工序列表
     * @return
     */
    public boolean viewCustomerList()
    {


        return isViewable(ModuleConstant.NAME_CUSTOMER);

    }


    /**
     * 查看材料类型列表
     * @return
     */
    public boolean viewMaterialClassList()
    {

        return isViewable(ModuleConstant.NAME_MATERIAL_CLASS);


    }


    /**
     * 查看材料类型列表
     * @return
     */
    public boolean viewPackMaterialClassList()
    {

        return isViewable(ModuleConstant.NAME_PACK_MATERIAL_CLASS);


    }

    /**
     * 查看权限模块
     */
    public boolean viewAuthorityModule()
    {


        return viewUserList()||viewModuleList()||viewAuthorityList();

    }


    /**
     * 查看用户列表
     */

    public boolean viewUserList()
    {

      return   isViewable(ModuleConstant.NAME_USER);
    }


    /**
     * 查看模块列表
     */

    public boolean viewModuleList()
    {

        return   isViewable(ModuleConstant.NAME_MODULE);
    }

    /**
     * 查看模块列表
     */

    public boolean viewAuthorityList()
    {

        return   isViewable(ModuleConstant.NAME_AUTHORITY);
    }


    /**
     * 系统模块是否可视
     * @return
     */
    public boolean viewSystemModule() {

        return viewSyncData() ||viewSysParam();
    }

    /**
     * 同步数据 菜单
     * @return
     */
    public boolean viewSyncData()
    {

        return isViewable(ModuleConstant.NAME_SYNC_DATA);
    }


    /**
     * 图片上传菜单
     * @return
     */
    public boolean viewPictureUpload()
    {



        return isViewable(ModuleConstant.NAME_PICTURE_UPLOAD);
    }


    /**
     * 是否有添加产品的权限
     * @return
     */
    public boolean addProduct() {


        return isAddable(ModuleConstant.NAME_PRODUCT);


    }

    /**
     * 添加材料的权限
     * @return
     */
    public boolean addMaterial() {
        return isAddable(ModuleConstant.NAME_MATERIAL);

    }


    /**
     * 添加报价单的权限
     * @return
     */
    public boolean addQuotation() {


        return isAddable(ModuleConstant.NAME_QUOTATION);


    }

    public boolean editProduct() {

        return editable(ModuleConstant.NAME_PRODUCT);
    }


    public boolean deleteProduct() {

        return deletable(ModuleConstant.NAME_PRODUCT);
    }

    public boolean exportProduct() {
        return exportable(ModuleConstant.NAME_PRODUCT);
    }

    public boolean editQuotation() {

        return editable(ModuleConstant.NAME_QUOTATION);
    }

    public boolean exportQuotation() {

        return exportable(ModuleConstant.NAME_QUOTATION);
    }

    public boolean deleteQuotation() {

        return deletable(ModuleConstant.NAME_QUOTATION);
    }

    public boolean editMaterial() {

        return editable(ModuleConstant.NAME_MATERIAL);
    }

    public boolean deleteMaterial() {

        return deletable(ModuleConstant.NAME_MATERIAL);
    }

    public boolean viewProductDelete() {

        return isViewable(ModuleConstant.NAME_PRODUCT_DELETE);
    }


    /**
     * 查看设置系统参数
     * @return
     */
    public boolean viewSysParam() {

        return isViewable(ModuleConstant.NAME_SYS_PARAM);

    }

    public boolean verifyQuotation() {
        return isVerifiable(ModuleConstant.NAME_QUOTATION);

    }

    public boolean viewProductReport() {

        return isViewable(ModuleConstant.NAME_PRODUCT_REPORT);
    }

    public boolean viewTaskManage() {
        return isViewable(ModuleConstant.NAME_TASK_MANAGE);
    }

    public boolean viewOrderMenu() {
        return isViewable(ModuleConstant.NAME_ORDER);
    }
}
