/*
 * PstAppPrivilegeObj.java
 *
 * Created on April 3, 2002, 9:29 AM
 */

package com.dimata.dtaxintegration.entity.biadmin;

/**
 *
 * @author  ktanjana
 * @version
 */

import java.sql.*;
import java.util.*;
import com.dimata.qdep.db.DBException;
import com.dimata.qdep.db.DBHandler;
import com.dimata.qdep.db.DBResultSet;
import com.dimata.qdep.db.I_DBInterface;
import com.dimata.qdep.db.I_DBType;
import com.dimata.qdep.entity.*;

public class PstAppPrivilegeObj extends DBHandler implements I_DBInterface, I_DBType, I_Persintent  {

    //public static final String TBL_APP_PRIVILEGE_OBJ = "APP_PRIVILEGE_OBJ";
    public static final String TBL_APP_PRIVILEGE_OBJ = "bi_app_privilege_obj";
    public static final int FLD_PRIV_OBJ_ID			= 0;
    public static final int FLD_PRIV_ID			= 1;
    public static final int FLD_CODE		= 2;



    public static  final String[] fieldNames = {
        "PRIV_OBJ_ID", "PRIV_ID", "CODE"
    } ;

    public static int[] fieldTypes = {
        TYPE_PK + TYPE_LONG + TYPE_ID, TYPE_LONG, TYPE_INT
    };

    public static final String FILTER_OBJ_MYSQL = "0xFFFFFF00";
    public static final String FILTER_OBJ_ORACLE = "-256";
    
    public static final String FILTER_CMD_MYSQL = "0x000000FF";
    public static final String FILTER_CMD_ORACLE = "255";
    
    public static final String SEPERATOR_MYSQL = " & ";
    public static final String SEPERATOR_ORACLE = " , ";
    
    public static final String HEXADECIMAL_MYSQL = "HEX";
    public static final String HEXADECIMAL_ORACLE = "HEX";
    
    public static final String strObjFilter = FILTER_OBJ_ORACLE;//"0xFFFFFF00";
    public static final String strCmdFilter = FILTER_CMD_ORACLE;//"0x000000FF";
    public static final String strSeperator = SEPERATOR_ORACLE;//"0x000000FF";
    public static final String strHexadecimal = HEXADECIMAL_ORACLE;//"0x000000FF";
    
    public static final int intObjFilter = 0xFFFFFF00;
    public static final int intCmdFilter = 0x000000FF;

    /** Creates new PstAppPrivilegeObj */
    public PstAppPrivilegeObj() {
    }

    public PstAppPrivilegeObj(int i) throws DBException {
        super(new PstAppPrivilegeObj());
    }


    public PstAppPrivilegeObj(String sOid) throws DBException {
        super(new PstAppPrivilegeObj(0));
        if(!locate(sOid))
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        else
            return;
    }


    public PstAppPrivilegeObj(long lOid) throws DBException {
        super(new PstAppPrivilegeObj(0));
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
        return TBL_APP_PRIVILEGE_OBJ;
    }

