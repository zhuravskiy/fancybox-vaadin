package org.vaadin.addons.fancybox;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.Link;
import org.vaadin.addons.fancybox.shared.FancyboxState;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * FancyBox javascript library wrapper. Usage:
 * <pre>
 *  Link link = new Link(
 *      "Click Me",
 *      new ExternalResource("//www.jail.se/hardware/digital_camera/canon/ixus_800is-powershot_sd700/images/sample_photos/sample3.jpg")
 *  );
 *  new Fancybox(link).setPadding(0).setVersion("2.1.5");
 * </pre>
 * @author zhuravskiy.vs@gmail.com
 */
@JavaScript({"//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"})
@StyleSheet({"//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"})
public class Fancybox extends AbstractExtension {
    protected boolean debug = false;
    protected String version = "2.1.5";
    protected String cdnPath = "//cdnjs.cloudflare.com/ajax/libs/fancybox/";
    protected Link extendable;

    public Fancybox(Link link) {
        super(link);
        extendable = link;
    }

    @Override
    protected FancyboxState getState() {
        return (FancyboxState)super.getState();
    }

    public boolean isDebug() {
        return debug;
    }

    public Fancybox setDebug(boolean debug) {
        boolean changed = debug != this.debug;
        this.debug = debug;
        if(changed)
            constructLibraryPath();
        return this;
    }

    public String getVersion() {
        return version;
    }

    public String getCdnPath() {
        return cdnPath;
    }

    public Fancybox setCdnPath(String cdnPath) {
        boolean changed = cdnPath != this.cdnPath;
        this.cdnPath = cdnPath;
        if(changed)
            constructLibraryPath();
        return this;
    }

    public Fancybox setVersion(String version) {
        boolean changed = version != this.version;
        this.version = version;
        if(changed)
            constructLibraryPath();
        return this;
    }

    protected void constructLibraryPath() {
        String basePath = cdnPath + version + "/";
        String javaScriptPath = basePath + (debug ? "jquery.fancybox.js" : "jquery.fancybox.min.js");
        String cssPath = basePath + (debug ? "jquery.fancybox.css" : "jquery.fancybox.min.css");
        changeAnnotationValue(Fancybox.class.getAnnotation(JavaScript.class), "value", new String[]{javaScriptPath});
        changeAnnotationValue(Fancybox.class.getAnnotation(StyleSheet.class), "value", new String[]{cssPath});
    }

