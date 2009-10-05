package org.purc.purcforms.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.purc.purcforms.client.util.FormUtil;
import org.purc.purcforms.client.xforms.XformConverter;

import com.google.gwt.xml.client.Element;


/**
 * This class represents a validation rule which in xforms is a constraint attribute.
 * An example of such would be constraint=". &gt;= 0 and . &lt;= 150" where a value is 
 * required to be between 0 and 150.
 * 
 * @author daniel
 *
 */
public class ValidationRule implements Serializable{
	
	/** The unique identifier of the question referenced by this validation rule. */
	private int questionId = ModelConstants.NULL_ID;
	
	/** A list of conditions (Condition object) to be tested for a rule. 
	 * E.g. age is greater than 4. etc
	 */
	private Vector conditions;
	
	
	/** The validation rule name. */
	private String errorMessage;
	
	/** Operator for combining more than one condition. (And, Or) only these two for now. */
	private int conditionsOperator = ModelConstants.CONDITIONS_OPERATOR_NULL;
	
	/** The form to which the validation rule belongs. */
	private FormDef formDef;
	
	/** Constructs a rule object ready to be initialized. */
	public ValidationRule(FormDef formDef){
		this.formDef = formDef;
	}
	
	public ValidationRule(int questionId, FormDef formDef){
		this.questionId = questionId;
		this.formDef = formDef;
	}
	
	/** Copy constructor. */
	public ValidationRule(ValidationRule validationRule){
		setQuestionId(validationRule.getQuestionId());
		setErrorMessage(validationRule.getErrorMessage());
		setConditionsOperator(validationRule.getConditionsOperator());
		copyConditions(validationRule.getConditions());
		setFormDef(new FormDef(validationRule.getFormDef(),false));
	}
	
	/** Construct a Rule object from parameters. 
	 * 
	 * @param ruleId 
	 * @param conditions 
	 * @param action
	 * @param actionTargets
	 */
	public ValidationRule(int questionId, Vector conditions , String errorMessage) {
		setQuestionId(questionId);
		setConditions(conditions);
		setErrorMessage(errorMessage);
	}

	public Vector getConditions() {
		return conditions;
	}

