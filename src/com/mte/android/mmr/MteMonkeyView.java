package com.mte.android.mmr;

import java.util.List;
import java.util.logging.Logger;

import com.android.chimpchat.core.ChimpRect;
import com.android.chimpchat.core.IChimpView;
import com.android.chimpchat.core.IChimpView.AccessibilityIds;

/**
 * Project :  MteMonkeyView
 * Created :  java
 * Date    :  7/1/15
 */

public class MteMonkeyView {
    private static final Logger LOG = Logger.getLogger(MteMonkeyView.class.getName());

    private IChimpView impl;

	public MteMonkeyView(IChimpView impl) {
		this.impl = impl;
	}
	
	public boolean getChecked(){
		return impl.getChecked();
	}
	
	public String getViewClass(){
		return impl.getViewClass();
	}
	
	public String getText(){
		return impl.getText();
	}
	
	public ChimpRect getLocation(){
		return impl.getLocation();
	}
	
	public boolean getSelected(){
		return impl.getSelected();
	}
	
	public void setSelected(boolean selected){
		 impl.setSelected(selected);
	}
	
	public boolean getFocused(){
		return impl.getFocused();
	}
	
	public void setFocused(boolean focused){
		impl.setFocused(focused);
	}
	
	public MteMonkeyView getParent(){
		MteMonkeyView parent=new MteMonkeyView(impl.getParent());
		return parent;
	}
	
	public List<IChimpView> getChildren(){		
		List<IChimpView> chimpChildren = impl.getChildren();
		return chimpChildren;
	}
	
	
	public AccessibilityIds getAccessibilityIds(){		
		return impl.getAccessibilityIds();
	}
	
	public boolean getEnabled(){
		return impl.getEnabled();
	}
	
}
