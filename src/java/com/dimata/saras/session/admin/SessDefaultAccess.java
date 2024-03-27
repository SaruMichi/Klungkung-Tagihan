/* Generated by Together */

package com.dimata.saras.session.admin;

import java.util.*;

import com.dimata.qdep.db.DBHandler;
import com.dimata.qdep.db.DBResultSet;
import com.dimata.saras.entity.admin.*;


public class SessDefaultAccess
{


    public static final int PRIV_GROUP_SYSTEM_ACCESS        = 0;

   		public static final int PRIV_SYSTEM_ACCESS_LOGIN    = 0;
    	public static final int PRIV_SYSTEM_ACCESS_LOGOUT   = 1;

    public static final int PRIV_GROUP_ADMIN                = 1;

   		public static final int PRIV_ADMIN_USER             = 0;
    	public static final int PRIV_ADMIN_SYSTEM           = 1;

    public static final int PRIV_GROUP_MASTER_DATA          = 2;

    	public static final int PRIV_MASTER_DATA_COMPANY	 	= 0;
    	public static final int PRIV_MASTER_DATA_MANAGEMENT     = 1;
        public static final int PRIV_MASTER_DATA_LOCKER  		= 2;
        public static final int PRIV_MASTER_DATA_SCHEDULE 		= 3;
        public static final int PRIV_MASTER_DATA_APPRAISAL  	= 4;


    public static final int PRIV_GROUP_CLINIC           	= 3;

    	public static final int PRIV_CLINIC_MEDICAL       	= 0;
        public static final int PRIV_CLINIC_EXPENSE		    = 1;


    public static final int PRIV_GROUP_LOCKER            = 4;

    	public static final int PRIV_LOCKER			      = 0;

    public static final int PRIV_GROUP_EMPLOYEE           = 5;

    	public static final int PRIV_EMP_DATABANK     		= 0;
        public static final int PRIV_EMP_ATTENDANCE     	= 1;
        public static final int PRIV_EMP_ATT_MACHINE     	= 2;
        public static final int PRIV_EMP_PRESENCE   		= 3;
        public static final int PRIV_EMP_SALARY   			= 4;
        public static final int PRIV_EMP_APPRAISAL   		= 5;
        public static final int PRIV_EMP_EXPLANATION   		= 6;

	public static final String[] privGroupNames =
    	{ 
        "Login Access",
        "Admin",
        "Master Data",
        "HRD",
        "Accounting",
        "Purchasing",
        "Production",
        "Warehouse",
        "Marketing"
        };

    public static final String[][] privNames =
    	{
        /*login*/
    		{ "Login Page", "Logout Page" },
    	/*admin*/
            { "User Management", "System Management" },
    	/*master data*/
            { "Company", "Data Management", "Company","Locker", "Schedule","Appraisal"},
        /*clinic*/
    		{ "Medical","Expense"},
        /*locker*/
    		{ "Locker"},
        /*employee*/
    		{ "Data Bank", "Attendance", "Attendance Machine", "Presence", "Salary", "Appraisal", "Explanation" }
    	};

    public static final String[][] privDescription =
    	{
        /*login*/
    		{ "Login Page", "Logout Page" },
    	/*admin*/
    		{ "User Management", "System Management" },
    	/*master data */
            { "Administer Company", "Administer Management", "Administer Locker", "Administer Schedule", "Administer Appraisal"},
        /*clinic*/
    		{ "Administer Clinic Data", "Medical Expense"},
        /*locker*/
    		{ "Adaminister Locker Data"},
        /*employee*/
    		{ "Data Bank", "Attendance", "Attendance Machine", "Employee Presence", "Employee Salary"," Employee Appraisal", "Explanation and Coverage"}
    	};

