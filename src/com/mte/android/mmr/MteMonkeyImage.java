package com.mte.android.mmr;



import com.android.chimpchat.core.IChimpImage;

import java.util.logging.Logger;

/**
 * Project :  MteMonkeyImage
 * Created :  java
 * Date    :  7/2/15
 */
public class MteMonkeyImage {
	
    private static Logger LOG = Logger.getLogger(MteMonkeyImage.class.getCanonicalName());

    private final IChimpImage impl;

	public MteMonkeyImage(IChimpImage impl) {
		this.impl = impl;
	}
	
    public IChimpImage getImpl() {
        return impl;
    }

    public byte[] convertToBytes(String format){
    	return impl.convertToBytes(format);
    }
    
    public boolean writeToFile(String path, String format){    	
    	return impl.writeToFile(path, format);  	
    }
    
    public boolean sameAs(float percent){
    	IChimpImage other =this.getImpl();  	
    	return impl.sameAs(other, percent);
    }
    
    public MteMonkeyImage getSubImage(int x, int y,int w, int h){
        IChimpImage image = impl.getSubImage(x, y, w, h);
        return new MteMonkeyImage(image);
    } 
	
}
