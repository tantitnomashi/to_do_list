package to_do_list.common;

public enum TaskStatusCode {
    INCOMPLETE(0), COMPLETED(1);

    private int value;

    TaskStatusCode(int numVal) {
        this.value = numVal;
    }

    public int getNumVal() {
        return value;
    }
}
