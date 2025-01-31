/*

 * $Id: PdfAction.java,v 1.1.1.1 2007/12/22 02:47:03 root Exp $

 * $Name:  $

 *

 * Copyright 2001, 2002 by Bruno Lowagie.

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



import java.net.URL;

import com.lowagie.text.ExceptionConverter;



/**

 * A <CODE>PdfAction</CODE> defines an action that can be triggered from a PDF file.

 *

 * @see		PdfDictionary

 */



public class PdfAction extends PdfDictionary {

    

    /** A named action to go to the first page.

     */

    public static final int FIRSTPAGE = 1;

    /** A named action to go to the previous page.

     */

    public static final int PREVPAGE = 2;

    /** A named action to go to the next page.

     */

    public static final int NEXTPAGE = 3;

    /** A named action to go to the last page.

     */

    public static final int LASTPAGE = 4;



    /** A named action to open a print dialog.

     */

    public static final int PRINTDIALOG = 5;



    // constructors

    public static final int SUBMIT_EXCLUDE = 1;

    public static final int SUBMIT_INCLUDE_NO_VALUE_FIELDS = 2;

    public static final int SUBMIT_HTML_FORMAT = 4;

    public static final int SUBMIT_HTML_GET = 8;

    public static final int SUBMIT_COORDINATES = 16;

    public static final int RESET_EXCLUDE = 1;

    

    /** Create an empty action.

     */    

    PdfAction() {

    }

    

    /**

     * Constructs a new <CODE>PdfAction</CODE> of Subtype URI.

     *

     * @param url the Url to go to

     */

    

    public PdfAction(URL url) {

        this(url.toExternalForm());

    }

    

    public PdfAction(URL url, boolean isMap) {

        this(url.toExternalForm(), isMap);

    }

    

    /**

     * Constructs a new <CODE>PdfAction</CODE> of Subtype URI.

     *

     * @param url the url to go to

     */

    

    public PdfAction(String url) {

        this(url, false);

    }

    

    public PdfAction(String url, boolean isMap) {

        put(PdfName.S, PdfName.URI);

        put(PdfName.URI, new PdfString(url));

        if (isMap)

            put(PdfName.ISMAP, PdfBoolean.PDFTRUE);

    }

    

    /**

     * Constructs a new <CODE>PdfAction</CODE> of Subtype GoTo.

     * @param destination the destination to go to

     */

    

    PdfAction(PdfIndirectReference destination) {

        put(PdfName.S, PdfName.GOTO);

        put(PdfName.D, destination);

    }

    

    /**

     * Constructs a new <CODE>PdfAction</CODE> of Subtype GoToR.

     * @param filename the file name to go to

     * @param name the named destination to go to

     */

    

    public PdfAction(String filename, String name) {

        put(PdfName.S, PdfName.GOTOR);

        put(PdfName.F, new PdfString(filename));

        put(PdfName.D, new PdfString(name));

    }

    

    /**

     * Constructs a new <CODE>PdfAction</CODE> of Subtype GoToR.

     * @param filename the file name to go to

     * @param page the page destination to go to

     */

    

    public PdfAction(String filename, int page) {

        put(PdfName.S, PdfName.GOTOR);

        put(PdfName.F, new PdfString(filename));

        put(PdfName.D, new PdfLiteral("[" + (page - 1) + " /FitH 10000]"));

    }

    

    /** Implements name actions. The action can be FIRSTPAGE, LASTPAGE,

     * NEXTPAGE and PREVPAGE.

     * @param named the named action

     */

    public PdfAction(int named) {

        put(PdfName.S, PdfName.NAMED);

        switch (named) {

            case FIRSTPAGE:

                put(PdfName.N, PdfName.FIRSTPAGE);

                break;

            case LASTPAGE:

                put(PdfName.N, PdfName.LASTPAGE);

                break;

            case NEXTPAGE:

                put(PdfName.N, PdfName.NEXTPAGE);

                break;

            case PREVPAGE:

                put(PdfName.N, PdfName.PREVPAGE);

                break;

            case PRINTDIALOG:

                put(PdfName.S, PdfName.JAVASCRIPT);

                put(PdfName.JS, new PdfString("this.print(true);\r"));

            default:

                throw new RuntimeException("Invalid named action.");

        }

    }

    

    /** Launchs an application or a document.

     * @param application the application to be launched or the document to be opened or printed.

     * @param parameters (Windows-specific) A parameter string to be passed to the application.

     * It can be <CODE>null</CODE>.

     * @param operation (Windows-specific) the operation to perform: "open" - Open a document,

     * "print" - Print a document.

     * It can be <CODE>null</CODE>.

     * @param defaultDir (Windows-specific) the default directory in standard DOS syntax.

     * It can be <CODE>null</CODE>.

     */

    public PdfAction(String application, String parameters, String operation, String defaultDir) {

        put(PdfName.S, PdfName.LAUNCH);

        if (parameters == null && operation == null && defaultDir == null)

            put(PdfName.F, new PdfString(application));

        else {

            PdfDictionary dic = new PdfDictionary();

            dic.put(PdfName.F, new PdfString(application));

            if (parameters != null)

                dic.put(PdfName.P, new PdfString(parameters));

            if (operation != null)

                dic.put(PdfName.O, new PdfString(operation));

            if (defaultDir != null)

                dic.put(PdfName.D, new PdfString(defaultDir));

            put(PdfName.WIN, dic);

        }

    }

    

