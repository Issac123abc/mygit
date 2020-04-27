package com.xinjing.dxg.common;

import org.hibernate.query.internal.AbstractProducedQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.query.internal.QueryImpl;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Validator;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseService {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    protected Validator validator;
    @PersistenceContext
    private EntityManager em;

    /**
     * @throws
     * @Title: find
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param hql
     * @param: @param params
     * @param: @param resultClass
     * @param: @return
     * @return: List<E>
     * @date: 2019年10月10日 上午10:03:56
     */
    protected <E> List<E> find(String hql, Map<String, Object> params,
                               Class<E> resultClass) {
        Query query = em.createQuery(hql);
        setParameter(query, params, QueryImpl.class);
        if (resultClass != null) {
            query.unwrap(QueryImpl.class).setResultTransformer(
                    Transformers.aliasToBean(resultClass));
        } else {
            query.unwrap(QueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        return query.getResultList();
    }

    /**
     * @Description:
     * @Param: [sql, params, resultClass]
     * @return: java.util.List<E>
     * @Author: lc
     * @Date: 2019/10/11 0011 14:15
     */
    protected <E> List<E> findBySql(String sql, Map<String, Object> params,
                                    Class<E> resultClass) {
        Query query = em.createNativeQuery(sql);
        setParameter(query, params, NativeQueryImpl.class);
        if (resultClass != null) {
            query.unwrap(NativeQueryImpl.class).setResultTransformer(
                    Transformers.aliasToBean(resultClass));
        } else {
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        return query.getResultList();
    }


    /**
     * @Description: 获取单行结果集 例如总数
     * @Param: [sql, params]
     * @return: java.lang.Object
     * @Author: lc
     * @Date: 2019/11/12 0012 15:55
     */
    protected <T> T findObjectBySql(String sql, Map<String, Object> params) {
        Query query = em.createNativeQuery(sql);
        setParameter(query, params, NativeQueryImpl.class);
        return (T) query.getSingleResult();
    }

    /**
     * @throws
     * @Title: query
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param hql
     * @param: @param params
     * @param: @param size
     * @param: @param number
     * @param: @param resultClass
     * @param: @return
     * @return: PageData<E>
     * @date: 2019年10月10日 上午10:03:21
     */
    protected <E> PageData<E> query(String hql, Map<String, Object> params,
                                    int size, int number, Class<E> resultClass) {

        PageData<E> page = new PageData<>();
        page.setSize(size);
        page.setNumber(number);

        String countQlString = "select count(1) "
                + removeSelect(removeOrders(hql));

        Query query = em.createQuery(countQlString);
        setParameter(query, params, QueryImpl.class);

        Long total = (Long) query.getSingleResult();
        page.setTotal(total == null ? 0 : total);
        page.initPage();

        if (page.getTotal() < 1) {
            return page;
        }

        Query query1 = em.createQuery(hql);
        setParameter(query1, params, QueryImpl.class);
        query1.setFirstResult(page.getFirstResult());
        query1.setMaxResults(page.getSize());
        if (resultClass != null) {
            query1.unwrap(QueryImpl.class).setResultTransformer(
                    Transformers.aliasToBean(resultClass));
        } else {
            query1.unwrap(QueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        page.setRows(query1.getResultList());

        return page;
    }

    /**
     * @throws
     * @Title: queryBySql
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param sql
     * @param: @param params
     * @param: @param resultClass
     * @param: @return
     * @return: List<E>
     * @date: 2019年10月10日 上午10:04:07
     */
    protected <E> List<E> queryBySql(String sql, Map<String, Object> params,
                                     Class<E> resultClass) {
        Query query = em.createNativeQuery(sql);
        setParameter(query, params, NativeQueryImpl.class);

        if (resultClass != null) {
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        } else {
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        List<E> resultList = query.getResultList();
        return resultList;
    }

    /**
     * @throws
     * @Title: pageBySql
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param sql
     * @param: @param params
     * @param: @param size
     * @param: @param number
     * @param: @param resultClass
     * @param: @return
     * @return: PageData<E>
     * @date: 2019年10月10日 上午10:04:20
     */
    protected <E> PageData<E> pageBySql(String sql, Map<String, Object> params,
                                        int size, int number, Class<E> resultClass) {
        String countSQlString = "select count(*) "
                + removeSelect(removeOrders(sql));
        return findBySql(countSQlString, sql, params, size, number, resultClass);
    }
 
    /**
     * @throws
     * @Title: findBySql
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param countSql
     * @param: @param sql
     * @param: @param params
     * @param: @param size
     * @param: @param number
     * @param: @param resultClass
     * @param: @return
     * @return: PageData<E>
     * @date: 2019年10月10日 上午10:04:28
     */
    protected <E> PageData<E> findBySql(String countSql, String sql,
                                        Map<String, Object> params, int size, int number,
                                        Class<E> resultClass) {
        PageData<E> page = new PageData<>();
        page.setSize(size);
        page.setNumber(number);
        Query query = em.createNativeQuery(countSql);
        setParameter(query, params, NativeQueryImpl.class);
        List list = query.getResultList();
        Object total = null;
        if (list != null && list.size() > 0) {
            total = list.get(0);
        }
        page.setTotal(total == null ? 0 : Long.parseLong(total.toString()));
        page.initPage();

        if (page.getTotal() < 1) {
            return page;
        }

        Query query1 = em.createNativeQuery(sql);
        setParameter(query1, params, NativeQueryImpl.class);
        query1.setFirstResult(page.getFirstResult());
        query1.setMaxResults(page.getSize());
        if (resultClass != null) {
            query1.unwrap(NativeQueryImpl.class).setResultTransformer(
                    Transformers.aliasToBean(resultClass));
        } else {
            query1.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        page.setRows(query1.getResultList());

        return page;
    }

    /**
     * @Description: 绑定参数
     * @Param: [query查询实例, parameter参数, queryImplTypeClass Query的实现类]
     * @return: void
     * @Author: lc
     * @Date: 2019/10/24 0024 17:10
     */
    protected void setParameter(Query query, Map<String, Object> parameter, Class<? extends AbstractProducedQuery> queryImplTypeClass) {
        if (parameter != null && parameter.size() > 0) {
            Set<String> keySet = parameter.keySet();
            for (String string : keySet) {
                Object value = parameter.get(string);
                // 这里考虑传入的参数是什么类型，不同类型使用的方法不同
                if (value instanceof Collection<?>) {
                    query.unwrap(queryImplTypeClass).setParameterList(
                            string, (Collection<?>) value);
                } else if (value instanceof Object[]) {
                    query.unwrap(queryImplTypeClass).setParameterList(
                            string, (Object[]) value);
                } else {
                    query.setParameter(string, value);
                }
            }
        }
    }

    /**
     * 去除hqlString的select子句。
     *
     * @param hqlString
     * @return
     */
    protected String removeSelect(String hqlString) {
        int beginPos = hqlString.toLowerCase().indexOf("from");
        return hqlString.substring(beginPos);
    }

    /**
     * 去除hql的orderBy子句。
     *
     * @param hqlString
     * @return
     */
    protected String removeOrders(String hqlString) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
                Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hqlString);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
