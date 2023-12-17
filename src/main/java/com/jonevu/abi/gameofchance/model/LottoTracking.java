/**
 * 
 */
package com.jonevu.abi.gameofchance.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.transaction.Transactional;

/**
 * @author jonev
 *
 */
@Transactional
@Entity
@Table(name = "LOTTO_TRACKING")
@SequenceGenerator(name = "LOTTO_SEQ", sequenceName = "LOTTO_SEQUENCE")
public class LottoTracking implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7383304851194874590L;
	
	@Id
    @Column(name = "LOTTO_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long lottoId;

    @Column(name = "DRAW_DATE")
    private Date drawDate;

    @Column(name = "YEAR")
    private short year;

    @Column(name = "MONTH")
    private String month;

    @Column(name = "DAY")
    private String day;

    @Column(name = "BOX1")
    private short box1;

    @Column(name = "BOX2")
    private short box2;

    @Column(name = "BOX3")
    private short box3;

    @Column(name = "BOX4")
    private short box4;

    @Column(name = "BOX5")
    private short box5;

    @Column (name = "PWR_BALL_NBR")
    private short pwrBallNbr;

    @Column(name = "MULTIPLEX")
    private String multiplex;

	/**
	 * @return the lottoId
	 */
	public Long getLottoId() {
		return lottoId;
	}

	/**
	 * @param lottoId the lottoId to set
	 */
	public void setLottoId(Long lottoId) {
		this.lottoId = lottoId;
	}

	/**
	 * @return the drawDate
	 */
	public Date getDrawDate() {
		return drawDate;
	}

	/**
	 * @param drawDate the drawDate to set
	 */
	public void setDrawDate(Date drawDate) {
		this.drawDate = drawDate;
	}

	/**
	 * @return the year
	 */
	public short getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(short year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the box1
	 */
	public short getBox1() {
		return box1;
	}

	/**
	 * @param box1 the box1 to set
	 */
	public void setBox1(short box1) {
		this.box1 = box1;
	}

	/**
	 * @return the box2
	 */
	public short getBox2() {
		return box2;
	}

	/**
	 * @param box2 the box2 to set
	 */
	public void setBox2(short box2) {
		this.box2 = box2;
	}

	/**
	 * @return the box3
	 */
	public short getBox3() {
		return box3;
	}

	/**
	 * @param box3 the box3 to set
	 */
	public void setBox3(short box3) {
		this.box3 = box3;
	}

	/**
	 * @return the box4
	 */
	public short getBox4() {
		return box4;
	}

	/**
	 * @param box4 the box4 to set
	 */
	public void setBox4(short box4) {
		this.box4 = box4;
	}

	/**
	 * @return the box5
	 */
	public short getBox5() {
		return box5;
	}

	/**
	 * @param box5 the box5 to set
	 */
	public void setBox5(short box5) {
		this.box5 = box5;
	}

	/**
	 * @return the pwrBallNbr
	 */
	public short getPwrBallNbr() {
		return pwrBallNbr;
	}

	/**
	 * @param pwrBallNbr the pwrBallNbr to set
	 */
	public void setPwrBallNbr(short pwrBallNbr) {
		this.pwrBallNbr = pwrBallNbr;
	}

	/**
	 * @return the multiplex
	 */
	public String getMultiplex() {
		return multiplex;
	}

	/**
	 * @param multiplex the multiplex to set
	 */
	public void setMultiplex(String multiplex) {
		this.multiplex = multiplex;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Lotto_tracking [lottoId=" + lottoId + ", drawDate=" + drawDate + ", year=" + year + ", month=" + month
				+ ", day=" + day + ", box1=" + box1 + ", box2=" + box2 + ", box3=" + box3 + ", box4=" + box4 + ", box5="
				+ box5 + ", pwrBallNbr=" + pwrBallNbr + ", multiplex=" + multiplex + "]";
	}    
}
