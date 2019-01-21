package com.ebaza.tech.dao.generic;

import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sairam Updated with - lokeshmadan,raveendrat
 *
 * @param <K>- Key
 * @param <T>- class
 */
public class AbstractDaoOld<K, T> implements IRootDao<K, T> {

    public static SessionFactory factory;
    public Session session = null;
    public Transaction t = null;
    protected Class<T> entityClass;
  private String CLASSNAME = "AbstractDao :: ";

	protected EntityManager entityManager;

    private static final transient Logger LOGGER = LoggerFactory
            .getLogger(AbstractDaoOld.class);
    private static final int TRIM_CYCLE = 4;
    private static final int SPLIT_COUNT = 50;
    private static final String PERSISTANT_CLASS_IS_NULL = "Persistent Class is null";
    private static final String WHERE_CLAUSE = " where ";
    private static final String SELECT_CLAUSE = " select ";
    private static final String FROM_CLAUSE = " from ";
    private static final String WHERE_PROPERTY_HOLDER = "=?";

    @SuppressWarnings("unchecked")
    public AbstractDaoOld() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[1];
      
        session=getMyCurrentSession();
               // t = session.beginTransaction();
    }

   protected  SessionFactory hibernateFactory() {
       
       
       try {
        
            factory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(entityClass).buildSessionFactory();
            System.out.println("Create Hibernate  sessionFactory  Started.. ");
        } catch (Throwable ex) {
            System.err.println("Failed to create Hibernate sessionFactory object" + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return factory;
      
    }
    
   protected  Session getMyCurrentSession(){
    
     System.out.println("Create   Hibernate   session Started.. ");
        Session session = hibernateFactory().getCurrentSession();
        t = session.beginTransaction();
            System.out.println("Hibernate session Created.. ");
         //hibernateFactory().close();
         	session.setFlushMode(FlushMode.AUTO);
    return session;
   
    }
        @SuppressWarnings("rawtypes")
    public List getGenericListWithHQLParameter(final String[] propertyName,
            final Object[] value, final String hqlStatement)
            throws Exception {
        
        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(FROM_CLAUSE);
            strQuery.append(hqlStatement);
            strQuery.append(WHERE_CLAUSE);

            for (int i = 0; i < propertyName.length; i++) {
                strQuery.append(propertyName[i]);
                strQuery.append(WHERE_PROPERTY_HOLDER + " and ");
            }

            strQuery.delete(strQuery.length() - TRIM_CYCLE,
                    strQuery.length() - 1);
            strQuery.append(") ");

            Query query = session.createQuery(
                    strQuery.toString());

            for (int i = 0; i < value.length; i++) {
                query.setParameter(i, value[i]);
            }
            
            return query.list();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(
                    hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }finally{
         
        
        }
    }
       public List<T> getListByDateBewteenOtherCriteria(String dateColumn,Date startDate,Date endDate,final String[] propertyName, final Object[] value) throws ParseException{
   // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//Date fromDate = df.parse(startDate);
//Date toDate = df.parse(endDate);
           try{
          
Criteria cr = session.createCriteria(entityClass);
   cr.add(Restrictions.between(dateColumn, startDate , endDate ));
       for (int i = 0; i < propertyName.length; i++) {
            for (int ii = 0; ii < value.length; ii++) {
   cr.add(Restrictions.eq(propertyName[i], value[ii]));
            }
       }
      
return cr.list();
           }catch(Exception mm){
           mm.printStackTrace();
          
           }finally{
               
           }
    return null;
    }
 public T getModelWithMyHQL(final String[] propertyName, final Object[] value,
            final String hqlStatement) throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {

            StringBuilder strQuery = new StringBuilder();
            strQuery.append(hqlStatement);
            strQuery.append(WHERE_CLAUSE);

            for (int i = 0; i < propertyName.length; i++) {
                strQuery.append(propertyName[i]);
                strQuery.append(WHERE_PROPERTY_HOLDER + " and ");
            }

            strQuery.delete(strQuery.length() - TRIM_CYCLE,
                    strQuery.length() - 1);

            Query query = session.createQuery(
                    strQuery.toString());

            for (int i = 0; i < value.length; i++) {
                query.setParameter(i, value[i]);
            }

            return (T) query.uniqueResult();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex);
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }finally{
           
              
        }
    }
    public T saveIntable(T model) {
        
  LOGGER.info(CLASSNAME+"SAVE start");
        System.out.println("Transaction Started.. for save config");
        try{
         Session s=getMyCurrentSession();
        
         Transaction t = s.beginTransaction();
       s.save(model);
        t.commit();
        //s.close();
        
         LOGGER.info(CLASSNAME+"SAVE end");
         return model;
        }catch(Exception k){
        k.printStackTrace();
           if (t != null) {
                t.rollback();
            }
        }finally{
          
             
        }
         
       return null;

    }
    public long getLastRecordId(String sql){
    //final String sql = "SELECT max( i.id ) FROM Item i";
           Session s=getMyCurrentSession();
return  (Integer) s.createQuery( sql ).uniqueResult();
    }

    public T updateIntable(T model) {
try{
        System.out.println("Transaction Started.. for update config");
        Session s=getMyCurrentSession();
              Transaction t = s.beginTransaction();
       s.update(model);
        t.commit();
         return model;
       
}catch(Exception j){
    if (t != null) {
                t.rollback();
            }

}finally{   }
       
return null;
    }

    public Object getLongIn(String userName, String password) {

        Criteria cr;

      
       Object val = new Object();

        try {
              
     
            cr = session.createCriteria(entityClass);
            cr.add(Restrictions.eq("password", password));
            cr.add(Restrictions.eq("userName", userName));

            List a = cr.list();
            for (Iterator iterator = a.iterator(); iterator.hasNext();) {
                val = (Class<T>) iterator.next();

            }
            
              return val;
        } catch (Exception jj) {
    
        }finally{
         
               
        }
        
        return val;
    }
    public List<T> getListByDateBewteen(String dateColumn,Date startDate,Date endDate) throws ParseException{
   // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//Date fromDate = df.parse(startDate);
//Date toDate = df.parse(endDate);
        try{
            
Criteria criteria = session.createCriteria(entityClass)
   .add(Restrictions.between(dateColumn, startDate , endDate ));
return criteria.list();
        }catch(Exception e){
        e.printStackTrace();
       // t.rollback();
        }finally{
       
                
        }
    return null;
    }
 
