package cn.tedu.aircraftwardemo;

public class imageshf {
    public static void main(String[] args) {
        Bigplane bigplane=new Bigplane();
        System.out.println(bigplane.state);

        for (int i = 0; i < 120; i++) {
            bigplane.nextImages();
            System.out.println(bigplane.image);
        }
    }
}