	public void setConditions(Vector conditions) {
		this.conditions = conditions;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public int getConditionsOperator() {
		return conditionsOperator;
	}

	public void setConditionsOperator(int conditionsOperator) {
		this.conditionsOperator = conditionsOperator;
	}
	
	public Condition getConditionAt(int index) {
		if(conditions == null)
			return null;
		return (Condition)conditions.elementAt(index);
	}
	
	public int getConditionCount() {
		if(conditions == null)
			return 0;
		return conditions.size();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public FormDef getFormDef() {
		return formDef;
	}

	public void setFormDef(FormDef formDef) {
		this.formDef = formDef;
	}

	public void addCondition(Condition condition){
		if(conditions == null)
			conditions = new Vector();
		conditions.add(condition);
	}
	
	public boolean containsCondition(Condition condition){
		if(conditions == null)
			return false;
		return conditions.contains(condition);
	}
	
	public void updateCondition(Condition condition){
		for(int i=0; i<conditions.size(); i++){
			Condition cond = (Condition)conditions.elementAt(i);
			if(cond.getId() == condition.getId()){
				conditions.remove(i);
				conditions.add(condition);
				break;
			}
		}
	}
	
	public void removeCondition(Condition condition){
		conditions.remove(condition);
	}
	
	public void removeQuestion(QuestionDef questionDef){
		for(int index = 0; index < getConditionCount(); index++){
			Condition condition = getConditionAt(index);
			if(condition.getQuestionId() == questionDef.getId() || 
					(condition.getValueQtnDef() != null && condition.getValueQtnDef().getId() == questionDef.getId())){
				removeCondition(condition);
				index++;
			}
		}
	}
	
	public boolean isValid(){
		return isValid(formDef);
	}

	/** 
	 * Checks conditions of a rule and executes the corresponding actions
	 * 
	 * @param data
	 */
	public boolean isValid(FormDef formDef){
		boolean trueFound = false, falseFound = false;
		
		for(int i=0; i<getConditions().size(); i++){
			Condition condition = (Condition)this.getConditions().elementAt(i);
			if(condition.isTrue(formDef,true))
				trueFound = true;
			else
				falseFound = true;
		}
		
		if(getConditions().size() == 1 || getConditionsOperator() == ModelConstants.CONDITIONS_OPERATOR_AND)
			return !falseFound;
		else if(getConditionsOperator() == ModelConstants.CONDITIONS_OPERATOR_OR)
			return trueFound;
		
		return false;
	}
	
	private void copyConditions(Vector conditions){
		this.conditions = new Vector();
		for(int i=0; i<conditions.size(); i++)
			this.conditions.addElement(new Condition((Condition)conditions.elementAt(i)));
	}
	
	public void updateDoc(FormDef formDef){
		XformConverter.fromValidationRule2Xform(this,formDef);
	}

	//TODO This should be smarter
	public byte getMaxValue(FormDef formDef){
		if(conditions == null || conditions.size() == 0)
			return 127;
		String value = ((Condition)conditions.get(0)).getValue(formDef);
		if(value == null || value.trim().length() == 0)
			return 127;
		try{
			return Byte.parseByte(value);
		}
		catch(Exception ex){}
		return 127;
	}
	
	public void updateConditionValue(String origValue, String newValue){
		for(int index = 0; index < this.getConditionCount(); index++)
			getConditionAt(index).updateValue(origValue, newValue);
	}
	
	public void buildLanguageNodes(FormDef formDef, Element parentNode){
		QuestionDef questionDef = formDef.getQuestion(questionId);
		if(questionDef == null || questionDef.getBindNode() == null)
			return;
		
		Element node = formDef.getDoc().createElement(XformConverter.NODE_NAME_TEXT);
		String xpath = FormUtil.getNodePath(questionDef.getBindNode());
		xpath += "[@"+XformConverter.ATTRIBUTE_NAME_ID+"='"+ questionDef.getBindNode().getAttribute(XformConverter.ATTRIBUTE_NAME_ID)+"']";
		xpath += "[@"+XformConverter.ATTRIBUTE_NAME_CONSTRAINT_MESSAGE+"]";
		node.setAttribute(XformConverter.ATTRIBUTE_NAME_XPATH, xpath);
		node.setAttribute(XformConverter.ATTRIBUTE_NAME_VALUE, errorMessage);
		parentNode.appendChild(node);
	}
	
	public void refresh(FormDef dstFormDef, FormDef srcFormDef){
		
		QuestionDef qtn = srcFormDef.getQuestion(questionId);
		if(qtn == null)
			return; //can this question really disappear?
		
		//using variable name instead of id because id could have changed as more questions are
		//added or some deleted.
		QuestionDef questionDef = dstFormDef.getQuestion(qtn.getVariableName());
		if(questionDef == null)
			return; //possibly question for the validation rule has been deleted.
		
		ValidationRule validationRule = new ValidationRule(questionDef.getId(),dstFormDef);
		validationRule.setConditionsOperator(getConditionsOperator());
		validationRule.setErrorMessage(getErrorMessage());
		
		for(int index = 0; index < getConditionCount(); index++){
			Condition condition = getConditionAt(index);
			qtn = srcFormDef.getQuestion(condition.getQuestionId());
			if(qtn == null)
				continue;
			
			questionDef = dstFormDef.getQuestion(qtn.getVariableName());
			if(questionDef == null)
				continue;
			
			condition.setQuestionId(questionDef.getId());
			validationRule.addCondition(new Condition(condition));
		}
		
		if(validationRule.getConditionCount() > 0)
			dstFormDef.addValidationRule(validationRule);
	}
	
	/**
	 * Checks of a validation rule references a particualar question in any of its conditions.
	 * 
	 * @param questionDef the question to be referenced.
	 * @return true if it does, else false.
	 */
	public boolean hasQuestion(QuestionDef questionDef){
		if(conditions == null)
			return false;
		
		for(int i=0; i<conditions.size(); i++){
			Condition condition = (Condition)conditions.elementAt(i);
			if(condition.hasQuestion(questionDef, formDef))
				return true;
		}
		
		return false;
	}
	
	//TODO Why does the validation rule have a value of formDef different from the one passed in as parameter?
	public List<QuestionDef> getQuestions(FormDef formDef){
		if(conditions == null)
			return null;
		
		List<QuestionDef> questions = new ArrayList<QuestionDef>();
		
		for(int i=0; i<conditions.size(); i++){
			Condition condition = (Condition)conditions.elementAt(i);
			QuestionDef questionDef = condition.getQuestion(formDef);
			if(questionDef != null && !questions.contains(questionDef))
				questions.add(questionDef);
		}
		
		return questions;
	}
}