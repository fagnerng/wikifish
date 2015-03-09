/*********************************************************************
 *  ____                      _____      _                           *
 * / ___|  ___  _ __  _   _  | ____|_ __(_) ___ ___ ___  ___  _ __   *
 * \___ \ / _ \| '_ \| | | | |  _| | '__| |/ __/ __/ __|/ _ \| '_ \  *
 *  ___) | (_) | | | | |_| | | |___| |  | | (__\__ \__ \ (_) | | | | *
 * |____/ \___/|_| |_|\__, | |_____|_|  |_|\___|___/___/\___/|_| |_| *
 *                    |___/                                          *
 *                                                                   *
 *********************************************************************
 * Copyright 2009 Sony Ericsson Mobile Communications AB.            *
 * All rights, including trade secret rights, reserved.              *
 *********************************************************************/

/**
 * @file CacheFileSort
 *
 * Class used only for ordering files by modification time.
 * 
 * @author Paulo Matuki da Cunha (paulo.matukidacunha@sonyericsson.com)
 */

package com.wikifish.persistence.cache;

import java.io.File;
import java.util.Comparator;

/**
 * @author vntpamc
 */
public class CacheFileSort implements Comparator<File> {

    /*
     * (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(File file, File otherFile) {
        int result = 0;
        long timeFileModified = file.lastModified();
        long timeOtherFileModified = otherFile.lastModified();

        if (timeFileModified < timeOtherFileModified) {
            result = -1;
        } else if (timeFileModified > timeOtherFileModified) {
            result = 1;
        }

        return result;
    }

}