    public static final int [][][][] privModules =
    {
        //login
    	{
	         //login Page
		   	 {
	    	 	{AppObjInfo.G1_LOGIN , AppObjInfo.G2_LOGIN, AppObjInfo.OBJ_LOGIN_LOGIN}
	         },
	         // logout Page
	         {
	    		{AppObjInfo.G1_LOGIN , AppObjInfo.G2_LOGIN, AppObjInfo.OBJ_LOGIN_LOGOUT}
	         }
    	},
		//admin
    	{
            //User Management
            {
                {AppObjInfo.G1_ADMIN , AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_PRIVILEGE},
      	   		{AppObjInfo.G1_ADMIN , AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_GROUP},
      	   		{AppObjInfo.G1_ADMIN , AppObjInfo.G2_ADMIN_USER, AppObjInfo.OBJ_ADMIN_USER_USER}
            },
            //System Management
            {
      	 		{AppObjInfo.G1_ADMIN , AppObjInfo.G2_ADMIN_SYSTEM, AppObjInfo.OBJ_ADMIN_SYSTEM_BACK_UP},
      	 		{AppObjInfo.G1_ADMIN , AppObjInfo.G2_ADMIN_SYSTEM, AppObjInfo.OBJ_ADMIN_SYSTEM_APP_SET}
            }
      	},
        //Master Data
        /*{
            //Company
        	{
        	 	{AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_COMP, AppObjInfo.OBJ_MASTER_D_DEPARTMENT},
                {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_COMP, AppObjInfo.OBJ_MASTER_D_EMP_POSITION},
                {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_COMP, AppObjInfo.OBJ_MASTER_D_EMP_SECTION},
                {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_COMP, AppObjInfo.OBJ_MASTER_D_GROUP_RANK},
                {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_COMP, AppObjInfo.OBJ_MASTER_D_EMP_LEVEL},
                {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_COMP, AppObjInfo.OBJ_MASTER_D_EMP_CATEGORY}
        	},
            // Data Management
        	{
                {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_MANAG, AppObjInfo.OBJ_MASTER_D_PERIODE},
                {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_MANAG, AppObjInfo.OBJ_MASTER_D_RELIGION},
                {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_MANAG, AppObjInfo.OBJ_MASTER_D_MARITAL},
                {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_MANAG, AppObjInfo.OBJ_MASTER_D_LANGUAGE}
        	},
            // Locker
        	{
               {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_LOCKER, AppObjInfo.OBJ_MASTER_D_LOCKER_LOC}
        	},
            // Schedule
        	{
               {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_SCHEDULE, AppObjInfo.OBJ_MASTER_D_SCH_SYMBOL},
               {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_SCHEDULE, AppObjInfo.OBJ_MASTER_D_SCH_CATEGORY}
        	},
            // Appraisal
        	{
               {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_APPRAISAL, AppObjInfo.OBJ_MASTER_D_APP_GROUP_CATEGORY},
               {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_APPRAISAL, AppObjInfo.OBJ_MASTER_D_APP_CATEGORY},
               {AppObjInfo.G1_MASTER_D , AppObjInfo.G2_MASTER_D_APPRAISAL, AppObjInfo.OBJ_MASTER_D_APP_EVALUATE}
        	}
      },
      // Clinic
      {
        	//Medical
        	{
               {AppObjInfo.G1_CLINIC , AppObjInfo.G2_CLINIC_MEDICAL, AppObjInfo.OBJ_CLINIC_MEDICINE},
               {AppObjInfo.G1_CLINIC , AppObjInfo.G2_CLINIC_MEDICAL, AppObjInfo.OBJ_CLINIC_MED_CONSUMP},
               {AppObjInfo.G1_CLINIC , AppObjInfo.G2_CLINIC_MEDICAL, AppObjInfo.OBJ_CLINIC_EMP_VISIT},
               {AppObjInfo.G1_CLINIC , AppObjInfo.G2_CLINIC_MEDICAL, AppObjInfo.OBJ_CLINIC_GUEST_HANDLING}
        	},
            //Expense
        	{
            	{AppObjInfo.G1_CLINIC , AppObjInfo.G2_CLINIC_EXPENSE, AppObjInfo.OBJ_CLINIC_EXP_TYPE},
                {AppObjInfo.G1_CLINIC , AppObjInfo.G2_CLINIC_EXPENSE, AppObjInfo.OBJ_CLINIC_EXP_RECAPITULATE}
        	}

      },
      //locker
      {
        	//locker
      		{
             	{AppObjInfo.G1_LOCKER , AppObjInfo.G2_LOCKER, AppObjInfo.OBJ_LOCKER},
                {AppObjInfo.G1_LOCKER , AppObjInfo.G2_LOCKER, AppObjInfo.OBJ_LOCKER_TREATMENT}
      		}
      },
      //employee
      {
        	//data bank
      	 	{
      	 		{AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_DATABANK, AppObjInfo.OBJ_DB_EMPLOYEE},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_DATABANK, AppObjInfo.OBJ_DB_FAM_MEMBER},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_DATABANK, AppObjInfo.OBJ_DB_EMP_LANGUAGE},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_DATABANK, AppObjInfo.OBJ_DB_EMP_EDUCATION},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_DATABANK, AppObjInfo.OBJ_DB_EMP_EXPERIENCE},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_DATABANK, AppObjInfo.OBJ_DB_EMP_CAREER_PATH}
      		},
            //attendance
      		{
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_ATTENDANCE, AppObjInfo.OBJ_ATT_WORK_SCHEDULE},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_ATTENDANCE, AppObjInfo.OBJ_ATT_LEAVE_MANAGEMENT},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_ATTENDANCE, AppObjInfo.OBJ_ATT_DAY_OF_PAYMENT},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_ATTENDANCE, AppObjInfo.OBJ_ATT_OCCUPANCY}
      		},
            //attendance machine
      		{
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_ATT_MACHINE, AppObjInfo.OBJ_MAC_UPLOAD_BARCODE},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_ATT_MACHINE, AppObjInfo.OBJ_MAC_DOWNLOAD_TRANSACT},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_ATT_MACHINE, AppObjInfo.OBJ_MAC_RESET}
      		},
            //presence
      		{
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_PRESENCE, AppObjInfo.OBJ_MANUAL_PRESENCE}
      		},
            //salary
	      	{
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_SALARY, AppObjInfo.OBJ_EMP_SALARY}
      		},
            //appraisal
      		{
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_APPRAISAL, AppObjInfo.OBJ_PERF_APPRAISAL},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_APPRAISAL, AppObjInfo.OBJ_PERF_EVALUATION},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_APPRAISAL, AppObjInfo.OBJ_IMPROV_APPRAISAL},
                {AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_APPRAISAL, AppObjInfo.OBJ_IMPROV_PLAN}
      	 	},
      		// explanation
      		{
        		{AppObjInfo.G1_EMPLOYEE , AppObjInfo.G2_EXPLANATION, AppObjInfo.OBJ_EXP_COVERAGE}
      	 	}
      }  */

    };

