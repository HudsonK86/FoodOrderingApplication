// what is this Task class for? for DeliveryRunner?
// but I made DeliveryTask class, check that
// no need both I guess, so compare and make changes and delete one class maybe

public class Task {
    private String details;
    private double fee;

    public Task(String details, double fee) {
        this.details = details;
        this.fee = fee;
    }

    public String getDetails() {
        return details;
    }

    public double getFee() {
        return fee;
    }
}
