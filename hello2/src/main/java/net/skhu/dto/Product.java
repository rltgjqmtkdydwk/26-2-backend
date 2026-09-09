/*데이터를 채워서 전달하기 위한 목적의 클래스를 DTO(Data Transfer Object) 라고 부른다.*/
package net.skhu.dto;

public class Product {
	/*1. 먼저 멤버 변수 구현*/
    String name;
    int unitCost;

    public Product(String name, int unitCost) {
		super();
		this.name = name;
		this.unitCost = unitCost;
	}

    /*2. getter setter 자동 구현*/
	/*3. source - format 으로 자동 줄맞춤*/
	/*4.생성자 자동 구현*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(int unitCost) {
        this.unitCost = unitCost;
    }

}