    public static final int [][][][] privModuleCommands = AppObjInfo.objectCommands;
	public static final int GROUP_SYSTEM_USER			 	= 0;
	public static final int GROUP_SYSTEM_ADMINISTRATOR	 	= 1;
	public static final int GROUP_MASTER_DATA	 			= 2;
	public static final int GROUP_CLINIC					= 3;
	public static final int GROUP_LOCKER					= 4;
	public static final int GROUP_EMPLOYEE					= 5;

    public static final String []groupNames=
    { "System User", "Administrator", "Master Data",
      "Clinic", "Locker", "Employee"
    };

    public static final String []groupDescs =
    { "System User", "Administrator", "Administer Master Data", 
      "Administer Clinic", "Administer Locker", "Administer Employee"
    };

    public static final int[][] groupPrivGroups=
    {
        // GROUP_SYSTEM_USER
    	{ PRIV_GROUP_SYSTEM_ACCESS },
        //GROUP_SYSTEM_ADMINISTRATOR
    	{ PRIV_GROUP_ADMIN },
        //GROUP_ADMIN_MASTER_DATA
    	{ PRIV_GROUP_MASTER_DATA },
        //GROUP_CLINIC
    	{ PRIV_GROUP_CLINIC },
    	//GROUP_LOCKER
    	{ PRIV_GROUP_LOCKER },
    	//GROUP_EMPLOYEE
    	{ PRIV_GROUP_EMPLOYEE },

    };

