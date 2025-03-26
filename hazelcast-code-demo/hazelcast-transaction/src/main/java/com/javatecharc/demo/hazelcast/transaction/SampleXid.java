package com.javatecharc.demo.hazelcast.transaction;

import javax.transaction.xa.Xid;
import java.util.Arrays;

public class SampleXid implements Xid {
    private final int formatId;
    private final byte[] globalTransactionId;
    private final byte[] branchQualifier;

    public SampleXid(int formatId, byte[] globalTransactionId, byte[] branchQualifier) {
        this.formatId = formatId;
        this.globalTransactionId = globalTransactionId;
        this.branchQualifier = branchQualifier;
    }

    @Override
    public int getFormatId() {
        return formatId;
    }

    @Override
    public byte[] getGlobalTransactionId() {
        return globalTransactionId;
    }

    @Override
    public byte[] getBranchQualifier() {
        return branchQualifier;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SampleXid that = (SampleXid) obj;
        return formatId == that.formatId &&
                Arrays.equals(globalTransactionId, that.globalTransactionId) &&
                Arrays.equals(branchQualifier, that.branchQualifier);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(globalTransactionId) + Arrays.hashCode(branchQualifier);
    }
}

