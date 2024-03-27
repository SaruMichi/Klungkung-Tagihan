/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataTakeover;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataTakeover extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataTakeover entContentDataTakeover;
    public static final String FRM_NAME_CONTENT_DATA_TAKEOVER = "FRM_NAME_CONTENT_DATA_TAKEOVER";
    public static final int FRM_FIELD_TAKEOVER_OID = 0;
    public static final int FRM_FIELD_NAMA_TAKEOVER = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_TAKEOVER_OID",
        "FRM_FIELD_NAMA_TAKEOVER",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataTakeover() {
    }

    public FrmContentDataTakeover(ContentDataTakeover entContentDataTakeover) {
        this.entContentDataTakeover = entContentDataTakeover;
    }

    public FrmContentDataTakeover(HttpServletRequest request, ContentDataTakeover entContentDataTakeover) {
        super(new FrmContentDataTakeover(entContentDataTakeover), request);
        this.entContentDataTakeover = entContentDataTakeover;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_TAKEOVER;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int getFieldSize() {
        return fieldNames.length;
    }

    public ContentDataTakeover getEntityObject() {
        return entContentDataTakeover;
    }

    public void requestEntityObject(ContentDataTakeover entContentDataTakeover) {
        try {
            this.requestParam();
//            entContentDataTakeover.setTakeoverOid(getLong(FRM_FIELD_TAKEOVER_OID));
            entContentDataTakeover.setNamaTakeover(getString(FRM_FIELD_NAMA_TAKEOVER));
            entContentDataTakeover.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataTakeover.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
