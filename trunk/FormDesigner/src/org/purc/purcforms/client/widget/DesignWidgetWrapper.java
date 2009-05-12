package org.purc.purcforms.client.widget;

import java.util.List;

import org.purc.purcforms.client.LeftPanel.Images;
import org.purc.purcforms.client.controller.FormDesignerDragController;
import org.purc.purcforms.client.controller.QuestionChangeListener;
import org.purc.purcforms.client.controller.WidgetSelectionListener;
import org.purc.purcforms.client.model.OptionDef;
import org.purc.purcforms.client.model.QuestionDef;
import org.purc.purcforms.client.util.FormDesignerUtil;
import org.purc.purcforms.client.util.FormUtil;
import org.zenika.widget.client.datePicker.DatePicker;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.MouseListenerCollection;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Element;


/**
 * 
 * @author daniel
 *
 */
public class DesignWidgetWrapper extends WidgetEx implements SourcesMouseEvents, QuestionChangeListener{
	
	private MouseListenerCollection mouseListeners;
	private WidgetSelectionListener widgetSelectionListener;
	private PopupPanel popup;

	public DesignWidgetWrapper(DesignWidgetWrapper designWidgetWrapper,Images images){
		super(designWidgetWrapper);
		
		this.widgetSelectionListener = designWidgetWrapper.widgetSelectionListener;
		this.popup = designWidgetWrapper.popup;
		
		if(widgetSelectionListener == null)
			widget.getElement();

		initWidget();
	}
	
	protected void copyWidget(WidgetEx widget){
		if(widget.getWrappedWidget() instanceof DesignGroupWidget)
			this.widget = new DesignGroupWidget((DesignGroupWidget)widget.getWrappedWidget(),((DesignGroupWidget)widget.getWrappedWidget()).getImages(),((DesignGroupWidget)widget.getWrappedWidget()).getWidgetPopupMenuListener());
		super.copyWidget(widget);
	}

	private void initWidget(){
		if(!(widget instanceof TabBar)){
			panel.add(widget);
			initWidget(panel);
			DOM.sinkEvents(getElement(),DOM.getEventsSunk(getElement()) | Event.MOUSEEVENTS | Event.ONCONTEXTMENU | Event.KEYEVENTS);
		}
	}

	public DesignWidgetWrapper(Widget widget,PopupPanel popup,WidgetSelectionListener widgetSelectionListener) {
		super();
		
		this.widget = widget;
		this.popup = popup;
		this.widgetSelectionListener = widgetSelectionListener;
		if(widgetSelectionListener == null)
			widget.getElement();

		initWidget();
	}

	public void onBrowserEvent(Event event) {
		int type = DOM.eventGetType(event);

		switch (type) {
		case Event.ONCONTEXTMENU:
			widgetSelectionListener.onWidgetSelected(this);
			popup.setPopupPosition(event.getClientX(), event.getClientY());
			popup.show();
			break;
		case Event.ONMOUSEDOWN:
			if(!event.getCtrlKey())
				widgetSelectionListener.onWidgetSelected(this); //TODO verify that this does not introduce a bug
		case Event.ONMOUSEUP:
		case Event.ONMOUSEMOVE:
		case Event.ONMOUSEOVER:
		case Event.ONMOUSEOUT:
			if (mouseListeners != null) 
				mouseListeners.fireMouseEvent(this, event);
			/*if(type == Event.ONMOUSEDOWN){
		        	if(!(event.getShiftKey() || event.getCtrlKey()))
		        		widgetSelectionListener.onWidgetSelected(this);
		        }*/

			//if(type == Event.ONMOUSEDOWN || type == Event.ONMOUSEUP){
			if(widget instanceof RadioButton)
				((RadioButton)widget).setChecked(false);
			if(widget instanceof CheckBox)
				((CheckBox)widget).setChecked(false);
			//}
			break;
		case Event.ONKEYDOWN:
			break;
		}

		FormDesignerUtil.disableContextMenu(widget.getElement());
		DOM.eventCancelBubble(event, true);
	}

	public void addMouseListener(MouseListener listener) {
		if (mouseListeners == null) {
			mouseListeners = new MouseListenerCollection();
		}
		mouseListeners.add(listener);
	}

