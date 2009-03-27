package org.purc.purcforms.client.view;

import java.util.Vector;

import org.purc.purcforms.client.controller.IFormSelectionListener;
import org.purc.purcforms.client.controller.WidgetSelectionListener;
import org.purc.purcforms.client.model.FormDef;
import org.purc.purcforms.client.model.OptionDef;
import org.purc.purcforms.client.model.QuestionDef;
import org.purc.purcforms.client.util.FormDesignerUtil;
import org.purc.purcforms.client.util.StyleUtil;
import org.purc.purcforms.client.widget.DesignWidgetWrapper;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusListenerAdapter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestionEvent;
import com.google.gwt.user.client.ui.SuggestionHandler;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;


/**
 * Contains the model of the displayed XForms document.
 * 
 * @author daniel
 *
 */
public class WidgetPropertiesView extends Composite implements WidgetSelectionListener, IFormSelectionListener{

	private FlexTable table = new FlexTable(); //Grid(7,2);
	private ScrollPanel scrollPanel = new ScrollPanel();
	private DesignWidgetWrapper widget;

	private TextBox txtText = new TextBox();
	private TextBox txtBinding = new TextBox();
	private TextBox txtChildBinding = new TextBox();
	private CheckBox chkEnabled = new CheckBox();
	private CheckBox chkVisible = new CheckBox();
	private TextBox txtWidth = new TextBox();
	private TextBox txtHeight = new TextBox();
	private TextBox txtLeft = new TextBox();
	private TextBox txtTop = new TextBox();
	private TextBox txtHelpText = new TextBox();
	private SuggestBox sgstBinding = new SuggestBox(new MultiWordSuggestOracle(),txtBinding);
	private SuggestBox sgstChildBinding = new SuggestBox(new MultiWordSuggestOracle(),txtChildBinding);
	private TextBox txtTabIndex = new TextBox();

	private TextBox txtForeColor  = new TextBox();
	private TextBox txtBackgroundColor  = new TextBox();
	private TextBox txtBorderColor  = new TextBox();
	private SuggestBox sgstForeColor = new SuggestBox(new MultiWordSuggestOracle(),txtForeColor);
	private SuggestBox sgstBackgroundColor;
	private SuggestBox sgstBorderColor;
	private ListBox lbFontWeight = new ListBox(false);
	private ListBox lbFontStyle = new ListBox(false);
	private TextBox txtFontSize= new TextBox();
	private TextBox txtFontFamily= new TextBox();
	private ListBox lbTextDecoration = new ListBox(false);
	private ListBox lbBorderStyle = new ListBox(false);
	private TextBox txtBorderWidth = new TextBox();
	private ListBox cbRepeat = new ListBox(false);
	private TextBox txtExternalSource = new TextBox();
	private TextBox txtDisplayField = new TextBox();
	private TextBox txtValueField = new TextBox();

	private FormDef formDef;
	QuestionDef questionDef;

