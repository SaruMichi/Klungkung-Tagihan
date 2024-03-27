/*

 * $Id: GlyphList.java,v 1.1.1.1 2007/12/22 02:47:03 root Exp $

 * $Name:  $

 *

 * Copyright 2001, 2002 Paulo Soares

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



import java.util.HashMap;



public class GlyphList

{

    private static HashMap unicode2names = new HashMap();

    private static HashMap names2unicode = new HashMap();

    

    private static final int unicode[] =

    {

        0x0041,

        0x00C6,

        0x01FC,

        0xF7E6,

        0x00C1,

        0xF7E1,

        0x0102,

        0x00C2,

        0xF7E2,

        0xF6C9,

        0xF7B4,

        0x00C4,

        0xF7E4,

        0x00C0,

        0xF7E0,

        0x0391,

        0x0386,

        0x0100,

        0x0104,

        0x00C5,

        0x01FA,

        0xF7E5,

        0xF761,

        0x00C3,

        0xF7E3,

        0x0042,

        0x0392,

        0xF6F4,

        0xF762,

        0x0043,

        0x0106,

        0xF6CA,

        0xF6F5,

        0x010C,

        0x00C7,

        0xF7E7,

        0x0108,

        0x010A,

        0xF7B8,

        0x03A7,

        0xF6F6,

        0xF763,

        0x0044,

        0x010E,

        0x0110,

        0x2206,

        0x0394,

        0xF6CB,

        0xF6CC,

        0xF6CD,

        0xF7A8,

        0xF6F7,

        0xF764,

        0x0045,

        0x00C9,

        0xF7E9,

        0x0114,

        0x011A,

        0x00CA,

        0xF7EA,

        0x00CB,

        0xF7EB,

        0x0116,

        0x00C8,

        0xF7E8,

        0x0112,

        0x014A,

        0x0118,

        0x0395,

        0x0388,

        0xF765,

        0x0397,

        0x0389,

        0x00D0,

        0xF7F0,

        0x20AC,

        0x0046,

        0xF766,

        0x0047,

        0x0393,

        0x011E,

        0x01E6,

        0x011C,

        0x0122,

        0x0120,

        0xF6CE,

        0xF760,

        0xF767,

        0x0048,

        0x25CF,

        0x25AA,

        0x25AB,

        0x25A1,

        0x0126,

        0x0124,

        0xF768,

        0xF6CF,

        0xF6F8,

        0x0049,

        0x0132,

        0x00CD,

        0xF7ED,

        0x012C,

        0x00CE,

        0xF7EE,

        0x00CF,

        0xF7EF,

        0x0130,

        0x2111,

        0x00CC,

        0xF7EC,

        0x012A,

        0x012E,

        0x0399,

        0x03AA,

        0x038A,

        0xF769,

        0x0128,

        0x004A,

        0x0134,

        0xF76A,

        0x004B,

        0x039A,

        0x0136,

        0xF76B,

        0x004C,

        0xF6BF,

        0x0139,

        0x039B,

        0x013D,

        0x013B,

        0x013F,

        0x0141,

        0xF6F9,

        0xF76C,

        0x004D,

        0xF6D0,

        0xF7AF,

        0xF76D,

        0x039C,

        0x004E,

        0x0143,

        0x0147,

        0x0145,

        0xF76E,

        0x00D1,

        0xF7F1,

        0x039D,

        0x004F,

        0x0152,

        0xF6FA,

        0x00D3,

        0xF7F3,

        0x014E,

        0x00D4,

        0xF7F4,

        0x00D6,

        0xF7F6,

        0xF6FB,

        0x00D2,

        0xF7F2,

        0x01A0,

        0x0150,

        0x014C,

        0x2126,

        0x03A9,

        0x038F,

        0x039F,

        0x038C,

        0x00D8,

        0x01FE,

        0xF7F8,

        0xF76F,

        0x00D5,

        0xF7F5,

        0x0050,

        0x03A6,

        0x03A0,

        0x03A8,

        0xF770,

        0x0051,

        0xF771,

        0x0052,

        0x0154,

        0x0158,

        0x0156,

        0x211C,

        0x03A1,

        0xF6FC,

        0xF772,

        0x0053,

        0x250C,

        0x2514,

        0x2510,

        0x2518,

        0x253C,

        0x252C,

        0x2534,

        0x251C,

        0x2524,

        0x2500,

        0x2502,

        0x2561,

        0x2562,

        0x2556,

        0x2555,

        0x2563,

        0x2551,

        0x2557,

        0x255D,

        0x255C,

        0x255B,

        0x255E,

        0x255F,

        0x255A,

        0x2554,

        0x2569,

        0x2566,

        0x2560,

        0x2550,

        0x256C,

        0x2567,

        0x2568,

        0x2564,

        0x2565,

        0x2559,

        0x2558,

        0x2552,

        0x2553,

        0x256B,

        0x256A,

        0x015A,

        0x0160,

        0xF6FD,

        0x015E,

        0xF6C1,

        0x015C,

        0x0218,

        0x03A3,

        0xF773,

        0x0054,

        0x03A4,

        0x0166,

        0x0164,

        0x0162,

        0x021A,

        0x0398,

        0x00DE,

        0xF7FE,

        0xF6FE,

        0xF774,

        0x0055,

        0x00DA,

        0xF7FA,

        0x016C,

        0x00DB,

        0xF7FB,

        0x00DC,

        0xF7FC,

        0x00D9,

        0xF7F9,

        0x01AF,

        0x0170,

        0x016A,

        0x0172,

        0x03A5,

        0x03D2,

        0x03AB,

        0x038E,

        0x016E,

        0xF775,

        0x0168,

        0x0056,

        0xF776,

        0x0057,

        0x1E82,

        0x0174,

        0x1E84,

        0x1E80,

        0xF777,

        0x0058,

        0x039E,

        0xF778,

        0x0059,

        0x00DD,

        0xF7FD,

        0x0176,

        0x0178,

        0xF7FF,

        0x1EF2,

        0xF779,

        0x005A,

        0x0179,

        0x017D,

        0xF6FF,

        0x017B,

        0x0396,

        0xF77A,

        0x0061,

        0x00E1,

        0x0103,

        0x00E2,

        0x00B4,

        0x0301,

        0x00E4,

        0x00E6,

        0x01FD,

        0x2015,

        0x0410,

        0x0411,

        0x0412,

        0x0413,

        0x0414,

        0x0415,

        0x0401,

        0x0416,

        0x0417,

        0x0418,

        0x0419,

        0x041A,

        0x041B,

        0x041C,

        0x041D,

        0x041E,

        0x041F,

        0x0420,

        0x0421,

        0x0422,

        0x0423,

        0x0424,

        0x0425,

        0x0426,

        0x0427,

        0x0428,

        0x0429,

        0x042A,

        0x042B,

        0x042C,

        0x042D,

        0x042E,

        0x042F,

        0x0490,

        0x0402,

        0x0403,

        0x0404,

        0x0405,

        0x0406,

        0x0407,

        0x0408,

        0x0409,

        0x040A,

        0x040B,

        0x040C,

        0x040E,

        0xF6C4,

        0xF6C5,

        0x0430,

        0x0431,

        0x0432,

        0x0433,

        0x0434,

        0x0435,

        0x0451,

        0x0436,

        0x0437,

        0x0438,

        0x0439,

        0x043A,

        0x043B,

        0x043C,

        0x043D,

        0x043E,

        0x043F,

        0x0440,

        0x0441,

        0x0442,

        0x0443,

        0x0444,

        0x0445,

        0x0446,

        0x0447,

        0x0448,

        0x0449,

        0x044A,

        0x044B,

        0x044C,

        0x044D,

        0x044E,

        0x044F,

        0x0491,

        0x0452,

        0x0453,

        0x0454,

        0x0455,

        0x0456,

        0x0457,

        0x0458,

        0x0459,

        0x045A,

        0x045B,

        0x045C,

        0x045E,

        0x040F,

        0x0462,

        0x0472,

        0x0474,

        0xF6C6,

        0x045F,

        0x0463,

        0x0473,

        0x0475,

        0xF6C7,

        0xF6C8,

        0x04D9,

        0x200E,

        0x200F,

        0x200D,

        0x066A,

        0x060C,

        0x0660,

        0x0661,

        0x0662,

        0x0663,

        0x0664,

        0x0665,

        0x0666,

        0x0667,

        0x0668,

        0x0669,

        0x061B,

        0x061F,

        0x0621,

        0x0622,

        0x0623,

        0x0624,

        0x0625,

        0x0626,

        0x0627,

        0x0628,

        0x0629,

        0x062A,

        0x062B,

        0x062C,

        0x062D,

        0x062E,

        0x062F,

        0x0630,

        0x0631,

        0x0632,

        0x0633,

        0x0634,

        0x0635,

        0x0636,

        0x0637,

        0x0638,

        0x0639,

        0x063A,

        0x0640,

        0x0641,

        0x0642,

        0x0643,

        0x0644,

        0x0645,

        0x0646,

        0x0648,

        0x0649,

        0x064A,

        0x064B,

        0x064C,

        0x064D,

        0x064E,

        0x064F,

        0x0650,

        0x0651,

        0x0652,

        0x0647,

        0x06A4,

        0x067E,

        0x0686,

        0x0698,

        0x06AF,

        0x0679,

        0x0688,

        0x0691,

        0x06BA,

        0x06D2,

        0x06D5,

        0x20AA,

        0x05BE,

        0x05C3,

        0x05D0,

        0x05D1,

        0x05D2,

        0x05D3,

        0x05D4,

        0x05D5,

        0x05D6,

        0x05D7,

        0x05D8,

        0x05D9,

        0x05DA,

        0x05DB,

        0x05DC,

        0x05DD,

        0x05DE,

        0x05DF,

        0x05E0,

        0x05E1,

        0x05E2,

        0x05E3,

        0x05E4,

        0x05E5,

        0x05E6,

        0x05E7,

        0x05E8,

        0x05E9,

        0x05EA,

        0xFB2A,

        0xFB2B,

        0xFB4B,

        0xFB1F,

        0x05F0,

        0x05F1,

        0x05F2,

        0xFB35,

        0x05B4,

        0x05B5,

        0x05B6,

        0x05BB,

        0x05B8,

        0x05B7,

        0x05B0,

        0x05B2,

        0x05B1,

        0x05B3,

        0x05C2,

        0x05C1,

        0x05B9,

        0x05BC,

        0x05BD,

        0x05BF,

        0x05C0,

        0x02BC,

        0x2105,

        0x2113,

        0x2116,

        0x202C,

        0x202D,

        0x202E,

        0x200C,

        0x066D,

        0x02BD,

        0x00E0,

        0x2135,

        0x03B1,

        0x03AC,

        0x0101,

        0x0026,

        0xF726,

        0x2220,

        0x2329,

        0x232A,

        0x0387,

        0x0105,

        0x2248,

        0x00E5,

        0x01FB,

        0x2194,

        0x21D4,

        0x21D3,

        0x21D0,

        0x21D2,

        0x21D1,

        0x2193,

        0xF8E7,

        0x2190,

        0x2192,

        0x2191,

        0x2195,

        0x21A8,

        0xF8E6,

        0x005E,

        0x007E,

        0x002A,

        0x2217,

        0xF6E9,

        0x0040,

        0x00E3,

        0x0062,

        0x005C,

        0x007C,

        0x03B2,

        0x2588,

        0xF8F4,

        0x007B,

        0xF8F3,

        0xF8F2,

        0xF8F1,

        0x007D,

        0xF8FE,

        0xF8FD,

        0xF8FC,

        0x005B,

        0xF8F0,

        0xF8EF,

        0xF8EE,

        0x005D,

        0xF8FB,

        0xF8FA,

        0xF8F9,

        0x02D8,

        0x00A6,

        0xF6EA,

        0x2022,

        0x0063,

        0x0107,

        0x02C7,

        0x21B5,

        0x010D,

        0x00E7,

        0x0109,

        0x010B,

        0x00B8,

        0x00A2,

        0xF6DF,

        0xF7A2,

        0xF6E0,

        0x03C7,

        0x25CB,

        0x2297,

        0x2295,

        0x02C6,

        0x2663,

        0x003A,

        0x20A1,

        0x002C,

        0xF6C3,

        0xF6E1,

        0xF6E2,

        0x2245,

        0x00A9,

        0xF8E9,

        0xF6D9,

        0x00A4,

        0xF6D1,

        0xF6D2,

        0xF6D4,

        0xF6D5,

        0x0064,

        0x2020,

        0x2021,

        0xF6D3,

        0xF6D6,

        0x010F,

        0x0111,

        0x00B0,

        0x03B4,

        0x2666,

        0x00A8,

        0xF6D7,

        0xF6D8,

        0x0385,

        0x00F7,

        0x2593,

        0x2584,

        0x0024,

        0xF6E3,

        0xF724,

        0xF6E4,

        0x20AB,

        0x02D9,

        0x0323,

        0x0131,

        0xF6BE,

        0x22C5,

        0xF6EB,

        0x0065,

        0x00E9,

        0x0115,

        0x011B,

        0x00EA,

        0x00EB,

        0x0117,

        0x00E8,

        0x0038,

        0x2088,

        0xF738,

        0x2078,

        0x2208,

        0x2026,

        0x0113,

        0x2014,

        0x2205,

        0x2013,

        0x014B,

        0x0119,

        0x03B5,

        0x03AD,

        0x003D,

        0x2261,

        0x212E,

        0xF6EC,

        0x03B7,

        0x03AE,

        0x00F0,

        0x0021,

        0x203C,

        0x00A1,

        0xF7A1,

        0xF721,

        0x2203,

        0x0066,

        0x2640,

        0xFB00,

        0xFB03,

        0xFB04,

        0xFB01,

        0x2012,

        0x25A0,

        0x25AC,

        0x0035,

        0x215D,

        0x2085,

        0xF735,

        0x2075,

        0xFB02,

        0x0192,

        0x0034,

        0x2084,

        0xF734,

        0x2074,

        0x2044,

        0x2215,

        0x20A3,

        0x0067,

        0x03B3,

        0x011F,

        0x01E7,

        0x011D,

        0x0123,

        0x0121,

        0x00DF,

        0x2207,

        0x0060,

        0x0300,

        0x003E,

        0x2265,

        0x00AB,

        0x00BB,

        0x2039,

        0x203A,

        0x0068,

        0x0127,

        0x0125,

        0x2665,

        0x0309,

        0x2302,

        0x02DD,

        0x002D,

        0x00AD,

        0xF6E5,

        0xF6E6,

        0x0069,

        0x00ED,

        0x012D,

        0x00EE,

        0x00EF,

        0x00EC,

        0x0133,

        0x012B,

        0x221E,

        0x222B,

        0x2321,

        0xF8F5,

        0x2320,

        0x2229,

        0x25D8,

        0x25D9,

        0x263B,

        0x012F,

        0x03B9,

        0x03CA,

        0x0390,

        0x03AF,

        0xF6ED,

        0x0129,

        0x006A,

        0x0135,

        0x006B,

        0x03BA,

        0x0137,

        0x0138,

        0x006C,

        0x013A,

        0x03BB,

        0x013E,

        0x013C,

        0x0140,

        0x003C,

        0x2264,

        0x258C,

        0x20A4,

        0xF6C0,

        0x2227,

        0x00AC,

        0x2228,

        0x017F,

        0x25CA,

        0x0142,

        0xF6EE,

        0x2591,

        0x006D,

        0x00AF,

        0x02C9,

        0x2642,

        0x2212,

        0x2032,

        0xF6EF,

        0x00B5,

        0x03BC,

        0x00D7,

        0x266A,

        0x266B,

        0x006E,

        0x0144,

        0x0149,

        0x0148,

        0x0146,

        0x0039,

        0x2089,

        0xF739,

        0x2079,

        0x2209,

        0x2260,

        0x2284,

        0x207F,

        0x00F1,

        0x03BD,

        0x0023,

        0x006F,

        0x00F3,

        0x014F,

        0x00F4,

        0x00F6,

        0x0153,

        0x02DB,

        0x00F2,

        0x01A1,

        0x0151,

        0x014D,

        0x03C9,

        0x03D6,

        0x03CE,

        0x03BF,

        0x03CC,

        0x0031,

        0x2024,

        0x215B,

        0xF6DC,

        0x00BD,

        0x2081,

        0xF731,

        0x00BC,

        0x00B9,

        0x2153,

        0x25E6,

        0x00AA,

        0x00BA,

        0x221F,

        0x00F8,

        0x01FF,

        0xF6F0,

        0x00F5,

        0x0070,

        0x00B6,

        0x0028,

        0xF8ED,

        0xF8EC,

        0x208D,

        0x207D,

        0xF8EB,

        0x0029,

        0xF8F8,

        0xF8F7,

        0x208E,

        0x207E,

        0xF8F6,

        0x2202,

        0x0025,

        0x002E,

        0x00B7,

        0x2219,

        0xF6E7,

        0xF6E8,

        0x22A5,

        0x2030,

        0x20A7,

        0x03C6,

        0x03D5,

        0x03C0,

        0x002B,

        0x00B1,

        0x211E,

        0x220F,

        0x2282,

        0x2283,

        0x221D,

        0x03C8,

        0x0071,

        0x003F,

        0x00BF,

        0xF7BF,

        0xF73F,

        0x0022,

        0x201E,

        0x201C,

        0x201D,

        0x2018,

        0x201B,

        0x2019,

        0x201A,

        0x0027,

        0x0072,

        0x0155,

        0x221A,

        0xF8E5,

        0x0159,

        0x0157,

        0x2286,

        0x2287,

        0x00AE,

        0xF8E8,

        0xF6DA,

        0x2310,

        0x03C1,

        0x02DA,

        0xF6F1,

        0x2590,

        0xF6DD,

        0x0073,

        0x015B,

        0x0161,

        0x015F,

        0xF6C2,

        0x015D,

        0x0219,

        0x2033,

        0x00A7,

        0x003B,

        0x0037,

        0x215E,

        0x2087,

        0xF737,

        0x2077,

        0x2592,

        0x03C3,

        0x03C2,

        0x223C,

        0x0036,

        0x2086,

        0xF736,

        0x2076,

        0x002F,

        0x263A,

        0x0020,

        0x00A0,

        0x2660,

        0xF6F2,

        0x00A3,

        0x220B,

        0x2211,

        0x263C,

        0x0074,

        0x03C4,

        0x0167,

        0x0165,

        0x0163,

        0x021B,

        0x2234,

        0x03B8,

        0x03D1,

        0x00FE,

        0x0033,

        0x215C,

        0x2083,

        0xF733,

        0x00BE,

        0xF6DE,

        0x00B3,

        0x02DC,

        0x0303,

        0x0384,

        0x2122,

        0xF8EA,

        0xF6DB,

        0x25BC,

        0x25C4,

        0x25BA,

        0x25B2,

        0xF6F3,

        0x0032,

        0x2025,

        0x2082,

        0xF732,

        0x00B2,

        0x2154,

        0x0075,

        0x00FA,

        0x016D,

        0x00FB,

        0x00FC,

        0x00F9,

        0x01B0,

        0x0171,

        0x016B,

        0x005F,

        0x2017,

        0x222A,

        0x2200,

        0x0173,

        0x2580,

        0x03C5,

        0x03CB,

        0x03B0,

        0x03CD,

        0x016F,

        0x0169,

        0x0076,

        0x0077,

        0x1E83,

        0x0175,

        0x1E85,

        0x2118,

        0x1E81,

        0x0078,

        0x03BE,

        0x0079,

        0x00FD,

        0x0177,

        0x00FF,

        0x00A5,

        0x1EF3,

        0x007A,

        0x017A,

        0x017E,

        0x017C,

        0x0030,

        0x2080,

        0xF730,

        0x2070,

        0x03B6

    };

    

    private static final String names[] =

    {

        "A",

        "AE",

        "AEacute",

        "AEsmall",

        "Aacute",

        "Aacutesmall",

        "Abreve",

        "Acircumflex",

        "Acircumflexsmall",

        "Acute",

        "Acutesmall",

        "Adieresis",

        "Adieresissmall",

        "Agrave",

        "Agravesmall",

        "Alpha",

        "Alphatonos",

        "Amacron",

        "Aogonek",

        "Aring",

        "Aringacute",

        "Aringsmall",

        "Asmall",

        "Atilde",

        "Atildesmall",

        "B",

        "Beta",

        "Brevesmall",

        "Bsmall",

        "C",

        "Cacute",

        "Caron",

        "Caronsmall",

        "Ccaron",

        "Ccedilla",

        "Ccedillasmall",

        "Ccircumflex",

        "Cdotaccent",

        "Cedillasmall",

        "Chi",

        "Circumflexsmall",

        "Csmall",

        "D",

        "Dcaron",

        "Dcroat",

        "Delta",

        "Delta",

        "Dieresis",

        "DieresisAcute",

        "DieresisGrave",

        "Dieresissmall",

        "Dotaccentsmall",

        "Dsmall",

        "E",

        "Eacute",

        "Eacutesmall",

        "Ebreve",

        "Ecaron",

        "Ecircumflex",

        "Ecircumflexsmall",

        "Edieresis",

        "Edieresissmall",

        "Edotaccent",

        "Egrave",

        "Egravesmall",

        "Emacron",

        "Eng",

        "Eogonek",

        "Epsilon",

        "Epsilontonos",

        "Esmall",

        "Eta",

        "Etatonos",

        "Eth",

        "Ethsmall",

        "Euro",

        "F",

        "Fsmall",

        "G",

        "Gamma",

        "Gbreve",

        "Gcaron",

        "Gcircumflex",

        "Gcommaaccent",

        "Gdotaccent",

        "Grave",

        "Gravesmall",

        "Gsmall",

        "H",

        "H18533",

        "H18543",

        "H18551",

        "H22073",

        "Hbar",

        "Hcircumflex",

        "Hsmall",

        "Hungarumlaut",

        "Hungarumlautsmall",

        "I",

        "IJ",

        "Iacute",

        "Iacutesmall",

        "Ibreve",

        "Icircumflex",

        "Icircumflexsmall",

        "Idieresis",

        "Idieresissmall",

        "Idotaccent",

        "Ifraktur",

        "Igrave",

        "Igravesmall",

        "Imacron",

        "Iogonek",

        "Iota",

        "Iotadieresis",

        "Iotatonos",

        "Ismall",

        "Itilde",

        "J",

        "Jcircumflex",

        "Jsmall",

        "K",

        "Kappa",

        "Kcommaaccent",

        "Ksmall",

        "L",

        "LL",

        "Lacute",

        "Lambda",

        "Lcaron",

        "Lcommaaccent",

        "Ldot",

        "Lslash",

        "Lslashsmall",

        "Lsmall",

        "M",

        "Macron",

        "Macronsmall",

        "Msmall",

        "Mu",

        "N",

        "Nacute",

        "Ncaron",

        "Ncommaaccent",

        "Nsmall",

        "Ntilde",

        "Ntildesmall",

        "Nu",

        "O",

        "OE",

        "OEsmall",

        "Oacute",

        "Oacutesmall",

        "Obreve",

        "Ocircumflex",

        "Ocircumflexsmall",

        "Odieresis",

        "Odieresissmall",

        "Ogoneksmall",

        "Ograve",

        "Ogravesmall",

        "Ohorn",

        "Ohungarumlaut",

        "Omacron",

        "Omega",

        "Omega",

        "Omegatonos",

        "Omicron",

        "Omicrontonos",

        "Oslash",

        "Oslashacute",

        "Oslashsmall",

        "Osmall",

        "Otilde",

        "Otildesmall",

        "P",

        "Phi",

        "Pi",

        "Psi",

        "Psmall",

        "Q",

        "Qsmall",

        "R",

        "Racute",

        "Rcaron",

        "Rcommaaccent",

        "Rfraktur",

        "Rho",

        "Ringsmall",

        "Rsmall",

        "S",

        "SF010000",

        "SF020000",

        "SF030000",

        "SF040000",

        "SF050000",

        "SF060000",

        "SF070000",

        "SF080000",

        "SF090000",

        "SF100000",

        "SF110000",

        "SF190000",

        "SF200000",

        "SF210000",

        "SF220000",

        "SF230000",

        "SF240000",

        "SF250000",

        "SF260000",

        "SF270000",

        "SF280000",

        "SF360000",

        "SF370000",

        "SF380000",

        "SF390000",

        "SF400000",

        "SF410000",

        "SF420000",

        "SF430000",

        "SF440000",

        "SF450000",

        "SF460000",

        "SF470000",

        "SF480000",

        "SF490000",

        "SF500000",

        "SF510000",

        "SF520000",

        "SF530000",

        "SF540000",

        "Sacute",

        "Scaron",

        "Scaronsmall",

        "Scedilla",

        "Scedilla",

        "Scircumflex",

        "Scommaaccent",

        "Sigma",

        "Ssmall",

        "T",

        "Tau",

        "Tbar",

        "Tcaron",

        "Tcommaaccent",

        "Tcommaaccent",

        "Theta",

        "Thorn",

        "Thornsmall",

        "Tildesmall",

        "Tsmall",

        "U",

        "Uacute",

        "Uacutesmall",

        "Ubreve",

        "Ucircumflex",

        "Ucircumflexsmall",

        "Udieresis",

        "Udieresissmall",

        "Ugrave",

        "Ugravesmall",

        "Uhorn",

        "Uhungarumlaut",

        "Umacron",

        "Uogonek",

        "Upsilon",

        "Upsilon1",

        "Upsilondieresis",

        "Upsilontonos",

        "Uring",

        "Usmall",

        "Utilde",

        "V",

        "Vsmall",

        "W",

        "Wacute",

        "Wcircumflex",

        "Wdieresis",

        "Wgrave",

        "Wsmall",

        "X",

        "Xi",

        "Xsmall",

        "Y",

        "Yacute",

        "Yacutesmall",

        "Ycircumflex",

        "Ydieresis",

        "Ydieresissmall",

        "Ygrave",

        "Ysmall",

        "Z",

        "Zacute",

        "Zcaron",

        "Zcaronsmall",

        "Zdotaccent",

        "Zeta",

        "Zsmall",

        "a",

        "aacute",

        "abreve",

        "acircumflex",

        "acute",

        "acutecomb",

        "adieresis",

        "ae",

        "aeacute",

        "afii00208",

        "afii10017",

        "afii10018",

        "afii10019",

        "afii10020",

        "afii10021",

        "afii10022",

        "afii10023",

        "afii10024",

        "afii10025",

        "afii10026",

        "afii10027",

        "afii10028",

        "afii10029",

        "afii10030",

        "afii10031",

        "afii10032",

        "afii10033",

        "afii10034",

        "afii10035",

        "afii10036",

        "afii10037",

        "afii10038",

        "afii10039",

        "afii10040",

        "afii10041",

        "afii10042",

        "afii10043",

        "afii10044",

        "afii10045",

        "afii10046",

        "afii10047",

        "afii10048",

        "afii10049",

        "afii10050",

        "afii10051",

        "afii10052",

        "afii10053",

        "afii10054",

        "afii10055",

        "afii10056",

        "afii10057",

        "afii10058",

        "afii10059",

        "afii10060",

        "afii10061",

        "afii10062",

        "afii10063",

        "afii10064",

        "afii10065",

        "afii10066",

        "afii10067",

        "afii10068",

        "afii10069",

        "afii10070",

        "afii10071",

        "afii10072",

        "afii10073",

        "afii10074",

        "afii10075",

        "afii10076",

        "afii10077",

        "afii10078",

        "afii10079",

        "afii10080",

        "afii10081",

        "afii10082",

        "afii10083",

        "afii10084",

        "afii10085",

        "afii10086",

        "afii10087",

        "afii10088",

        "afii10089",

        "afii10090",

        "afii10091",

        "afii10092",

        "afii10093",

        "afii10094",

        "afii10095",

        "afii10096",

        "afii10097",

        "afii10098",

        "afii10099",

        "afii10100",

        "afii10101",

        "afii10102",

        "afii10103",

        "afii10104",

        "afii10105",

        "afii10106",

        "afii10107",

        "afii10108",

        "afii10109",

        "afii10110",

        "afii10145",

        "afii10146",

        "afii10147",

        "afii10148",

        "afii10192",

        "afii10193",

        "afii10194",

        "afii10195",

        "afii10196",

        "afii10831",

        "afii10832",

        "afii10846",

        "afii299",

        "afii300",

        "afii301",

        "afii57381",

        "afii57388",

        "afii57392",

        "afii57393",

        "afii57394",

        "afii57395",

        "afii57396",

        "afii57397",

        "afii57398",

        "afii57399",

        "afii57400",

        "afii57401",

        "afii57403",

        "afii57407",

        "afii57409",

        "afii57410",

        "afii57411",

        "afii57412",

        "afii57413",

        "afii57414",

        "afii57415",

        "afii57416",

        "afii57417",

        "afii57418",

        "afii57419",

        "afii57420",

        "afii57421",

        "afii57422",

        "afii57423",

        "afii57424",

        "afii57425",

        "afii57426",

        "afii57427",

        "afii57428",

        "afii57429",

        "afii57430",

        "afii57431",

        "afii57432",

        "afii57433",

        "afii57434",

        "afii57440",

        "afii57441",

        "afii57442",

        "afii57443",

        "afii57444",

        "afii57445",

        "afii57446",

        "afii57448",

        "afii57449",

        "afii57450",

        "afii57451",

        "afii57452",

        "afii57453",

        "afii57454",

        "afii57455",

        "afii57456",

        "afii57457",

        "afii57458",

        "afii57470",

        "afii57505",

        "afii57506",

        "afii57507",

        "afii57508",

        "afii57509",

        "afii57511",

        "afii57512",

        "afii57513",

        "afii57514",

        "afii57519",

        "afii57534",

        "afii57636",

        "afii57645",

        "afii57658",

        "afii57664",

        "afii57665",

        "afii57666",

        "afii57667",

        "afii57668",

        "afii57669",

        "afii57670",

        "afii57671",

        "afii57672",

        "afii57673",

        "afii57674",

        "afii57675",

        "afii57676",

        "afii57677",

        "afii57678",

        "afii57679",

        "afii57680",

        "afii57681",

        "afii57682",

        "afii57683",

        "afii57684",

        "afii57685",

        "afii57686",

        "afii57687",

        "afii57688",

        "afii57689",

        "afii57690",

        "afii57694",

        "afii57695",

        "afii57700",

        "afii57705",

        "afii57716",

        "afii57717",

        "afii57718",

        "afii57723",

        "afii57793",

        "afii57794",

        "afii57795",

        "afii57796",

        "afii57797",

        "afii57798",

        "afii57799",

        "afii57800",

        "afii57801",

        "afii57802",

        "afii57803",

        "afii57804",

        "afii57806",

        "afii57807",

        "afii57839",

        "afii57841",

        "afii57842",

        "afii57929",

        "afii61248",

        "afii61289",

        "afii61352",

        "afii61573",

        "afii61574",

        "afii61575",

        "afii61664",

        "afii63167",

        "afii64937",

        "agrave",

        "aleph",

        "alpha",

        "alphatonos",

        "amacron",

        "ampersand",

        "ampersandsmall",

        "angle",

        "angleleft",

        "angleright",

        "anoteleia",

        "aogonek",

        "approxequal",

        "aring",

        "aringacute",

        "arrowboth",

        "arrowdblboth",

        "arrowdbldown",

        "arrowdblleft",

        "arrowdblright",

        "arrowdblup",

        "arrowdown",

        "arrowhorizex",

        "arrowleft",

        "arrowright",

        "arrowup",

        "arrowupdn",

        "arrowupdnbse",

        "arrowvertex",

        "asciicircum",

        "asciitilde",

        "asterisk",

        "asteriskmath",

        "asuperior",

        "at",

        "atilde",

        "b",

        "backslash",

        "bar",

        "beta",

        "block",

        "braceex",

        "braceleft",

        "braceleftbt",

        "braceleftmid",

        "bracelefttp",

        "braceright",

        "bracerightbt",

        "bracerightmid",

        "bracerighttp",

        "bracketleft",

        "bracketleftbt",

        "bracketleftex",

        "bracketlefttp",

        "bracketright",

        "bracketrightbt",

        "bracketrightex",

        "bracketrighttp",

        "breve",

        "brokenbar",

        "bsuperior",

        "bullet",

        "c",

        "cacute",

        "caron",

        "carriagereturn",

        "ccaron",

        "ccedilla",

        "ccircumflex",

        "cdotaccent",

        "cedilla",

        "cent",

        "centinferior",

        "centoldstyle",

        "centsuperior",

        "chi",

        "circle",

        "circlemultiply",

        "circleplus",

        "circumflex",

        "club",

        "colon",

        "colonmonetary",

        "comma",

        "commaaccent",

        "commainferior",

        "commasuperior",

        "congruent",

        "copyright",

        "copyrightsans",

        "copyrightserif",

        "currency",

        "cyrBreve",

        "cyrFlex",

        "cyrbreve",

        "cyrflex",

        "d",

        "dagger",

        "daggerdbl",

        "dblGrave",

        "dblgrave",

        "dcaron",

        "dcroat",

        "degree",

        "delta",

        "diamond",

        "dieresis",

        "dieresisacute",

        "dieresisgrave",

        "dieresistonos",

        "divide",

        "dkshade",

        "dnblock",

        "dollar",

        "dollarinferior",

        "dollaroldstyle",

        "dollarsuperior",

        "dong",

        "dotaccent",

        "dotbelowcomb",

        "dotlessi",

        "dotlessj",

        "dotmath",

        "dsuperior",

        "e",

        "eacute",

        "ebreve",

        "ecaron",

        "ecircumflex",

        "edieresis",

        "edotaccent",

        "egrave",

        "eight",

        "eightinferior",

        "eightoldstyle",

        "eightsuperior",

        "element",

        "ellipsis",

        "emacron",

        "emdash",

        "emptyset",

        "endash",

        "eng",

        "eogonek",

        "epsilon",

        "epsilontonos",

        "equal",

        "equivalence",

        "estimated",

        "esuperior",

        "eta",

        "etatonos",

        "eth",

        "exclam",

        "exclamdbl",

        "exclamdown",

        "exclamdownsmall",

        "exclamsmall",

        "existential",

        "f",

        "female",

        "ff",

        "ffi",

        "ffl",

        "fi",

        "figuredash",

        "filledbox",

        "filledrect",

        "five",

        "fiveeighths",

        "fiveinferior",

        "fiveoldstyle",

        "fivesuperior",

        "fl",

        "florin",

        "four",

        "fourinferior",

        "fouroldstyle",

        "foursuperior",

        "fraction",

        "fraction",

        "franc",

        "g",

        "gamma",

        "gbreve",

        "gcaron",

        "gcircumflex",

        "gcommaaccent",

        "gdotaccent",

        "germandbls",

        "gradient",

        "grave",

        "gravecomb",

        "greater",

        "greaterequal",

        "guillemotleft",

        "guillemotright",

        "guilsinglleft",

        "guilsinglright",

        "h",

        "hbar",

        "hcircumflex",

        "heart",

        "hookabovecomb",

        "house",

        "hungarumlaut",

        "hyphen",

        "hyphen",

        "hypheninferior",

        "hyphensuperior",

        "i",

        "iacute",

        "ibreve",

        "icircumflex",

        "idieresis",

        "igrave",

        "ij",

        "imacron",

        "infinity",

        "integral",

        "integralbt",

        "integralex",

        "integraltp",

        "intersection",

        "invbullet",

        "invcircle",

        "invsmileface",

        "iogonek",

        "iota",

        "iotadieresis",

        "iotadieresistonos",

        "iotatonos",

        "isuperior",

        "itilde",

        "j",

        "jcircumflex",

        "k",

        "kappa",

        "kcommaaccent",

        "kgreenlandic",

        "l",

        "lacute",

        "lambda",

        "lcaron",

        "lcommaaccent",

        "ldot",

        "less",

        "lessequal",

        "lfblock",

        "lira",

        "ll",

        "logicaland",

        "logicalnot",

        "logicalor",

        "longs",

        "lozenge",

        "lslash",

        "lsuperior",

        "ltshade",

        "m",

        "macron",

        "macron",

        "male",

        "minus",

        "minute",

        "msuperior",

        "mu",

        "mu",

        "multiply",

        "musicalnote",

        "musicalnotedbl",

        "n",

        "nacute",

        "napostrophe",

        "ncaron",

        "ncommaaccent",

        "nine",

        "nineinferior",

        "nineoldstyle",

        "ninesuperior",

        "notelement",

        "notequal",

        "notsubset",

        "nsuperior",

        "ntilde",

        "nu",

        "numbersign",

        "o",

        "oacute",

        "obreve",

        "ocircumflex",

        "odieresis",

        "oe",

        "ogonek",

        "ograve",

        "ohorn",

        "ohungarumlaut",

        "omacron",

        "omega",

        "omega1",

        "omegatonos",

        "omicron",

        "omicrontonos",

        "one",

        "onedotenleader",

        "oneeighth",

        "onefitted",

        "onehalf",

        "oneinferior",

        "oneoldstyle",

        "onequarter",

        "onesuperior",

        "onethird",

        "openbullet",

        "ordfeminine",

        "ordmasculine",

        "orthogonal",

        "oslash",

        "oslashacute",

        "osuperior",

        "otilde",

        "p",

        "paragraph",

        "parenleft",

        "parenleftbt",

        "parenleftex",

        "parenleftinferior",

        "parenleftsuperior",

        "parenlefttp",

        "parenright",

        "parenrightbt",

        "parenrightex",

        "parenrightinferior",

        "parenrightsuperior",

        "parenrighttp",

        "partialdiff",

        "percent",

        "period",

        "periodcentered",

        "periodcentered",

        "periodinferior",

        "periodsuperior",

        "perpendicular",

        "perthousand",

        "peseta",

        "phi",

        "phi1",

        "pi",

        "plus",

        "plusminus",

        "prescription",

        "product",

        "propersubset",

        "propersuperset",

        "proportional",

        "psi",

        "q",

        "question",

        "questiondown",

        "questiondownsmall",

        "questionsmall",

        "quotedbl",

        "quotedblbase",

        "quotedblleft",

        "quotedblright",

        "quoteleft",

        "quotereversed",

        "quoteright",

        "quotesinglbase",

        "quotesingle",

        "r",

        "racute",

        "radical",

        "radicalex",

        "rcaron",

        "rcommaaccent",

        "reflexsubset",

        "reflexsuperset",

        "registered",

        "registersans",

        "registerserif",

        "revlogicalnot",

        "rho",

        "ring",

        "rsuperior",

        "rtblock",

        "rupiah",

        "s",

        "sacute",

        "scaron",

        "scedilla",

        "scedilla",

        "scircumflex",

        "scommaaccent",

        "second",

        "section",

        "semicolon",

        "seven",

        "seveneighths",

        "seveninferior",

        "sevenoldstyle",

        "sevensuperior",

        "shade",

        "sigma",

        "sigma1",

        "similar",

        "six",

        "sixinferior",

        "sixoldstyle",

        "sixsuperior",

        "slash",

        "smileface",

        "space",

        "space",

        "spade",

        "ssuperior",

        "sterling",

        "suchthat",

        "summation",

        "sun",

        "t",

        "tau",

        "tbar",

        "tcaron",

        "tcommaaccent",

        "tcommaaccent",

        "therefore",

        "theta",

        "theta1",

        "thorn",

        "three",

        "threeeighths",

        "threeinferior",

        "threeoldstyle",

        "threequarters",

        "threequartersemdash",

        "threesuperior",

        "tilde",

        "tildecomb",

        "tonos",

        "trademark",

        "trademarksans",

        "trademarkserif",

        "triagdn",

        "triaglf",

        "triagrt",

        "triagup",

        "tsuperior",

        "two",

        "twodotenleader",

        "twoinferior",

        "twooldstyle",

        "twosuperior",

        "twothirds",

        "u",

        "uacute",

        "ubreve",

        "ucircumflex",

        "udieresis",

        "ugrave",

        "uhorn",

        "uhungarumlaut",

        "umacron",

        "underscore",

        "underscoredbl",

        "union",

        "universal",

        "uogonek",

        "upblock",

        "upsilon",

        "upsilondieresis",

        "upsilondieresistonos",

        "upsilontonos",

        "uring",

        "utilde",

        "v",

        "w",

        "wacute",

        "wcircumflex",

        "wdieresis",

        "weierstrass",

        "wgrave",

        "x",

        "xi",

        "y",

        "yacute",

        "ycircumflex",

        "ydieresis",

        "yen",

        "ygrave",

        "z",

        "zacute",

        "zcaron",

        "zdotaccent",

        "zero",

        "zeroinferior",

        "zerooldstyle",

        "zerosuperior",

        "zeta"

    };

    

    static

    {

        for (int k = 0; k < unicode.length; ++k)

        {

            Integer num = new Integer(unicode[k]);

            unicode2names.put(num, names[k]);

            int code[] = (int[])names2unicode.get(names[k]);

            if (code == null)

            {

                names2unicode.put(names[k], new int[]{unicode[k]});

            }

            else

            {

                int code2[] = new int[code.length + 1];

                System.arraycopy(code, 0, code2, 0, code.length);

                code2[code.length] = unicode[k];

                names2unicode.put(names[k], code2);

            }

        }

    }

    

    public static int[] nameToUnicode(String name)

    {

        return (int[])names2unicode.get(name);

    }

    

    public static String unicodeToName(int num)

    {

        return (String)unicode2names.get(new Integer(num));

    }

}