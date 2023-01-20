package com.fireDetection.cot.entities;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import com.fireDetection.cot.FieldPropertyVisibilityStrategy;
import javax.json.bind.annotation.JsonbVisibility;
import javax.persistence.Table;

@Entity
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
@Table(name = "sensorSmoke")
public class sensorSmoke {
    @Id
    private String id;
    @Column
    private double smokeValue;

    public sensorSmoke(){ };

    public String getId() {
        return id;
    }

    public double getSmoke() {
        return smokeValue;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setSmoke(double smokeValue) {
        this.smokeValue = smokeValue;
    }

    public static sensorBuilder builder() {
        return new sensorBuilder();
    }

    public static class sensorBuilder {
        private String id;
        private double smokeValue;

        public sensorBuilder WithId(String id) {
            this.id = id;
            return this;
        }
        public sensorBuilder WithSmoke(double smokeValue) {
            this.smokeValue = smokeValue;
            return this;
        }


        public sensorSmoke build() {
            sensorSmoke sensorSmoke = new sensorSmoke();
            sensorSmoke.id = id;
            sensorSmoke.smokeValue = smokeValue;
            return sensorSmoke;
        }
    }
}