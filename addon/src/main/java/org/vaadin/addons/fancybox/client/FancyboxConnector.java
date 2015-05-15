package org.vaadin.addons.fancybox.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VLink;
import com.vaadin.client.ui.VPanel;
import com.vaadin.client.ui.VWindow;
import com.vaadin.shared.ui.Connect;
import org.vaadin.addons.fancybox.Fancybox;
import org.vaadin.addons.fancybox.shared.FancyboxState;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zhuravskiy.vs@gmail.com
 */
@Connect(Fancybox.class)
public class FancyboxConnector extends AbstractExtensionConnector implements ClickHandler {
    VLink link;

    @Override
    protected void extend(ServerConnector target) {
        if(target instanceof ComponentConnector)
            extend(((ComponentConnector) target).getWidget());
        else
            Logger.getLogger(getClass().getSimpleName())
                    .log(Level.WARNING,
                            "PanelCaptionButtonConnector can work only with classes implemented ComponentConnector");

    }

    @Override
    public FancyboxState getState() {
        return (FancyboxState)super.getState();
    }

    protected void extend(Widget widget) {
        if(widget instanceof VLink)
            extend((VLink) widget);
        else
            Logger.getLogger(getClass().getSimpleName())
                    .log(Level.WARNING,
                            "PanelCaptionButtonConnector could not extend " + widget.getClass().getName() + ", VWindow and VPanel supports only");
    }

    protected void extend(final VLink link) {
        this.link = link;
        int handlersCount = ensureHandlerManager().getHandlerCount(ClickEvent.getType());
        //remove handlers
        for (int i = 0; i < handlersCount; i++) {
            ClickHandler h = ensureHandlerManager().getHandler(ClickEvent.getType(), i);
            ensureHandlerManager().removeHandler(ClickEvent.getType(), h);
        }
        //add own
        link.addClickHandler(this);

/*        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            public void execute() {
                fancy(link.src, link.anchor);
            }
        });*/
    }

    @Override
    public void onClick(ClickEvent event) {
        if(!getState().enabled) {
            link.onClick(event);
            return;
        }
        Event e = DOM.eventGetCurrentEvent();
        if (!e.getCtrlKey() && !e.getAltKey() && !e.getShiftKey()
                && !e.getMetaKey()) {
            fancy(link.src, link.anchor);
            e.preventDefault();
        }
    }

    public String getType() {
        if(getState().type == null)
            return null;
        return getState().type.name();
    }

    public String getScrolling() {
        if(getState().scrolling == null)
            return null;
        return getState().scrolling.name();
    }

    protected native void fancy(String imageHref, Element a) /*-{
        if ($wnd.jQuery) {
            var state = this.@org.vaadin.addons.fancybox.client.FancyboxConnector::getState()();
            var obj = {
                href: imageHref
            };
            var type = this.@org.vaadin.addons.fancybox.client.FancyboxConnector::getType()();
            if (type != null)
                obj.type = type;
            if (state.title != null)
                obj.title = state.title;
            if (state.closeClick != null)
                obj.closeClick = state.closeClick;
            var scrolling = this.@org.vaadin.addons.fancybox.client.FancyboxConnector::getScrolling()();
            if (scrolling != null)
                obj.scrolling = scrolling;
            if (state.autoSize != null)
                obj.autoSize = state.autoSize;
            if (state.fitToView != null)
                obj.fitToView = state.fitToView;
            if (state.padding != null)
                obj.padding = state.padding;
            if (state.width != null)
                obj.width = state.width;
            if (state.height != null)
                obj.height = state.height;
            if (state.minWidth != null)
                obj.minWidth = state.minWidth;
            if (state.minHeight != null)
                obj.minHeight = state.minHeight;
            if (state.maxWidth != null)
                obj.maxWidth = state.maxWidth;
            if (state.maxHeight != null)
                obj.maxHeight = state.maxHeight;
            if (state.tpl != null)
                obj.tpl = state.tpl;
            $wnd.jQuery.fancybox(obj);
        }
    }-*/;

/*    protected native void fancy(String imageHref, Element a) *//*-{
        if($wnd.jQuery) {
            $wnd.jQuery(a).click(new function () {
                $wnd.jQuery.fancybox({
                    href: imageHref,
                    padding : 0,
                });
            });
        }
    }-*//*;*/
}
