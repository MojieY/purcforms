package org.purc.purcforms.client.util;

import java.util.List;

import org.purc.purcforms.client.locale.LocaleText;
import org.purc.purcforms.client.model.OptionDef;
import org.purc.purcforms.client.view.ErrorDialog;
import org.purc.purcforms.client.view.ProgressDialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;


/**
 * 
 * @author daniel
 *
 */
public class FormUtil {
	
	private static DateTimeFormat dateTimeSubmitFormat;
	private static DateTimeFormat dateTimeDisplayFormat;
	private static DateTimeFormat timeSubmitFormat;
	private static DateTimeFormat timeDisplayFormat;
	
	private static String formDefDownloadUrlSuffix;
	private static String formDefUploadUrlSuffix;
	private static String entityFormDefDownloadUrlSuffix;
	private static String formDataUploadUrlSuffix;
	private static String afterSubmitUrlSuffix;
	private static String formDefRefreshUrlSuffix;
	private static String externalSourceUrlSuffix;
	
	private static String formIdName;
	private static String entityIdName;
	
	private static String formId;
	private static String entityId;
	
	private static String defaultFontFamily;
	
	private static boolean appendEntityIdAfterSubmit;
	
	private static boolean showLanguageTab = false;
	
	
	public static ProgressDialog dlg = new ProgressDialog();

	/**
	 * Maximizes a widget.
	 * 
	 * @param widget the widget to maximize.
	 */
	public static void maximizeWidget(Widget widget){
		widget.setSize("100%", "100%");
	}

