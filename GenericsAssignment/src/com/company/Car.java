package com.company;

import java.util.Objects;

public class Car implements IAggregable<Car, Integer>, IDeeplyCloneable<Car> {

    private final int wheels = 4;
    private int price;
    private String color;
    private String make;

    public Car(int price, String color, String make) {
        this.price = price;
        this.color = color;
        this.make = make;

        //validate whether the input is acceptable, throw exception otherwise
        if(price <= 0 || color.isEmpty() || make.isEmpty()) {
            throw new RuntimeException("Invalid input in car constructor!");
        }
    }

    //empty constructor to allow creation of empty object for deep cloning, only available privately to deepClone()
    private Car() {
    }

    @Override
    public Integer aggregate(Integer intermediateResult) {
        if (intermediateResult == null) {
            return price;
        }
        return price + intermediateResult;
    }

    @Override
    public Car deepClone() {
        Car copy = new Car();
        copy.price = this.price;
        copy.color = this.color;
        copy.make = this.make;
        return copy;
    }


    //overridden "equals" and "hashCode" for reliably comparing equal cars
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return  price == car.price &&
                Objects.equals(color, car.color) &&
                Objects.equals(make, car.make);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wheels, price, color, make);
    }
}