public boolean creatingNewTable(){
   //  Session session = getMyCurrentSession();
     
return true;
}
    public List<Object> getModelList() {

     
        List ite = null;
         
            
       
        try {
             //  t = session.beginTransaction();
            ite = session.createQuery("FROM "+entityClass.getName()).list();

            if (!t.wasCommitted()) {
                t.commit();
            }

        } catch (HibernateException e) {

            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        } finally {
           
         
        }
        return ite;

    }

    public Object getModelById(int modelId, String PrimaryKeycolumnName) {
        Criteria cr;
        Object ob =null;
LOGGER.info("here i am in getModelById!!");
      

 
        try {
         
            cr = session.createCriteria(entityClass.getName());
            cr.add(Restrictions.eq(PrimaryKeycolumnName, modelId));
            List a = cr.list();
            for (Iterator iterator = a.iterator(); iterator.hasNext();) {
                ob = iterator.next();
System.out.println("here i am!! test");
LOGGER.info("here in for loop!!");
            }
            if (!t.wasCommitted()) {
              //  t.commit();
            }
        } catch (Exception jj) {
jj.printStackTrace();
//t.rollback();
    if (t != null) {
                t.rollback();
            }
        }finally{
           
                
        }
       
        return ob;
    }

    public Object getLongInUserDeails(String userName) {

        Criteria cr;

        Object val = new Object();

        try {
           // t= session.beginTransaction();
            cr = session.createCriteria(entityClass);

            cr.add(Restrictions.eq("userName", userName));

            List a = cr.list();
            for (Iterator iterator = a.iterator(); iterator.hasNext();) {
                val = (Class<T>) iterator.next();

            }
            if (!t.wasCommitted()) {
                t.commit();
            }
        } catch (Exception jj) {
     if (t != null) {
                t.rollback();
            }
           jj.printStackTrace();
        }finally{
        
               
        }
      
        return val;
    }

    /**
     *
     * @return
     */
   


    @Override
    public void persist(T entity) throws Exception {
        try {

            entityManager.persist(entity);

        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured.gggg {}", ex);
            throw new Exception(ex);

        }

    }

    /**
     *
     */
    @Override
    public T findById(K id) throws Exception {

        try {
            return entityManager.find(entityClass, id);
        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }

    }

    @Override
    /**
     * Find by primary key.
     */
    public T getModel(K modelId) throws Exception {
        try {
            return entityManager.find(entityClass, modelId);
        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }

    }

  

    /**
     * Find by primary key and lock.
     *
     * @param modelId
     * @param lockMode
     * @return
     * @throws Exception
     */
    public T getModel(K modelId, final LockModeType lockMode)
            throws Exception {
        try {
            return entityManager.find(entityClass, modelId, lockMode);
        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }
    }

    /**
     * Make an instance managed and persistent.
     */
    @Override
    public T insert(T model) throws Exception {

        try {
            entityManager.persist(model);
        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }
        return model;
    }

    /**
     * Refresh the state of the instance from the database, overwriting changes
     * made to the entity, if any.
     */
    @Override
    public T refresh(T model) throws Exception {

        try {
            entityManager.refresh(model);
        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }
        return model;
    }

    /**
     * Refresh the state of the instance from the database, overwriting changes
     * made to the entity, if any, and lock it with respect to given lock mode
     * type
     *
     * @param model
     * @param lockMode
     * @return
     * @throws Exception
     */
    public T refresh(T model, LockModeType lockMode) throws Exception {

        try {
            entityManager.refresh(model, lockMode);
        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }
        return model;
    }

    @Override
    public T update(T model) throws Exception {

        try {
            entityManager.merge(model);
        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }
        return model;
    }

    /**
     * Merge the state of the given entity into the current persistence context.
     */
    @Override
    public T merge(T model) throws Exception {
        try {
            entityManager.merge(model);
        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }
        return model;
    }

    /**
     * Remove the entity instance.
     */
    @Override
    public void delete(T model) throws Exception {
        try {

            entityManager.remove(model);

        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }
    }

    /**
     *
     * @param propertyName
     * @param value
     * @throws Exception
     */
    public void deleteWithSinglePropertyValue(final String propertyName,
            final Object value) throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("delete " + FROM_CLAUSE);
            strQuery.append(entityClass.getSimpleName());
            strQuery.append(WHERE_CLAUSE);
            strQuery.append(propertyName);
            strQuery.append(WHERE_PROPERTY_HOLDER);
            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());
            query.setParameter(0, value);
            query.executeUpdate();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }
    }

    /**
     *
     * @param propertyName
     * @param value
     * @param hqlStatement
     * @throws Exception
     */
    public void deleteWithMultiplePropertyValueAndHQL(
            final String[] propertyName, final Object[] value,
            final String hqlStatement) throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(hqlStatement);
            strQuery.append(WHERE_CLAUSE);

            for (int i = 0; i < propertyName.length; i++) {
                strQuery.append(propertyName[i]);
                strQuery.append(WHERE_PROPERTY_HOLDER + " and ");
            }

            strQuery.delete(strQuery.length() - TRIM_CYCLE,
                    strQuery.length() - 1);
            strQuery.append(") ");

            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());

            for (int i = 0; i < value.length; i++) {
                query.setParameter(i, value[i]);
            }

            query.executeUpdate();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }
    }

    /**
     *
     * @param propertyName
     * @param value
     * @throws Exception
     */
    public void deleteWithManyPropertyValues(final String propertyName,
            final Object[] value) throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {

            StringBuilder strQuery = new StringBuilder();
            strQuery.append("delete " + FROM_CLAUSE);
            strQuery.append(entityClass.getSimpleName());
            strQuery.append(WHERE_CLAUSE);
            strQuery.append(propertyName);
            strQuery.append(" in (");

            for (int i = 0; i < value.length; i++) {
                strQuery.append("?,");
            }

            strQuery.deleteCharAt(strQuery.length() - 1);
            strQuery.append(") ");

            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());

            for (int i = 0; i < value.length; i++) {
                query.setParameter(i, value[i]);
            }
            query.executeUpdate();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }

    }

    /**
     *
     * @param propertyName
     * @param value
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public T getModel(final String propertyName, final Object value)
            throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {

            StringBuilder strQuery = new StringBuilder();
            strQuery.append(FROM_CLAUSE);
            strQuery.append(entityClass.getSimpleName());
            strQuery.append(" as a ");
            strQuery.append(WHERE_CLAUSE);
            strQuery.append("a.");
            strQuery.append(propertyName);
            strQuery.append(WHERE_PROPERTY_HOLDER);

            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());
            query.setMaxResults(1);
            query.setParameter(0, value);

            return (T) query.uniqueResult();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }
    }

    @SuppressWarnings("unchecked")
    public List<T> getModelList(final String propertyName, final Object value)
            throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {

            StringBuilder strQuery = new StringBuilder();
            strQuery.append(FROM_CLAUSE);
            strQuery.append(entityClass.getSimpleName());
            strQuery.append(" as a ");
            strQuery.append(WHERE_CLAUSE);
            strQuery.append("a.");
            strQuery.append(propertyName);
            strQuery.append(WHERE_PROPERTY_HOLDER);

            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());
            query.setParameter(0, value);

            return query.list();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }
    }

    /**
     *
     * @param propertyName
     * @param value
     * @param lockMode
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public T getModel(final String propertyName, final Object value,
            final LockMode lockMode) throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(FROM_CLAUSE);
            strQuery.append(entityClass.getSimpleName());
            strQuery.append(" as a");
            strQuery.append(WHERE_CLAUSE);
            strQuery.append("a.");
            strQuery.append(propertyName);
            strQuery.append(WHERE_PROPERTY_HOLDER);

            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());
            query.setParameter(0, value);
            query.setLockMode("a", lockMode);

            return (T) query.uniqueResult();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param propertyName
     * @param value
     * @param hqlStatement
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public T getModelWithHQL(final String[] propertyName, final Object[] value,
            final String hqlStatement) throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {

            StringBuilder strQuery = new StringBuilder();
            strQuery.append(hqlStatement);
            strQuery.append(WHERE_CLAUSE);

            for (int i = 0; i < propertyName.length; i++) {
                strQuery.append(propertyName[i]);
                strQuery.append(WHERE_PROPERTY_HOLDER + " and ");
            }

            strQuery.delete(strQuery.length() - TRIM_CYCLE,
                    strQuery.length() - 1);

            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());

            for (int i = 0; i < value.length; i++) {
                query.setParameter(i, value[i]);
            }

            return (T) query.uniqueResult();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex);
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param propertyName
     * @param value
     * @param hqlStatement
     * @return
     * @throws Exception
     */
    public Object getObjectWithHQL(final String[] propertyName,
            final Object[] value, final String hqlStatement)
            throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(hqlStatement);
            strQuery.append(WHERE_CLAUSE);

            for (int i = 0; i < propertyName.length; i++) {
                strQuery.append(propertyName[i]);
                strQuery.append(WHERE_PROPERTY_HOLDER + " and ");
            }

            strQuery.delete(strQuery.length() - TRIM_CYCLE,
                    strQuery.length() - 1);

            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());

            for (int i = 0; i < value.length; i++) {
                query.setParameter(i, value[i]);
            }

            return query.uniqueResult();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @return @throws Exception
     */
    public List<T> getList() throws Exception {

        return getList(0, 0);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getList(final int startRecord, final int numberOfRecord)
            throws Exception {
        LOGGER.debug(" Criteria list start time " + new Date());

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {
            Criteria crit = this.getMyCurrentSession()
                    .createCriteria(entityClass);

            if (numberOfRecord != 0) {
                crit.setFirstResult(startRecord);
                crit.setMaxResults(numberOfRecord);
            }

            return crit.list();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(
                    hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param projection
     * @param fetchTable
     * @param criterion
     * @param orderBy
     * @return
     * @throws Exception
     */
    public List<T> getListWithCriteria(final Projection projection,
            final Map<String, FetchMode> fetchTable,
            final List<Criterion> criterion, final Order orderBy)
            throws Exception {
        return getListWithCriteria(projection, null, fetchTable, criterion,
                orderBy, 0, 0);
    }

    /**
     *
     * @param projection
     * @param fetchTable
     * @param criterion
     * @param orderBy
     * @param startRecord
     * @param numberOfRecord
     * @return
     * @throws Exception
     */
    public List<T> getListWithCriteria(final Projection projection,
            final Map<String, FetchMode> fetchTable,
            final List<Criterion> criterion, final Order orderBy,
            final int startRecord, final int numberOfRecord)
            throws Exception {

        return getListWithCriteria(projection, null, fetchTable, criterion,
                orderBy, startRecord, numberOfRecord);
    }

    /**
     *
     * @param projection
     * @param joinTables
     * @param fetchTable
     * @param criterion
     * @param orderBy
     * @param startRecord
     * @param numberOfRecord
     * @return
     * @throws Exception
     */
    public List<T> getListWithCriteria(final Projection projection,
            final List<String> joinTables,
            final Map<String, FetchMode> fetchTable,
            final List<Criterion> criterion, final Order orderBy,
            final int startRecord, final int numberOfRecord)
            throws Exception {

        List<Order> orderByList = new ArrayList<Order>();
        if (orderBy != null) {
            orderByList.add(orderBy);
        }
        return this.getListWithCriteria(projection, joinTables, fetchTable,
                criterion, orderByList, startRecord, numberOfRecord);

    }

    /**
     *
     * @param projection
     * @param joinTables
     * @param fetchTable
     * @param criterion
     * @param orderByList
     * @param startRecord
     * @param numberOfRecord
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<T> getListWithCriteria(final Projection projection,
            final List<String> joinTables,
            final Map<String, FetchMode> fetchTable,
            final List<Criterion> criterion, final List<Order> orderByList,
            final int startRecord, final int numberOfRecord)
            throws Exception {
        LOGGER.debug(" Criteria list start time " + new Date());

        validateEntityClass();
        try {

            Criteria crit = this.getMyCurrentSession().createCriteria(
                    entityClass, entityClass.getSimpleName());

            crit = validateJoinTablesFetchTableAndCriterion(joinTables, fetchTable,
                    criterion, crit);
            if (projection != null) {
                crit.setProjection(projection);
                crit.setResultTransformer(Transformers.aliasToBean(entityClass));
            }

            if (!orderByList.isEmpty()) {
                for (Order orderBy : orderByList) {
                    crit.addOrder(orderBy);
                }
            }

            if (numberOfRecord != 0) {
                crit.setFirstResult(startRecord);
                crit.setMaxResults(numberOfRecord);
            }

            return crit.list();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(
                    hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param hqlStatement
     * @return
     * @throws Exception
     */
    public List<T> getListWithHQL(final String hqlStatement)
            throws Exception {
        return getListWithHQL(hqlStatement, 0, 0);
    }

    /**
     *
     * @param hqlStatement
     * @param startRecord
     * @param numberOfRecord
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<T> getListWithHQL(final String hqlStatement,
            final int startRecord, final int numberOfRecord)
            throws Exception {
        try {
            Query query = this.getMyCurrentSession().createQuery(hqlStatement);

            if (numberOfRecord != 0) {
                query.setFirstResult(startRecord);
                query.setMaxResults(numberOfRecord);
            }
            return query.list();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(
                    hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param model
     * @param startRecord
     * @param numberOfRecord
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<T> getListWithModel(final T model, final int startRecord,
            final int numberOfRecord) throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {
            Example exampleModel = Example.create(model);

            Criteria crit = this.getMyCurrentSession()
                    .createCriteria(entityClass);

            crit.add(exampleModel);

            crit.setFirstResult(startRecord);
            crit.setMaxResults(numberOfRecord);

            crit.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

            return crit.list();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(
                    hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     */
    @Override
    public T saveOrUpdate(final T model) throws Exception {
        try {
            this.getMyCurrentSession().saveOrUpdate(model);
            return model;
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex.getMessage(), hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }



    @Override
    public T saveOnFlush(final T model) throws Exception {
        try {
            this.getMyCurrentSession().save(model);
            this.getMyCurrentSession().flush();
            return model;
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex.getMessage(), hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    @Override
    public T mergeT(final T model) throws Exception {
        try {
            return entityManager.merge(model);
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex.getMessage(), hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param props
     * @param propertyName
     * @param value
     * @throws Exception
     */
    public void update(final Map<String, Object> props,
            final String propertyName, final Object value)
            throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {

            StringBuilder strQuery = new StringBuilder();
            strQuery.append("update ");
            strQuery.append(entityClass.getSimpleName());
            strQuery.append(" set ");

            for (String keys : props.keySet()) {
                strQuery.append(" " + keys + "=:" + keys + ",");
            }

            strQuery.deleteCharAt(strQuery.length() - 1);
            strQuery.append(" ");

            strQuery.append(WHERE_CLAUSE + propertyName + "=:parameter");

            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());

            query.setParameter("parameter", value);

            for (String keys : props.keySet()) {
                query.setParameter(keys, props.get(keys));
            }

            query.executeUpdate();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param props
     * @param propertyName
     * @param value
     * @throws Exception
     */

    public void update(final Map<String, Object> props,
            final String propertyName, final Object value,
            final String entityClassName) throws Exception {

        validateEntityClassName(entityClassName);
        try {

            StringBuilder strQuery = new StringBuilder();
            strQuery.append("update ");
            strQuery.append(entityClassName);
            strQuery.append(" set ");

            for (String keys : props.keySet()) {
                strQuery.append(" " + keys + "=:" + keys + ",");
            }

            strQuery.deleteCharAt(strQuery.length() - 1);
            strQuery.append(" ");

            strQuery.append(WHERE_CLAUSE + propertyName + "=:parameter");

            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());

            query.setParameter("parameter", value);

            for (String keys : props.keySet()) {
                query.setParameter(keys, props.get(keys));
            }

            query.executeUpdate();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }

    }

    public void update(final Map<String, Object> props,
            final Map<String, Object> whereClause, final String entityClassName)
            throws Exception {

        validateEntityClassName(entityClassName);
        try {

            StringBuilder strQuery = new StringBuilder();
            strQuery.append("update ");
            strQuery.append(entityClassName);
            strQuery.append(" set ");
            for (String keys : props.keySet()) {
                strQuery.append(" " + keys + "=:" + keys + ",");
            }

            strQuery.deleteCharAt(strQuery.length() - 1);
            strQuery.append(" ");

            strQuery.append(WHERE_CLAUSE);

            strQuery.append(" ");

            for (String wherprop : whereClause.keySet()) {
                strQuery.append(" " + wherprop + "=:" + wherprop + " and");
            }

            String queryString = strQuery.substring(0, strQuery.length() - 3);
            Query query = this.getMyCurrentSession().createQuery(queryString);

            for (String wherprop : whereClause.keySet()) {
                query.setParameter(wherprop, whereClause.get(wherprop));
            }
            for (String keys : props.keySet()) {
                query.setParameter(keys, props.get(keys));
            }
            query.executeUpdate();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }

    }

    public int updateWithResult(final Map<String, Object> props,
            final Map<String, Object> whereClause, final String entityClassName, final LockMode lockMode, final String cond)
            throws Exception {
        int result = 0;
        validateEntityClassName(entityClassName);
        try {

            StringBuilder strQuery = new StringBuilder();
            strQuery.append("update ");
            strQuery.append(entityClassName);
            strQuery.append(" set ");
            for (String keys : props.keySet()) {
                strQuery.append(" " + keys + "=:" + keys + ",");
            }

            strQuery.deleteCharAt(strQuery.length() - 1);
            strQuery.append(" ");

            strQuery.append(WHERE_CLAUSE);

            strQuery.append(" ");

            for (String wherprop : whereClause.keySet()) {
                strQuery.append(" " + wherprop + "=:" + wherprop + " and");
            }

            strQuery.append(cond + " and");
            String queryString = strQuery.substring(0, strQuery.length() - 3);
            Query query = this.getMyCurrentSession().createQuery(queryString);

            for (String wherprop : whereClause.keySet()) {
                query.setParameter(wherprop, whereClause.get(wherprop));
            }
            for (String keys : props.keySet()) {
                query.setParameter(keys, props.get(keys));
            }

            query.setLockMode("this", lockMode);
            result = query.executeUpdate();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }

        return result;
    }

    /**
     *
     * @param propertyName
     * @param value
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public boolean isExist(final String propertyName, final Object value)
            throws Exception {
        boolean isExist = false;
        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(FROM_CLAUSE);
            strQuery.append(entityClass.getSimpleName());
            strQuery.append(" as a" + WHERE_CLAUSE + " a." + propertyName
                    + WHERE_PROPERTY_HOLDER);

            Query query = this.getMyCurrentSession()
                    .createQuery(strQuery.toString()).setParameter(0, value);
            query.setMaxResults(1);

            T model = (T) query.uniqueResult();

            if (model != null) {
                isExist = true;
            }
            return isExist;

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param propertyName
     * @param value
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public boolean isExist(final String[] propertyName, final Object[] value)
            throws Exception {
        boolean isExist = false;
        validateEntityClass();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(FROM_CLAUSE);
            strQuery.append(entityClass.getSimpleName());
            strQuery.append(WHERE_CLAUSE + propertyName[0]
                    + WHERE_PROPERTY_HOLDER);
            strQuery = validatePropertyName(propertyName, strQuery);

            Query query = this.getMyCurrentSession()
                    .createQuery(strQuery.toString()).setParameter(0, value[0]);
            for (int i = 1; i < value.length; i++) {
                if (value[i] == null) {
                    throw new HibernateException(
                            "one of the value values is null");
                }
                query.setParameter(i, value[i]);
            }

            query.setMaxResults(1);
            T model = (T) query.uniqueResult();

            if (model != null) {
                isExist = true;
            }
            return isExist;

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param propertyName
     * @param value
     * @param hqlStatement
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public boolean isExistWithHQL(final String[] propertyName,
            final Object[] value, final String hqlStatement)
            throws Exception {
        boolean isExist = false;

        validateEntityClass();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(hqlStatement);
            strQuery.append(WHERE_CLAUSE);

            for (int i = 0; i < propertyName.length; i++) {
                strQuery.append(propertyName[i]);
                strQuery.append(WHERE_PROPERTY_HOLDER + " and ");
            }

            strQuery.delete(strQuery.length() - TRIM_CYCLE,
                    strQuery.length() - 1);
            strQuery.append(") ");

            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());

            for (int i = 0; i < value.length; i++) {
                query.setParameter(i, value[i]);
            }

            T model = (T) query.uniqueResult();

            if (model != null) {
                isExist = true;
            }
            return isExist;

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param fetchTable
     * @param criterion
     * @param distinctColumn
     * @return
     * @throws Exception
     */
    public int getNumberOfRecordsWithCriteria(
            final Map<String, FetchMode> fetchTable,
            final List<Criterion> criterion, final String distinctColumn)
            throws Exception {

        return getNumberOfRecordsWithCriteria(null, fetchTable, criterion,
                distinctColumn);

    }

    /**
     *
     * @param joinTables
     * @param fetchTable
     * @param criterion
     * @param distinctColumn
     * @return
     * @throws Exception
     */
    public int getNumberOfRecordsWithCriteria(final List<String> joinTables,
            final Map<String, FetchMode> fetchTable,
            final List<Criterion> criterion, final String distinctColumn)
            throws Exception {
        LOGGER.info(" Criteria list start time " + new Date());
        validateEntityClass();

        try {
            Criteria crit = this.getMyCurrentSession()
                    .createCriteria(entityClass);

            crit = validateJoinTablesFetchTableAndCriterion(joinTables, fetchTable,
                    criterion, crit);
            if (distinctColumn != null && distinctColumn.length() != 0) {
                crit.setProjection(Projections.countDistinct(distinctColumn));
            } else {
                crit.setProjection(Projections.rowCount());
            }

            Long uniqRes = (Long) crit.uniqueResult();
            return (int) uniqRes.longValue();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(
                    hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param sequenceName
     * @return
     * @throws Exception
     */
    public String getNextSequence(final String sequenceName)
            throws Exception {

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(SELECT_CLAUSE);
            strQuery.append(sequenceName);
            strQuery.append(".nextval " + FROM_CLAUSE + " dual d");

            Query query = this.getMyCurrentSession().createSQLQuery(
                    strQuery.toString());

            return (String) query.uniqueResult();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }

    }

    /**
     *
     * @param sequenceName
     * @return
     * @throws Exception
     */
    public Boolean resetSequence(final String sequenceName)
            throws Exception {

        try {
            Query firstSelectQuery = this.getMyCurrentSession().createSQLQuery(
                    SELECT_CLAUSE + sequenceName + ".nextval " + FROM_CLAUSE
                    + " dual d");
            String maxNumber = ((BigDecimal) firstSelectQuery.uniqueResult())
                    .toString();

            Query firstAlterQuery = this.getMyCurrentSession().createSQLQuery(
                    "ALTER SEQUENCE " + sequenceName + " INCREMENT BY -"
                    + maxNumber);
            firstAlterQuery.executeUpdate();

            Query secondSelectQuery = this.getMyCurrentSession().createSQLQuery(
                    SELECT_CLAUSE + sequenceName + ".nextval " + FROM_CLAUSE
                    + " dual d");
            secondSelectQuery.uniqueResult();

            Query secondAlterQuery = this.getMyCurrentSession().createSQLQuery(
                    "ALTER SEQUENCE " + sequenceName + " INCREMENT BY 1");
            secondAlterQuery.executeUpdate();

            return Boolean.valueOf(true);
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }

    }

    /**
     *
     * @param hqlStatement
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public T getModelWithHQL(final String hqlStatement) throws Exception {
        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(hqlStatement);
            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());
            return (T) query.uniqueResult();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param hqlStatement
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public List getGenericListWithHQL(final String hqlStatement)
            throws Exception {
        try {
            Query query = this.getMyCurrentSession().createQuery(hqlStatement);
            return query.list();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(
                    hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param propertyName
     * @param value
     * @param hqlStatement
     * @return
     * @throws Exception
     */


    /**
     *
     * @param hqlStatement
     * @param parameterMap
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public int getNumberOfRecordsWithHQL(final String hqlStatement,
            final Map parameterMap) throws Exception {
        try {
            Query query = this.getMyCurrentSession().createQuery(hqlStatement);
            query = query.setProperties(parameterMap);
            return ((Long) query.uniqueResult()).intValue();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param sqlStatement
     * @param parameters
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public long getNumberOfRecordsWithSQL(final String sqlStatement,
            final Map parameters) throws Exception {
        try {
            Query query = this.getMyCurrentSession().createSQLQuery(sqlStatement);
            query = query.setProperties(parameters);
            return ((BigDecimal) query.uniqueResult()).longValue();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param modelList
     * @throws Exception
     */
    public void bulkInsert(final List<T> modelList) throws Exception {

        try {
            Session session = this.getMyCurrentSession();
            for (int i = 0; i < modelList.size(); i++) {
                session.save(modelList.get(i));

                if (i % SPLIT_COUNT == 0) {
                    session.flush();
                    session.clear();
                }
            }

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param modelList
     * @throws Exception
     */
    public void bulkUpdate(final List<T> modelList) throws Exception {

        try {
            Session session = this.getMyCurrentSession();
            for (int i = 0; i < modelList.size(); i++) {
                session.update(modelList.get(i));

                if (i % SPLIT_COUNT == 0) {
                    session.flush();
                    session.clear();
                }
            }

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param modelList
     * @throws Exception
     */
    public void bulkMerge(final List<T> modelList) throws Exception {

        try {
            Session session = this.getMyCurrentSession();
            for (int i = 0; i < modelList.size(); i++) {
                session.merge(modelList.get(i));

                if (i % SPLIT_COUNT == 0) {
                    session.flush();
                    session.clear();
                }
            }

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     *
     * @param projection
     * @param joinTables
     * @param fetchTable
     * @param criterion
     * @param orderByList
     * @param startRecord
     * @param numberOfRecord
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<T> getListWithDistinctCriteria(final Projection projection,
            final List<String> joinTables,
            final Map<String, FetchMode> fetchTable,
            final List<Criterion> criterion, final List<Order> orderByList,
            final int startRecord, final int numberOfRecord)
            throws Exception {
        LOGGER.debug(" Criteria list start time " + new Date());

        validateEntityClass();
        try {
            Criteria crit = this.getMyCurrentSession()
                    .createCriteria(entityClass);

            crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

            crit = validateJoinTablesFetchTableAndCriterion(joinTables, fetchTable,
                    criterion, crit);
            if (projection != null) {
                crit.setProjection(projection);
                crit.setResultTransformer(Transformers.aliasToBean(entityClass));
            }

            if (!orderByList.isEmpty()) {
                for (Order orderBy : orderByList) {
                    crit.addOrder(orderBy);
                }
            }

            if (numberOfRecord != 0) {
                crit.setFirstResult(startRecord);
                crit.setMaxResults(numberOfRecord);
            }

            return crit.list();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(
                    hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     * @param hqlStatement
     * @throws Exception
     */
    public void executeUpdateWithHQL(final String hqlStatement)
            throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {
            Query query = this.getMyCurrentSession().createQuery(hqlStatement);
            query.executeUpdate();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex);
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    @Override
    public List<T> getEntitiesWithPagination() throws Exception {
        return new ArrayList<T>();
    }

    @Override
    public int getNumberOfRecords(T model) throws Exception {
        return 0;
    }

    /**
     *
     * @param projection
     * @param criterion
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<T> getListWithProjectionandCriteria(
            final Projection projection, final List<Criterion> criterion)
            throws Exception {
        LOGGER.debug(" Criteria list start time " + new Date());

        validateEntityClass();
        try {

            Criteria crit = this.getMyCurrentSession().createCriteria(
                    entityClass, entityClass.getSimpleName());

            if (projection != null) {
                crit.setProjection(projection);
            }

            if (criterion != null) {
                for (Criterion crits : criterion) {
                    crit.add(crits);
                }
            }

            return crit.list();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(
                    hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> getListWithProjectionandCriteriaWithEntity(
            final Projection projection, final List<Criterion> criterion,
            String tblname) throws Exception {
        LOGGER.debug(" Criteria list start time " + new Date());

        validateEntityClass();
        try {

            Criteria crit = this.getMyCurrentSession().createCriteria(
                    entityClass, tblname);

            if (projection != null) {
                crit.setProjection(projection);
            }

            if (criterion != null) {
                for (Criterion crits : criterion) {
                    crit.add(crits);
                }
            }

            return crit.list();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(
                    hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }

    /**
     * Detach Entity by entity.
     */
    public void detach(T model) throws Exception {
        try {
            entityManager.detach(model);
        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }

    }

    /**
     * Check whether an entity is detached.
     */
    public boolean isDetached(T model) throws Exception {
        try {
            return entityManager.contains(model);
        } catch (PersistenceException pex) {
            throw new Exception(pex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }

    }

    @SuppressWarnings("unchecked")
    public List<T> getLikeModel(final String propertyName, final Object value)
            throws Exception {

        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(FROM_CLAUSE);
            strQuery.append(entityClass.getSimpleName());
            strQuery.append(" as r ");
            strQuery.append(WHERE_CLAUSE);
            strQuery.append("r.");
            strQuery.append(propertyName);
            strQuery.append(" LIKE ");
            strQuery.append("?");
            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());
            query.setParameter(0, "%" + value + "%");
            return query.list();

        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex.getCause());
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);

        }

    }

    private void validateEntityClassName(final String entityClassName)
            throws Exception {
        if (entityClassName == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
    }

    private void validateEntityClass() throws Exception {
        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
    }

    private StringBuilder validatePropertyName(final String[] propertyName,
            StringBuilder strQuery) throws Exception {
        for (int i = 1; i < propertyName.length; i++) {
            if (propertyName[i] == null || propertyName[i].length() == 0) {
                throw new HibernateException(
                        "one of the propertyName values is null or empty");
            }
            strQuery.append(" and " + propertyName[i] + WHERE_PROPERTY_HOLDER);
        }
        return strQuery;
    }

    private Criteria validateJoinTablesFetchTableAndCriterion(
            final List<String> joinTables,
            final Map<String, FetchMode> fetchTable,
            final List<Criterion> criterion, Criteria crit)
            throws Exception {
        if (joinTables != null) {
            for (String joinTable : joinTables) {
                crit.createAlias(joinTable, joinTable);
            }
        }
        if (fetchTable != null) {
            for (Entry<String, FetchMode> key : fetchTable.entrySet()) {
                crit.setFetchMode(key.getKey(), key.getValue());
            }
        }
        if (criterion != null) {
            for (Criterion crits : criterion) {
                crit.add(crits);
            }
        }

        return crit;
    }

    /**
     *
     * @param propertyName
     * @param value
     * @param hqlStatement
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public T getModelWithHQLWithLockMode(final String[] propertyName, final Object[] value,
            final String hqlStatement, final LockMode lockMode) throws Exception {

        if (entityClass == null) {
            throw new Exception(
                    PERSISTANT_CLASS_IS_NULL);
        }
        try {

            StringBuilder strQuery = new StringBuilder();
            strQuery.append(hqlStatement);
            strQuery.append(WHERE_CLAUSE);

            for (int i = 0; i < propertyName.length; i++) {
                strQuery.append(propertyName[i]);
                strQuery.append(WHERE_PROPERTY_HOLDER + " and ");
            }

            strQuery.delete(strQuery.length() - TRIM_CYCLE,
                    strQuery.length() - 1);

            Query query = this.getMyCurrentSession().createQuery(
                    strQuery.toString());

            for (int i = 0; i < value.length; i++) {
                query.setParameter(i, value[i]);
            }
            query.setLockMode("a", lockMode);

            return (T) query.uniqueResult();
        } catch (HibernateException hibex) {
            LOGGER.error(hibex.getMessage(), hibex);
            throw new Exception(hibex);
        } catch (Exception ex) {
            LOGGER.error("System Error has occured. {}", ex);
            throw new Exception(ex);
        }
    }


}
