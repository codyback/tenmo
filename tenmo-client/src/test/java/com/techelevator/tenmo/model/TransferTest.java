package com.techelevator.tenmo.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TransferTest {

    private User fromUser;
    private User toUser;
    private Account fromAccount;
    private Account toAccount;
    private Transfer sut;

    @Before
    public void setup() {
        fromUser = new User();
        fromUser.setId(1001L);
        fromUser.setUsername("cody");

        toUser = new User();
        toUser.setId(1002L);
        toUser.setUsername("user");

        fromAccount = new Account();
        fromAccount.setAccountId(2001L);
        fromAccount.setUser(fromUser);
        fromAccount.setBalance(BigDecimal.valueOf(1000));

        toAccount = new Account();
        toAccount.setAccountId(2002L);
        toAccount.setUser(toUser);
        toAccount.setBalance(BigDecimal.valueOf(1000));

        sut = new Transfer();
        sut.setTransferId(3001L);
        sut.setTransferStatusId(TransferStatus.PENDING);
        sut.setTransferTypeId(TransferType.REQUEST);
        sut.setAccountFrom(fromAccount);
        sut.setAccountTo(toAccount);
        sut.setAmount(BigDecimal.TEN);
    }

    @Test
    public void getTransferTypeFromIdReturnsCorrectTransferType() {
        Assert.assertEquals(TransferType.REQUEST, sut.getTransferTypeFromId(1));
        Assert.assertEquals(TransferType.SEND, sut.getTransferTypeFromId(2));
        Assert.assertEquals(TransferType.REQUEST, sut.getTransferTypeFromId(3));
        Assert.assertEquals(TransferType.REQUEST, sut.getTransferTypeFromId(-1));
    }

    @Test
    public void getTransferStatusFromIdReturnsCorrectTransferStatus() {
        Assert.assertEquals(TransferStatus.PENDING, sut.getTransferStatusFromId(1));
        Assert.assertEquals(TransferStatus.APPROVED, sut.getTransferStatusFromId(2));
        Assert.assertEquals(TransferStatus.REJECTED, sut.getTransferStatusFromId(3));
        Assert.assertEquals(TransferStatus.PENDING, sut.getTransferStatusFromId(-1));
        Assert.assertEquals(TransferStatus.PENDING, sut.getTransferStatusFromId(6));
    }

    @Test
    public void getTransferTypeReturnsCorrectTransferType() {
        Assert.assertEquals(TransferType.REQUEST, sut.getTransferType());

        sut.setTransferTypeId(TransferType.SEND);

        Assert.assertEquals(TransferType.SEND, sut.getTransferType());
    }

    @Test
    public void getTransferStatusReturnsCorrectTransferStatus() {
        Assert.assertEquals(TransferStatus.PENDING, sut.getTransferStatus());

        sut.setTransferStatusId(TransferStatus.APPROVED);
        Assert.assertEquals(TransferStatus.APPROVED, sut.getTransferStatus());

        sut.setTransferStatusId(TransferStatus.REJECTED);
        Assert.assertEquals(TransferStatus.REJECTED, sut.getTransferStatus());
    }
}
