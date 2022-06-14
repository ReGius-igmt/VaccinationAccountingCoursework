package ru.regiuss.client.model;

public enum ReceptionStatus {
    WAIT(0, "Ожидание"),
    PROCESS(1, "В процессе"),
    WAIT_REPORT(2, "Ожидает отчета"),
    SUCCESS(3, "Успешно"),
    DID_NOT_COME(4, "Пациент не пришел"),
    UNSUCCESS(5, "Неуспешно");

    private final int status;
    private final String displayName;

    ReceptionStatus(int status, String displayName) {
        this.status = status;
        this.displayName = displayName;
    }

    public int getStatus() {
        return status;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
