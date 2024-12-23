import java.util.ArrayList;
import java.util.List;

public class Delivery extends User {
    private double earnings;
    private List<Task> taskHistory = new ArrayList<>();

    public void DeliveryRunner(String name, String userID, String password) {
        super(name, userID, password);
        this.earnings = 0.0;
    }

    public void acceptTask(Task task) {
        taskHistory.add(task);
        earnings += task.getFee();
        System.out.println("Task accepted: " + task.getDetails());
    }

    public void declineTask(Task task) {
        System.out.println("Task declined: " + task.getDetails());
    }

    @Override
    public void login() {
        System.out.println("Delivery Runner logged in.");
    }
}
