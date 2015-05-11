package com.mte.common.base;

import com.mte.util.ReportUtil;
import org.apache.log4j.Logger;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  4/17/15
 */
public class MteSenseBaseTask {

    private Logger logger = Logger.getLogger(MteSenseBaseTask.class);

    protected MteSenseCore asCore;

    private ReportUtil reporter = null;

    public MteSenseBaseTask(MteSenseCore senseCore, ReportUtil reporter) {
        this.asCore = senseCore;
        this.reporter=reporter;
    }

}