    public String getPersistentName() {
        return new PstAppPrivilegeObj().getClass().getName();
    }


    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }


    public long delete(Entity ent) {
        return delete((AppPrivilegeObj) ent);
    }

    public long insert(Entity ent) {
        return PstAppPrivilegeObj.insert((AppPrivilegeObj) ent);
    }


    public long update(Entity ent) {
        return update((AppPrivilegeObj) ent);
    }

    public long fetch(Entity ent) {
        AppPrivilegeObj entObj = PstAppPrivilegeObj.fetch(ent.getOID());
        ent = (Entity)entObj;
        return ent.getOID();
    }


    public static AppPrivilegeObj fetch(long oid) {
        AppPrivilegeObj entObj = new AppPrivilegeObj();
        try {
            PstAppPrivilegeObj pstObj = new PstAppPrivilegeObj(oid);
            entObj.setOID(oid);
            entObj.setPrivId(pstObj.getlong(FLD_PRIV_ID));
            entObj.setCode(pstObj.getInt(FLD_CODE));

            entObj.setCommands( pstObj.listCmd_ByPrivObj(entObj.getPrivId(), entObj.getCode()));
        }
        catch(DBException e) {
            System.out.println(e);
        }
        return entObj;
    }

    public static long insert(AppPrivilegeObj entObj) {
        try{
            int max = entObj.getCommandsSize();
            if(max<1)
                return 0;

            deleteByPrivOIDObjCode(entObj.getPrivId(), entObj.getCode() );

            int moduleObj = entObj.getCode() & intObjFilter;

            for(int i=0; i<max;i++){
                AppPrivilegeObj objTmp =  new  AppPrivilegeObj();

                objTmp.setPrivId(entObj.getPrivId());
                objTmp.setCode(moduleObj + (entObj.getCommand(i) & intCmdFilter));

                long oid = PstAppPrivilegeObj.insertObj(objTmp);
                if(oid>0)
                    entObj.setOID(objTmp.getOID());
            }
            return entObj.getOID();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    private static long insertObj(AppPrivilegeObj entObj) {
        try{
            PstAppPrivilegeObj pstObj = new PstAppPrivilegeObj(0);

            pstObj.setLong(FLD_PRIV_ID, entObj.getPrivId());
            pstObj.setInt(FLD_CODE , entObj.getCode());

            pstObj.insert();
            entObj.setOID(pstObj.getlong(FLD_PRIV_OBJ_ID));
            return entObj.getOID();
        }
        catch(DBException e) {
            System.out.println(e);
        }
        return 0;
    }


    /**
     * update module priv. as group. Delete the group first then insert
     */
    public static long update(AppPrivilegeObj entObj) {
        try{

            int max = entObj.getCommandsSize();

            deleteByPrivOIDObjCode(entObj.getPrivId(), entObj.getCode() );

            if(max<1){
                return entObj.getOID();
            }

            int moduleObj = entObj.getCode() & intObjFilter;

            for(int i=0; i<max;i++){
                AppPrivilegeObj objTmp =  new  AppPrivilegeObj();

                objTmp.setPrivId(entObj.getPrivId());
                objTmp.setCode(moduleObj + (entObj.getCommand(i) & intCmdFilter));
                objTmp.setOID(0);
                long oid = PstAppPrivilegeObj.insertObj(objTmp);
                if(oid>0)
                    entObj.setOID(objTmp.getOID());
            }
            return entObj.getOID();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    private static long updateObj(AppPrivilegeObj entObj) {
        if( (entObj!=null) && (entObj.getOID() != 0)) {
            try {
                PstAppPrivilegeObj pstObj = new PstAppPrivilegeObj(entObj.getOID());
                pstObj.setLong(FLD_PRIV_ID, entObj.getPrivId());
                pstObj.setInt(FLD_CODE, entObj.getCode());

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
            PstAppPrivilegeObj pstObj = new PstAppPrivilegeObj(oid);
            pstObj.delete();
            return oid;
        }catch(Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public static long deleteByPrivOID(long privOID){
        try{
            if(privOID==0)
                return 0;

            int count = 0;
            String sql = " DELETE " +
            " FROM " + TBL_APP_PRIVILEGE_OBJ +
            " WHERE " + fieldNames[FLD_PRIV_ID]+ " = '"+privOID+"'";

            System.out.println(sql);
            DBHandler.execUpdate(sql);
        }
        catch (Exception exc){
            System.out.println( "PstAppPrivilegeObj.deleteByPrivOID "+ exc);
            return 0;
        }
        return privOID;
    }

    public static long deleteByPrivOIDObjCode(long privOID, int objCode){
        try{
            if(privOID==0)
                return 0;
            System.out.println(" deleteByPrivOIDObjCode =>  ");
            
            String sql = "";
            switch (DBHandler.DBSVR_TYPE) {
                 case DBHandler.DBSVR_ORACLE : 
                     sql=" DELETE " +
                        " FROM " + TBL_APP_PRIVILEGE_OBJ +
                        " WHERE " + fieldNames[FLD_PRIV_ID]+ " = '"+privOID+"' AND (BITAND("+
                            fieldNames[FLD_CODE] + strSeperator + strObjFilter+ ")) = '"+
                            (objCode & intObjFilter ) +"'";
                    
                    break;
                     
                 default:
                    sql=" DELETE " +
                        " FROM " + TBL_APP_PRIVILEGE_OBJ +
                        " WHERE " + fieldNames[FLD_PRIV_ID]+ " = '"+privOID+"' AND ("+
                            fieldNames[FLD_CODE] + strSeperator + strObjFilter+ ") = '"+
                            (objCode & intObjFilter ) +"'";
            }
            

            System.out.println(sql);
            DBHandler.execUpdate(sql);
            return privOID;
        }
        catch (Exception exc){
            System.out.println("deleteByPrivOID "+ exc);
        }
        return 0;
    }

/*
    public static int getCount(String whereClause){
        try{
            int count = 0;
            String sql = " SELECT COUNT("+fieldNames[FLD_PRIV_OBJ_ID] +") AS NRCOUNT" +
                         " FROM " + TBL_APP_PRIVILEGE_OBJ;


            if(whereClause != null && whereClause.length() > 0)
                sql = sql + " WHERE " + whereClause;

            System.out.println(sql);
            ResultSet rs = execQuery(sql);

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

    }
 */

    public static int getCountByPrivOID_GroupByObj(long privOID){
        DBResultSet dbrs=null;
        try{
            int count = 0;
            /*
            String sql = " SELECT COUNT( DISTINCT("+  fieldNames[FLD_CODE] +" & 0xFFFFFF00)) AS NRCOUNT" +
            " FROM " + TBL_APP_PRIVILEGE_OBJ;
             **/

            String sql = "";
            
            switch (DBHandler.DBSVR_TYPE) {
                 case DBHandler.DBSVR_ORACLE : 
                     sql = " SELECT COUNT(DISTINCT(BITAND("+ fieldNames[FLD_CODE] +","+ strObjFilter + "))) AS NRCOUNT" + " FROM " + TBL_APP_PRIVILEGE_OBJ;

                    if(privOID!=0){
                        sql = sql + " WHERE " + fieldNames[FLD_PRIV_ID]+ " = '"+privOID+"'";
                    }
                    sql= sql + " GROUP BY BITAND("+ fieldNames[FLD_CODE] +","+ strObjFilter + ") ";
                    
                    break;
                     
                 default:
                    sql = " SELECT COUNT( DISTINCT("+strHexadecimal+"("+  fieldNames[FLD_CODE] +strSeperator+strObjFilter+"))) AS NRCOUNT" + " FROM " + TBL_APP_PRIVILEGE_OBJ;

                    if(privOID!=0)
                        sql = sql + " WHERE " + fieldNames[FLD_PRIV_ID]+ " = '"+privOID+"'";

                    sql= sql + " GROUP BY ("+ fieldNames[FLD_CODE] +strSeperator+ strObjFilter + ") ";
            }
            
            System.out.println(" getCountByPrivOID_GroupByObj => " +sql);

            dbrs=DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while(rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        }
        catch (Exception exc){
            System.out.println("getCountByPrivOID "+ exc);
            return 0;
        }
       finally{
            DBResultSet.close(dbrs);
       }


    }

    public static int getCountByPrivOID(long privOID){
        DBResultSet dbrs=null;
        try{
            int count = 0;
            String sql = " SELECT COUNT("+fieldNames[FLD_PRIV_OBJ_ID] +") AS NRCOUNT" +
            " FROM " + TBL_APP_PRIVILEGE_OBJ;


            if(privOID!=0)
                sql = sql + " WHERE " + fieldNames[FLD_PRIV_ID]+ " = '"+privOID+"'";

            System.out.println(sql);
            dbrs=DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while(rs.next()) {
                count = rs.getInt(1);
            }

            return count;
        }
        catch (Exception exc){
            System.out.println("getCountByPrivOID "+ exc);
            return 0;
        }
       finally{
            DBResultSet.close(dbrs);
       }


    }

    public static Vector listCmd_ByPrivObj(long privOID, int objCode){
        Vector lists = new Vector();
        DBResultSet dbrs= null;
        try {
            String sql = "";
            
            switch (DBHandler.DBSVR_TYPE) {
                 case DBHandler.DBSVR_ORACLE : 
                    sql= "SELECT "+fieldNames[FLD_CODE] +
                        " FROM " + TBL_APP_PRIVILEGE_OBJ +
                        " WHERE " + fieldNames[FLD_PRIV_ID]+ " = '"+privOID+"' AND (BITAND("+
                            fieldNames[FLD_CODE] + "," + strObjFilter+ ")) = '"+ (objCode & intObjFilter ) +"' " +
                        " ORDER BY (BITAND(" + fieldNames[FLD_CODE] + strSeperator + strCmdFilter+ "))";
                    break;
                     
                 default:
                    sql= "SELECT "+fieldNames[FLD_CODE] +
                        " FROM " + TBL_APP_PRIVILEGE_OBJ +
                        " WHERE " + fieldNames[FLD_PRIV_ID]+ " = '"+privOID+"' AND ("+
                            fieldNames[FLD_CODE] + strSeperator + strObjFilter+ ") = '"+ (objCode & intObjFilter ) +"' " +
                        " ORDER BY (" + fieldNames[FLD_CODE] + strSeperator + strCmdFilter+ ")";
            }
            

            System.out.println(" listCmd_ByPrivObj = > " +sql);

            dbrs=DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while(rs.next()) {
                lists.add(new Integer(rs.getInt(fieldNames[FLD_CODE]) & intCmdFilter ));
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

    public static Vector listWithCmd_ByPrivOID_GroupByObj( int start , int recordToGet,
    long privOID, String order){

        Vector listObjs = listByPrivOID_GroupByObj(start , recordToGet, privOID, order);

        Vector lists = new Vector(1,1);
        if( (listObjs==null) || (listObjs.size()<1))
            return lists;
        int max= listObjs.size();
        for(int i=0; i<max; i++){
            AppPrivilegeObj appPrivObj = (AppPrivilegeObj)listObjs.get(i);
            appPrivObj.setCommands(listCmd_ByPrivObj(privOID, appPrivObj.getCode()));
            lists.add(appPrivObj);
        }
        return lists;
    }


    public static Vector listByPrivOID_GroupByObj( int start , int recordToGet,
    long privOID, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs=null;
        try {
            String sql = "SELECT "+fieldNames[FLD_PRIV_ID] +
            ", " + fieldNames[FLD_CODE] +
            ", " + fieldNames[FLD_PRIV_OBJ_ID] +
            " FROM " + TBL_APP_PRIVILEGE_OBJ;


            if(privOID!=0){
                sql = sql + " WHERE " + fieldNames[FLD_PRIV_ID]+ " = '"+privOID+"'";
            }   
            
             switch (DBHandler.DBSVR_TYPE) {
                 case DBHandler.DBSVR_ORACLE : 
                    sql= sql + " GROUP BY (BITAND("+ fieldNames[FLD_CODE] +strSeperator+ strObjFilter + ")), "+
                                fieldNames[FLD_PRIV_ID]+", "+
                                fieldNames[FLD_CODE]+", "+
                                fieldNames[FLD_PRIV_OBJ_ID]+"";
                     break;
                 default:
                    sql= sql + " GROUP BY ("+ fieldNames[FLD_CODE] +strSeperator+ strObjFilter + ") ";
            }
            
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

            System.out.println(" listByPrivOID_GroupByObj =>" + sql);

            dbrs=DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while(rs.next()) {
                AppPrivilegeObj appPrivObj = new AppPrivilegeObj();
                resultToObject(rs, appPrivObj);
                lists.add(appPrivObj);
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


    public static Vector listByPrivOID(int start , int recordToGet, long privOID, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs=null;
        try {
            String sql = "SELECT "+fieldNames[FLD_PRIV_ID] +
            ", " + fieldNames[FLD_CODE] +
            ", " + fieldNames[FLD_PRIV_OBJ_ID] +
            " FROM " + TBL_APP_PRIVILEGE_OBJ;


            if(privOID!=0)
                sql = sql + " WHERE " + fieldNames[FLD_PRIV_ID]+ " = '"+privOID+"'";

            if(order != null && order.length() > 0)
                sql = sql + " ORDER BY " + order;


            if((start == 0)&&(recordToGet == 0))
                sql = sql + "" ;  //nothing to do
            else
                sql = sql + " LIMIT " + start + ","+ recordToGet ;

            System.out.println(sql);
            dbrs=DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while(rs.next()) {
                AppPrivilegeObj appPriv = new AppPrivilegeObj();
                resultToObject(rs, appPriv);
                lists.add(appPriv);
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



    private static void resultToObject(ResultSet rs, AppPrivilegeObj entObj) {
        try {
            entObj.setOID(rs.getLong(fieldNames[FLD_PRIV_OBJ_ID]));
            entObj.setPrivId(rs.getLong(fieldNames[FLD_PRIV_ID]));
            entObj.setCode(rs.getInt(fieldNames[FLD_CODE]));
        }catch(Exception e){
            System.out.println("resultToObject() " + e.toString());
        }
    }



}