    /**
     * Changes the annotation value for the given key of the given annotation to newValue and returns
     * the previous value.
     */
    @SuppressWarnings("unchecked")
    protected void changeAnnotationValue(Annotation annotation, String key, Object newValue){
        Object handler = Proxy.getInvocationHandler(annotation);
        Field f;
        try {
            f = handler.getClass().getDeclaredField("memberValues");
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
        f.setAccessible(true);
        Map<String, Object> memberValues;
        try {
            memberValues = (Map<String, Object>) f.get(handler);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        Object oldValue = memberValues.get(key);
        if (oldValue == null || oldValue.getClass() != newValue.getClass()) {
            throw new IllegalArgumentException();
        }
        memberValues.put(key, newValue);
    }


    /**
     * @return type for content
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public FancyboxState.Type getType() {
        return getState().type;
    }

    /**
     * @param type Default {@link org.vaadin.addons.fancybox.shared.FancyboxState.Type#image image}
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setType(FancyboxState.Type type) {
        getState().type = type;
        return this;
    }

    public String getTitle() {
        return getState().title;
    }

    /**
     * @param title Default null
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setTitle(String title) {
        getState().title = title;
        return this;
    }

    public Boolean isCloseClick() {
        return getState().closeClick;
    }

    /**
     * @param closeClick Default true
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setCloseClick(Boolean closeClick) {
        getState().closeClick = closeClick;
        return this;
    }

    public Boolean isCloseBtn() {
        return getState().closeBtn;
    }

    /**
     * @param closeBtn Default true
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setCloseBtn(Boolean closeBtn) {
        getState().closeBtn = closeBtn;
        return this;
    }

    public FancyboxState.Scrolling getScrolling() {
        return getState().scrolling;
    }

    /**
     * @param scrolling Default {@link org.vaadin.addons.fancybox.shared.FancyboxState.Scrolling#auto auto}
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setScrolling(FancyboxState.Scrolling scrolling) {
        getState().scrolling = scrolling;
        return this;
    }

    public Boolean isAutoSize() {
        return getState().autoSize;
    }

    /**
     * @param autoSize Default true
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setAutoSize(Boolean autoSize) {
        getState().autoSize = autoSize;
        return this;
    }

    public Boolean isFitToView() {
        return getState().fitToView;
    }

    /**
     * @param fitToView Default true
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setFitToView(Boolean fitToView) {
        getState().fitToView = fitToView;
        return this;
    }

    public Integer getPadding() {
        return getState().padding;
    }

    /**
     * @param padding Default 15
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setPadding(Integer padding) {
        getState().padding = padding;
        return this;
    }

    public Integer getMargin() {
        return getState().margin;
    }

    /**
     * @param margin Default 20
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setMargin(Integer margin) {
        getState().margin = margin;
        return this;
    }

    public Integer getWidth() {
        return getState().width;
    }

    /**
     * @param width Default 800
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setWidth(Integer width) {
        getState().width = width;
        return this;
    }

    public Integer getHeight() {
        return getState().height;
    }

    /**
     * @param height Default 600
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setHeight(Integer height) {
        getState().height = height;
        return this;
    }

    public Integer getMinWidth() {
        return getState().minWidth;
    }

    /**
     * @param minWidth Default 100
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setMinWidth(Integer minWidth) {
        getState().minWidth = minWidth;
        return this;
    }

    public Integer getMinHeight() {
        return getState().minHeight;
    }

    /**
     * @param minHeight Default 100
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setMinHeight(Integer minHeight) {
        getState().minHeight = minHeight;
        return this;
    }

    public Integer getMaxWidth() {
        return getState().maxWidth;
    }

    /**
     * @param maxWidth Default 9999
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setMaxWidth(Integer maxWidth) {
        getState().maxWidth = maxWidth;
        return this;
    }

    public Integer getMaxHeight() {
        return getState().maxHeight;
    }

    /**
     * @param maxHeight Default 9999
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setMaxHeight(Integer maxHeight) {
        getState().maxHeight = maxHeight;
        return this;
    }

    public HashMap<String, String> getTpl() {
        return getState().tpl;
    }

    /**
     * Default
     * {
     *  wrap     : '<div class="fancybox-wrap" tabIndex="-1"><div class="fancybox-skin"><div class="fancybox-outer"><div class="fancybox-inner"></div></div></div></div>',
     *  image    : '<img class="fancybox-image" src="{href}" alt="" />',
     *  iframe   : '<iframe id="fancybox-frame{rnd}" name="fancybox-frame{rnd}" class="fancybox-iframe" frameborder="0" vspace="0" hspace="0"' + ($.browser.msie ? ' allowtransparency="true"' : '') + '></iframe>',
     *  error    : '<p class="fancybox-error">The requested content cannot be loaded.<br/>Please try again later.</p>',
     *  closeBtn : '<a title="Close" class="fancybox-item fancybox-close" href="javascript:;"></a>',
     *  next     : '<a title="Next" class="fancybox-nav fancybox-next" href="javascript:;"><span></span></a>',
     *  prev     : '<a title="Previous" class="fancybox-nav fancybox-prev" href="javascript:;"><span></span></a>'
     * }
     * @param tpl
     * @see <a href=http://fancyapps.com/fancybox/#docs>http://fancyapps.com/fancybox/#docs</a>
     */
    public Fancybox setTpl(HashMap<String, String> tpl) {
        getState().tpl = tpl;
        return this;
    }

    public Fancybox setEnabled(boolean enabled) {
        getState().enabled = enabled;
        return this;
    }

    public boolean isEnabled() {
        return getState().enabled;
    }
}
