package com.exchangerpoint.genericservices.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.exchangerpoint.genericservices.exception.GenericException;
import com.exchangerpoint.genericservices.util.Constants;
import com.exchangerpoint.genericservices.util.PropertyConditionMatcher;

@Transactional
public class GenericDAOImpl implements GenericDAO {

	@Autowired
	@Qualifier(Constants.SESSION_FACTORY)
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Gets an Object (entity) based on it's primary key value.
	 * 
	 * @param classType entity type
	 * @param identifier value of it's primary key.
	 */
	@SuppressWarnings("unchecked")
	public Object getObjectById(@SuppressWarnings("rawtypes") Class classType, Serializable identifier) {
		Object object = null;
		try {
			object = sessionFactory.getCurrentSession().get(classType, identifier);
			return object;
		}
		catch(Exception e) {
			e.printStackTrace();
			return object;
		}
	}

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
			List<PropertyConditionMatcher> propertyConditionList, String orderByColumnName, String order) {

		try {
			Criteria cr = applyCriteriaByMultipleConditions(classType, propertyConditionList, orderByColumnName, order);

			return cr.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets list of Objects (entities) based on equality conditions as defined in a map.
	 * 
	 * @param classType entity type
	 * @param properties map of various equality conditions (COLUMN_NAME = VALUE), for example, sellid = "OUNFDDTUKM" etc.
	 */
	@SuppressWarnings("rawtypes")
	public List getObjectsByEqualityConditions(Class classType, Map<String, Object> properties) {
		Set propertiesSet = properties.keySet();
		Iterator propertiesSetIterator = propertiesSet.iterator();

		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(classType);

			while(propertiesSetIterator.hasNext()) {
				String key = (String) propertiesSetIterator.next();
				criteria.add(Restrictions.eq(key, properties.get(key)));
			}

			return criteria.list();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Executes an SQL update query. It can be an SQL update or delete statement. 
	 * 
	 * @param queryString an actual SQL query.
	 */
	public int executeSQLUpdate(String queryString) {
		TypedQuery query = sessionFactory.getCurrentSession().createSQLQuery(queryString);
		return query.executeUpdate();
	}

	/**
	 * Executes an SQL select query.
	 * 
	 * @param queryString an actual SQL select query.
	 */
	@SuppressWarnings("rawtypes")
	public List performSelectQuery(String queryString) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(queryString);
		return query.list();
	}

	/**
	 * Saves a new entry in a table of the type, passed as parameter.
	 * 
	 * @param object an entity object passed with values to be saved in database.
	 */
	public boolean save(Object object) {
		try {
			sessionFactory.getCurrentSession().save(object);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}	

	/**
	 * Saves or updates a new/existing entry in a table of the type, passed as parameter.
	 * 
	 * @param object an entity object passed with values to be saved in database.
	 */
	public boolean update(Object object) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(object);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes an entry from a table of the type, passed as parameter.
	 * 
	 * @param object an entity object, that has to be deleted from the database.
	 */
	public boolean delete(Object object) {
		try {
			sessionFactory.getCurrentSession().delete(object);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Gets list of objects from table, as matched with entity type passed as parameter. The objects are kept in order of retrieval.
	 * 
	 * @param classType an entity type, of which all objects are being retrieved.
	 */
	@SuppressWarnings("rawtypes")
	public List getList(Class classType) {

		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(classType);
			return criteria.list();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets list of objects from table, as matched with entity type passed as parameter. The objects are kept in sorted order as passed in parameters.
	 * This function works the same as getList(classType) if null or "" (empty) is passed in orderByColumnName.
	 * 
	 * @param orderByColumnName name of a valid column of the above entity. This may be null or "" (empty).
	 * @param order the order in which the list of objects is expected. This can be "asc" or "desc", else a db error may occur.
	 */
	@SuppressWarnings("rawtypes")
	public List getOrderedList(Class classType, String orderByColumnName, String order) {

		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(classType);

			criteria = getCriteriaWithOrder(criteria, orderByColumnName, order);

			return criteria.list();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets list of objects from table, as matched with entity type passed as parameter and based on equality conditions passed in map.
	 * The objects are kept in sorted order as passed in parameters orderByColumnName and order.
	 * This function works the same as getList(classType) if null or "" (empty) is passed in orderByColumnName.
	 * 
	 * @param orderByColumnName name of a valid column of the above entity. This may be null or "" (empty).
	 * @param order the order in which the list of objects is expected. This can be "asc" or "desc", else a db error may occur.
	 */
	@SuppressWarnings("rawtypes")
	public List getOrderedList(Class classType, Map<String, Object> properties, String orderByColumnName, String order) {

		Set propertiesSet = properties.keySet();
		Iterator propertiesSetIterator = propertiesSet.iterator();
		
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(classType);

			while(propertiesSetIterator.hasNext()) {
				String key = (String) propertiesSetIterator.next();
				criteria.add(Restrictions.eq(key, properties.get(key)));
			}
			
			criteria = getCriteriaWithOrder(criteria, orderByColumnName, order);

			return criteria.list();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets Objects based on Projections.
	 * 
	 * @param classType the entity type on which criteria is to be set.
	 * @param projectionColumns columns on which projection has to be added.
	 * @return list of projected objects from the database. This may be single columns, partial entities or complete entities.
	 */
	@SuppressWarnings("rawtypes")
	public List getObjectsUsingProjections(Class classType, String... projectionColumns) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(classType);
		return applyProjectionsToCriteria(criteria, projectionColumns).list();
	}
	
	/**
	 * Applies projections on given columns to the given criteria.
	 * 
	 * @param criteria the criteria on which projections are to be added.
	 * @param projectionColumns columns for which projections are to be added.
	 * @return Criteria after applying provided projections.
	 */
	public Criteria applyProjectionsToCriteria(Criteria criteria, String... projectionColumns) {
		
		ProjectionList projectionsList = Projections.projectionList();
		for (int i = 0; i < projectionColumns.length; i++) {
			projectionsList.add(Projections.property(projectionColumns[i]));
		}

		criteria.setProjection(projectionsList);
		
		return criteria;
	}
	
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
			String orderByColumnName, String order, String... projectionColumns) throws GenericException {
		Criteria criteria = createBlankCriteria(classType);
		
		criteria = applyCriteriaByMultipleConditions(classType, propertyConditionList, orderByColumnName, order);
		criteria = applyProjectionsToCriteria(criteria, projectionColumns);
		
		return executeCriteria(criteria);
	}

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
			List<PropertyConditionMatcher> propertyConditionList, String orderByColumnName, String order) throws GenericException {

		Criteria cr = createBlankCriteria(classType);
		cr = getCriteriaFromMultipleConditions(cr, propertyConditionList);
		cr = getCriteriaWithOrder(cr, orderByColumnName, order);

		return cr;
	}
	
	/**
	 * Adds AND condition to a list of conditions and adds the result to a criteria.
	 * 
	 * @param cr Criteria being worked upon
     * @param propertiesList list of various conditions, arithmetic or SQL or others as defined in objects of PropertyConditionMatcher in a list.
	 * @return criteria is updated and resent.
	 */
	public Criteria addANDConditionToCriteria(Criteria cr, List<PropertyConditionMatcher> propertiesList) throws GenericException {
		Criterion criterion = createANDCriterion(propertiesList);
		cr.add(criterion);
		return cr;
	}

	/**
	 * Adds OR condition to a list of conditions and adds the result to a criteria.
	 * 
	 * @param cr Criteria being worked upon
     * @param propertiesList list of various conditions, arithmetic or SQL or others as defined in objects of PropertyConditionMatcher in a list.
	 * @return criteria is updated and resent.
	 */
	public Criteria addORConditionToCriteria(Criteria cr, List<PropertyConditionMatcher> propertiesList) throws GenericException {
		Criterion criterion = createORCriterion(propertiesList);
		cr.add(criterion);
		return cr;
	}

	/**
	 * Creates AND criterion over a list of conditions.
	 * 
     * @param propertiesList list of various conditions, arithmetic or SQL or others as defined in objects of PropertyConditionMatcher in a list.
	 * @return criterion is created from the list of conditions.
	 */
	public Criterion createANDCriterion(List<PropertyConditionMatcher> propertiesList) throws GenericException {

		if (propertiesList.size() < 2)
			throw new GenericException("Invalid size of PropertiesList in AND opertion.");

		List<Criterion> criterions = getCriterionsList(propertiesList);

		return applyANDToCriterions(criterions);		
	}

	/**
	 * Creates OR criterion over a list of conditions.
	 * 
     * @param propertiesList list of various conditions, arithmetic or SQL or others as defined in objects of PropertyConditionMatcher in a list.
	 * @return criterion is created from the list of conditions.
	 */
	public Criterion createORCriterion(List<PropertyConditionMatcher> propertiesList) throws GenericException {
		if (propertiesList.size() < 2)
			throw new GenericException("Invalid size of PropertiesList in OR opertion.");

		List<Criterion> criterions = getCriterionsList(propertiesList);

		return applyORToCriterions(criterions);
	}

	/**
	 * Creates AND criterion from a list of criterions.
	 * 
     * @param criterions list of various criterions, arithmetic or SQL or others as created from a list of PropertyConditionMatcher.
	 * @return criterion is created from the list of criterions.
	 */
	public Criterion applyANDToCriterions(List<Criterion> criterions) {
		Criterion criterion1 = criterions.get(0);
		Criterion criterion2 = criterions.get(1);

		Criterion andCriterion = Restrictions.and(criterion1, criterion2);

		for (int i = 2; i < criterions.size(); i++) {
			criterion1 = andCriterion;
			criterion2 = criterions.get(i);
			andCriterion = Restrictions.and(criterion1, criterion2);
		}

		return andCriterion;
	}
	
	/**
	 * Creates OR criterion from a list of criterions.
	 * 
     * @param criterions list of various criterions, arithmetic or SQL or others as created from a list of PropertyConditionMatcher.
	 * @return criterion is created from the list of criterions.
	 */
	public Criterion applyORToCriterions(List<Criterion> criterions) {

		Criterion criterion1 = criterions.get(0);
		Criterion criterion2 = criterions.get(1);

		Criterion orCriterion = Restrictions.or(criterion1, criterion2);

		for (int i = 2; i < criterions.size(); i++) {
			criterion1 = orCriterion;
			criterion2 = criterions.get(i);
			orCriterion = Restrictions.or(criterion1, criterion2);
		}

		return orCriterion;
	}

	/**
	 * Creates a blank criteria
	 * 
     * @param classType the type of Class on which Criteria is to be created.
	 * @return criteria is created and returned.
	 */
	public Criteria createBlankCriteria(@SuppressWarnings("rawtypes") Class classType) {
		return sessionFactory.getCurrentSession().createCriteria(classType);
	}

	/**
	 * Execute a criteria
	 * 
     * @param criteria the Criteria to be executed.
	 * @return list of results is returned.
	 */
	@SuppressWarnings("rawtypes")
	public List executeCriteria(Criteria criteria) {
		return criteria.list();
	}

	private Criteria getCriteriaFromMultipleConditions(Criteria cr, List<PropertyConditionMatcher> propertiesList) throws GenericException {
		List<Criterion> criterions = getCriterionsList(propertiesList);

		for (Criterion criterion: criterions) {
			cr.add(criterion);
		}

		return cr;
	}

	private Criteria getCriteriaWithOrder(Criteria cr, String orderByColumnName, String order) {
		if (orderByColumnName != null && !orderByColumnName.isEmpty()) {
			if (order == null || order.isEmpty() || order.equals("asc")) {
				cr.addOrder(Order.asc(orderByColumnName));
			}
			else if (order.equals("desc")) {
				cr.addOrder(Order.desc(orderByColumnName));
			}
		}
		return cr;
	}


	private List<Criterion> getCriterionsList(List<PropertyConditionMatcher> propertiesList) throws GenericException {
		List<Criterion> criterions = new ArrayList<Criterion>();

		for(PropertyConditionMatcher pcm:propertiesList) {

			String condition = pcm.getPropertyCondition();

			switch(condition) {

			case "==":
			case "eq":
				criterions.add(Restrictions.eq(pcm.getPropertyName(), pcm.getPropertyValue()));
				break;

			case "!=":
			case "ne":
				criterions.add(Restrictions.ne(pcm.getPropertyName(), pcm.getPropertyValue()));
				break;

			case ">":
			case "gt":
				criterions.add(Restrictions.gt(pcm.getPropertyName(), pcm.getPropertyValue()));
				break;

			case ">=":
			case "ge":
				criterions.add(Restrictions.ge(pcm.getPropertyName(), pcm.getPropertyValue()));
				break;

			case "<":
			case "lt":
				criterions.add(Restrictions.lt(pcm.getPropertyName(), pcm.getPropertyValue()));
				break;

			case "<=":
			case "le":
				criterions.add(Restrictions.le(pcm.getPropertyName(), pcm.getPropertyValue()));
				break;

			case "ilike":
				criterions.add(Restrictions.ilike(pcm.getPropertyName(), pcm.getPropertyValue()));
				break;

			case "like":
				criterions.add(Restrictions.like(pcm.getPropertyName(), pcm.getPropertyValue()));
				break;

			case "isEmpty":
				criterions.add(Restrictions.isEmpty(pcm.getPropertyName()));
				break;

			case "isNotEmpty":
				criterions.add(Restrictions.isNotEmpty(pcm.getPropertyName()));
				break;

			case "isNull":
				criterions.add(Restrictions.isNull(pcm.getPropertyName()));
				break;

			case "isNotNull":
				criterions.add(Restrictions.isNotNull(pcm.getPropertyName()));
				break;

			case "in":
				criterions.add(Restrictions.in(pcm.getPropertyName(), pcm.getInValues()));
				break;

			case "between":
				criterions.add(Restrictions.between(pcm.getPropertyName(), pcm.getBetweenPropertyLowValue(), pcm.getBetweenPropertyHighValue()));
				break;

			default:
				throw new GenericException("Invalid check conditions. "
						+ "You may only enter \'==\', \'!=\', \'>\', \'>=\', \'<\', \'<=\',\'ne\', \'eq\', \'gt\', \'ge\', \'lt\', \'le\', "
						+ "\'ilike\', \'like\', \'isEmpty\', \'isNotEmpty\', \'isNull\', \'isNotNull\', \'in\', \'between\' as conditions.");
			}
		}
		return criterions;
	}
}
