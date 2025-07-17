package com.exchangerpoint.genericservices.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import com.exchangerpoint.genericservices.exception.GenericException;
import com.exchangerpoint.genericservices.util.PropertyConditionMatcher;

public interface GenericDAO {

	/**
     * Gets an Object (entity) based on it's primary key value.
     * 
     * @param classType entity type
     * @param identifier value of it's primary key.
     */
	Object getObjectById(@SuppressWarnings("rawtypes") Class classType, Serializable identifier);
	
	/**
     * Gets list of Objects (entities) based on multiple conditions as defined in PropertyConditionMatcher and may or may not be ordered by column.
     * 
     * @param classType entity type
     * @param propertyConditionList list of various conditions, arithmetic or SQL or others as defined in objects of PropertyConditionMatcher in a list.
     * @param orderByColumnName name of a valid column of the above entity. This may be null or "" (empty).
     * @param order the order in which the list of objects is expected. This can be "asc" or "desc", else a db error may occur.
     */
	@SuppressWarnings("rawtypes")
	public List getObjectsByMultipleConditions(Class classType, 
			List<PropertyConditionMatcher> propertyConditionList, String orderByColumnName, String order);
	
	/**
     * Gets list of Objects (entities) based on equality conditions as defined in a map.
     * 
     * @param classType entity type
     * @param properties map of various equality conditions, for example, sellid = "OUNFDDTUKM" etc.
     */
	@SuppressWarnings("rawtypes")
	public List getObjectsByEqualityConditions(Class classType, Map<String, Object> properties);
	
	/**
     * Executes an SQL update query. It can be an SQL update or delete statement. 
     * 
     * @param queryString an actual SQL query.
     */
	public int executeSQLUpdate(String queryString);
	
	/**
     * Executes an SQL select query.
     * 
     * @param queryString an actual SQL select query.
     */
	@SuppressWarnings("rawtypes")
	public List performSelectQuery(String queryString);
	
	/**
     * Saves a new entry in a table of the type, passed as parameter.
     * 
     * @param object an entity object passed with values to be saved in database.
     */
	boolean save(Object object);
	
	/**
     * Saves or updates a new/existing entry in a table of the type, passed as parameter.
     * 
     * @param object an entity object passed with values to be saved in database.
     */
	boolean update(Object object);
	
	/**
     * Deletes an entry from a table of the type, passed as parameter.
     * 
     * @param object an entity object, that has to be deleted from the database.
     */
	boolean delete(Object object);
	
	/**
     * Gets list of objects from table, as matched with entity type passed as parameter. The objects are kept in order of retrieval.
     * 
     * @param classType an entity type, of which all objects are being retrieved.
     */
	@SuppressWarnings("rawtypes")
	List getList(Class classType);
	
	/**
     * Gets list of objects from table, as matched with entity type passed as parameter. The objects are kept in sorted order as passed in parameters.
     * This function works the same as getList(classType) if null or "" (empty) is passed in orderByColumnName.
     * 
     * @param orderByColumnName name of a valid column of the above entity. This may be null or "" (empty).
     * @param order the order in which the list of objects is expected. This can be "asc" or "desc", else a db error may occur.
     */
	@SuppressWarnings("rawtypes")
	List getOrderedList(Class classType, String orderByColumnName, String order);
	
	/**
	 * Gets list of objects from table, as matched with entity type passed as parameter and based on equality conditions passed in map.
	 * The objects are kept in sorted order as passed in parameters orderByColumnName and order.
	 * This function works the same as getList(classType) if null or "" (empty) is passed in orderByColumnName.
	 * 
	 * @param orderByColumnName name of a valid column of the above entity. This may be null or "" (empty).
	 * @param order the order in which the list of objects is expected. This can be "asc" or "desc", else a db error may occur.
	 */
	@SuppressWarnings("rawtypes")
	public List getOrderedList(Class classType, Map<String, Object> properties, String orderByColumnName, String order);
	
	/**
	 * Gets Objects based on Projections.
	 * 
	 * @param classType the entity type on which criteria is to be set.
	 * @param projectionColumns columns on which projection has to be added.
	 * @return list of projected objects from the database. This may be single columns, partial entities or complete entities.
	 */
	@SuppressWarnings("rawtypes")
	public List getObjectsUsingProjections(Class classType, String... projectionColumns);
	
