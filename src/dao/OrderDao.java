package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Order;
import utils.JpaUtils;

public class OrderDao {
	public Order createOrder(Order order) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		order.setOrderDate(new Date());
		order.setOrderStatus(0);
		try {
			trans.begin();
			em.persist(order);
			em.flush();
			em.refresh(order);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
		return order;
	}

	public List<Order> findByUserMail(String queryName, String userMail) {
		EntityManager em = JpaUtils.getEntityManager();
		Query query = em.createNamedQuery(queryName);
		query.setParameter("userMail", userMail);
		List<Order> listOrder = query.getResultList();
		for (Order order : listOrder) {
			System.out.println(order.getOrderAddress());
		}
		em.close();
		return listOrder;
	}
}