	public void removeMouseListener(MouseListener listener) {
		if (mouseListeners != null) {
			mouseListeners.remove(listener);
		}
	}

	public void setText(String text){
		if(widget instanceof RadioButton)
			((RadioButton)widget).setText(text);
		else if(widget instanceof CheckBox)
			((CheckBox)widget).setText(text);
		else if(widget instanceof Button)
			((Button)widget).setText(text);
		else if(widget instanceof Label)
			((Label)widget).setText(text);
		else if(widget instanceof Hyperlink)
			((Hyperlink)widget).setText(text);
		else if(widget instanceof TabBar && text != null && text.trim().length() > 0)
			//((TabBar)widget).setTabHTML(((TabBar)widget).getSelectedTab(), URL.encode(text));
			((TabBar)widget).setTabHTML(((TabBar)widget).getSelectedTab(), "<span style='white-space:nowrap'>" + text + "</span>");
	}

	public void setTitle(String title){
		if(widget instanceof RadioButton)
			((RadioButton)widget).setText(title);
		else if(widget instanceof CheckBox)
			((CheckBox)widget).setTitle(title);
		else if(widget instanceof Button)
			((Button)widget).setTitle(title);
		else if(widget instanceof ListBox)
			((ListBox)widget).setTitle(title);
		else if(widget instanceof TextArea)
			((TextArea)widget).setTitle(title);
		else if(widget instanceof DatePicker)
			((DatePicker)widget).setTitle(title);
		else if(widget instanceof TextBox)
			((TextBox)widget).setTitle(title);
		else if(widget instanceof Label)
			((Label)widget).setTitle(title);
		else if(widget instanceof Image)
			((Image)widget).setTitle(title);
		else if(widget instanceof Hyperlink)
			((Hyperlink)widget).setTitle(title);
		else if(widget instanceof DesignGroupWidget)
			((DesignGroupWidget)widget).setTitle(title);
	}

	public String getText(){
		if(widget instanceof RadioButton)
			return ((RadioButton)widget).getText();
		else if(widget instanceof CheckBox)
			return ((CheckBox)widget).getText();
		else if(widget instanceof Button)
			return ((Button)widget).getText();
		else if(widget instanceof Label)
			return ((Label)widget).getText();
		else if(widget instanceof TextArea)
			return ((TextArea)widget).getText();
		else if(widget instanceof TextBox)
			return ((TextBox)widget).getText();
		else if(widget instanceof Hyperlink)
			return ((Hyperlink)widget).getText();
		else if(widget instanceof TabBar)
			return DesignWidgetWrapper.getTabDisplayText(((TabBar)widget).getTabHTML(((TabBar)widget).getSelectedTab()));
		return null;
	}
	
	public static String getTabDisplayText(String html){
		if(html.indexOf("&lt") > 0)
			html = URL.decode(html);
		if(html.indexOf("</") > 0)
			html = html.substring(html.indexOf(">")+1,html.indexOf("</"));
		//s = URL.decode(s);
		return html;
	}

	public String getTitle(){
		if(widget instanceof RadioButton)
			return ((RadioButton)widget).getTitle();
		else if(widget instanceof CheckBox)
			return ((CheckBox)widget).getTitle();
		else if(widget instanceof Button)
			return ((Button)widget).getTitle();
		else if(widget instanceof ListBox)
			return ((ListBox)widget).getTitle();
		else if(widget instanceof TextArea)
			return ((TextArea)widget).getTitle();
		else if(widget instanceof DatePicker)
			return ((DatePicker)widget).getTitle();
		else if(widget instanceof TextBox)
			return ((TextBox)widget).getTitle();
		else if(widget instanceof Label)
			return ((Label)widget).getTitle();
		else if(widget instanceof Image)
			return ((Image)widget).getTitle();
		else if(widget instanceof Hyperlink)
			return ((Hyperlink)widget).getTitle();
		else if(widget instanceof DesignGroupWidget)
			return ((DesignGroupWidget)widget).getTitle();
		return null;
	}

