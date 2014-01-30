package ru.org.icad.mishka.app.model;


import ru.org.icad.mishka.app.ColumnName;
import ru.org.icad.mishka.app.TableName;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = TableName.CAST_ELECTROLIZER)
public class CastElectrolizer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    @JoinColumn(name = ColumnName.CU_ID)
    private CastingUnit castingUnit;
    @Column(name = "CAST_DATE")
    private Date date;
    @Column(name = "SHIFT")
    private int shift;
    @Column(name = "CAST_NUMBER")
    private int castNumber;
    @OneToOne
    @JoinColumn(name = ColumnName.ORDER_ID)
    private Order order;
    @Column(name = "INGOT_COUNT")
    private int ingotCount;
    @Column(name = "INGOT_IN_BLANK_COUNT")
    private int ingotInBlankCount;
    @Column(name = "IS_ENOUGH")
    private boolean isEnough;

    public CastElectrolizer() {
    }

    public CastElectrolizer(Cast cast) {
        this.setCastingUnit(cast.getCastingUnit());
        this.setDate(cast.getDate());
        this.setShift(cast.getShift());
        this.setCastNumber(cast.getCastNumber());
        this.setOrder(cast.getOrder());
        this.setIngotCount(cast.getIngotCount());
        this.setIngotInBlankCount(cast.getIngotInBlankCount());
    }

    public CastElectrolizer(Cast cast, boolean isEnough) {
        this.setCastingUnit(cast.getCastingUnit());
        this.setDate(cast.getDate());
        this.setShift(cast.getShift());
        this.setCastNumber(cast.getCastNumber());
        this.setOrder(cast.getOrder());
        this.setIngotCount(cast.getIngotCount());
        this.setIngotInBlankCount(cast.getIngotInBlankCount());
        this.setEnough(isEnough);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CastingUnit getCastingUnit() {
        return castingUnit;
    }

    public void setCastingUnit(CastingUnit castingUnit) {
        this.castingUnit = castingUnit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public int getCastNumber() {
        return castNumber;
    }

    public void setCastNumber(int castNumber) {
        this.castNumber = castNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getIngotCount() {
        return ingotCount;
    }

    public void setIngotCount(int ingotCount) {
        this.ingotCount = ingotCount;
    }

    public int getIngotInBlankCount() {
        return ingotInBlankCount;
    }

    public void setIngotInBlankCount(int ingotInBlankCount) {
        this.ingotInBlankCount = ingotInBlankCount;
    }

    public boolean isEnough() {
        return isEnough;
    }

    public void setEnough(boolean isEnough) {
        this.isEnough = isEnough;
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }
}