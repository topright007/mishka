package ru.org.icad.mishka.app.model;

import ru.org.icad.mishka.app.ColumnName;
import ru.org.icad.mishka.app.TableName;

import javax.persistence.*;

@Entity
@Table(name = TableName.CAST_HOUSE)
public class CastHouse {

    @Id
    @Column(name = ColumnName.CH_ID)
    private int id;
    @Column(name = ColumnName.CH_NAME)
    private String castHouseName;
    @OneToOne
    @JoinColumn(name = ColumnName.PLANT_ID)
    private Plant plant;
    @Column(name = "LADLE_TONNAGE_MAX")
    private double ladleTonnageMax;
    @Column(name = "BLANK_WEIGHT_MAX")
    private int blankWeightMax;

    public CastHouse() {
    }

    public CastHouse(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCastHouseName() {
        return castHouseName;
    }

    public void setCastHouseName(String castHouseName) {
        this.castHouseName = castHouseName;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public double getLadleTonnageMax() {
        return ladleTonnageMax;
    }

    public void setLadleTonnageMax(double ladleTonnageMax) {
        this.ladleTonnageMax = ladleTonnageMax;
    }

    public int getBlankWeightMax() {
        return blankWeightMax;
    }

    public void setBlankWeightMax(int blankWeightMax) {
        this.blankWeightMax = blankWeightMax;
    }

    @Override
    public String toString() {
        return String.valueOf(getId());
    }
}
