package com.states.EmployeeStatesMS.event;

public interface StateEvent {
    public void beginCheck();
    public void approve();
    public void unapprove();
    public void activate();
}
