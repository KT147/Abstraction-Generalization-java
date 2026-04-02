package interfaceInfo;

public class Bird extends Animal implements FlightEnabled, Trackable{

    @Override
    public void move() {
        System.out.println("Flaps wings");
    }

    @Override
    public void takeOff() {
        System.out.println(getClass().getName() + " is taking off");
    }

    @Override
    public void land() {
        System.out.println(getClass().getName() + " is landing");
    }

    @Override
    public void fly() {
        System.out.println(getClass().getName() + " is flying");
    }

    @Override
    public void track() {
        System.out.println(getClass().getName() + " 's coordinates recorded");
    }
}