	public WidgetPropertiesView() {

		initStyles();

		int index = -1;
		table.setWidget(++index, 0, new Label("Text"));
		table.setWidget(++index, 0, new Label("Tooltip"));
		table.setWidget(++index, 0, new Label("Binding"));
		table.setWidget(++index, 0, new Label("Child Binding"));
		table.setWidget(++index, 0, new Label("Width"));
		table.setWidget(++index, 0, new Label("Height"));
		table.setWidget(++index, 0, new Label("Enabled"));
		table.setWidget(++index, 0, new Label("Visible"));
		table.setWidget(++index, 0, new Label("Left"));
		table.setWidget(++index, 0, new Label("Top"));
		table.setWidget(++index, 0, new Label("Tab Index"));
		table.setWidget(++index, 0, new Label("Repeat"));

		table.setWidget(++index, 0, new Label("External Source"));
		table.setWidget(++index, 0, new Label("Display Field"));
		table.setWidget(++index, 0, new Label("Value Field"));

		table.setWidget(++index, 0, new Label("Font Family"));
		table.setWidget(++index, 0, new Label("Fore Color"));
		table.setWidget(++index, 0, new Label("Font Weight"));
		table.setWidget(++index, 0, new Label("Font Style"));
		table.setWidget(++index, 0, new Label("Font Size"));
		table.setWidget(++index, 0, new Label("Text Decoration"));
		table.setWidget(++index, 0, new Label("Background Color"));
		table.setWidget(++index, 0, new Label("Border Style"));
		table.setWidget(++index, 0, new Label("Border Width"));
		table.setWidget(++index, 0, new Label("Border Color"));

		index = -1;
		table.setWidget(++index, 1,txtText );
		table.setWidget(++index, 1,txtHelpText );
		table.setWidget(++index, 1, sgstBinding);
		table.setWidget(++index, 1, sgstChildBinding);
		table.setWidget(++index, 1,txtWidth);
		table.setWidget(++index, 1,txtHeight);
		table.setWidget(++index, 1, chkEnabled);
		table.setWidget(++index, 1, chkVisible);
		table.setWidget(++index, 1, txtLeft);
		table.setWidget(++index, 1, txtTop);
		table.setWidget(++index, 1, txtTabIndex);
		table.setWidget(++index, 1, cbRepeat);

		table.setWidget(++index, 1, txtExternalSource);
		table.setWidget(++index, 1, txtDisplayField);
		table.setWidget(++index, 1, txtValueField);

		table.setWidget(++index, 1, txtFontFamily);
		table.setWidget(++index, 1, sgstForeColor);
		table.setWidget(++index, 1, lbFontWeight);
		table.setWidget(++index, 1, lbFontStyle);
		table.setWidget(++index, 1, txtFontSize);
		table.setWidget(++index, 1, lbTextDecoration);
		table.setWidget(++index, 1, sgstBackgroundColor);
		table.setWidget(++index, 1, lbBorderStyle);
		table.setWidget(++index, 1, txtBorderWidth);
		table.setWidget(++index, 1, sgstBorderColor);

		txtText.setWidth("100%");
		txtHelpText.setWidth("100%");
		txtChildBinding.setWidth("100%");
		txtBinding.setWidth("100%");
		txtWidth.setWidth("100%");
		txtHeight.setWidth("100%");
		txtLeft.setWidth("100%");
		txtTop.setWidth("100%");
		sgstChildBinding.setWidth("100%");
		sgstBinding.setWidth("100%");
		txtTabIndex.setWidth("100%");
		cbRepeat.setWidth("100%");
		txtExternalSource.setWidth("100%");
		txtDisplayField.setWidth("100%");
		txtValueField.setWidth("100%");

		sgstForeColor.setWidth("100%");
		lbFontWeight.setWidth("100%");
		lbFontStyle.setWidth("100%");
		txtFontSize.setWidth("100%");
		txtFontFamily.setWidth("100%");
		lbTextDecoration.setWidth("100%");
		sgstBackgroundColor.setWidth("100%");
		lbBorderStyle.setWidth("100%");
		txtBorderWidth.setWidth("100%");
		sgstBorderColor.setWidth("100%");

		table.setStyleName("cw-FlexTable");
		table.setWidth("100%");
		FlexCellFormatter cellFormatter = table.getFlexCellFormatter();
		cellFormatter.setWidth(0, 0, "30%");

		for(int i=0; i<table.getRowCount(); i++)
			cellFormatter.setHorizontalAlignment(i, 0, HasHorizontalAlignment.ALIGN_RIGHT);

		scrollPanel.setWidget(table);
		initWidget(scrollPanel);
		setupEvents();
		txtChildBinding.setEnabled(false);

		FormDesignerUtil.allowNumericOnly(txtWidth,false);
		FormDesignerUtil.allowNumericOnly(txtHeight,false);
		FormDesignerUtil.allowNumericOnly(txtLeft,false);
		FormDesignerUtil.allowNumericOnly(txtTop,false);
		FormDesignerUtil.allowNumericOnly(txtTabIndex,false);

		enableLabelProperties(false);

		cbRepeat.addItem("true");
		cbRepeat.addItem("false");
	}

	private void initStyles(){
		StyleUtil.loadColorNames((MultiWordSuggestOracle)sgstForeColor.getSuggestOracle());
		sgstBackgroundColor = new SuggestBox(sgstForeColor.getSuggestOracle(),txtBackgroundColor);
		sgstBorderColor = new SuggestBox(sgstForeColor.getSuggestOracle(),txtBorderColor);

		StyleUtil.loadFontWeights(lbFontWeight);
		StyleUtil.loadFontStyles(lbFontStyle);
		StyleUtil.loadTextDecoration(lbTextDecoration);
		StyleUtil.loadBorderStyles(lbBorderStyle);
	}

