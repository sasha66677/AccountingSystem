package HousePackage;

public class House {
    private Floor[] floors;
    public House(){
        int numFloors = (int) (Math.random()*5+4);
       floors = new Floor[numFloors];
       for (int i = 0; i < numFloors; ++i)
           floors[i] = new Floor();
    }

    public void getInfo(){
        for (int i = 0; i < floors.length; ++i)
            floors[i].getInfo();
    }
}