	public String getWidgetName(){
		if(widget instanceof RadioButton)
			return WidgetEx.WIDGET_TYPE_RADIOBUTTON;
		else if(widget instanceof CheckBox)
			return WidgetEx.WIDGET_TYPE_CHECKBOX;
		else if(widget instanceof Button)
			return WidgetEx.WIDGET_TYPE_BUTTON;
		else if(widget instanceof ListBox)
			return WidgetEx.WIDGET_TYPE_LISTBOX;
		else if(widget instanceof TextArea)
			return WidgetEx.WIDGET_TYPE_TEXTAREA;
		else if(widget instanceof DatePicker)
			return WidgetEx.WIDGET_TYPE_DATEPICKER;
		else if(widget instanceof TextBox)
			return WidgetEx.WIDGET_TYPE_TEXTBOX;
		else if(widget instanceof Label)
			return WidgetEx.WIDGET_TYPE_LABEL;
		else if(widget instanceof Image)
			return WidgetEx.WIDGET_TYPE_IMAGE;
		else if(widget instanceof Hyperlink)
			return WidgetEx.WIDGET_TYPE_VIDEO_AUDIO;
		else if(widget instanceof DesignGroupWidget)
			return WidgetEx.WIDGET_TYPE_GROUPBOX;
		return null;
	}

	public void storePosition(){
		super.storePosition();
		
		if(widget instanceof DesignGroupWidget)
			((DesignGroupWidget)widget).storePosition();
	}

	public void setWidthInt(int width){
		setWidth(width+"px");
	}

	public void setHeightInt(int height){
		setHeight(height+"px");
	}

	public int getWidthInt(){
		return FormUtil.convertDimensionToInt(getWidth());
	}

	public int getHeightInt(){
		return FormUtil.convertDimensionToInt(getHeight());
	}

	public int getLeftInt(){
		return FormUtil.convertDimensionToInt(getLeft());
	}

	public int getTopInt(){
		return FormUtil.convertDimensionToInt(getTop());
	}

	public void setLeftInt(int left){
		setLeft(left+"px");
	}

	public void setTopInt(int top){
		setTop(top+"px");
	}

	public boolean isWidgetInRect(int left, int top, int right, int bottom){
		int x = FormUtil.convertDimensionToInt(getLeft());
		int y = FormUtil.convertDimensionToInt(getTop());
		return (x > left && x < right && y > top && y < bottom) ||
		(x > right && x < left && y > bottom && y < top);
	}

	public int getTabIndex(){
		//return tabIndex;
		if(widget instanceof RadioButton)
			return ((RadioButton)widget).getTabIndex();
		else if(widget instanceof CheckBox)
			return ((CheckBox)widget).getTabIndex();
		else if(widget instanceof Button)
			return ((Button)widget).getTabIndex();
		else if(widget instanceof ListBox)
			return ((ListBox)widget).getTabIndex();
		else if(widget instanceof TextArea)
			return ((TextArea)widget).getTabIndex();
		else if(widget instanceof DatePicker)
			return ((DatePicker)widget).getTabIndex();
		else if(widget instanceof TextBox)
			return ((TextBox)widget).getTabIndex();
		else if(widget instanceof DesignGroupWidget)
			return ((DesignGroupWidget)widget).getTabIndex();
		return 0;
	}

	public void setTabIndex(int index){
		this.tabIndex = index;

		if(widget instanceof RadioButton)
			((RadioButton)widget).setTabIndex(index);
		else if(widget instanceof CheckBox)
			((CheckBox)widget).setTabIndex(index);
		else if(widget instanceof Button)
			((Button)widget).setTabIndex(index);
		else if(widget instanceof ListBox)
			((ListBox)widget).setTabIndex(index);
		else if(widget instanceof TextArea)
			((TextArea)widget).setTabIndex(index);
		else if(widget instanceof DatePicker)
			((DatePicker)widget).setTabIndex(index);
		else if(widget instanceof TextBox)
			((TextBox)widget).setTabIndex(index);
		else if(widget instanceof DesignGroupWidget)
			((DesignGroupWidget)widget).setTabIndex(index);
	}

