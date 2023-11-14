package LifeCycle;

public enum LifecycleState {
    NEW(false, null),
    INITIALIZING(false, "BEFORE_INIT_EVENT"),
    INITIALIZED(false, "AFTER_INIT_EVENT"),
    STARTING_PREP(false, "BEFORE_START_EVENT"),
    STARTING(true, "START_EVENT"),
    STARTED(true, "AFTER_START_EVENT"),
    STOPPING_PREP(true, "BEFORE_STOP_EVENT"),
    STOPPING(false, "STOP_EVENT"),
    STOPPED(false, "AFTER_STOP_EVENT"),
    DESTROYING(false, "BEFORE_DESTROY_EVENT"),
    DESTROYED(false, "AFTER_DESTROY_EVENT"),
    FAILED(false, null);
    private final boolean available;
    private final String lifecycleEvent;

    LifecycleState(boolean available, String lifecycleEvent) {
        this.available = available;
        this.lifecycleEvent = lifecycleEvent;
    }
    public String getLifecycleEvent(){
        return lifecycleEvent;
    }
    public boolean isAvailable()
    {
        return available;
    }


}
