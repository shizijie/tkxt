package org.pfw.framework.wjgl.dao;

import org.pfw.framework.wjgl.domain.Taoti;
import org.pfw.framework.modules.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Repository;

/**
 * 用户对象的泛型DAO类.
 */
@Repository
public class TaotiDao extends HibernateDao<Taoti, String> {
}