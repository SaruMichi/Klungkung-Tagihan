/*

 * $Id: PdfAnnotation.java,v 1.1.1.1 2007/12/22 02:47:03 root Exp $

 * $Name:  $

 *

 * Copyright 1999, 2000, 2001, 2002 Bruno Lowagie

 *

 * The contents of this file are subject to the Mozilla Public License Version 1.1

 * (the "License"); you may not use this file except in compliance with the License.

 * You may obtain a copy of the License at http://www.mozilla.org/MPL/

 *

 * Software distributed under the License is distributed on an "AS IS" basis,

 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License

 * for the specific language governing rights and limitations under the License.

 *

 * The Original Code is 'iText, a free JAVA-PDF library'.

 *

 * The Initial Developer of the Original Code is Bruno Lowagie. Portions created by

 * the Initial Developer are Copyright (C) 1999, 2000, 2001, 2002 by Bruno Lowagie.

 * All Rights Reserved.

 * Co-Developer of the code is Paulo Soares. Portions created by the Co-Developer

 * are Copyright (C) 2000, 2001, 2002 by Paulo Soares. All Rights Reserved.

 *

 * Contributor(s): all the names of the contributors are added in the source code

 * where applicable.

 *

 * Alternatively, the contents of this file may be used under the terms of the

 * LGPL license (the "GNU LIBRARY GENERAL PUBLIC LICENSE"), in which case the

 * provisions of LGPL are applicable instead of those above.  If you wish to

 * allow use of your version of this file only under the terms of the LGPL

 * License and not to allow others to use your version of this file under

 * the MPL, indicate your decision by deleting the provisions above and

 * replace them with the notice and other provisions required by the LGPL.

 * If you do not delete the provisions above, a recipient may use your version

 * of this file under either the MPL or the GNU LIBRARY GENERAL PUBLIC LICENSE.

 *

 * This library is free software; you can redistribute it and/or modify it

 * under the terms of the MPL as stated above or under the terms of the GNU

 * Library General Public License as published by the Free Software Foundation;

 * either version 2 of the License, or any later version.

 *

 * This library is distributed in the hope that it will be useful, but WITHOUT

 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS

 * FOR A PARTICULAR PURPOSE. See the GNU Library general Public License for more

 * details.

 *

 * If you didn't download this code from the following link, you should check if

 * you aren't using an obsolete version:

 * http://www.lowagie.com/iText/

 */



package com.lowagie.text.pdf;



import com.lowagie.text.Rectangle;

import java.util.HashMap;

import java.awt.Color;

/**

 * A <CODE>PdfAnnotation</CODE> is a note that is associated with a page.

 *

 * @see		PdfDictionary

 */



public class PdfAnnotation extends PdfDictionary {

    

    public static final PdfName HIGHLIGHT_NONE = PdfName.N;

    public static final PdfName HIGHLIGHT_INVERT = PdfName.I;

    public static final PdfName HIGHLIGHT_OUTLINE = PdfName.O;

    public static final PdfName HIGHLIGHT_PUSH = PdfName.P;

    public static final PdfName HIGHLIGHT_TOGGLE = PdfName.T;

    public static final int FLAGS_INVISIBLE = 1;

    public static final int FLAGS_HIDDEN = 2;

    public static final int FLAGS_PRINT = 4;

    public static final int FLAGS_NOZOOM = 8;

    public static final int FLAGS_NOROTATE = 16;

    public static final int FLAGS_NOVIEW = 32;

    public static final int FLAGS_READONLY = 64;

    public static final PdfName APPEARANCE_NORMAL = PdfName.N;

    public static final PdfName APPEARANCE_ROLLOVER = PdfName.R;

    public static final PdfName APPEARANCE_DOWN = PdfName.D;

    public static final PdfName AA_ENTER = PdfName.E;

    public static final PdfName AA_EXIT = PdfName.X;

    public static final PdfName AA_DOWN = PdfName.D;

    public static final PdfName AA_UP = PdfName.U;

    public static final PdfName AA_FOCUS = PdfName.FO;

    public static final PdfName AA_BLUR = PdfName.BL;

    public static final PdfName AA_JS_KEY = PdfName.K;

