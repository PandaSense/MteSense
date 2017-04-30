package com.mte.android.mmr;

import com.android.chimpchat.core.By;
import com.android.chimpchat.core.IChimpDevice;
import com.android.chimpchat.core.IChimpImage;
import com.android.chimpchat.core.IChimpView;
import com.android.chimpchat.core.TouchPressType;
import com.android.chimpchat.hierarchyviewer.HierarchyViewer;
import com.android.hierarchyviewerlib.models.ViewNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Project :  MteMonkeyDevice
 * Created :  java
 * Date    :  7/1/15
 * ddmlib.jar,guavalib.jar,sdklib.jar,chimpchat.jar,hierarchyviewer2lib.jar
 */
public class MteMonkeyDevice {

    private static final Logger LOG = Logger.getLogger(MteMonkeyDevice.class.getName());

    public static final String DOWN = TouchPressType.DOWN.getIdentifier();

    public static final String UP = TouchPressType.UP.getIdentifier();

    public static final String DOWN_AND_UP = TouchPressType.DOWN_AND_UP.getIdentifier();

    public static final String MOVE = TouchPressType.MOVE.getIdentifier();

    private IChimpDevice impl;

    public MteMonkeyDevice(IChimpDevice impl) {
        this.impl = impl;
    }

    public IChimpDevice getImpl() {
        return impl;
    }

    public void dispose(){
        impl.dispose();
    }


    public HierarchyViewer getHierarchyViewer() {

        return impl.getHierarchyViewer();
    }

    public IChimpImage takeSnapshot() {
        return impl.takeSnapshot();
    }

    public String getProperty(String key) {
        return impl.getProperty(key);
    }

    public void touch(int x, int y) {

        impl.touch(x, y, TouchPressType.DOWN_AND_UP);
    }

    public void drag(int startx, int starty, int endx, int endy, long ms) {
        drag(startx, starty, endx, endy, 10, ms);
    }

    public void drag(int startx, int starty, int endx, int endy, int steps, long ms) {
        impl.drag(startx, starty, endx, endy, steps, ms);
    }

    public void press(String name) {
        impl.press(name, TouchPressType.DOWN_AND_UP);
    }

    public void type(String message) {
        impl.type(message);
    }

    public String shell(String cmd) {
        return impl.shell(cmd);
    }

    public String shell(String cmd, int timeout) {
        return impl.shell(cmd, timeout);
    }

    public void reboot(String into) {
        impl.reboot(into);
    }

    public boolean installPackage(String path) {
        return impl.installPackage(path);
    }

    public boolean removePackage(String packageName){
        return impl.removePackage(packageName);
    }

    public void startActivity(String component) {
        String action = "android.intent.action.MAIN";
        Collection<String> categories = new ArrayList<String>();
        categories.add("android.intent.category.LAUNCHER");
        impl.startActivity(null, action, null, null, categories, new HashMap<String, Object>(), component, 0);
    }

    public void wake(){
        impl.wake();
    }

    public Collection<String> getPropertyList(){
        return impl.getPropertyList();
    }

    public Collection<String> getViewIdList(){
        return impl.getViewIdList();
    }

    public MteMonkeyView getViewById(String id){
        IChimpView view = impl.getView(By.id(id));
        return new MteMonkeyView(view);
    }

    public MteMonkeyView getViewByAccessibilityIds(int windowId,int accessibilityId){
        IChimpView view = impl.getView(By.accessibilityIds(windowId, accessibilityId));
        return new MteMonkeyView(view);
    }

    public MteMonkeyView getRootView(){

        return new MteMonkeyView(impl.getRootView());
    }

    public List<MteMonkeyView> getViewsByText(String text){
        Collection<IChimpView> views = impl.getViews(By.text(text));
        List<MteMonkeyView> viewSs=new ArrayList<MteMonkeyView>();
        for (IChimpView view : views){
            viewSs.add(new MteMonkeyView(view));
        }
        return viewSs;
    }


    public boolean visible(String byId) {
        ViewNode node = this.getHierarchyViewer().findViewById(byId);
        return this.getHierarchyViewer().visible(node);
    }


    public boolean exists(String byId) {
        ViewNode node = this.getHierarchyViewer().findViewById(byId);
        return node != null;
    }

    public String getFocusedWindowId() {
        return this.getHierarchyViewer().getFocusedWindowName();
    }

    public String getText(String byId) {
        ViewNode node = this.getHierarchyViewer().findViewById(byId);
        return this.getHierarchyViewer().getText(node);
    }

}