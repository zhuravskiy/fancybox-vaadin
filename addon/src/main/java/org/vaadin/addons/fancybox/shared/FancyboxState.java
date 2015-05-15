package org.vaadin.addons.fancybox.shared;

import com.vaadin.shared.communication.SharedState;

import java.util.HashMap;

/**
 * For more options see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
 * @author zhuravskiy.vs@gmail.com
 * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
 */
public class FancyboxState extends SharedState {
    public enum Type {image, inline, ajax, iframe, swf}
    public enum Scrolling { auto, yes, no, visible}
    public Type type = Type.image;
    /**
     * Default null
     */
    public String title;
    /**
     * Default true
     */
    public Boolean closeClick;
    /**
     * Default true
     */
    public Boolean closeBtn;
    /**
     * Default Scrolling.auto
     */
    public Scrolling scrolling;
    /**
     * Default true
     */
    public Boolean autoSize;
    /**
     * Default true
     */
    public Boolean fitToView;
    /**
     * Default 15
     */
    public Integer padding;
    /**
     * Default 20
     */
    public Integer margin;
    /**
     * Default 800
     */
    public Integer width;
    /**
     * Default 600
     */
    public Integer height;
    /**
     * Default 100
     */
    public Integer minWidth;
    /**
     * Default 100
     */
    public Integer minHeight;
    /**
     * Default 9999
     */
    public Integer maxWidth;
    /**
     * Default 9999
     */
    public Integer maxHeight;

    public HashMap<String, String> tpl = new HashMap<String, String>();

}