    public static final PdfName AA_JS_FORMAT = PdfName.F;

    public static final PdfName AA_JS_CHANGE = PdfName.V;

    public static final PdfName AA_JS_OTHER_CHANGE = PdfName.C;

    public static final int MARKUP_HIGHLIGHT = 0;

    public static final int MARKUP_UNDERLINE = 1;

    public static final int MARKUP_STRIKEOUT = 2;

    protected PdfWriter writer;

    protected PdfIndirectReference reference;

    protected HashMap templates;

    protected boolean form = false;

    protected boolean annotation = true;

    

    /** Holds value of property used. */

    protected boolean used = false;

    

    /** Holds value of property placeInPage. */

    private int placeInPage = -1;

    

    // constructors

    protected PdfAnnotation(PdfWriter writer, Rectangle rect) {

        this.writer = writer;

        if (rect != null)

            put(PdfName.RECT, new PdfRectangle(rect));

    }

    

/**

 * Constructs a new <CODE>PdfAnnotation</CODE> of subtype text.

 */

    

    PdfAnnotation(PdfWriter writer, float llx, float lly, float urx, float ury, PdfString title, PdfString content) {

        this.writer = writer;

        put(PdfName.SUBTYPE, PdfName.TEXT);

        put(PdfName.T, title);

        put(PdfName.RECT, new PdfRectangle(llx, lly, urx, ury));

        put(PdfName.CONTENTS, content);

    }

    

/**

 * Constructs a new <CODE>PdfAnnotation</CODE> of subtype link (Action).

 */

    

    public PdfAnnotation(PdfWriter writer, float llx, float lly, float urx, float ury, PdfAction action) {

        this.writer = writer;

        put(PdfName.SUBTYPE, PdfName.LINK);

        put(PdfName.RECT, new PdfRectangle(llx, lly, urx, ury));

        put(PdfName.A, action);

        put(PdfName.BORDER, new PdfBorderArray(0, 0, 0));

        put(PdfName.C, new PdfColor(0x00, 0x00, 0xFF));

    }

    

    PdfIndirectReference getIndirectReference() {

        if (reference == null) {

            reference = writer.getPdfIndirectReference();

        }

        return reference;

    }

    

    public static PdfAnnotation createText(PdfWriter writer, Rectangle rect, String title, String contents, boolean open, String icon) {

        PdfAnnotation annot = new PdfAnnotation(writer, rect);

        annot.put(PdfName.SUBTYPE, PdfName.TEXT);

        if (title != null)

            annot.put(PdfName.T, new PdfString(title, PdfObject.TEXT_UNICODE));

        if (contents != null)

            annot.put(PdfName.CONTENTS, new PdfString(contents, PdfObject.TEXT_UNICODE));

        if (open)

            annot.put(PdfName.OPEN, PdfBoolean.PDFTRUE);

        if (icon != null) {

            annot.put(PdfName.NAME, new PdfName(icon));

        }

        return annot;

    }

    

    protected static PdfAnnotation createLink(PdfWriter writer, Rectangle rect, PdfName highlight) {

        PdfAnnotation annot = new PdfAnnotation(writer, rect);

        annot.put(PdfName.SUBTYPE, PdfName.LINK);

        if (!highlight.equals(HIGHLIGHT_INVERT))

            annot.put(PdfName.H, highlight);

        return annot;

    }

    

    public static PdfAnnotation createLink(PdfWriter writer, Rectangle rect, PdfName highlight, PdfAction action) {

        PdfAnnotation annot = createLink(writer, rect, highlight);

        annot.putEx(PdfName.A, action);

        return annot;

    }



    public static PdfAnnotation createLink(PdfWriter writer, Rectangle rect, PdfName highlight, String namedDestination) {

        PdfAnnotation annot = createLink(writer, rect, highlight);

        annot.put(PdfName.DEST, new PdfString(namedDestination));

        return annot;

    }



    public static PdfAnnotation createLink(PdfWriter writer, Rectangle rect, PdfName highlight, int page, PdfDestination dest) {

        PdfAnnotation annot = createLink(writer, rect, highlight);

        PdfIndirectReference ref = writer.getPageReference(page);

        dest.addPage(ref);

        annot.put(PdfName.DEST, dest);

        return annot;

    }

    

