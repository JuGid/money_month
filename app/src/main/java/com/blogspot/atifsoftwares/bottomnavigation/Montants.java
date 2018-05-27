package com.blogspot.atifsoftwares.bottomnavigation;

public class Montants {
    String name;
    int montant;
    int type; //0 for outcome //1 for income

    public Montants(){
        name="no_name";
        montant=0;
        type=0;
    }

    public Montants(String name, int montant, int type){
        this.name=name;
        this.montant=montant;
        this.type=type;
    }

    String getName(){
        return this.name;
    }

    int getMontant(){
        return this.montant;
    }

    String getTypeString(){
        if(this.type==0){
            return "-";
        }
        else{
            return "+";
        }
    }

    int getType(){
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Le montant " + getName() + "est un " + getTypeString() + " de " + getMontant() ;
    }
}
