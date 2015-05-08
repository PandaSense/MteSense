package com.mte.common.base;

import org.apache.log4j.Logger;

/**
 * Project :  appiumsense
 * Created :  java
 * Date    :  4/12/15
 */
public class MteSenseBasePage {

    protected MteSenseCore asCore;

    private Logger logger = Logger.getLogger(MteSenseBasePage.class);

    public MteSenseBasePage(MteSenseCore asCore) {
        this.asCore = asCore;
    }


}