	/**
	 * Applies projections on given columns to the given criteria.
	 * 
	 * @param criteria the criteria on which projections are to be added.
	 * @param projectionColumns columns for which projections are to be added.
	 * @return Criteria after applying provided projections.
	 */
	public Criteria applyProjectionsToCriteria(Criteria criteria, String... projectionColumns);
	
	/**
	 * Capable of applying projections, and conditions, order, all together in one place.
	 * 
	 * @param classType entity type
     * @param propertyConditionList list of various conditions, arithmetic or SQL or others as defined in objects of PropertyConditionMatcher in a list.
     * @param orderByColumnName name of a valid column of the above entity. This may be null or "" (empty).
     * @param order the order in which the list of objects is expected. This can be "asc" or "desc", else a db error may occur.
     * @param projectionColumns columns for which projections are to be added.
	 * @throws GenericException
	 */
	@SuppressWarnings("rawtypes")
	public List getObjectsHavingProjectedColumnsWithMultipleConditionsInOrder(Class classType, List<PropertyConditionMatcher> propertyConditionList, 
			String orderByColumnName, String order, String... projectionColumns) throws GenericException;
	
	/**
	 * Works similar to getObjectsByMultipleConditions() function.
	 * 
	 * @param classType entity type
     * @param propertyConditionList list of various conditions, arithmetic or SQL or others as defined in objects of PropertyConditionMatcher in a list.
     * @param orderByColumnName name of a valid column of the above entity. This may be null or "" (empty).
     * @param order the order in which the list of objects is expected. This can be "asc" or "desc", else a db error may occur.
	 * @return criteria is created and sent.
	 */
	public Criteria applyCriteriaByMultipleConditions(@SuppressWarnings("rawtypes") Class classType, 
			List<PropertyConditionMatcher> propertyConditionList, String orderByColumnName, String order) throws GenericException;

	/**
	 * Adds AND condition to a list of conditions and adds the result to a criteria.
	 * 
	 * @param cr Criteria being worked upon
     * @param propertiesList list of various conditions, arithmetic or SQL or others as defined in objects of PropertyConditionMatcher in a list.
	 * @return criteria is updated and resent.
	 */
	public Criteria addANDConditionToCriteria(Criteria cr, List<PropertyConditionMatcher> propertiesList) throws GenericException;

	/**
	 * Adds OR condition to a list of conditions and adds the result to a criteria.
	 * 
	 * @param cr Criteria being worked upon
     * @param propertiesList list of various conditions, arithmetic or SQL or others as defined in objects of PropertyConditionMatcher in a list.
	 * @return criteria is updated and resent.
	 */
	public Criteria addORConditionToCriteria(Criteria cr, List<PropertyConditionMatcher> propertiesList) throws GenericException;

	/**
	 * Creates AND criterion over a list of conditions.
	 * 
     * @param propertiesList list of various conditions, arithmetic or SQL or others as defined in objects of PropertyConditionMatcher in a list.
	 * @return criterion is created from the list of conditions.
	 */
	public Criterion createANDCriterion(List<PropertyConditionMatcher> propertiesList) throws GenericException;

	/**
	 * Creates OR criterion over a list of conditions.
	 * 
     * @param propertiesList list of various conditions, arithmetic or SQL or others as defined in objects of PropertyConditionMatcher in a list.
	 * @return criterion is created from the list of conditions.
	 */
	public Criterion createORCriterion(List<PropertyConditionMatcher> propertiesList) throws GenericException;

	/**
	 * Creates AND criterion from a list of criterions.
	 * 
     * @param criterions list of various criterions, arithmetic or SQL or others as created from a list of PropertyConditionMatcher.
	 * @return criterion is created from the list of criterions.
	 */
	public Criterion applyANDToCriterions(List<Criterion> criterions);
	
	/**
	 * Creates OR criterion from a list of criterions.
	 * 
     * @param criterions list of various criterions, arithmetic or SQL or others as created from a list of PropertyConditionMatcher.
	 * @return criterion is created from the list of criterions.
	 */
	public Criterion applyORToCriterions(List<Criterion> criterions);

	/**
	 * Creates a blank criteria
	 * 
     * @param classType the type of Class on which Criteria is to be created.
	 * @return criteria is created and returned.
	 */
	public Criteria createBlankCriteria(@SuppressWarnings("rawtypes") Class classType);

	/**
	 * Execute a criteria
	 * 
     * @param criteria the Criteria to be executed.
	 * @return list of results is returned.
	 */
	@SuppressWarnings("rawtypes")
	public List executeCriteria(Criteria criteria);
}