	public static void allowNumericOnly(TextBox textBox, boolean allowDecimal){
		final boolean allowDecimalPoints = allowDecimal;
		textBox.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
				if ((!Character.isDigit(keyCode)) && (keyCode != (char) KeyboardListener.KEY_TAB)
						&& (keyCode != (char) KeyboardListener.KEY_BACKSPACE) && (keyCode != (char) KeyboardListener.KEY_LEFT)
						&& (keyCode != (char) KeyboardListener.KEY_UP) && (keyCode != (char) KeyboardListener.KEY_RIGHT)
						&& (keyCode != (char) KeyboardListener.KEY_DOWN)) {

					if(keyCode == '.' && allowDecimalPoints && !((TextBox)sender).getText().contains("."))
						return;
					
					String text = ((TextBox) sender).getText().trim();
					if(text.length() == 0 && keyCode == '-')
						return;
					
					((TextBox) sender).cancelKey(); 
				}
			}
		});
	}

	public static KeyboardListenerAdapter getAllowNumericOnlyKeyboardListener(TextBox textBox, boolean allowDecimal){
		final boolean allowDecimalPoints = allowDecimal;
		return new KeyboardListenerAdapter() {
			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
				if ((!Character.isDigit(keyCode)) && (keyCode != (char) KeyboardListener.KEY_TAB)
						&& (keyCode != (char) KeyboardListener.KEY_BACKSPACE) && (keyCode != (char) KeyboardListener.KEY_LEFT)
						&& (keyCode != (char) KeyboardListener.KEY_UP) && (keyCode != (char) KeyboardListener.KEY_RIGHT)
						&& (keyCode != (char) KeyboardListener.KEY_DOWN)) {

					if(keyCode == '.' && allowDecimalPoints && !((TextBox)sender).getText().contains("."))
						return;
					
					String text = ((TextBox) sender).getText().trim();
					if(text.length() == 0 && keyCode == '-')
						return;
					
					((TextBox) sender).cancelKey(); 
				}
			}
		};
	}

	public static void setWidgetPosition(Widget w, String left, String top) {
		com.google.gwt.user.client.Element h = w.getElement();
		DOM.setStyleAttribute(h, "position", "absolute");
		DOM.setStyleAttribute(h, "left", left);
		DOM.setStyleAttribute(h, "top", top);
	}

	public static void loadOptions(List options, MultiWordSuggestOracle oracle){
		if(options == null)
			return;
		
		for(int i=0; i<options.size(); i++){
			OptionDef optionDef = (OptionDef)options.get(i);
			oracle.add(optionDef.getText());	
		}
	}

	private static String indent(String text, int indentLevel) {
		for( int count = indentLevel ; count > 0 ; count--)
			text += "  ";

		return text;
	}

	/**
	 * Add formatting to an XML string
	 */
	
	public static String formatXml(String xmlContent){
		if(xmlContent == null)
			return null;
		
		return formatXmlPrivate(formatXmlPrivate(xmlContent));
	}
	
	private static String formatXmlPrivate(String xmlContent) {

		String result = "";

		try {
			String prevBeginSection = "";
			int prevIndex = 0;

			for(int indentLevel = 0, index = 0 ; index < xmlContent.length() ; index++) {

				//Seek to next "<"
				index = xmlContent.indexOf("<", index);

				if(index < 0 || index >= xmlContent.length())
					break;

				//Trim out XML block
				String section = xmlContent.substring(index, xmlContent.indexOf(">", index) + 1);

				if(section.matches("<!--.*-->")) {
					//Is comment <!--....-->
					result = indent(result, indentLevel);
				}
				else if(section.matches("<!.*>")) {
					//Directive
					result = indent(result, indentLevel);
				}
				else if(section.matches("<\\?.*\\?>")) {
					//Is directive <?...?>
					result = indent(result, indentLevel);
				}
				else if(section.matches("<[\\s]*[/\\\\].*>")) {
					//Is closing tag </...>
					result = indent(result, --indentLevel);
				}
				else if(section.matches("<.*[/\\\\][\\s]*>")) {
					//Is standalone tag <.../>
					result = indent(result, indentLevel);
					prevBeginSection = section;
				}
				else {
					//Is begin tag <....>
					result = indent(result, indentLevel++);
					prevBeginSection = section;
				}

				//My addition of making <> and </> be on same line and include text between
				//if(prevSection.equalsIgnoreCase(section.replace("/", ""))){
				//and we do this when we come accross a closing tag.
				if(section.matches("<[\\s]*[/\\\\].*>")) {
					if(prevIndex > 0){
						int len = 1+(indentLevel*2);
						if(result.substring(result.length()-len).contains("\n")){
							if(isClosingPreviousBeginTag(prevBeginSection,section))
								result = result.substring(0,result.length()-len);
							String s = xmlContent.substring(prevIndex+1,index);
							if(s.contains("\r\n")){
								if(!s.trim().equals(""))
									result += s.replace("\r\n", " ");
							}
							else if(s.contains("\n")){
								if(!s.trim().equals(""))
									result += s.replace("\n", " ");
							}
							else
								result += s;
							
							prevIndex = 0;
						}
					}
				}
				else
					prevIndex = xmlContent.indexOf(">", index);

				result += section + "\n";
			}
		}
		catch(StringIndexOutOfBoundsException s) {
			s.printStackTrace();
			return "Invalid XML";
		}

		return result;
	}

	private static boolean isClosingPreviousBeginTag(String prevBeginSection, String currentEndSection){
		int pos = prevBeginSection.indexOf(' ');
		if(pos < 0)
			pos = prevBeginSection.length()-1;
		String s = "</" + prevBeginSection.substring(1,pos) + ">";
		return s.equalsIgnoreCase(currentEndSection);
	}

	/**
	 * Sets up the uncaught exception handler.
	 *
	 */
	public static void setupUncaughtExceptionHandler(){

		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable throwable) {
				displayException(throwable);
				//throwable.printStackTrace();
				/*String text = LocaleText.get("uncaughtException");
				String s = text;
				while (throwable != null) {
					s = throwable.getMessage();
					StackTraceElement[] stackTraceElements = throwable.getStackTrace();
					text += throwable.toString() + "\n";
					for (int i = 0; i < stackTraceElements.length; i++) {
						text += "    at " + stackTraceElements[i] + "\n";
					}
					throwable = throwable.getCause();
					if (throwable != null) {
						text += LocaleText.get("CausedBy");
					}
				}
				ErrorDialog dialogBox = new ErrorDialog();
				dialogBox.setText(LocaleText.get("unexpectedFailure"));
				//DOM.setStyleAttribute(dialogBox.getElement(), "backgroundColor", "#ABCDEF");
				//System.err.print(text);
				//text = text.replaceAll(" ", "&nbsp;");
				dialogBox.setBody(s);//("<pre>" + text + "</pre>");
				dialogBox.setCallStack(text);
				dialogBox.center();*/
			}
		});
	}
	
	public static void retrieveUserDivParameters(){
		formDefDownloadUrlSuffix = getDivValue("formDefDownloadUrlSuffix");
		formDefUploadUrlSuffix = getDivValue("formDefUploadUrlSuffix");
		entityFormDefDownloadUrlSuffix = getDivValue("entityFormDefDownloadUrlSuffix");
		formDataUploadUrlSuffix = getDivValue("formDataUploadUrlSuffix");
		afterSubmitUrlSuffix = getDivValue("afterSubmitUrlSuffix");
		formDefRefreshUrlSuffix = getDivValue("formDefRefreshUrlSuffix");
		externalSourceUrlSuffix = getDivValue("externalSourceUrlSuffix");
		
		formIdName = getDivValue("formIdName");
		if(formIdName == null || formIdName.trim().length() == 0)
			formIdName = "formId";
		
		entityIdName = getDivValue("entityIdName");
		if(entityIdName == null || entityIdName.trim().length() == 0)
			entityIdName = "patientId";
		
		formId = getDivValue(formIdName);
		entityId = getDivValue(entityIdName);
		
		String format = getDivValue("dateTimeSubmitFormat");
		if(format != null && format.trim().length() > 0)
			setDateTimeSubmitFormat(format);
		
		format = getDivValue("dateTimeDisplayFormat");
		if(format != null && format.trim().length() > 0)
			setDateTimeDisplayFormat(format);
		
		format = getDivValue("timeDisplayFormat");
		if(format != null && format.trim().length() > 0)
			setTimeDisplayFormat(format);
		
		format = getDivValue("timeSubmitFormat");
		if(format != null && format.trim().length() > 0)
			setTimeSubmitFormat(format);
		
		defaultFontFamily = getDivValue("defaultFontFamily");
		if(defaultFontFamily == null || defaultFontFamily.trim().length() == 0)
			defaultFontFamily = "Verdana, 'Lucida Grande', 'Trebuchet MS', Arial, Sans-Serif";
	
		String s = getDivValue("appendEntityIdAfterSubmit");
		if(s == null || s.trim().length() == 0)
			appendEntityIdAfterSubmit = false;
		else
			appendEntityIdAfterSubmit = !s.equals("0");
		
		s = getDivValue("showLanguageTab");
		if(s != null && s.equals("1"))
			showLanguageTab = true;
	}
	
	public static String getDivValue(String id){
		RootPanel p = RootPanel.get(id);
		if(p != null){
			NodeList nodes = p.getElement().getChildNodes();
			Node node = nodes.getItem(0);
			String s = node.getNodeValue();
			p.getElement().removeChild(node);
			return s;
		 }
		
		return null;
	}
	
	public static void setDateTimeSubmitFormat(String format){
		dateTimeSubmitFormat = DateTimeFormat.getFormat(format);
	}
	
	public static DateTimeFormat getDateTimeSubmitFormat(){
		return dateTimeSubmitFormat;
	}
	
	public static void setDateTimeDisplayFormat(String format){
		dateTimeDisplayFormat = DateTimeFormat.getFormat(format);
	}
	
	public static DateTimeFormat getDateTimeDisplayFormat(){
		return dateTimeDisplayFormat;
	}
	
	public static void setTimeDisplayFormat(String format){
		timeDisplayFormat = DateTimeFormat.getFormat(format);
	}
	
	public static DateTimeFormat getTimeDisplayFormat(){
		return timeDisplayFormat;
	}
	
	public static void setTimeSubmitFormat(String format){
		timeSubmitFormat = DateTimeFormat.getFormat(format);
	}
	
	public static DateTimeFormat getTimeSubmitFormat(){
		return timeSubmitFormat;
	}
	
	public static String getFormDefDownloadUrlSuffix(){
		return formDefDownloadUrlSuffix;
	}
	
	public static String getFormDefUploadUrlSuffix(){
		return formDefUploadUrlSuffix;
	}
	
	public static String getEntityFormDefDownloadUrlSuffix(){
		return entityFormDefDownloadUrlSuffix;
	}
	
	public static String getFormDataUploadUrlSuffix(){
		return formDataUploadUrlSuffix;
	}
	
	public static String getAfterSubmitUrlSuffix(){
		return afterSubmitUrlSuffix;
	}
	
	public static String getFormDefRefreshUrlSuffix(){
		return formDefRefreshUrlSuffix;
	}
	
	public static String getExternalSourceUrlSuffix(){
		return externalSourceUrlSuffix;
	}
	
	public static String getFormIdName(){
		return formIdName;
	}
	
	public static String getEntityIdName(){
		return entityIdName;
	}
	
	public static String getFormId(){
		return formId;
	}
	
	public static String getEntityId(){
		return entityId;
	}
	
	public static boolean getShowLanguageTab(){
		return showLanguageTab;
	}
	
	public static String getHostPageBaseURL(){
		//return "http://127.0.0.1:8080/openmrs/";
		
		String s = GWT.getHostPageBaseURL();
		
		int pos = s.lastIndexOf(':');
		if(pos == -1)
			return s;
		
		pos = s.indexOf('/', pos+1);
		if(pos == -1)
			return s;
		
		pos = s.indexOf('/', pos+1);
		if(pos == -1)
			return s;
		
		return s.substring(0,pos+1);
	}
	
	public static String getDefaultFontFamily(){
		return defaultFontFamily;
	}
	
	public static boolean appendEntityIdAfterSubmit(){
		return appendEntityIdAfterSubmit;
	}
	
	public static void displayException(Throwable ex){
		ex.printStackTrace();

		String text = LocaleText.get("uncaughtException");
		String s = text;
		while (ex != null) {
			s = ex.getMessage();
			StackTraceElement[] stackTraceElements = ex.getStackTrace();
			text += ex.toString() + "\n";
			for (int i = 0; i < stackTraceElements.length; i++) {
				text += "    at " + stackTraceElements[i] + "\n";
			}
			ex = (Exception)ex.getCause();
			if (ex != null) {
				text += LocaleText.get("causedBy");
			}
		}

		ErrorDialog dialogBox = new ErrorDialog();
		dialogBox.setText(LocaleText.get("unexpectedFailure"));
		dialogBox.setBody(s);
		dialogBox.setCallStack(text);
		dialogBox.center();
	}
	
	public static int convertDimensionToInt(String dimension){
		if(dimension == null || dimension.trim().length() == 0)
			return 0;
		return Integer.parseInt(dimension.substring(0,dimension.length()-2));
	}
	
	public static String getNodePath(com.google.gwt.xml.client.Node node){
		String path = removePrefix(node.getNodeName());
		
		if(node.getNodeType() == Node.ELEMENT_NODE){
			com.google.gwt.xml.client.Node parent = node.getParentNode();
			while(parent != null && !(parent instanceof Document)){
				path = removePrefix(parent.getNodeName()) + "/" + path;
				parent = parent.getParentNode();
			}
		}
		
		return path;
	}
	
	private static String removePrefix(String name){
		int pos = name.indexOf(':');
		if(pos >= 0)
			name = name.substring(pos + 1);
		return name;
	}
	
	public static String getNodeName(Element node){
		return removePrefix(node.getNodeName());
	}
}