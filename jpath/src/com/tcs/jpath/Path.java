/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.jpath;

import java.io.File;

/**
 *
 * @author 1299792
 */
public final class Path {
    public static final String INPUT_FILE_NAME = "temp_jpath_012.txt";
    public static final String TEMP_DIR  = System.getProperty("java.io.tmpdir");
    public static final String JPATH_FILE = TEMP_DIR + File.separator + INPUT_FILE_NAME;
}
