package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the Account database table.
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
		@NamedQuery(name = "Account.checkAdminLogin", query = "SELECT a FROM Account a WHERE a.userMail = :userMail AND a.password = :password AND a.accountRole = 1"),
		@NamedQuery(name = "Account.checkLogin", query = "SELECT a FROM Account a WHERE  a.userMail = :userMail AND a.password = :password") })
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_mail")
	private String userMail;

	@Column(name = "account_role")
	private int accountRole = 0;

	private String password;

	@Column(name = "user_address")
	private String userAddress;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_phone")
	private String userPhone;

	public Account() {
	}

	public String getUserMail() {
		return this.userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public int getAccountRole() {
		return this.accountRole;
	}

	public void setAccountRole(int accountRole) {
		this.accountRole = accountRole;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}