    public static PdfAnnotation createFreeText(PdfWriter writer, Rectangle rect, String contents, PdfContentByte defaultAppearance) {

        PdfAnnotation annot = new PdfAnnotation(writer, rect);

        annot.put(PdfName.SUBTYPE, PdfName.FREETEXT);

        annot.put(PdfName.CONTENTS, new PdfString(contents, PdfObject.TEXT_UNICODE));

        annot.setDefaultAppearanceString(defaultAppearance);

        return annot;

    }



    public static PdfAnnotation createLine(PdfWriter writer, Rectangle rect, String contents, float x1, float y1, float x2, float y2) {

        PdfAnnotation annot = new PdfAnnotation(writer, rect);

        annot.put(PdfName.SUBTYPE, PdfName.LINE);

        annot.put(PdfName.CONTENTS, new PdfString(contents, PdfObject.TEXT_UNICODE));

        PdfArray array = new PdfArray(new PdfNumber(x1));

        array.add(new PdfNumber(y1));

        array.add(new PdfNumber(x2));

        array.add(new PdfNumber(y2));

        annot.put(PdfName.L, array);

        return annot;

    }



    public static PdfAnnotation createSquareCirlcle(PdfWriter writer, Rectangle rect, String contents, boolean square) {

        PdfAnnotation annot = new PdfAnnotation(writer, rect);

        if (square)

            annot.put(PdfName.SUBTYPE, PdfName.SQUARE);

        else

            annot.put(PdfName.SUBTYPE, PdfName.CIRCLE);

        annot.put(PdfName.CONTENTS, new PdfString(contents, PdfObject.TEXT_UNICODE));

        return annot;

    }



    public static PdfAnnotation createMarkup(PdfWriter writer, Rectangle rect, String contents, int type, float quadPoints[]) {

        PdfAnnotation annot = new PdfAnnotation(writer, rect);

        PdfName name = PdfName.HIGHLIGHT;

        switch (type) {

            case MARKUP_UNDERLINE:

                name = PdfName.UNDERLINE;

                break;

            case MARKUP_STRIKEOUT:

                name = PdfName.STRIKEOUT;

                break;

        }

        annot.put(PdfName.SUBTYPE, name);

        annot.put(PdfName.CONTENTS, new PdfString(contents, PdfObject.TEXT_UNICODE));

        PdfArray array = new PdfArray();

        for (int k = 0; k < quadPoints.length; ++k)

            array.add(new PdfNumber(quadPoints[k]));

        annot.put(PdfName.QUADPOINTS, array);

        return annot;

    }



    public static PdfAnnotation createStamp(PdfWriter writer, Rectangle rect, String contents, String name) {

        PdfAnnotation annot = new PdfAnnotation(writer, rect);

        annot.put(PdfName.SUBTYPE, PdfName.STAMP);

        annot.put(PdfName.CONTENTS, new PdfString(contents, PdfObject.TEXT_UNICODE));

        annot.put(PdfName.NAME, new PdfName(name));

        return annot;

    }



    public static PdfAnnotation createInk(PdfWriter writer, Rectangle rect, String contents, float inkList[][]) {

        PdfAnnotation annot = new PdfAnnotation(writer, rect);

        annot.put(PdfName.SUBTYPE, PdfName.STAMP);

        annot.put(PdfName.CONTENTS, new PdfString(contents, PdfObject.TEXT_UNICODE));

        PdfArray outer = new PdfArray();

        for (int k = 0; k < inkList.length; ++k) {

            PdfArray inner = new PdfArray();

            float deep[] = inkList[k];

            for (int j = 0; j < deep.length; ++j)

                inner.add(new PdfNumber(deep[j]));

            outer.add(inner);

        }

        annot.put(PdfName.INKLIST, outer);

        return annot;

    }



    public static PdfAnnotation createPopup(PdfWriter writer, Rectangle rect, String contents, boolean open) {

        PdfAnnotation annot = new PdfAnnotation(writer, rect);

        annot.put(PdfName.SUBTYPE, PdfName.POPUP);

        annot.put(PdfName.CONTENTS, new PdfString(contents, PdfObject.TEXT_UNICODE));

        if (open)

            annot.put(PdfName.OPEN, PdfBoolean.PDFTRUE);

        return annot;

    }



