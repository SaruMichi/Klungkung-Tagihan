package com.dimata.dtaxintegration.entity.biadmin;

import java.sql.*;
import java.util.*;
import java.util.Date;
import com.dimata.qdep.db.DBException;
import com.dimata.qdep.db.DBHandler;
import com.dimata.qdep.db.DBResultSet;
import com.dimata.qdep.db.I_DBInterface;
import com.dimata.qdep.db.I_DBType;
import com.dimata.qdep.entity.*;

/**
 * update opie-eyek 20130903
 * @author dimata005
 */
public class PstAppUser extends DBHandler implements I_DBInterface, I_DBType, I_Persintent  {

    //public static final String TBL_APP_USER = "APP_USER";
    public static final String TBL_APP_USER = "bi_app_user";
    public static final int FLD_USER_ID			= 0;
    public static final int FLD_LOGIN_ID		= 1;
    public static final int FLD_PASSWORD		= 2;
    public static final int FLD_FULL_NAME		= 3;
    public static final int FLD_EMAIL			= 4;
    public static final int FLD_DESCRIPTION		= 5;
    public static final int FLD_REG_DATE		= 6;
    public static final int FLD_UPDATE_DATE		= 7;
    public static final int FLD_USER_STATUS		= 8;
    public static final int FLD_LAST_LOGIN_DATE         = 9;
    public static final int FLD_LAST_LOGIN_IP		= 10;
    public static final int FLD_EMPLOYEE_ID		= 11;
    public static final int FLD_USER_GROUP_NEW          = 12;
    public static final int FLD_START_TIME = 13;
    public static final int FLD_END_TIME = 14;
    public static  final String[] fieldNames =
    {
        "USER_ID", "LOGIN_ID", "PASSWORD", "FULL_NAME", "EMAIL", "DESCRIPTION"
        ,"REG_DATE", "UPDATE_DATE", "USER_STATUS", "LAST_LOGIN_DATE", "LAST_LOGIN_IP"
        ,"EMPLOYEE_ID","USER_GROUP", "START_TIME", "END_TIME" // END TIME , TIME IN USE ONLY
    } ;

    public static int[] fieldTypes =
    {
        TYPE_PK + TYPE_LONG + TYPE_ID, TYPE_STRING,   TYPE_STRING,  TYPE_STRING,  TYPE_STRING,  TYPE_STRING,
        TYPE_DATE, TYPE_DATE, TYPE_INT, TYPE_DATE, TYPE_STRING, TYPE_LONG, TYPE_INT,TYPE_DATE, TYPE_DATE
    };

    /** Creates new PstAppUser */
    public PstAppUser() {
    }

    public PstAppUser(int i) throws DBException {
        super(new PstAppUser());
    }


    public PstAppUser(String sOid) throws DBException {
        super(new PstAppUser(0));
        if(!locate(sOid))
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        else
            return;
    }


    public PstAppUser(long lOid) throws DBException {
        super(new PstAppUser(0));
        String sOid = "0";
        try {
            sOid = String.valueOf(lOid);
        }catch(Exception e) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }

