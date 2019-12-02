package model.vo;

import java.sql.Date;

public class EMP {
	
	/* VO (Value Object) == DTO (Data Transfer Object)
	 * == DO (Domain Object) ==  Entity (개체 -- 객체에서 기능을 뺀 느낌..)
	 * == row (DB에서 그냥 한 행을 쭉 꺼내오기 때문에)
	 * == record (DB쪽의 기록)
	 * 
	 * --> DB 테이블의 한 행의 정보가 기록되는 저장용 객체
	 * 
	 * 1. 반드시 캡슐화 적용 -> 모든 필드에 private
	 * 2. 기본 생성자, 모든 매개변수 있는 생성자 작성
	 * 3. 모든 필드에 대하여 get/setter
	 */
	
	private int empNo; // 사번
	private String eName; // 사원명
	private String job; // 직책
	private int mgr; // 직속상사 (manager)
	private Date hireDate; // 입사일 (java.sql.Date)
	private int sal; // 급여
	private int comm; // 커미션 (인센티브)
	private int deptNo; // 부서번호
	
	public EMP() { // 기본생성자
		// TODO Auto-generated constructor stub
	}
	
	
	public EMP(int empNo, String eName, String job, int mgr, int sal, int comm, int deptNo) {
		super();
		this.empNo = empNo;
		this.eName = eName;
		this.job = job;
		this.mgr = mgr;
		this.sal = sal;
		this.comm = comm;
		this.deptNo = deptNo;
	}


	// 모든 매개변수 있는 생성자
	public EMP(int empNo, String eName, String job, int mgr, Date hireDate, int sal, int comm, int deptNo) { // 모든 매개변수 있는 생성자
		this(empNo, eName, job, mgr, sal, comm, deptNo);
		this.hireDate = hireDate;
	}
	
	
	// GET/SETTER

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	
	// toString
	
	@Override
	public String toString() {
		return empNo + "\t" + eName + "\t" + job + "\t\t" + mgr + "\t" + hireDate
				+ "\t" + sal + "\t" + comm + "\t" + deptNo;
	}
	
	
	
	
	
	
}