    /** Creates a JavaScript action. If the JavaScript is smaller than

     * 50 characters it will be placed as a string, otherwise it will

     * be placed as a compressed stream.

     * @param code the JavaScript code

     * @param writer the writer for this action

     * @param unicode select JavaScript unicode. Note that the internal

     * Acrobat JavaScript engine does not support unicode,

     * so this may or may not work for you

     * @return the JavaScript action

     */    

    public static PdfAction javaScript(String code, PdfWriter writer, boolean unicode) {

        PdfAction js = new PdfAction();

        js.put(PdfName.S, PdfName.JAVASCRIPT);

        if (unicode && code.length() < 50) {

                js.put(PdfName.JS, new PdfString(code, PdfObject.TEXT_UNICODE));

        }

        else if (!unicode && code.length() < 100) {

                js.put(PdfName.JS, new PdfString(code));

        }

        else {

            try {

                byte b[] = PdfEncodings.convertToBytes(code, unicode ? PdfObject.TEXT_UNICODE : PdfObject.ENCODING);

                PdfStream stream = new PdfStream(b);

                stream.flateCompress();

                js.put(PdfName.JS, writer.addToBody(stream).getIndirectReference());

            }

            catch (Exception e) {

                throw new ExceptionConverter(e);

            }

        }

        return js;

    }



    /** Creates a JavaScript action. If the JavaScript is smaller than

     * 50 characters it will be place as a string, otherwise it will

     * be placed as a compressed stream.

     * @param code the JavaScript code

     * @param writer the writer for this action

     * @return the JavaScript action

     */    

    public static PdfAction javaScript(String code, PdfWriter writer) {

        return javaScript(code, writer, false);

    }

    

    static PdfAction createHide(PdfObject obj, boolean hide) {

        PdfAction action = new PdfAction();

        action.put(PdfName.S, PdfName.HIDE);

        action.put(PdfName.T, obj);

        if (!hide)

            action.put(PdfName.H, PdfBoolean.PDFFALSE);

        return action;

    }

    

    public static PdfAction createHide(PdfAnnotation annot, boolean hide) {

        return createHide(annot.getIndirectReference(), hide);

    }

    

    public static PdfAction createHide(String name, boolean hide) {

        return createHide(new PdfString(name), hide);

    }

    

    static PdfArray buildArray(Object names[]) {

        PdfArray array = new PdfArray();

        for (int k = 0; k < names.length; ++k) {

            Object obj = names[k];

            if (obj instanceof String)

                array.add(new PdfString((String)obj));

            else if (obj instanceof PdfAnnotation)

                array.add(((PdfAnnotation)obj).getIndirectReference());

            else

                throw new RuntimeException("The array must contain String or PdfAnnotation.");

        }

        return array;

    }

    

    public static PdfAction createHide(Object names[], boolean hide) {

        return createHide(buildArray(names), hide);

    }

    

    public static PdfAction createSubmitForm(String file, Object names[], int flags) {

        PdfAction action = new PdfAction();

        action.put(PdfName.S, PdfName.SUBMITFORM);

        PdfDictionary dic = new PdfDictionary();

        dic.put(PdfName.F, new PdfString(file));

        dic.put(PdfName.FS, PdfName.URL);

        action.put(PdfName.F, dic);

        if (names != null)

            action.put(PdfName.FIELDS, buildArray(names));

        action.put(PdfName.FLAGS, new PdfNumber(flags));

        return action;

    }

    

    public static PdfAction createResetForm(Object names[], int flags) {

        PdfAction action = new PdfAction();

        action.put(PdfName.S, PdfName.RESETFORM);

        if (names != null)

            action.put(PdfName.FIELDS, buildArray(names));

        action.put(PdfName.FLAGS, new PdfNumber(flags));

        return action;

    }

    

    public static PdfAction createImportData(String file) {

        PdfAction action = new PdfAction();

        action.put(PdfName.S, PdfName.IMPORTDATA);

        action.put(PdfName.F, new PdfString(file));

        return action;

    }

    

    /** Add a chained action.

     * @param na the next action

     */    

    public void next(PdfAction na) {

        PdfObject nextAction = get(PdfName.NEXT);

        if (nextAction == null)

            put(PdfName.NEXT, na);

        else if (nextAction.type() == PdfObject.DICTIONARY) {

            PdfArray array = new PdfArray(nextAction);

            array.add(na);

            put(PdfName.NEXT, array);

        }

        else {

            ((PdfArray)nextAction).add(na);

        }

    }

    

    /** Creates a GoTo action to an internal page.

     * @param page the page to go. First page is 1

     * @param dest the destination for the page

     * @param writer the writer for this action

     * @return a GoTo action

     */    

    public static PdfAction gotoLocalPage(int page, PdfDestination dest, PdfWriter writer) {

        PdfIndirectReference ref = writer.getPageReference(page);

        dest.addPage(ref);

        PdfAction action = new PdfAction();

        action.put(PdfName.S, PdfName.GOTO);

        action.put(PdfName.D, dest);

        return action;

    }

}