    public static final int groupPrivs[][][]=
    {
        //GROUP_SYSTEM_USER
    	{   //PRIV_GROUP_SYSTEM_ACCESS
            {PRIV_SYSTEM_ACCESS_LOGIN , PRIV_SYSTEM_ACCESS_LOGOUT}
    	},
        //GROUP_SYSTEM_ADMINISTRATOR
    	{
            //PRIV_GROUP_ADMIN}
	    	{PRIV_ADMIN_USER, PRIV_ADMIN_SYSTEM}
    	},
        //GROUP_ADMIN_MASTER_DATA
    	{
            //PRIV_GROUP_MASTER_DATA
    		{PRIV_MASTER_DATA_COMPANY, PRIV_MASTER_DATA_MANAGEMENT,
             PRIV_MASTER_DATA_LOCKER, PRIV_MASTER_DATA_SCHEDULE,
             PRIV_MASTER_DATA_APPRAISAL}
    	},
        //GROUP_CLINIC
    	{
        	//PRIV_GROUP_CLINIC
    		{PRIV_CLINIC_MEDICAL, PRIV_CLINIC_EXPENSE}
    	},
        // GROUP_LOCKER
    	{
        	//PRIV_GROUP_LOCKER
    		{PRIV_LOCKER}
    	},
        // GROUP_EMPLOYEE
    	{
        	//PRIV_GROUP_EMPLOYEE
    		{PRIV_EMP_DATABANK, PRIV_EMP_ATTENDANCE, PRIV_EMP_ATT_MACHINE,
             PRIV_EMP_PRESENCE, PRIV_EMP_SALARY,PRIV_EMP_APPRAISAL,
             PRIV_EMP_EXPLANATION}
    	}
    };

    public static int GENERATE_FAIL = -1;
    public static int GENERATE_OK = 0;

    private static Vector privOIDs = new Vector(); // vector of vector of priv of oids grouped
	private static Vector groupOIDs = new Vector();

    private static int genPrivileges(){
        privOIDs= new Vector();
        int maxPrivGrp = privGroupNames.length;

        for(int privGrp = 0; privGrp < maxPrivGrp; privGrp++){
            int maxPriv = privNames[privGrp].length;
            // get privilege
            Vector vprivOID= new Vector();

            for(int priv = 0; priv < maxPriv; priv++){
            	String privName = privNames[privGrp][priv];
                String privDesc = privDescription[privGrp][priv];

                long privOID = insertPriv(privName, privDesc);

                if(privOID==0)
                    System.out.println(" Priv. insert failed at " + priv+ " name =" + privName);

                vprivOID.add( new Long(privOID));

                int maxPrivMod = privModules[privGrp][priv].length;
                Vector privMdl = new Vector();
	        for(int privMod = 0; privMod < maxPrivMod; privMod++){
                    // get G1, G2 , object index  and compose object code
                    int G1 = privModules[privGrp][priv][privMod][0];
                    int G2 = privModules[privGrp][priv][privMod][1];
                    int objIdx = privModules[privGrp][priv][privMod][2];
                    int objCode =  AppObjInfo.composeObjCode( G1, G2 , objIdx );
                    System.out.println("objCode"+objCode);
                    privMdl.add(new Integer(objCode));
                    // get commands
                    int maxPrivModCmds = privModuleCommands[G1][G2][objIdx].length;
                    System.out.println("maxPrivModCmds"+maxPrivModCmds);

                    Vector objCmds = new Vector();
                    for(int privModCmd=0; privModCmd<maxPrivModCmds; privModCmd++){
                        objCmds.add( new Integer(privModuleCommands[G1][G2][objIdx][privModCmd]));
                    }

                    long objID = insertObjAccess(privOID, objCode, objCmds);
                    if (objID == 0)
                        return GENERATE_FAIL;
                    }
        	}
            privOIDs.add(vprivOID);
            }
            return  GENERATE_OK;
        }
	private static long insertPriv(String privName, String privDesc){
        AppPriv priv =  new  AppPriv();
        priv.setPrivName(privName);
        priv.setDescr(privDesc);
        long privID = PstAppPriv.insert(priv);

        return privID;
    }

