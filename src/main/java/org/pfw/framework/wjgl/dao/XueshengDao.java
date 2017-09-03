package org.pfw.framework.wjgl.dao;

import org.pfw.framework.wjgl.domain.Xuesheng;
import org.pfw.framework.modules.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Repository;

/**
 * 用户对象的泛型DAO类.
 */
@Repository
public class XueshengDao extends HibernateDao<Xuesheng, String> {
}