        if(!locate(sOid))
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        else
            return;

    }

    public int getFieldSize() {
        return fieldNames.length;
    }

    public String getTableName() {
        return TBL_APP_USER;
    }

    public String getPersistentName() {
        return new PstAppUser().getClass().getName();
    }


    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }


    public long delete(Entity ent) {
        return delete((AppUser) ent);
    }

    public long insert(Entity ent){
        try{
            return PstAppUser.insert((AppUser) ent);
        } catch (Exception e){
            System.out.println(" EXC " + e);
            return 0;
        }
    }


    public long update(Entity ent) {
        return update((AppUser) ent);
    }

    public long fetch(Entity ent) {
        AppUser entObj = PstAppUser.fetch(ent.getOID());
        ent = (Entity)entObj;
        return ent.getOID();
    }


    public static AppUser fetch(long oid) {
        AppUser entObj = new AppUser();
        try {
            PstAppUser pstObj = new PstAppUser(oid);
            entObj.setOID(oid);
            entObj.setLoginId(pstObj.getString(FLD_LOGIN_ID));
            entObj.setPassword(pstObj.getString(FLD_PASSWORD));
            entObj.setFullName(pstObj.getString(FLD_FULL_NAME));
            entObj.setEmail(pstObj.getString(FLD_EMAIL));
            entObj.setDescription(pstObj.getString(FLD_DESCRIPTION));
            entObj.setRegDate(pstObj.getDate(FLD_REG_DATE));
            entObj.setUpdateDate(pstObj.getDate(FLD_UPDATE_DATE));
            entObj.setUserStatus(pstObj.getInt(FLD_USER_STATUS));
            entObj.setLastLoginDate(pstObj.getDate(FLD_LAST_LOGIN_DATE));
            entObj.setLastLoginIp(pstObj.getString(FLD_LAST_LOGIN_IP));
            entObj.setEmployeeId(pstObj.getlong(FLD_EMPLOYEE_ID));
            entObj.setUserGroupNew(pstObj.getInt(FLD_USER_GROUP_NEW));
            entObj.setStartTime(pstObj.getDate(FLD_START_TIME));
            entObj.setEndTime(pstObj.getDate(FLD_END_TIME));
        }
        catch(DBException e) {
            System.out.println(e);
        }
        return entObj;
    }


    public static long insert(AppUser entObj) throws DBException, com.dimata.common.db.DBException  {
               // try{
        PstAppUser pstObj = new PstAppUser(0);

        pstObj.setString(FLD_LOGIN_ID, entObj.getLoginId());
        pstObj.setString(FLD_PASSWORD, entObj.getPassword());
        pstObj.setString(FLD_FULL_NAME, entObj.getFullName());
        pstObj.setString(FLD_EMAIL, entObj.getEmail());
        pstObj.setString(FLD_DESCRIPTION, entObj.getDescription());
        pstObj.setDate(FLD_REG_DATE, new Date());//entObj.getRegDate());
        pstObj.setDate(FLD_UPDATE_DATE, new Date());//entObj.getUpdateDate());
        pstObj.setInt(FLD_USER_STATUS, entObj.getUserStatus());
        pstObj.setLong(FLD_EMPLOYEE_ID, entObj.getEmployeeId());
        //pstObj.setDate(FLD_LAST_LOGIN_DATE, entObj.getLastLoginDate());
        //pstObj.setString(FLD_LAST_LOGIN_IP, entObj.getLastLoginIp());
        pstObj.setInt(FLD_USER_GROUP_NEW, entObj.getUserGroupNew());
        pstObj.setDate(FLD_START_TIME, entObj.getStartTime());
        pstObj.setDate(FLD_END_TIME, entObj.getEndTime());

        pstObj.insert();
        entObj.setOID(pstObj.getlong(FLD_USER_ID));
        return entObj.getOID();
      /*  }
        catch(DBException e) {
            System.out.println(e);
        }
        return 0;
  */
    }


    public static long update(AppUser entObj) {
        if( (entObj!=null) && (entObj.getOID() != 0)) {
            try {
                PstAppUser pstObj = new PstAppUser(entObj.getOID());

                pstObj.setString(FLD_LOGIN_ID, entObj.getLoginId());
                pstObj.setString(FLD_PASSWORD, entObj.getPassword());
                pstObj.setString(FLD_FULL_NAME, entObj.getFullName());
                pstObj.setString(FLD_EMAIL, entObj.getEmail());
                pstObj.setString(FLD_DESCRIPTION, entObj.getDescription());
                //pstObj.setDate(FLD_REG_DATE, entObj.getRegDate());
                pstObj.setDate(FLD_UPDATE_DATE, new Date()); //entObj.getUpdateDate());
                pstObj.setInt(FLD_USER_STATUS, entObj.getUserStatus());
                pstObj.setDate(FLD_LAST_LOGIN_DATE, entObj.getLastLoginDate());
                pstObj.setString(FLD_LAST_LOGIN_IP, entObj.getLastLoginIp());
                pstObj.setLong(FLD_EMPLOYEE_ID, entObj.getEmployeeId());
                pstObj.setInt(FLD_USER_GROUP_NEW, entObj.getUserGroupNew());
                pstObj.setDate(FLD_START_TIME, entObj.getStartTime());
                pstObj.setDate(FLD_END_TIME, entObj.getEndTime());
                pstObj.update();

                return entObj.getOID();
            }catch(Exception e) {
                System.out.println(e);
            }
        }
        return 0;
    }


    public static long delete(long oid) {
        try {
            PstAppUser pstObj = new PstAppUser(oid);
            pstObj.delete();
            return oid;
        }catch(Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public static int getCount(String whereClause){
        DBResultSet dbrs=null;
        try{
            int count = 0;
            String sql = " SELECT COUNT("+fieldNames[FLD_USER_ID] +") AS NRCOUNT" +
            " FROM " + TBL_APP_USER;


            if(whereClause != null && whereClause.length() > 0)
                sql = sql + " WHERE " + whereClause;

            //System.out.println(sql);
            dbrs=DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while(rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            return count;
        }
        catch (Exception exc){
            System.out.println("getCount "+ exc);
            return 0;
        }
        finally{
            DBResultSet.close(dbrs);
        }

    }

    public static Vector listPartObj(int start , int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs=null;
        try {
            String sql = "SELECT " +  fieldNames[FLD_USER_ID]
            + ", " + fieldNames[FLD_LOGIN_ID]
            + ", " + fieldNames[FLD_FULL_NAME]
            + ", " + fieldNames[FLD_EMAIL]
            + ", " + fieldNames[FLD_USER_STATUS]
            + ", " + fieldNames[FLD_EMPLOYEE_ID]
            + ", " + fieldNames[FLD_USER_GROUP_NEW]
            + " FROM " + TBL_APP_USER;


            if(whereClause != null && whereClause.length() > 0)
                sql = sql + " WHERE " + whereClause;

            if(order != null && order.length() > 0)
                sql = sql + " ORDER BY " + order;


            switch (DBHandler.DBSVR_TYPE) {
                case DBHandler.DBSVR_MYSQL :
						if(start == 0 && recordToGet == 0)
							sql = sql + "";
						else
							sql = sql + " LIMIT " + start + ","+ recordToGet ;

                        break;

                 case DBHandler.DBSVR_POSTGRESQL :
						if(start == 0 && recordToGet == 0)
							sql = sql + "";
						else
							sql = sql + " LIMIT " +recordToGet + " OFFSET "+ start ;

                        break;

                 case DBHandler.DBSVR_SYBASE :
                    	break;

                 case DBHandler.DBSVR_ORACLE :
                    	break;

                 case DBHandler.DBSVR_MSSQL :
                    	break;

                default:
                    ;
            }


            //System.out.println(sql);
            dbrs=DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while(rs.next()) {
                AppUser appUser = new AppUser();
                resultToObject(rs, appUser);
                lists.add(appUser);
            }
            return lists;

        }catch(Exception e) {
            System.out.println(e);
        }
        finally{
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    private static void resultToObject(ResultSet rs, AppUser entObj) {
        try {
            entObj.setOID(rs.getLong(fieldNames[FLD_USER_ID]));
            entObj.setLoginId(rs.getString(fieldNames[FLD_LOGIN_ID]));
            entObj.setFullName(rs.getString(fieldNames[FLD_FULL_NAME]));
            entObj.setEmail(rs.getString(fieldNames[FLD_EMAIL]));
            entObj.setUserStatus(rs.getInt(fieldNames[FLD_USER_STATUS]));
            entObj.setEmployeeId(rs.getLong(fieldNames[FLD_EMPLOYEE_ID]));
            entObj.setUserGroupNew(rs.getInt(fieldNames[FLD_USER_GROUP_NEW]));
            entObj.setStartTime(DBHandler.convertDate(rs.getDate(PstAppUser.fieldNames[PstAppUser.FLD_START_TIME]),rs.getTime(PstAppUser.fieldNames[PstAppUser.FLD_START_TIME])));
            entObj.setEndTime(DBHandler.convertDate(rs.getDate(PstAppUser.fieldNames[PstAppUser.FLD_END_TIME]),rs.getTime(PstAppUser.fieldNames[PstAppUser.FLD_END_TIME])));

        }catch(Exception e){
            System.out.println("resultToObject() " + e.toString());
        }
    }

    public static Vector listFullObj(int start , int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs=null;
        try {
            String sql = "SELECT " +  fieldNames[FLD_USER_ID] +
            " FROM " + TBL_APP_USER;


            if(whereClause != null && whereClause.length() > 0)
                sql = sql + " WHERE " + whereClause;

            if(order != null && order.length() > 0)
                sql = sql + " ORDER BY " + order;


            if((start == 0)&&(recordToGet == 0))
                sql = sql + "" ;  //nothing to do
            else
                sql = sql + " LIMIT " + start + ","+ recordToGet ;

            //System.out.println(sql);
            dbrs=DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while(rs.next()) {
                AppUser appUser = new AppUser();
                appUser = PstAppUser.fetch(rs.getLong(fieldNames[FLD_USER_ID]));
                lists.add(appUser);
            }
            rs.close();

        }catch(Exception e) {
            System.out.println(e);
        }
        finally{
            DBResultSet.close(dbrs);
        }
        return lists;
    }

    public static Vector getUserPrivObj(long userOID){
        if(userOID==0)
            return  new Vector(1,1);
        DBResultSet dbrs=null;
        try{
            String sql =
            "SELECT DISTINCT(PO."+PstAppPrivilegeObj.fieldNames[PstAppPrivilegeObj.FLD_CODE]+") AS CODE FROM "+PstAppPrivilegeObj.TBL_APP_PRIVILEGE_OBJ+" PO "
            +" INNER JOIN "+PstGroupPriv.TBL_GROUP_PRIV+" GP ON GP."+PstGroupPriv.fieldNames[PstGroupPriv.FLD_PRIV_ID]+"=PO."+PstAppPrivilegeObj.fieldNames[PstAppPrivilegeObj.FLD_PRIV_ID]
            +" INNER JOIN "+PstUserGroup.TBL_USER_GROUP+" UG ON UG."+PstUserGroup.fieldNames[PstUserGroup.FLD_GROUP_ID]+"=GP."+PstGroupPriv.fieldNames[PstGroupPriv.FLD_GROUP_ID]
            +" WHERE UG."+PstUserGroup.fieldNames[PstUserGroup.FLD_USER_ID]+"='"+userOID+"'";

            //System.out.println(sql);

            dbrs=DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            Vector privObjs = new Vector(10,2);
            while(rs.next()) {
                privObjs.add(new  Integer(rs.getInt("CODE")));
            }
            rs.close();
            return privObjs;

        }catch(Exception e) {
            System.out.println("getUserPrivObj " + e);
        }
        finally{
            DBResultSet.close(dbrs);
        }

        return new Vector(1,1);
    }


//    public static long updateUserStatus(long userOID, int status) {
//        if(userOID==0)
//            return 0;
//
//        DBResultSet dbrs=null;
//        try{
//            String sql = "UPDATE "+ TBL_APP_USER + " SET "+fieldNames[FLD_USER_STATUS]+"='"+status+"'"
//            + " WHERE "+fieldNames[FLD_USER_ID]+"='"+userOID+"'";
//
//            //System.out.println("sql Update User : "+sql);
//            DBHandler.execUpdate(sql);
//            return userOID;
//        } catch(Exception e) {
//            System.out.println("updateUserStatus " +e);
//            return 0;
//        }
//        finally{
//            DBResultSet.close(dbrs);
//        }
//    }

    public static AppUser getByLoginIDAndPassword(String loginID, String password) {
        if((loginID==null) || (loginID.length()<1) || (password==null) || (password.length()<1))
        return null;

        try{

            String whereClause = " "+fieldNames[FLD_LOGIN_ID]+"='"+loginID.trim()+"' AND "
            +fieldNames[FLD_PASSWORD] +"='"+password.trim()+"'";

            Vector appUsers = listFullObj(0,0, whereClause, "");

            if( (appUsers==null) || (appUsers.size()!=1))
                return new AppUser();

            return (AppUser)  appUsers.get(0);

        } catch(Exception e) {
            System.out.println("getByLoginIDAndPassword " +e);
            return null;
        }
    }

    public static boolean checkOID(long userId)
    {
        DBResultSet dbrs = null;
        boolean result = false;
        try
        {
            String sql = "SELECT * FROM " + TBL_APP_USER + " WHERE " +
                PstAppUser.fieldNames[PstAppUser.FLD_USER_ID] + " = " + userId;

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while(rs.next())
            {
                result = true;
            }
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println("err : "+e.toString());
        }
        finally
        {
            DBResultSet.close(dbrs);
        }
        return result;
    }
    
    //This method use to get kasir names according code kasir
    //by mirahu
    //20120315
    public static String getNameCashier(long codeCashier) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT " + PstAppUser.fieldNames[PstAppUser.FLD_FULL_NAME] + " FROM " + TBL_APP_USER +
                    " WHERE " + PstAppUser.fieldNames[PstAppUser.FLD_USER_ID] + " = '" + codeCashier + "'";

            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            String name = "";
            while (rs.next()) {
                name = rs.getString(1);
            }

            rs.close();
            return name;
        } catch (Exception e) {
            return "";
        } finally {
            DBResultSet.close(dbrs);
        }
    }

    //Untuk prochain POS
    public static final int GROUP_CASHIER	= 0;
    public static final int GROUP_SUPERVISOR	= 1;
    public static final int GROUP_STORE 	= 2;
    public static final int GROUP_WAREHOUSE	= 3;
    public static final int GROUP_ADMINISTRATOR	= 4;

    /**
     * Ari wiweka 20130713
     * untuk privillage sales admin
     */
    public static final int GROUP_SALES_ADMIN	= 5;

    public static final String UserGroupNewKey[]={"Cashier","Cashier Supervisor","Store","Warehouse","Administrator","Sales Admin"};
    public static final int UserGroupNewValue[]={0,1,2,3,4,5};

    public static Vector listUserGroupNewTypeKey()
    {
    	Vector result = new Vector(1,1);
        for(int s = 0; s < UserGroupNewKey.length; s++)
        {
            result.add(UserGroupNewKey[s]);
        }
        return result;
    }

    public static Vector listUserGroupTypeValue()
    {
    	Vector result = new Vector(1,1);
        for(int s = 0; s < UserGroupNewValue.length; s++)
        {
            result.add(""+UserGroupNewValue[s]);
        }
        return result;
    }


    //--------------- add by edhy ---------------------
    public static long updateByLoggedIn(AppUser entObj)
    {
        if( (entObj!=null) && (entObj.getOID() != 0))
        {
            try
            {
                PstAppUser pstObj = new PstAppUser(entObj.getOID());
                pstObj.setString(FLD_FULL_NAME, entObj.getFullName());
                pstObj.setString(FLD_EMAIL, entObj.getEmail());
                pstObj.setString(FLD_DESCRIPTION, entObj.getDescription());
                pstObj.setDate(FLD_UPDATE_DATE, new Date());
                pstObj.setInt(FLD_USER_STATUS, entObj.getUserStatus());
                pstObj.setDate(FLD_LAST_LOGIN_DATE, entObj.getLastLoginDate());
                pstObj.setString(FLD_LAST_LOGIN_IP, entObj.getLastLoginIp());
                pstObj.setLong(FLD_EMPLOYEE_ID, entObj.getEmployeeId());
                pstObj.setInt(FLD_USER_GROUP_NEW, entObj.getUserGroupNew());
                 pstObj.setDate(FLD_START_TIME, entObj.getStartTime());
                pstObj.setDate(FLD_END_TIME, entObj.getEndTime());
                pstObj.update();
                System.out.println("------------> update user data by LoggedIn process");
                return entObj.getOID();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        return 0;
    }
    //--------------- add by edhy ---------------------

}
