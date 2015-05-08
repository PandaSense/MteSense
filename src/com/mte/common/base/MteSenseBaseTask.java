package com.mte.common.base;

import org.apache.log4j.Logger;

/**
 * Project :  appiumsense
 * Created :  java
 * Date    :  4/17/15
 */
public class MteSenseBaseTask {

    private Logger logger = Logger.getLogger(MteSenseBaseTask.class);

    protected MteSenseCore asCore;

    public MteSenseBaseTask(MteSenseCore senseCore) {
        this.asCore=senseCore;
    }




}
