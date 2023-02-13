package com.states.EmployeeStatesMS.enums;

public enum EmployeeState {
    ADDED {
        @Override
        public EmployeeState previousState() {
            return null;
        }

        @Override
        public EmployeeState nextState() {
            return IN_CHECK;
        }
    },
    IN_CHECK {
        @Override
        public EmployeeState previousState() {
            return null;
        }

        @Override
        public EmployeeState nextState() {
            return APPROVED;
        }
    },
    APPROVED {
        @Override
        public EmployeeState previousState() {
            return IN_CHECK;
        }

        @Override
        public EmployeeState nextState() {
            return ACTIVE;
        }
    },
    ACTIVE {
        @Override
        public EmployeeState previousState() {
            return null;
        }

        @Override
        public EmployeeState nextState() {
            return null;
        }
    };

    public abstract EmployeeState nextState();

    public abstract EmployeeState previousState();

}
