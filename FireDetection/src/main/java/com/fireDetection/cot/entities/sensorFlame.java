package com.fireDetection.cot.entities;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import com.fireDetection.cot.FieldPropertyVisibilityStrategy;
import javax.json.bind.annotation.JsonbVisibility;
import javax.persistence.Table;
@Entity
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
@Table(name = "sensorFlame")
public class sensorFlame {
    @Id
    private String id;
    @Column
    private double flameValue;

    public sensorFlame(){ };

    public String getId() {
        return id;
    }

    public double getFlame() {
        return flameValue;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setFlame(double flameValue) {
        this.flameValue = flameValue;
    }

    public static sensorBuilder builder() {
        return new sensorBuilder();
    }

    public static class sensorBuilder {
        private String id;
        private double flameValue;

        public sensorBuilder WithId(String id) {
            this.id = id;
            return this;
        }
        public sensorBuilder WithSmoke(double flameValue) {
            this.flameValue = flameValue;
            return this;
        }


        public sensorFlame build() {
            sensorFlame sensorFlame = new sensorFlame();
            sensorFlame.id = id;
            sensorFlame.flameValue = flameValue;
            return sensorFlame;
        }
    }
}