package LifeCycle;

public enum LifecycleState {
    NEW(false, null);
    private final boolean available;
    private final String lifecycleEvent;

    LifecycleState(boolean available, String lifecycleEvent) {
        this.available = available;
        this.lifecycleEvent = lifecycleEvent;
    }
    public String getLifecycleEvent(){
        return lifecycleEvent;
    }
    public boolean getAvailable()
    {
        return available;
    }


}
