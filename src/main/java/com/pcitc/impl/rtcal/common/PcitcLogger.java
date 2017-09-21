package com.pcitc.impl.rtcal.common;

import org.apache.log4j.Logger;

public class PcitcLogger {

	public static final Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	
}
