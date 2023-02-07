package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Account;
import utils.JpaUtils;

public class AccountDao {
	public void insert(Account account) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(account);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public boolean checkAdminLogin(String userMail, String password) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userMail", userMail);
		parameters.put("password", password);
		List<Account> listAccounts = findWithNameQuery("Account.checkAdminLogin", parameters);
		if (listAccounts.size() == 1) {
			return true;
		}
		return false;
	}

	public boolean checkLogin(String userMail, String password) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userMail", userMail);
		parameters.put("password", password);
		List<Account> listAccounts = findWithNameQuery("Account.checkLogin", parameters);
		if (listAccounts.size() == 1) {
			return true;
		}
		return false;
	}

	public List<Account> findWithNameQuery(String queryName, Map<String, Object> parameters) {
		EntityManager em = JpaUtils.getEntityManager();

		Query query = em.createNamedQuery(queryName);

		Set<Entry<String, Object>> setParameters = parameters.entrySet();

		for (Entry<String, Object> entry : setParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		List<Account> results = query.getResultList();
		em.close();
		return results;
	}

	public Account findById(String userMail) {
		EntityManager em = JpaUtils.getEntityManager();
		Account account = em.find(Account.class, userMail);
		return account;
	}

	public List<Account> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Account> query = em.createNamedQuery("Account.findAll", Account.class);
		return query.getResultList();
	}

	public void update(Account account) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.merge(account);

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(String userMail) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			Account account = em.find(Account.class, userMail);

			if (account != null) {
				em.remove(account);
			} else {
				throw new Exception("Account can not found!");
			}

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

}