	public void buildLayoutXml(Element parent, com.google.gwt.xml.client.Document doc){
		Element node = doc.createElement("Item");
		parent.appendChild(node);			 
		node.setAttribute(WidgetEx.WIDGET_PROPERTY_WIDGETTYPE, getWidgetName());

		String value = getText();
		if(value != null && value.trim().length() > 0)
			node.setAttribute(WidgetEx.WIDGET_PROPERTY_TEXT, value);
		else
			node.removeAttribute(WidgetEx.WIDGET_PROPERTY_TEXT);

		value = getTitle();
		if(value != null && value.trim().length() > 0)
			node.setAttribute(WidgetEx.WIDGET_PROPERTY_HELPTEXT, value);
		else
			node.removeAttribute(WidgetEx.WIDGET_PROPERTY_HELPTEXT);

		value = getBinding();
		if(value != null && value.trim().length() > 0)
			node.setAttribute(WidgetEx.WIDGET_PROPERTY_BINDING, value);
		else
			node.removeAttribute(WidgetEx.WIDGET_PROPERTY_BINDING);

		value = getParentBinding();
		if(value != null && value.trim().length() > 0)
			node.setAttribute(WidgetEx.WIDGET_PROPERTY_PARENTBINDING, value);
		else
			node.removeAttribute(WidgetEx.WIDGET_PROPERTY_PARENTBINDING);

		node.setAttribute(WidgetEx.WIDGET_PROPERTY_LEFT, getLeft());
		node.setAttribute(WidgetEx.WIDGET_PROPERTY_TOP, getTop());

		value = getWidth();
		if(value != null && value.trim().length() > 0)
			node.setAttribute(WidgetEx.WIDGET_PROPERTY_WIDTH, value);
		else
			node.removeAttribute(WidgetEx.WIDGET_PROPERTY_WIDTH);

		value = getHeight();
		if(value != null && value.trim().length() > 0)
			node.setAttribute(WidgetEx.WIDGET_PROPERTY_HEIGHT, value);
		else
			node.removeAttribute(WidgetEx.WIDGET_PROPERTY_HEIGHT);

		node.setAttribute(WidgetEx.WIDGET_PROPERTY_TABINDEX, String.valueOf(getTabIndex()));

		//if(widget instanceof Label)
			buildLabelProperties(node);

		if(widget instanceof DesignGroupWidget)
			((DesignGroupWidget)widget).buildLayoutXml(node, doc);
		
		if(isRepeated())
			node.setAttribute(WidgetEx.WIDGET_PROPERTY_REPEATED, WidgetEx.REPEATED_TRUE_VALUE);
		else
			node.removeAttribute(WidgetEx.WIDGET_PROPERTY_REPEATED);
		
		value = getExternalSource();
		if(value != null && value.trim().length() > 0)
			node.setAttribute(WidgetEx.WIDGET_PROPERTY_EXTERNALSOURCE, value);
		else
			node.removeAttribute(WidgetEx.WIDGET_PROPERTY_EXTERNALSOURCE);
		
		value = getDisplayField();
		if(value != null && value.trim().length() > 0)
			node.setAttribute(WidgetEx.WIDGET_PROPERTY_DISPLAYFIELD, value);
		else
			node.removeAttribute(WidgetEx.WIDGET_PROPERTY_DISPLAYFIELD);
		
		value = getValueField();
		if(value != null && value.trim().length() > 0)
			node.setAttribute(WidgetEx.WIDGET_PROPERTY_VALUEFIELD, value);
		else
			node.removeAttribute(WidgetEx.WIDGET_PROPERTY_VALUEFIELD);
	}