    private static long insertObjAccess(long privOID, int objCode, Vector objCmds){
        AppPrivilegeObj privObj = new AppPrivilegeObj();
        privObj.setPrivId(privOID);
        privObj.setCode(objCode);
        privObj.setCommands(objCmds);

        long AppPrivOID = PstAppPrivilegeObj.insert(privObj);
        return AppPrivOID;
    }

    private static int genGroup(){
        groupOIDs = new Vector();
	int maxGroup = groupNames.length;
        for(int group=0; group < maxGroup;group++){
            String groupName = groupNames[group];
            String groupDesc = groupDescs[group];

            long groupOID = insertGroup(groupName, groupDesc);
            if(groupOID==0)
                System.out.println(" Group. insert failed at " + group+ " name ="+ groupName);
            groupOIDs.add( new Long(groupOID));

            int maxGroupPrivGrp = groupPrivGroups[group].length;
            System.out.println("maxGroupPrivGrp"+maxGroupPrivGrp);
            for(int groupPrivGrp=0 ; groupPrivGrp < maxGroupPrivGrp ; groupPrivGrp++ ){
                int  idxPrivGrp =groupPrivGroups[group][groupPrivGrp];
                Vector privGroup = (Vector)privOIDs.get(0);
                long privOID = 0;
                long grpPrivID = 0;
                for(int i =0; i < privGroup.size(); i++){
                    privOID = ((Long) privGroup.get(i)).longValue();
                    grpPrivID = insertGroupPriv(groupOID, privOID);
                    if(grpPrivID == 0)
                        return GENERATE_FAIL;
                    }

                    if(idxPrivGrp != 0){
                        int maxGrpPriv = groupPrivs[group][groupPrivGrp].length;
	                for(int grpPriv = 0;grpPriv < maxGrpPriv ;grpPriv++){
            		int  idxPriv =groupPrivs[group][groupPrivGrp][grpPriv];
		
                        privGroup = (Vector) privOIDs.get(idxPrivGrp);
                        privOID = ((Long) privGroup.get(idxPriv)).longValue();

                        System.out.println("privOID="+privOID);
                        grpPrivID = insertGroupPriv(groupOID, privOID);

                        if(grpPrivID == 0)
                            return GENERATE_FAIL;
                    }
                }
            }
        }
        return  GENERATE_OK;
    }

    private static long insertGroup(String groupName, String groupDesc)
    {
    	AppGroup appGrp = new AppGroup();
        appGrp.setGroupName(groupName);
        appGrp.setGroupName(groupName);
        appGrp.setGroupName(groupDesc);

        long grpOID = PstAppGroup.insert(appGrp);
        return grpOID;
    }

    private static long insertGroupPriv(long groupOID, long privID)
    {
    	GroupPriv grpPriv = new GroupPriv();
        grpPriv.setGroupID(groupOID);
        grpPriv.setPrivID(privID);

        long grpPrivOID = PstGroupPriv.insert(grpPriv);
        return grpPrivOID;
    }


