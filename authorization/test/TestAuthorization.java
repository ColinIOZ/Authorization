import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import org.apache.catalina.tribes.util.Arrays;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.big.authorization.action.UserAction;
import com.big.authorization.dao.daoImplements.AuthorizedWebDaoImpl;
import com.big.authorization.dao.daoImplements.UserDaoImpl;
import com.big.authorization.dao.daoImplements.WebInfoDaoImpl;
import com.big.authorization.dao.daoInterface.AuthorizedWebDaoI;
import com.big.authorization.dao.daoInterface.UserDaoI;
import com.big.authorization.dao.daoInterface.WebInfoDaoI;
import com.big.authorization.dao.hibernatedao.HibernateBaseDao;
import com.big.authorization.dao.hibernatedao.HibernateUtil;
import com.big.authorization.po.AuthorizedWeb;
import com.big.authorization.po.User;
import com.big.authorization.po.WebInfo;
import com.big.authorization.po.WebapiStatistics;
import com.big.authorization.service.serviceImplements.AuthorizedWebServiceImpl;
import com.big.authorization.service.serviceImplements.UserServiceImpl;
import com.big.authorization.service.serviceInterface.AuthorizedWebServiceI;
import com.big.authorization.util.IDUtil;


public class TestAuthorization {
	/** 
	 * test the SessionFactory in HibernateUtil 
	 * the desired result : there is no error output in the console
	 * @throws Exception
	 */
	@Test
	public void testHibernateUtil() throws Exception {
		Session session  = HibernateUtil.getSession();
		System.out.println(session);
	}
	/** 
	 * test userLogin in background not in jsp 
	 * the desired result : for input userName="aaa" and userPwd="123",the output is success.
	 * 						for input userName="bbb" and userPwd="456",the output is loginError.
	 * @throws Exception
	 */
	@Test
	public void testUserLogin() throws Exception {
		HibernateBaseDao basedao = new HibernateBaseDao();
		UserDaoImpl userdao = new UserDaoImpl();
		userdao.setBasedao(basedao);
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserdao(userdao);
		UserAction userAction = new UserAction();
		userAction.setUserService(userService);
		userAction.setUsername("aaa");
		userAction.setUserpwd("123");
		System.out.println(userAction.userLogin());
		userAction.setUsername("bbb");
		userAction.setUserpwd("456");
		System.out.println(userAction.userLogin());
	}
	/**
	 * test webInfoCount in background not in jsp
	 * the desired result : for input null parameter the result is all the webinfocount
	 * @throws Exception
	 */
	@Test
	public void testGetWebInfoCount() throws Exception {
		HibernateBaseDao basedao = new HibernateBaseDao();
		int count = basedao.getCountWithHql("from WebInfo", null);
		System.out.println(count);
	}
	/**
	 * test deleteWebInfo in background not in jsp
	 * the desired result : for input one id parameter the result is true
	 * @throws Exception
	 */
	@Test
	public void testDeleteWebInfo() throws Exception {
		HibernateBaseDao basedao = new HibernateBaseDao();
		WebInfo webinfo = new WebInfo();
		webinfo = (WebInfo) basedao.getObjectById(WebInfo.class, "11111111111");
		System.out.println(webinfo);
		boolean flag = basedao.deleteById(webinfo.getClass(), "11111111111");
		System.out.println(flag);
	}
	/**
	 * test deleteWebInfos in background not in jsp
	 * the desired result : for input ids parameter the result is true
	 * @throws Exception
	 */
	@Test
	public void testDeleteWebInfos() throws Exception {
		HibernateBaseDao basedao = new HibernateBaseDao();
		Collection  webinfo = new ArrayList() ;
		webinfo.add(basedao.getObjectById(WebInfo.class, "22222222222"));
		webinfo.add(basedao.getObjectById(WebInfo.class, "3333333333333"));
		boolean flag = basedao.deleteAll(webinfo);
		System.out.println(flag);
	}
	/**
	 * test AuthorizedWebCount in background not in jsp
	 * the desired result : for input null parameter the result is all the authorizedwebcount
	 * @throws Exception
	 */
	@Test
	public void testGetAuthorizedWebCount() throws Exception {
		HibernateBaseDao basedao = new HibernateBaseDao();
		int count = basedao
				.getCountWithHql(
						"from AuthorizedWeb aw inner join fetch aw.user inner join fetch aw.web",
						null);
		System.out.println(count);
	}
	@Test
	public void a() throws Exception {
		HibernateBaseDao basedao = new HibernateBaseDao();
		WebInfoDaoImpl dao = new WebInfoDaoImpl();
		dao.setBasedao(basedao);
		int o = dao.webInfoCount(new Object[]{"webName", "555"});
		System.out.println(o);
	}

}
