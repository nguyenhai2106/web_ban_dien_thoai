package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Product;
import utils.JpaUtils;

public class ProductDao {
	public List<Product> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
		return query.getResultList();
	}

	public Product findById(int productId) {
		EntityManager em = JpaUtils.getEntityManager();
		Product product = em.find(Product.class, productId);
		return product;
	}

	public List<Product> findProducts(String productBrand, String productName, int firstResult, int maxResult) {
		EntityManager em = JpaUtils.getEntityManager();
		StringBuffer query = new StringBuffer();
		query.append("select distinct p from Product p");
		query.append(" where ");
		List<String> criteria = new ArrayList<String>();
		if (productBrand != null && !productBrand.equals("Category")) {
			criteria.add("p.productBrand = :productBrand");
		}
		if (productName != null) {
			criteria.add("p.productName like :productName");
		}
		if (criteria.size() == 0) {
			throw new RuntimeException("no criteria");
		}

		for (int i = 0; i < criteria.size(); i++) {
			if (i > 0) {
				query.append(" and ");
			}
			query.append(criteria.get(i));
		}
		Query q = em.createQuery(query.toString());
		if (productBrand != null && !productBrand.equals("Category")) {
			q.setParameter("productBrand", productBrand);
		}
		if (productName != null) {
			q.setParameter("productName", "%" + productName + "%");
		}

		q.setFirstResult(firstResult * maxResult);
		q.setMaxResults(maxResult);
		List<Product> products = q.getResultList();
		return products;
	}

	public long countWithNameQuery(String queryName) {
		EntityManager em = JpaUtils.getEntityManager();
		Query query = em.createNamedQuery(queryName);
		long result = (long) query.getSingleResult();
		em.close();
		return result;
	}

	public List<Product> findWithNameQuery(String queryName, int firstResult, int maxResult) {
		EntityManager em = JpaUtils.getEntityManager();
		Query query = em.createNamedQuery(queryName);
		query.setFirstResult(firstResult * maxResult);
		query.setMaxResults(maxResult);
		List<Product> resultsList = query.getResultList();
		em.close();
		return resultsList;
	}

	public List<Product> findAllPagination(int firstPage, int pageSize) {
		return findWithNameQuery("Product.findAll", firstPage, pageSize);
	}

	public long countAllProduct() {
		return countWithNameQuery("Product.countAll");
	}

	public long countAllSearch(String productBrand, String productName) {
		EntityManager em = JpaUtils.getEntityManager();
		StringBuffer query = new StringBuffer();
		query.append("select count(p) from Product p");
		query.append(" where ");
		List<String> criteria = new ArrayList<String>();
		if (productBrand != null && !productBrand.equals("Category")) {
			criteria.add("p.productBrand = :productBrand");
		}
		if (productName != null) {
			criteria.add("p.productName like :productName");
		}
		if (criteria.size() == 0) {
			throw new RuntimeException("no criteria");
		}

		for (int i = 0; i < criteria.size(); i++) {
			if (i > 0) {
				query.append(" and ");
			}
			query.append(criteria.get(i));
		}
		Query q = em.createQuery(query.toString());
		if (productBrand != null && !productBrand.equals("Category")) {
			q.setParameter("productBrand", productBrand);
		}
		if (productName != null) {
			q.setParameter("productName", "%" + productName + "%");
		}
		return (long) q.getSingleResult();
	}

	public void insert(Product product) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(product);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(Product product) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(product);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(int productId) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			Product product = em.find(Product.class, productId);

			if (product != null) {
				em.remove(product);
			} else {
				throw new Exception("Product can not found!");
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
