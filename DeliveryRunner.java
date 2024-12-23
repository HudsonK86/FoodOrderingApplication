import java.util.ArrayList;
import java.util.List;

public class DeliveryRunner extends User {
    private double earnings;
    private List<Task> taskHistory = new ArrayList<>();

    public void DeliveryRunner(String name, String userID, String password)
    {
        super(name, userID, password);
        this.earnings = 0.0;
    }

    public void acceptTask(Task task)
    {
        taskHistory.add(task);
        earnings += task.getFee();
        System.out.println("Task accepted: " + task.getDetails());
    }

    public void declineTask(Task task)
    {
        System.out.println("Task declined: " + task.getDetails());
    }

    @Override
    public void login()
    {
        System.out.println("Delivery Runner logged in.");
    }
}


// just putting another DeliveryRunner class by commenting

/*
import java.util.List;
import java.util.ArrayList;

public class DeliveryRunner extends User {
    private List<DeliveryTask> taskHistory; // Aggregation with DeliveryTask

    public DeliveryRunner(String username, String password, String name, String email) {
        super(username, password, name, email, "DeliveryRunner");
        this.taskHistory = new ArrayList<>();
    }

    public void acceptTask(DeliveryTask task) {
        // Accept delivery task logic
    }

    public void declineTask(DeliveryTask task) {
        // Decline task logic
    }

    public void updateTaskStatus(DeliveryTask task, String status) {
        // Update task status logic
    }

    public double getEarnings() {
        // Calculate and return earnings for the runner
        return 0.0;
    }

    public List<DeliveryTask> getTaskHistory() {
        return taskHistory;
    }

    // Other methods as required
}

 */