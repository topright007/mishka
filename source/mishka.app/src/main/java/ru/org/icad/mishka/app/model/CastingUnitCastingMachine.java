package ru.org.icad.mishka.app.model;

import ru.org.icad.mishka.app.ColumnName;
import ru.org.icad.mishka.app.TableName;

import javax.persistence.*;

@Entity
@Table(name = TableName.CU_CASTING_MACHINE)
public class CastingUnitCastingMachine {

    @Id
    @Column(name = ColumnName.CAST_MACH_ID)
    private int id;
    @OneToOne
    @JoinColumn(name = ColumnName.CU_ID)
    private CastingUnit castingUnit;
    @Column(name = "REMOULD_TIME")
    private int remouldTime;
    @Column(name = "LENGHT_BLANK_MAX")
    private int lenghtBlankMax;

    public CastingUnitCastingMachine() {
    }

    public CastingUnitCastingMachine(int id) {
        this.id = id;
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

    public int getRemouldTime() {
        return remouldTime;
    }

    public void setRemouldTime(int remouldTime) {
        this.remouldTime = remouldTime;
    }

    public int getLenghtBlankMax() {
        return lenghtBlankMax;
    }

    public void setLenghtBlankMax(int lenghtBlankMax) {
        this.lenghtBlankMax = lenghtBlankMax;
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }
}
