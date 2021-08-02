package to_do_list.common;

public enum TASK_STATUS_CODE {
    UNCOMPLETE(0), COMPLETED(1);

    private int value;

    TASK_STATUS_CODE(int numVal) {
        this.value = numVal;
    }

    public int getNumVal() {
        return value;
    }
}
