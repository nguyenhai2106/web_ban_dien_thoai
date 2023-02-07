package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Orders_detail;
import utils.JpaUtils;

public class OrderDetailDao {
	public void createOrderDetail(List<Orders_detail> ordersDetailList) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			for (Orders_detail ordersDetail : ordersDetailList) {
				em.persist(ordersDetail);
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		em.close();
	}
}