    public void setDefaultAppearanceString(PdfContentByte cb) {

        byte b[] = cb.getInternalBuffer().toByteArray();

        int len = b.length;

        for (int k = 0; k < len; ++k) {

            if (b[k] == '\n')

                b[k] = 32;

        }

        put(PdfName.DA, new PdfString(b));

    }

    

    public void setFlags(int flags) {

        if (flags == 0)

            remove(PdfName.F);

        else

            put(PdfName.F, new PdfNumber(flags));

    }

    

    public void setBorder(PdfBorderArray border) {

        putDel(PdfName.BORDER, border);

    }



    public void setBorderStyle(PdfBorderDictionary border) {

        putDel(PdfName.BS, border);

    }

    

    public void setAppearance(PdfName ap, PdfAppearance template) {

        PdfDictionary dic = (PdfDictionary)get(PdfName.AP);

        if (dic == null)

            dic = new PdfDictionary();

        dic.put(ap, template.getIndirectReference());

        put(PdfName.AP, dic);

        if (!form)

            return;

        if (templates == null)

            templates = new HashMap();

        templates.put(template, null);

    }



    public void setAppearance(PdfName ap, String state, PdfAppearance template) {

        PdfDictionary dicAp = (PdfDictionary)get(PdfName.AP);

        if (dicAp == null)

            dicAp = new PdfDictionary();



        PdfDictionary dic;

        PdfObject obj = dicAp.get(ap);

        if (obj != null && obj.type() == DICTIONARY)

            dic = (PdfDictionary)obj;

        else

            dic = new PdfDictionary();

        dic.put(new PdfName(state), template.getIndirectReference());

        dicAp.put(ap, dic);

        put(PdfName.AP, dicAp);

        if (!form)

            return;

        if (templates == null)

            templates = new HashMap();

        templates.put(template, null);

    }

    

    public void setAppearanceState(String state) {

        if (state == null) {

            remove(PdfName.AS);

            return;

        }

        put(PdfName.AS, new PdfName(state));

    }

    

    public void setColor(Color color) {

        putDel(PdfName.C, new PdfColor(color));

    }

    

    public void setTitle(String title) {

        if (title == null) {

            remove(PdfName.T);

            return;

        }

        put(PdfName.T, new PdfString(title, PdfObject.TEXT_UNICODE));

    }

    

    public void setPopup(PdfAnnotation popup) {

        put(PdfName.POPUP, popup.getIndirectReference());

        popup.put(PdfName.PARENT, getIndirectReference());

    }

    

    public void setAction(PdfAction action) {

        putDel(PdfName.A, action);

    }

    

    public void setAdditionalActions(PdfName key, PdfAction action) {

        PdfDictionary dic;

        PdfObject obj = get(PdfName.AA);

        if (obj != null && obj.type() == DICTIONARY)

            dic = (PdfDictionary)obj;

        else

            dic = new PdfDictionary();

        dic.put(key, action);

        put(PdfName.AA, dic);

    }

    

    /** Getter for property used.

     * @return Value of property used.

     */

    public boolean isUsed() {

        return used;

    }

    

    /** Setter for property used.

     */

    void setUsed() {

        used = true;

    }

    

    HashMap getTemplates() {

        return templates;

    }

    

    /** Getter for property form.

     * @return Value of property form.

     */

    public boolean isForm() {

        return form;

    }

    

    /** Getter for property annotation.

     * @return Value of property annotation.

     */

    public boolean isAnnotation() {

        return annotation;

    }

    

    public void setPage(int page) {

        put(PdfName.P, writer.getPageReference(page));

    }

    

    public void setPage() {

        put(PdfName.P, writer.getCurrentPage());

    }

    

    /** Getter for property placeInPage.

     * @return Value of property placeInPage.

     */

    public int getPlaceInPage() {

        return placeInPage;

    }

    

    /** Places the annotation in a specified page that must be greater

     * or equal to the current one. The first page is 1.

     * @param placeInPage New value of property placeInPage.

     */

    public void setPlaceInPage(int placeInPage) {

        this.placeInPage = placeInPage;

    }

    

}