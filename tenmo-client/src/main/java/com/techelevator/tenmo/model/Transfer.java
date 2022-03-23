package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private long transferId;
    private int transferTypeId;
    private int transferStatusId;
    private Account accountFrom;
    private Account accountTo;
    private BigDecimal amount;

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public TransferType getTransferTypeFromId(int transferTypeId) {
        if (transferTypeId == 2) {
            return TransferType.SEND;
        }

        return TransferType.REQUEST;
    }

    public TransferStatus getTransferStatusFromId(int transferStatusId) {
        switch (transferStatusId) {
            case 2:
                return TransferStatus.APPROVED;
            case 3:
                return TransferStatus.REJECTED;
            default:
                return TransferStatus.PENDING;
        }
    }

    public TransferType getTransferType() {
        return getTransferTypeFromId(this.transferTypeId);
    }

    public TransferStatus getTransferStatus() {
        return getTransferStatusFromId(this.transferStatusId);
    }

    public long getTransferId() {
        return transferId;
    }

    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTransferTypeId(TransferType transferType) {
        this.transferTypeId = transferType.getValue();
    }

    public void setTransferStatusId(TransferStatus transferStatus) {
        this.transferStatusId = transferStatus.getValue();
    }

    private String getColorFromTransferStatus() {
        switch (this.getTransferStatus()) {
            case PENDING:
                return "33"; // yellow
            case APPROVED:
                return "32"; // green
            case REJECTED:
                return "31"; // red
        }

        return "0";
    }

    public String getAccountSummary(Long accountId) {
        String fromOrTo;
        if (accountId == this.accountFrom.getAccountId()) {
            fromOrTo = "To: " + this.accountTo.getUser().getUsername();
        } else {
            fromOrTo = "From: " + this.accountFrom.getUser().getUsername();
        }
        String formattedString = String.format("│%-10s %-20s%1s %-10s│",
                this.getTransferId(), fromOrTo, "$", this.getAmount());

        String statusColor = getColorFromTransferStatus();

        formattedString += (char)27 + "[" + statusColor + "m " + getTransferStatus().toString() + (char)27 + "[0m";

        return formattedString;
    }

    public String getPendingSummary(Boolean isFrom) {
        String username;
        if (isFrom) {
            username = getAccountFrom().getUser().getUsername();
        } else {
            username = getAccountTo().getUser().getUsername();
        }
        return String.format("│%-10s %-20s%1s %-10s│", getTransferId(), username, "$", getAmount());
    }

    private String getRow(String column1, String column2) {
        return String.format("\n│%20s │ %-20s│", column1, column2);
    }

    @Override
    public String toString() {
        return String.format("│%21s│ %-20s│","ID: ", getTransferId()) +
                getRow("From:", getAccountFrom().getUser().getUsername()) +
                getRow("To:", getAccountTo().getUser().getUsername()) +
                getRow("Type:", getTransferType().toString()) +
                getRow("Status:", getTransferStatus().toString()) +
                getRow("Amount:", getAmount().toString());
    }

}