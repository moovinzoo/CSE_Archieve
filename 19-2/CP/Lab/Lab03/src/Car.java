class newCar {
    String location = "Home";
    public void driveToWork() {
        this.location = "Work";
        System.out.println("vroom vroom...");
    }
    public void whereAmI() {
        System.out.println("I am at " + this.location);
    }
    
}