    private static int genAdminUser(){
	AppUser user = new AppUser();

        user.setLoginId("admin");
        user.setPassword("admin");
        user.setFullName("administrator");
        user.setUserStatus(AppUser.STATUS_NEW);
        long  usrGrpID = 0;
        long usrOID = 0;
        try {
            usrOID = PstAppUser.insert(user);
        }
        catch (Exception exc) {
        }

        long sysAdmin = ((Long)groupOIDs.get(GROUP_SYSTEM_USER)).longValue();
        //insert system_user
        usrGrpID = insertUserGroup(usrOID, sysAdmin);

        if(usrOID != 0){
            for (int usr = 1;usr < groupOIDs.size(); usr++) {
                long   adminID = ((Long)groupOIDs.get(usr)).longValue();
                System.out.println("adminID"+adminID);
	        //insert user_group
	         usrGrpID = insertUserGroup(usrOID, adminID);
            }
        }
       return  GENERATE_OK;
    }

    private static long insertUserGroup(long usrID, long adminID)
    {
        UserGroup usrGrp = new UserGroup();
        usrGrp.setUserID(usrID);
        usrGrp.setGroupID(adminID);

        long usrGrpID = PstUserGroup.insert(usrGrp);
        return usrGrpID;
    }

    public static String genSecurity() {
	if(genPrivileges()!= GENERATE_OK)
            return "Privilege generator is failed";
	if(genGroup()!= GENERATE_OK)
            return "Group generator is failed";
	if(genAdminUser()!= GENERATE_OK)
            return "User generator is failed";
        return "Security generator ok";
    }
    
    public static String deleteSecurity(){
        // delete app_user 
        String sql = "DELETE FROM " + PstAppUser.TBL_APP_USER;        
        try {
            DBResultSet dbrs = DBHandler.execQueryResult(sql);
            dbrs.close(dbrs);
        } catch (Exception exc) {
            System.out.println("DELETE USER " + exc);
            return "Failed";
        }
        
        // delete user_group
        sql = "DELETE FROM " + PstUserGroup.TBL_USER_GROUP;        
        try {
            DBResultSet dbrs = DBHandler.execQueryResult(sql);
            dbrs.close(dbrs);
        } catch (Exception exc) {
            System.out.println("DELETE USER GROUP " + exc);
            return "Failed";
        }
        
        // delete group
        sql = "DELETE FROM " + PstAppGroup.TBL_APP_GROUP;        
        try{
            DBResultSet dbrs = DBHandler.execQueryResult(sql);
            dbrs.close(dbrs);
        } catch (Exception exc){
            System.out.println("DELETE APP GROUP "+ exc);
            return "Failed";
        }
        
        // delete group_priv
        sql = "DELETE FROM " + PstGroupPriv.TBL_GROUP_PRIV;        
        try{
            DBResultSet dbrs = DBHandler.execQueryResult(sql);
            dbrs.close(dbrs);
        } catch (Exception exc) {
            System.out.println("DELETE GROUP PRIV" + exc);
            return "Failed";
        }
        
        // delete app_priv
        sql = "DELETE FROM " + PstAppPriv.TBL_APP_PRIVILEGE;        
        try{
            DBResultSet dbrs = DBHandler.execQueryResult(sql);
            dbrs.close(dbrs);
        } catch (Exception exc) {
            System.out.println("DELETE APP PRIV" + exc);
            return "Failed";
        }
        
        // delete app_privobj
        sql = "DELETE FROM " + PstAppPrivilegeObj.TBL_APP_PRIVILEGE_OBJ;        
        try {
            DBResultSet dbrs = DBHandler.execQueryResult(sql);
            dbrs.close(dbrs);
        } catch (Exception exc){
            System.out.println("DELETE APP PRIV OBJ" + exc);
            return "Failed";
        }
        
        return "OK"; 
    }



    public static void main(String [] args)
    {
     	String result = genSecurity();
        System.out.println("result" + result);
    }


}
