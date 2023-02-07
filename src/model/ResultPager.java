package model;

import javax.persistence.EntityManager;

import utils.JpaUtils;

public class ResultPager {
	private EntityManager em;
	private String reportQueryName;
	private long currentPage;
	private long maxResults;
	private long pageSize;

	public String getReportQueryName() {
		return reportQueryName;
	}

	public void setReportQueryName(String reportQueryName) {
		this.reportQueryName = reportQueryName;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(long maxResults) {
		this.maxResults = maxResults;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getMaxPages() {
		return Math.ceilDivExact(maxResults, pageSize);
	}

	public void ResultPager(String reportQueryName, String countQueryName, long pageSize) {
		em = JpaUtils.getEntityManager();
		this.pageSize = pageSize;
		this.reportQueryName = reportQueryName;
		this.currentPage = 0;
		this.maxResults = (long) em.createNamedQuery(countQueryName).getSingleResult();
	}
}
