/* Generated by Together */

package com.dimata.common.db;

import java.util.*;

public class OIDFactory
{
    private static int appIdx = 7;
    private static OIDFactory oidFactory = null;
    static long lastOID=0;
    public final static long filterAppIndex =  0xFF00000000000000L;
    public final static long factorId = 0x0100000000000000L;
    public final static long filterObjIdOnly =  0x00FFFFFFFFFFFFFFL;
  
    public OIDFactory()
    {
    }

    public OIDFactory(int appIndex)
    {
        getOIDFactory();
        appIdx = appIndex;
    }

	public static OIDFactory getOIDFactory()
    {
        if(oidFactory == null) {
            oidFactory = new OIDFactory();
        }
        return  oidFactory;
    }


    public int getAppIndex(){
    	return appIdx;
    }

    public void setAppIndex(int idx){
    	appIdx = idx;
    }
    
    public int getAppIndex(long OID){
        if(OID != 0)
            appIdx = (int)((OID & filterAppIndex ) / factorId);  	
    	 
        return appIdx;
    }    

    synchronized public static long generateOID()
    {
        Date dateGenerated = new Date();
        long oid = dateGenerated.getTime() + (0x0100000000000000L * appIdx);
        while(lastOID== oid){
	        try{
	            Thread.sleep(1);
	        }catch(Exception e){}
            dateGenerated = new Date();
			oid=dateGenerated.getTime() + (0x0100000000000000L * appIdx);
            //System.out.print("try oid="+oid);
        }
		//System.out.print("new oid="+oid);
        lastOID=oid;
        return oid;
    }


} // end of OIDFactory
