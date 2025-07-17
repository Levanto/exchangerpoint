package com.exchangerpoint.genericservices.util;

public class PropertyConditionMatcher {

	private String propertyName;
	private Object propertyValue;
	private String propertyCondition;
	private Object betweenOperatorLowValue;
	private Object betweenOperatorHighValue;
	private Object[] inOperatorValues;
	
	private static final String BETWEEN_OPERATOR = "between";
	private static final String IN_OPERATOR = "in";
	
	/**
     * This constructor is usable for implementing SQL operators on a property.
     * @param propertyName property being talked about
     * @param propertyCondition operator to be used, for example, 'isNull', 'isNotEmpty', etc.
     */
	public PropertyConditionMatcher(String propertyName, String propertyCondition) {
		this.propertyName = propertyName;
		this.propertyCondition = propertyCondition;
	}
	
	/**
     * This constructor is usable for implementing any mathematical operator on a property.
     * @param propertyName property being talked about
     * @param propertyCondition operator to be used, for example, '==', '!=', '>', '<=', etc.
     * @param propertyValue value of property being talked about
     */
	public PropertyConditionMatcher(String propertyName, String propertyCondition, Object propertyValue) {
		this.propertyName = propertyName;
		this.propertyCondition = propertyCondition;
		this.propertyValue = propertyValue;
	}
	
	/**
     * This constructor is usable for implementing between operator on a property.
     * @param propertyName property being talked about
     * @param betweenPropertyLowValue lower limit for between operator to work
     * @param betweenPropertyHighValue higher limit for between operator to work
     */
	public PropertyConditionMatcher(String propertyName, Object betweenPropertyLowValue, Object betweenPropertyHighValue) {
		this.propertyName = propertyName;
		this.propertyCondition = BETWEEN_OPERATOR;
		this.betweenOperatorLowValue = betweenPropertyLowValue;
		this.betweenOperatorHighValue = betweenPropertyHighValue;
	}
	
	/**
     * This constructor is usable for implementing in operator on a property.
     * @param propertyName property being talked about
     * @param values array of values
     */
	public PropertyConditionMatcher(String propertyName, Object... values) {
		this.propertyName = propertyName;
		this.propertyCondition = IN_OPERATOR;
		this.inOperatorValues = values;
	}
	
	public String getPropertyName() {
		return propertyName;
	}

	public Object getPropertyValue() {
		return propertyValue;
	}
	
	public String getPropertyCondition() {
		return propertyCondition;
	}

	public Object getBetweenPropertyLowValue() {
		return betweenOperatorLowValue;
	}

	public Object getBetweenPropertyHighValue() {
		return betweenOperatorHighValue;
	}

	public Object[] getInValues() {
		return inOperatorValues;
	}
}