	private void setupEvents(){
		txtText.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateText();
			}
		});

		txtText.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				updateText();
			}
		});

		txtHelpText.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateHelpText();
			}
		});
		txtHelpText.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				updateHelpText();
			}
		});

		txtWidth.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateWidth();
			}
		});
		txtWidth.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				updateWidth();
			}
		});

		txtHeight.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateHeight();
			}
		});
		txtHeight.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				updateHeight();
			}
		});

		txtLeft.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateLeft();
			}
		});
		txtLeft.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				updateLeft();
			}
		});

		txtTop.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateTop();
			}
		});
		txtTop.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				updateTop();
			}
		});

		/*txtBinding.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateBinding();
			}
		});*/
		txtChildBinding.addFocusListener(new FocusListenerAdapter(){
			public void onFocus(Widget sender){
				txtChildBinding.selectAll();
			}
		});

		txtBinding.addFocusListener(new FocusListenerAdapter(){
			public void onFocus(Widget sender){
				txtBinding.selectAll();
			}
		});

		sgstBinding.addEventHandler(new SuggestionHandler(){
			public void onSuggestionSelected(SuggestionEvent event){
				updateBinding();
			}
		});

		sgstChildBinding.addEventHandler(new SuggestionHandler(){
			public void onSuggestionSelected(SuggestionEvent event){
				updateChildBinding();
			}
		});

		txtTabIndex.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateTabIndex();
			}
		});

		txtTabIndex.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				updateTabIndex();
			}
		});

		txtExternalSource.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateExternalSource();
			}
		});

		cbRepeat.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateIsRepeat();
			}
		});

		txtExternalSource.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				updateExternalSource();
			}
		});

		txtDisplayField.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateDisplayField();
			}
		});

		txtDisplayField.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				updateDisplayField();
			}
		});

		txtValueField.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				updateValueField();
			}
		});

		txtValueField.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				updateValueField();
			}
		});


		txtForeColor.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				widget.setForeColor(txtForeColor.getText());
			}
		});
		sgstForeColor.addEventHandler(new SuggestionHandler(){
			public void onSuggestionSelected(SuggestionEvent event){
				widget.setForeColor(txtForeColor.getText());
			}
		});
		txtBackgroundColor.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				widget.setBackgroundColor(txtBackgroundColor.getText());
			}
		});
		sgstBackgroundColor.addEventHandler(new SuggestionHandler(){
			public void onSuggestionSelected(SuggestionEvent event){
				widget.setBackgroundColor(txtBackgroundColor.getText());
			}
		});
		txtBorderColor.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				widget.setBorderColor(txtBorderColor.getText());
			}
		});
		sgstBorderColor.addEventHandler(new SuggestionHandler(){
			public void onSuggestionSelected(SuggestionEvent event){
				widget.setBorderColor(txtBorderColor.getText());
			}
		});
		txtFontSize.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				widget.setFontSize(txtFontSize.getText());
			}
		});
		txtFontSize.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				widget.setFontSize(txtFontSize.getText());
			}
		});
		txtFontFamily.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				widget.setFontFamily(txtFontFamily.getText());
			}
		});
		txtFontFamily.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				widget.setFontFamily(txtFontFamily.getText());
			}
		});
		txtBorderWidth.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				widget.setBorderWidth(txtBorderWidth.getText());
			}
		});
		txtBorderWidth.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				widget.setBorderWidth(txtBorderWidth.getText());
			}
		});
		lbTextDecoration.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				widget.setTextDecoration(lbTextDecoration.getItemText(lbTextDecoration.getSelectedIndex()));
			}
		});
		lbFontStyle.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				widget.setFontStyle(lbFontStyle.getItemText(lbFontStyle.getSelectedIndex()));
			}
		});
		lbFontWeight.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				widget.setFontWeight(lbFontWeight.getItemText(lbFontWeight.getSelectedIndex()));
			}
		});
		lbBorderStyle.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender){
				widget.setBorderStyle(lbBorderStyle.getItemText(lbBorderStyle.getSelectedIndex()));
			}
		});
	}

	private void updateText(){
		if(widget != null && txtText.getText().trim().length() > 0) //No setting of empty strings as text.
			widget.setText(txtText.getText());
	}

	private void updateHelpText(){
		if(widget != null)
			widget.setTitle(txtHelpText.getText());
	}

	private void updateExternalSource(){
		if(widget != null)
			widget.setExternalSource(txtExternalSource.getText());
	}

	private void updateDisplayField(){
		if(widget != null)
			widget.setDisplayField(txtDisplayField.getText());
	}

	private void updateValueField(){
		if(widget != null)
			widget.setValueField(txtValueField.getText());
	}

	private void updateIsRepeat(){
		if(widget != null)
			widget.setRepeated(cbRepeat.getSelectedIndex() == 0);
	}

	private void updateChildBinding(){
		if(widget != null){
			OptionDef optionDef = questionDef.getOptionWithText(txtChildBinding.getText());
			if(optionDef == null){
				String text = txtChildBinding.getText();
				if("browse".equalsIgnoreCase(text) || "clear".equalsIgnoreCase(text))
					widget.setBinding(text);
				return;
			}

			widget.setBinding(optionDef.getVariableName());

			if(((widget.getWrappedWidget() instanceof RadioButton) && ((RadioButton)widget.getWrappedWidget()).getText().equals("RadioButton")) ||
					((widget.getWrappedWidget() instanceof CheckBox) && ((CheckBox)widget.getWrappedWidget()).getText().equals("CheckBox"))){
				txtText.setText(txtChildBinding.getText());
				updateText();
			}
			if(txtHelpText.getText().trim().length() == 0 || txtHelpText.getText().equals("CheckBox") || txtHelpText.getText().equals("RadioButton")){
				txtHelpText.setText(txtChildBinding.getText());
				updateHelpText();
			}
			/*String binding = null;
			if(hasParentBinding()){
				OptionDef optionDef = questionDef.getOptionWithText(txtBinding.getText());
				if(optionDef == null)
					return;
				binding = optionDef.getText();
			}
			else{*/			
		}
	}

	private void updateBinding(){
		txtChildBinding.setEnabled(false);
		if(widget != null && formDef != null){
			questionDef = formDef.getQuestionWithText(txtBinding.getText());
			if(questionDef == null){
				String text = txtBinding.getText();
				if(text.equals("submit")||text.equals("addnew")||text.equals("remove")
						||text.equals("browse")||text.equals("clear"))
					widget.setBinding(text);
				return;
			}

			//widget.setBinding(formDef.getQuestionBinding(questionDef));


			if(hasParentBinding()){
				widget.setParentBinding(questionDef.getVariableName());
				updateQuestionOptionsOracle();
			}
			else{
				widget.setBinding(questionDef.getVariableName());

				if((widget.getWrappedWidget() instanceof Label) && ((Label)widget.getWrappedWidget()).getText().equals("Label")){
					txtText.setText(txtBinding.getText());
					updateText();
				}
				if(txtHelpText.getText().trim().length() == 0 || txtHelpText.getText().equals("Label")){
					txtHelpText.setText(txtBinding.getText());
					updateHelpText();
				}
			}
		}
	}

	private void updateWidth(){
		if(widget != null && txtWidth.getText().trim().length() > 0)
			widget.setWidth(txtWidth.getText()+"px");
	}

	private void updateHeight(){
		if(widget != null && txtHeight.getText().trim().length() > 0)
			widget.setHeight(txtHeight.getText()+"px");
	}

	private void updateLeft(){
		if(widget != null && txtLeft.getText().trim().length() > 0)
			widget.setLeft(txtLeft.getText()+"px");
	}

	private void updateTop(){
		if(widget != null && txtTop.getText().trim().length() > 0)
			widget.setTop(txtTop.getText()+"px");
	}

	private void updateTabIndex(){
		if(widget != null && txtTabIndex.getText().trim().length() > 0)
			widget.setTabIndex(Integer.parseInt(txtTabIndex.getText()));
	}

	public void onWidgetSelected(DesignWidgetWrapper widget) {
		this.widget = widget;

		//Removed from here for smooth updateing where value has not changed
		/*txtText.setText(null);
		txtHelpText.setText(null);
		txtBinding.setText(null);
		txtHeight.setText(null);
		txtWidth.setText(null);
		chkVisible.setChecked(false);
		chkEnabled.setChecked(false);
		sgstBinding.setText(null);
		txtTop.setText(null);
		txtLeft.setText(null);*/

		if(widget != null){
			txtText.setText(widget.getText());

			if(widget.getWrappedWidget() instanceof TabBar)
				return;

			String value = widget.getHeight();
			if(value != null && value.trim().length() > 0)
				txtHeight.setText(value.substring(0, value.length()-2));
			else
				txtHeight.setText(null);

			value = widget.getWidth();
			if(value != null && value.trim().length() > 0)
				txtWidth.setText(value.substring(0, value.length()-2));
			else
				txtWidth.setText(null);

			value = widget.getTitle();
			if(value != null && value.trim().length() > 0)
				txtHelpText.setText(value);
			else
				txtHelpText.setText(txtText.getText());

			value = widget.getExternalSource();
			if(value != null && value.trim().length() > 0)
				txtExternalSource.setText(value);
			else
				txtExternalSource.setText(txtText.getText());

			value = widget.getDisplayField();
			if(value != null && value.trim().length() > 0)
				txtDisplayField.setText(value);
			else
				txtDisplayField.setText(txtText.getText());

			value = widget.getValueField();
			if(value != null && value.trim().length() > 0)
				txtValueField.setText(value);
			else
				txtValueField.setText(txtText.getText());

			cbRepeat.setSelectedIndex(widget.isRepeated() ? 0 : 1);

			txtChildBinding.setText(null);
			if(widget.getWrappedWidget() instanceof CheckBox || (widget.getWrappedWidget() instanceof Button &&
					"browse".equalsIgnoreCase(widget.getBinding())||"clear".equalsIgnoreCase(widget.getBinding()))){
				value = widget.getParentBinding();
				if(value != null && value.trim().length() > 0 && formDef != null){
					questionDef = formDef.getQuestion(value);
					if(questionDef != null)
						sgstBinding.setText(questionDef.getText()); 
					else if(value != null && value.equals("submit") && widget.getWrappedWidget() instanceof Button)
						txtBinding.setText(value);
					else
						txtBinding.setText(null);
				}
				else
					txtBinding.setText(null);

				value = widget.getBinding();
				if(questionDef != null && value != null && value.trim().length() > 0){
					OptionDef optionDef = questionDef.getOptionWithValue(value);
					if(optionDef != null)
						sgstChildBinding.setText(optionDef.getText()); 
					else if(widget.getWrappedWidget() instanceof Button)
						sgstChildBinding.setText(value);
					else
						txtChildBinding.setText(null);
				}
				else
					txtChildBinding.setText(null);
			}
			else{
				value = widget.getBinding();
				if(formDef != null){
					questionDef = formDef.getQuestion(value);
					if(questionDef != null)
						txtBinding.setText(questionDef.getText());
					else{
						if("submit".equalsIgnoreCase(value)||"addnew".equalsIgnoreCase(value)||"remove".equalsIgnoreCase(value)
								|| "browse".equalsIgnoreCase(value)||"clear".equalsIgnoreCase(value))
							txtBinding.setText(value);
						else
							txtBinding.setText(null);
					}
				}
				else
					txtBinding.setText(null);
			}

			value = widget.getLeft();
			if(value != null && value.trim().length() > 0)
				txtLeft.setText(value.substring(0, value.length()-2));
			else
				txtLeft.setText(null);

			value = widget.getTop();
			if(value != null && value.trim().length() > 0)
				txtTop.setText(value.substring(0, value.length()-2));
			else
				txtTop.setText(null);

			txtTabIndex.setText(String.valueOf(widget.getTabIndex()));

			txtChildBinding.setEnabled((hasParentBinding() && this.sgstBinding.getText().trim().length() > 0));

			if(!txtChildBinding.isEnabled())
				txtChildBinding.setText(null);

			enableLabelProperties(widget.getWrappedWidget() instanceof Label);
		}
		else{

			txtText.setText(null);
			txtHelpText.setText(null);
			txtBinding.setText(null);
			txtHeight.setText(null);
			txtWidth.setText(null);
			chkVisible.setChecked(false);
			chkEnabled.setChecked(false);
			sgstBinding.setText(null);
			sgstChildBinding.setText(null);
			txtTop.setText(null);
			txtLeft.setText(null);
			txtTabIndex.setText(null);
			txtExternalSource.setText(null);
			txtDisplayField.setText(null);
			txtValueField.setText(null);
			cbRepeat.setSelectedIndex(-1);
			enableLabelProperties(false);
		}
	}

	private boolean hasParentBinding(){
		return (widget.getWrappedWidget() instanceof RadioButton) || (widget.getWrappedWidget() instanceof CheckBox)
		|| (widget.getWrappedWidget() instanceof Button);
	}

	private void updateQuestionOptionsOracle(){
		MultiWordSuggestOracle oracle = (MultiWordSuggestOracle)sgstChildBinding.getSuggestOracle();
		oracle.clear();
		if(widget.getWrappedWidget() instanceof Button){
			oracle.add("browse");
			oracle.add("clear");
			txtChildBinding.setEnabled(true);
		}
		else{
			Vector options  = questionDef.getOptions();
			if(options != null){
				FormDesignerUtil.loadOptions(options,oracle);
				txtChildBinding.setEnabled(true);
			}
		}
	}

	public void setupFormDef(FormDef formDef){
		this.formDef = formDef;

		MultiWordSuggestOracle oracle = (MultiWordSuggestOracle)sgstBinding.getSuggestOracle();
		oracle.clear();
		for(int i=0; i<formDef.getPageCount(); i++)
			FormDesignerUtil.loadQuestions(formDef.getPageAt(i).getQuestions(),oracle);
		oracle.add("submit");
		oracle.add("addnew");
		oracle.add("remove");
		oracle.add("browse");
		oracle.add("clear");

		//sgstBinding.
		//table.remove(sgstBinding);
		//sgstBinding.r
		//sgstBinding = new SuggestBox(oracle,txtBinding);
		//table.setWidget(2, 1, sgstBinding);
		//selectFirstQuestion();



		/*sgstField.addFocusListener(new FocusListenerAdapter(){
			public void onLostFocus(Widget sender){
				stopSelection();
			}
		});*/
	}

	public void onFormItemSelected(Object formItem) {
		if(formItem == null){
			//enableQuestionOnlyProperties(false);
			//txtText.setEnabled(false);
			//txtDescTemplate.setEnabled(false);
			return;
		}

		if(formItem instanceof FormDef)
			setupFormDef((FormDef)formItem);
		/*else if(formItem instanceof PageDef)
			setPageProperties((PageDef)formItem);
		else if(formItem instanceof QuestionDef)
			setQuestionProperties((QuestionDef)formItem);
		else if(formItem instanceof OptionDef)
			setQuestionOptionProperties((OptionDef)formItem);*/
	}

	public void refresh(){
		setupFormDef(formDef);
	}

	private void enableLabelProperties(boolean enable){
		enable = true;

		txtForeColor.setEnabled(enable);
		lbFontWeight.setEnabled(enable);
		lbFontStyle.setEnabled(enable);
		txtFontSize.setEnabled(enable);
		txtFontFamily.setEnabled(enable);
		lbTextDecoration.setEnabled(enable);
		txtBackgroundColor.setEnabled(enable);
		lbBorderStyle.setEnabled(enable);
		txtBorderWidth.setEnabled(enable);
		txtBorderColor.setEnabled(enable);

		if(!enable){
			txtForeColor.setText(null);
			lbFontWeight.setSelectedIndex(-1);
			lbFontStyle.setSelectedIndex(-1);
			txtFontSize.setText(null);
			txtFontFamily.setText(null);
			lbTextDecoration.setSelectedIndex(-1);
			txtBackgroundColor.setText(null);
			lbBorderStyle.setSelectedIndex(-1);
			txtBorderWidth.setText(null);
			txtBorderColor.setText(null);
		}
		else if(widget != null){
			txtForeColor.setText(widget.getForeColor());
			StyleUtil.setFontWeightIndex(widget.getFontWeight(), lbFontWeight);
			StyleUtil.setFontStyleIndex(widget.getFontStyle(), lbFontStyle);
			txtFontSize.setText(widget.getFontSize());
			txtFontFamily.setText(widget.getFontFamily());
			StyleUtil.setTextDecorationIndex(widget.getTextDecoration(), lbTextDecoration);
			txtBackgroundColor.setText(widget.getBackgroundColor());
			StyleUtil.setBorderStyleIndex(widget.getBorderStyle(), lbBorderStyle);
			txtBorderWidth.setText(widget.getBorderWidth());
			txtBorderColor.setText(widget.getBorderColor());
		}
		else if(widget == null){
			txtForeColor.setText(null);
			lbFontWeight.setSelectedIndex(-1);
			lbFontStyle.setSelectedIndex(-1);
			txtFontSize.setText(null);
			txtFontFamily.setText(null);
			lbTextDecoration.setSelectedIndex(-1);
			txtBackgroundColor.setText(null);
			lbBorderStyle.setSelectedIndex(-1);
			txtBorderWidth.setText(null);
			txtBorderColor.setText(null);
		}
	}
}