	private void buildLabelProperties(Element node){
		String value = getForeColor();
		if(value != null && value.trim().length() > 0)
			node.setAttribute("color", value);
		else
			node.removeAttribute("color");

		value = getFontWeight();
		if(value != null && value.trim().length() > 0)
			node.setAttribute("fontWeight", value);
		else
			node.removeAttribute("fontWeight");

		value = getFontStyle();
		if(value != null && value.trim().length() > 0)
			node.setAttribute("fontStyle", value);
		else
			node.removeAttribute("fontStyle");

		value = getFontSize();
		if(value != null && value.trim().length() > 0)
			node.setAttribute("fontSize", value);
		else
			node.removeAttribute("fontSize");
		
		value = getFontFamily();
		if(value != null && value.trim().length() > 0)
			node.setAttribute("fontFamily", value);
		else
			node.removeAttribute("fontFamily");

		value = getTextDecoration();
		if(value != null && value.trim().length() > 0)
			node.setAttribute("textDecoration", value);
		else
			node.removeAttribute("textDecoration");

		value = getBackgroundColor();
		if(value != null && value.trim().length() > 0)
			node.setAttribute("backgroundColor", value);
		else
			node.removeAttribute("backgroundColor");

		value = getBorderStyle();
		if(value != null && value.trim().length() > 0)
			node.setAttribute("borderStyle", value);
		else
			node.removeAttribute("borderStyle");

		value = getBorderWidth();
		if(value != null && value.trim().length() > 0)
			node.setAttribute("borderWidth", value);
		else
			node.removeAttribute("borderWidth");

		value = getBorderColor();
		if(value != null && value.trim().length() > 0)
			node.setAttribute("borderColor", value);
		else
			node.removeAttribute("borderColor");	
	}

	public FormDesignerDragController getDragController(){
		if(widget instanceof DesignGroupWidget)
			return ((DesignGroupWidget)widget).getDragController();
		return null;
	}

	public AbsolutePanel getPanel(){
		if(widget instanceof DesignGroupWidget)
			return ((DesignGroupWidget)widget).getPanel();
		return null;
	}
	
	public PopupPanel getWidgetPopup(){
		if(widget instanceof DesignGroupWidget)
			return ((DesignGroupWidget)widget).getWidgetPopup();
		return null;
	}
	
	public void setQuestionDef(QuestionDef questionDef){
		this.questionDef = questionDef;
		questionDef.addChangeListener(this);
	}

	public void onBindingChanged(String newValue) {
		this.binding = newValue;
		
	}

	public void onEnabledChanged(boolean enabled) {
		// TODO Auto-generated method stub
		
	}

	public void onLockedChanged(boolean locked) {
		// TODO Auto-generated method stub
		
	}

	public void onRequiredChanged(boolean required) {
		// TODO Auto-generated method stub
		
	}

	public void onVisibleChanged(boolean visible) {
		// TODO Auto-generated method stub
		
	}	
	
	public void onDataTypeChanged(int dataType){
		if(dataType == QuestionDef.QTN_TYPE_DATE || dataType == QuestionDef.QTN_TYPE_DATE_TIME){
			if(!(widget instanceof DatePicker)){
				panel.remove(widget);
				widget = new DatePickerWidget();
				panel.add(widget);
				refreshSize();
			}
		}
		else if(dataType == QuestionDef.QTN_TYPE_TEXT || dataType == QuestionDef.QTN_TYPE_NUMERIC || dataType == QuestionDef.QTN_TYPE_DECIMAL){
			if(( !(widget instanceof TextBox || widget instanceof TextArea) || (widget instanceof DatePicker)) ){
				panel.remove(widget);
				widget = new TextBox();
				panel.add(widget);
				refreshSize();
			}
		}
		else if(dataType == QuestionDef.QTN_TYPE_BOOLEAN){
			if(!(widget instanceof ListBox || widget instanceof CheckBox)){
				panel.remove(widget);
				widget = new ListBox(false);
				panel.add(widget);
				refreshSize();
			}
		}
		else if(dataType == QuestionDef.QTN_TYPE_LIST_EXCLUSIVE){
			if(!(widget instanceof ListBox || widget instanceof RadioButton)){
				panel.remove(widget);
				widget = new ListBox(false);
				panel.add(widget);
				refreshSize();
			}
		}
		else if(dataType == QuestionDef.QTN_TYPE_IMAGE){
			if(!(widget instanceof Image)){
				panel.remove(widget);
				widget = new Image();
				panel.add(widget);
				refreshSize();
			}
		}
		else if(dataType == QuestionDef.QTN_TYPE_VIDEO || dataType == QuestionDef.QTN_TYPE_VIDEO){
			if(!(widget instanceof Hyperlink)){
				panel.remove(widget);
				widget = new Hyperlink();
				((Hyperlink)widget).setText("Click to play");
				panel.add(widget);
				refreshSize();
			}
		}
	}
	
	public void onOptionsChanged(List<OptionDef> optionList){
		
	}
}