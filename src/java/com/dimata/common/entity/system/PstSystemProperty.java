/* Generated by Together */

package com.dimata.common.entity.system;


import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

//import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.common.db.*;

/***/



public class PstSystemProperty extends DBHandler implements I_DBInterface, I_DBType, I_Persintent {
    /**
     *	Table Map
     */
    public static final String TBL_SYSPROP = "system_property";
    
    public static final int FLD_SYSPROP_ID 	= 0;
    public static final int FLD_NAME 	= 1;
    public static final int FLD_VALUE 	= 2;
    public static final int FLD_VALTYPE = 3;
    public static final int FLD_DISTYPE = 4;
    public static final int FLD_GROUP	= 5;
    public static final int FLD_NOTE    = 6;
    
    public static String[] fieldNames = {
        "SYSPROP_ID",
        "NAME",
        "VALUEPROP",
        "VALTYPE",
        "DISTYPE",
        "GROUPPROP",
        "NOTE"
    };
    
    public static int[] fieldTypes = {
        TYPE_PK + TYPE_LONG+ TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };
    
    
    //	Other constanta goes here (if any)
    
    public static String[] valueTypes = { "STRING", "TEXT","NUMBER" };
    public static String[] displayTypes = { "SINGLE TEXT","MULTI TEXT","DROP DOWN" };
    
    
    
