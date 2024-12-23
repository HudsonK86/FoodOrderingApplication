public class DeliveryTask
{
    private String taskId;
    private DeliveryRunner runner;
    private Order order;
    private String status; // "Pending", "In Progress", "Completed", "Cancelled"

    public DeliveryTask(String taskId, DeliveryRunner runner, Order order)
    {
        this.taskId = taskId;
        this.runner = runner;
        this.order = order;
        this.status = "Pending";
    }

    public String getTaskId()
    {
        return taskId;
    }

    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }

    public DeliveryRunner getRunner()
    {
        return runner;
    }

    public void setRunner(DeliveryRunner runner)
    {
        this.runner = runner;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

}