    /**
     *	Contractor
     */
    public PstSystemProperty() {
    }
    
    
    public PstSystemProperty(int i) throws DBException {
        super(new PstSystemProperty());
    }
    
    
    public PstSystemProperty(String sOid) throws DBException {
        super(new PstSystemProperty(0));
        if(!locate(sOid))
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        else
            return;
    }
    
    
    public PstSystemProperty(long lOid) throws DBException {
        super(new PstSystemProperty(0));
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
    
    
    /**
     *	Implemanting I_Entity interface methods
     */
    public int getFieldSize() {
        return fieldNames.length;
    }
    
    public String getTableName() {
        return TBL_SYSPROP;
    }
    
    public String[] getFieldNames() {
        return fieldNames;
    }
    
    public int[] getFieldTypes() {
        return fieldTypes;
    }
    
    public String getPersistentName() {
        return new PstSystemProperty().getClass().getName();
    }
    
    
    /**
     *	Implemanting I_DBInterface interface methods
     */
    public long fetch(Entity ent) {
        SystemProperty sysProp = PstSystemProperty.fetch(ent.getOID());
        ent = (Entity)sysProp;
        return sysProp.getOID();
    }
    
    
    public long insert(Entity ent) {
        return PstSystemProperty.insert((SystemProperty) ent);
    }
    
    public long update(Entity ent) {
        return update((SystemProperty) ent);
    }
    
    public long delete(Entity ent) {
        return delete((SystemProperty) ent);
    }
    
    
    
    
    public static SystemProperty fetch(long oid) {
        SystemProperty sysProp = new SystemProperty();
        try {
            PstSystemProperty pSystemProperty = new PstSystemProperty(oid);
            sysProp.setOID(oid);
            sysProp.setName(pSystemProperty.getString(FLD_NAME));
            sysProp.setValue(pSystemProperty.getString(FLD_VALUE));
            sysProp.setValueType(pSystemProperty.getString(FLD_VALTYPE));
            sysProp.setDisplayType(pSystemProperty.getString(FLD_DISTYPE));
            sysProp.setGroup(pSystemProperty.getString(FLD_GROUP));
            sysProp.setNote(pSystemProperty.getString(FLD_NOTE));
        }
        catch(DBException e) {
            System.out.println(e.toString());
        }
        return sysProp;
    }
    
    
    public static long insert(SystemProperty sysProp) {
        try{
            PstSystemProperty pSystemProperty = new PstSystemProperty(0);
            pSystemProperty.setString(FLD_NAME, sysProp.getName());
            pSystemProperty.setString(FLD_VALUE, sysProp.getValue());
            pSystemProperty.setString(FLD_VALTYPE, sysProp.getValueType());
            pSystemProperty.setString(FLD_DISTYPE, sysProp.getDisplayType());
            pSystemProperty.setString(FLD_GROUP, sysProp.getGroup());
            pSystemProperty.setString(FLD_NOTE, sysProp.getNote());
            
            pSystemProperty.insert();
            sysProp.setOID(pSystemProperty.getlong(FLD_SYSPROP_ID));
            return sysProp.getOID();
        }
        catch(DBException e) {
            System.out.println(e.toString());
        }
        return 0;
    }
    
    
    public static long update(SystemProperty sysProp) {
        if(sysProp.getOID() != 0) {
            try {
                PstSystemProperty pSystemProperty = new PstSystemProperty(sysProp.getOID());
                pSystemProperty.setString(FLD_NAME, sysProp.getName());
                pSystemProperty.setString(FLD_VALUE, sysProp.getValue());
                pSystemProperty.setString(FLD_VALTYPE, sysProp.getValueType());
                pSystemProperty.setString(FLD_DISTYPE, sysProp.getDisplayType());
                pSystemProperty.setString(FLD_GROUP, sysProp.getGroup());
                pSystemProperty.setString(FLD_NOTE, sysProp.getNote());
                
                pSystemProperty.update();
                return sysProp.getOID();
            }catch(Exception e) {
                System.out.println(e.toString());
            }
        }
        return 0;
    }
    
    
    public static long delete(long oid) {
        try {
            PstSystemProperty pSystemProperty = new PstSystemProperty(oid);
            pSystemProperty.delete();
            return oid;
        }catch(Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }
    
    
    
    
    public static Vector listAll() {
        return list(0, 0, null,null);
    }
    
    public static Vector listByGroup(String gr) {
        String whereClause = fieldNames[FLD_GROUP] + " = '"+ gr + "'";
        String orderClause = fieldNames[FLD_NAME];
        return list(0, 0, whereClause, orderClause);
    }
    
    
    
    public static Vector listGroups() {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            
            String sql = "SELECT DISTINCT("+ FLD_GROUP +") FROM " + TBL_SYSPROP;
            //ResultSet rs = execQuery(sql);
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            
            while(rs.next()) {
                lists.add(rs.getString(1));
            }
            rs.close();
            return lists;
            
        }catch(Exception e) {
            System.out.println(e.toString());
        }finally {
            DBResultSet.close(dbrs);
        }
        
        return new Vector();
    }
    
    
    
    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            
            String sql = "SELECT * FROM " + TBL_SYSPROP + " ";
            
            if(whereClause != null && whereClause.length() > 0)
                sql = sql + " WHERE " + whereClause;
            
            if(order != null && order.length() > 0)
                sql = sql + " ORDER BY " + order;
            
            switch (DBHandler.DBSVR_TYPE) {
                case DBHandler.DBSVR_MYSQL :
                    if(limitStart == 0 && recordToGet == 0)
                        sql = sql + "";
                    else
                        sql = sql + " LIMIT " + limitStart + ","+ recordToGet ;
                    
                    break;
                    
                case DBHandler.DBSVR_POSTGRESQL :
                    if(limitStart == 0 && recordToGet == 0)
                        sql = sql + "";
                    else
                        sql = sql + " LIMIT " +recordToGet + " OFFSET "+ limitStart ;
                    
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
            
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            
            while(rs.next()) {
                SystemProperty sysProp = new SystemProperty();
                resultToObject(rs, sysProp);
                lists.add(sysProp);
            }
            rs.close();
            return lists;
            
        }catch(Exception e) {
            System.out.println("PstSystemProperty.list(#,#,#,#): "+e.toString());
        }
        return new Vector();
    }
    
    
    public static String getValueByName(String name) {
        String val = "0";
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT "+ fieldNames[FLD_VALUE] +" FROM "+ TBL_SYSPROP +" WHERE " + fieldNames[FLD_NAME] + "='" + name +"'";
            
            System.out.println("load system property: "+name);
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            
            while(rs.next()) {
                val = rs.getString(1);
                break;
            }
            rs.close();
            return val;
            
        }catch(Exception e) {
            System.out.println(e.toString());
        }finally {
            DBResultSet.close(dbrs);
        }
        return val;
    }
    
    
    
    public static int getCount() {
        int count = 0;
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT COUNT("+ fieldNames[FLD_SYSPROP_ID] +") FROM " + TBL_SYSPROP;
            //ResultSet rs = execQuery(sql);
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while(rs.next()) {
                count = rs.getInt(1);
                break;
            }
            rs.close();
            return count;
            
        }catch(Exception e) {
            System.out.println(e.toString());
        }finally {
            DBResultSet.close(dbrs);
        }
        return 0;
    }
    
    
    private static void resultToObject(ResultSet rs, SystemProperty sysProp) {
        try {
            sysProp.setOID(rs.getLong(FLD_SYSPROP_ID + 1));
            sysProp.setName(rs.getString(FLD_NAME + 1));
            sysProp.setValue(rs.getString(FLD_VALUE + 1));
            sysProp.setValueType(rs.getString(FLD_VALTYPE + 1));
            sysProp.setDisplayType(rs.getString(FLD_DISTYPE + 1));
            sysProp.setGroup(rs.getString(FLD_GROUP + 1));
            sysProp.setNote(rs.getString(FLD_NOTE + 1));
            
        }catch(Exception e){
            System.out.println("resultToObject() " + e.toString());
        }
    }
    
} // end of PstSystemProperty
